package cat.cat.stage.application;

import cat.cat.helper.domain.CatHelper;
import cat.cat.helper.domain.CatHelperRepository;
import cat.cat.member.domain.Member;
import cat.cat.member.domain.MemberRepository;
import cat.cat.member.exception.MemberException;
import cat.cat.stage.dto.UpdateExpInfoAfterStageRequest;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class StageService {
    private final MemberRepository memberRepository;
    private final CatHelperRepository catHelperRepository;

    public void updateExpInfoAfterStage(final long memberId, final UpdateExpInfoAfterStageRequest expInfoAfterStageRequest) {
        updateMemberExpInfo(memberId, expInfoAfterStageRequest);
        updateHelperExpInfo(memberId, expInfoAfterStageRequest.getHelperIds(), expInfoAfterStageRequest.getExp());
    }

    private void updateMemberExpInfo(final long memberId, final UpdateExpInfoAfterStageRequest expInfoAfterStageRequest) {
        final Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));

        member.setGold(member.getGold() + expInfoAfterStageRequest.getMemberGold());
        member.setTotalExp(member.getTotalExp() + expInfoAfterStageRequest.getExp());
    }

    private void updateHelperExpInfo(final long memberId, final List<Long> helperIds, final Double exp) {
        for(final Long helperId : helperIds) {
            final CatHelper catHelper = catHelperRepository.findByMemberIdAndHelperId(memberId, helperId);
            catHelper.setExp(catHelper.getExp() + exp);
        }
    }
}
