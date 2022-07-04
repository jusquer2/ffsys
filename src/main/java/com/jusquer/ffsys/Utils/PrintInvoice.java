package com.jusquer.ffsys.Utils;

import com.jusquer.ffsys.domain.dto.ProductosTotales;
import com.jusquer.ffsys.domain.dto.ReporteDatosJasper;
import com.jusquer.ffsys.domain.dto.Resultado;
import com.jusquer.ffsys.domain.dto.Total;
import com.jusquer.ffsys.persistence.entity.Mermas;
import com.jusquer.ffsys.persistence.entity.Prestamocaja;
import com.jusquer.ffsys.persistence.entity.VentaTotal;
import com.jusquer.ffsys.persistence.entity.Ventas;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.PrinterName;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.io.*;
import java.util.*;

@Component
public class PrintInvoice {

   @Value("${receipt.title}")
    private String TITLE;

    @Value("${receipt.footer1}")
    private String FOOTER1;
    @Value("${receipt.msgline1}")
    private String MSGLINE1;
    @Value("${receipt.msgline2}")
    private String MSGLINE2;
    @Value("${receipt.address1}")
    private String ADDRESS1;
    @Value("${receipt.address2}")
    private String ADDRESS2;
    @Value("${receipt.name}")
    private String NAME;

    public Resultado getFormatoReporte(VentaTotal ventaTotal) {
        Resultado resultado = new Resultado();
        Resultado resultadoDatosReporteJasper = null;
        List<String> lstResultado = new ArrayList<String>();

        String fileName = "";
        String folderPlantillas = "";
        ReporteDatosJasper datosReporteJasper = null;

        folderPlantillas = "C:/";
        fileName = "Invoice.jrxml";

        fileName = folderPlantillas + fileName;

        resultadoDatosReporteJasper = llenarDatos(ventaTotal);

        try {
            if (resultadoDatosReporteJasper.getLstResultado() != null && !resultadoDatosReporteJasper.getError()) {
                datosReporteJasper = (ReporteDatosJasper) resultadoDatosReporteJasper.getLstResultado().get(0);

                // Llena el reporte con el hashMap
                InputStream is = new FileInputStream(new File("C:/Invoice.jrxml"));
                JasperDesign jd = JRXmlLoader.load(is);
                int dynamicHeight = 0;
                if (ventaTotal.getLstVentas().size() > 4) {
                    dynamicHeight = (20 * (ventaTotal.getLstVentas().size() - 4));
                    //dynamicHeight = (25 * (ventaTotal.getLstVentas().size() - 4));
                }
                //jd.setPageHeight(325 + dynamicHeight);
                jd.setPageHeight(405 + dynamicHeight);
                JasperReport jr = JasperCompileManager.compileReport(jd);
                JasperPrint print = JasperFillManager.fillReport(jr, datosReporteJasper.getParametros(),
                        datosReporteJasper.getFieldsReporte());
                try {
                    PrintService service = PrintServiceLookup.lookupDefaultPrintService();
                    //PrintFromJasper(print, "EPSON TM-T20II Receipt");
                    PrintFromJasper(print, service.getName());
                } catch (Exception e) {
                    System.err.println("No hay impresora DEFAULT" + e.getMessage());
                }

                resultado.setLstResultado(lstResultado);
            }
        } catch (JRException e) {
            resultado.setError(true);
            e.printStackTrace();
        } catch (Exception e) {
            resultado.setError(true);
            e.printStackTrace();
        }

        return resultado;
    }

    /**
     *
     * @author Juan Manuel Esquer Esquer
     * @return resultado
     **/
    private Resultado llenarDatos(VentaTotal ventaTotal) {
        Resultado resultado = new Resultado();

        List<ReporteDatosJasper> lstResultado = new ArrayList<ReporteDatosJasper>();
        ReporteDatosJasper datosReporteJasper = new ReporteDatosJasper();
        JRMapCollectionDataSource fieldsReporte;
        HashMap<String, Object> simpleMasterMap = null;
        Collection<Map<String, ?>> simpleMasterList = new ArrayList<>();

        simpleMasterMap = new HashMap<String, Object>();
        //simpleMasterMap.put("titulo", "Hot Dog's Carlos Alamos, Son.");
        //simpleMasterMap.put("titulo", "Taqueria La Morelos");
        simpleMasterMap.put("titulo", TITLE==null?"Dogos de la morelos":TITLE);
        //simpleMasterMap.put("footer1", "Tel. 01 (647) 428 1518");
        //simpleMasterMap.put("footer1", "Tel. 642 151 0093");
        simpleMasterMap.put("footer1", FOOTER1==null?"Tel. 642 149 2023":FOOTER1);
        simpleMasterMap.put("numOrden", ventaTotal.getNumOrden() + "");
        simpleMasterMap.put("total", ventaTotal.getTotal().toString());
        simpleMasterMap.put("pago", ventaTotal.getPago().toString());
        simpleMasterMap.put("cambio", ventaTotal.getCambio().toString());
        datosReporteJasper.setParametros(simpleMasterMap);

        for (Ventas venta : ventaTotal.getLstVentas()) {
            simpleMasterMap = new HashMap<String, Object>();
            simpleMasterMap.put("descripcion", venta.getDescripcion());
            simpleMasterMap.put("cantidad", venta.getCantidad() + "");
            simpleMasterMap.put("pu", venta.getPrecioUnitario().toString());
            simpleMasterMap.put("precio", venta.getPrecio().toString());
            simpleMasterList.add(simpleMasterMap);
        }

        try {
            fieldsReporte = new JRMapCollectionDataSource(simpleMasterList);
            datosReporteJasper.setFieldsReporte(fieldsReporte);

            lstResultado.add(datosReporteJasper);
            resultado.setError(false);
            resultado.setLstResultado(lstResultado);

        } catch (Exception e) {
            lstResultado = null;
            resultado.setError(true);
            resultado.setMensajeError("ReportesDAO-llenarDatosReporteOrden" + e.getMessage());
        } finally {

        }
        return resultado;
    }

    public void PrintFromJasper(JasperPrint jasperPrint, String printerNameShort) {
        try {

            PrinterJob printerJob = PrinterJob.getPrinterJob();

            PageFormat pageFormat = PrinterJob.getPrinterJob().defaultPage();
            printerJob.defaultPage(pageFormat);

            int selectedService = 0;

            AttributeSet attributeSet = new HashPrintServiceAttributeSet(new PrinterName(printerNameShort, null));

            PrintService[] printService = PrintServiceLookup.lookupPrintServices(null, attributeSet);
            PrinterService printerService = new PrinterService();

            try {
                printerJob.setPrintService(printService[selectedService]);

            } catch (Exception e) {

                System.out.println(e);
            }
            JRPrintServiceExporter exporter;
            PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
            printRequestAttributeSet.add(MediaSizeName.INVOICE);
            printRequestAttributeSet.add(new Copies(1));

            // these are deprecated
            exporter = new JRPrintServiceExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, printService[selectedService]);
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET,
                    printService[selectedService].getAttributes());
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET,
                    printRequestAttributeSet);
            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
            exporter.exportReport();
            printerService.printBytes(printerNameShort, PrinterService.PAPER_FULL_CUT);
            printerService.printBytes(printerNameShort, PrinterService.CD_KICK_2);
            printerService.printBytes(printerNameShort, PrinterService.CD_KICK_5);

        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    public Resultado cortePrint(Total total) {
        Resultado resultado = new Resultado();
        PrinterService printerService = new PrinterService();

        String printername = NAME;//"EPSON TM-T20II Receipt";
        try{
            PrintService service = PrintServiceLookup.lookupDefaultPrintService();
            printername = service.getName();
        }catch(Exception e){
            printername = NAME;
        }
        try {
            printerService.printBytes(printername, PrinterService.HW_INIT);
            printerService.printBytes(printername, PrinterService.BREAK_LINE);
            printerService.printBytes(printername, PrinterService.TXT_ALIGN_CT);
            //printerService.printString(printername, "Hot Dog's Carlos, Alamos,Son.");
            //printerService.printString(printername, "Taqueria La Morelos");
            printerService.printString(printername, TITLE==null?"DOGOS DE LA MORELOS, Navojoa Son.":TITLE);
            printerService.printBytes(printername, PrinterService.BREAK_LINE);
            printerService.printString(printername, new Date().toString());
            printerService.printBytes(printername, PrinterService.BREAK_LINE);
            printerService.printString(printername, "================================");
            printerService.printBytes(printername, PrinterService.BREAK_LINE);
            printerService.printBytes(printername, PrinterService.TXT_ALIGN_RT);
            printerService.printString(printername, "MERMAS");
            printerService.printBytes(printername, PrinterService.BREAK_LINE);

            for (Mermas merma:total.getMerma()) {
                printerService.printString(printername, merma.getPersona().getNombrePersona() + " ("+merma.getCantidad()+")");
                printerService.printBytes(printername, PrinterService.BREAK_LINE);
            }
            printerService.printString(printername, "================================");
            printerService.printBytes(printername, PrinterService.BREAK_LINE);
            printerService.printString(printername, "PRODUCTOS VENDIDOS");
            printerService.printBytes(printername, PrinterService.BREAK_LINE);
            for (ProductosTotales productos_Totales:total.getProductosTotales()) {
                printerService.printString(printername, productos_Totales.getProducto() + " ("+productos_Totales.getCantidad()+")");
                printerService.printBytes(printername, PrinterService.BREAK_LINE);
            }
            printerService.printString(printername, "================================");
            printerService.printBytes(printername, PrinterService.BREAK_LINE);
            printerService.printString(printername, "PRESTAMOS DE CAJA");
            printerService.printBytes(printername, PrinterService.BREAK_LINE);
            for (Prestamocaja  prestamoCaja:total.getPrestamoCaja()) {
                printerService.printString(printername, prestamoCaja.getPersona().getNombrePersona() + " ($"+prestamoCaja.getCantidadDinero()+")");
                printerService.printBytes(printername, PrinterService.BREAK_LINE);
            }
            printerService.printString(printername, "================================");
            printerService.printBytes(printername, PrinterService.BREAK_LINE);
            printerService.printString(printername, "DINERO CAJA INICIO VENTA    " + total.getDineroCaja());
            printerService.printBytes(printername, PrinterService.BREAK_LINE);
            printerService.printString(printername, "DINERO CAJA CORTE    " + total.getDineroCajaCorte());
            printerService.printBytes(printername, PrinterService.BREAK_LINE);
            printerService.printString(printername, "TOTAL PAPAS    " + total.getTotalPapas());
            printerService.printBytes(printername, PrinterService.BREAK_LINE);
            printerService.printString(printername, "TOTAL VENDIDO    " + total.getTotalVendido());
            printerService.printBytes(printername, PrinterService.BREAK_LINE);
            printerService.printString(printername, "TOTAL UBER    " + total.getUberTotal());
            printerService.printBytes(printername, PrinterService.BREAK_LINE);
            printerService.printString(printername, "TOTAL TARJETA    " + total.getTarjetaTotal());
            printerService.printBytes(printername, PrinterService.BREAK_LINE);
            printerService.printString(printername, "================================");
            printerService.printString(printername, "TOTAL     " + ( Float.parseFloat(total.getDineroCaja())+Float.parseFloat( total.getTotalVendido())));
            printerService.printBytes(printername, PrinterService.BREAK_LINE);
            printerService.printString(printername, "================================");
            printerService.printBytes(printername, PrinterService.TXT_BOLD_ON); // bold on
            printerService.printBytes(printername, PrinterService.TXT_ALIGN_CT);
            printerService.printBytes(printername, PrinterService.BREAK_LINE);
            printerService.printBytes(printername, PrinterService.BREAK_LINE);
            printerService.printBytes(printername, PrinterService.BREAK_LINE);
            printerService.printBytes(printername, PrinterService.BREAK_LINE);
            //printerService.printBytes(printername, PrinterService.PAPER_FULL_CUT);
            printerService.printBytes(printername, PrinterService.CD_KICK_2);
            printerService.printBytes(printername, PrinterService.CD_KICK_5);
            resultado.setError(false);
        } catch (Exception e) {
            resultado.setError(true);
            resultado.setMensajeError(e.getMessage());
        }
        return resultado;
    }

}