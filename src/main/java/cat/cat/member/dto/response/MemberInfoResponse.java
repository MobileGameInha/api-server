package cat.cat.member.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberInfoResponse {

    @Schema(description = "생성된 회원 ID", example = "75")
    private Long id;

    @Schema(description = "생성된 회원 닉네임", example = "devhaon")
    private String nickname;

    @Schema(description = "생성된 회원 유저 아이디", example = "devhaon123123")
    private String username;

    @Schema(description = "유저의 총 경험치", example = "5000")
    private Double totalExp;

    @Schema(description = "유저의 총 경험치", example = "100000")
    private Long gold;

    public MemberInfoResponse(final Long id, final String nickname, final String username,
                              final Double totalExp, final Long gold) {
        this.id = id;
        this.nickname = nickname;
        this.username = username;
        this.totalExp = totalExp;
        this.gold = gold;
    }
}
