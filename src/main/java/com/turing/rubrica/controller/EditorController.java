package com.turing.rubrica.controller;

import java.awt.HeadlessException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;

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
        int eta = Integer.parseInt(view.getTxtEta().getText());
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
