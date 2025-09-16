package app.vercel.tajanara.dto.response;

import app.vercel.tajanara.domain.Score;
import lombok.*;

@ToString
@EqualsAndHashCode(callSuper = false)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public final class ScoreResponse extends BaseModel {

    private String userId;

    private String songId;

    private Integer value;

    @Setter
    private UserResponse user;

    @Setter
    private SongResponse song;

    public ScoreResponse(Score score) {
        setId(score.getId());
        setCreatedBy(score.getCreatedBy());
        setCreatedDate(score.getCreatedDate());
        setLastModifiedBy(score.getLastModifiedBy());
        setLastModifiedDate(score.getLastModifiedDate());
        this.userId = score.getUser().getId();
        this.songId = score.getSong().getId();
        this.value = score.getValue();
    }

}
