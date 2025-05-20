package cat.cat.stage.application;

import cat.cat.helper.domain.CatHelper;
import cat.cat.helper.domain.CatHelperRepository;
import cat.cat.member.domain.Member;
import cat.cat.member.domain.MemberRepository;
import cat.cat.member.exception.MemberException;
import cat.cat.stage.domain.Stage;
import cat.cat.stage.domain.StageRepository;
import cat.cat.stage.dto.UpdateExpInfoAfterStageRequest;
import cat.cat.stage.exception.StageException;
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
    private final StageRepository stageRepository;

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

    @Transactional
    public void updateStageScore(final long memberId, final long stageId, final long score) {
        Stage stage = stageRepository.findByMemberIdAndStageNumber(memberId, stageId)
                .orElseGet(() -> {
                    Stage newStage = new Stage();
                    newStage.setMemberId(memberId);
                    newStage.setStageNumber(stageId);
                    newStage.setScore(0L);
                    newStage.setScore(score);
                    return stageRepository.save(newStage);
                });

        stage.setScore(score);
    }
}
