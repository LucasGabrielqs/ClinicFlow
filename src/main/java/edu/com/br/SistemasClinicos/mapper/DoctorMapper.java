package edu.com.br.SistemasClinicos.mapper;

import edu.com.br.SistemasClinicos.dto.Doctor.DoctorRequest;
import edu.com.br.SistemasClinicos.dto.Doctor.DoctorResponse;
import edu.com.br.SistemasClinicos.model.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Interface Mapper responsável pela conversão (mapeamento) entre a Entidade Doctor
 * e seus Data Transfer Objects (DTOs) de requisição e resposta.
 * <p>
 * Utiliza a biblioteca MapStruct, configurada para ser injetada como um componente Spring.
 */
@Mapper(componentModel = "spring")
public interface DoctorMapper {

    /**
     * Converte um DTO de Requisição (DoctorRequest) para a Entidade Doutor (Doctor).
     * <p>
     * O campo 'id' é ignorado, pois é gerado pelo banco de dados.
     * O campo 'specialty' é ignorado neste mapeamento, pois a lógica de associação
     * (buscar a Entidade Specialty pelo ID) geralmente ocorre na camada de Serviço.
     * * @param doctorRequest O DTO de requisição contendo os dados do Doutor.
     * @return A Entidade Doctor preenchida com os dados da requisição.
     */
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "specialty",ignore = true)
    Doctor toEntity(DoctorRequest doctorRequest);

    /**
     * Converte a Entidade Doutor (Doctor) para um DTO de Resposta (DoctorResponse).
     * <p>
     * Realiza o mapeamento aninhado (flatting) para extrair o ID e o Nome da
     * Entidade Specialty aninhada e colocá-los diretamente nos campos
     * 'specialtyId' e 'specialtyName' do DTO de resposta.
     * * @param doctor A Entidade Doctor a ser mapeada.
     * @return O DTO DoctorResponse contendo os dados do Doutor, incluindo a especialidade.
     */
    @Mapping(target = "specialtyId", source = "specialty.id")
    @Mapping(target = "specialtyName", source = "specialty.name")
    DoctorResponse toResponse(Doctor doctor);

    /**
     * Converte uma lista de Entidades Doutor (List<Doctor>) para uma lista de
     * DTOs de Resposta (List<DoctorResponse>).
     * <p>
     * Utiliza o método {@code toResponse(Doctor doctor)} internamente para
     * mapear cada elemento da lista.
     * * @param doctors A lista de Entidades Doctor.
     * @return A lista de DTOs DoctorResponse.
     */
    List<DoctorResponse> toResponseList(List<Doctor> doctors);
}