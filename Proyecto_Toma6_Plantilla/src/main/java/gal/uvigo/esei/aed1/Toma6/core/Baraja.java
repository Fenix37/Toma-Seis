
/*
* Representa la baraja del juego Toma 6, en total 104 cartas, enumeradas del 1 al 104 con el número de bueyes
* correspondiente en función del valor de la misma (revisar condiciones en el enunciado del juego). 
* Estructura: se utilizará un TAD adecuado
* Funcionalidad: barajar las cartas, devolver la carta situada encima del montón de cartas
 */
package gal.uvigo.esei.aed1.Toma6.core;

import java.util.Stack;

public class Baraja {

    Stack<Carta> baraja;

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
            baraja.add(new Carta(numBueyes, i));
        }
    }

    public Baraja(boolean esVacia) {
        baraja = new Stack();
    }
    public Baraja(Stack<Carta> baraja){
        this.baraja = baraja;
    }
    public void addCarta(Carta carta) {
        baraja.push(carta);
    }

    public int getNumCartas() {
        return baraja.size();
    }

    public Carta getTop() {
        return baraja.peek();
    }

    //Devuelve el ultimo objeto y lo borra
    public Carta getPop() {
        return baraja.pop();
    }

    public boolean esVacia() {
        return baraja.isEmpty();
    }

    public Baraja ordenarBaraja() {
        int num = baraja.size();
        Stack <Carta> toret = new Stack();
        Stack <Carta> aux = new Stack();
        toret.add(baraja.pop());
        while (!baraja.empty()) {
            if(baraja.peek().getNumCarta() > toret.peek().getNumCarta()) {
                toret.add(baraja.pop());
            }
            else{
                while(toret.peek().getNumCarta() > baraja.peek().getNumCarta() || toret.empty()){
                    aux.add(toret.pop());
                }
                toret.add(baraja.pop());
                while(!aux.empty()){
                    toret.add(aux.pop());
                    
                }
            }
        }
        return new Baraja(toret);
    }

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
