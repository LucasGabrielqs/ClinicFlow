package edu.com.br.SistemasClinicos.service.Specialty;


import edu.com.br.SistemasClinicos.dto.Specialty.SpecialtyRequest;
import edu.com.br.SistemasClinicos.dto.Specialty.SpecialtyResponse;
import edu.com.br.SistemasClinicos.model.Specialty;

import java.util.List;

/**
 * Interface que define o contrato de serviço para as operações de negócio
 * relacionadas à entidade Especialidade (Specialty).
 * <p>
 * A implementação desta interface (como SpecialtyServiceImp) é responsável por
 * gerenciar a lógica de negócio, transações e a manipulação de dados para as especialidades médicas.
 */
public interface SpecialtyService {

    /**
     * Cria uma nova Especialidade no sistema.
     * <p>
     * Espera um {@code SpecialtyRequest} e persiste a nova entidade no repositório.
     * * @param request O DTO contendo os dados da Especialidade a ser criada.
     * @return O DTO de resposta da Especialidade criada, incluindo o ID gerado.
     */
    SpecialtyResponse createSpecialty(SpecialtyRequest request);

    /**
     * Atualiza os dados de uma Especialidade existente.
     * <p>
     * Localiza a Especialidade pelo ID e aplica as alterações do {@code SpecialtyRequest}.
     * * @param id O ID da Especialidade a ser atualizada.
     * @param request O DTO contendo os novos dados da Especialidade.
     * @return O DTO de resposta da Especialidade atualizada.
     */
    SpecialtyResponse updateSpecialty(Long id,SpecialtyRequest request);

    /**
     * Remove uma Especialidade do sistema pelo seu ID.
     * <p>
     * Se a Especialidade não for encontrada, deve ser tratada a exceção apropriada.
     * * @param id O ID da Especialidade a ser excluída.
     */
    void deleteSpecialty(Long id);

    /**
     * Retorna uma lista de todas as Especialidades cadastradas no sistema.
     * * @return Uma lista de DTOs {@code SpecialtyResponse}.
     */
    List<SpecialtyResponse> findAll();

    /**
     * Busca uma Especialidade específica pelo seu ID.
     * * @param id O ID da Especialidade a ser encontrado.
     * @return O DTO de resposta da Especialidade encontrada.
     * @throws RuntimeException (ou exceção de recurso não encontrado) Se a Especialidade não for encontrada.
     */
    SpecialtyResponse findById(Long id);
}