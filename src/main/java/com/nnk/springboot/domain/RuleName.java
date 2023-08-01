package com.nnk.springboot.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "rule_name")
public class RuleName {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    Integer id;
    @Column(name = "name")
    String name;
    @Column(name = "description")
    String description;
    @Column(name = "json")
    String json;
    @Column(name = "template")
    String template;
    @Column(name = "sql_str")
    String sqlStr;
    @Column(name = "sql_part")
    String sqlPart;

}
