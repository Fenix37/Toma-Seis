/*
* Representa la baraja del juego Toma 6, en total 104 cartas, enumeradas del 1 al 104 con el número de bueyes
* correspondiente en función del valor de la misma (revisar condiciones en el enunciado del juego). 

@@ -7,9 +6,136 @@
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
        int numBueyes = 1;
        for (int i = 1; i < 104; i++) {
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
    public static Baraja crearBarajaVacía() {
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
    public void ordenarBaraja() {

        
    }
    /**
     *Modifica this, barajándola de forma aleatoria.
     */
    public void barajar() {
        int random = 0;
        int numCartas= baraja.size();
        Baraja aux1 = new Baraja(true);
        Baraja aux2 = new Baraja(true);
        while (!this.esVacia()) {
            aux1.addCarta(this.getPop());
            if(!this.esVacia()){
                aux2.addCarta(this.getPop());
            }
            
        }
        while (this.getNumCartas()<numCartas) {
            // da un valor aleatorio a random que pode ser 0 ou 1
            random = ((int) (Math.random() * 10)) % 2;
            if (random == 1&&!aux1.esVacia()||aux2.esVacia()) {
                this.addCarta(aux1.getPop());
            } else  {
                this.addCarta(aux2.getPop());
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
            sb.append(baraja.peek());
            aux.push(baraja.pop());
        }
        for (int i = 0; i < tam; i++) {
            baraja.push(aux.pop());
        }
        return sb.toString();
    }
}