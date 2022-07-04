package com.jusquer.ffsys.persistence.crud;

import com.jusquer.ffsys.persistence.entity.Personas;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonasCrudRepository extends CrudRepository<Personas,Integer> {
    List<Personas> findByEliminado(Boolean eliminado);
}
