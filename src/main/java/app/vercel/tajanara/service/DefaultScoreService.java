package app.vercel.tajanara.service;

import app.vercel.tajanara.domain.Score;
import app.vercel.tajanara.domain.Song;
import app.vercel.tajanara.domain.User;
import app.vercel.tajanara.dto.request.CreateScoreRequest;
import app.vercel.tajanara.dto.response.ScoreResponse;
import app.vercel.tajanara.dto.response.SongResponse;
import app.vercel.tajanara.dto.response.UserResponse;
import app.vercel.tajanara.repository.ScoreRepository;
import app.vercel.tajanara.repository.SongRepository;
import app.vercel.tajanara.repository.UserRepository;
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
public class DefaultScoreService implements ScoreService {

    private final ScoreRepository scoreRepository;

    private final UserRepository userRepository;

    private final SongRepository songRepository;

    @Override
    public ScoreResponse createScore(CreateScoreRequest request) {
        User user = Optional.ofNullable(userRepository.getReferenceById(request.getUserId()))
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 계정: " + request.getUserId()));
        Song song = Optional.ofNullable(songRepository.getReferenceById(request.getSongId()))
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 노래: " + request.getSongId()));
        Score score = Score.builder()
                .user(user)
                .song(song)
                .value(request.getValue())
                .build();
        scoreRepository.save(score);
        return new ScoreResponse(score);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<ScoreResponse> getScores(Pageable pageable) {
        Page<Score> page = scoreRepository.findAll(pageable);
        if (page.isEmpty()) {
            return Page.empty(pageable);
        }
        var userIds = page.getContent().stream().map(s -> s.getUser().getId()).distinct().toList();
        var songIds = page.getContent().stream().map(s -> s.getSong().getId()).distinct().toList();
        var users = userRepository.findAllByIdIn(userIds).stream().map(UserResponse::new).toList();
        var songs = songRepository.findAllByIdIn(songIds).stream().map(SongResponse::new).toList();
        return page.map(score -> {
            ScoreResponse scoreResponse = new ScoreResponse(score);
            scoreResponse.setUser(users.stream().filter(u -> u.getId().equals(score.getUser().getId())).findFirst().orElse(null));
            scoreResponse.setSong(songs.stream().filter(s -> s.getId().equals(score.getSong().getId())).findFirst().orElse(null));
            return scoreResponse;
        });
    }

    @Transactional(readOnly = true)
    @Override
    public ScoreResponse getScoreById(String id) {
        Score score = scoreRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 점수: " + id));
        return new ScoreResponse(score);
    }

    @Override
    public void deleteScoreById(String id) {
        scoreRepository.deleteById(id);
    }

}
