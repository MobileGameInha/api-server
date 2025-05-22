package cat.cat.stage.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStageScoreRequest {
    @Schema(description = "점수 증가량", example = "10")

    private Long score;
}
