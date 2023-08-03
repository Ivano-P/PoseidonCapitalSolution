package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;

import java.util.List;

public interface CurveService {
    List<CurvePoint> getAllCurvePoints();
    CurvePoint getCurvePointById(int id);
    CurvePoint getCurvePointByCurveId(int curveId);

    void saveCurvePoint(CurvePoint curvePointToSave);
    void updateCurvePoint(CurvePoint updatedCurvePoint, int curvePointToUpdateId);
    void deleteCurvePoint(CurvePoint curvePoint);
    void deleteCurvePointById(int id);
}
