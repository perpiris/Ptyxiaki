package Ptyxiaki.Configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan("Ptyxiaki.Entities")
@EnableJpaRepositories("Ptyxiaki.Repositories")
@EnableTransactionManagement
public class DomainConfig {

}
