package cat.cat.inventory.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor @NoArgsConstructor
public class FindInventoryResponse {

    @Schema(description = "인벤토리 번호", example = "2")
    private Long itemNumber;

    @Schema(description = "인벤토리 수량", example = "10")
    private Long count;
}
