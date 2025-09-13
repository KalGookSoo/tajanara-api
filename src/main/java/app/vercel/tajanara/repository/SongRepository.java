package app.vercel.tajanara.repository;

import app.vercel.tajanara.domain.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface SongRepository extends Repository<Song, String> {
    void save(Song song);

    Page<Song> findAll(Pageable pageable);

    Optional<Song> findById(String id);

    Song getReferenceById(String id);

    void deleteById(String id);
}
