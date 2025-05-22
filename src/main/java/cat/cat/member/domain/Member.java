package cat.cat.member.domain;

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
@Getter @Setter
public class Member {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String email;
    private String username;
    private String nickname;
    private String password;
    private Long gold;
    private Double totalExp;
    private Long profileNumber;

    public Member(final String email, final String username, final String nickname, final String password) {
        this.email = email;
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.gold = 0L;
        this.totalExp = 0.0;
        this.profileNumber = 0L;
    }
}
