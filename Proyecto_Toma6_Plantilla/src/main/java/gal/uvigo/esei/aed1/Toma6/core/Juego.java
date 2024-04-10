/**
 * Representa el juego Toma 6, con sus reglas (definidas en el documento Primera entrega).
 * Se recomienda una implementación modular.
 */
package gal.uvigo.esei.aed1.Toma6.core;

import gal.uvigo.esei.aed1.Toma6.iu.IU;
import java.util.ArrayList;
import java.util.Collection;

public class Juego {

    private final IU iu;
    private Baraja baraja;
    private Collection<Jugador> jugadores;

    public Juego(IU iu) {
        this.iu = iu;
        jugadores = new ArrayList<>();

    }
        

    public void jugar() {
        for(String nombreJugador: iu.pedirNombresJugadores()){
            jugadores.add(new Jugador(this.subbarajar(10), nombreJugador));
        }
    }


    /**
     *
     * @param numCartas Número de cartas que se van a retirar de la baraja para crear la subbaraja.
     * @return Devuelve una subbaraja ordenada de los primeros numCartas elementos de la baraja.
     */   
    public int getNumeroJugadores() {
        return jugadores.size();
    }
    public int getNumJugadores(){
        return jugadores.size();
    }

}