package cat.cat.inventory.application;

import cat.cat.inventory.domain.Inventory;
import cat.cat.inventory.domain.InventoryRepository;
import cat.cat.inventory.dto.FindInventoriesResponse;
import cat.cat.inventory.dto.FindInventoryResponse;
import cat.cat.inventory.dto.InventorySaveRequest;
import cat.cat.member.domain.Member;
import cat.cat.member.domain.MemberRepository;
import cat.cat.member.exception.MemberException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void createInventory(final InventorySaveRequest saveRequest) {
        final Long memberId = saveRequest.getMemberId();
        final Long itemNumber = saveRequest.getItemNumber();

        if(inventoryRepository.existsByMemberIdAndItemNumber(memberId, itemNumber)) {
            final Inventory inventory = inventoryRepository.findByMemberIdAndItemNumber(memberId, saveRequest.getItemNumber());
            inventory.setCount(inventory.getCount() + 1);
        } else {
            inventoryRepository.save(new Inventory(saveRequest.getItemNumber(), memberId));
        }

        final Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("없는 멤버입니다."));
        member.setGold(member.getGold() - 200);
    }

    public FindInventoriesResponse getInventoryBalance(Long memberId) {
        List<Inventory> inventories = inventoryRepository.findByMemberId(memberId);

        Map<Long, Long> inventoryMap = inventories.stream()
                .collect(Collectors.toMap(
                        Inventory::getItemNumber,
                        Inventory::getCount
                ));

        List<FindInventoryResponse> result = new ArrayList<>();
        for (long itemNumber = 1; itemNumber <= 8; itemNumber++) {
            Long count = inventoryMap.getOrDefault(itemNumber, 0L);
            result.add(new FindInventoryResponse(itemNumber, count));
        }

        return new FindInventoriesResponse(result);
    }

}
