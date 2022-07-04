package com.jusquer.ffsys.persistence.crud;

import com.jusquer.ffsys.persistence.entity.Mermas;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MermaCrudRepository extends CrudRepository<Mermas,Integer> {
    List<Mermas> findByIdCorte(Integer idCorte);
}
