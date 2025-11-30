package edu.com.br.SistemasClinicos.service.Doctor;

import edu.com.br.SistemasClinicos.dto.Doctor.DoctorRequest;
import edu.com.br.SistemasClinicos.dto.Doctor.DoctorResponse;
import edu.com.br.SistemasClinicos.mapper.DoctorMapper;
import edu.com.br.SistemasClinicos.model.Doctor;
import edu.com.br.SistemasClinicos.model.Specialty;
import edu.com.br.SistemasClinicos.repository.DoctorRepository;
import edu.com.br.SistemasClinicos.repository.SpecialtyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementação da interface {@code DoctorService}.
 * <p>
 * Contém a lógica de negócio para o gerenciamento de Doutores,
 * orquestrando o acesso a dados via repositórios e a conversão de objetos
 * via mappers. Utiliza {@code @RequiredArgsConstructor} para injeção de dependência.
 */
@Service
@RequiredArgsConstructor
public class DoctorServiceImp implements DoctorService{

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;
    private final SpecialtyRepository specialtyRepository;

    /**
     * Cria um novo Doutor no sistema.
     * <p>
     * 1. Verifica a unicidade do CPF.
     * 2. Mapeia o DTO de requisição para a Entidade Doutor (ignorando a especialidade e ID).
     * 3. Busca a Entidade Specialty pelo ID.
     * 4. Associa a Specialty ao Doutor.
     * 5. Persiste o Doutor no repositório.
     * 6. Mapeia a Entidade salva para um DTO de resposta.
     * * @param request DTO contendo os dados do Doutor a ser criado.
     * @return DTO de resposta do Doutor criado.
     * @throws RuntimeException Se o CPF já existir ou a Especialidade não for encontrada.
     */
    @Override
    public DoctorResponse createDoctor(DoctorRequest request) {
        if (doctorRepository.existsByCpf(request.getCpf())) {
            throw new RuntimeException("CPF exists"); // Lançar exceção de negócio mais específica (ex: CpfAlreadyExistsException)
        }

        Doctor doctor = doctorMapper.toEntity(request);

        Specialty specialty = specialtyRepository.findById(request.getSpecialtyId())
                .orElseThrow(() -> new RuntimeException("Specialty not found"));

        doctor.setSpecialty(specialty);
        Doctor savedDoctor = doctorRepository.save(doctor);
        return doctorMapper.toResponse(savedDoctor);

    }

    /**
     * Atualiza um Doutor existente.
     * <p>
     * 1. Busca o Doutor existente pelo ID.
     * 2. Mapeia os dados da requisição para uma nova Entidade Doutor.
     * 3. Mantém o ID original na nova Entidade mapeada.
     * 4. Busca e associa a Especialidade.
     * 5. Persiste a Entidade atualizada.
     * * @param id O ID do Doutor a ser atualizado.
     * @param request DTO contendo os novos dados do Doutor.
     * @return DTO de resposta do Doutor atualizado.
     * @throws RuntimeException Se o Doutor ou a Especialidade não forem encontrados.
     */
    @Override
    public DoctorResponse updateDoctor(Long id, DoctorRequest request) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Doctor updatedDoctor = doctorMapper.toEntity(request);
        updatedDoctor.setId(doctor.getId()); // Garante que o ID da entidade original seja mantido para a atualização

        Specialty specialty = specialtyRepository.findById(request.getSpecialtyId())
                .orElseThrow(() -> new RuntimeException("Specialty not found"));
        updatedDoctor.setSpecialty(specialty);

        updatedDoctor = doctorRepository.save(updatedDoctor);
        return doctorMapper.toResponse(updatedDoctor);
    }

    /**
     * Exclui um Doutor do sistema pelo seu ID.
     * * @param id O ID do Doutor a ser excluído.
     * @throws org.springframework.dao.EmptyResultDataAccessException Se o ID não for encontrado (exceção padrão do Spring Data Jpa).
     */
    @Override
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

    /**
     * Busca um Doutor pelo seu ID.
     * * @param id O ID do Doutor a ser buscado.
     * @return DTO de resposta do Doutor encontrado.
     * @throws RuntimeException Se o Doutor não for encontrado.
     */
    @Override
    public DoctorResponse findDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        return doctorMapper.toResponse(doctor);
    }

    /**
     * Retorna todos os Doutores cadastrados.
     * * @return Uma lista de DTOs {@code DoctorResponse}.
     */
    @Override
    public List<DoctorResponse> findall() {
        return doctorMapper.toResponseList(doctorRepository.findAll());
    }

    /**
     * Busca Doutores pelo nome.
     * * @param name O nome ou parte do nome a ser buscado.
     * @return Uma lista de DTOs {@code DoctorResponse} correspondentes.
     * @throws RuntimeException Se nenhum Doutor for encontrado com o nome fornecido.
     */
    @Override
    public List<DoctorResponse> findDoctorByName(String name) {
        // Assume-se que doctorRepository.findByName(String name) faz uma busca aproximada (ex: Like %name%)
        List<Doctor> doctors = doctorRepository.findByName(name);

        if (doctors.isEmpty()) {
            throw new RuntimeException("Patient not found"); // Sugestão: Alterar para "Doctor not found" para consistência
        }

        return doctors.stream()
                .map(doctorMapper::toResponse)
                .toList();
    }

    /**
     * Busca Doutores pelo nome da Especialidade.
     * * @param specialtyName O nome da Especialidade a ser usada como filtro.
     * @return Uma lista de DTOs {@code DoctorResponse} com a Especialidade correspondente.
     * @throws RuntimeException Se nenhum Doutor for encontrado para a Especialidade.
     */
    @Override
    public List<DoctorResponse> findDoctorBySpecialtyName(String specialtyName) {
        // Assume-se que doctorRepository.findBySpecialty_Name(String specialtyName) utiliza Jpa Query Methods
        List<Doctor> doctors = doctorRepository.findBySpecialty_Name(specialtyName);

        if (doctors.isEmpty()) {
            throw new RuntimeException("No doctors found for this specialty name");
        }

        return doctors.stream()
                .map(doctorMapper::toResponse)
                .toList();
    }
}