package cat.cat.helper.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BuyCatHelperRequest {

    @Schema(description = "조력자 구매 가격", example = "100")
    private Long helperPrice;
}
