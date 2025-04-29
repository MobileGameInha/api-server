package cat.cat.member.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberInfoResponse {

    @Schema(description = "생성된 회원 ID", example = "75")
    private Long id;

    @Schema(description = "생성된 회원 닉네임", example = "devhaon")
    private String name;
}
