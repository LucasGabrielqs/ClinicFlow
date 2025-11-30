package edu.com.br.SistemasClinicos.repository;

import edu.com.br.SistemasClinicos.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface de Repositório para a entidade {@link Patient}.
 * <p>
 * Estende {@link JpaRepository} para fornecer operações CRUD básicas (Create, Read, Update, Delete)
 * e funcionalidades de paginação e ordenação, além de definir métodos de consulta
 * específicos baseados em convenções de nomes do Spring Data JPA.
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {

    /**
     * Busca e retorna uma lista de pacientes cujo nome corresponde ao critério fornecido.
     * <p>
     * O Spring Data JPA traduz automaticamente este método para uma consulta SQL
     * (geralmente usando LIKE) no campo 'name' da entidade Patient.
     * * @param name O nome ou parte do nome do paciente a ser buscado.
     * @return Uma lista de objetos {@link Patient} que correspondem ao nome.
     */
    List<Patient> findByName(String name);

    /**
     * Verifica se existe algum paciente cadastrado no banco de dados com o CPF fornecido.
     * <p>
     * Este método é otimizado para retornar um booleano de forma eficiente,
     * sendo crucial para validar a unicidade de CPFs antes de criar novos registros.
     * * @param cpf O CPF a ser verificado.
     * @return {@code true} se um paciente com o CPF existir, {@code false} caso contrário.
     */
    boolean existsByCpf(String cpf);

}