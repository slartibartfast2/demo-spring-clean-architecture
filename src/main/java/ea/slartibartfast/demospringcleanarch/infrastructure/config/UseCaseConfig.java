package ea.slartibartfast.demospringcleanarch.infrastructure.config;

import ea.slartibartfast.demospringcleanarch.domain.usecase.customer.CreateCustomerUseCase;
import ea.slartibartfast.demospringcleanarch.domain.usecase.customer.CustomerRepository;
import ea.slartibartfast.demospringcleanarch.domain.usecase.order.*;
import ea.slartibartfast.demospringcleanarch.domain.usecase.product.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public DeliveryOrderUseCase deliveryOrderUseCase(OrderRepository repository) {
        return new DeliveryOrderUseCase(repository);
    }

    @Bean
    public PayOrderUseCase payOrderUseCase(OrderRepository repository) {
        return new PayOrderUseCase(repository);
    }

    @Bean
    public DeleteOrderUseCase deleteOrderUseCase(OrderRepository repository) {
        return new DeleteOrderUseCase(repository);
    }

    @Bean
    public GetCustomerOrderUseCase getCustomerOrderUseCase(GetOrderUseCase getOrderUseCase) {
        return new GetCustomerOrderUseCase(getOrderUseCase);
    }

    @Bean
    public GetOrderUseCase getOrderUseCase(OrderRepository repository) {
        return new GetOrderUseCase(repository);
    }

    @Bean
    public CreateOrderUseCase createOrderUseCase(GetProductsByStoreAndProductsIdUseCase getProductsByStoreAndProductsIdUseCase,
                                                 OrderRepository orderRepository,
                                                 ProductRepository productRepository) {
        return new CreateOrderUseCase(getProductsByStoreAndProductsIdUseCase, orderRepository, productRepository);
    }

    @Bean
    public GetProductsByStoreAndProductsIdUseCase getProductsByStoreAndProductsIdUseCase(ProductRepository repository) {
        return new GetProductsByStoreAndProductsIdUseCase(repository);
    }

    @Bean
    public CreateCustomerUseCase createCustomerUseCase(CustomerRepository repository) {
        return new CreateCustomerUseCase(repository);
    }

    @Bean
    public GetProductUseCase getProductUseCase(ProductRepository repository) {
        return new GetProductUseCase(repository);
    }

    @Bean
    public GetAllProductsUseCase getAllProductsUseCase(ProductRepository repository) {
        return new GetAllProductsUseCase(repository);
    }
}