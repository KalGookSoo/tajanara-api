package app.vercel.tajanara.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@ToString
@EqualsAndHashCode
@Getter
@AllArgsConstructor
public final class SongRequest implements Serializable {
    @NotBlank
    private final String title;

    @NotBlank
    private final String artist;

    @NotBlank
    private final String lyrics;
}
