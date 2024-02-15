import React from "react";
import { MovieType } from "../utils/movies.types";
import "./Movie.css";
import Modal from "../Modal/Modal";
import { FC, useState } from "react";


type MovieProps = {
  data: MovieType;
  onCategoryChange: (category: string) => void;
  onRatingChange: (rating: number) => void;
  onDelete: (movieId: string) => void;
}
const categories= ['Drama','Action', 'Family','Comedy']


const Movie = ({ data ,onCategoryChange, onRatingChange, onDelete }: MovieProps): JSX.Element => {
  const [showModal, setShowModal] = useState<boolean>(false);

  function toggleModal() {
      setShowModal(!showModal);
  }
  const handleCategoryChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
    const newCategory = event.target.value;
    onCategoryChange(newCategory); // Invoke the callback function with the new category
  };

  const handleRatingChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const newRating = parseInt(event.target.value); // Convert value to number
    onRatingChange(newRating);
  };
 

  return (
    <>
      <Modal open={showModal} id={data.id} onClose={toggleModal} onDelete={onDelete}>
                <div>
                    Click yes to confirm
                </div>
      </Modal>
    <li className="movie">
      
      <img
        className="cover"
        src={`/covers/${data.imageUrl}`}
        alt={`${data.title} Poster by Chung Kong`}
      />
      
      <h3 className="movie-title">{data.title}</h3>
      <p>Rating: {data.rating} / 5</p>
      
      {/* <p>Category: {data.category}</p> */}
     
      <select id="categorySelect" value={data.category} onChange={handleCategoryChange}>
        {categories.map((category, index) => (
          <option key={index} value={category} >
            {category}
          </option>
        ))}
      </select>
      
      {/* Radio buttons for rating */}
      <div>
        <span>Rating: </span>
        {[1, 2, 3, 4, 5].map((rating) => (
          <label key={rating}>
            <input
              type="radio"
              value={rating}
              checked={data.rating === rating}
              onChange={handleRatingChange}
            />
            {rating}
          </label>
        ))}
      </div>
      <button type="button" className="btn" onClick={toggleModal}>Delete</button>
    </li>
    </>
  );
};

export default Movie;
