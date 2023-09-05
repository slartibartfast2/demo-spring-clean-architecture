package ea.slartibartfast.demospringcleanarch.domain.model.exception;

public class ValidationException extends DomainException {

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String messageFormat, Object... args) {
        super(String.format(messageFormat, args));
    }
}
