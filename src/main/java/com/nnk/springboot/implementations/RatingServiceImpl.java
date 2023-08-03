package com.nnk.springboot.implementations;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.RatingService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Log4j2
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;


    @Override
    public void saveRating(Rating rating) {
        log.info("saveRating method called with : {}", rating);
        ratingRepository.save(rating);
    }

    @Override
    public void updateRating(Rating updatedRating, int ratingToUpdateId) {
        log.info("updatedRating method called with : {}, {}", updatedRating, ratingToUpdateId);
        Optional<Rating> ratingOptional = ratingRepository.findById(ratingToUpdateId);
        Rating ratingToUpdate = ratingOptional.orElseThrow(NoSuchElementException::new);
        ratingToUpdate.setMoodysRating(updatedRating.getMoodysRating());
        ratingToUpdate.setSandPRating(updatedRating.getSandPRating());
        ratingToUpdate.setFitchRating(updatedRating.getFitchRating());
        ratingToUpdate.setOrderNumber(updatedRating.getOrderNumber());
        saveRating(ratingToUpdate);
    }

    @Override
    public List<Rating> getAllRatings() {
        log.info("getAllRatings method called");
        return ratingRepository.findAll();
    }

    @Override
    public Rating getRatingById(int id) {
        log.info("getRatingById method called with : {}", id);
        Optional<Rating> ratingOptional = ratingRepository.findById(id);
        return ratingOptional.orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void deleteRatingById(int id) {
        log.info("deleteRatingById method called with : {}", id);
        ratingRepository.deleteById(id);
    }
}
