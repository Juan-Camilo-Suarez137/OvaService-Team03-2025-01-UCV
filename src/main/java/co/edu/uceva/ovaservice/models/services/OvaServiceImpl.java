package co.edu.uceva.ovaservice.models.services;

import co.edu.uceva.ovaservice.models.entities.Ova;
import co.edu.uceva.ovaservice.models.repositories.IOvaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OvaServiceImpl implements IOvaService {
    IOvaRepository ovaRepository;

    public OvaServiceImpl(IOvaRepository ovaRepository) { this.ovaRepository = ovaRepository; }

    @Override
    @Transactional
    public Ova save (Ova ova) { return this.ovaRepository.save(ova); }

    @Override
    @Transactional
    public void delete (Ova ova){ this.ovaRepository.delete(ova); }

    @Override
    @Transactional(readOnly = true)
    public Ova findById(long id) {return ovaRepository.findById(id).orElse(null);}

    @Override
    @Transactional
    public Ova update(Ova ova) {return ovaRepository.save(ova);}

    @Override
    @Transactional(readOnly = true)
    public List<Ova> findAll() {return (List<Ova>) ovaRepository.findAll();}

    @Override
    @Transactional(readOnly = true)
    public Page<Ova> findAll(Pageable pageable){
        return ovaRepository.findAll(pageable);
    }

}
