package cat.cat.inventory.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor @NoArgsConstructor
public class InventorySaveRequest {
    @Schema(description = "인벤토리 번호", example = "2")
    private Long itemNumber;

    @Schema(description = "멤버 ID 값", example = "5")
    private Long memberId;
}
