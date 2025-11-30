package edu.com.br.SistemasClinicos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "patient")
@Getter
@Setter
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100,nullable = false)
    private String name;

    @Column(length = 11, nullable = false,unique = true)
    private String cpf;

    private Date dateBirth;

    @Column(length = 18, nullable = false)
    private String phone;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(nullable = false)
    private String address;

    @Column(length = 50, nullable = false)
    private String city;

    @Column(length = 50, nullable = false)
    private String state;
}
