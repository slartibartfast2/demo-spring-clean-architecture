package ea.slartibartfast.demospringcleanarch.infrastructure.usecase.security;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService userDetailsService();
}
