package com.jusquer.ffsys.Utils;

import org.springframework.stereotype.Component;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;


import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.ArrayList;
import java.util.List;

public class PrinterService implements Printable {
    public final byte[] CTL_LF = {0x0a}; // Print and line feed
    public static final byte[] CAN_HT = {0x1b, 0x44, 0x00}; // Cancel  Horizontal Tab
    public static final byte[] HT = {0x09}; // Horizontal Tab
    public static final byte[] LINE_SPACE_24 = {0x1b, 0x33, 24}; // Set the line spacing at 24
    public static final byte[] LINE_SPACE_30 = {0x1b, 0x33, 30}; // Set the line spacing at 30
    //Image
    public static final byte[] SELECT_BIT_IMAGE_MODE = {0x1B, 0x2A, 33};
    // Printer hardware
    public static final byte[] HW_INIT = {0x1b, 0x40}; // Clear data in buffer and reset modes
    // Cash Drawer
    public static final byte[] CD_KICK_2 = {0x1b, 0x70, 0x00}; // Sends a pulse to pin 2 []
    public static final byte[] CD_KICK_5 = {0x1b, 0x70, 0x01}; // Sends a pulse to pin 5 []
    // Paper
    public static final byte[] PAPER_FULL_CUT = {0x1d, 0x56, 0x00}; // Full cut paper
    public static final byte[] PAPER_PART_CUT = {0x1d, 0x56, 0x01}; // Partial cut paper
    // Text format
    public static final byte[] TXT_NORMAL = {0x1b, 0x21, 0x00}; // Normal text
    public static final byte[] TXT_2HEIGHT = {0x1b, 0x21, 0x10}; // Double height text
    public static final byte[] TXT_2WIDTH = {0x1b, 0x21, 0x20}; // Double width text
    public static final byte[] TXT_4SQUARE = {0x1b, 0x21, 0x30}; // Quad area text
    public static final byte[] TXT_UNDERL_OFF = {0x1b, 0x2d, 0x00}; // Underline font OFF
    public static final byte[] TXT_UNDERL_ON = {0x1b, 0x2d, 0x01}; // Underline font 1-dot ON
    public static final byte[] TXT_UNDERL2_ON = {0x1b, 0x2d, 0x02}; // Underline font 2-dot ON
    public static final byte[] TXT_BOLD_OFF = {0x1b, 0x45, 0x00}; // Bold font OFF
    public static final byte[] TXT_BOLD_ON = {0x1b, 0x45, 0x01}; // Bold font ON
    public static final byte[] TXT_FONT_A = {0x1b, 0x4d, 0x00}; // Font type A
    public static final byte[] TXT_FONT_B = {0x1b, 0x4d, 0x01}; // Font type B
    public static final byte[] TXT_ALIGN_LT = {0x1b, 0x61, 0x00}; // Left justification
    public static final byte[] TXT_ALIGN_CT = {0x1b, 0x61, 0x01}; // Centering
    public static final byte[] TXT_ALIGN_RT = {0x1b, 0x61, 0x02}; // Right justification
    public static final byte[] LEFT_MARGIN = {0x1b, 0x6c, 0x08}; // Left Margin
    public static final byte[] BREAK_LINE = {0x0A};

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex > 0) { /* We have only one page, and 'page' is zero-based */
            return NO_SUCH_PAGE;
        }

        /*
         * User (0,0) is typically outside the imageable area, so we must
         * translate by the X and Y values in the PageFormat to avoid clipping
         */
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        /* Now we perform our rendering */

        graphics.setFont(new Font("Roman", 0, 8));
        graphics.drawString("Hello world !", 0, 10);

        return PAGE_EXISTS;

    }
    public List<String> getPrinters(){

        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();

        PrintService printServices[] = PrintServiceLookup.lookupPrintServices(
                flavor, pras);

        List<String> printerList = new ArrayList<String>();
        for(PrintService printerService: printServices){
            printerList.add( printerService.getName());
        }

        return printerList;
    }
    public void printString(String printerName, String text) {

        // find the printService of name printerName
        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();

        PrintService printService[] = PrintServiceLookup.lookupPrintServices(
                flavor, pras);
        PrintService service = findPrintService(printerName, printService);

        DocPrintJob job = service.createPrintJob();

        try {

            byte[] bytes;

            // important for umlaut chars
            bytes = text.getBytes("CP437");

            Doc doc = new SimpleDoc(bytes, flavor, null);


            job.print(doc, null);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void printBytes(String printerName, byte[] bytes) {

        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();

        PrintService printService[] = PrintServiceLookup.lookupPrintServices(
                flavor, pras);
        PrintService service = findPrintService(printerName, printService);

        DocPrintJob job = service.createPrintJob();

        try {

            Doc doc = new SimpleDoc(bytes, flavor, null);

            job.print(doc, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static PrintService findPrintService(String printerName,
                                                 PrintService[] services) {
        for (PrintService service : services) {
            if (service.getName().equalsIgnoreCase(printerName)) {
                return service;
            }
        }

        return null;
    }


}
