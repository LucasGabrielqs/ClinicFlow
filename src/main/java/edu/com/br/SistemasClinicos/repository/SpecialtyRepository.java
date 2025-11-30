package edu.com.br.SistemasClinicos.repository;

import edu.com.br.SistemasClinicos.model.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface de Repositório para a entidade {@link Specialty}.
 * <p>
 * Estende {@link JpaRepository} para herdar automaticamente métodos de
 * acesso a dados, como CRUD (Create, Read, Update, Delete), paginação e
 * ordenação para a entidade Specialty.
 * <p>
 * Não possui métodos de consulta específicos definidos, utilizando apenas
 * as funcionalidades padrão fornecidas pelo Spring Data JPA.
 */
@Repository
public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
}