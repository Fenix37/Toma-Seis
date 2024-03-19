/**
 * Representa el juego Toma 6, con sus reglas (definidas en el documento Primera entrega).
 * Se recomienda una implementación modular.
 */
package gal.uvigo.esei.aed1.Toma6.core;

import gal.uvigo.esei.aed1.Toma6.iu.IU;

public class Juego {

    private final IU iu;
    private Baraja baraja;
    private Jugador[] jugadores;

    public Juego(IU iu) {
        this.iu = iu;

    }

    public void jugar() {
        // Preguntar cuántos jugadores/as van a jugar
        int numJugadores;
        do {
            numJugadores = iu.leeNum("Ingrese el número de jugadores (entre 2 y 10): ");
        } while (numJugadores < 2 || numJugadores > 10);
        jugadores = new Jugador[numJugadores];
        baraja = new Baraja();
        // Pedir nombres de los jugadores
        for (int i = 0; i < numJugadores; i++) {
            String nombre = iu.leeString("Ingrese el nombre del jugador " + (i + 1) + ": ");
            
            jugadores[i] = new Jugador(baraja, nombre);
        
        }
    }
    public int getNumJugadores(){
        return jugadores.length;
    }

}
