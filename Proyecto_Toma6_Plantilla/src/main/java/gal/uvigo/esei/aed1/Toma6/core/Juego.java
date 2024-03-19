/**
 * Representa el juego Toma 6, con sus reglas (definidas en el documento Primera entrega). 
 * Se recomienda una implementación modular.
 */

package gal.uvigo.esei.aed1.Toma6.core;


import gal.uvigo.esei.aed1.Toma6.iu.IU;
import java.util.Stack;


public class Juego {
    private int numXogadores;
    private final IU iu;
    
    
    public Juego(IU iu){
        this.iu = iu;

    }
        
    public void jugar(){
        

    }
    /**
     * 
     * @param baralla: Baraja con todalas cartas do xogo para dividir en grupos
     * @return pila coas cartas do xogador
     */
    public Baraja barajar(Baraja baralla){
        int random;
        Baraja baralladas=  new Baraja();
        //recorre baralla e (para cada carta) decide se a mete en barajadas ou non
        while(baralladas.numCartas()<10){
            random=((int)(Math.random()*23))%2;
            if(random==1){
                baralladas.addCarta(baralla.pop());
            }   
        }
       return baralladas; 
    }

    public int getNumeroJugadores() {
        return numXogadores;
    }
    
    
}
