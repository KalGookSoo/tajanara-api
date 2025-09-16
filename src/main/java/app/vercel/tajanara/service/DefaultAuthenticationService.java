package app.vercel.tajanara.service;

import app.vercel.tajanara.dto.request.SignInRequest;
import app.vercel.tajanara.dto.response.JwtResponse;
import app.vercel.tajanara.repository.UserRepository;
import app.vercel.tajanara.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultAuthenticationService implements AuthenticationService {

    private final UserRepository userRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    @Override
    public JwtResponse authenticate(SignInRequest request) {
        // todo access_token으로 OAuth2.0 검증 후 Return new JWT
        return null;
    }

    @Override
    public JwtResponse refreshToken(String refreshToken) {
        return jwtTokenProvider.refreshToken(refreshToken);
    }

}
