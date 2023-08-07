package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "rule_name")
public class RuleName {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    Integer id;

    @NotBlank
    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description;

    @NotBlank()
    @Column(name = "json")
    String json;

    @NotBlank
    @Column(name = "template")
    String template;

    @NotBlank
    @Column(name = "sql_str")
    String sqlStr;

    @NotBlank
    @Column(name = "sql_part")
    String sqlPart;

}
