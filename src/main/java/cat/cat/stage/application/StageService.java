package cat.cat.stage.application;

import cat.cat.helper.domain.CatHelper;
import cat.cat.helper.domain.CatHelperRepository;
import cat.cat.member.domain.Member;
import cat.cat.member.domain.MemberRepository;
import cat.cat.member.exception.MemberException;
import cat.cat.stage.domain.Stage;
import cat.cat.stage.domain.StageRepository;
import cat.cat.stage.dto.StageRankResponse;
import cat.cat.stage.dto.StageRankingSummaryResponse;
import cat.cat.stage.dto.TierResponse;
import cat.cat.stage.dto.UpdateExpInfoAfterStageRequest;
import cat.cat.stage.exception.StageException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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

    @Transactional
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
            if(helperId == 0) {
                continue;
            }
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


    public StageRankingSummaryResponse getStageRanking(Long stageNumber, Long memberId) {
        List<Stage> stages = stageRepository.findAllByStageNumberOrderByScoreDesc(stageNumber);

        List<StageRankResponse> topRanks = new ArrayList<>();
        StageRankResponse myRank = null;

        int rank = 1;
        for (Stage s : stages) {
            Member m = memberRepository.findById(s.getMemberId())
                    .orElseThrow(() -> new MemberException("회원 없음"));

            StageRankResponse response = new StageRankResponse(
                    m.getId(),
                    m.getNickname(),
                    s.getScore(),
                    rank,
                    m.getProfileNumber(),
                    m.getTotalExp()
            );

            if (rank <= 3) {
                topRanks.add(response);
            }
            if (s.getMemberId().equals(memberId)) {
                myRank = response;
            }
            rank++;
        }

        // topRanks의 길이를 3으로 보장
        while (topRanks.size() < 3) {
            topRanks.add(new StageRankResponse(
                    0L, "", 0L, 0, 0L, 0.0
            ));
        }

        return new StageRankingSummaryResponse(topRanks, myRank);
    }


    public TierResponse calculateTier(Long memberId) {
        Map<Long, Long> userTotalScoreMap = stageRepository.findAll().stream()
                .collect(Collectors.groupingBy(Stage::getMemberId,
                        Collectors.summingLong(Stage::getScore)));

        Long myTotalScore = userTotalScoreMap.getOrDefault(memberId, 0L);

    // 점수 기준 내림차순 정렬 (중복 점수 고려)
            List<Long> sortedScores = userTotalScoreMap.values().stream()
                    .sorted(Comparator.reverseOrder())
                    .toList();

    // 점수별 순위 매핑
            Map<Long, Integer> scoreToRankMap = new HashMap<>();
            int rankCounter = 1;
            for (Long score : sortedScores) {
                if (!scoreToRankMap.containsKey(score)) {
                    scoreToRankMap.put(score, rankCounter);
                }
                rankCounter++;
            }

    // 내 순위 계산
        Integer rank = scoreToRankMap.get(myTotalScore);
        if (rank == null) {
            return new TierResponse("Unranked");
        }

        int totalUsers = sortedScores.size();
        double percentile = ((double) rank / totalUsers) * 100;

        String tier;
        if (percentile <= 5) tier = "Challenger";
        else if (percentile <= 20) tier = "Master";
        else if (percentile <= 40) tier = "Diamond";
        else if (percentile <= 60) tier = "Gold";
        else if (percentile <= 80) tier = "Bronze";
        else tier = "Unranked";

        return new TierResponse(tier);
    }

}
