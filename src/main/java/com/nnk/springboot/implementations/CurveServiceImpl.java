package com.nnk.springboot.implementations;

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

@Log4j2
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
@Service
public class CurveServiceImpl implements CurveService {

    private final CurvePointRepository curvePointRepository;
    public List<CurvePoint> getAllCurvePoints() {
        log.info("getAllCurvePoints method called");
        return curvePointRepository.findAll();
    }

    public CurvePoint getCurvePointById(int id) {
        log.info("etCurvePointById method called with : {}", id);
        Optional<CurvePoint> curvePointOptional = curvePointRepository.findById(id);
        return curvePointOptional.orElseThrow(NoSuchElementException::new);
    }

    public CurvePoint getCurvePointByCurveId(int curveId) {
        log.info("getCurvePointByCurveId method called with : {}", curveId);
        Optional<CurvePoint> curvePointOptional = curvePointRepository.findByCurveId(curveId);
        return curvePointOptional.orElseThrow(NoSuchElementException::new);
    }

    public void saveCurvePoint(CurvePoint updatedCurvePoint) {
        log.info("saveCurvePoint method called with : {}", updatedCurvePoint);
        curvePointRepository.save(updatedCurvePoint);
    }

    public void updateCurvePoint(CurvePoint updatedCurvePoint, int curvePointToUpdateId){
        log.info("updateCurvePoint method called with : {}, {}", updatedCurvePoint, curvePointToUpdateId);
        CurvePoint curvePointToUpdate = getCurvePointById(curvePointToUpdateId);
        curvePointToUpdate.setCurveId(updatedCurvePoint.getCurveId());
        curvePointToUpdate.setTerm(updatedCurvePoint.getTerm());
        curvePointToUpdate.setValue(updatedCurvePoint.getValue());
        saveCurvePoint(curvePointToUpdate);
    }

    public void deleteCurvePointById(int id) {
        log.info("deleteCurvePointById method called with : {}", id);
        curvePointRepository.deleteById(id);
    }
}
