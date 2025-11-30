package edu.com.br.SistemasClinicos.dto.Doctor;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) usado para enviar dados de um Doutor
 * em resposta a uma requisição HTTP (GET, POST, PUT).
 * <p>
 * Este objeto é responsável por expor as informações do Doutor de forma
 * estruturada, incluindo o ID da Especialidade e seu nome, facilitando
 * a comunicação com o cliente.
 */
@Getter
@Setter
public class DoctorResponse {
    private Long id;
    private String name;
    private String cpf;
    private String email;
    private String phone;
    private String crm;
    private Long specialtyId;
    private String specialtyName;
}