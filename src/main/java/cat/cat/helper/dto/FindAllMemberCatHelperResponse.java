package cat.cat.helper.dto;

import cat.cat.helper.domain.CatHelper;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FindAllMemberCatHelperResponse {
    private List<CatHelper> catHelpers;

    public FindAllMemberCatHelperResponse(final List<CatHelper> catHelpers) {
        this.catHelpers = catHelpers;
    }
}
