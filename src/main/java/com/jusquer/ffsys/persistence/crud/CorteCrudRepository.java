package com.jusquer.ffsys.persistence.crud;

import com.jusquer.ffsys.persistence.entity.Corte;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface CorteCrudRepository extends CrudRepository<Corte,Integer> {
    @Query(value = "SELECT case when sum(m.cantidad) is null then 0 else sum(m.cantidad) end as mermas, case when sum(v.cantidad) is null then 0 else sum(v.cantidad) end as panesVendidos, c.panes, (c.panes-(case when sum(v.cantidad) is null then 0 else sum(v.cantidad) end)-(case when sum(m.cantidad) is null then 0 else sum(m.cantidad) end)) as restantes FROM ventas v INNER JOIN public.hotdogs h on h.idhotdog=v.idhotdog INNER JOIN public.venta_total vt ON vt.idventa_total=v.idventa_total inner join corte c on c.idcorte=vt.idcorte left join mermas m on m.idcorte=c.idcorte where vt.idcorte=:idcorte group by panes", nativeQuery = true)
    List<Map<String, Serializable>> getPanesByIdCorte(Integer idcorte);

    @Query(value = " SELECT panes as restantes, 0 as mermas, 0 as panesVendidos, panes from corte where idcorte=:idcorte", nativeQuery = true)
    List<Map<String, Serializable>> getPanesByIdCorteEmpty(Integer idcorte);

    @Query(value = "SELECT idcorte, dinerocaja, dinerocajacorte, comentario, horaentrada, horasalida, nombreCajero FROM public.corte where horaentrada = :fecha;", nativeQuery = true)
    List<Map<String, Serializable>> getCortesFecha(Timestamp fecha);

    @Query(value = "select case when hd.hotdog is null then pd.producto else hd.hotdog end as descripcion , SUM(v.cantidad) as cantidad, case when (hd.precio*SUM(v.cantidad)) is null then (pd.precio*SUM(v.cantidad)) else (hd.precio*SUM(v.cantidad)) END as total, case when hd.hotdog is null then pd.precio else hd.precio end as preciounitario from public.ventas v inner join public.venta_total vt on  vt.idventa_total=v.idventa_total left join public.hotdogs hd on hd.idhotdog=v.idhotdog left join public.productos pd on pd.idproducto=v.idproducto where vt.idcorte=? group by v.idhotdog,v.idproducto,hd.hotdog,pd.producto,hd.precio,pd.producto,pd.precio", nativeQuery = true)
    List<Map<String, Serializable>> getTotalVentaCorte(Integer idcorte);

    @Query(value = "select * from corte  where :horaEntrada between horaentrada and horasalida", nativeQuery = true)
    List<Corte> findByHoraEntrada(Timestamp horaEntrada);
}
