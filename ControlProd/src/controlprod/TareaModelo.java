/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlprod;

import controlprod.Excepciones.TareaNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class TareaModelo {
    
    private List<Tarea> tareas = new ArrayList<>();
    
    public void agregarTareas(Tarea tarea)
    {
        tareas.add(tarea);
    }
    
    public Tarea obtenerTareaPorId(int id) throws TareaNotFoundException
    {
        for (Tarea tarea : tareas)
        {
            if(tarea.getId()==id)
            {
                return tarea;
            }
        }
        throw new TareaNotFoundException("Tarea " + id + " no encontrada");
    }
    
    public List<Tarea> obtenerTodasTareas()
    {
        return tareas;
    }
    
}
