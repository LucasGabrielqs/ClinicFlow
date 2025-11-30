package edu.com.br.SistemasClinicos.dto.Doctor;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) usado para receber dados de uma requisição
 * HTTP (POST ou PUT) para a criação ou atualização de um Doutor.
 * <p>
 * Contém as regras de Bean Validation para garantir que os dados de entrada
 * estejam corretos e completos antes do processamento na camada de serviço.
 */
@Getter
@Setter
public class DoctorRequest {

    /**
     * Nome do doutor.
     * Restrições: Não pode ser nulo ou vazio ({@code @NotBlank}).
     */
    @NotBlank(message = "Doctor name is required")
    private String name;

    /**
     * Cadastro de Pessoa Física (CPF) do doutor.
     * Restrições: Não pode ser nulo ou vazio ({@code @NotBlank}).
     * Deve ter exatamente 11 caracteres ({@code @Size}).
     */
    @NotBlank(message = "CPF is required")
    @Size(min = 11, max = 11, message = "CPF must contain 11 characters")
    private String cpf;

    /**
     * Endereço de e-mail do doutor.
     * Restrições: Deve ser um formato de e-mail válido ({@code @Email})
     * e não pode ser nulo ou vazio ({@code @NotBlank}).
     */
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    /**
     * Número de telefone para contato.
     * Restrições: Não pode ser nulo ou vazio ({@code @NotBlank}).
     */
    @NotBlank(message = "Phone number is required")
    private String phone;

    /**
     * Número de Registro no Conselho Regional de Medicina (CRM).
     * Restrições: Não pode ser nulo ou vazio ({@code @NotBlank}).
     */
    @NotBlank(message = "CRM is required")
    private String crm;

    /**
     * ID da Especialidade associada ao doutor.
     * Restrições: Não pode ser nulo ({@code @NotNull}). Este ID será usado
     * para buscar a entidade Specialty correspondente no banco de dados.
     */
    @NotNull(message = "Specialty ID is required")
    private Long specialtyId;
}