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
public class OrdFeDes implements Comparator<Relaciones>{
    @Override
    public int compare(Relaciones p1, Relaciones p2) {
        return p2.getVenta().getFecha().compareTo(p1.getVenta().getFecha());
        
    }
    
}
