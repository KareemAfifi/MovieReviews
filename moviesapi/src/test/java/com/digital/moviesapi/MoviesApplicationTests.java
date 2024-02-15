package com.digital.moviesapi;

import com.digital.moviesapi.controller.MovieController;
import com.digital.moviesapi.model.Movie;
import com.digital.moviesapi.repository.MovieRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MoviesApplicationTests {

	@Autowired
	MovieRepository movieRepository;
	@Autowired
	MovieController movieController;

	@Test
	public void contextLoads() {
		assertThat(movieController).isNotNull();
	}

	@Test
	public void getAllMoviesTest() {
		List<Movie> movies = movieController.getAllMovies();
		assertThat(movies.size()).isEqualTo(10);
	}

	@Test
	public void addAMovie_thenDeleteThatMovie() {
		// Given
		Movie movie = movieRepository.save(new Movie(11, "some test movie", 1, "Family", "unknownImageUrl"));
		assertThat(movieController.getAllMovies().size()).isEqualTo(11);
		assertThat(movieRepository.findById(movie.getId()).isPresent()).isTrue();

		// When
		movieRepository.deleteById(movie.getId());

		// Then
		assertThat(movieController.getAllMovies().size()).isEqualTo(10);
	}

}
