package ea.slartibartfast.demospringcleanarch.domain.model.exception;

public class BusinessException extends DomainException {
    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String messageFormat, Object... args) {
        super(String.format(messageFormat, args));
    }
}
