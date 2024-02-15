import React, { useEffect, useState } from "react";
import {
  getAllMovies,
} from "./api";
import Header from "./components/Header/Header";
import MovieList from "./components/MovieList/MovieList";
import "./App.css";
import { MovieType } from "./components/utils/movies.types";

/**
 * import { networkInterfaces } from "os";

/**
 * This component is your overall. React starts here, and draws each child component (Like <Header>)
 * one by one below. And if those children have children of their own, it follows down those too.
 *
 * You can picture it like a tree structure, this is the very top leaf, and React will go down each branch
 * until it has built a full webpage.
 */
const App = () => {
  const [movies, setMovies] = useState<MovieType[]>([]);
  const [categories, setCategories] = useState([])
  const [currentCategory, setcurrentCategory] = useState("Default")
  const [currentSort, setcurrentsort] = useState("Default")
  /**
   * Runs automatically when the component "mounts", i.e. is first drawn to the screen.
   *
   * Call the GET /movies API, and store the result.
   */
  useEffect(() => {
    getAllMovies().then(movies => {
      setMovies(movies);
      const uniqueCatergories=[...new Set(movies.map(item => item.category))];
      uniqueCatergories.unshift("Default")
      setCategories(uniqueCatergories)
    });
  },[]);
function Deletebyid(MovieId) {
  const idToRemove = MovieId
const filteredMovies = movies.filter((item) => item.id !== idToRemove);
setMovies(filteredMovies)
  }
  const handleCategoryChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
    const newCategory = event.target.value;
    setcurrentCategory(newCategory);
    if (newCategory=="Default"){
      getAllMovies().then(movies => {
        if (currentSort=="Default"){setMovies(movies);}
        else if(currentSort=="Ascending"){
          const sortedMovies = [...movies];
          sortedMovies.sort((a, b) => {
                return a.rating - b.rating;
            })
          
          setMovies(sortedMovies)
        }
        else if(currentSort=="Descending"){
          const sortedMovies = [...movies];
          sortedMovies.sort((a, b) => {
                return b.rating - a.rating;
            })
          
          setMovies(sortedMovies)
        }
      });
    }
    else{
      getAllMovies().then(movies => {
        const filteredMovies = movies.filter((item) => item.category == newCategory)
        
        if (currentSort=="Default"){setMovies(filteredMovies);}
        else if(currentSort=="Ascending"){
          const sortedMovies = [...filteredMovies];
          sortedMovies.sort((a, b) => {
                return a.rating - b.rating;
            })
          
          setMovies(sortedMovies)
        }
        else if(currentSort=="Descending"){
          const sortedMovies = [...filteredMovies];
          sortedMovies.sort((a, b) => {
                return b.rating - a.rating;
            })
          
          setMovies(sortedMovies)
        }
      });
    }
  };
  const handleSortChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
    const sort = event.target.value;
    setcurrentsort(sort)
    if (sort=="Default"){
      getAllMovies().then(movies => {
        if (currentCategory=="Default"){setMovies(movies);}
        else{
          const filteredMovies = movies.filter((item) => item.category == currentCategory)
          setMovies(filteredMovies)
        }
        
      });
    }
    else if(sort=="Ascending"){

      const sortedMovies = [...movies];
      sortedMovies.sort((a, b) => {
            return a.rating - b.rating;
        })
      
      setMovies(sortedMovies)

    }
    else if(sort=="Descending"){
      const sortedMovies = [...movies];
      sortedMovies.sort((a, b) => {
            return b.rating - a.rating;
        })
      // const sortedMovies= movies.sort((a, b) => {
      //     return a.rating - b.rating;
      // });
      
      setMovies(sortedMovies)
    }
  }
  const handleRatingChange = (movieId: string, newRating: number) => {
    const updatedMovies = movies.map((movie) => {
      if (movie.id === movieId) {
        return { ...movie, rating: newRating };
      }
      return movie;
    });
    setMovies(updatedMovies);
    const baseUrl = 'http://localhost:8080/api/movie/rating';
    const params = new URLSearchParams();
    params.append('id', movieId);
    params.append('rating', newRating+"");
    
    const url = `${baseUrl}?${params.toString()}`;
    console.log(url)
    fetch(url, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      //body: JSON.stringify({ movieId, newRating }),
    })
      .then(response => {
        if (!response.ok) {
          throw new Error('Failed to save category');
        }
        return response.json();
      })
  };
  return (
    <div className="App">
    
      <Header />
      <div>
      <span>Filter by Category: </span>
      <select id="categorySelect"  onChange={handleCategoryChange}>
        {categories.map((category, index) => (
          <option key={index} value={category} >
            {category}
          </option>
        ))}
      </select>
      <span>sort by: </span>
      <select id="sortSelect" onChange={handleSortChange}>
                  <option value="Default" >Default</option>
                  <option value="Ascending" >Ascending</option>
                  <option value="Descending" >Descending</option>
      </select>
      </div>
      <div className="container">

      <MovieList
          movies={movies}
          onDelete={Deletebyid}
          setMovies={setMovies}
          onRatingChange={handleRatingChange}
          ></MovieList>
      </div>
    </div>
  );
};

export default App;
