package cat.cat.helper.dto;

import cat.cat.helper.domain.CatHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FindCatHelperResponse {
    private Long helperId;
    private Double exp;
    private Long level;
    private boolean active;

    public FindCatHelperResponse(final CatHelper catHelper) {
        this.helperId = catHelper.getHelperId();
        this.exp = catHelper.getExp();
        this.level = catHelper.getLevel();
        this.active = catHelper.isActive();
    }
}
