/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Marco René Esquivel Juárez
 */
public class Venta {
    private double monto;
    public ArrayList<Farmaco> lF;
    private Date fecha;
    
    public Venta(double pMon, ArrayList<Farmaco> pLF, Date pD){
        this.setMonto(pMon);
        this.lF = pLF;
        this.setFecha(pD);
    }

    /**
     * @return the monto
     */
    public double getMonto() {
        return monto;
    }

    /**
     * @param monto the monto to set
     */
    public void setMonto(double monto) {
        this.monto = monto;
    }


    /**
     * @return the lF
     */
    public ArrayList<Farmaco> getlF() {
        return lF;
    }
    /**
     * @param plF the lF to set
     */
    public void setlF(ArrayList<Farmaco> plF) {
        this.lF = plF;
    }
    
    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
