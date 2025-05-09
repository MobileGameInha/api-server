package cat.cat.stage.presentation;

import cat.cat.stage.application.StageService;
import cat.cat.stage.dto.UpdateExpInfoAfterStageRequest;
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

    @PostMapping("/{memberId}")
    public ResponseEntity<Void> updateExpInfoAfterStage(@PathVariable("memberId") final long memberId, @RequestBody final UpdateExpInfoAfterStageRequest updateExpInfoAfterStageRequest) {
        stageService.updateExpInfoAfterStage(memberId, updateExpInfoAfterStageRequest);
    }
}
