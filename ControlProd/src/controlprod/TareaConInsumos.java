/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlprod;

import java.util.Date;

/**
 *
 * @author User
 */
public class TareaConInsumos extends Tarea {
    private Insumo insumo1;
    private Insumo insumo2;
    private int cantidadInsumo1;
    private int cantidadInsumo2;
    
    
    
    public TareaConInsumos(int numeroOrden, String descripcion, Date fechaInicio, Date fechaCierre, boolean completada, String usuario, Insumo insumo1,int cantInsumo1,Insumo insumo2, int cantInsumo2) {
        super(numeroOrden, descripcion, fechaInicio, fechaCierre, completada, usuario);
        this.insumo1 = insumo1;
        this.cantidadInsumo1 = cantInsumo1;
        this.insumo2 = insumo2;
        this.cantidadInsumo2 = cantInsumo2;
        
    }

    public Insumo getInsumo1() {
        return insumo1;
    }

    public void setInsumo1(Insumo insumo1) {
        this.insumo1 = insumo1;
    }

    public Insumo getInsumo2() {
        return insumo2;
    }

    public void setInsumo2(Insumo insumo2) {
        this.insumo2 = insumo2;
    }

    public int getCantidadInsumo1() {
        return cantidadInsumo1;
    }

    public void setCantidadInsumo1(int cantidadInsumo1) {
        this.cantidadInsumo1 = cantidadInsumo1;
    }

    public int getCantidadInsumo2() {
        return cantidadInsumo2;
    }

    public void setCantidadInsumo2(int cantidadInsumo2) {
        this.cantidadInsumo2 = cantidadInsumo2;
    }
    
    
    
}
