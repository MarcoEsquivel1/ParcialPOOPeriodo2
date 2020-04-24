/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ordenar;

import Entidades.Farmaco;
import java.util.Comparator;

/**
 *
 * @author Marco René Esquivel Juárez
 */
public class OrdPreAsc implements Comparator<Farmaco>{
    
    @Override
    public int compare(Farmaco p1, Farmaco p2) {
        return Double.compare(p1.getPrecio(), p2.getPrecio());
        
    }
}
