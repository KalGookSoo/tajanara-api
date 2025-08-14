package app.vercel.tajanara.song;

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
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/songs")
public class SongController {

    private final SongRepository songRepository;

    @PostMapping
    public ResponseEntity<Map<String, Song>> createSong(@RequestBody @Valid CreateSongRequest request) {
        Song song = Song.builder()
                .title(request.getTitle())
                .artist(request.getArtist())
                .lyrics(request.getLyrics())
                .build();

        Song newSong = songRepository.save(song);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("song", newSong));
    }

    @GetMapping
    public ResponseEntity<PagedModel<Song>> getSongs(@PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Song> page = songRepository.findAll(pageable);
        return ResponseEntity.ok(new PagedModel<>(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Song>> getSong(@PathVariable Long id) {
        Song song = songRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No song found for id: " + id));
        return ResponseEntity.ok(Map.of("song", song));
    }

}
