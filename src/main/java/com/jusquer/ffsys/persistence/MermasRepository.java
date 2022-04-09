package com.jusquer.ffsys.persistence;

import com.jusquer.ffsys.domain.repository.MermaRepository;
import com.jusquer.ffsys.persistence.crud.MermaCrudRepository;
import com.jusquer.ffsys.persistence.entity.Mermas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MermasRepository implements MermaRepository {
    @Autowired
    MermaCrudRepository mermaCrudRepository;
    @Override
    public Mermas save(Mermas mermas) {
        return mermaCrudRepository.save(mermas);
    }
}
