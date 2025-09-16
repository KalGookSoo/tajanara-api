package app.vercel.tajanara.dto.response;

import app.vercel.tajanara.domain.Role;
import lombok.*;

@ToString
@EqualsAndHashCode(callSuper = false)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponse extends BaseModel {

    private String name;

    private String alias;

    public RoleResponse(Role role) {
        setId(role.getId());
        setCreatedBy(role.getCreatedBy());
        setCreatedDate(role.getCreatedDate());
        setLastModifiedBy(role.getLastModifiedBy());
        setLastModifiedDate(role.getLastModifiedDate());
        this.name = role.getName();
        this.alias = role.getAlias();
    }

}
