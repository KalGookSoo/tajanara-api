package app.vercel.tajanara.song;

import app.vercel.tajanara.song.service.SongService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/songs")
public class SongController {

    private final SongService songService;

    @PostMapping
    public ResponseEntity<Map<String, Song>> createSong(@RequestBody @Valid SongRequest request) {
        Song song = songService.createSong(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("song", song));
    }

    @GetMapping
    public ResponseEntity<PagedModel<Song>> getSongs(@PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Song> page = songService.getSongs(pageable);
        PagedModel<Song> pagedModel = new PagedModel<>(page);
        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Song>> getSong(@PathVariable Long id) {
        Song song = songService.getSongById(id);
        return ResponseEntity.ok(Map.of("song", song));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Song>> updateSong(@PathVariable Long id, @RequestBody @Valid SongRequest request) {
        Song song = songService.updateSongById(id, request);
        return ResponseEntity.ok(Map.of("song", song));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSong(@PathVariable Long id) {
        songService.deleteSongById(id);
        return ResponseEntity.noContent().build();
    }

}
