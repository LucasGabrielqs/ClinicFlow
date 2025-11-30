package edu.com.br.SistemasClinicos.service.Patient;


import edu.com.br.SistemasClinicos.dto.Patient.PatientRequest;
import edu.com.br.SistemasClinicos.dto.Patient.PatientResponse;
import edu.com.br.SistemasClinicos.mapper.PatientMapper;
import edu.com.br.SistemasClinicos.model.Patient;
import edu.com.br.SistemasClinicos.repository.PatientRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementação da interface {@code PatientService}.
 * <p>
 * Contém a lógica de negócio para o gerenciamento de Pacientes,
 * orquestrando o acesso a dados via {@code PatientRepository} e a conversão de objetos
 * via {@code PatientMapper}. Utiliza {@code @RequiredArgsConstructor} para injeção de dependência.
 */
@Service
@RequiredArgsConstructor
public class PatientServiceImp implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    /**
     * Cria um novo Paciente no sistema.
     * <p>
     * 1. Verifica a unicidade do CPF usando o repositório.
     * 2. Mapeia o DTO de requisição para a Entidade Paciente.
     * 3. Persiste o Paciente no repositório.
     * 4. Mapeia a Entidade salva para um DTO de resposta.
     * * @param request DTO contendo os dados do Paciente a ser criado.
     * @return DTO de resposta do Paciente criado.
     * @throws RuntimeException Se o CPF já existir.
     */
    @Override
    public PatientResponse createPatient(PatientRequest request) {
        if (patientRepository.existsByCpf(request.getCpf())) {
            throw new RuntimeException("CPF exists"); // Lançar exceção de negócio (ex: CpfAlreadyExistsException)
        }
        Patient patient = patientMapper.toEntity(request);

        Patient savedPatient =patientRepository.save(patient);

        return patientMapper.toResponse(savedPatient);
    }

    /**
     * Atualiza os dados de um Paciente existente.
     * <p>
     * 1. Busca o Paciente existente pelo ID.
     * 2. Mapeia os dados da requisição para uma nova Entidade.
     * 3. Mantém o ID original na nova Entidade mapeada para garantir a operação de atualização.
     * 4. Persiste a Entidade atualizada.
     * * @param id O ID do Paciente a ser atualizado.
     * @param request DTO contendo os novos dados do Paciente.
     * @return DTO de resposta do Paciente atualizado.
     * @throws RuntimeException Se o Paciente não for encontrado.
     */
    @Override
    public PatientResponse updatePatient(Long id, PatientRequest request) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Patient updatedPatient = patientMapper.toEntity(request);
        updatedPatient.setId(patient.getId()); // Essencial para o JPA/Hibernate entender que é um update

        updatedPatient = patientRepository.save(updatedPatient);
        return patientMapper.toResponse(updatedPatient);
    }

    /**
     * Exclui um Paciente do sistema pelo seu ID.
     * * @param id O ID do Paciente a ser excluído.
     * @throws org.springframework.dao.EmptyResultDataAccessException Se o ID não for encontrado (exceção padrão do Spring Data Jpa).
     */
    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    /**
     * Busca um Paciente pelo seu ID.
     * * @param id O ID do Paciente a ser buscado.
     * @return DTO de resposta do Paciente encontrado.
     * @throws RuntimeException Se o Paciente não for encontrado.
     */
    @Override
    public PatientResponse findPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        return patientMapper.toResponse(patient);
    }

    /**
     * Retorna todos os Pacientes cadastrados.
     * * @return Uma lista de DTOs {@code PatientResponse}.
     */
    @Override
    public List<PatientResponse> findAllPatients() {
        return patientMapper.toResponseList(patientRepository.findAll());
    }

    /**
     * Busca Pacientes pelo nome.
     * <p>
     * Assume-se que {@code patientRepository.findByName(name)} realiza uma busca por correspondência.
     * * @param name O nome ou parte do nome a ser buscado.
     * @return Uma lista de DTOs {@code PatientResponse} correspondentes.
     * @throws RuntimeException Se nenhum Paciente for encontrado com o nome fornecido.
     */
    @Override
    public List<PatientResponse> findPatientByName(String name) {
        List<Patient> patients = patientRepository.findByName(name);

        if (patients.isEmpty()) {
            throw new RuntimeException("Patient not found");
        }

        return patients.stream()
                .map(patientMapper::toResponse)
                .toList();
    }
}