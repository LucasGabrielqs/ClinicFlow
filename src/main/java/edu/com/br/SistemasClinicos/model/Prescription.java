package edu.com.br.SistemasClinicos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "prescription")
@Getter
@Setter
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Description is required")
    private String description;

    private String medications;

    @NotNull(message = "Issue date is required")
    private LocalDate issueDate;

    @NotNull(message = "Appointment reference is required")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;
}
