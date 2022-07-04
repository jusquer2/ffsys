package com.jusquer.ffsys.persistence;

import com.jusquer.ffsys.persistence.crud.PersonasCrudRepository;
import com.jusquer.ffsys.persistence.entity.Personas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonasRepository implements com.jusquer.ffsys.domain.repository.PersonasRepository {
    @Autowired
    PersonasCrudRepository personasCrudRepository;


    @Override
    public List<Personas> getAll() {
        return (List<Personas>) personasCrudRepository.findAll();
    }

    @Override
    public List<Personas> findByEliminado(Boolean eliminado) {
        return personasCrudRepository.findByEliminado(eliminado);
    }

    @Override
    public Personas save(Personas personas) {
        return personasCrudRepository.save(personas);
    }

    @Override
    public Personas findById(Integer id) {
        return personasCrudRepository.findById(id).get();
    }
}
