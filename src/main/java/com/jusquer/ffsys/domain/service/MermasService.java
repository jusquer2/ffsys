package com.jusquer.ffsys.domain.service;

import com.jusquer.ffsys.domain.repository.MermaRepository;
import com.jusquer.ffsys.persistence.entity.Mermas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MermasService {
    @Autowired
    MermaRepository mermaRepository;
    public Mermas save(Mermas mermas){
        return mermaRepository.save(mermas);
    }
}
