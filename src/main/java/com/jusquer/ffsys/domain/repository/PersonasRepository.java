package com.jusquer.ffsys.domain.repository;

import com.jusquer.ffsys.persistence.entity.Personas;

import java.util.List;

public interface PersonasRepository {
    List<Personas> getAll();
    List<Personas> findByEliminado(Boolean eliminado);
    Personas save(Personas personas);
    Personas findById(Integer id);
}
