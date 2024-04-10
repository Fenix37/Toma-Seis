/*
* Representa la baraja del juego Toma 6, en total 104 cartas, enumeradas del 1 al 104 con el número de bueyes
* correspondiente en función del valor de la misma (revisar condiciones en el enunciado del juego). 

@@ -7,9 +6,136 @@
 */
package gal.uvigo.esei.aed1.Toma6.core;

import java.util.Random;
import java.util.Stack;

public class Baraja {

    private Stack<Carta> baraja;

    /**
     * Crea la baraja predeterminada con las 104 cartas ordenadas de mayor a
     * menor.
     */
    public Baraja() {
        baraja = new Stack();
        //Valor predeterminado
        for (int i = 1; i <= 104; i++) {
            int numBueyes = 1;
            if (i == 55) {
                numBueyes = 7;
            } else if (i % 5 == 0) {
                if (i % 10 == 0) {
                    numBueyes = 3;
                } else {
                    numBueyes = 2;
                }
            } else {
                if (i % 11 == 0) {
                    numBueyes = 5;
                }
            }
            addCarta(new Carta(numBueyes, i));
        }
    }

    /**
     * Crea una baraja nueva con la pila que se le pasa como parámetro.
     *
     * @param baraja
     */
    public Baraja(Stack<Carta> baraja) {
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
     *
     * @param esVacia Variable para que el constructor se diferencie del
     * predeterminado
     */
    private Baraja(boolean esVacia) {
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
<<<<<<< Updated upstream

    /**
     *
     * @return Devuelve la propia baraja ordenada de menor a maior
     */
    /**
     * Modifica this, barajándola de forma aleatoria.
     */
    public void barajar() {
        Random random = new Random();
        int randomNumber;
        Baraja aux1 = Baraja.crearBarajaVacía();
        Baraja aux2 = Baraja.crearBarajaVacía();
        /*execútase 4 veces para asegurarse de mezclar ben todo(se se requerise 
        máis aleatoriedade aumentar o número de iteracións)*/
        for (int i = 0; i < 20; i++) {
            //separa a baralla en 2 stacks de tamaño aleatorio
            randomNumber = random.nextInt(getNumCartas() / 2);
            for (int j = 0; j < randomNumber; j++) {
                aux1.addCarta(this.baraja.pop());
            }
            while (!this.baraja.empty()) {
                aux2.addCarta(this.baraja.pop());
            }
            //devolve os estaques a baralla mezclados ao orixinal
            while (!aux1.esVacia() || !aux2.esVacia()) {
                if (!aux1.esVacia()&&random.nextInt(2)==1) {
                    baraja.push(aux1.getPop());
                }

                if (!aux2.esVacia()&&random.nextInt(2)==0) {
                    baraja.push(aux2.getPop());
                }

            }
        }
    }

=======
>>>>>>> Stashed changes
    /**
     * Introduce a carta de forma que a baralla quede ordeada de menor a maior
     * NON SERVE PARA ORDEAR UNHA BARALLA XA CREADA,se a baralla está desordeada
     * introducirá a carta despóis do primer elemento cun número de carta maior
     * que o parámetro.
     *
     * @param novaCarta
     */

    /**
     *
     * @return Devuelve todas las cartas de la baraja como carta1 \n carta2 \n
     * carta3 etc.
     */
    @Override
    public String toString() {
        int tam = getNumCartas();
        StringBuilder sb = new StringBuilder();
        Baraja aux = Baraja.crearBarajaVacía();
        for (int i = 0; i < tam; i++) {
            sb.append(this.getPop());
            aux.addCarta(this.getTop());
        }
        for (int i = 0; i < tam; i++) {
            this.addCarta(aux.getTop());
        }
        return sb.toString();
    }
}
