package app.vercel.tajanara.repository;

import app.vercel.tajanara.domain.Score;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface ScoreRepository extends Repository<Score, String> {
    void save(Score score);

    Page<Score> findAll(Pageable pageable);

    Optional<Score> findById(String id);

    Score getReferenceById(String id);

    void deleteById(String id);
}
