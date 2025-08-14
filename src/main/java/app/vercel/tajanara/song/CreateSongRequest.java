package app.vercel.tajanara.song;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class CreateSongRequest implements Serializable {

    @NotBlank
    private final String title;

    @NotBlank
    private final String artist;

    @NotBlank
    private final String lyrics;

}
