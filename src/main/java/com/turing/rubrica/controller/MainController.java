package com.turing.rubrica.controller;

import java.util.Vector;

import javax.swing.JOptionPane;

import com.turing.rubrica.model.Persona;
import com.turing.rubrica.view.EditorView;
import com.turing.rubrica.view.MainView;

public class MainController 
{
	private Vector<Persona> rubrica;
	private MainView view;
	
	public MainController(Vector<Persona> rubrica, MainView view)
	{
		this.rubrica = rubrica;
		this.view = view;
		view.getBtnNuovo().addActionListener(e -> openEditor());
		view.getBtnModifica().addActionListener(e -> editPersona());
		view.getBtnElimina().addActionListener(e -> deletePersona());
	}

	public Vector<Persona> getRubrica() 
	{
		return rubrica;
	}
	
	private void openEditor() 
	{
		EditorView editorView = new EditorView();
		EditorController editorController = new EditorController(rubrica, editorView, null, this);
        editorView.setVisible(true);
    }

    private void editPersona() 
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
		EditorController editorController = new EditorController(rubrica, editorView, p, this);
		editorView.getTxtNome().setText(p.getNome());
		editorView.getTxtCognome().setText(p.getCognome());
		editorView.getTxtIndirizzo().setText(p.getIndirizzo());
		editorView.getTxtTelefono().setText(p.getTelefono());
		editorView.getTxtEta().setText(Integer.toString(p.getEta()));
		editorView.setVisible(true);
    }

    private void deletePersona() 
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
        	rubrica.remove(p);
        	updateTable();
        }
    }
    
    public Persona findPersonByTelephone(String telefono)
    {
    	for(Persona p : rubrica)
    	{
    		if(p.getTelefono().equals(telefono))
    			return p;
    	}
		return null;
    }
    
    public void updateTable() 
    {
    	view.getTableModel().setRowCount(0);
        for (Persona persona : rubrica) { //personaDao.getAll()
            Object[] row = {persona.getNome(), persona.getCognome(), persona.getTelefono()};
            view.getTableModel().addRow(row);
        }
    }

}
