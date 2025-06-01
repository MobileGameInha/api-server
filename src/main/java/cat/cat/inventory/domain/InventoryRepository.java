package cat.cat.inventory.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    boolean existsByMemberIdAndItemNumber(final Long memberId, final Long itemNumber);
    Inventory findByMemberIdAndItemNumber(final Long memberId, final Long itemNumber);
    List<Inventory> findByMemberId(final Long memberId);
}
