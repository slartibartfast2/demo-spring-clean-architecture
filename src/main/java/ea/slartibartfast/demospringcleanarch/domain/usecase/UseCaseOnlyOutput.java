package ea.slartibartfast.demospringcleanarch.domain.usecases;

public abstract class UseCaseOnlyOutput<O extends UseCase.OutputValues> {

    public abstract O execute();

    public interface OutputValues {
    }
}
