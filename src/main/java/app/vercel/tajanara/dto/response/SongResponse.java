package app.vercel.tajanara.dto.response;

import app.vercel.tajanara.domain.Song;
import lombok.*;

@ToString
@EqualsAndHashCode(callSuper = false)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public final class SongResponse extends BaseModel {
    private String title;

    private String artist;

    private String lyrics;

    public SongResponse(Song song) {
        setId(song.getId());
        setCreatedBy(song.getCreatedBy());
        setCreatedDate(song.getCreatedDate());
        setLastModifiedBy(song.getLastModifiedBy());
        setLastModifiedDate(song.getLastModifiedDate());
        this.title = song.getTitle();
        this.artist = song.getArtist();
        this.lyrics = song.getLyrics();
    }
}
