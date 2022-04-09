package com.jusquer.ffsys.domain.dto;

import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;

import java.util.Map;

public class ReporteDatosJasper {
    private Map<String, Object> parametros;
    private JRMapCollectionDataSource fieldsReporte;

    public Map<String, Object> getParametros() {
        return parametros;
    }

    public void setParametros(Map<String, Object> parametros) {
        this.parametros = parametros;
    }

    public JRMapCollectionDataSource getFieldsReporte() {
        return fieldsReporte;
    }

    public void setFieldsReporte(JRMapCollectionDataSource fieldsReporte) {
        this.fieldsReporte = fieldsReporte;
    }
}
