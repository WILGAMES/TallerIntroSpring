package co.icesi.taller_intro.config;

import co.icesi.taller_intro.repositories.ArtistRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "co.icesi")
public class AppConfiguration {
    @Bean
    public ArtistRepository createArtistRepository() {
        return new ArtistRepository();
    }
}
