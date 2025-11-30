package edu.com.br.SistemasClinicos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointment")
@Getter
@Setter
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Appointment date and time are required")
    @Future(message = "Appointment must be scheduled in the future")
    private LocalDateTime dateTime;

    @NotNull(message = "Patient is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @NotNull(message = "Doctor is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @Column(length = 255)
    private String notes;

    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL)
    private Prescription prescription;

}
