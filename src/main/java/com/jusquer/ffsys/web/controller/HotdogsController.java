package com.jusquer.ffsys.web.controller;

import com.jusquer.ffsys.Utils.PrintInvoice;
import com.jusquer.ffsys.domain.dto.Resultado;
import com.jusquer.ffsys.domain.dto.Total;
import com.jusquer.ffsys.domain.service.*;
import com.jusquer.ffsys.persistence.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/DogosServices", produces = "application/json")
public class HotdogsController {
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
    @GetMapping("/getHotDogs")
    public ResponseEntity<Resultado> getHotDogs(){
        Resultado resultado = new Resultado();
        resultado.setError(false);
        resultado.setLstResultado(hotdogsService.getAll());
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }
    @PostMapping("/insertHotDogs")
    public ResponseEntity<Resultado> insertHotDogs(@RequestBody Hotdogs hotdogs){
        hotdogsService.insertHotDogs(hotdogs);
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
        resultado.setLstResultado(personasService.getAll());
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
    @PostMapping("/insertVenta")
    public ResponseEntity<Resultado> insertVenta(@RequestBody VentaTotal ventas){
        Resultado resultado = new Resultado();
        resultado.setError(false);
        ventas.setFechaventa(new Timestamp(new Date().getTime()));
        ventas = ventaTotalService.insertVenta(ventas);
        for (Ventas v : ventas.getLstVentas()){
            v.setIdVentaTotal(ventas.getIdventa_total());
            v.setTotal(v.getPrecio());
            v = ventasService.insertVenta(v);
        }
        return new ResponseEntity<>(resultado,HttpStatus.OK);
    }

    @PostMapping("/printTotal")
    public ResponseEntity<?> printTotal(@RequestBody Total total){
        Resultado resultado = new PrintInvoice().cortePrint(total);
        return new ResponseEntity<>(resultado,HttpStatus.OK);
    }

    @PostMapping("/insertPersona")
    public ResponseEntity<Resultado> insertPersona(@RequestBody Personas personas){
        Resultado resultado = new Resultado();
        resultado.setError(false);
        personasService.equals(personas);
        return new ResponseEntity<>(resultado,HttpStatus.OK);
    }


    @GetMapping("/getTotal/{idcorte}")
    @ResponseBody()
    public ResponseEntity<Total> productostotal(@PathVariable("idcorte") Integer idcorte){
        Total total = new Total();

        total.setProductosTotales(ventaTotalService.productosTotales(idcorte));
        total.setMerma(ventaTotalService.findMermasByIdCorte(idcorte));
        total.setPrestamoCaja( ventaTotalService.findPrestamoCajaByIdCorte(idcorte));

        Map<String, Serializable> totalVendido= ventaTotalService.getTotalVendidoByIdCorte(idcorte);
        total.setDineroCaja(totalVendido.get("dinerocaja").toString());
        total.setDineroCajaCorte ( totalVendido.get("dinerocajacorte").toString());
        total.setTotalVendido(totalVendido.get("totalvendido").toString());

        Map<String,Serializable> totalPapas = ventaTotalService.findTotalVendidoPapasByIdCorte(idcorte);
        total.setTotalPapas ( totalPapas.get("total_papas").toString());
        Map<String,Serializable> totalPapasProductos = ventaTotalService.findTotalVendidoPapasProductosByIdCorte(idcorte);
        total.setTotalPapas( (Double.parseDouble(totalPapasProductos.get("total").toString()) + Double.parseDouble(total.getTotalPapas()))+"");
        total.setTotal ( Double.parseDouble(total.getTotalVendido())+Double.parseDouble(total.getDineroCaja()));
        for(Map<String, Serializable> presCaja: total.getPrestamoCaja()){
            total.setTotal( total.getTotal() - Double.parseDouble(presCaja.get("total").toString()));
        }
        return new ResponseEntity<>(total,HttpStatus.OK);
    }
    @PostMapping("/insertMerma")
    public ResponseEntity<Resultado> insertMerma(@RequestBody Mermas merma)
    {
        Resultado resultado = new Resultado();
        resultado.setError(false);
        mermasService.save(merma);
        return new ResponseEntity<>(resultado,HttpStatus.OK);
    }
    @PostMapping("/insertPrestamoCaja")
    public ResponseEntity<Resultado> insertPrestamoCaja(@RequestBody Prestamocaja prestamocaja)
    {
        Resultado resultado = new Resultado();
        resultado.setError(false);
        prestamocajaService.save(prestamocaja);
        return new ResponseEntity<>(resultado,HttpStatus.OK);
    }
    @GetMapping("/getPanes/{idcorte}")
    public ResponseEntity<Resultado> getPanes(@PathVariable("idcorte") Integer idcorte){
        Resultado resultado = new Resultado();
        resultado.setError(false);
        resultado.setLstResultado(corteService.getPanesByIdCorte(idcorte));
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
