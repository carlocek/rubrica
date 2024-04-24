package com.turing.rubrica.view;

import javax.swing.*;

import java.awt.*;

public class EditorView extends JFrame 
{
	
	private static final long serialVersionUID = 1L;
	private JLabel lblNome, lblCognome, lblIndirizzo, lblTelefono, lblEta;
    private JTextField txtNome, txtCognome, txtIndirizzo, txtTelefono, txtEta;
    private JButton btnSalva, btnAnnulla;
   
    public EditorView() 
    {    	
        setTitle("Editor Persona");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Chiude solo la finestra corrente

        JPanel mainPanel = new JPanel(new GridLayout(5, 2, 10, 10));  // 6 righe, 2 colonne, spazio tra i componenti
    	mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        lblNome = new JLabel("Nome:");
        setTxtNome(new JTextField());

        lblCognome = new JLabel("Cognome:");
        setTxtCognome(new JTextField());

        lblIndirizzo = new JLabel("Indirizzo:");
        setTxtIndirizzo(new JTextField());

        lblTelefono = new JLabel("Telefono:");
        setTxtTelefono(new JTextField());

        lblEta = new JLabel("Età:");
        setTxtEta(new JTextField());

        mainPanel.add(lblNome);
        mainPanel.add(getTxtNome());
        mainPanel.add(lblCognome);
        mainPanel.add(getTxtCognome());
        mainPanel.add(lblIndirizzo);
        mainPanel.add(getTxtIndirizzo());
        mainPanel.add(lblTelefono);
        mainPanel.add(getTxtTelefono());
        mainPanel.add(lblEta);
        mainPanel.add(getTxtEta());
        
        btnSalva = new JButton("Salva");
        btnAnnulla = new JButton("Annulla");
        
        JPanel btnPanel = new JPanel();
        btnPanel.add(btnSalva);
        btnPanel.add(btnAnnulla);
        
        add(mainPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
    }

	public JTextField getTxtNome() 
	{
		return txtNome;
	}

	public void setTxtNome(JTextField txtNome) 
	{
		this.txtNome = txtNome;
	}

	public JTextField getTxtCognome() 
	{
		return txtCognome;
	}

	public void setTxtCognome(JTextField txtCognome) 
	{
		this.txtCognome = txtCognome;
	}
	
	public JTextField getTxtIndirizzo() 
	{
		return txtIndirizzo;
	}

	public void setTxtIndirizzo(JTextField txtIndirizzo) 
	{
		this.txtIndirizzo = txtIndirizzo;
	}

	public JTextField getTxtTelefono() 
	{
		return txtTelefono;
	}

	public void setTxtTelefono(JTextField txtTelefono) 
	{
		this.txtTelefono = txtTelefono;
	}
	
	public JTextField getTxtEta() 
	{
		return txtEta;
	}

	public void setTxtEta(JTextField txtEta) 
	{
		this.txtEta = txtEta;
	}

	public JButton getBtnSalva() 
	{
		return btnSalva;
	}
	public JButton getBtnAnnulla() 
	{
		return btnAnnulla;
	}
}
