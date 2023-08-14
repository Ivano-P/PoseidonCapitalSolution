package com.nnk.springboot.implementations;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.RatingService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Provides an implementation for the {@link RatingService}.
 * Manages CRUD operations related to the {@link Rating} entity.
 *
 */
@Log4j2
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class RatingServiceImpl implements RatingService {

    /** The repository responsible for managing Rating data. */
    private final RatingRepository ratingRepository;


    /**
     * Saves the provided {@link Rating} entity to the database.
     *
     * @param rating the rating entity to save.
     */
    @Override
    public void saveRating(Rating rating) {
        log.info("saveRating method called with : {}", rating);
        ratingRepository.save(rating);
    }

    /**
     * Updates an existing {@link Rating} based on the provided updatedRating and its ID.
     *
     * @param updatedRating the updated rating information.
     * @param ratingToUpdateId the ID of the rating to be updated.
     */
    @Override
    public void updateRating(Rating updatedRating, int ratingToUpdateId) {
        log.info("updatedRating method called with : {}, {}", updatedRating, ratingToUpdateId);
        Rating ratingToUpdate = getRatingById(ratingToUpdateId);
        ratingToUpdate.setMoodysRating(updatedRating.getMoodysRating());
        ratingToUpdate.setSandPRating(updatedRating.getSandPRating());
        ratingToUpdate.setFitchRating(updatedRating.getFitchRating());
        ratingToUpdate.setOrderNumber(updatedRating.getOrderNumber());
        saveRating(ratingToUpdate);
    }

    /**
     * Retrieves all the {@link Rating} entities from the database.
     *
     * @return a list of all ratings.
     */
    @Override
    public List<Rating> getAllRatings() {
        log.info("getAllRatings method called");
        return ratingRepository.findAll();
    }

    /**
     * Retrieves a {@link Rating} by its ID.
     *
     * @param id the ID of the rating to retrieve.
     * @return the corresponding rating.
     * @throws NoSuchElementException if the rating with the provided ID is not found.
     */
    @Override
    public Rating getRatingById(int id) {
        log.info("getRatingById method called with : {}", id);
        Optional<Rating> ratingOptional = ratingRepository.findById(id);
        return ratingOptional.orElseThrow(NoSuchElementException::new);
    }

    /**
     * Deletes a {@link Rating} from the database based on its ID.
     *
     * @param id the ID of the rating to delete.
     */
    @Override
    public void deleteRatingById(int id) {
        log.info("deleteRatingById method called with : {}", id);
        ratingRepository.deleteById(id);
    }
}
