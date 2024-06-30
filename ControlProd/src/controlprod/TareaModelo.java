/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlprod;


import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class TareaModelo {
    
    
     private final ConexionBd con;
    private PreparedStatement ps; //Compila la sentencia SQL y la envia a la BD
    private ResultSet rs; //Almacena el resultado;
    
      public TareaModelo()
    {
        con = ConexionBd.getInstancia();
    }
//   
    
private List<Tarea> tareas = new ArrayList<>();
    
    
    public void agregarTarea(Tarea tarea) throws SQLException
    {
        String query = "insert into tarea(NumeroOrdenTarea,Descripcion,FechaInicio,FechaCierre,Completada,UsuarioResponsable,ordenTrabajoId) values (?,?,?,?,?,?,?)";
        try
        {
            ps = con.conectarBd().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, tarea.getNumeroOrden());
            ps.setString(2,tarea.getDescripcion());
            ps.setDate(3, new java.sql.Date(tarea.getFechaInicio().getTime()));
            if(tarea.getFechaCierre() != null)
            {
                ps.setDate(4,new java.sql.Date(tarea.getFechaCierre().getTime()));
            } else
            {
                ps.setNull(4, Types.DATE);
            }
            ps.setBoolean(5, tarea.isCompletada());
            ps.setString(6,tarea.getUsuario());
            ps.setInt(7, tarea.getOrdenTrabajoId());
            
            int filasAfectadas =        ps.executeUpdate();
            if(filasAfectadas == 0)
            {
                throw new SQLException ("Error al agregar la tarea");
            }
     
            try(ResultSet generatedKeys = ps.getGeneratedKeys())
            {
                if(generatedKeys.next())
                {
                    tarea.setId(generatedKeys.getInt(1));
                }else
                {
                    throw new SQLException("Error al agregar la tarea");
                }
            }
            
        }catch(SQLException e)
        {
            e.printStackTrace();
            throw new SQLException("Error al agregar la tarea");
        } finally
        {
            ps = null;
            con.desconectarBd();
        }
    }
    
     public void agregarTareaConInsumo(TareaConInsumos tarea) throws SQLException
    {
        String query = "insert into tarea(NumeroOrdenTarea,Descripcion,FechaInicio,FechaCierre,Completada,UsuarioResponsable,Insumo1id,CantidadInsumo1,Insumo2id,CantidadInsumo2,ordenTrabajoId) values (?,?,?,?,?,?,?,?,?,?,?)";
        try
        {
            ps = con.conectarBd().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
       
            ps.setInt(1, tarea.getNumeroOrden());
            ps.setString(2,tarea.getDescripcion());
            ps.setDate(3, new java.sql.Date(tarea.getFechaInicio().getTime()));
            if(tarea.getFechaCierre() != null)
            {
                ps.setDate(4,new java.sql.Date(tarea.getFechaCierre().getTime()));
            } else
            {
                ps.setNull(4, Types.DATE);
            }
            ps.setBoolean(5, tarea.isCompletada());
            ps.setString(6,tarea.getUsuario());
            ps.setInt(7,tarea.getInsumo1().getId());
            ps.setInt(8,tarea.getCantidadInsumo1());
            ps.setInt(9,tarea.getInsumo2().getId());
            ps.setInt(10,tarea.getCantidadInsumo2());
            ps.setInt(11,  tarea.getOrdenTrabajoId());
            ps.executeUpdate();
            try(ResultSet generatedKeys = ps.getGeneratedKeys())
            {
                if(generatedKeys.next())
                {
                    tarea.setId(generatedKeys.getInt(1));
                }
            }
            
        }catch(SQLException e)
        {
            e.printStackTrace();
            throw new SQLException("Error al agregar la tarea");
        } finally
        {
            ps = null;
            con.desconectarBd();
        }
    }
    
    public List<Tarea> obtenerTareasPorOrdenTrabajo(int ordenTrabajoId) throws SQLException
    {
        String query = "select * from tarea where ordenTrabajoId = ?";
        List<Tarea> tareas = new ArrayList<>();

        try
        {
            ps = con.conectarBd().prepareStatement(query);
            ps.setInt(1, ordenTrabajoId);
            rs = ps.executeQuery();
                    
           while(rs.next())
                        {
                                    Tarea tarea = new Tarea(
                                    rs.getInt("numeroOrdenTarea"),
                                    rs.getString("Descripcion"),
                                    rs.getDate("fechaInicio"),
                                    rs.getDate("fechacierre"),
                                    rs.getBoolean("completada"),
                                    rs.getString("usuarioresponsable")
                            );
                            tarea.setId(rs.getInt("id"));
                            tarea.setOrdenTrabajoId(rs.getInt("ordenTrabajoId"));
                            tareas.add(tarea);
                        }
                    
         }catch(SQLException e)
        {
            e.printStackTrace();
            throw new SQLException ("Error al obtener la tarea seleccionada");
        } finally
        {
            ps = null;
            rs = null;
            con.desconectarBd();
        }
         return tareas;
    }
    
     public List<Tarea> obtenerTareaPorId(int ordenTrabajoId) throws SQLException {
        String query = "select * from Tarea where id = ?";
        List<Tarea> tareas = new ArrayList<>();
        
        try
        {
            ps = con.conectarBd().prepareStatement(query);
            ps.setInt(1,ordenTrabajoId);
            rs = ps.executeQuery();
            if(rs.next())
            {
                Tarea tarea = new Tarea(
                rs.getInt("numeroOrden"),
                        rs.getString("descripcion"),
                        rs.getDate("fechainicio"),
                        rs.getDate("fechacierre"),
                        rs.getBoolean("completada"),
                        rs.getString("usuarioResponsable")
                );
                tarea.setId(rs.getInt("id"));
                tarea.setOrdenTrabajoId(rs.getInt("ordenTrabajoId"));
                tareas.add(tarea);
            }
            
        }catch(SQLException e)
                    {
                    e.printStackTrace();
                    throw new SQLException ("Error al obtener la tarea con Id: " + ordenTrabajoId);
                    } finally
        {
            ps=null;
            rs=null;
            con.desconectarBd();
        }
        return tareas;
     }

    
    public void actualizarTarea(int id,String usuarioResponsable) throws SQLException
    {
        String query = "update tarea set completada=true, fechacierre=?, usuarioresponsable=? where id=?";
        
        try
        {
            ps = con.conectarBd().prepareStatement(query);
             ps.setDate(1,new java.sql.Date(System.currentTimeMillis()));
             ps.setString(2, usuarioResponsable);
             ps.setInt(3, id);
             ps.executeUpdate();
             
        }catch(SQLException e)
        {
            e.printStackTrace();
            throw new SQLException("Error al cerrar la Tarea");
        }
    }
    }
     