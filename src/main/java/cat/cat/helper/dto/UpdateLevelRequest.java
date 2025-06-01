package cat.cat.helper.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor @NoArgsConstructor
public class UpdateLevelRequest {

    @Schema(
            description = "사용할 아이템 번호와 수량 (itemNumber → 사용 수량)",
            example = "{ \"1\": 3, \"5\": 2 }"
    )
    private Map<Long, Long> itemCount;
}
