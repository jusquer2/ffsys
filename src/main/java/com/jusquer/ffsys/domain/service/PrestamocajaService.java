package com.jusquer.ffsys.domain.service;

import com.jusquer.ffsys.domain.repository.PrestamocajaRepository;
import com.jusquer.ffsys.persistence.entity.Prestamocaja;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrestamocajaService {
    @Autowired
    PrestamocajaRepository prestamocajaRepository;
    public Prestamocaja save(Prestamocaja prestamocaja){
        return prestamocajaRepository.save(prestamocaja);
    }
}
