package app.vercel.tajanara.service;

import app.vercel.tajanara.dto.request.CreateUserRequest;
import app.vercel.tajanara.dto.response.UserResponse;

public interface UserService {
    void initialSystemUsers();

    UserResponse createUser(CreateUserRequest request);
}
