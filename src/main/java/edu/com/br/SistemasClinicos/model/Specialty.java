package edu.com.br.SistemasClinicos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "specialty")
@Getter
@Setter
public class Specialty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Specialty name is required")
    @Column(nullable = false, unique = true)
    private String name;

    @Size(max = 255, message = "Description must have up to 255 characters")
    @Column(length = 255)
    private String description;
}
