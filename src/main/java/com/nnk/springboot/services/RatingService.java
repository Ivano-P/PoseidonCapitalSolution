package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;

import java.util.List;

public interface RatingService {
    void saveRating(Rating rating);
    void updateRating(Rating updatedRating, int ratingToUpdateId);
    List<Rating> getAllRatings();
    Rating getRatingById(int id);
    void deleteRatingById(int id);
}
