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
     * Lee un número de teclado
     *
     * @return El numero como entero
     */
    public int leeNum() {
        boolean repite;
        int toret = 0;

        do {
            repite = false;
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
    int[] longitudNombres = new int[10];
    
    System.out.print("|");
    for (Jugador jugadorActual : jugadores) {
        longitudNombres[i]=jugadorActual.getNombre().length();
        if(longitudNombres[i]<10){
            for(int j=0;j<(Math.abs(longitudNombres[i]-10)/2);j++){
                System.out.print(" ");
            }
            System.out.print(jugadorActual.getNombre());
            for(int j=0;j<(Math.abs(longitudNombres[i]-10)/2);j++){
                System.out.print(" ");
            }
            longitudNombres[i]+=2*(Math.abs(longitudNombres[i]-10)/2);
        }
        else{
            System.out.print(jugadorActual.getNombre());
        }
        System.out.print("|");
        i++;
    }
    System.out.print("\n|");
    for(int j=0; j<i; j++){
        if(cartas.get(j).getNumBueyes()!=0){
            if(longitudNombres[j]<=10){
                System.out.print("Numero ");
                if(cartas.get(j).getNumCarta()<10){
                    System.out.print("00"+cartas.get(j).getNumCarta());
                }
                else if(cartas.get(j).getNumCarta()<100){
                    System.out.print("0"+cartas.get(j).getNumCarta());
                }
                else{
                    System.out.println(cartas.get(j).getNumCarta());
                }
            }
            else{
                for(int f=0;f<(Math.abs(longitudNombres[j]-10)/2);f++){
                    System.out.print(" ");
                }
                System.out.print("Numero ");
                if(cartas.get(j).getNumCarta()<10){
                    System.out.print("00"+cartas.get(j).getNumCarta());
                }
                else if(cartas.get(j).getNumCarta()<100){
                    System.out.print("0"+cartas.get(j).getNumCarta());
                }
                else{
                    System.out.println(cartas.get(j).getNumCarta());
                }
                for(int f=0;f<(Math.abs(longitudNombres[j]-10)/2);f++){
                    System.out.print(" ");
                }
            }
        }
        else{
            if(longitudNombres[i]<10){
                for(int r=0;r<(Math.abs(longitudNombres[i]-10)/2);r++){
                    System.out.print(" ");
                }
                System.out.print("   Done   ");
                for(int r=0;r<(Math.abs(longitudNombres[i]-10)/2);r++){
                    System.out.print(" ");
                }
            }
            else{
                System.out.print("   Done   ");
            }
        }
        
        System.out.print("|");
    }
    System.out.print("\n|");
    for(int j=0; j<i; j++){
        if(cartas.get(j).getNumBueyes()!=0){
            if(longitudNombres[j]<=10){
                System.out.print(" "+cartas.get(j).getNumBueyes()+" bueyes ");
            }
            else{
                for(int f=0;f<(Math.abs(longitudNombres[j]-8)/2);f++){
                    System.out.print(" ");
                }
                System.out.print(cartas.get(j).getNumBueyes()+" bueyes");
                for(int f=0;f<(Math.abs(longitudNombres[j]-8)/2);f++){
                    System.out.print(" ");
                }
            }
        }
        else{
            if(longitudNombres[i]<10){
                for(int r=0;r<(Math.abs(longitudNombres[i]-10)/2);r++){
                    System.out.print(" ");
                }
                System.out.print("   Done   ");
                for(int r=0;r<(Math.abs(longitudNombres[i]-10)/2);r++){
                    System.out.print(" ");
                }
            }
            else{
                System.out.print("   Done   ");
            }
        }
        
        System.out.print("|");
    }
    System.out.print("\n");
}
    
    /**
     * le pido a mi victima que elija una de sus cartas
     * @param jugador víctima
     * @return 
     */
    public int pedirCartaAJugar(Jugador jugador){
        int decision = 0;
        borrarPantalla();
        leeString("Es el turno de "+jugador.getNombre()+". Pulsa Enter para continuar.");
        borrarPantalla();
        System.out.println(jugador.manoToString());
        System.out.println(jugador.getNombre()+", escribe el numero de la carque que quieras jugar");
        decision = leeNum();
        return decision;
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
        borrarPantalla();
        for (Jugador jugador : jugadores) {
            mostrarJugador(jugador);
        }
    }
}