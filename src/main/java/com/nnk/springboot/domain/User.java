package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Pattern(regexp = "^[a-zA-Z]+$", message = "Only alphabetic characters are allowed.")
    @NotBlank(message = "Username is mandatory")
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    @NotBlank(message = "Password is mandatory")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[*@#$€%^&+=]).+$", message = "Password must have at least one uppercase letter, one number, and one symbol (accepted symbols '').")
    @Length(min = 8, message = "password must be at the least 8 characters")
    private String password;

    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Only alphabetic characters and spaces are allowed.")
    @NotBlank(message = "FullName is mandatory")
    @Column(name = "fullname")
    private String fullname;

    @NotBlank(message = "Role is mandatory")
    @Column(name = "role")
    private String role;
}
