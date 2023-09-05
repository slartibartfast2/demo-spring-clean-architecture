package ea.slartibartfast.demospringcleanarch.infrastructure.usecase;

import ea.slartibartfast.demospringcleanarch.domain.usecase.UseCase;
import ea.slartibartfast.demospringcleanarch.domain.usecase.UseCaseExecutor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UseCaseExecutorImpl implements UseCaseExecutor {
    @Override
    public <RX, I extends UseCase.InputValues, O extends UseCase.OutputValues> RX execute(
            UseCase<I, O> useCase, I input, Function<O, RX> outputMapper) {
        var output = useCase.execute(input);
        return outputMapper.apply(output);
    }
}
