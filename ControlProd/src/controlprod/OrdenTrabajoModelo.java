/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlprod;
import java.util.ArrayList;
import java.util.List;
import controlprod.Excepciones.OrdenTrabajoNotFoundException;
import java.sql.*;

public class OrdenTrabajoModelo {

    private final ConexionBd con;
    private PreparedStatement ps; //Compila la sentencia SQL y la envia a la BD
    private ResultSet rs; //Almacena el resultado;

    public OrdenTrabajoModelo() {
        con = ConexionBd.getInstancia();
    }
    private List<OrdenTrabajo> ordenesTrabajo = new ArrayList<>();

    public void agregarOrdenTrabajo(OrdenTrabajo ordenTrabajo) throws SQLException {
        String query = "insert into ordentrabajo(NumeroOrden,Descripcion,FechaInicio,FechaFin,Usuario,Estado) values(?,?,?,?,?,?)";

        try {
            ps = con.conectarBd().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, ordenTrabajo.getNumeroOrden());
            ps.setString(2, ordenTrabajo.getDescripcion());
            ps.setDate(3, new java.sql.Date(ordenTrabajo.getFechaInicio().getTime()));
            if (ordenTrabajo.getFechaCierre() != null) {
                ps.setDate(4, new java.sql.Date(ordenTrabajo.getFechaCierre().getTime()));
            } else {
                ps.setNull(4, java.sql.Types.DATE);
            }
            ps.setString(5, ordenTrabajo.getUsuario());
            ps.setString(6, ordenTrabajo.getEstado());

            ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    ordenTrabajo.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error al agregar la orden de trabajo");
        } finally {
            ps = null;
            con.desconectarBd();
        }

    }

    public OrdenTrabajo obtenerOrdenPorId(int id) throws SQLException {
        String query = "select * from OrdenTrabajo where id= ?";
        OrdenTrabajo ordenTrabajo = null;
        try {
            ps = con.conectarBd().prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                ordenTrabajo = new OrdenTrabajo(
                        rs.getInt("numeroOrden"),
                        rs.getString("descripcion"),
                        rs.getDate("fechainicio"),
                        rs.getDate("FechaFin"),
                        rs.getString("Usuario"),
                        rs.getString("estado")
                );
                ordenTrabajo.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error al obtener la orden de trabajo seleccionada");
        }
        return ordenTrabajo;
    }

    public List<OrdenTrabajo> obtenerTodasOrdenes() throws SQLException
    {
        String query = "select * from OrdenTrabajo";
        List<OrdenTrabajo> ordenesTrabajo = new ArrayList<>();

        try {
            ps = con.conectarBd().prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                OrdenTrabajo ordenTrabajo = new OrdenTrabajo(
                        rs.getInt("numeroOrden"),
                        rs.getString("descripcion"),
                        rs.getDate("fechainicio"),
                        rs.getDate("FechaFin"),
                        rs.getString("Usuario"),
                        rs.getString("estado")
                );
                ordenTrabajo.setId(rs.getInt("id"));
                ordenesTrabajo.add(ordenTrabajo);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error al intentar listar las ordenes de trabajo");
        } finally
        {
            ps = null;
            rs = null;
            con.desconectarBd();
        }  
          return ordenesTrabajo;
              
        
    }
        public void cerrarOrdenTrabajo(int ordenTrabajoId, Date fechaFin, String Estado) throws OrdenTrabajoNotFoundException, SQLException
        {
            String estado = "cerrada";
            String query = "update OrdenTrabajo set FechaFin=?, Estado=? where id=?";
            try
            {
                ps = con.conectarBd().prepareStatement(query);
                ps.setDate(1,fechaFin);
                ps.setString(2, estado);
                ps.setInt(3, ordenTrabajoId);
                
                ps.executeUpdate();
                
            }catch (SQLException e)
            {
                        throw new SQLException("Error al intentar cerrar la Orden de Trabajo");
            }finally
            {
                ps=null;
                con.desconectarBd();
            }
        }
    }

   
       
    

