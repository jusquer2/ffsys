package com.jusquer.ffsys.web.controller;

import com.jusquer.ffsys.Utils.PrintInvoice;
import com.jusquer.ffsys.Utils.PrinterService;
import com.jusquer.ffsys.domain.dto.ProductosTotales;
import com.jusquer.ffsys.domain.dto.Resultado;
import com.jusquer.ffsys.domain.dto.Total;
import com.jusquer.ffsys.domain.service.*;
import com.jusquer.ffsys.persistence.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/DogosServices", produces = "application/json")
public class HotdogsController {
    @Value("${receipt.name}")
    String serviceName;
    @Autowired
    HotdogService hotdogsService;
    @Autowired
    ProductosService productosService;
    @Autowired
    PersonasService personasService;
    @Autowired
    CorteService corteService;
    @Autowired
    VentaTotalService ventaTotalService;
    @Autowired
    MermasService mermasService;
    @Autowired
    PrestamocajaService prestamocajaService;
    @Autowired
    VentasService ventasService;
    @Autowired
    CategoriaService categoriaService;
    @Autowired
    PrintInvoice pi;

    @GetMapping("/getHotDogs")
    public ResponseEntity<Resultado> getHotDogs(){
        Resultado resultado = new Resultado();
        resultado.setError(false);
        resultado.setLstResultado(hotdogsService.getAll());
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }
    @GetMapping("/getCategorias")
    public ResponseEntity<?> getCategorias(){
        Resultado resultado = new Resultado();
        resultado.setError(false);
        resultado.setLstResultado(categoriaService.findByEliminado(false ));
        return new ResponseEntity<>(resultado.getLstResultado(), HttpStatus.OK);
    }
    @PostMapping("/insertHotDogs")
    public ResponseEntity<Resultado> insertHotDogs(@RequestBody Hotdogs hotdogs){
        hotdogs.setEliminado(false);
        hotdogs.setPrioridad(100);
        hotdogsService.insertHotDogs(hotdogs);
        Resultado resultado = new Resultado();
        resultado.setError(false);
        return new ResponseEntity<>(resultado,HttpStatus.OK);
    }

    @PostMapping("/insertCategoria")
    public ResponseEntity<Resultado> insertCategoria(@RequestBody Categorias categorias){
        if(categorias.getEliminado()==null){
            categorias.setEliminado(false);
        }
        categoriaService.save(categorias);
        Resultado resultado = new Resultado();
        resultado.setError(false);
        return new ResponseEntity<>(resultado,HttpStatus.OK);
    }

    @PostMapping("/updatetHotDogs")
    public ResponseEntity<Resultado> updatetHotDogs(@RequestBody Hotdogs hotdogs){
        hotdogsService.insertHotDogs(hotdogs);
        Resultado resultado = new Resultado();
        resultado.setError(false);
        return new ResponseEntity<>(resultado,HttpStatus.OK);
    }
    @PostMapping("/eliminarHotDogs")
    public ResponseEntity<Resultado> eliminarHotDogs(@RequestBody Hotdogs hotdogs){
        hotdogs.setEliminado(true);
        hotdogsService.insertHotDogs(hotdogs);
        Resultado resultado = new Resultado();
        resultado.setError(false);
        return new ResponseEntity<>(resultado,HttpStatus.OK);
    }
    @GetMapping("/getProductos")
    public ResponseEntity<Resultado> getProductos(){
        Resultado resultado = new Resultado();
        resultado.setError(false);
        resultado.setLstResultado(productosService.getAll());
        return new ResponseEntity<>(resultado,HttpStatus.OK);
    }

    @PostMapping("/insertProductos")
    public ResponseEntity<Resultado> insertProductos(@RequestBody Productos productos){
        productos.setEliminado(false);
        productos.setIspapas(false);
        productos.setPrioridad(100);
        productosService.save(productos);
        Resultado resultado = new Resultado();
        resultado.setError(false);
        return new ResponseEntity<>(resultado,HttpStatus.OK);
    }

    @PostMapping("/updateProductos")
    public ResponseEntity<Resultado> updateProductos(@RequestBody Productos productos){
        productosService.save(productos);
        Resultado resultado = new Resultado();
        resultado.setError(false);
        return new ResponseEntity<>(resultado,HttpStatus.OK);
    }

    @PostMapping("/eliminarProductos")
    public ResponseEntity<Resultado> eliminarProductos(@RequestBody Productos productos){
        productos.setEliminado(true);
        productosService.save(productos);
        Resultado resultado = new Resultado();
        resultado.setError(false);
        return new ResponseEntity<>(resultado,HttpStatus.OK);
    }

    @GetMapping("/getPersonas")
    public ResponseEntity<Resultado> getPersonas(){
        Resultado resultado = new Resultado();
        resultado.setError(false);
        resultado.setLstResultado(personasService.findByEliminado(false));
        return new ResponseEntity<>(resultado,HttpStatus.OK);
    }

    @PostMapping("/crearCorte")
    public ResponseEntity<Resultado> crearCorte(@RequestBody Corte corte){
        Resultado resultado = new Resultado();
        resultado.setError(false);
        List<Corte> lstResultado = new ArrayList<>();
        corte.setHoraEntrada(new Timestamp(new Date().getTime()));
        lstResultado.add(corteService.crearCorte(corte));
        resultado.setLstResultado(lstResultado);
        return new ResponseEntity<>(resultado,HttpStatus.OK);
    }
    @PostMapping("/updateCorte")
    public ResponseEntity<Resultado> updateCorte(@RequestBody Corte corte){
        Resultado resultado = new Resultado();
        resultado.setError(false);
        corte.setHoraSalida(new Timestamp(new Date().getTime()));

        corteService.crearCorte(corte);
        return new ResponseEntity<>(resultado,HttpStatus.OK);
    }
    @PostMapping("/insertVenta/{imp}")
    public ResponseEntity<Resultado> insertVenta(@RequestBody VentaTotal ventas, @PathVariable("imp") Boolean imp){
        Resultado resultado = new Resultado();
        resultado.setError(false);
        ventas.setFechaventa(new Timestamp(new Date().getTime()));
        ventas = ventaTotalService.insertVenta(ventas);

        for (Ventas v : ventas.getLstVentas()){
            v.setIdVentaTotal(ventas.getIdventa_total());
            v.setTotal(v.getPrecio());
            v = ventasService.insertVenta(v);
        }
        if(!imp){
            PrinterService ps = new PrinterService();
            ps.printBytes(serviceName, PrinterService.CD_KICK_2);
            ps.printBytes(serviceName, PrinterService.CD_KICK_5);
        }

        return new ResponseEntity<>(resultado,HttpStatus.OK);
    }

    @PostMapping("/printTotal")
    public ResponseEntity<?> printTotal(@RequestBody Total total){
        Resultado resultado = pi.cortePrint(total);
        return new ResponseEntity<>(resultado,HttpStatus.OK);
    }

    @PostMapping("/insertPersona")
    public ResponseEntity<Resultado> insertPersona(@RequestBody Personas personas){
        Resultado resultado = new Resultado();
        resultado.setError(false);
        if(personas.getEliminado()==null){
            personas.setEliminado(false);
        }
        personasService.save(personas);
        return new ResponseEntity<>(resultado,HttpStatus.OK);
    }

    @PostMapping("/updatePersonas/{id}")
    public ResponseEntity<Resultado> updatePersonas(@RequestBody Personas personas, @PathVariable("id") Integer id){
        Resultado resultado = new Resultado();
        personas = personasService.findById(id);
        resultado.setError(false);
        personas.setEliminado(true);
        personasService.save(personas);
        return new ResponseEntity<>(resultado,HttpStatus.OK);
    }

    @GetMapping("/getTotal/{idcorte}")
    public ResponseEntity<Resultado> productostotal(@PathVariable("idcorte") Integer idcorte){
        Total total = new Total();
        List<ProductosTotales> productosTotalesList = new ArrayList<>();
        List<Map<String,Serializable>> mapsProductosTotales=ventaTotalService.productosTotales(idcorte);
        for (Map<String,Serializable> map:mapsProductosTotales
             ) {
            ProductosTotales pt = new ProductosTotales();
            pt.setProducto((String) map.get("producto"));
            pt.setCantidad(((BigInteger) map.get("cantidad"))+"");
            pt.setTotal(((Double) map.get("total"))+"");
            productosTotalesList.add(pt);
        }
        total.setProductosTotales(productosTotalesList);
        total.setMerma(mermasService.findByIdCorte(idcorte));
        List<Prestamocaja> prestamocajaList = new ArrayList<>();
        List<Map<String,Serializable>> mapsPrestamoCaja = ventaTotalService.findPrestamoCajaByIdCorte(idcorte);
        for(Map<String,Serializable> map : mapsPrestamoCaja){
            Prestamocaja prestamocaja = new Prestamocaja();
            prestamocaja.setCantidadDinero((Double) map.get("total"));
            prestamocaja.setIdCorte(idcorte);
            Personas persona = new Personas();
            persona.setNombrePersona((String) map.get("nombrepersona"));
            prestamocaja.setPersona(persona);
            prestamocajaList.add(prestamocaja);

        }
        total.setPrestamoCaja(prestamocajaList);

        Map<String, Serializable> totalVendido= ventaTotalService.getTotalVendidoByIdCorte(idcorte);
        total.setDineroCaja(totalVendido.get("dinerocaja").toString());
        total.setDineroCajaCorte ( totalVendido.get("dinerocajacorte").toString());
        total.setTotalVendido(totalVendido.get("totalvendido").toString());

        Map<String,Serializable> totalPapas = ventaTotalService.findTotalVendidoPapasByIdCorte(idcorte);
        try {
            total.setTotalPapas ( totalPapas.get("total_papas").toString());
        }catch (Exception e){
            total.setTotalPapas("0");
        }

        Map<String,Serializable> totalPapasProductos = ventaTotalService.findTotalVendidoPapasProductosByIdCorte(idcorte);
        total.setTotalPapas( (totalPapasProductos.size()==0?0:(Double.parseDouble(totalPapasProductos.get("total").toString())) + Double.parseDouble(total.getTotalPapas()))+"");
        total.setTotal ( Double.parseDouble(total.getTotalVendido())+Double.parseDouble(total.getDineroCaja()));
        for(Prestamocaja presCaja: total.getPrestamoCaja()){
            total.setTotal( total.getTotal() - presCaja.getCantidadDinero());
        }
        List<Map<String,Serializable>>totalUber = ventaTotalService.getTotalUber(idcorte);
        for(Map<String, Serializable> uber:totalUber){
            if(uber== null){
                total.setUberTotal(0.0);
            }else{
                total.setUberTotal((Double) uber.get("total"));
            }
        }
        List<Map<String,Serializable>>totalTarjeta = ventaTotalService.getTotalTarjeta(idcorte);
        for(Map<String, Serializable> uber:totalTarjeta){
            if(uber== null){
                total.setTarjetaTotal(0.0);
            }else{
                total.setTarjetaTotal((Double) uber.get("total"));
            }
        }
        total.setTotal(Double.parseDouble(total.getTotalVendido())-total.getTarjetaTotal()-total.getUberTotal()+Double.parseDouble( total.getDineroCaja()));
        Resultado resultado = new Resultado();
        List<Total> lstTotal = new ArrayList<>();
        lstTotal.add(total);
        resultado.setLstResultado(lstTotal);
        resultado.setError(false);
        return new ResponseEntity<>(resultado,HttpStatus.OK);
    }
    @PostMapping("/insertMerma")
    public ResponseEntity<Resultado> insertMerma(@RequestBody List<Mermas> mermas)
    {
        Resultado resultado = new Resultado();
        resultado.setError(false);
        for (Mermas merma:mermas
             ) {
            merma.setFechamerma(new Timestamp(new Date().getTime()));
            mermasService.save(merma);
        }

        return new ResponseEntity<>(resultado,HttpStatus.OK);
    }
    @PostMapping("/insertPrestamoCaja")
    public ResponseEntity<Resultado> insertPrestamoCaja(@RequestBody Prestamocaja prestamocaja)
    {
        Resultado resultado = new Resultado();
        resultado.setError(false);
        prestamocaja.setFechaprestamo(new Timestamp(new Date().getTime()));
        prestamocajaService.save(prestamocaja);
        return new ResponseEntity<>(resultado,HttpStatus.OK);
    }
    @GetMapping("/getPanes/{idcorte}")
    public ResponseEntity<Resultado> getPanes(@PathVariable("idcorte") Integer idcorte){
        Resultado resultado = new Resultado();
        resultado.setError(false);
        List<Map<String,Serializable>> lstPanes = corteService.getPanesByIdCorte(idcorte);
        if(lstPanes.isEmpty()){
            lstPanes = corteService.getPanesByIdCorteEmpty(idcorte);
        }
        resultado.setLstResultado(lstPanes);
        return new ResponseEntity<>(resultado,HttpStatus.OK);
    }

    @GetMapping("/getTotalVentaCorte/{idcorte}")
    public ResponseEntity<Resultado> getTotalVentaCorte(@PathVariable("idcorte") Integer idcorte){
        Resultado resultado = new Resultado();
        resultado.setError(false);
        List<Map<String,Serializable>> lstResultado = corteService.getTotalVentaCorte(idcorte);
        resultado.setLstResultado(lstResultado);
        return new ResponseEntity<>(resultado,HttpStatus.OK);
    }

    @GetMapping("/getVentasPorMes/{periodo}")
    public ResponseEntity<Resultado> getVentasPorMes(@PathVariable("periodo") Integer periodo){
        Resultado resultado = new Resultado();
        resultado.setError(false);
        resultado.setLstResultado(ventaTotalService.getVentasPorMes(periodo));
        return new ResponseEntity<>(resultado,HttpStatus.OK);
    }

    @GetMapping("/getPeriodos")
    public ResponseEntity<Resultado> getPeriodos(){
        Resultado resultado = new Resultado();
        resultado.setError(false);
        resultado.setLstResultado(ventaTotalService.getPeriodos());
        return new ResponseEntity<>(resultado,HttpStatus.OK);
    }

    @GetMapping("/getProductosPorMes")
    public ResponseEntity<Resultado> getProductosPorMes(){
        Resultado resultado = new Resultado();
        resultado.setError(false);
        resultado.setLstResultado(ventaTotalService.getProductosPorMes());
        return new ResponseEntity<>(resultado,HttpStatus.OK);
    }

    @GetMapping("/getCortesFecha/{fecha}")
    public ResponseEntity<Resultado> getCortesFecha(@PathVariable("fecha") String fecha){
        Resultado resultado = new Resultado();
        resultado.setError(false);
        resultado.setLstResultado(corteService.getCortesFecha(fecha));
        return new ResponseEntity<>(resultado,HttpStatus.OK);
    }

    @GetMapping("/getTickets/{idcorte}")
    public ResponseEntity<Resultado> getCortesFecha(@PathVariable("idcorte") Integer idcorte){
        Resultado resultado = new Resultado();
        resultado.setError(false);
        resultado.setLstResultado(ventaTotalService.findByIdCorte(idcorte));
        return new ResponseEntity<>(resultado,HttpStatus.OK);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Resultado> exceptionHand(IllegalStateException e){
        Resultado resultado = new Resultado();
        resultado.setError(true);
        resultado.setMensajeError(e.getMessage());
        resultado.setCodigoError("400");
        return new ResponseEntity<>(resultado,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Resultado> exceptionHand(Exception e){
        Resultado resultado = new Resultado();
        resultado.setError(true);
        resultado.setMensajeError(e.getMessage());
        resultado.setCodigoError("500");
        return new ResponseEntity<>(resultado,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
