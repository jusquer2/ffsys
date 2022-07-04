package com.jusquer.ffsys.domain.repository;

import com.jusquer.ffsys.persistence.entity.Mermas;

import java.util.List;

public interface MermaRepository {
    Mermas save(Mermas mermas);
    List<Mermas> findByIdCorte(Integer idCorte);
}
