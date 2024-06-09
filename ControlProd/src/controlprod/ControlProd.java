/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package controlprod;

import controlprod.Excepciones.OrdenTrabajoNotFoundException;
import controlprod.Excepciones.TareaNotFoundException;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;


public class ControlProd {

private OrdenTrabajoControlador controlador = new OrdenTrabajoControlador();
private Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        
        ControlProd controlProd = new ControlProd();
        controlProd.menuOpciones();
        
    }
        private void menuOpciones()
        {
            while(true)
            {
                System.out.println("-------------Menú de Opciones--------------");
                System.out.println("1. Crear una nueva order de trabajo");
                System.out.println("2. Agregar tareas a una orden de trabajo");
                System.out.println("3. Listar todas las ordenes de trabajo");
                System.out.println("4. Listar tareas");
                System.out.println("5. Completar tareas");
                System.out.println("6. Salir");
                System.out.println("Elija una opción:");
                int opcion = Integer.parseInt(sc.nextLine());
                
                try
                {
                    switch (opcion)
                    {
                        case 1:
                            crearOrdenTrabajo();
                            break;
                         
                        case 2:
                            agregarTareaAOrdenTrabajo();
                            break;
                            
                        case 3:
                            listarOrdenes();
                            break;
                            
                        case 4:
                            listarTareas();
                            break;
                            
                        case 5:
                            completarTareas();
                            break;
                            
                        case 6:
                            System.out.println("Saliendo de la aplicación..");
                            return;
                        default:
                            System.out.println("Seleccione una opción válida");
                            
                           
                           
                    
                    }
                }catch(Exception e)
                        {
                            System.out.println("Error:  " + e.getMessage());
                        }
                
                     
            }
        }
    
    private void crearOrdenTrabajo()
    {
        System.out.println("Ingrese el numero de Orden:");
        String inputNumeroOrden = sc.nextLine();
        if(inputNumeroOrden.isEmpty())
        {
            System.out.println("El número de Orden no puede estar vacío");
                    return;
        }
        int numeroOrden = Integer.parseInt(inputNumeroOrden);
        
        System.out.println("Ingrese la descripción");
        String descripcion = sc.nextLine();
         if(descripcion.isEmpty())
        {
            System.out.println("La descripción de la Orden no puede estar vacía");
                    return;
        }
         
        System.out.println("Ingrese la fecha de la orden (aaaa-mm-dd)");
        String  inputFechaInicio = sc.nextLine();
          if(inputFechaInicio.isEmpty())
        {
            System.out.println("La Fecha de inicio de la Orden no puede estar vacía");
                    return;
        }
          Date fechaInicio = Date.valueOf(inputFechaInicio);
        
        System.out.println("Ingrese la fecha de cierre de la orden (aaaa-mm-dd)");
        String  inputFechaCierre = sc.nextLine();
          if(inputFechaCierre.isEmpty())
        {
            System.out.println("La Fecha de Cierre de la Orden no puede estar vacía");
                    return;
        }
          Date fechaCierre = Date.valueOf(inputFechaCierre);
          
          
        System.out.println("Su nombre de Usuario");
        String usuario = sc.nextLine();
      
        if(usuario.isEmpty())
        {
            System.out.println("El nombre de usuario no puede estar vacío");
                    return;
        }
        
        
        String estado = "Abierta"; // Al crear una orden, setea el valor del estado de la orden como abierta 
        
        OrdenTrabajo ordenTrabajo = new OrdenTrabajo( numeroOrden,descripcion,fechaInicio,fechaCierre,usuario,estado);
        controlador.crearOrdenTrabajo(ordenTrabajo);
        System.out.println("Orden de Trabajo Creada");
        
    }
    
    
 private void agregarTareaAOrdenTrabajo() 
        {
            System.out.println("Ingrese el Id de la Orden de trabajo");
            int ordenTrabajoId = Integer.parseInt(sc.nextLine());
            System.out.println("Ingrese el numero de orden de la tarea");
            int numeroOrden = Integer.parseInt(sc.nextLine());
            System.out.println("Ingrese la descripcion de la tarea");
            String descripcion = sc.nextLine();
            System.out.println("Ingrese la fecha de incio de la tarea (aaaa-mm-dd)");
            Date fechaInicio = Date.valueOf(sc.nextLine());
            System.out.println("Ingrese la fecha de cierre de la tarea (aaaa-mm-dd)");
            Date fechaCierre = Date.valueOf(sc.nextLine());
            System.out.println("Ingrese el usuario");
            String usuario = sc.nextLine();
            
            boolean estado = false;
            
            Tarea tarea = new Tarea(numeroOrden,descripcion,fechaInicio,fechaCierre,estado,usuario);
            try
            {
                controlador.agregarTareaAOrdenTrabajo(ordenTrabajoId, tarea);
                System.out.println("Tarea agregada con éxito");
            } catch(OrdenTrabajoNotFoundException e)
            {
                System.out.println("Error: " + e.getMessage());
            }
            
        }
 
 private void listarOrdenes()
 {
     List<OrdenTrabajo> ordenesTrabajo = controlador.listarOrdenes();
     for(OrdenTrabajo ordenTrabajo : ordenesTrabajo)
     {
         System.out.println("\nOrden de trabajo ID: " + ordenTrabajo.getId());
         System.out.println("  Número de Orden: " + ordenTrabajo.getNumeroOrden());
         System.out.println("Descripción: " + ordenTrabajo.getDescripcion());
         System.out.println("Fecha de inicio: " + ordenTrabajo.getFechaInicio());
         System.out.println("Fecha de Fin: " + ordenTrabajo.getFechaCierre());
         System.out.println("Usuario: " + ordenTrabajo.getUsuario());
         System.out.println("Estado: " + ordenTrabajo.getEstado());
         System.out.println("------------------------ Tareas ------------------------");
         for (Tarea tarea : ordenTrabajo.getTareas())
         {
             System.out.println("   - Número de Orden: " + tarea.getNumeroOrden());
             System.out.println("   Descripción: " + tarea.getDescripcion());
             System.out.println("   Fecha de Inicio: " + tarea.getFechaInicio());
             System.out.println("   Fecha de Fin: " + tarea.getFechaCierre());
             System.out.println("   Usuario: " + tarea.getUsuario());
            if(tarea.isCompletada() == false)
            {
                System.out.println("Estado: Pendiente");
            }
            else
            {
                System.out.println("Estado: Cerrada");
            }

         }

         
     }
 }
 
 private void listarTareas() {
        List<Tarea> tareas = controlador.obtenerTodasTareas();
        for (Tarea tarea : tareas) {
            System.out.println("\nTarea ID: " + tarea.getId());
            System.out.println("  Número de orden: " + tarea.getNumeroOrden());
            System.out.println("  Descripción: " + tarea.getDescripcion());
            System.out.println("  Fecha de inicio: " + tarea.getFechaInicio());
            System.out.println("  Fecha de cierre: " + tarea.getFechaCierre());
              if(tarea.isCompletada() == false)
            {
                System.out.println("Estado: Pendiente");
            }
            else
            {
                System.out.println("Estado: Cerrada");
            }
            System.out.println("  Usuario que completó: " + tarea.getUsuario());
        }
    }
 
 private void completarTareas()
 {
       System.out.println("Ingrese el Id de la tarea que desea Completar: ");
       int tareaId = Integer.parseInt(sc.nextLine());
       System.out.println("Ingrese el nombre de usuario");
       String usuario = sc.nextLine();
       
       try
       {
           controlador.completarTarea(tareaId, usuario);
           System.out.println("Tarea Completada");
       }catch(TareaNotFoundException e)
       {
           System.out.println("Error: " + e.getMessage());
       }
 }


    
}
