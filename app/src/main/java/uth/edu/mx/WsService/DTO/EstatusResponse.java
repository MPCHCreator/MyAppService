package uth.edu.mx.WsService.DTO;

public class EstatusResponse{
    private String estatus ="";
    private String mensaje ="";

    public EstatusResponse(){

    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String status) {
        this.estatus = estatus;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    }

