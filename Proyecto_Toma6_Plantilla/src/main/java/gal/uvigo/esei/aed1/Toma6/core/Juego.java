/**
 * Representa el juego Toma 6, con sus reglas (definidas en el documento Primera entrega). 
 * Se recomienda una implementación modular.
 */

package gal.uvigo.esei.aed1.Toma6.core;


import gal.uvigo.esei.aed1.Toma6.iu.IU;
import java.util.Stack;


public class Juego {
    private Jugador jugadores[];
    private final IU iu;
    private Baraja baraja;
    
    public Juego(IU iu){
        this.iu = iu;
        this.baraja = new Baraja();
    }
        
    public void jugar(){
        

    }
    /**
     * 
     * @brief Modifica a baralla original
     * @return baraja coas cartas do xogador
     */
    public Baraja subbarajar(){
        Baraja baralladas=  new Baraja();
        //mete cartas na baralla que devolve ata q esta teña 10
        while(baralladas.getNumCartas()<10){
                baralladas.addCarta(baraja.getPop());
            }   
        baralladas.ordenarBaraja();
       return baralladas; 
    }    
    public int getNumeroJugadores() {
        return jugadores.length;
    }
    
    

}
