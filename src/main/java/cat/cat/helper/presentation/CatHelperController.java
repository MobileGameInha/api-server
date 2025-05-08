package cat.cat.helper.presentation;

import cat.cat.helper.application.CatHelperService;
import cat.cat.helper.domain.CatHelper;
import cat.cat.helper.dto.FindAllMemberCatHelperResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "cat helper api", description = "조력자 관련 API")
@RequestMapping("/helper")
@RequiredArgsConstructor
@RestController
public class CatHelperController {
    private final CatHelperService catHelperService;

    @PostMapping("/buy/{memberId}/{helperId}")
    public ResponseEntity<Void> buyCatHelper(@PathVariable("memberId") final long memberId, @PathVariable("helperId") final long helperId) {
        catHelperService.buyCatHelper(memberId, helperId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{memberId}")
    public ResponseEntity<FindAllMemberCatHelperResponse> findAllMemberCatHelpers(@PathVariable final long memberId) {
        final List<CatHelper> catHelpers = catHelperService.findAllMemberCatHelpers(memberId);
        return ResponseEntity.ok(new FindAllMemberCatHelperResponse(catHelpers));
    }
}
