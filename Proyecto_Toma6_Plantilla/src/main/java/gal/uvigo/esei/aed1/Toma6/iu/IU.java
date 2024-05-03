/**
 * Representa la interfaz del juego Toma 6, en este proyecto va a ser una entrada/salida en modo texto
 * Se recomienda una implementación modular.
 */
package gal.uvigo.esei.aed1.Toma6.iu;

import gal.uvigo.esei.aed1.Toma6.core.Carta;
import gal.uvigo.esei.aed1.Toma6.core.Jugador;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class IU {

    private final Scanner teclado;

    public IU() {
        teclado = new Scanner(System.in);
    }

    /**
     * Envia un mensaje y lee un número de teclado
     *
     * @param msg El mensaje a visualizar.
     * @return El numero como entero
     */
    public int leeNum(String msg) {
        boolean repite;
        int toret = 0;

        do {
            repite = false;
            mostrarMensaje(msg);
            try {
                toret = Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException exc) {
                repite = true;
            }
        } while (repite);

        return toret;
    }

    /**
     * Lee un texto de teclado
     *
     * @param msg El mensaje a utilizar
     * @return El texto como String
     */
    public String leeString(String msg) {
        String toret;
        mostrarMensaje(msg);
        toret = teclado.nextLine();
        return toret;
    }

    /**
     * Muestra un mensaje por pantalla
     *
     * @param msg El mensaje a mostrar
     */
    public void mostrarMensaje(String msg) {
        StringBuilder sb = new StringBuilder();
        sb.append("~");
        for(int i=0;i<msg.length();i++){
            sb.append("-");
        }
        sb.append("~\n|").append(msg).append("|\n~");
        for(int i=0;i<msg.length();i++){
            sb.append("-");
        }
        sb.append("~\n");
        System.out.println(sb);
    }

    /**
     * Solicita los nombres de los jugadores por teclado y los almacena en una
     * estructura de datos
     *
     * @return Los datos de los jugadores almacenados en la estructura de datos
     * correspondiente
     */
    public Collection<String> pedirNombresJugadores() {
        Collection<String> toret = new ArrayList<>();
        int numJugadores;
        do {
            numJugadores = leeNum("Introduce el numero de jugadores: ");
        } while (numJugadores < 2 || numJugadores > 10);
        for (int i = 0; i < numJugadores; i++) {
            String add = "";
            do{
                add = leeString("Introduce el nombre del jugador " + (i + 1));
            }while(add == "");
            toret.add(add);
        }
        return toret;
    }
    
    /**
     * Enseña por pantalla la mesa sola
     * @param mesa mesa a imprimir
     */
    public void mostrarMesa(String mesa){
        System.out.println(mesa);
    }
    
    /**
     * 
     * @param mesa mesa a imprimir
     * @param jugadores de aquí saco los nombres para poner de quien es cada carta
     * @param cartas cartas elegidas por los jugadores
     */
    public void mostrarMesaEnReparto(String mesa, Collection<Jugador> jugadores, List<Carta> cartas){
        mostrarMesa(mesa);
        int i=0;
        mostrarMensaje("Nombre: NumeroDeLaCarta, BueyesDeLaCarta");
        for (Jugador jugadorActual : jugadores) {
            System.out.print(jugadorActual.getNombre()+": " + cartas.get(i));
            i++;
        }
    }
    
    /**
     * le pido a mi victima que elija una de sus cartas
     * @param jugador víctima
     * @return 
     */
    public int pedirCartaAJugar(Jugador jugador){
        mostrarMensaje("Es el turno de "+jugador.getNombre());
        System.out.println(jugador.manoToString());
        return leeNum(jugador.getNombre()+", escribe el numero de la carta que quieras jugar");
    }

    /**
     * Muestra por pantalla los datos de un jugador
     *
     * @param jugador Jugador para el cual se mostrarán los datos por
     * pantalla
     */
    private void mostrarJugador(Jugador jugador) {
        System.out.println(jugador.toString());
    }

    /**
     * Muestra por pantalla los datos de una coleccion de jugadores
     *
     * @param jugadores Jugadores cuyos datos se mostrarán por pantalla
     */
    public void mostrarJugadores(Collection<Jugador> jugadores) {
        for (Jugador jugador : jugadores) {
            mostrarJugador(jugador);
        }
    }
    
    /**
     * Muestra por pantalla los bueyes acumulados de cada jugador
     * @param jugadores Collection de los jugadores víctimas de la función
     */
    public void bueyesPorJugador(Collection<Jugador> jugadores){
        StringBuilder sb = new StringBuilder();
        for (Jugador jugador : jugadores) {
            sb.append(jugador.getNombre()).append(": ").append(jugador.getNumBueyes()).append(" bueyes\n");
        }
        System.out.print(sb);
    }
}