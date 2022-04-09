package com.jusquer.ffsys.persistence;

import com.jusquer.ffsys.domain.repository.HotdogsRepository;
import com.jusquer.ffsys.persistence.crud.HotdogsCrudRepository;
import com.jusquer.ffsys.persistence.entity.Hotdogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class HotDogsRepository implements HotdogsRepository {
    @Autowired
    private HotdogsCrudRepository hotdogsCrudRepository;

    @Override
    public List<Hotdogs> getAll() {
        return (List<Hotdogs>) hotdogsCrudRepository.findAll();
    }

    @Override
    public Hotdogs save(Hotdogs hotdog) {
        return hotdogsCrudRepository.save(hotdog);
    }

    @Override
    public List<Hotdogs> findAllByOrderByPrioridadAsc() {
        return hotdogsCrudRepository.findAllByOrderByPrioridadAsc();
    }

    @Override
    public List<Hotdogs> findByEliminadoOrderByPrioridadAsc(boolean eliminado) {
        return hotdogsCrudRepository.findByEliminadoOrderByPrioridadAsc(eliminado);
    }
}
