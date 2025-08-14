package app.vercel.tajanara.song;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/songs")
public class SongController {

    private final SongRepository songRepository;

    @PostMapping
    public ResponseEntity<CreateSongResponse> createSong(@RequestBody @Valid CreateSongRequest request) {
        Song song = Song.builder()
                .title(request.getTitle())
                .artist(request.getArtist())
                .lyrics(request.getLyrics())
                .build();

        Song newSong = songRepository.save(song);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateSongResponse(newSong));
    }

}
