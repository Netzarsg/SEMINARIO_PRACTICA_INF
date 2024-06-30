
package controlprod;

import controlprod.Excepciones.OrdenTrabajoNotFoundException;
import controlprod.Excepciones.PermisoException;
import controlprod.Excepciones.TareaNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.text.ParseException;
import java.util.Scanner;

public class ControlProd {

private OrdenTrabajoControlador controlador = new OrdenTrabajoControlador();
private InsumoModelo insumoModelo = new InsumoModelo();
private UsuarioModelo usuarioModelo = new UsuarioModelo();
private Scanner sc = new Scanner(System.in);
private Usuario usuarioSesion;
    
    public static void main(String[] args) throws SQLException, Exception {
               
        ControlProd controlProd = new ControlProd();
        controlProd.iniciarSesion();
        controlProd.menuOpciones();
        
    }
    
    /*------------------INICIO SESION--------------------------*/
    
     public void iniciarSesion()
     {
         while(usuarioSesion == null)
         {
             System.out.println("Ingrese el nombre de Usuario:");
             String nombreUsuario = sc.nextLine();
             System.out.println("Ingrese la contraseña: ");
             String contrasena = sc.nextLine();
             
             try
             {
                 Usuario usuario = usuarioModelo.obtenerUsuarioPorNombre(nombreUsuario);
                         {
                             if(usuario != null && usuario.getContrasena().equals(contrasena))
                             {
                                 usuarioSesion = usuario;
                                 System.out.println("Inicio Exitoso");
                             } else
                             {
                                 System.out.println("Nombre de Usuario o contraseña incorrectos.");
                             }
                         }
             }catch(SQLException e)
             {
                 e.printStackTrace();
                 System.out.println("Error al intentar iniciar sesión: " + e.getMessage());
             }
         }
     }
    
    /*----------------------FIN INICIO SESION------------------------*/
     
     private void volverAMenu()
     {
         System.out.println("--------------------------------------------------------");
         System.out.println("Presiona Enter para volver al Menú Principal");
         try
         {
             System.in.read();
         }catch(Exception e)
         {
             e.printStackTrace();
         }
     }
        private void menuOpciones() throws SQLException, Exception
        {
            while(true)
            {
                System.out.println("-------------Menú de Opciones--------------");
                System.out.println("1. Crear una nueva order de trabajo");
                System.out.println("2. Agregar tareas a una orden de trabajo");
                System.out.println("3. Agregar tareas con insumo");
                System.out.println("4. Listar todas las ordenes de trabajo");
                System.out.println("5. Listar tareas por ordenes de trabajo");
                System.out.println("6. Completar tareas");
                System.out.println("7. Cerrar Orden de Trabajo");
                System.out.println("8. Salir");
                System.out.println("Elija una opción:");
                int opcion = 0;
           
                
                try
                {
                     opcion = Integer.parseInt(sc.nextLine());
                }catch(NumberFormatException e)
                {
                    System.out.println("Opción invalida, ingresa un numero");
                    continue;
                }
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
                            agregarTareaConInsumosAOrdenTrabajo();
                            break;    
                        case 4 :
                            listarOrdenes();
                            break;
                            
                        case 5:
                            listarTareasPorOrdenesTrabajo();
                            break;
                            
                        case 6:
                            completarTarea();
                            break;
                            
                        case 7:
                            cerrarOrdenTrabajo();
                            break;

                        case 8:
                            System.out.println("Saliendo de la aplicación..");
                            return;
                        default:
                            System.out.println("Seleccione una opción válida");
                    }
                    volverAMenu();
                }catch(  OrdenTrabajoNotFoundException | TareaNotFoundException | ParseException | PermisoException e)
                        {
                            System.out.println("Error:  " + e.getMessage());
                            volverAMenu();
                        }
                
                     
            }
        }
    
        
        /*----------------------METODOS ORDENES TRABAJO ------------------------*/
    private void crearOrdenTrabajo() throws SQLException, Exception, OrdenTrabajoNotFoundException, SQLException, ParseException, PermisoException
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
         
        System.out.println("Ingrese la fecha de la orden (" + FormatoFecha.FORMATO_FECHA + "):");
        String  inputFechaInicio = sc.nextLine();
          if(inputFechaInicio.isEmpty())
        {
            throw new OrdenTrabajoNotFoundException("La fecha de inicio no puede estar vacía.");
        }
          java.util.Date fechaInicioUtil = FormatoFecha.convertirFecha(inputFechaInicio);
          java.util.Date fechaInicio = new java.sql.Date(fechaInicioUtil.getTime());

          
        System.out.println("Su nombre de Usuario");
        String usuario = sc.nextLine();
      
        if(usuario.isEmpty())
        {
            System.out.println("El nombre de usuario no puede estar vacío");
                    return;
        }
        
        
        String estado = "Abierta"; // Al crear una orden, setea el valor del estado de la orden como abierta 

        
        OrdenTrabajo ordenTrabajo = new OrdenTrabajo( numeroOrden,descripcion,fechaInicio,null,usuario,estado);
        controlador.crearOrdenTrabajo(ordenTrabajo,usuarioSesion);
        System.out.println("Orden de Trabajo Creada");
        
    }
    
     private void cerrarOrdenTrabajo() throws OrdenTrabajoNotFoundException
 {
     System.out.println("Ingrese el ID de la orden que desea cerrar");
     String inputOrdenTrabajoId = sc.nextLine();
     if(inputOrdenTrabajoId.isEmpty())
     {
         throw new OrdenTrabajoNotFoundException("El ID de la orden no puede estár vacío");
   
     }
         int ordenTrabajoId = Integer.parseInt(inputOrdenTrabajoId);
         try
         {
             controlador.cerrarOrdenTrabajo(ordenTrabajoId);
             System.out.println("Orden: " + ordenTrabajoId + " cerrada con éxito");
         }catch(SQLException | OrdenTrabajoNotFoundException e)
         {
             System.err.println("Error al intentar cerrar la Orden de Trabajo: " + e.getMessage());
         }

 }


 private void listarOrdenes()   throws SQLException
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

        /*---------------------- FIN METODOS ORDENES TRABAJO ------------------------*/
        
        /*----------------------METODOS TAREAS ------------------------*/
 private void agregarTareaAOrdenTrabajo() throws SQLException, Exception// OrdenTrabajoNotFoundException,TareaNotFoundException, ParseException,PermisoException
        {
            System.out.println("Ingrese el Id de la Orden de trabajo");
            int ordenTrabajoId = Integer.parseInt(sc.nextLine());
            System.out.println("Ingrese el numero de orden de la tarea");
            int numeroOrden = Integer.parseInt(sc.nextLine());
            System.out.println("Ingrese la descripcion de la tarea");
            String descripcion = sc.nextLine();
            System.out.println("Ingrese la fecha de incio de la tarea ("+ FormatoFecha.FORMATO_FECHA + ")");
            String inputFechaInicio = sc.nextLine();
            if(inputFechaInicio.isEmpty())
            {
                throw new TareaNotFoundException("La fecha inicio no puede estar vacía");
            }
            java.util.Date fechaInicioUtil = FormatoFecha.convertirFecha(inputFechaInicio);
            java.util.Date fechaInicio = new java.sql.Date(fechaInicioUtil.getTime());
            
//            System.out.println("Ingrese la fecha de cierre de la tarea (aaaa-mm-dd)");
//            Date fechaCierre = Date.valueOf(sc.nextLine());
            System.out.println("Ingrese el usuario");
            String usuario = sc.nextLine();
            
            boolean estado = false;
            
            Tarea tarea = new Tarea(numeroOrden,descripcion,fechaInicio,null,estado,usuario);
            try
            {
                controlador.agregarTareaAOrdenTrabajo(ordenTrabajoId, tarea,usuarioSesion);
                System.out.println("Tarea agregada con éxito");
            } catch(OrdenTrabajoNotFoundException e)
            {
                System.out.println("Error: " + e.getMessage());
            } 
        }
 
   private void agregarTareaConInsumosAOrdenTrabajo() throws SQLException, OrdenTrabajoNotFoundException, TareaNotFoundException, ParseException, PermisoException
   {
       System.out.println("Ingrese el ID de la Orden de Trabajo:");
       String inputOrdenTrabajoId = sc.nextLine();
       if(inputOrdenTrabajoId.isEmpty())
       {
           throw new OrdenTrabajoNotFoundException("La orden de trabajo no puede estar vacia");
       }
       int ordenTrabajoId = Integer.parseInt(inputOrdenTrabajoId);
       
        System.out.print("Ingrese el número de orden de la tarea: ");
        String inputNumeroOrden = sc.nextLine();
        if (inputNumeroOrden.isEmpty()) {
            throw new TareaNotFoundException("El número de orden de la tarea no puede ser nulo o vacío.");
        }
        int numeroOrden = Integer.parseInt(inputNumeroOrden);

        System.out.print("Ingrese la descripción de la tarea: ");
        String descripcion = sc.nextLine();
        if (descripcion.isEmpty()) {
            throw new TareaNotFoundException("La descripción de la tarea no puede ser nula o vacía.");
        }

        System.out.print("Ingrese la fecha de inicio de la tarea (" + FormatoFecha.FORMATO_FECHA + "): ");
        String inputFechaInicio = sc.nextLine();
        if (inputFechaInicio.isEmpty()) {
            throw new TareaNotFoundException("La fecha de inicio de la tarea no puede ser nula o vacía.");
        }
        java.util.Date fechaInicioUtil = FormatoFecha.convertirFecha(inputFechaInicio);
        java.sql.Date fechaInicio = new java.sql.Date(fechaInicioUtil.getTime());

        System.out.print("Ingrese el estado de la tarea (true/false): ");
        String inputCompletada = sc.nextLine();
        if (inputCompletada.isEmpty()) {
            throw new TareaNotFoundException("El estado de la tarea no puede ser nulo o vacío.");
        }
        boolean completada = Boolean.parseBoolean(inputCompletada);

        System.out.print("Ingrese el usuario que completó la tarea: ");
        String usuarioResponsable = sc.nextLine();
        if (usuarioResponsable.isEmpty()) {
            throw new TareaNotFoundException("El usuario que completó la tarea no puede ser nulo o vacío.");
        }

        System.out.print("Ingrese el nombre del primer insumo: ");
        String nombreInsumo1 = sc.nextLine();
        Insumo insumo1 = new Insumo(nombreInsumo1);
        insumoModelo.agregarInsumo(insumo1);

        System.out.print("Ingrese la cantidad del primer insumo: ");
        int cantidadInsumo1 = Integer.parseInt(sc.nextLine());

        System.out.print("Ingrese el nombre del segundo insumo: ");
        String nombreInsumo2 = sc.nextLine();
        Insumo insumo2 = new Insumo(nombreInsumo2);
        insumoModelo.agregarInsumo(insumo2);

        System.out.print("Ingrese la cantidad del segundo insumo: ");
        int cantidadInsumo2 = Integer.parseInt(sc.nextLine());

        TareaConInsumos tarea = new TareaConInsumos(numeroOrden, descripcion, fechaInicio, null, completada, usuarioResponsable, insumo1, cantidadInsumo1, insumo2, cantidadInsumo2);
        controlador.agregarTareaConInsumoAOrdenTrabajo(ordenTrabajoId,tarea,usuarioSesion);
        System.out.println("Tarea con insumos agregada con éxito a la orden de trabajo.");
       
   }
 private void listarTareasPorOrdenesTrabajo() throws SQLException, TareaNotFoundException, OrdenTrabajoNotFoundException
 {
     System.out.println("Ingrese el ID de la orden de trabajo: ");
     String inputIdOrdenTrabajo = sc.nextLine();
     if(inputIdOrdenTrabajo.isEmpty())
     {
         throw new OrdenTrabajoNotFoundException("Debe ingresar el ID de la orden de trabajo.");
     }
     int ordenTrabajoId = Integer.parseInt(inputIdOrdenTrabajo);
     
     try 
     {
            List<Tarea> tareas = controlador.obtenerTareasPorOrdenTrabajo(ordenTrabajoId);
            if(tareas.isEmpty()){
                System.out.println("No se encontraron tareas para la orden de trabajo con id: " + ordenTrabajoId);
            }
           for (Tarea tarea : tareas) {
            
            System.out.println("\nTarea ID: " + tarea.getId());
            System.out.println("  Número de orden: " + tarea.getNumeroOrden() );
            System.out.println("  Descripción: " + tarea.getDescripcion());
            System.out.println("  Fecha de inicio: " + FormatoFecha.formatearFecha(tarea.getFechaInicio()));
            System.out.println("  Fecha de cierre: " + (tarea.getFechaCierre() != null ? FormatoFecha.formatearFecha(tarea.getFechaCierre()) : "N/A"));
              if(tarea.isCompletada() == false)
            {
                System.out.println("Estado: Pendiente");
            }
            else
            {
                System.out.println("Estado: Cerrada");
            }
                System.out.println("  Usuario que completó: " + tarea.getUsuario());
                if(tarea instanceof TareaConInsumos)
                {
                    TareaConInsumos tareaConInsumos = (TareaConInsumos) tarea;
                    System.out.println("Insumo 1: " + tareaConInsumos.getInsumo1().getNombre() + " - Cantidad: " + tareaConInsumos.getCantidadInsumo1() + "Kgs / Lts");
                    System.out.println("Insumo 2: " + tareaConInsumos.getInsumo2().getNombre() + " - Cantidad: " + tareaConInsumos.getCantidadInsumo2() + "Kgs / Lts");
                }
        } 
     }catch(SQLException e)
     {
         e.printStackTrace();
         throw new TareaNotFoundException("Tareas no encontradas: " + e.getMessage());
     }
       
       
    }
 
 private void completarTarea() throws SQLException, TareaNotFoundException,PermisoException
 {
     System.out.println("Ingrese el ID de la tarea a completar");
     String inputTareaId = sc.nextLine();
     if(inputTareaId.isEmpty())
     {
         throw new TareaNotFoundException("El ID de la tarea no puede estar vacío");
     }
     int tareaId = Integer.parseInt(inputTareaId);
     
     System.out.println("Ingrese el nombre de usuario que completa la tarea");
     String usuarioResponsable = sc.nextLine();
     if(usuarioResponsable.isEmpty())
     {
         throw new TareaNotFoundException("Debe ingresar un usuario");
     }
     try
     {
         controlador.completarTarea(tareaId, usuarioResponsable, usuarioSesion);
         System.out.println("Tarea Completada con éxito");
     } catch(SQLException | TareaNotFoundException e)
     {
         throw new TareaNotFoundException("Error al completar la tarea: " + e.getMessage());
     }
 }

     /*--------------FIN Metodos para Tareas--------------*/

    
}
