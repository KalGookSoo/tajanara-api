package app.vercel.tajanara.dto.request;

import app.vercel.tajanara.domain.vo.RoleName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Collection;

@ToString
@EqualsAndHashCode
@Getter
@AllArgsConstructor
public final class CreateUserRequest implements Serializable {

    @NotNull
    @NotBlank
    private final String username;

    private final String password;

    @NotNull
    @NotBlank
    private final String name;

    @NotNull
    private final Collection<RoleName> roles;

}
