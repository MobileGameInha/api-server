package cat.cat.helper.application;

import cat.cat.helper.domain.CatHelper;
import cat.cat.helper.domain.CatHelperRepository;
import cat.cat.helper.exception.CatHelperException;
import cat.cat.inventory.domain.Inventory;
import cat.cat.inventory.domain.InventoryRepository;
import cat.cat.member.domain.Member;
import cat.cat.member.domain.MemberRepository;
import cat.cat.member.exception.MemberException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CatHelperService {
    private final MemberRepository memberRepository;
    private final CatHelperRepository catHelperRepository;
    private final InventoryRepository inventoryRepository;

    @Transactional
    public void buyCatHelper(final long memberId, final long helperId, final long helperPrice) {
        checkLackOfGoldExceptionAndDecreaseGold(memberId, helperPrice);
        checkAlreadyBuyHelper(memberId, helperId);
        catHelperRepository.save(new CatHelper(memberId, helperId));
    }

    private void checkLackOfGoldExceptionAndDecreaseGold(final long memberId, final long helperPrice) {
        final Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));
        final long memberGold = member.getGold();

        if(memberGold - helperPrice < 0) {
            throw new CatHelperException("골드가 부족합니다.");
        }

        member.setGold(memberGold - helperPrice);
    }

    private void checkAlreadyBuyHelper(final long memberId, final long helperId) {
        if(catHelperRepository.existsByMemberIdAndHelperId(memberId, helperId)) {
            throw new CatHelperException("이미 구매한 조력자입니다.");
        }
    }

    @Transactional(readOnly = true)
    public List<CatHelper> findAllMemberCatHelpers(final long memberId) {
        return catHelperRepository.findByMemberId(memberId);
    }

    @Transactional
    public void chooseCatHelpers(final long memberId, final List<Long> helperIds) {
        // 1. 해당 memberId의 모든 CatHelper 를 비활성화
        // 2. helperId가 일치하는 것들만 다시 활성화
        final List<CatHelper> helpers = catHelperRepository.findByMemberId(memberId);
        checkMaxChooseSize(helperIds.size());
        changeAllHelperInActive(memberId, helpers);
        changeRequestHelperActive(memberId, helperIds);
    }

    private void checkMaxChooseSize(final long size) {
        if(size > 3) {
            throw new CatHelperException("조력자는 최대 3마리만 선택 가능합니다.");
        }
    }

    private void changeAllHelperInActive(final long memberId, final List<CatHelper> helpers) {
        for (final CatHelper helper : helpers) {
            helper.setActive(false);
        }
    }

    private void changeRequestHelperActive(final Long memberId, final List<Long> helperIds) {
        for(final Long helperId : helperIds) {
            if(helperId == 0) {
                continue;
            }
            final CatHelper catHelper = catHelperRepository.findByMemberIdAndHelperId(memberId, helperId);
            catHelper.setActive(true);
        }
    }

    public List<Long> findChooseCatHelpers(final long memberId) {
        final List<CatHelper> activeHelpers = catHelperRepository.findAllByMemberIdAndActiveTrue(memberId);
        final List<Long> helperIds = activeHelpers.stream()
                .map(CatHelper::getHelperId)
                .collect(Collectors.toList());

        while(helperIds.size() < 3) {
            helperIds.add(0L);
        }
        return helperIds.subList(0, 3);
    }

    public CatHelper findHelperDetail(final long memberId, final long helperId) {
        return catHelperRepository.findByMemberIdAndHelperId(memberId, helperId);
    }

    @Transactional
    public void updateHelperLevel(final long memberId, final long helperId,
                                  final List<Long> itemNumbers, final int itemCount) {

        final CatHelper catHelper = catHelperRepository.findByMemberIdAndHelperId(memberId, helperId);

        if (catHelper.getLevel() >= 5) {
            throw new CatHelperException("조력자의 최대 Level은 5입니다.");
        }

        // 인벤토리 조회 및 Map 변환
        List<Inventory> inventories = inventoryRepository.findByMemberId(memberId);
        Map<Long, Inventory> inventoryMap = inventories.stream()
                .collect(Collectors.toMap(Inventory::getItemNumber, inv -> inv));

        // 아이템 수량 차감
        for (Long itemNumber : itemNumbers) {
            Inventory inventory = inventoryMap.get(itemNumber);
            if (inventory == null || inventory.getCount() < itemCount) {
                throw new IllegalArgumentException("재고가 부족한 아이템: " + itemNumber);
            }

            inventory.setCount(inventory.getCount() - itemCount);
        }

        // 조력자 레벨업
        catHelper.setLevel(catHelper.getLevel() + 1);
    }

}
