package app.vercel.tajanara.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.lang.NonNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@EqualsAndHashCode(callSuper = false)
@Comment("점수")
@Entity
@Table(name = "tb_score")
public class Score extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @Comment("계정 식별자")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "song_id", nullable = false)
    @Comment("노래 식별자")
    private Song song;

    @Comment("값")
    @Column(nullable = false)
    private Integer value;

    public void update(@NonNull User user, @NonNull Song song, @NonNull Integer value) {
        this.user = user;
        this.song = song;
        this.value = value;
    }

}
