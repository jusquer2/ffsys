package com.jusquer.ffsys.persistence.crud;

import com.jusquer.ffsys.domain.dto.ProductosTotales;
import com.jusquer.ffsys.persistence.entity.VentaTotal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface VentasTotalCrudRepository extends CrudRepository<VentaTotal,Integer> {
    List<VentaTotal> findByIdCorte(Integer idcorte);

    @Query(value = "select case when hd.hotdog is null then pd.producto else hd.hotdog end as producto , SUM(v.cantidad) as cantidad, case when (hd.precio*SUM(v.cantidad)) is null then (pd.precio*SUM(v.cantidad)) else (hd.precio*SUM(v.cantidad)) END as total, case when hd.hotdog is null then pd.precio else hd.precio end as preciounitario from venta_total vt inner join ventas v on  vt.idventa_total=v.idventa_total left join hotdogs hd on hd.idhotdog=v.idhotdog left join productos pd on pd.idproducto=v.idproducto  where vt.idcorte=:idcorte group by v.idhotdog,v.idproducto,hd.hotdog,pd.producto,hd.precio,pd.producto,pd.precio", nativeQuery = true)
    List<Map<String, Serializable>> findTotalByIdCorte(Integer idcorte);

    @Query(value = "select personas.nombrepersona, SUM(merma.cantidad) as cantidad,hd.hotdog from public.mermas merma inner join public.personas personas on personas.idpersona=merma.idpersona left join public.hotdogs hd on hd.idhotdog=merma.idhotdog where idcorte=:idcorte group by personas.nombrepersona,hd.hotdog", nativeQuery = true)
    List<Map<String, Serializable>> findMermasByIdCorte(Integer idcorte);

    @Query(value = "select personas.nombrepersona, sum(cantidaddinero) as total from prestamocaja inner join public.personas personas on personas.idpersona=prestamocaja.idpersona where idcorte=:idcorte group by personas.idpersona", nativeQuery = true)
    List<Map<String, Serializable>> findPrestamoCajaByIdCorte(Integer idcorte);

    @Query(value = "select SUM(vt.total) as totalvendido, corte.dinerocaja, corte.dinerocajacorte from public.venta_total vt inner join public.corte corte on corte.idcorte=vt.idcorte where vt.idcorte=:idcorte group by corte.dinerocaja, corte.dinerocajacorte", nativeQuery = true)
    Map<String, Serializable> findTotalVendidoByIdCorte(Integer idcorte);

    @Query(value = "SELECT SUM(papas) as total_papas FROM (SELECT  (SUM(v.total)-(SUM(cantidad)*h.precio)) as papas FROM public.ventas v LEFT join venta_total  vt on vt.idventa_total=v.idventa_total LEFT JOIN hotdogs h on h.idhotdog=v.idhotdog where vt.idcorte=:idcorte and h.hotdog is not null GROUP BY h.idhotdog, h.precio) datos;", nativeQuery = true)
    Map<String, Serializable> findTotalVendidoPapasByIdCorte(Integer idcorte);

    @Query(value = "SELECT CASE WHEN SUM (total) is null then 0 else SUM (total) end as total FROM(SELECT   SUM(v.total) as total, p.producto, p.ispapas, vt.idcorte FROM public.ventas v LEFT JOIN productos p on p.idproducto=v.idproducto left join venta_total vt on vt.idventa_total=v.idventa_total where p.producto is not null and vt.idcorte=:idcorte and p.ispapas=true GROUP BY  p.producto, p.ispapas, vt.idcorte) datos", nativeQuery = true)
    Map<String, Serializable> findTotalVendidoPapasProductosByIdCorte(Integer idcorte);

    @Query(value = "SELECT sum(totalvendido), mes, periodo FROM (select SUM(vt.total) as totalvendido,corte.idcorte,date_part('month', corte.horaentrada) mes, date_part('year',  corte.horaentrada) periodo from public.venta_total vt inner join public.corte corte on corte.idcorte=vt.idcorte where date_part('year',  corte.horaentrada)=:periodo  and corte.horasalida is not null  group by corte.dinerocaja, corte.dinerocajacorte,corte.idcorte,corte.horaentrada) datos group by mes, periodo", nativeQuery = true)
    List<Map<String, Serializable>> getVentasPorMes(Integer periodo);

    @Query(value = "SELECT date_part('year', corte.horaentrada) periodo FROM corte where corte.horasalida is not null group by date_part('year', corte.horaentrada)", nativeQuery = true)
    List<Map<String, Serializable>> getPeriodos();

    @Query(value = "SELECT sum(cantidad) as cantidad, producto as descripcion, sum(total) as total   FROM(SELECT idventa, CASE WHEN h.hotdog IS NULL and p is not null THEN UPPER(p.producto) ELSE UPPER(h.hotdog) END AS producto , v.cantidad, v.total, v.idventa_total, v.idhotdog, v.idproducto, vt.fechaventa FROM ventas v LEFT JOIN public.hotdogs h on h.idhotdog=v.idhotdog LEFT JOIN public.productos p on p.idproducto=v.idproducto INNER JOIN public.venta_total vt ON vt.idventa_total=v.idventa_total) datos where date_part('year',fechaventa)=date_part('year',now()) and date_part('month',fechaventa)=date_part('month',now()) group by producto order by sum(cantidad ) desc", nativeQuery = true)
    List<Map<String, Serializable>> getProductosPorMes();

    @Query(value = "SELECT case when sum(m.cantidad) is null then 0 else sum(m.cantidad) end as mermas, case when sum(v.cantidad) is null then 0 else sum(v.cantidad) end as panesVendidos, c.panes, (c.panes-(case when sum(v.cantidad) is null then 0 else sum(v.cantidad) end)-(case when sum(m.cantidad) is null then 0 else sum(m.cantidad) end)) as restantes FROM ventas v INNER JOIN public.hotdogs h on h.idhotdog=v.idhotdog INNER JOIN public.venta_total vt ON vt.idventa_total=v.idventa_total inner join corte c on c.idcorte=vt.idcorte left join mermas m on m.idcorte=c.idcorte where vt.idcorte=:idcorte group by panes", nativeQuery = true)
    List<Map<String, Serializable>> getPanesRestantes(Integer idcorte);

    @Query(value = "SELECT sum(venta_total.total) as total FROM venta_total where idcorte=:idcorte and uber=true", nativeQuery = true)
    List<Map<String, Serializable>> getTotalUber(Integer idcorte);

    @Query(value = "SELECT sum(venta_total.total) as total FROM venta_total where idcorte=:idcorte and tarjeta=true", nativeQuery = true)
    List<Map<String, Serializable>> getTotalTarjeta(Integer idcorte);


}
