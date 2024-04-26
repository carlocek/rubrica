package com.turing.rubrica.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.turing.rubrica.model.Persona;
import com.turing.rubrica.view.EditorView;
import com.turing.rubrica.database.Database;

public class EditorController 
{
	private Persona pToEdit;
    private EditorView view;
    private MainController mc;

    public EditorController(EditorView view, Persona pToEdit, MainController mc) {
        this.view = view;
        this.pToEdit = pToEdit;
        this.mc = mc;

        view.getBtnSalva().addActionListener(e -> {
			try {
				savePersona();
			} catch (IOException | SQLException e1) {
				e1.printStackTrace();
			}
		});
        view.getBtnAnnulla().addActionListener(e -> view.dispose());
    }

    private void savePersona() throws IOException, SQLException 
    {
    	String nome = view.getTxtNome().getText();
        String cognome = view.getTxtCognome().getText();
        String indirizzo = view.getTxtIndirizzo().getText();
        String telefono = view.getTxtTelefono().getText();
        if(!telefono.chars().allMatch(Character::isDigit))
    	{
    		JOptionPane.showMessageDialog(view, "Il campo Telefono deve contenere solo cifre", "Errore", JOptionPane.ERROR_MESSAGE);
    		return;
    	}
        int eta;
        try {
        	eta = Integer.parseInt(view.getTxtEta().getText());
        } catch(NumberFormatException e) {
        	JOptionPane.showMessageDialog(view, "Il campo Eta deve contenere un numero", "Errore", JOptionPane.ERROR_MESSAGE);
    		return;
        }
        Persona pToAdd = new Persona(nome, cognome, indirizzo, telefono, eta);
        
        if(pToEdit == null)
        {
            for(Persona p : Database.getAll())
            {
            	if(p.getTelefono().equals(pToAdd.getTelefono()))
            	{
            		JOptionPane.showMessageDialog(view, "Una persona con lo stesso telefono è già presente in rubrica", "Errore", JOptionPane.ERROR_MESSAGE);
            		return;
            	}
            }
            Database.save(pToAdd);
        }
        else
        {
        	Database.update(pToEdit, pToAdd);
        }
        mc.updateTable();
        view.dispose();
    }

}
