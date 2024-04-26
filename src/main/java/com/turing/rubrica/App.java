package com.turing.rubrica;

import java.io.IOException;
import java.sql.SQLException;

import com.turing.rubrica.controller.LoginController;
import com.turing.rubrica.view.LoginView;

public class App 
{
	public static void main(String[] args) throws IOException, SQLException 
    {
		LoginView loginView = new LoginView();
        LoginController loginController = new LoginController(loginView);
        loginView.setVisible(true);
    }
}
