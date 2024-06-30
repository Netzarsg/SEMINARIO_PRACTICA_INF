/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlprod;

import java.util.HashMap;
import java.util.Map;

public class PermisoRol {
    
    // En este Map se guardan los permisos que posee cada rol, se utiliza la clave para los nombres de rol, y un arreglo para los tipos de permiso
    private static final Map<String,String[]> PERMISO_POR_ROL = new HashMap<>();
    
    // En este bloque se definen e inicializan los permisos por rol
    static{
                    PERMISO_POR_ROL.put("admin", new String[] {"crearOrdenTrabajo","agregarTarea","completarTarea","completarOrden" });
                    PERMISO_POR_ROL.put("supervisor", new String[]{"crearOrdenTrabajo","agregarTarea","completarTarea"});
                    PERMISO_POR_ROL.put("operario", new String[]{"agregarTarea"});
            }
    
    
    // Este método verifica si un Usuario tiene permisos
    public static boolean tienePermiso(Usuario usuario, String permiso)
    {
        //Obtiene los permisos del rol que posee el usuario
        String[] permisos = PERMISO_POR_ROL.get(usuario.getRol());
      
        if(permisos == null)   // Si no tiene rol devuelve false
        {
            return false;
        }
        for (String p : permisos)
        {
            if(p.equals(permiso)) // Verifica si el permiso está en la lista de permisos por rol
            {
                return true;
            }
        }
        return false; // Si no está en la lista devuelve falso
    }
}
