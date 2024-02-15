package com.digital.moviesapi.controller;

import com.digital.moviesapi.model.Movie;
import com.digital.moviesapi.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.*;

/**
 * A Controller in an API is your entry and exit point for clients. The request will hit the Controller method below,
 * which runs logic from the rest of your app as appropriate, and then sends a Response back to the user.
 * <p>
 * The @RestController annotations tells Spring that this class will represent a REST API.
 * Google more for the principles behind REST :)
 * e.g. https://restfulapi.net/
 */
@RestController
@RequestMapping("/api") // Tells Spring that all of the API methods will start with /api/, e.g. site.com/api/movies
public class MovieController {
    Logger logger = LoggerFactory.getLogger(MovieController.class);

    // Inject a "singleton" instance of our MovieRepository, to be used in the Controller methods.
    @Autowired
    MovieRepository movieRepository;

    // Get All Movies. "GET" is a specific type of HTTP method.
    // You should get used to seeing the common ones, like GET, POST, PUT and DELETE.
    //      https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods
    @GetMapping("/movies")
    public List<Movie> getAllMovies() {
        // Extra credit - Rather than manually adding a log to each request like this, you can have Spring automatically log every request that hits it.
        logger.info("GET /api/movies");
        return movieRepository.getAll();
    }

    @DeleteMapping("/movies/{id}")
    public void deleteMovie(@PathVariable("id") int id) {
        // Extra credit - Rather than manually adding a log to each request like this, you can have Spring automatically log every request that hits it.
        logger.info("Delete /api/movies");
        movieRepository.deleteById(Integer.valueOf(id));
    }
     @GetMapping("/movies/{id}/category")
         public String getMovieCategory(@PathVariable("id") int id) {
         Movie movie = movieRepository.findById(id).get();
         return movie.getCategory();
    }


    @PostMapping("/movie/category")
    public void changeCategory(@PathParam("category") String category, @PathParam("id") int id) {
             Movie movie = movieRepository.findById(id).get();
             movie.setCategory(category);
             movieRepository.save(movie);
    }
    @PostMapping("/movie/rating")
    public void AddRating(@PathParam("rating") String rating, @RequestParam("id") int id) {
        System.out.println("Entered Method");
        Movie movie = movieRepository.findById(id).get();
        movie.setRating(Integer.parseInt(rating));
        movieRepository.save(movie);
    }
//    @GetMapping("/movies/sortandcat")
//    public List<Movie> sortandcat(@RequestBody Map<String,String> map){
//        List<Movie> res = new ArrayList();
//        String category = map.get("category");
//        String sort = map.get("sort");
//        if(category.equals("Default"))
//            res.addAll(getAllMovies());
//        else {
//            for (Movie movie : getAllMovies()) {
//                if (movie.getCategory().equals(category))
//                    res.add(movie);
//            }
//        }
//        if(sort.equals("Default")){
//            return res;
//        }
//        if(sort.equals("Ascending")){
//            Collections.sort(res, Comparator.comparingInt(Movie::getRating));
//        }
//
//        if(sort.equals("Descending")){
//            Collections.sort(res, Comparator.comparingInt(Movie::getRating).reversed());
//        }
//
//        return res;
//
//    }

}








