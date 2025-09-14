package app.vercel.tajanara.service;

import app.vercel.tajanara.dto.request.CreateScoreRequest;
import app.vercel.tajanara.dto.response.ScoreResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ScoreService {
    ScoreResponse createScore(CreateScoreRequest request);

    Page<ScoreResponse> getScores(Pageable pageable);

    ScoreResponse getScoreById(String id);

    void deleteScoreById(String id);
}
