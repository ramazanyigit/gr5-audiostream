package tr.ege.edu.microservices.gr5.audiostream.playlist.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AuthenticationUtil {
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static UUID getId() {
        return UUID.fromString(getAuthentication().getName());
    }
}
