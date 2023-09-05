package ea.slartibartfast.demospringcleanarch.infrastructure.usecase.security;

import ea.slartibartfast.demospringcleanarch.data.db.jpa.entity.CustomerData;
import ea.slartibartfast.demospringcleanarch.domain.usecase.customer.CustomerRepository;
import ea.slartibartfast.demospringcleanarch.infrastructure.usecase.model.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        CustomerData customerData = customerRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", email)));

        return UserPrincipal.from(customerData);
    }
}
