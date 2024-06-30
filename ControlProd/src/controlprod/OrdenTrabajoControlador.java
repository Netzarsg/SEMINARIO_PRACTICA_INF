/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlprod;

import java.sql.SQLException;
import controlprod.Excepciones.OrdenTrabajoNotFoundException;
import controlprod.Excepciones.PermisoException;
import controlprod.Excepciones.TareaNotFoundException;
import java.text.ParseException;
import java.util.List;


public class OrdenTrabajoControlador {
    
    
    // ORDENES DE TRABAJO //
    private OrdenTrabajoModelo ordenTrabajoModelo = new OrdenTrabajoModelo();
    private TareaModelo tareaModelo = new TareaModelo();
    private UsuarioModelo usuarioModelo = new UsuarioModelo();
    private InsumoModelo insumoModelo = new InsumoModelo();
    
    
    /*---------------CONTROLADORES ORDENTRABAJO---------------------*/
    
    public void crearOrdenTrabajo(OrdenTrabajo ordenTrabajo, Usuario usuario) throws SQLException, ParseException, PermisoException
    {
        if(PermisoRol.tienePermiso(usuario, "crearOrdenTrabajo"))
        {
                 ordenTrabajoModelo.agregarOrdenTrabajo (ordenTrabajo);
        } else
        {
            throw new PermisoException("El usuario no tiene permiso para crear una orden de trabajo");
        }
   
    }
    
     public List<OrdenTrabajo> listarOrdenes() throws SQLException
    {
        return ordenTrabajoModelo.obtenerTodasOrdenes();
    }
    
    public OrdenTrabajo buscarOrdenes(int id) throws SQLException
    {
        return ordenTrabajoModelo.obtenerOrdenPorId(id);
    }
    
    public void cerrarOrdenTrabajo(int IdOrdenTrabajo) throws OrdenTrabajoNotFoundException, SQLException
    {
    OrdenTrabajo ordenTrabajo = ordenTrabajoModelo.obtenerOrdenPorId(IdOrdenTrabajo);
    if(ordenTrabajo== null)
    {
        throw new OrdenTrabajoNotFoundException("Orden de Trabajo no encontrada");
    }
    ordenTrabajoModelo.cerrarOrdenTrabajo(IdOrdenTrabajo, new java.sql.Date(System.currentTimeMillis()), "Cerrada");
        
    }
    
    /*---------------FIN CONTROLADORES ORDENTRABAJO---------------------*/
    
/*---------------CONTROLADORES TAREA---------------------*/
    
    public void agregarTareaAOrdenTrabajo(int ordenTrabajoId, Tarea tarea, Usuario usuario) throws OrdenTrabajoNotFoundException, SQLException, PermisoException
    {
        if(PermisoRol.tienePermiso(usuario, "agregarTarea"))
        {
             OrdenTrabajo ordenTrabajo = ordenTrabajoModelo.obtenerOrdenPorId(ordenTrabajoId);
        if(ordenTrabajo == null)
        {
        throw new OrdenTrabajoNotFoundException("Orden de Trabajo no encontrada: " + ordenTrabajoId);
        }
        tarea.setOrdenTrabajoId(ordenTrabajoId);
        tareaModelo.agregarTarea(tarea);
        }      else
    {
        throw new PermisoException ("El usuario no tiene permisos para agregar una Tarea");
    }
    }
    
    public void agregarTareaConInsumoAOrdenTrabajo(int ordenTrabajoId,TareaConInsumos tarea, Usuario usuario) throws SQLException, OrdenTrabajoNotFoundException,PermisoException
    {
        if(PermisoRol.tienePermiso(usuario, "agregarTarea"))
        {
            OrdenTrabajo ordenTrabajo = ordenTrabajoModelo.obtenerOrdenPorId(ordenTrabajoId);
            if(ordenTrabajo == null)
            {
                throw new OrdenTrabajoNotFoundException("Orden De Trabajo no encontrada");
            }
        tarea.setOrdenTrabajoId(ordenTrabajoId);
        insumoModelo.agregarInsumo(tarea.getInsumo1());
        insumoModelo.agregarInsumo(tarea.getInsumo2());
        tareaModelo.agregarTareaConInsumo(tarea);
        } else
        {
            throw new PermisoException("El usuario no tiene permisos para agregar una Tarea");
        }
       
    }
    
    public List<Tarea> obtenerTareasPorOrdenTrabajo(int ordenTrabajoId) throws SQLException
    {
        return tareaModelo.obtenerTareasPorOrdenTrabajo(ordenTrabajoId);
    }

    
    public void completarTarea(int idTarea, String usuarioResponsable, Usuario usuario) throws SQLException, TareaNotFoundException,PermisoException
    {
        if(PermisoRol.tienePermiso(usuario, "completarTarea"))
        {
            tareaModelo.actualizarTarea(idTarea, usuarioResponsable);
        }else
        {
            throw new PermisoException("El usuario no tiene permisos para completar una tarea");
        }
//    public void completarTarea(int idTarea, String usuarioResponsable, Usuario usuario) throws TareaNotFoundException, SQLException, PermisoException
//    {
//        if(PermisoRol.tienePermiso(usuario, "completarTarea"))
//        {
//               Tarea tarea = tareaModelo.obtenerTareaPorId(idTarea);
//        if(tarea==null)
//        {
//            throw new TareaNotFoundException("Tarea No encontrada: " + idTarea);
//        }
//        tarea.setCompletada(true);
//        tarea.setUsuario(usuarioResponsable);  
//        tarea.setFechaCierra(new java.sql.Date(System.currentTimeMillis()));
//        tareaModelo.actualizarTarea(tarea);
//        }else
//        {
//            throw new PermisoException("El usuario no tiene permisos para completar  una Tarea");
//        }
//     
    }
        /*--------------- FIN CONTROLADORES TAREA---------------------*/
        
        /*---------------CONTROLADORES INSUMO---------------------*/
        
        public void agregarInsumo(Insumo insumo) throws SQLException
        {
            insumoModelo.agregarInsumo(insumo);
        }
        
        public List<Insumo> listarInsumos() throws SQLException
        {
          return  insumoModelo.ObtenerTodosinsumos();
        }

        /*---------------FIN CONTROLADORES INSUMO---------------------*/
        
            /*---------------CONTROLADORES USUARIO---------------------*/
        public void agregarUsuario(Usuario usuario) throws SQLException
        {
            usuarioModelo.agregarUsuario(usuario);
        }
        
        public void eliminarUsuario(int id) throws SQLException
        {
            usuarioModelo.eliminarUsuario(id);
        }
        
        public Usuario obtenerUsuarioPorNombre(String nombreUsuario) throws SQLException
        {
            return usuarioModelo.obtenerUsuarioPorNombre(nombreUsuario);
        }
        
        public List<Usuario> listarUsuarios() throws SQLException
        {
            return usuarioModelo.obtenerTodosUsuarios();
        }
        
            /*---------------FIN CONTROLADORES USUARIO---------------------*/
    }
    
    

