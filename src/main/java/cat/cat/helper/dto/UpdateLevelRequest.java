package cat.cat.helper.dto;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor @NoArgsConstructor
public class UpdateLevelRequest {
    private Map<Long, Long> itemCount;
}
