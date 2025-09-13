package app.vercel.tajanara.service;

import app.vercel.tajanara.domain.Song;
import app.vercel.tajanara.dto.request.SongRequest;
import app.vercel.tajanara.dto.response.SongResponse;
import app.vercel.tajanara.repository.SongRepository;
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

    @Override
    public SongResponse createSong(SongRequest request) {
        Song song = Song.builder()
                .title(request.getTitle())
                .artist(request.getArtist())
                .lyrics(request.getLyrics())
                .build();
        songRepository.save(song);
        return new SongResponse(song);
    }

    @Override
    public Page<SongResponse> getSongs(Pageable pageable) {
        Page<Song> page = songRepository.findAll(pageable);
        return page.map(SongResponse::new);
    }

    @Override
    public SongResponse getSongById(String id) {
        Song song = songRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 노래: " + id));
        return new SongResponse(song);
    }

    @Override
    public SongResponse updateSongById(String id, SongRequest request) {
        Song song = Optional.ofNullable(songRepository.getReferenceById(id))
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 노래: " + id));
        song.update(
                request.getTitle(),
                request.getArtist(),
                request.getLyrics()
        );
        songRepository.save(song);
        return new SongResponse(song);
    }

    @Override
    public void deleteSongById(String id) {
        songRepository.deleteById(id);
    }
}
