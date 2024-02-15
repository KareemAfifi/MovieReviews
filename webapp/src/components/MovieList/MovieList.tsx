import React from 'react';
import Movie from "../Movie/Movie";
import { MovieType } from '../utils/movies.types';
import "./MovieList.css";

MoviesList:
type MovieListProps = {
  movies: MovieType[];
  setMovies:React.Dispatch<React.SetStateAction<MovieType[]>>
  onRatingChange: (movieId: string, newRating: number) => void;
  onDelete: (movieId: string) => void;
}



const MovieList = ({movies, onDelete ,setMovies,onRatingChange}: MovieListProps): JSX.Element => {

  const handleCategoryChange = (movieId, newCategory) => {
    const updatedMovies = movies.map(movie => {
      if (movie.id === movieId) {
        return { ...movie, category: newCategory };
      }
      return movie;
    });
    setMovies(updatedMovies);
    const baseUrl = 'http://localhost:8080/api/movie/category';
    const params = new URLSearchParams();
    params.append('id', movieId);
    params.append('category', newCategory);
    
    const url = `${baseUrl}?${params.toString()}`;
    console.log(url);
    fetch(url, {
      method: 'POST',
     
      headers: {
        'Content-Type': 'application/json',
      },
     // body: JSON.stringify({ movieId, newCategory }),
    })
      .then(response => {
        if (!response.ok) {
          throw new Error('Failed to save category');
        }
        return response.json();
      })
      
  }


  
  if (!movies || movies.length === 0) {
    return <p>No movies found</p>
  }

  return (
    <ul className='movie-list'>
      {movies.map((movie: MovieType): JSX.Element => (
        <Movie
          key={movie.id}
          data={movie}
          onCategoryChange={(newCategory) => handleCategoryChange(movie.id, newCategory)}
          onRatingChange={(newRating) => onRatingChange(movie.id, newRating)}
          onDelete={onDelete}
        />
      ))}
    </ul>
  )
}


export default MovieList;
