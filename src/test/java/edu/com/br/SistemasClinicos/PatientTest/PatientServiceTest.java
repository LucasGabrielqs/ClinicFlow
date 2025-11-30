package edu.com.br.SistemasClinicos.PatientTest;

import edu.com.br.SistemasClinicos.dto.Patient.PatientRequest;
import edu.com.br.SistemasClinicos.dto.Patient.PatientResponse;
import edu.com.br.SistemasClinicos.mapper.PatientMapper;
import edu.com.br.SistemasClinicos.model.Patient;
import edu.com.br.SistemasClinicos.repository.PatientRepository;
import edu.com.br.SistemasClinicos.service.Patient.PatientServiceImp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Classe de teste unitário para validar a lógica de negócio na camada de serviço
 * {@code PatientServiceImp}.
 * <p>
 * Utiliza o framework Mockito para isolar a classe sob teste, mockando suas dependências
 * ({@code PatientRepository} e {@code PatientMapper}).
 */
class PatientServiceTest {

    private PatientRepository repository;
    private PatientMapper mapper;

    private PatientServiceImp service;

    /**
     * Configuração inicial (setup) para cada teste.
     * <p>
     * Inicializa os mocks e a instância do serviço com as dependências mockadas.
     */
    @BeforeEach
    void setup() {
        repository = mock(PatientRepository.class);
        mapper = mock(PatientMapper.class);
        service = new PatientServiceImp(repository, mapper);
    }

    /**
     * Testa o cenário de sucesso na criação de um paciente (Método POST).
     * <p>
     * Verifica se o serviço executa o fluxo correto: mapear DTO para entidade, salvar
     * no repositório e mapear a entidade salva para DTO de resposta.
     */
    @Test
    void shouldCreatePatientSuccessfully() {

        PatientRequest request = new PatientRequest();
        request.setName("Lucas");

        Patient entity = new Patient();
        entity.setName("Lucas");

        Patient saved = new Patient();
        saved.setId(1L);
        saved.setName("Lucas");

        PatientResponse response = new PatientResponse();
        response.setId(1L);
        response.setName("Lucas");

        when(mapper.toEntity(request)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(saved);
        when(mapper.toResponse(saved)).thenReturn(response);

        PatientResponse result = service.createPatient(request);

        assertEquals(1L, result.getId());
        assertEquals("Lucas", result.getName());

        verify(mapper).toEntity(request);
        verify(repository).save(entity);
        verify(mapper).toResponse(saved);
    }

    /**
     * Testa o cenário de sucesso na atualização de um paciente existente (Método PUT).
     * <p>
     * Verifica se o serviço: busca a entidade existente, mapeia a request para uma
     * nova entidade (mantendo o ID para atualização) e persiste o resultado.
     */
    @Test
    void shouldUpdatePatientSuccessfully() {

        Long id = 1L;

        PatientRequest request = new PatientRequest();
        request.setName("Novo Nome");

        Patient existing = new Patient();
        existing.setId(1L);
        existing.setName("Lucas");

        Patient updatedEntity = new Patient();
        updatedEntity.setName("Novo Nome");
        Patient savedEntity = new Patient();
        savedEntity.setId(1L);
        savedEntity.setName("Novo Nome");

        PatientResponse response = new PatientResponse();
        response.setId(1L);
        response.setName("Novo Nome");

        when(repository.findById(id)).thenReturn(Optional.of(existing));
        when(mapper.toEntity(request)).thenReturn(updatedEntity);
        when(repository.save(updatedEntity)).thenReturn(savedEntity);
        when(mapper.toResponse(savedEntity)).thenReturn(response);

        PatientResponse result = service.updatePatient(id, request);

        assertEquals(1L, result.getId());
        assertEquals("Novo Nome", result.getName());

        verify(repository).findById(id);
        verify(mapper).toEntity(request);
        verify(repository).save(updatedEntity);
        verify(mapper).toResponse(savedEntity);
    }

    /**
     * Testa se o serviço lança a exceção esperada ao tentar atualizar um paciente
     * com um ID que não existe no repositório.
     */
    @Test
    void shouldThrowExceptionWhenUpdatingNonExistingPatient() {
        Long id = 999L;
        PatientRequest request = new PatientRequest();

        when(repository.findById(id)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> service.updatePatient(id, request));

        assertEquals("Patient not found", ex.getMessage());

        verify(repository).findById(id);
        verify(repository, never()).save(any());
    }

    /**
     * Testa o cenário de exclusão de um paciente pelo ID.
     * <p>
     * Verifica se o método {@code deleteById} do repositório é chamado exatamente uma vez.
     */
    @Test
    void shouldDeletePatientById() {
        Long id = 1L;

        doNothing().when(repository).deleteById(id);

        service.deletePatient(id);

        verify(repository).deleteById(id);
    }
}