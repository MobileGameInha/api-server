package cat.cat.member.application;

import cat.cat.member.domain.Member;
import cat.cat.member.domain.MemberRepository;
import cat.cat.member.dto.request.SignUpRequest;
import cat.cat.member.dto.response.SignUpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public SignUpResponse signUp(final SignUpRequest signUpRequest) {
        final Member member = memberRepository.save(new Member(signUpRequest.getName(), signUpRequest.getPassword()));
        return new SignUpResponse(member.getId());
    }
}
