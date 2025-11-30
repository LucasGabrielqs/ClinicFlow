package edu.com.br.SistemasClinicos.dto.Specialty;


import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) usado para **enviar** dados de uma Especialidade
 * em resposta a uma requisição HTTP (GET, POST, PUT).
 * <p>
 * Este objeto reflete a representação da Especialidade que é
 * retornada ao cliente, incluindo seu identificador único.
 */
@Getter
@Setter
public class SpecialtyResponse {

    private Long id;
    private String name;
    private String description;
}