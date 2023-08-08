package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
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

    @NotNull(message = "curve id is mandatory")
    @Min(value = 1)
    @Column(name = "curve_id")
    int curveId;

    @Column(name = "as_of_date")
    Timestamp asOfDate;

    @NotNull(message = "term is mandatory")
    @DecimalMin(value = "1.00", message = "term must be at the least 1.0")
    @Column(name = "term")
    Double term;

    @NotNull(message = "value is mandatory")
    @DecimalMin(value = "1.00", message = "value must be at the least 1.0")
    @Column(name = "value")
    Double value;

    @Column(name = "creation_date")
    Timestamp creationDate;

}
