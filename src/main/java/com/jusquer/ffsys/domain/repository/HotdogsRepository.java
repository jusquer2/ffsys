package com.jusquer.ffsys.domain.repository;

import com.jusquer.ffsys.persistence.entity.Hotdogs;

import java.util.List;

public interface HotdogsRepository {
    List<Hotdogs> getAll();
    Hotdogs save(Hotdogs hotdog);
    List<Hotdogs> findAllByOrderByPrioridadAsc();
    List<Hotdogs> findByEliminadoOrderByPrioridadAsc(boolean eliminado);


}
