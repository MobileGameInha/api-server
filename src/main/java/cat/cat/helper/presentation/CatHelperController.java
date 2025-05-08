package cat.cat.helper.presentation;

import cat.cat.helper.application.CatHelperService;
import cat.cat.helper.domain.CatHelper;
import cat.cat.helper.dto.BuyCatHelperRequest;
import cat.cat.helper.dto.ChooseCatHelperRequest;
import cat.cat.helper.dto.FindAllMemberCatHelperResponse;
import cat.cat.helper.dto.FindCatHelperResponse;
import cat.cat.helper.dto.FindChooseCatHelperAllResponse;
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

    @PostMapping("/buy/{memberId}/{helperId}")
    public ResponseEntity<Void> buyCatHelper(@PathVariable("memberId") final long memberId, @PathVariable("helperId") final long helperId, @RequestBody final BuyCatHelperRequest buyCatHelperRequest) {
        catHelperService.buyCatHelper(memberId, helperId, buyCatHelperRequest.getHelperPrice());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all/{memberId}")
    public ResponseEntity<FindAllMemberCatHelperResponse> findAllMemberCatHelpers(@PathVariable final long memberId) {
        final List<CatHelper> catHelpers = catHelperService.findAllMemberCatHelpers(memberId);
        return ResponseEntity.ok(new FindAllMemberCatHelperResponse(catHelpers));
    }

    @PostMapping("/choose/{memberId}")
    public ResponseEntity<Void> chooseCatHelpers(@PathVariable final long memberId, @RequestBody final ChooseCatHelperRequest chooseCatHelperRequest) {
        catHelperService.chooseCatHelpers(memberId, chooseCatHelperRequest.getHelperIds());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/choose/{memberId}")
    public ResponseEntity<FindChooseCatHelperAllResponse> findChooseCatHelpers(@PathVariable final long memberId) {
        final List<CatHelper> catHelpers = catHelperService.findChooseCatHelpers(memberId);
        return ResponseEntity.ok(new FindChooseCatHelperAllResponse(catHelpers));
    }

    @GetMapping("/detail/{memberId}/{helperId}")
    public ResponseEntity<FindCatHelperResponse> findCatHelperDetail(@PathVariable("memberId") final long memberId, @PathVariable("helperId") final long helperId) {
        final CatHelper catHelper = catHelperService.findHelperDetail(memberId, helperId);
        return ResponseEntity.ok(new FindCatHelperResponse(catHelper));
    }
}
