
/*
 * Programación interactiva
 * Autor: Joshua Sebastian Chicame Muñoz -202074121
 * Mini proyecto 2 - Arca De Noe
 */
package arcaDeNoe;

/*
 * Programación interactiva
 * Autor: Joshua Sebastian Chicame Muñoz -202074121
 * Mini proyecto 2 - Arca De Noe
 */
import java.awt.EventQueue;

import javax.swing.JOptionPane;
import javax.swing.UIManager;


public class PrincipalArcaDeNoe {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//control de las excepciones
		try {
			String javaLookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(javaLookAndFeel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Opps hay una daño en la JVM");
		} 
		
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				// instaci la vista
				VistaGUI myVista = new VistaGUI();
			}	
		});

	}

}