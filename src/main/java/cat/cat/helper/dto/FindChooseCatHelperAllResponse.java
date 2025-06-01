package cat.cat.helper.dto;

import cat.cat.helper.domain.CatHelper;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FindChooseCatHelperAllResponse {

    @Schema(description = "전장에 데리고 온 조력자 번호 리스트", example = "[1, 2, 5]")
    private List<Long> catHelperIds;

    public FindChooseCatHelperAllResponse(final List<Long> catHelpers) {
        this.catHelperIds = catHelpers;
    }
}
