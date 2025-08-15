package app.vercel.tajanara.song.service;

import app.vercel.tajanara.song.SongRequest;
import app.vercel.tajanara.song.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SongService {

    Song createSong(SongRequest request);

    Page<Song> getSongs(Pageable pageable);

    Song getSongById(Long id);

    Song updateSongById(Long id, SongRequest request);

    void deleteSongById(Long id);

}
