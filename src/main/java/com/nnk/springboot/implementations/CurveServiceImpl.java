package com.nnk.springboot.implementations;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.CurveService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Implementation for {@link CurveService}.
 * Provides CRUD operations for {@link CurvePoint} objects.
 *
 */
@Log4j2
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
@Service
public class CurveServiceImpl implements CurveService {

    /** The repository responsible for managing curve point data. */
    private final CurvePointRepository curvePointRepository;

    /**
     * Fetches all available {@link CurvePoint}.
     *
     * @return a list of all curve points.
     */
    public List<CurvePoint> getAllCurvePoints() {
        log.info("getAllCurvePoints method called");
        return curvePointRepository.findAll();
    }

    /**
     * Retrieves a  {@link CurvePoint} by its ID.
     *
     * @param id the ID of the curve point to be retrieved.
     * @return the curve point if found.
     * @throws NoSuchElementException if the curve point is not found.
     */
    public CurvePoint getCurvePointById(int id) {
        log.info("etCurvePointById method called with : {}", id);
        Optional<CurvePoint> curvePointOptional = curvePointRepository.findById(id);
        return curvePointOptional.orElseThrow(NoSuchElementException::new);
    }

    /**
     * Retrieves a  {@link CurvePoint} by its curve ID.
     *
     * @param curveId the ID of the curve to retrieve its point.
     * @return the curve point if found.
     * @throws NoSuchElementException if the curve point is not found.
     */
    public CurvePoint getCurvePointByCurveId(int curveId) {
        log.info("getCurvePointByCurveId method called with : {}", curveId);
        Optional<CurvePoint> curvePointOptional = curvePointRepository.findByCurveId(curveId);
        return curvePointOptional.orElseThrow(NoSuchElementException::new);
    }

    /**
     * Saves a given  {@link CurvePoint}.
     *
     * @param updatedCurvePoint the curve point to be saved.
     */
    public void saveCurvePoint(CurvePoint updatedCurvePoint) {
        log.info("saveCurvePoint method called with : {}", updatedCurvePoint);
        curvePointRepository.save(updatedCurvePoint);
    }

    /**
     * Updates an existing  {@link CurvePoint} identified by the provided ID.
     *
     * @param updatedCurvePoint        the updated curve point data.
     * @param curvePointToUpdateId     the ID of the curve point to be updated.
     */
    public void updateCurvePoint(CurvePoint updatedCurvePoint, int curvePointToUpdateId){
        log.info("updateCurvePoint method called with : {}, {}", updatedCurvePoint, curvePointToUpdateId);
        CurvePoint curvePointToUpdate = getCurvePointById(curvePointToUpdateId);
        curvePointToUpdate.setCurveId(updatedCurvePoint.getCurveId());
        curvePointToUpdate.setTerm(updatedCurvePoint.getTerm());
        curvePointToUpdate.setValue(updatedCurvePoint.getValue());
        saveCurvePoint(curvePointToUpdate);
    }

    /**
     * Deletes a  {@link CurvePoint} by its ID.
     *
     * @param id the ID of the curve point to be deleted.
     */
    public void deleteCurvePointById(int id) {
        log.info("deleteCurvePointById method called with : {}", id);
        curvePointRepository.deleteById(id);
    }
}
