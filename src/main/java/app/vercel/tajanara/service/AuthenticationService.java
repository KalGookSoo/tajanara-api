package app.vercel.tajanara.service;

import app.vercel.tajanara.dto.request.SignInRequest;
import app.vercel.tajanara.dto.response.JwtResponse;

public interface AuthenticationService {

    JwtResponse authenticate(SignInRequest request);

    JwtResponse refreshToken(String refreshToken);

}
