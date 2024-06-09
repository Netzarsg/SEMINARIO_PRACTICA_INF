
package controlprod;

import java.util.Date;

public class Tarea extends Entidad {
    
    private int numeroOrden;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaCierre;
    private boolean completada;
    private String usuario;

    public Tarea( int numeroOrden, String descripcion, Date fechaInicio, Date fechaCierre, boolean completada, String usuario) {
        super(); // Llama al constructor de Entidad para asignar un Id incremental
        this.numeroOrden = numeroOrden;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaCierre = fechaCierre;
        this.completada = completada;
        this.usuario = usuario;
    }

    public int getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(int numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierra(Date fechaCierra) {
        this.fechaCierre = fechaCierre;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    
    
    
}


