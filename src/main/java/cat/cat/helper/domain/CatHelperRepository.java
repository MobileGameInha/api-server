package cat.cat.helper.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatHelperRepository extends JpaRepository<CatHelper, Long> {
    List<CatHelper> findByMemberId(final long memberId);
    boolean existsByMemberIdAndHelperId(final long memberId, final long helperId);
    List<CatHelper> findAllByMemberIdAndActiveTrue(final long memberId);
    CatHelper findByMemberIdAndHelperId(final long memberId, final long helperId);
}
