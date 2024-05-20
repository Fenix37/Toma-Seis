/**
 * Representa el juego Toma 6, con sus reglas (definidas en el documento Primera entrega).
 * Se recomienda una implementación modular.
 */
package gal.uvigo.esei.aed1.Toma6.core;

import gal.uvigo.esei.aed1.Toma6.iu.IU;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Juego {

    public static final int NUM_BUEYES_GANAR = 66;
    static final int MAX_CARTAS_JUGADOR = 10;
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
    private boolean finalPartida() {
        for (Jugador jugador : jugadores) {
            if (jugador.getNumBueyes() >= NUM_BUEYES_GANAR) {
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
    private List<Jugador> ganadores() {
        int minimo = Integer.MAX_VALUE;
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
     * asignados pero sen ningunha carta
     *
     */
    private void crearJugadores() {
        //crea os xogadores
        for (String nombreJugador : iu.pedirNombresJugadores()) {
            jugadores.add(new Jugador(nombreJugador));
        }
    }

    /**
     * Modifica: mesa,fai que cada fila da mesa teña excatamente 1 carta
     *
     */
    private void inicializarMesa() {
        for (int i = 0; i < MesaDeJuego.NUM_FILAS_MESA; i++) {
            mesa.vaciarFila(i, baraja.getPop());
        }
    }

    /**
     * dalle MAX_CARTAS_JUGADOR cartas a cada xogador
     *
     */
    private void repartirCartasJugadores() {
        for (Jugador jug : jugadores) {
            for (int i = 0; i < MAX_CARTAS_JUGADOR; i++) {
                jug.introducirCarta(baraja.getPop());
            }
        }
    }

    /**
     * @return modifica baralla ao barallala,reinicia as cartas da mesa, reparte
     * as cartas aos xogadores e os mostra por pantalla
     */
    private void iniciarRonda() {
        baraja.barajar();
        repartirCartasJugadores();
        inicializarMesa();
        iu.mostrarJugadores(jugadores);
        iu.mostrarMesa(mesa.toString());
    }

    /**
     * @return mostra os bueis de cada xogador e devolve os montóns a baralla
     */
    private void finalizarRonda() {
        iu.bueyesPorJugador(jugadores);
        devolverCartasMonton();
    }
    /**
     * 
     * @param jug
     * @return carta elixida polo xogador 
     * @modify baralla ao quitarlle a carta que elixiu o xogaodr
     */
    private Carta pedirCarta(Jugador jug) {
        boolean cartaValida = false;
        Carta toRet;
        do {
            toRet = jug.sacarCarta(iu.pedirCartaAJugar(jug));
            //Si la carta es null es porque no se pudo sacar de su mano ya que no la tiene
            //por lo que es inválida
            if (toRet == null) {
                iu.mostrarMensaje("Esta carta non se atopa na sua man, "
                        + "por favor introduzca unha carta valida. ");

            } else {

                cartaValida = true;
            }
        } while (!cartaValida);
        return toRet;
    }
    /**
     * 
     * @param elecciones:lista coas eleccións de cada xogaddor
     * @param orden:Lista na que se garda a orde orixinal na que se  
     *          fixeron as elecciones
     * @param numXogador:numero do xogador co que se está tratando
     * @param codigo :resultado da inserción que contén o código do erro ocorrido
     *          (no caso de que non ocorrera ningún problema este método non fai nada)
     */
    private void erroDeInsercion(List<Carta> elecciones, List<Integer> orden, int numXogador, int codigo) {
        if (codigo == -1) {
            todalasFilasMayores(elecciones, orden, numXogador);
        } else if (codigo == -2) {
            filaChea(elecciones, orden, numXogador);
        }
    }
    /**
     * 
     * @param elecciones lista coas eleccións de cada xogaddor
     * @param orden Lista na que se garda a orde orixinal na que se  
     *          fixeron as elecciones
     * @param numXogador numero do xogador co que se está tratando
     * preguntalle ao xogador que fila quere levarse e añade dita fila ao montón do xogador
     */
    private void todalasFilasMayores(List<Carta> elecciones, List<Integer> orden, int numXogador) {
        int opFila = -1;
        do {
            int cartaElegida = elecciones.getFirst().getNumCarta();
            iu.mostrarMensaje("La carta " + cartaElegida + " no pudo ser introducida ya que es menor a todas las ultimas de la mesa.");
            opFila = iu.leeNum("Introduce la fila de la mesa de las que se va a llevar las cartas: [" + 1 + "-" + MesaDeJuego.NUM_FILAS_MESA + "] ");
        } while (opFila < 1 || opFila > MesaDeJuego.NUM_FILAS_MESA);
        Jugador aModificar = getJugador(orden.get(numXogador));
        for (Carta carta : mesa.vaciarFila(opFila - 1, elecciones.getFirst())) {
            aModificar.addMonton(carta);
        }
    }
    /**
     * 
     * @param elecciones lista coas eleccións de cada xogaddor
     * @param orden Lista na que se garda a orde orixinal na que se  
     *          fixeron as elecciones
     * @param numXogador numero do xogador co que se está tratando
     * 
     * informa ao xogador de que non pode insertar a carta na fila apropiada e 
     * añade dita fila ao montón do xogador
     */
    private void filaChea(List<Carta> elecciones, List<Integer> orden, int numXogador) {
        int cartaElegida = elecciones.getFirst().getNumCarta();
        int fila = mesa.filaMenor(cartaElegida);
        iu.leeString("La carta " + cartaElegida + " no se pudo insertar ya que la fila " + (fila + 1) + " tiene "
                + MesaDeJuego.MAX_CARTAS_FILA + " cartas.");
        Jugador jugadorActual = getJugador(orden.get(numXogador));
        List<Carta> cartasDeMesa = mesa.vaciarFila(fila, elecciones.getFirst());
        for (Carta carta : cartasDeMesa) {
            jugadorActual.addMonton(carta);
        }
    }
    /**
     * 
     * @param elecciones lista coas eleccións dos xogadores
     * 
     * recorre elecciones e executa as accións requeridas para cada elemento
     */
    private void xestionarElecciones(List<Carta> elecciones) {
        List<Integer> orden = ordenarCartas(elecciones);
        int i = 0;
        while (!elecciones.isEmpty()) {
            int resultadoEleccion = mesa.insertarCarta(elecciones.getFirst());
            erroDeInsercion(elecciones, orden, i, resultadoEleccion);
            elecciones.removeFirst();
            iu.mostrarMesa(mesa.toString());
            i++;
        }
        elecciones.clear();
    }

    public void jugar() {
        crearJugadores();
        do {
            iniciarRonda();
            List<Carta> elecciones = new ArrayList<>();
            for (int turno = 0; turno < MAX_CARTAS_JUGADOR; turno++) {
                //Se le pide una carta a cada jugador
                for (Jugador jug : jugadores) {
                    elecciones.add(pedirCarta(jug));
                }
                iu.mostrarMensaje("Elecciones hechas: ");
                iu.mostrarMesaEnReparto(mesa.toString(), jugadores, elecciones);
                this.xestionarElecciones(elecciones);
            }
            finalizarRonda();
        } while (!finalPartida());
        mostrarGañadores();
    }
    /**
     * mostra o nome do gañador ou gañadores por pantalla
     */
    private void mostrarGañadores() {
        iu.mostrarMensaje("FINAL DO XOGO\n");
        Collection<Jugador> ganadores = ganadores();
        if (ganadores.size() > 1) {
            iu.mostrarMensaje("\tOs gañadores son:");
        } else {
            iu.mostrarMensaje("\tGañou:");
        }
        for (Jugador ganador : ganadores) {
            iu.mostrarMensaje(ganador.getNombre());
        }
    }
    /**
     * devolve todalas cartas dos montóns dos xogadores a baralla
     */
    private void devolverCartasMonton() {
        for (Jugador jugador : jugadores) {
            for (Carta carta : jugador.getMonton()) {
                baraja.addCarta(carta);
            }
        }
    }

    /**
     * Consigue el objeto Jugador con un indice del Collection Jugadores
     *
     * @param pos indice donde se encuentra el jugador deseado
     * @return jugador buscado
     */
    private Jugador getJugador(int pos) {
        if (pos < 0 || pos > jugadores.size() - 1) {
            throw new IllegalArgumentException("Posicion invalida");
        }
        Iterator<Jugador> itr = jugadores.iterator();
        for (int i = 0; i < pos; i++) {
            itr.next();
        }
        return itr.next();
    }

    /**
     * Ordena la lista de cartas y crea una lista con su posicion original
     *
     * @param cartas lista de cartas a ordenar
     * @return lista con las posiciones originales de las cartas
     */
    private List<Integer> ordenarCartas(List<Carta> cartas) {
        List<Integer> orden = new ArrayList<>();
        for (int i = 0; i < cartas.size(); i++) {
            orden.add(i);
        }
        Carta tempC;
        int tempI;
        for (int i = 0; i < (cartas.size() - 1); i++) {
            for (int j = 0; j < (cartas.size() - i - 1); j++) {
                if (cartas.get(j).getNumCarta() > (cartas.get(j + 1).getNumCarta())) {
                    tempC = cartas.get(j);
                    tempI = orden.get(j);
                    cartas.set(j, cartas.get(j + 1));
                    orden.set(j, orden.get(j + 1));
                    cartas.set(j + 1, tempC);
                    orden.set(j + 1, tempI);
                }
            }

        }
        return orden;
    }
}
