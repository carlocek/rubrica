package com.turing.rubrica.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.turing.rubrica.database.Database;
import com.turing.rubrica.model.Utente;
import com.turing.rubrica.view.LoginView;
import com.turing.rubrica.view.MainView;

public class LoginController 
{
	private LoginView view;
	
	public LoginController(LoginView view)
	{
		this.view = view;
		view.getBtnRegistrazione().addActionListener(e -> {
			try {
				register();
			} catch (IOException | SQLException e1) {
				e1.printStackTrace();
			}
		});
		view.getBtnLogin().addActionListener(e -> {
			try {
				login();
			} catch (IOException | SQLException e1) {
				e1.printStackTrace();
			}
		});
	}
	
	private void register() throws IOException, SQLException 
    {
		String username = view.getTxtUsername().getText();
        String password = view.getTxtPassword().getText();
        if(username.equals("") || password.equals(""))
        {
        	JOptionPane.showMessageDialog(view, "Username e Password non devono essere vuoti", "Errore", JOptionPane.ERROR_MESSAGE);
        	return;
        }
        else if(Database.authenticate(username, password)) 
        {
        	JOptionPane.showMessageDialog(view, "Credenziali già presenti, effettua login", "Errore", JOptionPane.ERROR_MESSAGE);
        	return;
        }
    	Utente uToAdd = new Utente(username, password);
    	Database.save(uToAdd);
    	JOptionPane.showMessageDialog(view, "Utente aggiunto con successo", "Conferma", JOptionPane.INFORMATION_MESSAGE);
    }
	
	private void login() throws IOException, SQLException 
    {
		String username = view.getTxtUsername().getText();
        String password = view.getTxtPassword().getText();
        if(!Database.authenticate(username, password)) 
        {
        	JOptionPane.showMessageDialog(view, "Credenziali errate", "Errore", JOptionPane.ERROR_MESSAGE);
        	return;
        }
        MainView mainView = new MainView();
		MainController mainController = new MainController(mainView);
		mainView.setVisible(true);
		view.dispose();
    }

}
