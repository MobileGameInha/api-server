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
        checkDuplicateUsername(signUpRequest.getUsername());
        final Member member = memberRepository.save(new Member(signUpRequest.getEmail(), signUpRequest.getUsername(), signUpRequest.getNickname(), signUpRequest.getPassword()));
        return new SignUpResponse(member.getId());
    }

    private void checkDuplicateUsername(final String username) {
        if(memberRepository.existsByUsername(username)) {
            throw new MemberException("이미 존재하는 회원입니다.");
        }
    }

    public MemberInfoResponse findMemberInfo(final long memberId) {
        final Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("회원 정보가 존재하지 않습니다."));

        return new MemberInfoResponse(
                member.getId(),
                member.getNickname(),
                member.getUsername(),
                member.getTotalExp(),
                member.getGold(),
                member.getProfileNumber());
    }

    @Transactional
    public LoginResponse login(final LoginRequest loginRequest) {
        final Member member = memberRepository.findByUsernameAndPassword(loginRequest.getUsername(),
                loginRequest.getPassword()).orElseThrow(() -> new MemberException("회원 정보가 존재하지 않습니다."));

        return new LoginResponse(member.getId());
    }

    @Transactional
    public void updateProfileNumber(final long memberId, final long profileNumber) {
        final Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));
        member.setProfileNumber(profileNumber);
    }
}
