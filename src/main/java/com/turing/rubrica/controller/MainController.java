package com.turing.rubrica.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.turing.rubrica.database.Database;
import com.turing.rubrica.model.Persona;
import com.turing.rubrica.view.EditorView;
import com.turing.rubrica.view.MainView;

public class MainController 
{
	private MainView view;
	
	public MainController(MainView view)
	{
		this.view = view;
		view.getBtnNuovo().addActionListener(e -> openEditor());
		view.getBtnModifica().addActionListener(e -> {
			try {
				editPersona();
			} catch (IOException | SQLException e1) {
				e1.printStackTrace();
			}
		});
		view.getBtnElimina().addActionListener(e -> {
			try {
				deletePersona();
			} catch (IOException | SQLException e1) {
				e1.printStackTrace();
			}
		});
	}
	
	private void openEditor() 
	{
		EditorView editorView = new EditorView();
		EditorController editorController = new EditorController(editorView, null, this);
        editorView.setVisible(true);
    }

    private void editPersona() throws IOException, SQLException 
    {
    	int selectedRow = view.getTable().getSelectedRow();
        if(selectedRow == -1) 
        {
        	JOptionPane.showMessageDialog(view, "Seleziona prima una persona da modificare", "Errore", JOptionPane.ERROR_MESSAGE);
        	return;
        }
        
        String telefono = (String)view.getTable().getValueAt(selectedRow, 2);
        Persona p = findPersonByTelephone(telefono);
        
        EditorView editorView = new EditorView();
		EditorController editorController = new EditorController(editorView, p, this);
		editorView.getTxtNome().setText(p.getNome());
		editorView.getTxtCognome().setText(p.getCognome());
		editorView.getTxtIndirizzo().setText(p.getIndirizzo());
		editorView.getTxtTelefono().setText(p.getTelefono());
		editorView.getTxtEta().setText(Integer.toString(p.getEta()));
		editorView.setVisible(true);
    }

    private void deletePersona() throws IOException, SQLException 
    {
    	int selectedRow = view.getTable().getSelectedRow();
        if(selectedRow == -1) 
        {
        	JOptionPane.showMessageDialog(view, "Seleziona prima una persona da eliminare", "Errore", JOptionPane.ERROR_MESSAGE);
        	return;
        }
        
        String telefono = (String)view.getTable().getValueAt(selectedRow, 2);
        Persona p = findPersonByTelephone(telefono);
        int choice = JOptionPane.showConfirmDialog(view, "Eliminare la persona "+p.getNome()+" "+p.getCognome()+"?", "Conferma Eliminazione", JOptionPane.YES_NO_OPTION);
        if(choice == JOptionPane.YES_OPTION)
        {
        	Database.delete(p);
        	updateTable();
        }
    }
    
    public Persona findPersonByTelephone(String telefono) throws IOException, SQLException
    {
    	for(Persona p : Database.getAll())
    	{
    		if(p.getTelefono().equals(telefono))
    			return p;
    	}
		return null;
    }
    
    public void updateTable() throws IOException, SQLException 
    {
    	view.getTableModel().setRowCount(0);
        for (Persona persona : Database.getAll()) 
        {
            Object[] row = {persona.getNome(), persona.getCognome(), persona.getTelefono()};
            view.getTableModel().addRow(row);
        }
    }

}
