package com.jusquer.ffsys.persistence;

import com.jusquer.ffsys.persistence.crud.PrestamocajaCrudRepository;
import com.jusquer.ffsys.persistence.entity.Prestamocaja;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PrestamocajaRepository implements com.jusquer.ffsys.domain.repository.PrestamocajaRepository {
    @Autowired
    PrestamocajaCrudRepository prestamocajaCrudRepository;

    @Override
    public Prestamocaja save(Prestamocaja prestamocaja) {
        return prestamocajaCrudRepository.save(prestamocaja);
    }
}
