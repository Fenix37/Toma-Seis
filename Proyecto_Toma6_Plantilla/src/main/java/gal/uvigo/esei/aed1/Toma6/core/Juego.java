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
    private MesaDeJuego mesa;

    public Juego(IU iu) {
        this.iu = iu;
        baraja = new Baraja();
        jugadores = new ArrayList<>();
        mesa = new MesaDeJuego();

    }

    public void jugar() {
        baraja.barajar();
        for (String nombreJugador : iu.pedirNombresJugadores()) {
            jugadores.add(new Jugador(nombreJugador));
        }
        for (Jugador jug : jugadores) {
            for (int i = 0; i < 10; i++) {
                jug.IntroducirCarta(baraja.getPop());
            }
        }
        for (int i = 0; i < 4; i++) {
            mesa.insertarCarta(baraja.getPop(), i);
        }
        iu.mostrarJugadores(jugadores);
        iu.mostrarMesa(mesa.toString());
        Carta elecciones[] = new Carta[10];
        int i = 0;
        for (Jugador jug : jugadores) {
            boolean cartaValida = false;
            do {
                try {
                    elecciones[i] = jug.SacarCarta(iu.pedirCartaAJugar(jug));
                    cartaValida = true;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } while (!cartaValida);
        }
        
    }
}
