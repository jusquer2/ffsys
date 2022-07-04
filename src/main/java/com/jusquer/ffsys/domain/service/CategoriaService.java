package com.jusquer.ffsys.domain.service;

import com.jusquer.ffsys.domain.repository.CategoriaRepository;
import com.jusquer.ffsys.persistence.entity.Categorias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    CategoriaRepository categoriaRepository;
    public Categorias save(Categorias categoria){
        return categoriaRepository.save(categoria);
    }
    public List<Categorias> findByEliminado(Boolean eliminado){
        return categoriaRepository.findByEliminado(eliminado);
    }
}
