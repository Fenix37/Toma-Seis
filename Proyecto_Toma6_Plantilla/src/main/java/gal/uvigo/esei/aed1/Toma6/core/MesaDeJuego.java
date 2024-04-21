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

    /**Inserta una nueva carta a una de las listas según que fila es seleccionada
     *
     * @param nueva Carta a insertar en la mesa
     * @param fila Fila donde se inserta la carta
     */
    public void insertarCarta(Carta nueva, int fila){
        if(fila < 0 ||fila > cartasEnMesa.length){
            throw new IllegalArgumentException("La fila introducida no existe.");
        }
        cartasEnMesa[fila].add(nueva);
    }

    /**
     *
     * @param nueva Cartqa nueva a insertar en la mesa
     * @param nombre Nombre del jugador al que le pertenece la carta que se trata de insertar
     * @return false si no se puede insertar la carta, ya sea porque ya hay 5 cartas en la fila
     * donde corresponde o porque sea menor a todas las últimas cartas de las listas.
     * true si se pudo insertar la carta
     */
    public boolean insertarCarta(Carta nueva, String nombre){
        int fila = filaMenor(nueva.getNumCarta());
        if(fila == -1){
            System.out.println("La carta de " + nombre + " no puede ser jugada ya que es menor a todas las finales de la mesa.");
            return false;
        }
        if(cartasEnMesa[fila].size() == 5){
            System.out.println("La carta de " + nombre + " no puede ser jugada ya que su respectiva fila está llena.");
            return false;
        }
        insertarCarta(nueva, fila);
        return true;
    }
    /*
    *Calcula la fila a la que pertenece el número de carta que se le pasa.
    *Si no tiene fila correspondiente devuelve -1
    *@param Número de la carta de la que se busca calcular la fila
    *@return Fila a la que pertenece el número pasado, si no pertenece a ninguna se devuelve -1
    */
    private int filaMenor(int numCarta){
        int menorDiferencia = 105;
        int fila = -1;
        for(int i = 0; i < cartasEnMesa.length; i++){
            if(cartasEnMesa[i].get(cartasEnMesa[i].size()-1).getNumCarta() < numCarta && (numCarta - cartasEnMesa[i].get(cartasEnMesa[i].size()-1).getNumCarta()) < menorDiferencia){
                fila = i;
                menorDiferencia = numCarta - cartasEnMesa[i].get(cartasEnMesa[i].size()-1).getNumCarta();
            }
        }
        return fila;
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
