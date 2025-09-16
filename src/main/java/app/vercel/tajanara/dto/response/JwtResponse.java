package app.vercel.tajanara.dto.response;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@ToString
@EqualsAndHashCode
@Getter
@AllArgsConstructor
public final class JwtResponse implements Serializable {

    private final String accessToken;

    private final String refreshToken;

    private final long expiresIn;

}
