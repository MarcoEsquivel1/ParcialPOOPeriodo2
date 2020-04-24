/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ordenar;


import Entidades.Relaciones;
import java.util.Comparator;

/**
 *
 * @author Marco René Esquivel Juárez
 */
public class OrdFeAsc implements Comparator<Relaciones>{
    @Override
    public int compare(Relaciones p1, Relaciones p2) {
        return p1.getVenta().getFecha().compareTo(p2.getVenta().getFecha());
        
    }
    
}
