package app.vercel.tajanara.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.lang.NonNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@EqualsAndHashCode(callSuper = false)
@Comment("노래")
@Entity
@Table(name = "tb_song")
public class Song extends BaseEntity {
    @Comment("제목")
    @Column(nullable = false)
    private String title;

    @Comment("아티스트")
    @Column(nullable = false)
    private String artist;

    @Comment("가사")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String lyrics;

    public void update(@NonNull String title, @NonNull String artist, @NonNull String lyrics) {
        this.title = title;
        this.artist = artist;
        this.lyrics = lyrics;
    }
}
