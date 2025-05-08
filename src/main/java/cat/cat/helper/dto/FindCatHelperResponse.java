package cat.cat.helper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FindCatHelperResponse {
    private Long helperId;
    private Long exp;
    private Long level;
    private boolean active;
}
