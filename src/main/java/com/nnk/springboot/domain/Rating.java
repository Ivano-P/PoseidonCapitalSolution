package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    Integer id;

    @Pattern(regexp = "^(|[A-Da-d]{1,3}([+-/])?|\\\\d|/)$", message = "Invalid rating, rating should be something like AAA, A-, Caa3, ... ")
    @Column(name = "moody_rating")
    String moodysRating;


    @Pattern(regexp = "^(|[A-Da-d]{1,3}([+-])?|\\\\d)$", message = "Invalid rating, rating should be something like AAA, A-, Caa3, ... ")
    @Column(name = "sand_p_rating")
    String sandPRating;


    @Pattern(regexp = "^(|[A-Da-d]{1,3}([+-])?|\\\\d)$", message = "Invalid rating, rating should be something like AAA, A-, Caa3, ... ")

    @Column(name = "fitch_rating")
    String fitchRating;

    @Min(value = 1)
    @NotNull(message = "order number is mandatory")
    @Column(name = "order_number")
    int orderNumber;
}
