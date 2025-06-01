package cat.cat.inventory.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @AllArgsConstructor @NoArgsConstructor
public class FindInventoriesResponse {
    private List<FindInventoryResponse> inventories;
}
