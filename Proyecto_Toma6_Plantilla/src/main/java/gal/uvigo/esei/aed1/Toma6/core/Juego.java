/**
 * Representa el juego Toma 6, con sus reglas (definidas en el documento Primera entrega).
 * Se recomienda una implementación modular.
 */
package gal.uvigo.esei.aed1.Toma6.core;

import gal.uvigo.esei.aed1.Toma6.iu.IU;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
        List<Carta> elecciones = new ArrayList<>();
        List<String> nombres = new ArrayList<>();
        for (int turno = 0; turno < 10; turno++) {
            for (Jugador jug : jugadores) {
                boolean cartaValida = false;
                do {
                    try {
                        elecciones.add(jug.SacarCarta(iu.pedirCartaAJugar(jug)));
                        nombres.add(jug.getNombre());
                        cartaValida = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                } while (!cartaValida);
            }
            ordenarCartas(elecciones, nombres);
            System.out.println("Turnos: ");
            for(int i = 0; i < nombres.size(); i++){
                System.out.println((i+1) + ": " + nombres.get(i));
            }
            //Se muestra la mesa
            System.out.println("Elecciones hechas: ");
            iu.mostrarMesaEnReparto(mesa.toString(), jugadores, elecciones);
            
            while (!elecciones.isEmpty()) {
                iu.leeString("Turno de: " + nombres.getFirst() + ". Pulse enter para continuar.");
                if (mesa.insertarCarta(elecciones.getFirst(), nombres.getFirst()) == false) {
                    baraja.addCarta(elecciones.getFirst());
                }
                elecciones.removeFirst();
                nombres.removeFirst();
                iu.mostrarMesa(mesa.toString());
            }
        }
        System.out.println("Fin de la partida.");
    }

    public void ordenarCartas(List<Carta> cartas, List<String> nombres) {
        for (int i = 0; i < cartas.size() - 1; i++) {
            for (int j = 0; j < cartas.size() - 1; j++) {
                if (cartas.get(j).getNumCarta() > cartas.get(j + 1).getNumCarta()) {
                    Carta auxCarta = cartas.get(j);
                    String auxString = nombres.get(j);
                    cartas.set(j, cartas.get(j + 1));
                    cartas.set(j + 1, auxCarta);
                    nombres.set(j, nombres.get(j + 1));
                    nombres.set(j + 1, auxString);
                }
            }
        }
    }
}
