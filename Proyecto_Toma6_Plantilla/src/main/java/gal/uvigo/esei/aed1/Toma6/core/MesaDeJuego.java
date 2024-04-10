/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gal.uvigo.esei.aed1.Toma6.core;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aragu
 */
public class MesaDeJuego {
    private List <Carta>[]cartasEnMesa;
    
    public MesaDeJuego(){
        cartasEnMesa = new List[4];
        for (int i = 0; i < cartasEnMesa.length; i++) {
            cartasEnMesa [i] = new ArrayList<>();
        }
    }
    
}
