package cat.cat.member.presentation;

import cat.cat.member.application.MemberService;
import cat.cat.member.dto.request.LoginRequest;
import cat.cat.member.dto.request.SignUpRequest;
import cat.cat.member.dto.response.LoginResponse;
import cat.cat.member.dto.response.MemberInfoResponse;
import cat.cat.member.dto.response.SignUpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/member")
@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/sign")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody final SignUpRequest signUpRequest) {
        return ResponseEntity.ok(memberService.signUp(signUpRequest));
    }

    @GetMapping("/info/{memberId}")
    public ResponseEntity<MemberInfoResponse> findMemberInfo(@PathVariable("memberId") final Long memberId) {
        return ResponseEntity.ok(memberService.findMemberInfo(memberId));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody final LoginRequest loginRequest) {
        return ResponseEntity.ok(memberService.login(loginRequest));
    }
}
