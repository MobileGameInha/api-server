package cat.cat.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class SignUpRequest {

    @Schema(description = "아이디", example = "hihihi")
    private String username;

    @Schema(description = "이메일", example = "msung1234@naver.com")
    private String email;

    @Schema(description = "닉네임", example = "devhaon25")
    private String nickname;

    @Schema(description = "비밀번호", example = "12345678")
    private String password;
}
