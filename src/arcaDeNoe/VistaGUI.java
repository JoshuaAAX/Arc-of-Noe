/*
 * Programación interactiva
 * Autor: Joshua Sebastian Chicame Muñoz -202074121
 * Mini proyecto 2 - Arca De Noe
 */
package arcaDeNoe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


import misComponentes.Titulos;

// TODO: Auto-generated Javadoc
/**
 * The Class VistaGUI.
 */
public class VistaGUI  extends JFrame {

	private Escucha escucha;
	private JPanel zonaJuego, zonaResultados;
	private List<JButton> botones = new ArrayList<JButton>();
	private JLabel puntos;
	private JTextField valorPuntos;
	private Titulos titulo;
	private JButton salir;
	private ImageIcon imagen;
	private ControlArcaDeNoe controlArcaDeNoe;
	private Icon icono; // = new ImageIcon("src/imagenes/iconEnd.png");
	
	private int contador = 0 ;
	private int indexCard1=0;   
	private int indexCard2=0;   
	

	private Temporizador temporizador = new Temporizador();
	
	private JFrame vistaGridBagLayout;
	
	
	/**
	 * Instantiates a new vista GUI.
	 * ajusta los atributos de la ventana 
	 */
	public VistaGUI() 
	{
		
	    initGUI();
	
	    this.setUndecorated(true);
	    this.setTitle("Juego Del Arca De Noe");
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Inits the GUI.
	 */
	private void initGUI() {
		

		//set up window container - Layout
		this.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints contraints = new GridBagConstraints();
		
		//create Control, Listener and supports classes
		escucha = new Escucha();
		controlArcaDeNoe = new ControlArcaDeNoe();
		vistaGridBagLayout=this;
	
		
		//set up Graphic Components
		
	    //title
		titulo = new Titulos(" Juego Del Arca de noe ", 17, new Color(56,57,59));
		titulo.addMouseListener(escucha);
		titulo.addMouseMotionListener(escucha);
		contraints.gridx=0;
		contraints.gridy=0;	
		contraints.gridwidth=1;
		contraints.fill=GridBagConstraints.HORIZONTAL;
		
		add(titulo,contraints);
		
		
		//exit
		salir = new JButton();
		imagen = new ImageIcon("src/imagenes/salir.png");
        salir.setIcon(imagen);
		salir.setBorder(null);
		salir.setFocusPainted(false);
        salir.setBackground(new Color(56,57,59));
		salir.addActionListener(escucha);
		contraints.gridx=1;
		contraints.gridy=0;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		contraints.fill=GridBagConstraints.NONE;
		
		add(salir, contraints);

		
		//game zone
		zonaJuego = new JPanel();
		zonaJuego.setLayout(new GridLayout(2, 6));
		
		
		boardCards();
		
        contraints.gridx=0;
		contraints.gridy=1;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		contraints.fill=GridBagConstraints.NONE;
		contraints.anchor=GridBagConstraints.CENTER;
		
		add(zonaJuego,contraints);
		
		
		//results zone
		zonaResultados = new JPanel();
		zonaResultados.setLayout(new GridLayout(0,1));
		puntos = new JLabel("Puntos");
		puntos.setHorizontalAlignment(JLabel.CENTER);
		valorPuntos = new JTextField();
		valorPuntos.setHorizontalAlignment(JLabel.CENTER);
		valorPuntos.setEditable(false);
		valorPuntos.setText(String.valueOf(controlArcaDeNoe.getPuntos()));
		zonaResultados.add(puntos);
		zonaResultados.add(valorPuntos);
		contraints.gridx=1;
		contraints.gridy=1;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		contraints.fill=GridBagConstraints.NONE;
		contraints.anchor=GridBagConstraints.PAGE_START;
		
		add(zonaResultados,contraints);
		
			
	}
	
	
	/**
	 * Adds the card.
	 * agrega ubn  boto(carta) al tablero sin bordes 
	 */
	private void addCard() {
		if(botones.size()<12) {
			botones.add(new JButton());
			botones.get(botones.size()-1).setBorder(null);
			botones.get(botones.size()-1).setFocusPainted(false);
			zonaJuego.add(botones.get(botones.size()-1));
		}
		
	}
	
	
	/**
	 * Board cards.
	 * crea el tablero con cuatro botones(cartas) y les asigna la imagen de reverso
	 */
	private void boardCards() {
		
		for(int index = 0; index<controlArcaDeNoe.Size() ; index++ ) {
			botones.add(new JButton());
				
			ImageIcon cartas = new ImageIcon("src/imagenes/reverse.png");
			botones.get(index).setIcon(cartas);
			botones.get(index).setBorder(null);
			botones.get(index).setFocusPainted(false);
				
			zonaJuego.add(botones.get(index));
		}	
		agregarEscuchas();	
		
	}
	
	
	

	
	
	
	/**
	 * Agregar escuchas.
	 * agrega escuchas recorre el array de botones y agrega los escucha
	 */
	private void agregarEscuchas() {

		for(int index = 0; index<controlArcaDeNoe.Size() ; index++ ) {//
			botones.get(index).addActionListener(escucha);
		}	
		
	}
	
	
	/**
	 * New board.
	 * cada vez que se pasa de ronda se reinica el tablero, se voltean las cartas y se agregan dos botones(cartas) más  y se agregan los escuchas necesarios
	 * se reajusta el tamaño de la ventana y se situa en el centro
	 */
	private void newBoard() {	
		

		indexCard1=0;   
		indexCard2=0; 
		contador=0;
		addCard();
		addCard();
		
		for(int index = 0; index<controlArcaDeNoe.Size(); index++ ) {
			
			ImageIcon cartas = new ImageIcon("src/imagenes/reverse.png");
			botones.get(index).setIcon(cartas);
		}	
		
		agregarEscuchas();
		
	    pack();
	    this.setLocationRelativeTo(null);

	}
	
	                                                                                           
	/**
	 * Voltear.
	 * el atributo contador representa click, si el contador esta 0 es el primer click si esta en 1 es el segundo, 
	 * esta funcion se encarga de realizar todas acciones que ocurre cuando se voltean las dos cartas pares
	 * @param index the index
	 */
	private void voltear(int index)                                                                     
	{    
		//cuando se da el prime click y si la carta esta en reversa(estado = false) lo que hace es voltear la carta(estado = true) y pasa al siguiente click                                                                                          
		if(contador==0 &&  controlArcaDeNoe.getEstadoCard(index)==false) {                                     
                                                                                                   
			imagen = new ImageIcon("src/imagenes/"+ controlArcaDeNoe.getCard(index)+".png");                   
			botones.get(index).setIcon(imagen);   
			//se quita el escucha para que no puedan volver dar click
			botones.get(index).removeActionListener(escucha);                                          
			//guarda la posicion de la boton(carta) del primer click
			indexCard1= index;   

			
			contador++;                                                                             
		}  
		//cuando se da el segundo clik y la segunda carta esta en reversa(estado = false), voltea la carta(estado = true) 
        //el contador se aumenta y pasa a ser dos eso significa que se dieron los dos click o sea ya se voltearon dos cartas
		//y llama a una funcion que determina si son iguales,
		else if(contador==1 && controlArcaDeNoe.getEstadoCard(index)==false) {                                
                                                                                                                                                                                    
			imagen = new ImageIcon("src/imagenes/"+controlArcaDeNoe.getCard(index)+".png");                   
			botones.get(index).setIcon(imagen);           
			//se quita el escucha para que no puedan volver dar click
			botones.get(index).removeActionListener(escucha);   
			//guarda la posicion de la boton(carta) del segundo click
			indexCard2= index;                                                                          
			
			contador++;                                                                             
			/**
			llama a la funcion de cartas iguales, 
			si son iguales el estado de las dos queda en true o sea volteadas
			si no son iguales pues su estado  en false o sea en reverso
			**/
			controlArcaDeNoe.cartasIguales(indexCard1,indexCard2);
			
			//ajusta los puntos despues de que se voltearon las dos cartas
			valorPuntos.setText(String.valueOf(controlArcaDeNoe.getPuntos()));
			
			//el temporizador soluciona el problema que pasa cuando se da por segunda vez click, voltea las cartas o las saca del tablero al instante, 
			// al determinar si son iguales o no, la acción siguiente actua rapido
			temporizador.tiempo();
			                                                                                                                                                                                                                                                  
		}                                                                                           
	                                                                                                
   }         
	
   /**
    * Ventana perdida.
    * una ventana que pregunta si quiere reiniciar el juego o salir
    */
   private void ventanaPerdida() {
	   
	   valorPuntos.setText(String.valueOf(0));
	   icono = new ImageIcon("src/imagenes/iconEnd.png");
	   
	   String[] botones = {"Reiniciar", "Salir"};
	   
	   int opciones = JOptionPane.showOptionDialog(null, 
			                                      "¿Quieres Reiniciar o Salir? ", 
			                                      "opciones de juego", 
			                                      JOptionPane.DEFAULT_OPTION, 
			                                      JOptionPane.QUESTION_MESSAGE, 
			                                      icono, 
			                                      botones, 
			                                      botones[0]);
	   
	   if(opciones == 0) {
		   System.gc();
		   dispose();
		   VistaGUI nuevaVista = new VistaGUI();
	   } else {
		   System.exit(0);
	   }			
       
	   
	   /**
	   int opcion = JOptionPane.showConfirmDialog(null, "	¿Quieres reiniciar? si no sales del juego",
               											"Opciones",
               											JOptionPane.YES_NO_OPTION, 
               											JOptionPane.QUESTION_MESSAGE
               											);
	   													

	   if(opcion == 0) {
		   System.out.println("Opcion A");
		   System.gc();
		   dispose();
		   VistaGUI nuevaVista = new VistaGUI();
	   }else {
		   System.exit(0);
	   }**/
   }
                                                                                                   
	
	/**
	 * The Class Escucha.
	 */
	private class Escucha extends MouseAdapter implements ActionListener {

		/** The y. */
		private int x, y;
		
		/**
		 * Action performed.
		 *
		 * @param eventAction the event action
		 */
		@Override
		public void actionPerformed(ActionEvent eventAction) {
			
			if(eventAction.getSource()==salir) {
				
				ventanaPerdida();
			
			}

			for(int unBoton = 0; unBoton<controlArcaDeNoe.Size(); unBoton++) {              
				
				if(eventAction.getSource() == botones.get(unBoton)) {                                     
                    //llama a voltear cuando se da click a una boton(carta)
					voltear(unBoton);                                                                                                                                           		                                                                            
			}   	
			
		}
	
	  }
		
	  /**
  	 * Mouse dragged.
  	 *
  	 * @param eventMouseMotion the event mouse motion
  	 */
  	public void mouseDragged(MouseEvent eventMouseMotion) 
	  {
			// TODO Auto-generated method stub
			setLocation(vistaGridBagLayout.getLocation().x+eventMouseMotion.getX()-x,
				        vistaGridBagLayout.getLocation().y+eventMouseMotion.getY()-y);
			
	  }
	  
	  /**
  	 * Mouse pressed.
  	 *
  	 * @param eventMouse the event mouse
  	 */
  	public void mousePressed(MouseEvent eventMouse) 
		{
			// TODO Auto-generated method stub
			x = eventMouse.getX();
			y = eventMouse.getY();
			
		}
	
   }
	
	
   /**
    * The Class Temporizador.
    */
   public class Temporizador {
	
		/**
		 * Tiempo.
		 */
		public void tiempo() {
			Timer timer = new Timer();
			TimerTask voltearTiempo = new TimerTask() {

				public void run() {
	 
					//si la primera carta es falsa y la segunda es falsa y ya se dieron los dos clik necesarios,
					//
					if (controlArcaDeNoe.getEstadoCard(indexCard1)==false && controlArcaDeNoe.getEstadoCard(indexCard2)==false && contador==2 )
					{
						//las cartas vuelven a estar en reversa
						imagen = new ImageIcon("src/imagenes/reverse.png");
						botones.get(indexCard1).setIcon(imagen);
						botones.get(indexCard2).setIcon(imagen);
						//se reinica el contador de click para que pueda volver a clickear dos cartas
						contador = 0;
						//agrega de nuevo lo escucha
						botones.get(indexCard1).addActionListener(escucha);	
						botones.get(indexCard2).addActionListener(escucha);	
						
						//cada carta tiene un atributa que cuenta las veces que se ha volteado, este código suma ese atributo
						controlArcaDeNoe.getCardOnly(indexCard1).setAbierta(controlArcaDeNoe.getCardOnly(indexCard1).getAbierta()+1);
						controlArcaDeNoe.getCardOnly(indexCard2).setAbierta(controlArcaDeNoe.getCardOnly(indexCard2).getAbierta()+1);
						
						
						//cuando voltea las dos cartas, y los puntos son menor a 0 -> pierde
						//la primera ronda no pierde en 0 porque empieza en ese estado, así que no deja pasar hasta que tenga una buena ronda
						if(controlArcaDeNoe.getPuntos() <= 0) {
							if(controlArcaDeNoe.getRonda() >1) {
								ventanaPerdida();
							} else if(controlArcaDeNoe.getPuntos() <= -1) {
								ventanaPerdida();	
							}											
						}
							
				

					} else {
                        
						//en caso de que tenga los mismo id pues salen del tablero, se les asigna una imagen null, y NO se le agregan los escucha
					    imagen = new ImageIcon("src/imagenes/null.png");
						botones.get(indexCard1).setIcon(imagen);
						botones.get(indexCard2).setIcon(imagen);
						//se reinica el contador de click para que pueda volver a clickear dos cartas
						contador=0;
						//en que estado esta el juego para así agregar la nueva ronda el control
						controlArcaDeNoe.determinarJuego();
						
						//si esta el control en estado dos reinia el tablero con dos botones(cartas) más
						if(controlArcaDeNoe.getEstadoJuego() == 2) {
							
							//se reinicia en control
							controlArcaDeNoe.resetBoard();
							//se establece el nuevo tablero
							newBoard();	
			
						}	
					}
				}
			};
			
			timer.schedule(voltearTiempo,600);
			}

		}	
	
}