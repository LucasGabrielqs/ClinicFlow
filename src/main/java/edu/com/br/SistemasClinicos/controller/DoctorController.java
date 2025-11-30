package edu.com.br.SistemasClinicos.controller;

import edu.com.br.SistemasClinicos.dto.Doctor.DoctorRequest;
import edu.com.br.SistemasClinicos.dto.Doctor.DoctorResponse;
import edu.com.br.SistemasClinicos.service.Doctor.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST responsável por gerenciar as requisições HTTP para a
 * entidade Doutor (Doctor).
 * <p>
 * Define os endpoints (URI: /api/doctors) para as operações CRUD e consultas
 * específicas, delegando a lógica de negócio para {@code DoctorService}.
 * Utiliza {@code @RequiredArgsConstructor} para injeção de dependência via construtor.
 */
@RestController
@RequestMapping("api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    /**
     * Endpoint para criar um novo Doutor.
     * <p>
     * Mapeado para {@code POST /api/doctors}.
     * O corpo da requisição é validado ({@code @Valid}) usando as regras definidas em {@code DoctorRequest}.
     * * @param doctorRequest O DTO com os dados do Doutor a ser criado.
     * @return Uma resposta HTTP 201 (Created) contendo o DTO do Doutor criado.
     */
    @PostMapping
    public ResponseEntity<DoctorResponse> createDoctor(@Valid @RequestBody DoctorRequest doctorRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.createDoctor(doctorRequest));
    }

    /**
     * Endpoint para atualizar um Doutor existente.
     * <p>
     * Mapeado para {@code PUT /api/doctors/{id}}.
     * O DTO de requisição é validado ({@code @Valid}).
     * * @param id O ID do Doutor a ser atualizado, extraído da URI.
     * @param request O DTO com os novos dados do Doutor.
     * @return Uma resposta HTTP 200 (OK) contendo o DTO do Doutor atualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody DoctorRequest request) {
        return ResponseEntity.ok(doctorService.updateDoctor(id, request));
    }

    /**
     * Endpoint para buscar um Doutor pelo seu ID.
     * <p>
     * Mapeado para {@code GET /api/doctors/{id}}.
     * * @param id O ID do Doutor a ser buscado.
     * @return Uma resposta HTTP 200 (OK) contendo o DTO do Doutor.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.findDoctorById(id));
    }

    /**
     * Endpoint para buscar todos os Doutores.
     * <p>
     * Mapeado para {@code GET /api/doctors}.
     * * @return Uma resposta HTTP 200 (OK) contendo uma lista de DTOs {@code DoctorResponse}.
     */
    @GetMapping
    public ResponseEntity<List<DoctorResponse>> findAll() {
        return ResponseEntity.ok(doctorService.findall());
    }

    /**
     * Endpoint para excluir um Doutor pelo seu ID.
     * <p>
     * Mapeado para {@code DELETE /api/doctors/{id}}.
     * * @param id O ID do Doutor a ser excluído.
     * @return Uma resposta HTTP 204 (No Content), indicando sucesso na exclusão.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint para buscar Doutores pelo nome.
     * <p>
     * Mapeado para {@code GET /api/doctors/search/{name}}.
     * * @param name O nome (ou parte do nome) do Doutor a ser buscado.
     * @return Uma resposta HTTP 200 (OK) contendo uma lista de DTOs {@code DoctorResponse} correspondentes.
     */
    @GetMapping("/search/{name}")
    public ResponseEntity<List<DoctorResponse>> findByName(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.findDoctorByName(name));
    }

    /**
     * Endpoint para buscar Doutores pelo nome da Especialidade.
     * <p>
     * Mapeado para {@code GET /api/doctors/search/specialty/{name}}.
     * * @param name O nome da Especialidade a ser usada como filtro.
     * @return Uma resposta HTTP 200 (OK) contendo uma lista de DTOs {@code DoctorResponse} da Especialidade.
     */
    @GetMapping("/search/specialty/{name}")
    public ResponseEntity<List<DoctorResponse>> findBySpecialtyName(@PathVariable String name) {
        return ResponseEntity.ok(doctorService.findDoctorBySpecialtyName(name));
    }
}