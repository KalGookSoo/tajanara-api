package app.vercel.tajanara.song;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;

/**
 * 노래
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@EqualsAndHashCode

@Entity
@Table(name = "tb_song")
public class Song {

    /**
     * 식별자
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 제목
     */
    @Column(nullable = false)
    private String title;

    /**
     * 아티스트
     */
    @Column(nullable = false)
    private String artist;

    /**
     * 가사
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String lyrics;

    public void update(@NonNull String title, @NonNull String artist, @NonNull String lyrics) {
        this.title = title;
        this.artist = artist;
        this.lyrics = lyrics;
    }

}
