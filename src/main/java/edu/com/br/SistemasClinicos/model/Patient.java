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
    @Column(length = 100)
    private String name;
    @Column(length = 11, nullable = false)
    private String cpf;
    private Date dateBirth;
    private String phone;
    private String email;
    private String address;
    private String city;
    private String state;
}
