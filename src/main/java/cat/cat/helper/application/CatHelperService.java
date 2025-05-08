package cat.cat.helper.application;

import cat.cat.helper.domain.CatHelper;
import cat.cat.helper.domain.CatHelperRepository;
import cat.cat.helper.exception.CatHelperException;
import cat.cat.member.domain.Member;
import cat.cat.member.domain.MemberRepository;
import cat.cat.member.exception.MemberException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CatHelperService {
    private final MemberRepository memberRepository;
    private final CatHelperRepository catHelperRepository;

    @Transactional
    public void buyCatHelper(final long memberId, final long helperId, final long helperPrice) {
        checkLackOfGoldException(memberId, helperPrice);
        checkAlreadyBuyHelper(memberId, helperId);
        catHelperRepository.save(new CatHelper(memberId, helperId));
    }

    private void checkLackOfGoldException(final long memberId, final long helperPrice) {
        final Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));
        final long memberGold = member.getGold();

        if(memberGold - helperPrice < 0) {
            throw new CatHelperException("골드가 부족합니다.");
        }
    }

    private void checkAlreadyBuyHelper(final long memberId, final long helperId) {
        if(catHelperRepository.existsByMemberIdAndHelperId(memberId, helperId)) {
            throw new CatHelperException("이미 구매한 조력자입니다.");
        }
    }

    @Transactional(readOnly = true)
    public List<CatHelper> findAllMemberCatHelpers(final long memberId) {
        return catHelperRepository.findByMemberId(memberId);
    }
}
