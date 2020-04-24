/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Marco René Esquivel Juárez
 */
public class Antialergico extends Farmaco{

    public Antialergico(String n, double p, Date c, String cod, int u){
        this.setTipo("Antialergico");
        this.setNombre(n);
        this.setPrecio(p);
        this.caducidad = c;
        this.setCod(cod);
        this.setUnidades(u);
    }
    
    @Override
    public void mostrar() {
        JOptionPane.showMessageDialog(null, "Se está mostrando el antialergico: "+ this.getNombre());
    }

    @Override
    public void comprar() {
        JOptionPane.showMessageDialog(null, "Se ha comprado el antialergico: "+ this.getNombre());
    }

    @Override
    public void infoFarmaco() {
        JOptionPane.showMessageDialog(null, "Alivia los efectos negativos de las alergias.");
    }

    @Override
    public Date caducidad() {
        return this.caducidad;
    }
    
}
