package ea.slartibartfast.demospringcleanarch.domain.model;

public record Identity(Long number) {
    public static Identity nothing() {
        return new Identity(Long.MIN_VALUE);
    }
}