/*
 * Representa a un jugador, identificado por el nombre y sus cartas de la mano
 * Estructura mano: se utilizará un TAD adecuado
 * Funcionalidad: Añadir carta a la mano (añadir la carta de foma que queden 
 * ordenadas de menor a mayor por su número), convertir a String el objeto Jugador (toString)
 */
package gal.uvigo.esei.aed1.Toma6.core;

import java.util.List;
import java.util.ArrayList;

public class Jugador {

    private List<Carta> mano;
    private List <Carta> monton; 
    private String nombre;

    public Jugador(String nombre) {
        this.mano = new ArrayList<>();
        this.monton = new ArrayList<>();
        this.nombre = nombre;
    }
    public void addMonton(Carta nueva){
        monton.add(nueva);
    }
    public int getNumBueyes(){
        int numBueyes = 0;
        for(Carta carta: monton){
            numBueyes += carta.getNumBueyes();
        }
        return numBueyes;
    }

    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica this ao añadir unha carta a mano do xogador e lanza unha
     * ArrayOutOfBoundsException en caso de que o xgador xa teña 10 cartas
     *
     * @param carta :Carta que se desexa introducir na mano do xogador
     */
    public void introducirCarta(Carta carta) {
        boolean introducida = false;
        int fin, inicio, medio;
        //comproba que estea cheo
        if (mano.size() == 10) {
            throw new ArrayIndexOutOfBoundsException("O xogador non debe ter máis de 10 cartas");
        }
        //comproba se está valeiro
        if (mano.isEmpty()) {
            mano.add(0, carta);
            introducida = true;
            //algoritmo de ordenación binario que elixe onde vai a carta
        } else {
            fin = mano.size() - 1;
            inicio = 0;
            //busqueda binaria
            while (!introducida) {
                medio = (fin + inicio) / 2;
                if (mano.get(medio).getNumCarta() > carta.getNumCarta()) {
                    fin = medio - 1;
                } else if (mano.get(medio).getNumCarta() < carta.getNumCarta()) {
                    inicio = medio + 1;
                } else {
                    mano.add(medio, carta);
                    introducida = true;
                }
                if (inicio > fin) {
                    mano.add(inicio, carta);
                    introducida = true;
                }
            }
        }
    }

    /**
     * modifica this ao quitarlle unha carta e se non existen cartas na mano
     * devolve null
     *
     * @param numCarta: Número da carta que se desexa sacar da baraja do xogador
     * @return Carta co número que se pediu e en caso de non atoparse ou de
     * estar a mano baleira null
     */
    public Carta sacarCarta(int numCarta) {
        //comproba se está valeiro
        if (mano.isEmpty()) {
            return null;
        }
        //algoritmo de ordenación binario que busca a carta
        int fin = mano.size() - 1;
        int inicio = 0;
        int medio;
        while (true) {
            medio = (fin + inicio) / 2;
            if (mano.get(medio).getNumCarta() > numCarta) {
                fin = medio - 1;
            } else if (mano.get(medio).getNumCarta() < numCarta) {
                inicio = medio + 1;
            } else {
                return mano.remove(medio);
            }
            if (inicio > fin) {
                return null;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("~-----------------------~\n").append("Nombre: ").append(nombre).append("\n         =Mano=\n").append(manoToString()).append("<_______________________>");
        return sb.toString();
    }

    public String manoToString() {
        StringBuilder toRet = new StringBuilder();
        for (Carta carta : mano) {
            toRet.append(carta.toString());
        }
        return toRet.toString();
    }
}
