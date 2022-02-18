/*
 * Programación interactiva
 * Autor: Joshua Sebastian Chicame Muñoz -202074121
 * Mini proyecto 2 - Arca De Noe
 */

package arcaDeNoe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * The Class ControlArcaDeNoe.
 */
public class ControlArcaDeNoe {
	
	private int estadoJuego; 
	private int puntos;
	private int tiro;
	private int ronda;
	private ArrayList<Carta> cards = new ArrayList<Carta>();
	private ArrayList<Integer> arrayRandom = new ArrayList<Integer>();
	
	
	/**
	 * Instantiates a new control arca de noe.
	 */
	public ControlArcaDeNoe() {
		this.puntos=0;
		this.tiro = 1;
		createBoard();
	
	}
	
	
	/**
	 * Creates the color.
	 * Da un numero aleatorio del 0 al 7 (no retorna dos veces el mismo número)
	 * @return the int
	 */
	public int createColor() {

		Random aleatorio = new Random();
		int numberRandom = aleatorio.nextInt(7);
		
		Integer enterito = Integer.valueOf(numberRandom);
		arrayRandom.add(enterito);
	
		if(arrayRandom.size() == 1) {
			
		}else {
			for(int index = 0; index<arrayRandom.size()-1; index++) {
				if(arrayRandom.get(index) == enterito) {
					arrayRandom.remove(arrayRandom.size()-1);
					numberRandom = createColor();
				}else {
					
				}
			}
		}
		return numberRandom;	
	}
	
	
	
	/**
	 * Creates the board.
	 * crea el tablero de inicio con cuatro cartas en una posición aleatoria 
	 */
	public void createBoard() {
		
		ronda = 1;
		for(int index = 0; index < 4 ; index = index + 2) {
			
			int color = createColor();
			cards.add(new Carta(color));
			cards.add(new Carta(color));
	
		}
		shuffle(cards);
		
	}
	
	
	/**
	 * Shuffle.
	 * una funcion de collections que cambia de posicion los objetos de un arraylist
	 * @param tarjetas the tarjetas
	 */
	public void shuffle(List<Carta> tarjetas) {
		Collections.shuffle(tarjetas);
	}
	
	
    /**
     * reinia el tablero, volteando todas las cartas al reverso(todas las cartas del array su atributo estado le asigna false)
     * y agrega dos cartas
     * Reset board.
     */
    public void resetBoard() {
		
		estadoJuego = 0;
		tiro = 1;
		
		
		addCards();
	    
		for(Carta carta: cards) {
			if(carta.isEstado()) {
				carta.setEstado(false);
			}
		}
		
		ronda = ronda+1;
		
		shuffle(cards);
		
		for(Carta carta: cards) {
			carta.setAbierta(1);
		}
		
	}
    
	
	

	/**
	 * esta función se encarga de agregar las dos cartas pares
	 * Adds the cards.
	 * 
	 */
	public void addCards() {
		
		if(cards.size()<12) {
			
			int color = createColor();
			cards.add(new Carta(color));
			cards.add(new Carta(color));
		}		
	}
	
	
	/**
	 * Cartas iguales.
	 * determina si dos cartas son iguales, si dos cartas son iguales suma  un punto sino quita un punto
	 * @param indexCard1 the index card 1
	 * @param indexCard2 the index card 2
	 */
	public void cartasIguales(int indexCard1, int indexCard2) {
		if ( (getCard(indexCard1) == getCard(indexCard2)) &&  (indexCard1!=indexCard2) ) {
			
			cards.get(indexCard1).setEstado(true);
			cards.get(indexCard2).setEstado(true);
			
			puntos++;
			
		}else {
			
			cards.get(indexCard1).setEstado(false);
			cards.get(indexCard2).setEstado(false);
			
			//solo quita puntos cuando la carta ha sido abierta más de una vez
			if(cards.get(indexCard1).getAbierta()>1 || cards.get(indexCard2).getAbierta()>1) {
				puntos--;
			}
			tiro++;
		}

	}
	
	
	/**
	 * Determinar juego.
	 * determina el estado de juego, cuando todas las cartas han sido volteadas porque son pares pasa al estado 2
	 * 
	 * estado 1(siga volteando cartas)
	 * estado 2(nueva ronda)
	 */
	public void determinarJuego()
	{
		for(int index = 0; index<cards.size(); index++) 
		{
			if (cards.get(index).isEstado() == false) 
			{
				estadoJuego = 1;
				index = cards.size();
				
			}else if ( index + 1 == cards.size() && cards.get(cards.size()-1).isEstado() == true)
			{
				estadoJuego = 2;
			}
			
		}
	}


	/**
	 * Gets the estado juego.
	 * retorna el estado de juego 
	 * @return the estado juego
	 */
	public int getEstadoJuego() {
		return estadoJuego;
	}

	
	/**
	 * Sets the estado juego.
	 * ajuesta el estado de juego( es necesario porque cada vez que una persona reinicia el estado de juego tiene que empezar de nuevo)
	 * @param estadoJuego the new estado juego
	 */
	public void setEstadoJuego(int estadoJuego) {
		this.estadoJuego = estadoJuego;
	}


	/**
	 * Gets the card.
	 * entra una número(representa la posición) y retorna  el ID  de la carta en esa posición
	 * @param index the index
	 * @return the card
	 */
	public int getCard(int index) 
	{
		return cards.get(index).getIdImage();
	}
	
	
	/**
	 * Gets the estado card.
	 * entra la un número(representa la posición) y retorna el estado(el estado representa si esta la carta esta volteada o no) en esa posición
	 * @param index the index
	 * @return the estado card
	 */
	public boolean getEstadoCard(int index) 
	{
		return cards.get(index).isEstado();
	}
	
	
	/**
	 * Size.
	 * tamaño del array de cartas
	 * @return the int
	 */
	public int Size() {
		return cards.size();
	}
	
	
	/**
	 * Gets the puntos.
	 * retorna los puntps
	 * @return the puntos
	 */
	public int getPuntos() {
		return puntos;
	}
	
	/**
	 * Gets the card only.
	 * retorna la carta en la posición que introduce
	 * @param index the index
	 * @return the card only
	 */
	public Carta getCardOnly(int index) 
	{
		return cards.get(index);
	}

	
	/**
	 * Gets the ronda.
	 * retorna la ronda (es necesario porque en la primera ronda no pierde en 0)
	 * @return the ronda
	 */
	public int getRonda() {
		return ronda;
	}

	
}
