/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlprod;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioModelo {
   
    private final ConexionBd con;
    private PreparedStatement ps; //Compila la sentencia SQL y la envia a la BD
    private ResultSet rs; //Almacena el resultado;
    
      public UsuarioModelo()
    {
        con = ConexionBd.getInstancia();
    }
    
     public void agregarUsuario(Usuario usuario) throws SQLException {
        String query = "INSERT INTO Usuario (nombreUsuario, contrasena, rol) VALUES (?, ?, ?)";
        try
        {
            ps = con.conectarBd().prepareStatement(query);
            ps.setString(1,usuario.getNombreUsuario());
            ps.setString(2,usuario.getContrasena());
            ps.setString(3, usuario.getRol());
            ps.executeUpdate();
        } catch(SQLException e)
        {
            e.printStackTrace();
            throw new SQLException("Error al agregar un nuevo usuario: " + e.getMessage());
        } finally
        {
            ps = null;
            con.desconectarBd();
        }
     }
        
        public List<Usuario> obtenerTodosUsuarios() throws SQLException
        {
         String query = "select * from usuario";
         List<Usuario> usuarios = new ArrayList<>();
         
         try
         {
             ps = con.conectarBd().prepareStatement(query);
             rs = ps.executeQuery(query);
             while(rs.next())
             {
                 Usuario usuario = new Usuario(
                 rs.getString("nombreusuario"),
                         rs.getString("contrasena"),
                         rs.getString("rol")
                 );
                 usuario.setId(rs.getInt("id"));
                 usuarios.add(usuario);
             }
         }catch(Exception e)
         {
             e.printStackTrace();
             throw new SQLException("Error al listar los usuarios");
         }finally
         {
             ps=null;
             rs=null;
             con.desconectarBd();
         }
         return usuarios;
        } 
        
        public Usuario obtenerUsuarioPorNombre(String nombreUsuario) throws SQLException
        {
         String query = "select * from usuario where nombreUsuario = ?";
         Usuario usuario = null;
         
         try
         {
             ps = con.conectarBd().prepareStatement(query);
             ps.setString(1,nombreUsuario);
             rs = ps.executeQuery();
             while(rs.next())
             {
                usuario = new Usuario(
                 rs.getString("nombreusuario"),
                         rs.getString("contrasena"),
                         rs.getString("rol")
                 );
                 usuario.setId(rs.getInt("id"));
          
             }
         }catch(Exception e)
         {
             e.printStackTrace();
             throw new SQLException("Error al listar los usuarios");
         }finally
         {
             ps=null;
             rs=null;
             con.desconectarBd();
         }
         return usuario;
        } 
        
        public void eliminarUsuario(int id) throws SQLException
        {
            String query = "delete from Usuario where id=?";
            try
            {
                ps = con.conectarBd().prepareStatement(query);
                ps.setInt(1, id);
                ps.executeUpdate();
                
            }catch(SQLException e)
            {
                e.printStackTrace();
                throw new SQLException ("Error al intentar eliminar el usuario: " + e.getMessage());
            }finally
            {
            ps = null;
            con.desconectarBd();
            }
        }
}
