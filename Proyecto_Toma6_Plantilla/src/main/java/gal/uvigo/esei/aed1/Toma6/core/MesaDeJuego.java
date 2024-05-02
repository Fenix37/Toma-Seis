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

    public static final int NUM_FILAS_MESA = 4;
    private List<Carta>[] cartasEnMesa;

    public MesaDeJuego() {
        cartasEnMesa = new List[NUM_FILAS_MESA];
        for (int i = 0; i < cartasEnMesa.length; i++) {
            cartasEnMesa[i] = new ArrayList<>();
        }
    }

    /**
     * Inserta una nueva carta a una de las listas según que fila es
     * seleccionada
     *
     * @param nueva Carta a insertar en la mesa
     * @param fila Fila donde se inserta la carta
     */
    public void insertarCarta(Carta nueva, int fila) {
        if (fila < 0 || fila >= NUM_FILAS_MESA) {
            throw new IllegalArgumentException("La fila introducida no existe.");
        }
        cartasEnMesa[fila].add(nueva);
    }

    /**
     *
     * @param nueva Carta nueva a insertar en la mesa
     * @return "-2" si no se puede insertar la carta porque ya hay 5 cartas en
     * la fila donde corresponde, y "-1" porque es menor a todas las últimas
     * cartas de las listas. "1" si se pudo insertar la carta
     */
    public int insertarCarta(Carta nueva) {
        int fila = filaMenor(nueva.getNumCarta());
        if (fila == -1) {
            return -1; // La carta no puede ser jugada ya que es menor a todas las finales de la mesa.
        }
        if (cartasEnMesa[fila].size() == 5) {
            return -2; // La carta no puede ser jugada ya que su respectiva fila está llena.
        }
        cartasEnMesa[fila].add(nueva); // Insertar la carta en la fila correspondiente de la mesa
        return 1; // La inserción fue exitosa
    }
    
    /**
     * 
     * @param fila
     * @param nueva
     * @return 
     */
    public List<Carta> vaciarFila(int fila, Carta nueva){
        if(fila < 0 || fila > NUM_FILAS_MESA-1){
            throw new IllegalArgumentException("La fila introducida es incorrecta");
        }
        List<Carta> cartas = new ArrayList<>();
        int numCartas = cartasEnMesa[fila].size();
        for(int i = 0; i < numCartas; i++){
            cartas.add(cartasEnMesa[fila].removeLast());
        }
        cartasEnMesa[fila].add(nueva);
        return cartas;
    }
    /*
    *Calcula la fila a la que pertenece el número de carta que se le pasa.
    *Si no tiene fila correspondiente devuelve -1
    *@param Número de la carta de la que se busca calcular la fila
    *@return Fila a la que pertenece el número pasado, si no pertenece a ninguna se devuelve -1
     */
    public int filaMenor(int numCarta) {
        int menorDiferencia = 105;
        int fila = -1;
        for (int i = 0; i < NUM_FILAS_MESA; i++) {
            if (cartasEnMesa[i].get(cartasEnMesa[i].size() - 1).getNumCarta() < numCarta && (numCarta - cartasEnMesa[i].get(cartasEnMesa[i].size() - 1).getNumCarta()) < menorDiferencia) {
                fila = i;
                menorDiferencia = numCarta - cartasEnMesa[i].get(cartasEnMesa[i].size() - 1).getNumCarta();
            }
        }
        return fila;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[Numero de carta|Numero de bueyes]\n");
        for (int i = 0; i < NUM_FILAS_MESA; i++) {
            sb.append("\n\n");
            for (Carta carta : cartasEnMesa[i]) {
                sb.append("[").append(carta.getNumCarta()).append("|").append(carta.getNumBueyes()).append("] ");
            }
        }

        return sb.toString();
    }

}
