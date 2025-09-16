package app.vercel.tajanara.repository;

import app.vercel.tajanara.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.Repository;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository extends Repository<User, String> {

    void save(User user);

    Optional<User> findById(String id);

    User getReferenceById(String id);

    Collection<User> findAllByIdIn(Collection<String> ids);

    @EntityGraph(attributePaths = {"roles"})
    Optional<User> findByUsername(String username);

}
