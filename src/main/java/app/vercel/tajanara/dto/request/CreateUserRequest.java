package app.vercel.tajanara.dto.request;

import app.vercel.tajanara.domain.vo.RoleName;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@ToString
@EqualsAndHashCode
@Getter
@AllArgsConstructor
public final class CreateUserRequest implements Serializable {
    private String username;

    private String password;

    private String name;

    private RoleName role;
}
