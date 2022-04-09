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


public class PrintInvoice {
    @Value("${receipt.title}")
    private String title;
    @Value("${receipt.footer1}")
    private String footer1;
    @Value("${receipt.msgline1}")
    private String msgline1;
    @Value("${receipt.msgline2}")
    private String msgline2;
    @Value("${receipt.address1}")
    private String address1;
    @Value("${receipt.address2}")
    private String address2;


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
                InputStream is = new FileInputStream(new File("C:/Invoice58mm.jrxml"));
                JasperDesign jd = JRXmlLoader.load(is);
                int dynamicHeight = 0;
                if (ventaTotal.getLstVentas().size() > 4) {
                    dynamicHeight = (15 * (ventaTotal.getLstVentas().size() - 4));
                }
                jd.setPageHeight(500 + dynamicHeight);
                JasperReport jr = JasperCompileManager.compileReport(jd);
                JasperPrint print = JasperFillManager.fillReport(jr, datosReporteJasper.getParametros(),
                        datosReporteJasper.getFieldsReporte());

                // Crea el documento
                JRRtfExporter exporter = new JRRtfExporter();
                final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                OutputStream outputStream = new FileOutputStream("C:\\Users\\Dell\\Downloads/application.txt");
                // configuracin de la exportacion
                exporter.setExporterInput(new SimpleExporterInput(print));
                exporter.setExporterOutput(new SimpleWriterExporterOutput(outStream));

                outputStream.write(outStream.toByteArray());
                outputStream.close();
                // Exporta el archivo
                exporter.exportReport();

                // Genera una cadena codificada en base 64 de los bytes del reporte
                String reporteB64 = Base64.getEncoder().encodeToString(outStream.toByteArray());
                lstResultado.add(reporteB64);
                try {
                    PrintFromJasper(print, PrintServiceLookup.lookupDefaultPrintService().getName());
                } catch (Exception e) {
                    System.err.println("No hay impresoras " + e.getMessage());
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
     * Genera el reporte de los detalles de tariamas individuales mediante el
     * idTarima y la zona
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
        simpleMasterMap.put("titulo", title);
        simpleMasterMap.put("footer1", footer1);
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
            byte[] cutP = new byte[] { 0x1d, 'V', 1 };
            byte[] open = { 27, 112, 48, 55, 121 };
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
            printerService.printBytes(printerNameShort, cutP);
            printerService.printBytes(printerNameShort, open);
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
        String printername = PrintServiceLookup.lookupDefaultPrintService().getName();

        try {
            printerService.printBytes(printername, PrinterService.HW_INIT);
            printerService.printBytes(printername, PrinterService.BREAK_LINE);
            printerService.printBytes(printername, PrinterService.TXT_ALIGN_CT);
            printerService.printString(printername, title);
            printerService.printBytes(printername, PrinterService.BREAK_LINE);
            printerService.printString(printername, new Date().toString());
            printerService.printBytes(printername, PrinterService.BREAK_LINE);
            printerService.printString(printername, "================================");
            printerService.printBytes(printername, PrinterService.BREAK_LINE);
            printerService.printBytes(printername, PrinterService.TXT_ALIGN_RT);
            printerService.printString(printername, "MERMAS");
            printerService.printBytes(printername, PrinterService.BREAK_LINE);

            /*for (Map<String,Serializable> merma:total.getMerma()) {
                printerService.printString(printername, merma.getPersonas().getNombrepersona() + " ("+merma.getCantidad()+")");
                printerService.printBytes(printername, PrinterService.BREAK_LINE);
            }*/
            printerService.printString(printername, "================================");
            printerService.printBytes(printername, PrinterService.BREAK_LINE);
            printerService.printString(printername, "PRODUCTOS VENDIDOS");
            printerService.printBytes(printername, PrinterService.BREAK_LINE);
            for (Map<String,Serializable> productos_Totales:total.getProductosTotales()) {
                printerService.printString(printername, productos_Totales.get("descripcion") + " ("+productos_Totales.get("cantidad")+")");
                printerService.printBytes(printername, PrinterService.BREAK_LINE);
            }
            printerService.printString(printername, "================================");
            printerService.printBytes(printername, PrinterService.BREAK_LINE);
            printerService.printString(printername, "PRESTAMOS DE CAJA");
            printerService.printBytes(printername, PrinterService.BREAK_LINE);
            /*for (Map<String,Serializable> prestamoCaja:total.getPrestamoCaja()) {
                printerService.printString(printername, prestamoCaja.getPersonas().getNombrepersona() + " ($"+prestamoCaja.getCantidaddinero()+")");
                printerService.printBytes(printername, PrinterService.BREAK_LINE);
            }*/
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
            printerService.printString(printername, "================================");
            printerService.printString(printername, "TOTAL     " + ( Float.parseFloat(total.getDineroCaja())+Float.parseFloat( total.getTotalVendido())));
            printerService.printBytes(printername, PrinterService.BREAK_LINE);
            printerService.printString(printername, "================================");
            printerService.printBytes(printername, PrinterService.TXT_BOLD_ON); // bold on
            printerService.printBytes(printername, PrinterService.TXT_ALIGN_CT);
            printerService.printString(printername, "CORTE");
            printerService.printBytes(printername, PrinterService.BREAK_LINE);
            printerService.printString(printername, msgline1);
            printerService.printBytes(printername, PrinterService.BREAK_LINE);
            printerService.printString(printername, msgline2);
            printerService.printBytes(printername, PrinterService.BREAK_LINE);
            printerService.printString(printername, address1);
            printerService.printBytes(printername, PrinterService.BREAK_LINE);
            printerService.printString(printername, address2);
            printerService.printBytes(printername, PrinterService.BREAK_LINE);
            printerService.printBytes(printername, PrinterService.BREAK_LINE);
            printerService.printBytes(printername, PrinterService.BREAK_LINE);
            printerService.printBytes(printername, PrinterService.BREAK_LINE);
            printerService.printBytes(printername, PrinterService.CD_KICK_2);
            printerService.printBytes(printername, PrinterService.CD_KICK_5);
            printerService.printString(printername, "'");
            printerService.printBytes(printername, PrinterService.TXT_BOLD_OFF);
            resultado.setError(false);
        } catch (Exception e) {
            resultado.setError(true);
            resultado.setMensajeError(e.getMessage());
        }
        return resultado;
    }


}
