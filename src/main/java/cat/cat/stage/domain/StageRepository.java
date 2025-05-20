package cat.cat.stage.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StageRepository extends JpaRepository<Stage, Long> {
    Optional<Stage> findByMemberIdAndStageNumber(long memberId, long stageNumber);
}
