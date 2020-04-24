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
public class Relaciones {
    private Persona cliente;
    private Venta venta;

    public Relaciones(Persona pCli, Venta pVen){
        this.setCliente(pCli);
        this.setVenta(pVen);
    }
    /**
     * @return the cliente
     */
    public Persona getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Persona cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the venta
     */
    public Venta getVenta() {
        return venta;
    }

    /**
     * @param venta the venta to set
     */
    public void setVenta(Venta venta) {
        this.venta = venta;
    }
    
    
}
