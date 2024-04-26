package com.turing.rubrica.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;

import com.turing.rubrica.model.Persona;
import com.turing.rubrica.model.Utente;

public class Database 
{
	public static Connection getConnection() throws IOException, SQLException
	{
		Properties prp = new Properties();
//		FileInputStream credentials = new FileInputStream(new File("src/main/java/com/turing/rubrica/credenziali_database.properties").getAbsolutePath());
		FileInputStream credentials = new FileInputStream(new File("credenziali_database.properties").getAbsolutePath());
		prp.load(credentials);
        String username = prp.getProperty("username");
        String password = prp.getProperty("password");
        String ip = prp.getProperty("ip-server-mysql");
        String porta = prp.getProperty("porta");
        String url = "jdbc:mysql://" + ip + ":" + porta + "/rubrica_db";
		
		return DriverManager.getConnection(url, username, password);
	}
	
	public static void save(Persona pToAdd) throws IOException, SQLException 
	{
        String query = "INSERT INTO rubrica (nome, cognome, indirizzo, telefono, eta) VALUES (?, ?, ?, ?, ?)";
        Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query);
        
        pstmt.setString(1, pToAdd.getNome());
        pstmt.setString(2, pToAdd.getCognome());
        pstmt.setString(3, pToAdd.getIndirizzo());
        pstmt.setString(4, pToAdd.getTelefono());
        pstmt.setInt(5, pToAdd.getEta());
        
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }
	
	public static void save(Utente uToAdd) throws IOException, SQLException 
	{
        String query = "INSERT INTO utenti (username, password) VALUES (?, ?)";
        Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query);
        
        pstmt.setString(1, uToAdd.getUsername());
        pstmt.setString(2, uToAdd.getPassword());
        
        pstmt.executeUpdate();
    }
	
	public static void update(Persona pOld, Persona pNew) throws IOException, SQLException 
	{
	    String query = "UPDATE rubrica SET nome=?, cognome=?, indirizzo=?, telefono=?, eta=? WHERE telefono=?";
	    Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query);
  
        pstmt.setString(1, pNew.getNome());
        pstmt.setString(2, pNew.getCognome());
        pstmt.setString(3, pNew.getIndirizzo());
        pstmt.setString(4, pNew.getTelefono());
        pstmt.setInt(5, pNew.getEta());
        pstmt.setString(6, pOld.getTelefono());

        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
	}
	
	public static void delete(Persona pToDelete) throws IOException, SQLException 
	{
	    String query = "DELETE FROM rubrica WHERE telefono=?";
	    Connection conn = getConnection();
	    PreparedStatement pstmt = conn.prepareStatement(query);
	    
	    pstmt.setString(1, pToDelete.getTelefono());
	    
	    pstmt.executeUpdate();
	    pstmt.close();
        conn.close();
	}
	
	public static Vector<Persona> getAll() throws IOException, SQLException 
	{
		Vector<Persona> rubrica = new Vector<Persona>();
	    String query = "SELECT * FROM rubrica";
	    Connection conn = getConnection();
	    Statement stmt = conn.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    
	    while (rs.next()) 
        {
            String nome = rs.getString("nome");
            String cognome = rs.getString("cognome");
            String indirizzo = rs.getString("indirizzo");
            String telefono = rs.getString("telefono");
            int eta = rs.getInt("eta");
            rubrica.add(new Persona(nome, cognome, indirizzo, telefono, eta));
        }
	    stmt.close();
        conn.close();
	    
	    return rubrica;
	}
	
	public static boolean authenticate(String username, String password) throws IOException, SQLException 
	{
	    String query = "SELECT username, password FROM utenti";
	    Connection conn = getConnection();
	    Statement stmt = conn.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    while (rs.next()) 
        {
            String u = rs.getString("username");
            String p = rs.getString("password");
            if(username.equals(u) && password.equals(p))
            	return true;
        }
	    return false;
	}
}
