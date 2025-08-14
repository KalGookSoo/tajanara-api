package app.vercel.tajanara.song;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface SongRepository extends Repository<Song, Long> {

    Song save(Song song);

    Page<Song> findAll(Pageable pageable);

    Optional<Song> findById(Long id);

    void deleteById(Long id);

}
