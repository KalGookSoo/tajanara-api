package app.vercel.tajanara.service;

import app.vercel.tajanara.domain.Role;
import app.vercel.tajanara.domain.User;
import app.vercel.tajanara.domain.vo.RoleName;
import app.vercel.tajanara.dto.request.CreateUserRequest;
import app.vercel.tajanara.dto.response.RoleResponse;
import app.vercel.tajanara.dto.response.UserResponse;
import app.vercel.tajanara.repository.RoleRepository;
import app.vercel.tajanara.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class DefaultUserService implements UserService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Override
    public void initialSystemUsers() {
        log.info("역할 정보를 초기화합니다.");
        Role roleAdmin = new Role(RoleName.ROLE_ADMIN.name(), RoleName.ROLE_ADMIN.getDescription());
        Role roleManager = new Role(RoleName.ROLE_MANAGER.name(), RoleName.ROLE_MANAGER.getDescription());
        Role roleUser = new Role(RoleName.ROLE_USER.name(), RoleName.ROLE_USER.getDescription());

        log.info("계정 정보를 초기화합니다.");
        User userAdmin = User.create(RoleName.ROLE_ADMIN.name().toLowerCase(), "1234", RoleName.ROLE_ADMIN.getDescription());
        userAdmin.addRole(roleAdmin);
        userAdmin.addRole(roleManager);
        userAdmin.addRole(roleUser);
        userRepository.save(userAdmin);

        User userManager = User.create(RoleName.ROLE_MANAGER.name().toLowerCase(), "1234", RoleName.ROLE_MANAGER.getDescription());
        userManager.addRole(roleManager);
        userManager.addRole(roleUser);
        userRepository.save(userManager);

        User userUser = User.create(RoleName.ROLE_USER.name().toLowerCase(), "1234", RoleName.ROLE_USER.getDescription());
        userUser.addRole(roleUser);
        userRepository.save(userUser);
    }

    @Override
    public UserResponse createUser(CreateUserRequest request) {
        User user = User.create(request.getUsername(), request.getPassword(), request.getName());
        List<String> roleNames = request.getRoles().stream().map(RoleName::name).toList();
        Iterable<Role> roles = roleRepository.findAllByNameIn(roleNames);
        roles.forEach(user::addRole);
        userRepository.save(user);

        UserResponse userResponse = new UserResponse(user);
        for (Role role : roles) {
            userResponse.getRoles().add(new RoleResponse(role));
        }
        return userResponse;
    }
}
