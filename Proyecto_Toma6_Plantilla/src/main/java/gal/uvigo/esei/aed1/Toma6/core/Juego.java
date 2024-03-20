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

    }

    public void jugar() {
        // Preguntar cuántos jugadores/as van a jugar
        int numJugadores;
        do {
            numJugadores = iu.leeNum("Ingrese el número de jugadores (entre 2 y 10): ");
        } while (numJugadores < 2 || numJugadores > 10);
        jugadores = new ArrayList<>();
        //Se baraja la baraja antes de empezar.
        baraja.barajar();
        // Pedir nombres de los jugadores
        for (int i = 0; i < numJugadores; i++) {
            String nombre = iu.leeString("Ingrese el nombre del jugador " + (i + 1) + ": ");
            jugadores.add(new Jugador(subbarajar(), nombre));
        }
        iu.mostrarJugadores(jugadores);
    }

    public int getNumJugadores() {
        return jugadores.size();
    }

    /**
     *
     * @brief Modifica a baralla original
     * @return baraja coas cartas do xogador
     */
    public Baraja subbarajar() {
        int random;
        Baraja baralladas = Baraja.crearBarajaVacia();
        //recorre baralla e (para cada carta) decide se a mete en barajadas ou non
        while (baralladas.getNumCartas() < 10) {
            random = ((int) (Math.random() * 23)) % 2;
            if (random == 1) {
                baralladas.addCarta(baraja.getPop());
            }
        }
        return baralladas.ordenarBaraja();
    }

}
