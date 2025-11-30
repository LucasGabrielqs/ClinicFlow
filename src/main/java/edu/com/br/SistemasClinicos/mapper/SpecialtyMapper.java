package edu.com.br.SistemasClinicos.mapper;



import edu.com.br.SistemasClinicos.dto.Specialty.SpecialtyRequest;
import edu.com.br.SistemasClinicos.dto.Specialty.SpecialtyResponse;
import edu.com.br.SistemasClinicos.model.Specialty;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interface Mapper responsável pela conversão (mapeamento) entre a Entidade Specialty
 * e seus Data Transfer Objects (DTOs) de requisição e resposta.
 * <p>
 * Utiliza a biblioteca MapStruct, configurada para ser injetada como um componente Spring ({@code componentModel = "spring"}).
 */
@Mapper(componentModel = "spring")
public interface SpecialtyMapper {

    /**
     * Converte um DTO de Requisição (SpecialtyRequest) para a Entidade Especialidade (Specialty).
     * <p>
     * O mapeamento é direto, ideal para operações de criação ou atualização.
     * * @param request O DTO de requisição contendo os dados da Especialidade.
     * @return A Entidade Specialty preenchida com os dados da requisição.
     */
    Specialty toEntity(SpecialtyRequest request);

    /**
     * Converte a Entidade Especialidade (Specialty) para um DTO de Resposta (SpecialtyResponse).
     * <p>
     * O mapeamento é direto.
     * * @param specialty A Entidade Specialty a ser mapeada.
     * @return O DTO SpecialtyResponse contendo os dados da Especialidade.
     */
    SpecialtyResponse toResponse(Specialty specialty);

    /**
     * Converte uma lista de Entidades Especialidade (List<Specialty>) para uma lista de
     * DTOs de Resposta (List<SpecialtyResponse>).
     * <p>
     * Utiliza o método {@code toResponse(Specialty specialty)} internamente para
     * mapear cada elemento da lista.
     * * @param specialties A lista de Entidades Specialty.
     * @return A lista de DTOs SpecialtyResponse.
     */
    List<SpecialtyResponse> toResponseList(List<Specialty> specialties);
}