package cat.cat.inventory.presentation;

import cat.cat.global.error.dto.ExceptionResponse;
import cat.cat.inventory.application.InventoryService;
import cat.cat.inventory.dto.FindInventoryResponse;
import cat.cat.inventory.dto.InventorySaveRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "inventory API", description = "인벤토리 관련 API")
@RequestMapping("/inventory")
@RequiredArgsConstructor
@RestController
public class InventoryController {
    private final InventoryService inventoryService;

    @Operation(summary = "인벤토리 잔고량 1만큼 증가시키기 (없으면 신규 인벤토리 저장하고, 잔고량 1로 셋팅함)API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "능력치 업데이트 성공", content = @Content(schema = @Schema(implementation = Void.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PostMapping
    public ResponseEntity<Void> createInventory(@RequestBody final InventorySaveRequest saveRequest) {
        inventoryService.createInventory(saveRequest);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "멤버가 보유한 인벤토리 리스트 조회 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "인벤토리 리스트 조회 성공",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = FindInventoryResponse.class)))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "잘못된 요청",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))
            )
    })
    @GetMapping("{memberId}")
    public ResponseEntity<List<FindInventoryResponse>> findInventorys(@PathVariable Long memberId) {
        return ResponseEntity.ok(inventoryService.getInventoryBalance(memberId));
    }
}