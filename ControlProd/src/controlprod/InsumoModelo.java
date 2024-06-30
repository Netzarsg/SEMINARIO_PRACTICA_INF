/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlprod;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class InsumoModelo {

    private ConexionBd con;
    private PreparedStatement ps;
    private ResultSet rs;

          public InsumoModelo()
    {
        con = ConexionBd.getInstancia();
    }
          
    public void agregarInsumo(Insumo insumo) throws SQLException {
        String query = "Insert into Insumo (nombre) values (?)";

        try {
            ps = con.conectarBd().prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, insumo.getNombre());
            ps.executeUpdate();
            try(ResultSet generatedKeys = ps.getGeneratedKeys())
            {
                if(generatedKeys.next())
                {
                    insumo.setId(generatedKeys.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Error al agregar un nuevo insumo: " + e.getMessage());
        } finally {
            ps = null;
            con.desconectarBd();
        }
    }
    
    public List<Insumo> ObtenerTodosinsumos() throws SQLException
    {
        String query = "select * from Insumo";
        List<Insumo> insumos = new ArrayList<>();
        try
        {
            ps = con.conectarBd().prepareStatement(query);
            rs = ps.executeQuery(query);
            while(rs.next())
            {
                Insumo insumo = new Insumo(
    rs.getString("nombre")
                );
                insumo.setId(rs.getInt("id"));
                insumo.add(insumo);
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
            throw new SQLException("Error al listar los insumos: " + e.getMessage());
        }finally
        {
            ps = null;
            rs = null;
            con.desconectarBd();
        }
      return insumos;
    }  

}
