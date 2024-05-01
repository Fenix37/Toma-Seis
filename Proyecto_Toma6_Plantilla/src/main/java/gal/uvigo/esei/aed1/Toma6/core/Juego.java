/**
 * Representa el juego Toma 6, con sus reglas (definidas en el documento Primera entrega).
 * Se recomienda una implementación modular.
 */
package gal.uvigo.esei.aed1.Toma6.core;

import gal.uvigo.esei.aed1.Toma6.iu.IU;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
    private boolean finalPartida() {
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
    private List<Jugador> ganadores() {
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
        iu.mostrarJugadores(jugadores);
        iu.mostrarMesa(mesa.toString());


        List<Carta> elecciones = new ArrayList<>();
        List<Integer> orden;
        for (int turno = 0; turno < 10; turno++) {
            for (Jugador jug : jugadores) {
                boolean cartaValida = false;
                do {
                    jugada = jug.sacarCarta(iu.pedirCartaAJugar(jug));
                    if (jugada == null) {
                        iu.mostrarMensaje("Esta carta non se atopa na sua man, "
                                + "por favor introduzca unha carta valida. ");

                    } else {
                        cartaValida = true;
                    }
                } while (!cartaValida);

                elecciones.add(jugada);
            }
            iu.mostrarMensaje("Elecciones hechas: ");
            iu.mostrarMesaEnReparto(mesa.toString(), jugadores, elecciones);
            orden = ordenarCartas(elecciones);
            int i = 0;
            while (!elecciones.isEmpty()) {
                int resultadoEleccion = mesa.insertarCarta(elecciones.getFirst());
                if (resultadoEleccion == -1) {
                    int opFila = -1;
                    do{
                        iu.mostrarMensaje("La carta no pudo ser introducida ya que es menor a todas las últimas de la mesa.");
                        opFila = iu.leeNum("Introduce la fila de la mesa de las que se va a llevar las cartas: [" + 1 + "-" + MesaDeJuego.NUM_FILAS_MESA + "] ");
                    }while(opFila < 1 || opFila > MesaDeJuego.NUM_FILAS_MESA);
                    Jugador aModificar = getJugador(orden.get(i));
                    for(Carta carta: mesa.vaciarFila(opFila-1, elecciones.getFirst())){
                        aModificar.addMonton(carta);
                    }
                }
                else{
                   
                }
                elecciones.removeFirst();
                iu.mostrarMesa(mesa.toString());
                i++;
            }
            elecciones.clear();
        }
        if (finalPartida()) {
            iu.mostrarMensaje("FINAL DO XOGO\n");
            Collection<Jugador> ganadores = ganadores();
            if (ganadores.size() > 1) {
                iu.mostrarMensaje("\tOs gañadores son:");
            } else {
                iu.mostrarMensaje("\tGañou:");
            }
            iu.mostrarJugadores(ganadores());
        }

    }
    
    /**
     * Consigue el objeto Jugador con un indice del Collection Jugadores
     * @param pos indice donde se encuentra el jugador deseado
     * @return jugador buscado
     */
    public Jugador getJugador(int pos){
        if(pos < 0 || pos > jugadores.size()-1){
            throw new IllegalArgumentException("Posicion invalida");
        }
        Iterator<Jugador> itr = jugadores.iterator();
        for(int i = 0; i < pos; i++){
            itr.next();
        }
        return itr.next();
    }
    
    /**
     * Ordena la lista de cartas y crea una lista con su posicion original
     * @param cartas lista de cartas a ordenar
     * @return lista con las posiciones originales de las cartas
     */
    public List<Integer> ordenarCartas(List<Carta> cartas) {
        List<Integer> orden = new ArrayList<>();
        for (int i=0; i<cartas.size();i++) {
            orden.add(i);
        }
        Carta tempC;
        int tempI;
        for (int i = 0; i < (cartas.size()-1); i++) {
            for (int j = 0; j < (cartas.size()-i-1); j++) {
                if(cartas.get(j).getNumCarta()>(cartas.get(j).getNumCarta()+1)){
                    tempC=cartas.get(j);
                    tempI=orden.get(j);
                    cartas.set(j, cartas.get(j+1));
                    orden.set(j, orden.get(j+1));
                    cartas.set(j+1, tempC);
                    orden.set(j+1, tempI);
                }
            }
            
        }
        return orden;
    }
}
