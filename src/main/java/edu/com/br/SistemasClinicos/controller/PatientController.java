package edu.com.br.SistemasClinicos.controller;


import edu.com.br.SistemasClinicos.dto.Patient.PatientRequest;
import edu.com.br.SistemasClinicos.dto.Patient.PatientResponse;
import edu.com.br.SistemasClinicos.service.Patient.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST responsável por gerenciar as requisições HTTP para a
 * entidade Paciente (Patient).
 * <p>
 * Define os endpoints (URI: /api/patients) para as operações CRUD e consultas
 * específicas, delegando a lógica de negócio para {@code PatientService}.
 * Utiliza {@code @RequiredArgsConstructor} para injeção de dependência via construtor.
 */
@RestController
@RequestMapping("api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    /**
     * Endpoint para criar um novo Paciente.
     * <p>
     * Mapeado para {@code POST /api/patients}.
     * O corpo da requisição é validado ({@code @Valid}) usando as regras definidas em {@code PatientRequest}.
     * * @param patientRequest O DTO com os dados do Paciente a ser criado.
     * @return Uma resposta HTTP 201 (Created) contendo o DTO do Paciente criado.
     */
    @PostMapping
    public ResponseEntity<PatientResponse> createPatient(@Valid @RequestBody PatientRequest patientRequest){
        return  ResponseEntity.status(HttpStatus.CREATED).body(patientService.createPatient(patientRequest));
    }

    /**
     * Endpoint para atualizar um Paciente existente.
     * <p>
     * Mapeado para {@code PUT /api/patients/{id}}.
     * O DTO de requisição é validado ({@code @Valid}).
     * * @param id O ID do Paciente a ser atualizado, extraído da URI.
     * @param request O DTO com os novos dados do Paciente.
     * @return Uma resposta HTTP 200 (OK) contendo o DTO do Paciente atualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PatientResponse> updatePatient(
            @PathVariable Long id,
            @Valid @RequestBody PatientRequest request){
        return  ResponseEntity.status(HttpStatus.OK).body(patientService.updatePatient(id,request));
    }

    /**
     * Endpoint para buscar um Paciente pelo seu ID.
     * <p>
     * Mapeado para {@code GET /api/patients/{id}}.
     * * @param id O ID do Paciente a ser buscado.
     * @return Uma resposta HTTP 200 (OK) contendo o DTO do Paciente.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PatientResponse> findPatientById(@PathVariable Long id){
        return  ResponseEntity.status(HttpStatus.OK).body(patientService.findPatientById(id));
    }

    /**
     * Endpoint para buscar todos os Pacientes.
     * <p>
     * Mapeado para {@code GET /api/patients}.
     * * @return Uma resposta HTTP 200 (OK) contendo uma lista de DTOs {@code PatientResponse}.
     */
    @GetMapping
    public ResponseEntity<List<PatientResponse>> findAllPatients(){
        return  ResponseEntity.status(HttpStatus.OK).body(patientService.findAllPatients());
    }

    /**
     * Endpoint para excluir um Paciente pelo seu ID.
     * <p>
     * Mapeado para {@code DELETE /api/patients/{id}}.
     * * @param id O ID do Paciente a ser excluído.
     * @return Uma resposta HTTP 204 (No Content), indicando sucesso na exclusão.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<PatientResponse> deletePatient(@PathVariable Long id){
        patientService.deletePatient(id);
        return  ResponseEntity.noContent().build();
    }

    /**
     * Endpoint para buscar Pacientes pelo nome.
     * <p>
     * Mapeado para {@code GET /api/patients/search/{name}}.
     * * @param name O nome (ou parte do nome) do Paciente a ser buscado.
     * @return Uma resposta HTTP 200 (OK) contendo uma lista de DTOs {@code PatientResponse} correspondentes.
     */
    @GetMapping("/search/{name}")
    public ResponseEntity<List<PatientResponse>> findPatientByName(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.OK).body(patientService.findPatientByName(name));
    }
}