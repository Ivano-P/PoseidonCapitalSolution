package com.nnk.springboot.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "rating")
public class Rating {

    @Id
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
