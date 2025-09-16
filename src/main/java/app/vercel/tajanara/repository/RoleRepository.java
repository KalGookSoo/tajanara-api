package app.vercel.tajanara.repository;

import app.vercel.tajanara.domain.Role;
import org.springframework.data.repository.Repository;

import java.util.Collection;
import java.util.Optional;

public interface RoleRepository extends Repository<Role, String> {

    Optional<Role> findByName(String name);

    Collection<Role> findAllByNameIn(Collection<String> names);

    Role save(Role role);

}
