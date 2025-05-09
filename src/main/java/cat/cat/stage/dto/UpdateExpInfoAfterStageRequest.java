package cat.cat.stage.dto;


import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateExpInfoAfterStageRequest {
    private Long memberExp;
    private Long memberGold;
    private Map<Long, Long> exps;
}
