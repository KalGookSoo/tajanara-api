package app.vercel.tajanara.service;

import app.vercel.tajanara.dto.request.CreateSongRequest;
import app.vercel.tajanara.dto.response.SongResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SongService {

    SongResponse createSong(CreateSongRequest request);

    Page<SongResponse> getSongs(Pageable pageable);

    SongResponse getSongById(String id);

    SongResponse updateSongById(String id, CreateSongRequest request);

    void deleteSongById(String id);

    void initialDefaultSongs();

}
