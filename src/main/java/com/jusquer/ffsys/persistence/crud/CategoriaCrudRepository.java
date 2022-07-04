package com.jusquer.ffsys.persistence.crud;

import com.jusquer.ffsys.persistence.entity.Categorias;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoriaCrudRepository extends CrudRepository<Categorias,Integer> {
    List<Categorias> findByEliminado(Boolean eliminado);
}
