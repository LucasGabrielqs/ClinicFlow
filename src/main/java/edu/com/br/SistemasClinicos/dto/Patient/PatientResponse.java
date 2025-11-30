package edu.com.br.SistemasClinicos.dto.Patient;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

/**
 * Data Transfer Object (DTO) usado para **enviar** dados de um Paciente
 * em resposta a uma requisição HTTP (GET, POST, PUT).
 * <p>
 * Este objeto reflete a representação dos dados do Paciente que são
 * retornados ao cliente após a criação, busca ou atualização.
 */
@Getter
@Setter
public class PatientResponse {

    private Long id;
    private String name;
    private String cpf;
    private Date dateBirth;
    private String phone;
    private String email;
    private String address;
    private String city;
    private String state;
}