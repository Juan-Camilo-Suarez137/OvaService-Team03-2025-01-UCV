package co.edu.uceva.ovaservice.models.repositories;

import co.edu.uceva.ovaservice.models.entities.Ova;
import org.springframework.data.repository.CrudRepository;

public interface IOvaRepository extends CrudRepository<Ova, Long> {
}
