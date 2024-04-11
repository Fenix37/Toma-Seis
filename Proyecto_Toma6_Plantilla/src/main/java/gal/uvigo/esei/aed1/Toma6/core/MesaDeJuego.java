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
    public void insertarCarta(Carta nueva, int fila){
        cartasEnMesa[fila].add(nueva);
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[Numero de carta|Numero de bueyes]\n");
        for (int i = 0; i < 4; i++) {
            sb.append("\n\n");
            for (Carta carta : cartasEnMesa[i]) {
                sb.append("[").append(carta.getNumCarta()).append("|").append(carta.getNumBueyes()).append("] ");
            }
        }
        
        return sb.toString();
    }
    
}
