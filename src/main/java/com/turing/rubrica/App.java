package com.turing.rubrica;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;

import com.turing.rubrica.model.Persona;
import com.turing.rubrica.controller.MainController;
import com.turing.rubrica.view.MainView;

public class App 
{
	public static void main(String[] args) throws IOException, SQLException 
    {
		MainView mainView = new MainView();
		MainController mainController = new MainController(mainView);
		mainView.setVisible(true);
    }
}
