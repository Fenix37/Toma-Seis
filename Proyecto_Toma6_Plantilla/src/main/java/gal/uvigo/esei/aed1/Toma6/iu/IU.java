/**
 * Representa la interfaz del juego Toma 6, en este proyecto va a ser una entrada/salida en modo texto 
 * Se recomienda una implementación modular.
 */

package gal.uvigo.esei.aed1.Toma6.iu;

import gal.uvigo.esei.aed1.Toma6.core.Jugador;
import java.util.Collection;
import java.util.Scanner;

public class IU {

    private final Scanner teclado;

    public IU() {
        teclado = new Scanner(System.in);
    }

    /**
     * Lee un número de teclado
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
        
        /**
         * Limpia la pantalla:
         * Crea un proceso CMD y usa el comando System("CLS")
         */
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            /*No hacer nada*/
        }
        System.out.print("~");
        for(int i=0;i<msg.length();i++){
            System.out.print("-");
        }
        System.out.println("~");
        System.out.print("|");
        System.out.println(msg);
        System.out.println("|");
        System.out.print("~");
        for(int i=0;i<msg.length();i++){
            System.out.print("-");
        }
        System.out.println("~");
    }

    /**
     * Solicita los nombres de los jugadores por teclado y los almacena en una
     * estructura de datos
     *
     * @return Los datos de los jugadores almacenados en la estructura de datos
     * correspondiente
     */
    public Collection<String> pedirNombresJugadores() {
        return null;
    }

    /**
     * Muestra por pantalla los datos de un jugador
     *
     * @param jugador Jugador para el cual se mostrarán los datos por pantalla
     */
    private void mostrarJugador(Jugador jugador) {
        String nombre = jugador.getNombre();
        String cartas = jugador.getBaraja.toString();
        System.out.println("~-----------------------~");
        System.out.println("   ="+nombre+"=");
        System.out.println(cartas);
        System.out.println("<_______________________>");
    }

    /**
     * Muestra por pantalla los datos de una coleccion de jugadores
     *
     * @param jugadores Jugadores cuyos datos se mostrarán por pantalla
     */
    public void mostrarJugadores(Collection<Jugador> jugadores) {
        /**
         * Limpia la pantalla:
         * Crea un proceso CMD y usa el comando System("CLS")
         */
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            /*No hacer nada*/
        }
        for(int i=0; i<jugadores.size();i++){
            mostrarJugador(jugadores[i]);
        }
    
    
    
    }

}