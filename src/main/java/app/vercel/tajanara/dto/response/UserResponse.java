package app.vercel.tajanara.dto.response;

import app.vercel.tajanara.domain.User;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@ToString
@EqualsAndHashCode(callSuper = false)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse extends BaseModel {
    private String username;

    private String name;

    private Set<RoleResponse> roles = new LinkedHashSet<>();

    public UserResponse(User user) {
        setId(user.getId());
        setCreatedBy(user.getCreatedBy());
        setCreatedDate(user.getCreatedDate());
        setLastModifiedBy(user.getLastModifiedBy());
        setLastModifiedDate(user.getLastModifiedDate());
        this.username = user.getUsername();
        this.name = user.getName();
    }
}
