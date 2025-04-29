package cat.cat.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @Schema(description = "회원 아이디", example = "devhaon")
    private String username;

    @Schema(description = "회원 비밀번호", example = "12345678")
    private String password;
}
