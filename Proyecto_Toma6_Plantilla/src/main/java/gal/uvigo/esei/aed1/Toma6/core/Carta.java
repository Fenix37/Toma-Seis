
/*
 * Representa una carta, formada por un número y la cantidad de bueyes correspondiente
 */
package gal.uvigo.esei.aed1.Toma6.core;


public class Carta {

    private int numBueyes;
    private int numCarta;

    public Carta(int numBueyes, int numCarta) {
        this.numBueyes = numBueyes;
        this.numCarta = numCarta;
    }
    public int getNumBueyes(){
        return numBueyes;
    }
    public int getNumCarta(){
        return numCarta;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[Número: ").append(getNumCarta())
                .append("] [Bueyes: ").append(getNumBueyes())
                .append("]\n");
        return sb.toString();
    }
}
