package cat.cat.stage.presentation;

import cat.cat.global.error.dto.ExceptionResponse;
import cat.cat.member.dto.response.SignUpResponse;
import cat.cat.stage.application.StageService;
import cat.cat.stage.dto.StageRankingSummaryResponse;
import cat.cat.stage.dto.TierResponse;
import cat.cat.stage.dto.UpdateExpInfoAfterStageRequest;
import cat.cat.stage.dto.UpdateStageScoreRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "stage API", description = "Stage 게임 플레이 관련 API")
@RequestMapping("/stage")
@RequiredArgsConstructor
@RestController
public class StageController {
    private final StageService stageService;

    @Operation(summary = "Stage 클리어 이후 경험치, 골드등 능력치 증가 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "능력치 업데이트 성공", content = @Content(schema = @Schema(implementation = Void.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PostMapping("/{memberId}")
    public ResponseEntity<Void> updateExpInfoAfterStage(@PathVariable("memberId") final long memberId, @RequestBody final UpdateExpInfoAfterStageRequest updateExpInfoAfterStageRequest) {
        stageService.updateExpInfoAfterStage(memberId, updateExpInfoAfterStageRequest);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Stage 점수 업데이트")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "능력치 업데이트 성공", content = @Content(schema = @Schema(implementation = Void.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PostMapping("/{memberId}/{stageId}")
    public ResponseEntity<Void> updateStageScore(@PathVariable("memberId") final long memberId,
                                                 @PathVariable("stageId") final long stageId,
                                                 @RequestBody final UpdateStageScoreRequest updateStageScoreRequest) {
        stageService.updateStageScore(memberId, stageId, updateStageScoreRequest.getScore());
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "스테이지 랭킹 조회",
            description = "특정 스테이지(stageNumber)에 대한 상위 3명의 랭킹 정보와, 본인의 랭킹 정보를 조회합니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "랭킹 조회 성공",
                            content = @Content(schema = @Schema(implementation = StageRankingSummaryResponse.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "회원 또는 스테이지 정보 없음"),
            }
    )
    @GetMapping("/{stageNumber}/{memberId}/ranking")
    public ResponseEntity<StageRankingSummaryResponse> getStageRanking(
            @Parameter(description = "조회할 스테이지 번호", example = "5")
            @PathVariable Long stageNumber,

            @Parameter(description = "랭킹을 조회할 회원 ID", example = "2")
            @PathVariable Long memberId
    ) {
        return ResponseEntity.ok(stageService.getStageRanking(stageNumber, memberId));
    }

    @GetMapping("/tier/{memberId}")
    @Operation(
            summary = "유저의 티어 조회",
            description = "모든 Stage의 최고점수 합계를 기준으로 전체 유저 중 상위 퍼센트를 계산하고 티어를 반환합니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "티어 계산 성공",
                            content = @Content(schema = @Schema(implementation = TierResponse.class))
                    )
            }
    )
    public ResponseEntity<TierResponse> getTier(
            @Parameter(description = "회원 ID", example = "1")
            @PathVariable Long memberId
    ) {
        return ResponseEntity.ok(stageService.calculateTier(memberId));
    }
}
