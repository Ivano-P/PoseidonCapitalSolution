package com.nnk.springboot.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "bid_list")
public class BidList {

    @Id
    @Column(name="bidlist_id")
    Integer BidListId;

    @Column(name = "account")
    String account;

    @Column(name="type")
    String type;

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
