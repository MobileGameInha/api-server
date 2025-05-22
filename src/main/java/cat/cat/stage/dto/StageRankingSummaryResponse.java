package cat.cat.stage.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;


@Schema(description = "스테이지 랭킹 요약 정보")
public record StageRankingSummaryResponse(
        @Schema(description = "상위 1~3등 랭커 목록") List<StageRankResponse> topRanks,
        @Schema(description = "본인의 랭킹 정보") StageRankResponse myRank
) {}