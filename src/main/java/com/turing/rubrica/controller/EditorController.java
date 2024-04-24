package com.turing.rubrica.controller;

import java.util.Vector;

import javax.swing.JOptionPane;

import com.turing.rubrica.model.Persona;
import com.turing.rubrica.view.EditorView;

public class EditorController 
{
	private Vector<Persona> rubrica;
	private Persona pToEdit;
    private EditorView view;
    private MainController mc;

    public EditorController(Vector<Persona> rubrica, EditorView view, Persona pToEdit, MainController mc) {
        this.rubrica = rubrica;
        this.view = view;
        this.pToEdit = pToEdit;
        this.mc = mc;

        // Aggiungiamo i listener ai pulsanti della EditorView
        view.getBtnSalva().addActionListener(e -> savePersona());
        view.getBtnAnnulla().addActionListener(e -> view.dispose());
    }

    private void savePersona() 
    {
    	String nome = view.getTxtNome().getText();
        String cognome = view.getTxtCognome().getText();
        String indirizzo = view.getTxtIndirizzo().getText();
        String telefono = view.getTxtTelefono().getText();
        int eta = Integer.parseInt(view.getTxtEta().getText());
        
        if(pToEdit == null)
        {
            Persona pToAdd = new Persona(nome, cognome, indirizzo, telefono, eta);
            for(Persona p : rubrica)
            {
            	if(p.equals(pToAdd))
            	{
            		JOptionPane.showMessageDialog(view, "La persona identificata è già in rubrica", "Errore", JOptionPane.ERROR_MESSAGE);
            		return;
            	}
            }
            rubrica.add(pToAdd);
            
        }
        else
        {
        	int indexToEdit = rubrica.indexOf(pToEdit);
        	Persona p = new Persona(nome, cognome, indirizzo, telefono, eta);
        	rubrica.set(indexToEdit, p);
        }
        mc.updateTable();
        view.dispose();
    }

}
