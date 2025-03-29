package co.edu.uceva.ovaservice.models.repositories;

import co.edu.uceva.ovaservice.models.entities.Ova;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Interface que hereda de JpaRepository para realizar las
 * operaciones de CRUD paginacion y ordenamiento sobre la entidad Producto
 */
public interface IOvaRepository extends JpaRepository<Ova, Long> {
}
