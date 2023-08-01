package com.nnk.springboot.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    Integer id;

    @Column(name = "moody_rating")
    String moodysRating;

    @Column(name = "sand_p_rating")
    String sandPRating;

    @Column(name = "fitch_rating")
    String fitchRating;

    @Column(name = "order_number")
    Integer orderNumber;
}
