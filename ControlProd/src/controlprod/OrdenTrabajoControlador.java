/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlprod;

import controlprod.Excepciones.OrdenTrabajoNotFoundException;
import controlprod.Excepciones.TareaNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrdenTrabajoControlador {
    
    private OrdenTrabajoModelo ordenTrabajoModelo = new OrdenTrabajoModelo();
    private TareaModelo tareaModelo = new TareaModelo();
    
    public void crearOrdenTrabajo(OrdenTrabajo ordenTrabajo)
    {
        ordenTrabajoModelo.agregarOrdenTrabajo (ordenTrabajo);
    }
    
    public void agregarTareaAOrdenTrabajo(int ordenTrabajoId, Tarea tarea) throws OrdenTrabajoNotFoundException
    {
        OrdenTrabajo ordenTrabajo = ordenTrabajoModelo.obtenerOrdenPorId(ordenTrabajoId);
        ordenTrabajo.agregarTareas(tarea);
        tareaModelo.agregarTareas(tarea);
        
    }
    
    public List<OrdenTrabajo> listarOrdenes()
    {
        return ordenTrabajoModelo.obtenerTodasOrdenes();
    }
    
    public OrdenTrabajo buscarOrdenes(int id) throws OrdenTrabajoNotFoundException
    {
        return ordenTrabajoModelo.obtenerOrdenPorId(id);
    }
    
    public Tarea buscarTarea (int id) throws TareaNotFoundException
    {
        return tareaModelo.obtenerTareaPorId(id);
    }
    
    public List<Tarea> obtenerTodasTareas()
    {
        return tareaModelo.obtenerTodasTareas();
    }
    
    public void completarTarea(int idTarea, String usuario) throws TareaNotFoundException
    {
        Tarea tarea = tareaModelo.obtenerTareaPorId(idTarea);
        tarea.setCompletada(true);
        tarea.setUsuario(usuario);
        
    }
    }
    

