package cat.cat.inventory.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Entity
@AllArgsConstructor @NoArgsConstructor
public class Inventory {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Long itemNumber;
    private Long count;
    private Long memberId;

    public Inventory(final Long itemNumber, final Long memberId) {
        this.itemNumber = itemNumber;
        this.count = 1L;
        this.memberId = memberId;
    }

    public Inventory(final Long itemNumber, final Long memberId, final Long count) {
        this.itemNumber = itemNumber;
        this.count = count;
        this.memberId = memberId;
    }
}
