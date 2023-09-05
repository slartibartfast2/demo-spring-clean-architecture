package ea.slartibartfast.demospringcleanarch.domain.usecases;

public abstract class UseCaseOnlyInput<I extends UseCase.InputValues> {

    public abstract void execute(I input);

    public interface InputValues {
    }
}
