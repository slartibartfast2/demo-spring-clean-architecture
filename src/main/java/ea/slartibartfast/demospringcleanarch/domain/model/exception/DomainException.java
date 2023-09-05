package ea.slartibartfast.demospringcleanarch.domain.model;

public abstract class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }
}
