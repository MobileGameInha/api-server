package cat.cat.helper.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CatHelper {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Long memberId;
    private Long helperId;
    public Long exp;
    public Long level;
    public boolean active;

    public CatHelper(final long memberId, final long helperId) {
        this.memberId = memberId;
        this.helperId = helperId;
        this.exp = 0L;
        this.level = 0L;
        this.active = false;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setLevel(Long level) {
        this.level = level;
    }
}
