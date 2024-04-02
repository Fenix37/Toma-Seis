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

    /**
     *
     * @return Devuelve la propia baraja ordenada de menor a maior
     */

    /**
     * Modifica this, barajándola de forma aleatoria.
     */
    public void barajar() {
        long semilla = System.currentTimeMillis();;
        Random random = new Random();
        int randomNumber;
        Stack<Carta> aux1 = new Stack<>();
        Stack<Carta> aux2 = new Stack<>();
        /*execútase 4 veces para asegurarse de mezclar ben todo(se se requerise 
        máis aleatoriedade aumentar o número de iteracións)*/
        for (int i = 0; i < 20; i++) {
            Random aleatorio = new Random(semilla);
            //separa a baralla en 2 stacks de tamaño aleatorio
            randomNumber = random.nextInt(getNumCartas()/2);
            for (int j = 0; j < randomNumber; j++) {
                aux1.push(this.baraja.pop());
            }
            while (!this.baraja.empty()) {
                aux2.push(this.baraja.pop());
            }
            //devolve os estaques a baralla mezclados ao orixinal
            while(!aux1.empty()||!aux2.empty()){
                if(!aux1.empty()){
                    baraja.push(aux1.pop());
                }
                if(!aux2.empty()){
                    baraja.push(aux2.pop());
                }
            }
            /*
            while (!aux1.empty()) {
                this.baraja.push(aux1.pop());
            }
            while (!aux2.empty()) {
                this.baraja.push(aux2.pop());
            }
            */
        }
    }

    /**
     * Introduce a carta de forma que a baralla quede ordeada de menor a maior
     * NON SERVE PARA ORDEAR UNHA BARALLA XA CREADA,se a baralla está desordeada 
     * introducirá a carta despóis do primer elemento cun número de carta maior que 
     * o parámetro.
     *
     * @param novaCarta
     */
    public void introducirOrdenado(Carta novaCarta) {
        Stack<Carta> aux = new Stack<>();
        boolean introducida = false;
        //se a baralla é baleira introducimos a carta si ou si.
        if (this.esVacia()) {
            this.addCarta(novaCarta);
        } else {
            //vai sacando elementos da baralla e metendos en aux ata que atopa un maior que novaCarta
            while (!introducida) {
                if (this.getTop().getNumCarta() < novaCarta.getNumCarta()) {
                    aux.push(this.getPop());
                } else {
                    this.addCarta(novaCarta);
                    introducida = true;
                }

            }
            //volve a introducir todos os elementos de aux que(supon) xa están ordeados
            while (!aux.empty()) {
                this.addCarta(aux.pop());
            }
        }
    }

    /**
     *
     * @return Devuelve todas las cartas de la baraja como carta1 \n carta2 \n
     * carta3 etc.
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
