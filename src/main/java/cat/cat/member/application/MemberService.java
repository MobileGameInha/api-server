package cat.cat.member.application;

import cat.cat.member.domain.Member;
import cat.cat.member.domain.MemberRepository;
import cat.cat.member.dto.request.LoginRequest;
import cat.cat.member.dto.request.SignUpRequest;
import cat.cat.member.dto.response.LoginResponse;
import cat.cat.member.dto.response.MemberInfoResponse;
import cat.cat.member.dto.response.SignUpResponse;
import cat.cat.member.exception.MemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public SignUpResponse signUp(final SignUpRequest signUpRequest) {
        checkDuplicateNickname(signUpRequest.getNickname());
        final Member member = memberRepository.save(new Member(signUpRequest.getEmail(), signUpRequest.getUsername(), signUpRequest.getNickname(), signUpRequest.getPassword()));
        return new SignUpResponse(member.getId());
    }

    private void checkDuplicateNickname(final String nickname) {
        if(memberRepository.existsByNickname(nickname)) {
            throw new MemberException("이미 존재하는 회원입니다.");
        }
    }

    public MemberInfoResponse findMemberInfo(final long memberId) {
        final Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("회원 정보가 존재하지 않습니다."));

        return new MemberInfoResponse(member.getId(), member.getNickname(), member.getUsername());
    }

    @Transactional
    public LoginResponse login(final LoginRequest loginRequest) {
        final Member member = memberRepository.findByNicknameAndPassword(loginRequest.getUsername(),
                loginRequest.getPassword()).orElseThrow(() -> new MemberException("회원 정보가 존재하지 않습니다."));

        return new LoginResponse(member.getId());
    }
}
