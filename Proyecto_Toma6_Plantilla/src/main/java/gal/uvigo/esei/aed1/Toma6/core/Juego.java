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
        //Se baraja la baraja antes de empezar.
        baraja.barajar(); 
        System.out.println(baraja);
       // Pedir nombres de los jugadores
        int i = 0;
        for (String str: iu.pedirNombresJugadores()) {
            jugadores.add(new Jugador(baraja.subbarajar(), str));
        }
        iu.mostrarJugadores(jugadores);
    }

    public int getNumJugadores() {
        return jugadores.size();
    }

}
