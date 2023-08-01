package com.nnk.springboot.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Timestamp;


@Data
@Entity
@Table(name = "curve_point")
public class CurvePoint {


    @Id
    @Column(name = "id")
    Integer id;

    @Column(name = "curve_id")
    Integer curveId;

    @Column(name = "as_of_date")
    Timestamp asOfDate;

    @Column(name = "term")
    Double term;

    @Column(name = "value")
    Double value;

    @Column(name = "creation_date")
    Timestamp creationDate;

}
