package cat.cat.helper.dto;

import cat.cat.helper.domain.CatHelper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FindCatHelperResponse {

    @Schema(description = "조력자 번호", example = "4")
    private Long helperId;

    @Schema(description = "조력자 경험치", example = "20.1")
    private Double exp;

    @Schema(description = "조력자 레벨", example = "20")
    private Long level;

    @Schema(description = "조력자 활성화 여부(true 이라면 전장에 데리고 가도록 활성화된 상태임을 의미)", example = "true")
    private boolean active;

    public FindCatHelperResponse(final CatHelper catHelper) {
        this.helperId = catHelper.getHelperId();
        this.exp = catHelper.getExp();
        this.level = catHelper.getLevel();
        this.active = catHelper.isActive();
    }
}
