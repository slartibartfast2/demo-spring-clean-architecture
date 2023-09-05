package ea.slartibartfast.demospringcleanarch.infrastructure.rest.model;

import lombok.Value;

@Value
public class AuthenticationResponse {
    boolean success = true;
    String token;
}
