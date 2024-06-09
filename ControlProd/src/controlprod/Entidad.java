/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlprod;

/**
 *
 * @author User
 */
public abstract class Entidad {
    private static int idCounter = 1;
    protected int id;
    

    
    public Entidad()
    {
        this.id=idCounter++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
