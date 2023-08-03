package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.implementations.CurveServiceImpl;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CurveServiceTest {

    @InjectMocks
    CurveServiceImpl curveService;

    @Mock
    CurvePointRepository curvePointRepository;

    @Mock
    CurvePoint mockCurvePoint;

    @BeforeEach
    void setUp() {
        mockCurvePoint = new CurvePoint();
        mockCurvePoint.setCurveId(5);
        mockCurvePoint.setTerm(10.00);
        mockCurvePoint.setValue(10.00);
    }

    @Test
    void testGetAllCurvePoints() {
        //Arrange
        when(curvePointRepository.findAll()).thenReturn(Collections.singletonList(mockCurvePoint));

        //Act
        List<CurvePoint> result = curveService.getAllCurvePoints();

        //Assert
        assertThat(result).isEqualTo(Collections.singletonList(mockCurvePoint));
    }

    @Test
    void testGetCurvePointById() {
        //Arrange
        when(curvePointRepository.findById(anyInt())).thenReturn(Optional.of(mockCurvePoint));
        //Act
        CurvePoint result = curveService.getCurvePointById(1);
        //Assert
        assertThat(result).isEqualTo(mockCurvePoint);
    }

    @Test
    void testGetCurvePointByCurveId() {
        //Arrange
        when(curvePointRepository.findByCurveId(anyInt())).thenReturn(Optional.of(mockCurvePoint));
        //Act
        CurvePoint result = curveService.getCurvePointByCurveId(1);
        //Assert
        assertThat(result).isEqualTo(mockCurvePoint);
    }

    @Test
    void testSaveCurvePoint() {
        //Act
        curveService.saveCurvePoint(mockCurvePoint);
        //Assert
        verify(curvePointRepository, times(1)).save(mockCurvePoint);
    }

    @Test
    void testUpdateCurvePoint() {
        //Arrange
        when(curvePointRepository.findById(anyInt())).thenReturn(Optional.of(mockCurvePoint));

        CurvePoint newCurvePoint = new CurvePoint();
        newCurvePoint.setCurveId(6);
        newCurvePoint.setTerm(20.00);
        newCurvePoint.setValue(20.00);

        //Act
        curveService.updateCurvePoint(newCurvePoint, 1);

        //Assert
        assertThat(mockCurvePoint.getCurveId()).isEqualTo(newCurvePoint.getCurveId());
        assertThat(mockCurvePoint.getTerm()).isEqualTo(newCurvePoint.getTerm());
        assertThat(mockCurvePoint.getValue()).isEqualTo(newCurvePoint.getValue());

        verify(curvePointRepository, times(1)).save(mockCurvePoint);
    }

    @Test
    void testDeleteCurvePoint() {
        //Act
        curveService.deleteCurvePoint(mockCurvePoint);
        //Assert
        verify(curvePointRepository, times(1)).delete(mockCurvePoint);
    }

    @Test
    void testDeleteCurvePointById() {
        //Act
        curveService.deleteCurvePointById(1);
        //Assert
        verify(curvePointRepository, times(1)).deleteById(1);
    }


}
