package edu.com.br.SistemasClinicos.dto.Specialty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) usado para receber dados de uma requisição
 * HTTP (POST ou PUT) para a criação ou atualização de uma Especialidade.
 * <p>
 * Contém as regras de Bean Validation que garantem que o nome seja fornecido
 * e que o tamanho da descrição não exceda o limite máximo.
 */
@Getter
@Setter
public class SpecialtyRequest {

    /**
     * Nome da Especialidade (Ex: Cardiologia, Pediatria).
     * Restrições: Não pode ser nulo ou vazio ({@code @NotBlank}).
     */
    @NotBlank(message = "Specialty name is required")
    private String name;

    /**
     * Descrição detalhada da Especialidade.
     * Restrições: Deve ter no máximo 255 caracteres ({@code @Size}).
     */
    @Size(max = 255, message = "Description must have up to 255 characters")
    private String description;
}