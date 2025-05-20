package cat.cat.stage.presentation;

import cat.cat.global.error.dto.ExceptionResponse;
import cat.cat.member.dto.response.SignUpResponse;
import cat.cat.stage.application.StageService;
import cat.cat.stage.dto.UpdateExpInfoAfterStageRequest;
import cat.cat.stage.dto.UpdateStageScoreRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @PostMapping("/{memberId}/{stageId}")
    public ResponseEntity<Void> updateStageScore(@PathVariable("memberId") final long memberId,
                                                 @PathVariable("stageId") final long stageId,
                                                 @RequestBody final UpdateStageScoreRequest updateStageScoreRequest) {
        stageService.updateStageScore(memberId, stageId, updateStageScoreRequest.getScore());
        return ResponseEntity.noContent().build();
    }
}
