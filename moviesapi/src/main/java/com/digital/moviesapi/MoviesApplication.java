package com.digital.moviesapi;

import com.digital.moviesapi.model.Movie;
import com.digital.moviesapi.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MoviesApplication {

    private Logger logger = LoggerFactory.getLogger(MoviesApplication.class);

    // Start point for the Java application.
    public static void main(String[] args) {
        SpringApplication.run(MoviesApplication.class, args);
    }

    @Bean
    public CommandLineRunner populateMovieRepo(MovieRepository movieRepository) {
        return (args) -> {
            // Populate movie repository

            movieRepository.save(new Movie(1, "The Return of the King", 5, "Family", "return-of-the-king.jpg"));
            movieRepository.save(new Movie(2, "Anchorman", 3, "Comedy", "anchorman.jpg"));
            movieRepository.save(new Movie(3, "The Great Gatsby", 1, "Drama", "the-great-gatsby.jpg"));
            movieRepository.save(new Movie(4, "The Last Jedi", 1, "Action", "last-jedi.jpg"));
            movieRepository.save(new Movie(5, "Deadpool", 3, "Comedy", "deadpool.jpg"));
            movieRepository.save(new Movie(6, "V for Vendetta", 5, "Drama", "v-for-vendetta.jpg"));
            movieRepository.save(new Movie(7, "Inception", 2, "Action", "inception.jpg"));
            movieRepository.save(new Movie(8, "Wonder Woman", 4, "Action", "wonder-woman.jpg"));
            movieRepository.save(new Movie(9, "Harry Potter & the Philosopher\"s Stone", 4, "Family", "harry-potter.jpg"));
            movieRepository.save(new Movie(10, "Lion King", 5, "Family", "lion-king.jpg"));

            logger.info("Movies Repository Populated");
        };
    }
}
