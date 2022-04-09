package com.jusquer.ffsys.persistence.crud;

import com.jusquer.ffsys.persistence.entity.Ventas;
import org.springframework.data.repository.CrudRepository;

public interface VentaCrudRepository extends CrudRepository<Ventas,Integer> {
}
