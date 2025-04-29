package cat.cat.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class SignUpRequest {

    @Schema(description = "아이디", example = "devhaon25")
    private String name;

    @Schema(description = "비밀번호", example = "12345678")
    private String password;
}
