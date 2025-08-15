package app.vercel.tajanara.song.service;

import app.vercel.tajanara.song.Song;
import app.vercel.tajanara.song.SongRepository;
import app.vercel.tajanara.song.SongRequest;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class DefaultSongService implements SongService {

    private final SongRepository songRepository;

    private final EntityManager entityManager;


    @Override
    public Song createSong(SongRequest request) {
        Song song = Song.builder()
                .title(request.getTitle())
                .artist(request.getArtist())
                .lyrics(request.getLyrics())
                .build();
        return songRepository.save(song);
    }

    @Override
    public Page<Song> getSongs(Pageable pageable) {
        return songRepository.findAll(pageable);
    }

    @Override
    public Song getSongById(Long id) {
        return songRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No song found for id: " + id));
    }

    @Override
    public Song updateSongById(Long id, SongRequest request) {
        Song song = Optional.ofNullable(entityManager.getReference(Song.class, id))
                .orElseThrow(() -> new NoSuchElementException("No song found for id: " + id));

        song.update(
                request.getTitle(),
                request.getArtist(),
                request.getLyrics()
        );

        return songRepository.save(song);
    }

    @Override
    public void deleteSongById(Long id) {
        songRepository.deleteById(id);
    }

}
