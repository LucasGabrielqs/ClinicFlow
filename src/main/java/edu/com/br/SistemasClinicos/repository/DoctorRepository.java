package edu.com.br.SistemasClinicos.repository;

import edu.com.br.SistemasClinicos.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface de Repositório para a entidade {@link Doctor}.
 * <p>
 * Estende {@link JpaRepository} para fornecer operações CRUD básicas (Create, Read, Update, Delete)
 * e funcionalidades de paginação e ordenação, além de definir métodos de consulta
 * específicos baseados em convenções de nomes do Spring Data JPA.
 */
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    /**
     * Busca e retorna uma lista de doutores cujo nome corresponde ao critério fornecido.
     * <p>
     * O Spring Data JPA traduz este método para uma consulta SQL no campo 'name' da entidade Doctor.
     * Assume-se que a busca pode ser por correspondência parcial (LIKE) dependendo da configuração.
     * * @param name O nome ou parte do nome do doutor a ser buscado.
     * @return Uma lista de objetos {@link Doctor} que correspondem ao nome.
     */
    List<Doctor> findByName(String name);

    /**
     * Busca e retorna uma lista de doutores filtrando pelo nome da sua Especialidade.
     * <p>
     * Utiliza a convenção de Query Methods do Spring Data JPA, navegando através da relação
     * {@code specialty} para buscar pelo seu atributo {@code name}.
     * * @param specialtyName O nome da Especialidade (Specialty) a ser usado como filtro.
     * @return Uma lista de objetos {@link Doctor} que possuem a Especialidade correspondente.
     */
    List<Doctor> findBySpecialty_Name(String specialtyName);

    /**
     * Verifica se existe algum doutor cadastrado no banco de dados com o CPF fornecido.
     * <p>
     * Este método é otimizado para retornar um booleano de forma eficiente, sendo usado
     * primariamente para validar a unicidade do CPF na criação de novos registros.
     * * @param cpf O CPF a ser verificado.
     * @return {@code true} se um doutor com o CPF existir, {@code false} caso contrário.
     */
    boolean existsByCpf(String cpf);
}