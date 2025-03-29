package co.edu.uceva.ovaservice.models.services;

import co.edu.uceva.ovaservice.models.entities.Ova;

import java.util.List;

public interface IOvaService {
    Ova save(Ova ova);
    void delete(Ova ova);
    Ova findById(long id);
    Ova update(Ova ova);
    List<Ova> findAll();
}
