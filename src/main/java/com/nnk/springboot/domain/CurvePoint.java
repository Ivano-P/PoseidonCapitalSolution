package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Timestamp;


@Data
@Entity
@Table(name = "curve_point")
public class CurvePoint {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    Integer id;

    @NotNull
    @Column(name = "curve_id")
    int curveId;

    @Column(name = "as_of_date")
    Timestamp asOfDate;

    @Column(name = "term")
    Double term;

    @Column(name = "value")
    Double value;

    @Column(name = "creation_date")
    Timestamp creationDate;

}
