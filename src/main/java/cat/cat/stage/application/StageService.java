package cat.cat.stage.application;

import cat.cat.helper.domain.CatHelper;
import cat.cat.helper.domain.CatHelperRepository;
import cat.cat.member.domain.Member;
import cat.cat.member.domain.MemberRepository;
import cat.cat.member.exception.MemberException;
import cat.cat.stage.dto.UpdateExpInfoAfterStageRequest;
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
        updateHelperExpInfo(memberId, expInfoAfterStageRequest.getExps());
    }

    private void updateMemberExpInfo(final long memberId, final UpdateExpInfoAfterStageRequest expInfoAfterStageRequest) {
        final Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));

        member.setGold(member.getGold() + expInfoAfterStageRequest.getMemberGold());
        member.setTotalExp(member.getTotalExp() + expInfoAfterStageRequest.getMemberExp());
    }

    private void updateHelperExpInfo(final long memberId, final Map<Long, Long> exps) {
        for(Map.Entry<Long, Long> expInfo : exps.entrySet()) {
            final CatHelper catHelper = catHelperRepository.findByMemberIdAndHelperId(memberId, expInfo.getKey());
            catHelper.setExp(catHelper.getExp() + expInfo.getValue());
        }
    }
}
