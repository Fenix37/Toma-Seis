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
     * @return Devuelve la propia baraja ordenada de menor a maior
     */
    public void ordenarBaraja() {
        Stack<Carta> aux1 = new Stack();
        Stack<Carta> aux2 = new Stack();
        /*o código vai metendo os elementos da baralla en aux1 ata que se atope 
        con algún que é maior ca aux1.peek,nese caso saca os elementos de aux1 a
        aux 2 ata atopar un sitio para o ultimo elemento da baralla.
        unha vez atopa ese sitio volca aux2 enriba de aux1
        */
        while(!this.baraja.empty()||!aux2.empty()){
            //esta parte é para o comezo
            if(!this.baraja.empty()){
                /*se aux1 está valeiro ou o ultimo elemento da baralla e menor co o último de aux1
                    aux1.push(this.baraja.pop());
                */
              if(aux1.empty()||this.baraja.peek().getNumCarta()<aux1.peek().getNumCarta()){
                if(aux2.empty()||aux2.peek().getNumCarta()<this.baraja.peek().getNumCarta()){
                    aux1.push(this.baraja.pop());
                    /*se o ultimo elemento de aux2 existe e (ultimo de aux1>ultimo de aux2>ultimo de baralla)
                    mete aux2 na baralla
                    */
                }else if((aux2.peek().getNumCarta()<aux1.peek().getNumCarta())&&
                        (aux2.peek().getNumCarta() >this.baraja.peek().getNumCarta()))
                {
                    aux1.push(aux2.pop());
                    //se non se da ningún dos casos anteriores o último elemento de baraja vai a aux2
                }else{
                aux2.push(this.baraja.pop());
            }
            }else{
                aux2.push(aux1.pop());
            }  
              //aquí volca os elementos que sobraran en aux2 ao rematarse a baralla
            }else{
                while(!aux2.empty()){
                    aux1.push(aux2.pop());
                }
            }
           // System.out.println(aux1+":\n");
        }
        System.out.println("fin");
        //a baralla quedou gardada en aux1 polo que cambiamos a referencia para que apunte a este último 
        this.baraja=aux1;
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