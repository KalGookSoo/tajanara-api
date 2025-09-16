package app.vercel.tajanara.controller;

import app.vercel.tajanara.dto.request.SignInRequest;
import app.vercel.tajanara.dto.request.TokenRefreshRequest;
import app.vercel.tajanara.dto.response.JwtResponse;
import app.vercel.tajanara.service.AuthenticationService;
import app.vercel.tajanara.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignApiController {

    private final UserService userService;

    private final AuthenticationService authenticationService;

    @PostMapping("/sign-in")
    public ResponseEntity<JwtResponse> signIn(@Valid @RequestBody SignInRequest request) {
        JwtResponse response = authenticationService.authenticate(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/token/refresh")
    public ResponseEntity<JwtResponse> refreshToken(@Valid @RequestBody TokenRefreshRequest request) {
        JwtResponse response = authenticationService.refreshToken(request.getRefreshToken());
        return ResponseEntity.ok(response);
    }

}
