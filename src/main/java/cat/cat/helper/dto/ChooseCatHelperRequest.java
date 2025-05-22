package cat.cat.helper.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChooseCatHelperRequest {

    @Schema(description = "전장에 데리고 갈 조력자 번호 리스트", example = "[1, 2, 5]")
    private List<Long> helperIds;

    public ChooseCatHelperRequest(final List<Long> helperIds) {
        this.helperIds = helperIds;
    }
}
