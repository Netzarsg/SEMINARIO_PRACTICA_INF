

package controlprod;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;


public class OrdenTrabajo extends Entidad {
    
    private int numeroOrden;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaCierre;
    private String usuario;
    private String estado;
    private List<Tarea> tareas;

    public OrdenTrabajo(int numeroOrden, String descripcion, Date fechaInicio, Date fechaCierre, String usuario, String estado) {
        super(); // Llama al constructor de Entidad para asignar un Id incremental
        this.numeroOrden = numeroOrden;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaCierre = fechaCierre;
        this.usuario = usuario;
        this.estado = estado;
        this.tareas = new ArrayList<>();
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

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Tarea> getTareas() {
        return tareas;
    }

    public void agregarTareas(Tarea tarea) {
       this.tareas.add(tarea);
    }
    
    
    
}
