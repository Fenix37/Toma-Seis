/**
 * Representa la interfaz del juego Toma 6, en este proyecto va a ser una entrada/salida en modo texto
 * Se recomienda una implementación modular.
 */
package gal.uvigo.esei.aed1.Toma6.iu;

import gal.uvigo.esei.aed1.Toma6.core.Jugador;
import java.util.ArrayList;
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
         * Limpia la pantalla:
         * Crea un proceso CMD y usa el comando System("CLS")
         */
    public void borrarPantalla(){
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            /*No hacer nada*/
        }
    }
        
    /**
     * Muestra un mensaje por pantalla
     *
     * @param msg El mensaje a mostrar
     */
    public void mostrarMensaje(String msg) {
        borrarPantalla();
        System.out.print("~");
        for(int i=0;i<msg.length();i++){
            System.out.print("-");
        }
        System.out.println("~");
        System.out.print("|");
        System.out.print(msg);
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
        Collection<String> toret = new ArrayList<>();
        int numJugadores;
        do {
            numJugadores = leeNum("Introduce el numero de jugadores: ");
        } while (numJugadores < 2 || numJugadores > 10);
        for (int i = 0; i < numJugadores; i++) {

            toret.add(leeString("Introduce le numero del jugador" + (i + 1)));
            
        }
        return toret;
    }
            String add = "";
            do{
                add = leeString("Introduce el nombre del jugador " + (i + 1));
            }while(add == "");
            toret.add(add);
        }
        return toret;
    }
        /**
         * Muestra por pantalla los datos de un jugador
         *
         * @param jugador Jugador para el cual se mostrarán los datos por
         * pantalla
         */

    }

    /**
     * Muestra por pantalla los datos de una colección de jugadores
     *
     * @param jugadores Jugadores cuyos datos se mostrarán por pantalla
     */
    public void mostrarJugadores(Collection<Jugador> jugadores) {
        borrarPantalla();
        Jugador[] jugadoresArray;
        jugadoresArray = new Jugador[10];
        jugadores.toArray(jugadoresArray);
        int i=0;
        while(jugadoresArray[i]!=null&&i<10){
            mostrarJugador(jugadoresArray[i]);
            i++;
        }
    }
}
