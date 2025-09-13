package app.vercel.tajanara.repository;

import app.vercel.tajanara.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UserRepository extends Repository<User, String> {
    void save(User user);

    @EntityGraph(attributePaths = {"roles"})
    Optional<User> findByUsername(String username);
}
