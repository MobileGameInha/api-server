package cat.cat.stage.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "개별 유저의 랭킹 정보")
public record StageRankResponse(
        @Schema(description = "회원 ID", example = "2") Long memberId,
        @Schema(description = "회원 닉네임", example = "세영") String memberName,
        @Schema(description = "최고 점수", example = "820") Long score,
        @Schema(description = "랭킹 (1~n위)", example = "3") int rank
) {}