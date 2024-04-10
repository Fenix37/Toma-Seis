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
        baraja = new Baraja();
        jugadores = new ArrayList<>();

    }
        

    public void jugar() {
        baraja.barajar();
        for(String nombreJugador: iu.pedirNombresJugadores()){
            jugadores.add(new Jugador(this.subbarajar(10), nombreJugador));
        }
        iu.mostrarJugadores(jugadores);
    }

   
}
