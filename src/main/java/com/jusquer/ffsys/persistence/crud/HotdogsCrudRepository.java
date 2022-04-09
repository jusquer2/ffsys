package com.jusquer.ffsys.persistence.crud;

import com.jusquer.ffsys.persistence.entity.Hotdogs;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface HotdogsCrudRepository extends CrudRepository<Hotdogs,Integer> {
    Optional<List<Hotdogs>> findByPrecio(Double precio);
    List<Hotdogs> findAllByOrderByPrioridadAsc();
    List<Hotdogs> findByEliminadoOrderByPrioridadAsc(boolean eliminado);
}
