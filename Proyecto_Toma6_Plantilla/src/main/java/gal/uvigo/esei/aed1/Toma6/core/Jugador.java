/*
 * Representa a un jugador, identificado por el nombre y sus cartas de la mano
 * Estructura mano: se utilizará un TAD adecuado
 * Funcionalidad: Añadir carta a la mano (añadir la carta de foma que queden 
 * ordenadas de menor a mayor por su número), convertir a String el objeto Jugador (toString)
 */
package gal.uvigo.esei.aed1.Toma6.core;

import java.util.List;
import java.util.ArrayList;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Jugador {

    private List<Carta> mano;
    private String nombre;

    public Jugador(String nombre) {
        this.mano = new ArrayList<>();
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
/**
 * 
 * @param carta :Carta que se desexa introducir na mano do xogador
 * Modifica this ao añadir unha carta a mano do xogador e lanza unha 
 * ArrayOutOfBoundsException en caso de que o xgador xa teña 10 cartas
 */
    public void IntroducirCarta(Carta carta) {
        boolean introducida = false;
        if(mano.size()==10){
            throw new ArrayIndexOutOfBoundsException("O xoador non debe ter máis de 10 cartas");
        }
        for (int i = 0; i < mano.size(); i++) {
            if (mano.get(i).getNumCarta() < carta.getNumCarta() && carta.getNumCarta() < mano.get(i + 1).getNumCarta()) {
                mano.add(i + 1, carta);
            }
        }
    }

/**
 * 
 * @param numCarta: Número da carta que se desexa sacar da baraja do xogador
 * @return Carta co número que se pediu e en caso de non atoparse IllgalArgumentException.
 *          modifica this ao quitarlle unha carta e se non existen cartas na mano lanza unha NullPointerException
 */
    public Carta SacarCarta(int numCarta) {
        Carta toRet;
        if(this.mano.isEmpty()){
            throw new NullPointerException("non hai cartas para extraer");
        }
        for (int i = 0; i < mano.size(); i++) {
            if (mano.get(i).getNumCarta() == numCarta) {
                toRet = mano.remove(i);
                return toRet;
            }

        }
        throw new IllegalArgumentException("Este xogadr non ten unha carta con ese número");
}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("~-----------------------~\n   =").append("Nombre: ").append(nombre).append("=\n   =Mano=\n").append(mano).append("<_______________________>");
        return sb.toString();
    }
}
