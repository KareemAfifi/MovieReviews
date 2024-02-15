package com.digital.moviesapi.model;

import java.io.Serializable;

/**
 * Model class.
 * An instance of a Movie model represents a single row in the Movies table of your database.
 * The rest of your API uses instances of the model to use the parts of the data they need.
 */
public class Movie extends EntityBase implements Serializable {

    // e.g. The "title" column can't be left blank, and must be less than 100 characters long.
    private String title;

    private Integer rating;

    private String category;

    // We can provide a different database column name to the variable name here if we choose.
    private String imageUrl;

    public Movie(Integer id, String title, Integer rating, String category, String imageUrl) {
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    /**
     * Getter and Setter methods.
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}