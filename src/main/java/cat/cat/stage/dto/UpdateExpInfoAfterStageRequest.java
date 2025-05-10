package cat.cat.stage.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateExpInfoAfterStageRequest {

    @Schema(description = "경험치 증가량", example = "10.0")
    private Double exp;

    @Schema(description = "골드 획득량", example = "1000")
    private Long memberGold;

    @Schema(description = "경험치 값 증가시킬 조력자 번호 리스트(이번 stage에서 전장에 데리고간 조력자들)", example = "[1, 2, 5]]")
    private List<Long> helperIds;
}
