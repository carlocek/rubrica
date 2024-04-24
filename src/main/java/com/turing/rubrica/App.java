package com.turing.rubrica;

import java.util.Vector;

import com.turing.rubrica.model.Persona;
import com.turing.rubrica.controller.MainController;
import com.turing.rubrica.view.MainView;

public class App 
{
	
	public static void main(String[] args) 
    {
		Vector<Persona> rubrica = new Vector<Persona>();
		Persona p1 = new Persona("carlo", "ceccherelli", "via gg 13", "0000000000", 26);
		Persona p2 = new Persona("emily", "afeltra", "via cc 31", "1111111111", 25);
		rubrica.add(p1);
		rubrica.add(p2);
		
		MainView mainView = new MainView(rubrica);
		MainController mainController = new MainController(rubrica, mainView);
		mainView.setVisible(true);
	    
		
    	
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                new App();
//            }
//        });
    }
}
