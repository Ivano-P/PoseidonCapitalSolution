package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "bid_list")
public class BidList {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    Integer id;

    @NotBlank(message = "Account is mandatory")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Only alphabetic characters and spaces are allowed.")
    @Column(name = "account")
    String account;

    @NotBlank(message = "Type is mandatory")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Only alphabetic characters and spaces are allowed.")
    @Column(name="type")
    String type;

    @DecimalMin(value = "1.0", message = "Bid must be at the least 1.0")
    @NotNull(message = "bid quantity is mandatory")
    @Column(name="bid_quantity")
    Double bidQuantity;

    @Column(name="ask_quantity")
    Double askQuantity;

    @Column(name="bid")
    Double bid;

    @Column(name="ask")
    Double ask;

    @Column(name="benchmark")
    String benchmark;

    @Column(name="bid_list_date")
    Timestamp bidListDate;

    @Column(name="commentary")
    String commentary;

    @Column(name="security")
    String security;

    @Column(name="status")
    String status;

    @Column(name="trader")
    String trader;

    @Column(name="book")
    String book;

    @Column(name="creation_name")
    String creationName;

    @Column(name="creation_date")
    Timestamp creationDate;

    @Column(name="revision_name")
    String revisionName;

    @Column(name="revision_date")
    Timestamp revisionDate;

    @Column(name="deal_name")
    String dealName;

    @Column(name="deal_type")
    String dealType;

    @Column(name="source_list_id")
    String sourceListId;

    @Column(name="side")
    String side;

}
