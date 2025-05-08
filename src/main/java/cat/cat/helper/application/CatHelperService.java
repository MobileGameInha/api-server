package cat.cat.helper.application;

import cat.cat.helper.domain.CatHelper;
import cat.cat.helper.domain.CatHelperRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CatHelperService {
    private final CatHelperRepository catHelperRepository;

    @Transactional
    public void buyCatHelper(final long memberId, final long helperId) {
        catHelperRepository.save(new CatHelper(memberId, helperId));
    }

    @Transactional(readOnly = true)
    public List<CatHelper> findAllMemberCatHelpers(final long memberId) {
        return catHelperRepository.findByMemberId(memberId);
    }
}
