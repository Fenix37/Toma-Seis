/*
 * Representa a un jugador, identificado por el nombre y sus cartas de la mano
 * Estructura mano: se utilizará un TAD adecuado
 * Funcionalidad: Añadir carta a la mano (añadir la carta de foma que queden 
 * ordenadas de menor a mayor por su número), convertir a String el objeto Jugador (toString)
 */

package gal.uvigo.esei.aed1.Toma6.core;

import java.util.Scanner;


public class Jugador {
    private Baraja baraja;
    private String nombre;

    public Jugador(Baraja baraja, String nombre) {
        this.baraja = baraja;
        this.nombre = nombre;
    }

    public Baraja getBaraja() {
        return baraja;
    }
    public void setBaraja(Baraja baraja) {
        this.baraja = baraja;
    }

    public String getNombre() {
        return nombre;
    }

}
