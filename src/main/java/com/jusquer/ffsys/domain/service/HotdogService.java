package com.jusquer.ffsys.domain.service;

import com.jusquer.ffsys.domain.repository.HotdogsRepository;
import com.jusquer.ffsys.persistence.entity.Hotdogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotdogService {
    @Autowired
    private HotdogsRepository hotdogsRepository;
    public List<Hotdogs> getAll(){
        return hotdogsRepository.findByEliminadoOrderByPrioridadAsc(false);
    }
    public List<Hotdogs> getAllOrder(){
        return hotdogsRepository.findAllByOrderByPrioridadAsc();
    }
    public Hotdogs insertHotDogs(Hotdogs hotdogs){
        return hotdogsRepository.save(hotdogs);
    }

}
