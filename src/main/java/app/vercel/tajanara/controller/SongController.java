package app.vercel.tajanara.controller;

import app.vercel.tajanara.dto.request.CreateSongRequest;
import app.vercel.tajanara.dto.response.SongResponse;
import app.vercel.tajanara.service.SongService;
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
    public ResponseEntity<Map<String, SongResponse>> createSong(@RequestBody @Valid CreateSongRequest request) {
        SongResponse song = songService.createSong(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("song", song));
    }

    @GetMapping
    public ResponseEntity<PagedModel<SongResponse>> getSongs(@PageableDefault(size = 20, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<SongResponse> page = songService.getSongs(pageable);
        PagedModel<SongResponse> pagedModel = new PagedModel<>(page);
        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, SongResponse>> getSong(@PathVariable String id) {
        SongResponse song = songService.getSongById(id);
        return ResponseEntity.ok(Map.of("song", song));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, SongResponse>> updateSong(@PathVariable String id, @RequestBody @Valid CreateSongRequest request) {
        SongResponse song = songService.updateSongById(id, request);
        return ResponseEntity.ok(Map.of("song", song));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSong(@PathVariable String id) {
        songService.deleteSongById(id);
        return ResponseEntity.noContent().build();
    }
}
