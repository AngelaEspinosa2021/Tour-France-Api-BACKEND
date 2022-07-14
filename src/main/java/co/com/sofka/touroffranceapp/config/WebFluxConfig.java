package co.com.sofka.touroffranceapp.config;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.config.WebFluxConfigurerComposite;

@Configuration
public class WebFluxConfig {

    @Bean
    public WebFluxConfigurer corsConfigure(){
        return new WebFluxConfigurerComposite(){

            @Override
            public void addCorsMappings(CorsRegistry registry){
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("*");
            }
        };
    }

    @Bean
    public WebProperties.Resources resources() {
        return new WebProperties.Resources();
    }

}
