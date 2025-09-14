package app.vercel.tajanara.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@ToString
@EqualsAndHashCode
@Getter
@AllArgsConstructor
public final class CreateScoreRequest implements Serializable {
    @NotBlank
    private final String userId;

    @NotBlank
    private final String songId;

    @NotNull
    private final Integer value;
}