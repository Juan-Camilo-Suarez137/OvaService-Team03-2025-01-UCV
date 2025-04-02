package co.edu.uceva.ovaservice.domain.services;

import co.edu.uceva.ovaservice.domain.model.Ova;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IOvaService {
    Ova save(Ova ova);
    void delete(Ova ova);
    Optional<Ova> findById(long id);
    Ova update(Ova ova);
    List<Ova> findAll();
    Page<Ova> findAll(Pageable pageable);
}
