package cat.cat.helper.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CatHelper {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Long memberId;
    private Long helperId;
    public Double exp;
    public Long level;
    public boolean active;

    public CatHelper(final long memberId, final long helperId) {
        this.memberId = memberId;
        this.helperId = helperId;
        this.exp = 0.0;
        this.level = 0L;
        this.active = false;
    }

    public CatHelper(final long memberId, final long helperId, final long level, final Double exp) {
        this.memberId = memberId;
        this.helperId = helperId;
        this.exp = exp;
        this.level = level;
        this.active = false;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setLevel(Long level) {
        this.level = level;
    }
}
