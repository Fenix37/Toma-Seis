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
        List<Integer> orden = new ArrayList<>();
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
            orden = ordenarCartas(elecciones);
            //Se muestra la mesa
            System.out.println("Elecciones hechas: ");
            iu.mostrarMesaEnReparto(mesa.toString(), jugadores, elecciones);
            int i=0;
            System.out.println("Pulse enter para empezar el reparto");
            Scanner jin = new Scanner(System.in);
            jin.nextLine();
            jin.close();
            while (!nombres.isEmpty()) {
                
                if (mesa.insertarCarta(elecciones.get(orden.get(i)), nombres.get(orden.get(i))) == false) {
                    baraja.addCarta(elecciones.get(orden.get(i)));
                }
                elecciones.set(orden.get(i), new Carta(0,105));
                nombres.remove(nombres.get(orden.get(i)));
                iu.mostrarMesaEnReparto(mesa.toString(), jugadores,elecciones);
                i++;
                iu.leeString("Pulse enter para continuar.");
            }
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
        int count=0;
        for (Integer i : orden) {
            index=0;
            continiu=true;
            for (Carta carta : cartas){
                if(carta.getNumCarta()==i){
                    continiu=false;
                }
                if(continiu==true){
                    index++;
                }
            }
            orden.set(count,index);
            count++;
        }
        return orden;
    }
}