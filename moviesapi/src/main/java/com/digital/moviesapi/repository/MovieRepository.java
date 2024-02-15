package com.digital.moviesapi.repository;


import com.digital.moviesapi.model.Movie;
import com.digital.moviesapi.repository.database.InMemoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Our Movie repository which extends the generic InMemoryRepository. You can add any custom
 * functions for querying/sorting/filtering on the movie data to this class.
 * <p>
 * The getAll function is making use of Java 8's Stream API to manipulate data coming back from the
 * Repository, this API is useful for various data operations like filtering, reducing etc and
 * will prove useful for some of the other operations you will need during this exercise. Take a look at this
 * link for more information:
 * https://www.baeldung.com/java-8-streams-introduction
 * </p>
 */
public class MovieRepository extends InMemoryRepository<Movie, Integer> {

    public List<Movie> getAll() {
        return StreamSupport.stream(findAll().spliterator(), false).
                collect(Collectors.toList());
    }

    @Override
    public Optional<Movie> findById(Integer integer) {
        return super.findById(integer);
    }

    @Override
    public <S extends Movie> S save(S entity) {
        return super.save(entity);
    }
}