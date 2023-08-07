package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "trade")
public class Trade {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "trade_id")
    Integer tradeId;

    @NotBlank
    @Column(name = "account")
    String account;

    @NotBlank
    @Column(name = "type")
    String type;

    @NotNull
    @Column(name = "buy_quantity")
    Double buyQuantity;

    @Column(name = "sell_quantity")
    Double sellQuantity;

    @Column(name = "buy_price")
    Double buyPrice;

    @Column(name = "sell_price")
    Double sellPrice;

    @Column(name = "benchmark")
    String benchmark;

    @Column(name = "trade_date")
    Timestamp tradeDate;

    @Column(name = "security")
    String security;

    @Column(name = "status")
    String status;

    @Column(name = "trader")
    String trader;

    @Column(name = "book")
    String book;

    @Column(name = "creation_name")
    String creationName;

    @Column(name = "creation_date")
    Timestamp creationDate;

    @Column(name = "revision_name")
    String revisionName;

    @Column(name = "revision_date")
    Timestamp revisionDate;

    @Column(name = "deal_name")
    String dealName;

    @Column(name = "deal_type")
    String dealType;

    @Column(name = "source_list_id")
    String sourceListId;

    @Column(name = "side")
    String side;
}
