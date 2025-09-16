package app.vercel.tajanara.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * 토큰 갱신 요청 명령
 */
@ToString
@EqualsAndHashCode
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TokenRefreshRequest {

    @NotNull
    @NotBlank
    private String refreshToken;

}