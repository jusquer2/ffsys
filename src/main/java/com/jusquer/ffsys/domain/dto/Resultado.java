package com.jusquer.ffsys.domain.dto;

import java.util.List;

public class Resultado {
    private String codigoError;
    private String mensajeError;
    private Boolean error;
    private List<?> lstResultado;

    public String getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(String codigoError) {
        this.codigoError = codigoError;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<?> getLstResultado() {
        return lstResultado;
    }

    public void setLstResultado(List<?> lstResultado) {
        this.lstResultado = lstResultado;
    }
}
