package com.jusquer.ffsys.persistence;

import com.jusquer.ffsys.persistence.crud.CategoriaCrudRepository;
import com.jusquer.ffsys.persistence.entity.Categorias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoriaRepository implements com.jusquer.ffsys.domain.repository.CategoriaRepository {
    @Autowired
    CategoriaCrudRepository categoriaCrudRepository;


    @Override
    public Categorias save(Categorias categoria) {
        return categoriaCrudRepository.save(categoria);
    }

    @Override
    public List<Categorias> findByEliminado(Boolean eliminado) {
        return categoriaCrudRepository.findByEliminado(eliminado);
    }
}
