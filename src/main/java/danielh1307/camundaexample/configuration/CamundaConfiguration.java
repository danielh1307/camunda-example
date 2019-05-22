package danielh1307.camundaexample.configuration;

import org.camunda.bpm.engine.spring.ProcessEngineFactoryBean;
import org.camunda.bpm.engine.spring.SpringProcessEngineConfiguration;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Arrays;

@EnableProcessApplication
@Configuration
public class CamundaConfiguration {

    @Bean
    public SpringProcessEngineConfiguration processEngineConfiguration(DataSource dataSource, PlatformTransactionManager platformTransactionManager) {
        SpringProcessEngineConfiguration config = new SpringProcessEngineConfiguration();

        config.setDataSource(dataSource);
        config.setTransactionManager(platformTransactionManager);
        config.setDbMetricsReporterActivate(false);

        config.setDatabaseSchemaUpdate("true");
        config.setHistory("audit");
        config.setJobExecutorActivate(true);

        return config;
    }

    @Bean
    public ProcessEngineFactoryBean processEngine(SpringProcessEngineConfiguration springProcessEngineConfiguration) {
        ProcessEngineFactoryBean factoryBean = new ProcessEngineFactoryBean();
        factoryBean.setProcessEngineConfiguration(springProcessEngineConfiguration);
        return factoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
