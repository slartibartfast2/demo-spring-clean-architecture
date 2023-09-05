package ea.slartibartfast.demospringcleanarch.infrastructure.usecase.security;

import ea.slartibartfast.demospringcleanarch.data.db.jpa.entity.CustomerData;
import ea.slartibartfast.demospringcleanarch.domain.usecase.customer.CustomerRepository;
import ea.slartibartfast.demospringcleanarch.infrastructure.usecase.model.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final CustomerRepository customerRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return email -> {
            CustomerData customerData = customerRepository
                    .findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", email)));

            return UserPrincipal.from(customerData);
        };
    }
}
