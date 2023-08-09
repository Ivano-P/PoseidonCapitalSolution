package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;

import java.util.List;

/**
 * Service interface for operations related to CurvePoint.
 */
public interface CurveService {

    /**
     * Retrieves all CurvePoint entities.
     *
     * @return a list of all curve points
     */
    List<CurvePoint> getAllCurvePoints();

    /**
     * Retrieves a CurvePoint entity by its ID.
     *
     * @param id the ID of the curve point to retrieve
     * @return the curve point corresponding to the given ID
     */
    CurvePoint getCurvePointById(int id);

    /**
     * Retrieves a CurvePoint entity by its curve ID.
     *
     * @param curveId the ID of the curve to retrieve its associated point
     * @return the curve point corresponding to the given curve ID
     */
    CurvePoint getCurvePointByCurveId(int curveId);

    /**
     * Persists a given CurvePoint entity.
     *
     * @param curvePointToSave the curve point to save
     */
    void saveCurvePoint(CurvePoint curvePointToSave);

    /**
     * Updates an existing CurvePoint entity identified by its ID.
     *
     * @param updatedCurvePoint the updated curve point
     * @param curvePointToUpdateId the ID of the curve point to update
     */
    void updateCurvePoint(CurvePoint updatedCurvePoint, int curvePointToUpdateId);

    /**
     * Deletes a CurvePoint entity identified by its ID.
     *
     * @param id the ID of the curve point to delete
     */
    void deleteCurvePointById(int id);
}
