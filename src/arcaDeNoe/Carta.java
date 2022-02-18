/*
 * Programación interactiva
 * Autor: Joshua Sebastian Chicame Muñoz -202074121
 * Mini proyecto 2 - Arca De Noe
 */
package arcaDeNoe;

/**
 * The Class Carta.
 */
public class Carta {
	
	private int idImage;
	private int abierta;
	private boolean estado;
	
	
	/**
	 * Instantiates a new carta.
	 * Instacia la carta, al reverso(false), un contador que determina sí ha sido abierta y un identificador 
	 * @param idImage the id image
	 */
	public Carta(int idImage){
		this.estado = false;
		this.abierta =1;
		this.idImage = idImage;
	}


	/**
	 * Gets the id image.
	 * retorna el id de la imagen
	 * @return the id image
	 */
	public int getIdImage() {
		return idImage;
	}
	
	/**
	 * Checks if is estado.
	 * retorna el estado de la carta
	 * @return true, if is estado
	 */
	public boolean isEstado() {
		return estado;
	}
	

	/**
	 * Sets the estado.
	 * ajusta el estado de la carta
	 * @param estado the new estado
	 */
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	/**
	 * Gets the abierta.
	 * retorna el contado de la carta
	 * @return the abierta
	 */
	public int getAbierta() {
		return abierta;
	}

	/**
	 * Sets the abierta.
	 * ajusta el contador de la carta
	 * @param abierta the new abierta
	 */
	public void setAbierta(int abierta) {
		this.abierta = abierta;
	}

	
}
