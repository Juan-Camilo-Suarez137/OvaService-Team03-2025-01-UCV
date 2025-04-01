package co.edu.uceva.ovaservice.domain.repository;

import co.edu.uceva.ovaservice.domain.model.Ova;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface que hereda de JpaRepository para realizar las
 * operaciones de CRUD paginacion y ordenamiento sobre la entidad Producto
 */
public interface IOvaRepository extends JpaRepository<Ova, Long> {
}
