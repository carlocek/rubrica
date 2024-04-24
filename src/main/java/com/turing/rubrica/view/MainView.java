package com.turing.rubrica.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.turing.rubrica.model.Persona;

import java.awt.*;
import java.util.Vector;

public class MainView extends JFrame 
{
	
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;
	private JTable table;
	private JButton btnNuovo;
	private JButton btnModifica;
	private JButton btnElimina;
	private Vector<Persona> rubrica;
	

    public MainView(Vector<Persona> rubrica) 
    {
    	this.rubrica = rubrica;
        // creazione della finestra principale
    	setTitle("Rubrica");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // aggiunta margini
        
        // creazione della JTable
        tableModel = new DefaultTableModel(new Object[]{"Nome", "Cognome", "Telefono"}, 0);
        table = new JTable(tableModel);
        // aggiunta nuove righe al DefaultTableModel con i dati del Vector rubrica
        for(Persona p : this.rubrica) 
        {
            Object[] row = {p.getNome(), p.getCognome(), p.getTelefono()};
            tableModel.addRow(row);
        }
        mainPanel.add(new JScrollPane(table));
        
        // creazione dei bottoni
        btnNuovo = new JButton("Nuovo");
        btnModifica = new JButton("Modifica");
        btnElimina = new JButton("Elimina");
        
        JPanel btnPanel = new JPanel();
        btnPanel.add(btnNuovo);
        btnPanel.add(btnModifica);
        btnPanel.add(btnElimina);
        
        add(mainPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);              
        setVisible(true);
    }
   
    
    public JButton getBtnNuovo() 
    {
        return btnNuovo;
    }

    public JButton getBtnModifica() 
    {
        return btnModifica;
    }

    public JButton getBtnElimina() 
    {
        return btnElimina;
    }

    public JTable getTable() 
    {
        return table;
    }
    
    public DefaultTableModel getTableModel() 
    {
        return tableModel;
    }
 
}
