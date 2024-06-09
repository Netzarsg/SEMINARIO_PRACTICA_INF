/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlprod;

import java.util.ArrayList;
import java.util.List;
import controlprod.Excepciones.OrdenTrabajoNotFoundException;



public class OrdenTrabajoModelo {
    
    private List<OrdenTrabajo> ordenesTrabajo = new ArrayList<>();
    
    public void agregarOrdenTrabajo(OrdenTrabajo ordenTrabajo)
    {
        ordenesTrabajo.add(ordenTrabajo);
    }
    
    public OrdenTrabajo obtenerOrdenPorId(int id) throws OrdenTrabajoNotFoundException
    {
        for (OrdenTrabajo ordenTrabajo : ordenesTrabajo)
        {
            if(ordenTrabajo.getId() == id)
            {
                return ordenTrabajo;
            }
        }
        throw new OrdenTrabajoNotFoundException("Orden de Trabajo no encontradada: " + id );
    }
    
    public List<OrdenTrabajo> obtenerTodasOrdenes()
    {
        return ordenesTrabajo;
    }
}
