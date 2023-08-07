package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    Integer id;

    @NotBlank
    @Column(name = "moody_rating")
    String moodysRating;

    @NotBlank
    @Column(name = "sand_p_rating")
    String sandPRating;

    @NotBlank
    @Column(name = "fitch_rating")
    String fitchRating;

    @NotNull
    @Column(name = "order_number")
    Integer orderNumber;
}
