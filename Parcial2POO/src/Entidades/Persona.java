/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author Marco René Esquivel Juárez
 */
public class Persona {
    private String nombre;
    private String apellido;
    private String DUI;
    
    public Persona(String pNom, String pApe, String pDUI){
        this.setNombre(pNom);
        this.setApellido(pApe);
        this.setDUI(pDUI);
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the DUI
     */
    public String getDUI() {
        return DUI;
    }

    /**
     * @param DUI the DUI to set
     */
    public void setDUI(String DUI) {
        this.DUI = DUI;
    }
}
