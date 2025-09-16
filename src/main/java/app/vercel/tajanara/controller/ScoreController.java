package app.vercel.tajanara.controller;

import app.vercel.tajanara.dto.request.CreateScoreRequest;
import app.vercel.tajanara.dto.response.ScoreResponse;
import app.vercel.tajanara.service.ScoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/scores")
public class ScoreController {

    private final ScoreService scoreService;

    @PostMapping
    public ResponseEntity<Map<String, ScoreResponse>> createScore(@RequestBody @Valid CreateScoreRequest request) {
        ScoreResponse score = scoreService.createScore(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("score", score));
    }

    @GetMapping
    public ResponseEntity<PagedModel<ScoreResponse>> getScores(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        Page<ScoreResponse> page = scoreService.getScores(pageRequest);
        PagedModel<ScoreResponse> pagedModel = new PagedModel<>(page);
        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, ScoreResponse>> getScore(@PathVariable String id) {
        ScoreResponse score = scoreService.getScoreById(id);
        return ResponseEntity.ok(Map.of("score", score));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScore(@PathVariable String id) {
        scoreService.deleteScoreById(id);
        return ResponseEntity.noContent().build();
    }

}
