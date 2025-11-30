package edu.com.br.SistemasClinicos.service.Patient;

import edu.com.br.SistemasClinicos.dto.Patient.PatientRequest;
import edu.com.br.SistemasClinicos.dto.Patient.PatientResponse;

import java.util.List;

/**
 * Interface que define o contrato de serviço para as operações de negócio
 * relacionadas à entidade Paciente (Patient).
 * <p>
 * A implementação desta interface (como PatientServiceImp) deve gerenciar
 * a lógica de negócio, transações e orquestração entre repositórios e mappers
 * para o módulo de pacientes.
 */
public interface PatientService {

    /**
     * Cria um novo registro de Paciente no sistema.
     * <p>
     * Espera um {@code PatientRequest}, realiza validações (ex: unicidade do CPF)
     * e persiste a nova entidade.
     * * @param request O DTO contendo os dados do Paciente a ser criado.
     * @return O DTO de resposta do Paciente criado, incluindo o ID gerado.
     */
    PatientResponse createPatient(PatientRequest request);

    /**
     * Atualiza os dados de um Paciente existente.
     * <p>
     * Localiza o Paciente pelo ID, aplica as alterações do {@code PatientRequest}
     * e persiste a entidade atualizada.
     * * @param id O ID do Paciente a ser atualizado.
     * @param request O DTO contendo os novos dados do Paciente.
     * @return O DTO de resposta do Paciente atualizado.
     */
    PatientResponse updatePatient(Long id,PatientRequest request);

    /**
     * Remove um Paciente do sistema pelo seu ID.
     * <p>
     * Se o Paciente não for encontrado, deve ser lançada uma exceção de recurso não encontrado.
     * * @param id O ID do Paciente a ser excluído.
     */
    void deletePatient(Long id);

    /**
     * Busca um Paciente específico pelo seu ID.
     * * @param id O ID do Paciente a ser encontrado.
     * @return O DTO de resposta do Paciente encontrado.
     * @throws RuntimeException (ou exceção de recurso não encontrado) Se o Paciente não for encontrado.
     */
    PatientResponse findPatientById(Long id);

    /**
     * Retorna uma lista de todos os Pacientes cadastrados no sistema.
     * * @return Uma lista de DTOs {@code PatientResponse}.
     */
    List<PatientResponse> findAllPatients();

    /**
     * Busca Pacientes cujo nome contenha a string fornecida.
     * <p>
     * A busca geralmente é case-insensitive (ignorando maiúsculas/minúsculas) e por correspondência parcial (LIKE).
     * * @param name A string de busca para o nome do Paciente.
     * @return Uma lista de DTOs {@code PatientResponse} que correspondem ao critério.
     */
    List<PatientResponse> findPatientByName(String name);
}