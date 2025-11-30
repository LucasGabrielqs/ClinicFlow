package edu.com.br.SistemasClinicos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "doctor")
@Getter
@Setter
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 11, nullable = false,unique = true)
    private String cpf;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 18, nullable = false)
    private String phone;

    @Column(length = 50, nullable = false)
    private String crm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;
}
