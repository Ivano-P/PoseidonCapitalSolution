package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@Table(name = "rule_name")
public class RuleName {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    Integer id;

    @NotBlank(message = "rule name is mandatory")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Only alphabetic characters and spaces are allowed.")
    @Column(name = "name")
    String name;

    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Only alphabetic characters and spaces are allowed.")
    @Column(name = "description")
    String description;

    @NotBlank(message = "json is mandatory")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Only alphabetic characters and spaces are allowed.")
    @Column(name = "json")
    String json;

    @NotBlank(message = "template is mandatory")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Only alphabetic characters and spaces are allowed.")
    @Column(name = "template")
    String template;

    @NotBlank(message = "sql str is mandatory")
    @Column(name = "sql_str")
    String sqlStr;

    @NotBlank(message = "sql part is mandatory")
    @Column(name = "sql_part")
    String sqlPart;

}
