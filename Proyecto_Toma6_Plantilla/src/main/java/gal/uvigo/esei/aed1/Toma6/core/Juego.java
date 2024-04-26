/**
 * Representa el juego Toma 6, con sus reglas (definidas en el documento Primera entrega).
 * Se recomienda una implementación modular.
 */
package gal.uvigo.esei.aed1.Toma6.core;

import gal.uvigo.esei.aed1.Toma6.iu.IU;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Juego {

    public final static int NumBueyesGanar = 66;
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
/**
 * 
 * @return true se existe algún xogador con máis de 66 bueyes e false en 
 * caso contrario
 */
    private boolean finalRonda() {
        for (Jugador jugador : jugadores) {
            if (jugador.getNumBueyes() >= NumBueyesGanar) {
                return true;
            }
        }
        return false;
    }
/**
 * 
 * @return o xogador con menos bueyes,se houbera varios devolve todos os que
 * teñan menos bueyes
 */
    private List<Jugador> Ganadores() {
        int minimo = 0;
        List<Jugador> toRet = new ArrayList();
        for (Jugador jugador : jugadores) {
            if (jugador.getNumBueyes() == minimo) {
                toRet.add(jugador);
            } else if (jugador.getNumBueyes() < minimo) {
                toRet.clear();
                toRet.add(jugador);
                minimo = jugador.getNumBueyes();
            }
        }
        return toRet;
    }

    /**
     * Modifica: jugadores ao crear os xogadores con todolos seus valores
     * asignados
     *
     */
    public void crearJugadores() {
        final int numCartas = 10;
        //crea os xogadores
        for (String nombreJugador : iu.pedirNombresJugadores()) {
            jugadores.add(new Jugador(nombreJugador));
        }
        //asignalle as cartas a cada un
        for (Jugador jug : jugadores) {
            for (int i = 0; i < numCartas; i++) {
                jug.introducirCarta(baraja.getPop());
            }
        }
    }

    /**
     * Modifica: mesa,introduce en cada fila da mesa unha carta
     *
     */
    public void inicializarMesa() {
        for (int i = 0; i < MesaDeJuego.NUM_FILAS_MESA; i++) {
            mesa.insertarCarta(baraja.getPop(), i);
        }
    }

    public void jugar() {
        Carta jugada;
        baraja.barajar();
        crearJugadores();
        inicializarMesa();
        Scanner jin = new Scanner(System.in);
        iu.mostrarJugadores(jugadores);
        iu.mostrarMesa(mesa.toString());

        System.out.println("Pulsa enter para empezar el turno");
        jin.nextLine();
        List<Carta> elecciones = new ArrayList<>();
        List<String> nombres = new ArrayList<>();
        List<Integer> orden = new ArrayList<>();
        for (int turno = 0; turno < 10; turno++) {
            for (Jugador jug : jugadores) {
                boolean cartaValida = false;
                do {
                    jugada = jug.sacarCarta(iu.pedirCartaAJugar(jug));
                    if (jugada == null) {
                        iu.mostrarMensaje("Esta carta non se atopa na sua man,por favor introduzca unha carta válida. ");
                    } else {
                        cartaValida = true;
                    }
                } while (!cartaValida);

                elecciones.add(jugada);
                nombres.add(jug.getNombre());

            }
            orden = ordenarCartas(elecciones);
            //Se muestra la mesa
            System.out.println("Elecciones hechas: ");
            iu.mostrarMesaEnReparto(mesa.toString(), jugadores, elecciones);
            int i = 0;
            System.out.println("Pulse enter para empezar el reparto");
            jin.nextLine();
            while (orden.size() > i) {

                if (mesa.insertarCarta(elecciones.get(orden.get(i)), nombres.get(orden.get(i))) == false) {
                    baraja.addCarta(elecciones.get(orden.get(i)));
                }
                elecciones.set(orden.get(i), new Carta(0, 105));
                iu.mostrarMesaEnReparto(mesa.toString(), jugadores, elecciones);
                i++;
                jin.nextLine();
            }
            elecciones.clear();
            nombres.clear();
        }
        System.out.println("Fin de la partida.");
    }

    public List<Integer> ordenarCartas(List<Carta> cartas) {
        List<Integer> orden = new ArrayList<>();
        for (Carta carta : cartas) {
            orden.add(carta.getNumCarta());
        }
        Collections.sort(orden);
        int index;
        boolean continiu;
        int count = 0;
        for (Integer i : orden) {
            index = 0;
            continiu = true;
            for (Carta carta : cartas) {
                if (carta.getNumCarta() == i) {
                    continiu = false;
                }
                if (continiu == true) {
                    index++;
                }
            }
            orden.set(count, index);
            count++;
        }
        return orden;
    }
}
