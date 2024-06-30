/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlprod;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatoFecha {
    public static final String FORMATO_FECHA = "dd-MM-yyyy";
    private static final SimpleDateFormat formatoFecha = new SimpleDateFormat(FORMATO_FECHA);
    
    
    public static String formatearFecha(Date date)
    {
        return formatoFecha.format(date);
    }
    
    public static Date convertirFecha(String fechaString) throws ParseException
    {
        return formatoFecha.parse(fechaString);
    }
}
