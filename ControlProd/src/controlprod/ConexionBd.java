
package controlprod;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class ConexionBd {
    
    private static final String DRIVER="com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String BD="controlprodbd";
    private static final String USER = "root";
    private static final String PASSWORD ="";
    
    // Almacena la conexión
    public Connection cadena;
    //Instancia única de la clase
    public static ConexionBd instancia;
   // Metodo privado para evitar que se creen multiples instancias de la clase 
   private ConexionBd()
   {
       this.cadena=null;
   }
   // Metodo para conectar con la BD
   public Connection conectarBd () throws SQLException
   {
       try
       {
           //Llama al driver de conexion
           Class.forName(DRIVER);
           //Establece la conexion
           this.cadena=DriverManager.getConnection(URL+BD,USER,PASSWORD);
       }catch(ClassNotFoundException | SQLException e)
       {
           e.printStackTrace();
           throw new SQLException("Error al conectar con la BD: " + BD + "Error: " +e);       
       }
       return cadena;
   }
   // Metodo para cerrar la conexión con la BD
   public void desconectarBd() throws SQLException
   {
       try
       {
           this.cadena.close();
       } catch (SQLException e)
       {
           e.printStackTrace();
           throw new SQLException("Error al cerrar la conexión con la BD: "+BD+"Error: " +e);
       }
   }
   
   // Metodo para mantener una única instancia de la clase en el sistema
    public synchronized static ConexionBd getInstancia()
    {
        if(instancia==null)
        {
            instancia=new ConexionBd();
        }
        return instancia;
    }
    
}
