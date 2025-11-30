package edu.com.br.SistemasClinicos.service.Doctor;

import edu.com.br.SistemasClinicos.dto.Doctor.DoctorRequest;
import edu.com.br.SistemasClinicos.dto.Doctor.DoctorResponse;

import java.util.List;

/**
 * Interface que define o contrato de serviço para as operações de negócio
 * relacionadas à entidade Doutor (Doctor).
 * <p>
 * A implementação desta interface deve gerenciar a lógica de negócio,
 * transações e orquestração entre repositórios e mappers.
 */
public interface DoctorService {

    /**
     * Cria um novo registro de Doutor no sistema.
     * <p>
     * Espera um {@code DoctorRequest}, realiza as validações de unicidade
     * e integridade (ex: CPF, CRM) e persiste a nova entidade.
     * * @param request O DTO contendo os dados do Doutor a ser criado.
     * @return O DTO de resposta do Doutor criado, incluindo o ID gerado.
     */
    DoctorResponse createDoctor(DoctorRequest request);

    /**
     * Atualiza os dados de um Doutor existente.
     * <p>
     * Localiza o Doutor pelo ID, aplica as alterações do {@code DoctorRequest}
     * e persiste a entidade atualizada.
     * * @param id O ID do Doutor a ser atualizado.
     * @param request O DTO contendo os novos dados do Doutor.
     * @return O DTO de resposta do Doutor atualizado.
     */
    DoctorResponse updateDoctor(Long id,DoctorRequest request);

    /**
     * Remove um Doutor do sistema pelo seu ID.
     * <p>
     * Se o Doutor não for encontrado, deve ser lançada uma exceção de recurso não encontrado.
     * * @param id O ID do Doutor a ser excluído.
     */
    void deleteDoctor(Long id);

    /**
     * Busca um Doutor específico pelo seu ID.
     * * @param id O ID do Doutor a ser encontrado.
     * @return O DTO de resposta do Doutor encontrado.
     */
    DoctorResponse findDoctorById(Long id);

    /**
     * Retorna uma lista de todos os Doutores cadastrados no sistema.
     * * @return Uma lista de DTOs {@code DoctorResponse}.
     */
    List<DoctorResponse> findall();

    /**
     * Busca Doutores cujo nome contenha a string fornecida.
     * <p>
     * A busca geralmente é case-insensitive (ignorando maiúsculas/minúsculas).
     * * @param name A string de busca para o nome do Doutor.
     * @return Uma lista de DTOs {@code DoctorResponse} que correspondem ao critério.
     */
    List<DoctorResponse> findDoctorByName(String name);

    /**
     * Busca Doutores filtrando pelo nome da sua Especialidade.
     * * @param specialtyName O nome da Especialidade a ser usada como filtro.
     * @return Uma lista de DTOs {@code DoctorResponse} com a Especialidade correspondente.
     */
    List<DoctorResponse> findDoctorBySpecialtyName(String specialtyName);
}