package cat.cat.stage.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "유저의 누적 점수 기반 티어 응답")
public record TierResponse(
        @Schema(description = "계산된 유저 티어", example = "실버") String tier
) {}