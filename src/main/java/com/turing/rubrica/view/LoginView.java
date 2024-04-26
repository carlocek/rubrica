package com.turing.rubrica.view;

import javax.swing.*;

import java.awt.*;

public class LoginView extends JFrame 
{
	
	private static final long serialVersionUID = 1L;
	private JLabel lblUsername, lblPassword;
    private JTextField txtUsername, txtPassword;
    private JButton btnLogin, btnRegistrazione;
   
    public LoginView() 
    {    	
        setTitle("Login");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridLayout(2, 2, 10, 10));  // 5 righe, 2 colonne, spazio tra i componenti
    	mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        lblUsername = new JLabel("Username:");
        setTxtUsername(new JTextField());

        lblPassword = new JLabel("Password:");
        setTxtPassword(new JTextField());


        mainPanel.add(lblUsername);
        mainPanel.add(getTxtUsername());
        mainPanel.add(lblPassword);
        mainPanel.add(getTxtPassword());
        
        btnLogin = new JButton("Login");
        btnRegistrazione = new JButton("Registrati");
        
        JPanel btnPanel = new JPanel();
        btnPanel.add(btnLogin);
        btnPanel.add(btnRegistrazione);
        
        add(mainPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
    }

	public JTextField getTxtUsername() 
	{
		return txtUsername;
	}

	public void setTxtUsername(JTextField txtUsername) 
	{
		this.txtUsername = txtUsername;
	}

	public JTextField getTxtPassword() 
	{
		return txtPassword;
	}

	public void setTxtPassword(JTextField txtPassword) 
	{
		this.txtPassword = txtPassword;
	}
	
	public JButton getBtnLogin() 
	{
		return btnLogin;
	}
	
	public JButton getBtnRegistrazione() 
	{
		return btnRegistrazione;
	}
}
