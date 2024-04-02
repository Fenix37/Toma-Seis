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

    public Juego(IU iu) {
        this.iu = iu;
        jugadores = new ArrayList<>();

    }
        

    public void jugar() {
        for(String nombreJugador: iu.pedirNombresJugadores()){
            jugadores.add(new Jugador(this.subbarajar(10), nombreJugador));
        }
    }
    public Baraja subbarajar(int numCartas){
        if(numCartas<0){
            throw new IllegalArgumentException("O número de cartas é negativo");
        }
        if(numCartas>this.baraja.getNumCartas()){
            throw new IllegalArgumentException("O número de cartas é maior que o da baralla da que se deben extraer");
        }
        Baraja baralladas=  new Baraja();
        //mete cartas na baralla que devolve ata q esta teña 10
        while(baralladas.getNumCartas()<numCartas){
                baralladas.introducirOrdenado(baraja.getPop());
            }   
       return baralladas; 
    }    
    public int getNumeroJugadores() {
        return jugadores.size();
    }
    public int getNumJugadores(){
        return jugadores.size();
    }

}