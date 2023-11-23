package com.vg.sgca;

public class Ticket {

    private String asunto;
    private String detalles;
    private String estado; // Cambié EstadoTicket a String para simplificar

    public Ticket() {
        // Constructor vacío requerido para Firebase
    }

    public Ticket(String asunto, String detalles, String estado) {
        this.asunto = asunto;
        this.detalles = detalles;
        this.estado = estado;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }




}


