package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;

import java.util.List;

/**
 * Service interface for operations related to Rating.
 */
public interface RatingService {
    /**
     * Persists a given Rating entity.
     *
     * @param rating the rating to save
     */
    void saveRating(Rating rating);

    /**
     * Updates an existing Rating entity identified by its ID.
     *
     * @param updatedRating the updated rating
     * @param ratingToUpdateId the ID of the rating to update
     */
    void updateRating(Rating updatedRating, int ratingToUpdateId);

    /**
     * Retrieves all Rating entities.
     *
     * @return a list of all ratings
     */
    List<Rating> getAllRatings();

    /**
     * Retrieves a Rating entity by its ID.
     *
     * @param id the ID of the rating to retrieve
     * @return the rating corresponding to the given ID
     */
    Rating getRatingById(int id);

    /**
     * Deletes a Rating entity identified by its ID.
     *
     * @param id the ID of the rating to delete
     */
    void deleteRatingById(int id);
}
