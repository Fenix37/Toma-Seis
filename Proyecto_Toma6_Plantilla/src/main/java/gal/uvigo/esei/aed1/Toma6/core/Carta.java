/*
 
Representa una carta, formada por un número y la cantidad de bueyes correspondiente*/
package gal.uvigo.esei.aed1.Toma6.core;


public class Carta {

    private int numBueyes;
    private int numCarta;

    /**
     *
     * @param numBueyes Número de bueyes de la carta.
     * @param numCarta  Número de la carta-
     */
    public Carta(int numBueyes, int numCarta) {
        this.numBueyes = numBueyes;
        this.numCarta = numCarta;
    }
    /**
     *
     * @return Devuelve el número de bueyes que tiene asignados la carta.
     */
    public int getNumBueyes(){
        return numBueyes;
    }

    /**
     *
     * @return Devuelve el número de la carta.
     */
    public int getNumCarta(){
        return numCarta;
    }

    /**
     *
     * @return Devuelve la carta en formato: [Número: XXX] [Bueyes: XXX]
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[Número: ").append(getNumCarta())
                .append("] [Bueyes: ").append(getNumBueyes())
                .append("]\n");
        return sb.toString();
    }
}
