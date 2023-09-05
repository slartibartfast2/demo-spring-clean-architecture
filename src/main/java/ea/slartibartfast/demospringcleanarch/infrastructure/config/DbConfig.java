package ea.slartibartfast.demospringcleanarch.infrastructure.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {"ea.slartibartfast.demospringcleanarch.data.db.jpa.entity"})
@EnableJpaRepositories(basePackages = {"ea.slartibartfast.demospringcleanarch.data.db.jpa.repository"})
public class DbConfig {
}
