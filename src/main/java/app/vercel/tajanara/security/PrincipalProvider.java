package app.vercel.tajanara.security;

import org.springframework.security.core.Authentication;

public interface PrincipalProvider {

    Authentication getAuthentication();

}
