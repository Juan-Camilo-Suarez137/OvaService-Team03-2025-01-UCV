package co.edu.uceva.ovaservice.domain.services;

import co.edu.uceva.ovaservice.domain.model.Ova;
import co.edu.uceva.ovaservice.domain.repository.IOvaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    public Optional<Ova> findById(long id) {
        return ovaRepository.findById(id);
    }

    @Override
    @Transactional
    public Ova update(Ova ova) {return ovaRepository.save(ova);}

    @Override
    @Transactional(readOnly = true)
    public List<Ova> findAll() {
        return ovaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Ova> findAll(Pageable pageable){

        return ovaRepository.findAll(pageable);
    }

}
