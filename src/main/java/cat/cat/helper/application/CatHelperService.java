package cat.cat.helper.application;

import cat.cat.helper.domain.CatHelper;
import cat.cat.helper.domain.CatHelperRepository;
import cat.cat.helper.exception.CatHelperException;
import cat.cat.member.domain.Member;
import cat.cat.member.domain.MemberRepository;
import cat.cat.member.exception.MemberException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CatHelperService {
    private final MemberRepository memberRepository;
    private final CatHelperRepository catHelperRepository;

    @Transactional
    public void buyCatHelper(final long memberId, final long helperId, final long helperPrice) {
        checkLackOfGoldException(memberId, helperPrice);
        checkAlreadyBuyHelper(memberId, helperId);
        catHelperRepository.save(new CatHelper(memberId, helperId));
    }

    private void checkLackOfGoldException(final long memberId, final long helperPrice) {
        final Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));
        final long memberGold = member.getGold();

        if(memberGold - helperPrice < 0) {
            throw new CatHelperException("골드가 부족합니다.");
        }
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
        checkMaxChooseSize(helpers.size());
        changeAllHelperInActive(memberId, helpers);
        changeRequestHelperActive(helpers);
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

    private void changeRequestHelperActive(final List<CatHelper> helpers) {
        for (final CatHelper helper : helpers) {
            helper.setActive(true);
        }
    }
}
