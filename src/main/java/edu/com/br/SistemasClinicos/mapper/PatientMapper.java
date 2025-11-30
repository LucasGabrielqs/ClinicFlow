package edu.com.br.SistemasClinicos.mapper;

import edu.com.br.SistemasClinicos.dto.Patient.PatientRequest;
import edu.com.br.SistemasClinicos.dto.Patient.PatientResponse;
import edu.com.br.SistemasClinicos.model.Patient;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interface Mapper responsável pela conversão (mapeamento) entre a Entidade Patient
 * e seus Data Transfer Objects (DTOs) de requisição e resposta.
 * <p>
 * Utiliza a biblioteca MapStruct, configurada para ser injetada como um componente Spring ({@code componentModel = "spring"}).
 */
@Mapper(componentModel = "spring")
public interface PatientMapper {

    /**
     * Converte um DTO de Requisição (PatientRequest) para a Entidade Paciente (Patient).
     * <p>
     * O mapeamento é direto, assumindo que os nomes dos campos entre o DTO e a Entidade são idênticos.
     * * @param request O DTO de requisição contendo os dados do Paciente.
     * @return A Entidade Patient preenchida com os dados da requisição.
     */
    Patient toEntity(PatientRequest request);

    /**
     * Converte a Entidade Paciente (Patient) para um DTO de Resposta (PatientResponse).
     * <p>
     * O mapeamento é direto.
     * * @param patient A Entidade Patient a ser mapeada.
     * @return O DTO PatientResponse contendo os dados do Paciente.
     */
    PatientResponse toResponse(Patient patient);

    /**
     * Converte uma lista de Entidades Paciente (List<Patient>) para uma lista de
     * DTOs de Resposta (List<PatientResponse>).
     * <p>
     * Utiliza o método {@code toResponse(Patient patient)} internamente para
     * mapear cada elemento da lista.
     * * @param patients A lista de Entidades Patient.
     * @return A lista de DTOs PatientResponse.
     */
    List<PatientResponse> toResponseList(List<Patient> patients);
}