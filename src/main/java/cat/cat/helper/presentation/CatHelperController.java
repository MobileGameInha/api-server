package cat.cat.helper.presentation;

import cat.cat.global.error.dto.ExceptionResponse;
import cat.cat.helper.application.CatHelperService;
import cat.cat.helper.domain.CatHelper;
import cat.cat.helper.dto.BuyCatHelperRequest;
import cat.cat.helper.dto.ChooseCatHelperRequest;
import cat.cat.helper.dto.FindAllMemberCatHelperResponse;
import cat.cat.helper.dto.FindCatHelperResponse;
import cat.cat.helper.dto.FindChooseCatHelperAllResponse;
import cat.cat.helper.dto.UpdateLevelRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "cat helper api", description = "조력자 관련 API")
@RequestMapping("/helper")
@RequiredArgsConstructor
@RestController
public class CatHelperController {
    private final CatHelperService catHelperService;

    @Operation(summary = "조력자 구매")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "능력치 업데이트 성공", content = @Content(schema = @Schema(implementation = Void.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @Parameter(name = "memberId", description = "회원의 ID 값", example = "75", required = true)
    @Parameter(name = "helperId", description = "구매할 조력자 번호", example = "5", required = true)
    @PostMapping("/buy/{memberId}/{helperId}")
    public ResponseEntity<Void> buyCatHelper(@PathVariable("memberId") final long memberId, @PathVariable("helperId") final long helperId, @RequestBody final BuyCatHelperRequest buyCatHelperRequest) {
        catHelperService.buyCatHelper(memberId, helperId, buyCatHelperRequest.getHelperPrice());
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "회원이 보유한 모든 조력자 목록 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조력자 리스트 & 상세정보", content = @Content(schema = @Schema(implementation = FindAllMemberCatHelperResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @Parameter(name = "memberId", description = "회원의 ID 값", example = "75", required = true)
    @GetMapping("/all/{memberId}")
    public ResponseEntity<FindAllMemberCatHelperResponse> findAllMemberCatHelpers(@PathVariable final long memberId) {
        final List<CatHelper> catHelpers = catHelperService.findAllMemberCatHelpers(memberId);
        return ResponseEntity.ok(new FindAllMemberCatHelperResponse(catHelpers));
    }

    @Operation(summary = "전장에 데리고 갈 조력자 선택")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "선택한 조력자 번호 리스트", content = @Content(schema = @Schema(implementation = Void.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @Parameter(name = "memberId", description = "회원의 ID 값", example = "75", required = true)
    @PostMapping("/choose/{memberId}")
    public ResponseEntity<Void> chooseCatHelpers(@PathVariable final long memberId, @RequestBody final ChooseCatHelperRequest chooseCatHelperRequest) {
        catHelperService.chooseCatHelpers(memberId, chooseCatHelperRequest.getHelperIds());
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "전장에 데리고 온 조력자 번호 리스트 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "선택한 조력자 번호 리스트", content = @Content(schema = @Schema(implementation = FindChooseCatHelperAllResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @Parameter(name = "memberId", description = "회원의 ID 값", example = "75", required = true)
    @GetMapping("/choose/{memberId}")
    public ResponseEntity<FindChooseCatHelperAllResponse> findChooseCatHelpers(@PathVariable final long memberId) {
        final List<Long> catHelperIds = catHelperService.findChooseCatHelpers(memberId);
        return ResponseEntity.ok(new FindChooseCatHelperAllResponse(catHelperIds));
    }

    @Operation(summary = "특정 조력자에 대한 상세 정보(경험치, 레벨, ... 등) 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "선택한 조력자 번호 리스트", content = @Content(schema = @Schema(implementation = FindCatHelperResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @Parameter(name = "memberId", description = "회원의 ID 값", example = "75", required = true)
    @Parameter(name = "helperId", description = "조력자 번호", example = "5", required = true)
    @GetMapping("/detail/{memberId}/{helperId}")
    public ResponseEntity<FindCatHelperResponse> findCatHelperDetail(@PathVariable("memberId") final long memberId, @PathVariable("helperId") final long helperId) {
        final CatHelper catHelper = catHelperService.findHelperDetail(memberId, helperId);
        return ResponseEntity.ok(new FindCatHelperResponse(catHelper));
    }

    @PostMapping("/detail/{memberId}/{helperId}")
    @Operation(summary = "특정 조력자의 레벨업 요청 (현재 레벨에서 레벨 1 만큼 증가시킴)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "성공적으로 레벨업 수행됨"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @Parameter(name = "memberId", description = "회원의 ID 값", example = "75", required = true)
    @Parameter(name = "helperId", description = "조력자 번호", example = "5", required = true)
    public ResponseEntity<Void> updateHelperLevel(
            @PathVariable("memberId") final long memberId,
            @PathVariable("helperId") final long helperId,
            @RequestBody final UpdateLevelRequest request
    ) {
        catHelperService.updateHelperLevel(memberId, helperId, request.getItems(), request.getItemCount());
        return ResponseEntity.noContent().build();
    }

}
