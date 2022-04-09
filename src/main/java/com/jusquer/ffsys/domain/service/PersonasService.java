package com.jusquer.ffsys.domain.service;

import com.jusquer.ffsys.domain.repository.PersonasRepository;
import com.jusquer.ffsys.persistence.entity.Personas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonasService {
    @Autowired
    PersonasRepository personasRepository;
    public List<Personas> getAll(){
        return personasRepository.getAll();
    }
    public Personas save(Personas personas){
        return personasRepository.save(personas);
    }
}
