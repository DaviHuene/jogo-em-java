
package jogo;

import jplay.GameImage;
import jplay.Keyboard;
import jplay.Scene;
import jplay.URL;
import jplay.Window;

public class Endgame extends Cenario{
	   private Window janela;
	    private Keyboard teclado;
	 
	    public Endgame (Window window) {
	        janela = window;
	        new Scene();
	        GameImage plano = new GameImage(URL.sprite("end.png"));
	       
	        teclado = janela.getKeyboard();
	        Som.play("MUSICA ÉPICA SEM DIREITOS AUTORAIS.wav");
	       
 
	       
	     
	    while (true) {
            // Draw the background image of the menu
            plano.draw();
            // Update the window
           janela.update();

            // Check if the "Enter" key was pressed
            if (teclado.keyDown(Keyboard.ENTER_KEY)) {
                // If so, start the first scene of the game
            	  System.exit(0);
            	 janela.update();
             // new  ();
            }
            if (teclado.keyDown(Keyboard.ESCAPE_KEY)) {
                System.exit(0);
            }
	    }
	    }

}
	        
	 

