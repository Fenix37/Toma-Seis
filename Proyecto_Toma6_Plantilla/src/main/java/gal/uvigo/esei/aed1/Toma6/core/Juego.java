/**
 * Representa el juego Toma 6, con sus reglas (definidas en el documento Primera entrega). 
 * Se recomienda una implementación modular.
 */

package gal.uvigo.esei.aed1.Toma6.core;


import gal.uvigo.esei.aed1.Toma6.iu.IU;


public class Juego {
    
    private final IU iu;

    
    public Juego(IU iu){
        this.iu = iu;

    }
        
    public void jugar(){
        Baraja cosa = new Baraja();
        System.out.println(cosa);
    }
    
    
    
}
