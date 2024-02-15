package com.digital.moviesapi;

import com.digital.moviesapi.repository.MovieRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public MovieRepository getMovieRepository() {
        return new MovieRepository();
    }
}
