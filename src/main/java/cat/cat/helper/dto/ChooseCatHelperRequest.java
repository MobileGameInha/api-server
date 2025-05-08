package cat.cat.helper.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class ChooseCatHelperRequest {
    private final List<Long> helperIds;

    public ChooseCatHelperRequest(final List<Long> helperIds) {
        this.helperIds = helperIds;
    }
}
