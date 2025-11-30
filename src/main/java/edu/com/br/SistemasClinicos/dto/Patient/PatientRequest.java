package edu.com.br.SistemasClinicos.dto.Patient;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

/**
 * Data Transfer Object (DTO) usado para receber dados de uma requisição
 * HTTP (POST ou PUT) para a criação ou atualização de um Paciente.
 * <p>
 * Contém as regras de Bean Validation para garantir que os dados de entrada
 * estejam corretos e completos antes do processamento na camada de serviço.
 */
@Getter
@Setter
public class PatientRequest {

    /**
     * Nome completo do Paciente.
     * Restrições: Não pode ser nulo ou vazio ({@code @NotBlank}).
     */
    @NotBlank(message = "Patient name is required")
    private String name;

    /**
     * Cadastro de Pessoa Física (CPF) do paciente.
     * Restrições: Não pode ser nulo ou vazio ({@code @NotBlank}).
     * Deve ter exatamente 11 caracteres ({@code @Size}).
     */
    @NotBlank(message = "CPF is required")
    @Size(min = 11, max = 11, message = "CPF must contain 11 characters")
    private String cpf;

    /**
     * Data de Nascimento do Paciente.
     * Restrições: Não pode ser nula ({@code @NotNull}).
     */
    @NotNull(message = "Date of Birth is required")
    private Date dateBirth;

    /**
     * Número de telefone para contato.
     * Restrições: Não pode ser nulo ou vazio ({@code @NotBlank}).
     */
    @NotBlank(message = "Phone number is required")
    private String phone;

    /**
     * Endereço de e-mail do Paciente.
     * Restrições: Deve ser um formato de e-mail válido ({@code @Email})
     * e não pode ser nulo ou vazio ({@code @NotBlank}).
     */
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    /**
     * Endereço residencial do Paciente.
     * Restrições: Não pode ser nulo ou vazio ({@code @NotBlank}).
     */
    @NotBlank(message = "Address is required")
    private String address;

    /**
     * Cidade de residência do Paciente.
     * Restrições: Não pode ser nulo ou vazio ({@code @NotBlank}).
     */
    @NotBlank(message = "City is required")
    private String city;

    /**
     * Estado (UF) de residência do Paciente.
     * Restrições: Não pode ser nulo ou vazio ({@code @NotBlank}).
     */
    @NotBlank(message = "State is required")
    private String state;
}