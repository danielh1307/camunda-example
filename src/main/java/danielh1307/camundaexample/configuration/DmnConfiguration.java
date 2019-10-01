package danielh1307.camundaexample.configuration;

import org.camunda.bpm.dmn.engine.DmnDecision;
import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.dmn.engine.DmnEngineConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class DmnConfiguration {

    private ResourceLoader resourceLoader;

    public DmnConfiguration(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Bean
    public DmnEngine createDmnEngine() {
        DmnEngineConfiguration configuration = DmnEngineConfiguration
                .createDefaultDmnEngineConfiguration();
        return configuration.buildEngine();
    }

    @Bean(name = "dish")
    public DmnDecision createDishDecision(DmnEngine dmnEngine) {
        Resource resource = resourceLoader.getResource("classpath:dish-decision.dmn11.xml");
        InputStream is = null;
        try {
            is = resource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dmnEngine.parseDecision("decision", is);
    }

}
