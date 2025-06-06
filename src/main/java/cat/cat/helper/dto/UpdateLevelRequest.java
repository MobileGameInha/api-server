package cat.cat.helper.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLevelRequest {

    @Schema(
            description = "사용할 아이템 번호 리스트",
            example = "[1, 2, 5]"
    )
    private List<Long> items;

    @Schema(
            description = "각 아이템별로 차감할 수량 (모든 아이템에 동일하게 적용)",
            example = "5"
    )
    private int itemCount;
}