package co.edu.uceva.ovaservice.models.services;

import co.edu.uceva.ovaservice.models.entities.Ova;
import co.edu.uceva.ovaservice.models.repositories.IOvaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OvaServiceImpl implements IOvaService {
    IOvaRepository ovaRepository;

    public OvaServiceImpl(IOvaRepository ovaRepository) { this.ovaRepository = ovaRepository; }

    @Override
    public Ova save (Ova ova) { return this.ovaRepository.save(ova); }

    @Override
    public void delete (Ova ova){ this.ovaRepository.delete(ova); }

    @Override
    public Ova findById(long id) {return ovaRepository.findById(id).orElse(null);}

    @Override
    public Ova update(Ova ova) {return ovaRepository.save(ova);}

    @Override
    public List<Ova> findAll() {return (List<Ova>) ovaRepository.findAll();}
}
