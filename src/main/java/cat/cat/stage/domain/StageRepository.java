package cat.cat.stage.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StageRepository extends JpaRepository<Stage, Long> {
    Optional<Stage> findByMemberIdAndStageNumber(long memberId, long stageNumber);

    @Query("""
    SELECT s FROM Stage s
    WHERE s.stageNumber = :stageNumber
    ORDER BY s.score DESC
    """)
    List<Stage> findAllByStageNumberOrderByScoreDesc(@Param("stageNumber") Long stageNumber);
}
