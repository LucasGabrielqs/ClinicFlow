package edu.com.br.SistemasClinicos.controller;


import edu.com.br.SistemasClinicos.dto.Specialty.SpecialtyRequest;
import edu.com.br.SistemasClinicos.dto.Specialty.SpecialtyResponse;
import edu.com.br.SistemasClinicos.service.Specialty.SpecialtyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST responsável por gerenciar as requisições HTTP para a
 * entidade Especialidade (Specialty).
 * <p>
 * Define os endpoints (URI: /api/specialties) para as operações CRUD (Criação, Leitura, Atualização, Exclusão)
 * de especialidades, delegando toda a lógica de negócio para {@code SpecialtyService}.
 * Utiliza {@code @RequiredArgsConstructor} para injeção de dependência via construtor.
 */
@RestController
@RequestMapping("api/specialties")
@RequiredArgsConstructor
public class SpecialtyController {

    private final SpecialtyService specialtyService;

    /**
     * Endpoint para criar uma nova Especialidade.
     * <p>
     * Mapeado para {@code POST /api/specialties}.
     * O corpo da requisição é validado ({@code @Valid}) usando as regras definidas em {@code SpecialtyRequest}.
     * * @param specialtyRequest O DTO com os dados da Especialidade a ser criada.
     * @return Uma resposta HTTP 201 (Created) contendo o DTO da Especialidade criada.
     */
    @PostMapping
    public ResponseEntity<SpecialtyResponse> createSpecialty(@Valid @RequestBody SpecialtyRequest specialtyRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(specialtyService.createSpecialty(specialtyRequest));
    }

    /**
     * Endpoint para atualizar uma Especialidade existente.
     * <p>
     * Mapeado para {@code PUT /api/specialties/{id}}.
     * O DTO de requisição é validado ({@code @Valid}).
     * * @param id O ID da Especialidade a ser atualizada, extraído da URI.
     * @param specialtyRequest O DTO com os novos dados da Especialidade.
     * @return Uma resposta HTTP 200 (OK) contendo o DTO da Especialidade atualizada.
     */
    @PutMapping("/{id}")
    public ResponseEntity<SpecialtyResponse> updateSpecialty(
            @PathVariable Long id,
            @Valid @RequestBody SpecialtyRequest specialtyRequest){
        return ResponseEntity.status(HttpStatus.OK).body(specialtyService.updateSpecialty(id, specialtyRequest));
    }

    /**
     * Endpoint para buscar uma Especialidade pelo seu ID.
     * <p>
     * Mapeado para {@code GET /api/specialties/{id}}.
     * * @param id O ID da Especialidade a ser buscada.
     * @return Uma resposta HTTP 200 (OK) contendo o DTO da Especialidade.
     */
    @GetMapping("/{id}")
    public ResponseEntity<SpecialtyResponse> findSpecialtyById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(specialtyService.findById(id));
    }

    /**
     * Endpoint para buscar todas as Especialidades.
     * <p>
     * Mapeado para {@code GET /api/specialties}.
     * * @return Uma resposta HTTP 200 (OK) contendo uma lista de DTOs {@code SpecialtyResponse}.
     */
    @GetMapping
    public ResponseEntity<List<SpecialtyResponse>> getAllSpecialty(){
        return ResponseEntity.status(HttpStatus.OK).body(specialtyService.findAll());
    }

    /**
     * Endpoint para excluir uma Especialidade pelo seu ID.
     * <p>
     * Mapeado para {@code DELETE /api/specialties/{id}}.
     * * @param id O ID da Especialidade a ser excluída.
     * @return Uma resposta HTTP 204 (No Content), indicando sucesso na exclusão.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<SpecialtyResponse> deleteSpecialty(@PathVariable Long id){
        specialtyService.deleteSpecialty(id);
        return ResponseEntity.noContent().build();
    }

}