package edu.com.br.SistemasClinicos.service.Specialty;


import edu.com.br.SistemasClinicos.dto.Specialty.SpecialtyRequest;
import edu.com.br.SistemasClinicos.dto.Specialty.SpecialtyResponse;
import edu.com.br.SistemasClinicos.mapper.SpecialtyMapper;
import edu.com.br.SistemasClinicos.model.Specialty;
import edu.com.br.SistemasClinicos.repository.SpecialtyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementação da interface {@code SpecialtyService}.
 * <p>
 * Contém a lógica de negócio básica para o gerenciamento de Especialidades,
 * orquestrando o acesso a dados via {@code SpecialtyRepository} e a conversão de objetos
 * via {@code SpecialtyMapper}. Utiliza {@code @RequiredArgsConstructor} para injeção de dependência.
 */
@Service
@RequiredArgsConstructor
public class SpecialtyServiceImp implements SpecialtyService{

    private final SpecialtyRepository specialtyRepository;
    private final SpecialtyMapper specialtyMapper;

    /**
     * Cria uma nova Especialidade no sistema.
     * <p>
     * 1. Mapeia o DTO de requisição para a Entidade Specialty.
     * 2. Persiste a Entidade no repositório.
     * 3. Mapeia a Entidade salva para um DTO de resposta.
     * * @param request DTO contendo os dados da Especialidade a ser criada.
     * @return DTO de resposta da Especialidade criada.
     */
    @Override
    public SpecialtyResponse createSpecialty(SpecialtyRequest request) {
        Specialty specialty = specialtyMapper.toEntity(request);

        Specialty savedSpecialty = specialtyRepository.save(specialty);

        return specialtyMapper.toResponse(savedSpecialty);
    }

    /**
     * Atualiza os dados de uma Especialidade existente.
     * <p>
     * 1. Busca a Especialidade existente pelo ID.
     * 2. Mapeia os dados da requisição para uma nova Entidade.
     * 3. Mantém o ID original na nova Entidade mapeada.
     * 4. Persiste a Entidade atualizada.
     * * @param id O ID da Especialidade a ser atualizada.
     * @param request DTO contendo os novos dados da Especialidade.
     * @return DTO de resposta da Especialidade atualizada.
     * @throws RuntimeException Se a Especialidade não for encontrada.
     */
    @Override
    public SpecialtyResponse updateSpecialty(Long id, SpecialtyRequest request) {
        Specialty specialty = specialtyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Specialty not found"));

        Specialty updatedSpecialty = specialtyMapper.toEntity(request);
        updatedSpecialty.setId(specialty.getId()); // Essencial para o JPA/Hibernate entender que é um update

        updatedSpecialty =  specialtyRepository.save(updatedSpecialty);
        return specialtyMapper.toResponse(updatedSpecialty);
    }

    /**
     * Exclui uma Especialidade do sistema pelo seu ID.
     * * @param id O ID da Especialidade a ser excluída.
     * @throws org.springframework.dao.EmptyResultDataAccessException Se o ID não for encontrado (exceção padrão do Spring Data Jpa).
     */
    @Override
    public void deleteSpecialty(Long id) {
        specialtyRepository.deleteById(id);
    }

    /**
     * Retorna uma lista de todas as Especialidades cadastradas.
     * * @return Uma lista de DTOs {@code SpecialtyResponse}.
     */
    @Override
    public List<SpecialtyResponse> findAll() {
        return specialtyMapper.toResponseList(specialtyRepository.findAll());
    }

    /**
     * Busca uma Especialidade pelo seu ID.
     * * @param id O ID da Especialidade a ser buscada.
     * @return DTO de resposta da Especialidade encontrada.
     * @throws RuntimeException Se a Especialidade não for encontrada.
     */
    @Override
    public SpecialtyResponse findById(Long id) {
        Specialty specialty = specialtyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Specialty not found"));
        return specialtyMapper.toResponse(specialty);
    }
}