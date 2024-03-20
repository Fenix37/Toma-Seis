/*
* Representa la baraja del juego Toma 6, en total 104 cartas, enumeradas del 1 al 104 con el número de bueyes
* correspondiente en función del valor de la misma (revisar condiciones en el enunciado del juego). 
* Estructura: se utilizará un TAD adecuado
* Funcionalidad: barajar las cartas, devolver la carta situada encima del montón de cartas
 */
package gal.uvigo.esei.aed1.Toma6.core;

import java.util.Stack;

public class Baraja {

    private Stack<Carta> baraja;

    /**
     * Crea la baraja predeterminada con las 104 cartas ordenadas de mayor a menor.
     */
    public Baraja() {
        baraja = new Stack();
        //Valor predeterminado
        for (int i = 1; i < 105; i++) {
            int numBueyes = 1;
            if (i % 5 == 0) {
                if (i % 10 == 0) {
                    numBueyes = 3;
                } else {
                    numBueyes = 2;
                }
            } else {
                if (i % 11 == 0) {
                    if (i == 55) {
                        numBueyes = 7;
                    } else {
                        numBueyes = 5;
                    }
                }
            }
            addCarta(new Carta(numBueyes, i));
        }
    }
    /**
     *Crea una baraja nueva con la pila que se le pasa como parámetro.
     * @param baraja
     */
    public Baraja(Stack<Carta> baraja){
        this.baraja = baraja;
    }
    public void addCarta(Carta carta) {
        baraja.push(carta);
    }
    /**
     *
     * @return Devuelve el número de cartas de la baraja.
     */
    public int getNumCartas() {
        return baraja.size();
    }
    /**
     *
     * @return Devuelve la última carta de la baraja sin modificarla.
     */
    public Carta getTop() {
        return baraja.peek();
    }
    /**
     *
     * @return Devuelve la última carta de la baraja y la elimina.
     */
    public Carta getPop() {
        return baraja.pop();
    }
    /**
     * Crea una baraja vacía.
     * @param esVacia Variable para que el constructor se diferencie del predeterminado
     */
    private Baraja(boolean esVacia){
        baraja = new Stack();
    }
    /**
     * 
     * @return Devuelve una baraja vacía
     */
    public static Baraja crearBarajaVacia() {
        return new Baraja(true);
    }
    /**
     *
     * @param carta: Carta a insertar en la baraja.
     */
    /**
     *
     * @return Devuelve true si la baraja está vacía y false si no
     */
    public boolean esVacia() {
        return baraja.isEmpty();
    }
    /**
     *
     * @return Devuelve la propia baraja ordenada vaciándola en el proceso.
     */
    public Baraja ordenarBaraja() {
        Baraja toret = Baraja.crearBarajaVacia();
        Baraja aux = Baraja.crearBarajaVacia();
        toret.addCarta(baraja.pop());
        while (!esVacia()) {
            if(getTop().getNumCarta() > toret.getTop().getNumCarta()) {
                toret.addCarta(getPop());
            }
            else{
                while(!toret.esVacia() && toret.getTop().getNumCarta() > getTop().getNumCarta()){
                    aux.addCarta(toret.getPop());
                }
                toret.addCarta(getPop());
                while(!aux.esVacia()){
                    toret.addCarta(aux.getPop());
                }
            }
        }
        return toret;
    }
    
    /**
     *Modifica this, barajándola de forma aleatoria.
     */
    public void barajar(){
        int tam = getNumCartas();
        Baraja aux1 = Baraja.crearBarajaVacia();
        Baraja aux2 = Baraja.crearBarajaVacia();
        for(int i = 0; i < 30; i++){
            while(!esVacia()){
                int res = (int)(Math.random()*23)%2;
                if(res == 0){
                    aux1.addCarta(getPop());
                }
                else{
                    aux2.addCarta(getPop());
                }
            }
            while(!aux1.esVacia()){
                if(!aux1.esVacia()) addCarta(aux1.getPop());
            }
            while(!aux2.esVacia()){
                if(!aux2.esVacia()) addCarta(aux2.getPop());
            }
        }
    }

    /**
     *
     * @return Devuelve todas las cartas de la baraja como carta1 \n carta2 \n carta3 etc.
     */
    @Override
    public String toString() {
        int tam = baraja.size();
        StringBuilder sb = new StringBuilder();
        Stack<Carta> aux = new Stack();
        for (int i = 0; i < tam; i++) {
            sb.append(baraja.peek().toString());
            aux.push(baraja.pop());
        }
        for (int i = 0; i < tam; i++) {
            baraja.push(aux.pop());
        }
        return sb.toString();
    }
}
