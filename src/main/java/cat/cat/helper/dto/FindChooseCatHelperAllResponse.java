package cat.cat.helper.dto;

import cat.cat.helper.domain.CatHelper;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FindChooseCatHelperAllResponse {
    private List<Long> catHelperIds;

    public FindChooseCatHelperAllResponse(final List<CatHelper> catHelpers) {
        this.catHelperIds = catHelpers.stream().map(
                CatHelper::getHelperId).toList();
    }
}
