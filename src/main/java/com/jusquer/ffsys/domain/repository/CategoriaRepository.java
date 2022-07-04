package com.jusquer.ffsys.domain.repository;

import com.jusquer.ffsys.persistence.entity.Categorias;

import java.util.List;

public interface CategoriaRepository {
    Categorias save(Categorias categoria);
    List<Categorias> findByEliminado(Boolean eliminado);
}
