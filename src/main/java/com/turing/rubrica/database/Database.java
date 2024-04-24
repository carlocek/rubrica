package com.turing.rubrica.database;

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

public class Database 
{
	public static Connection getConnection() throws IOException, SQLException
	{
		Properties prp = new Properties();
        FileInputStream credentials = new FileInputStream("C:/Users/carlo/eclipse-workspace-java/rubricabackup/src/main/java/com/turing/rubricabackup/credenziali_database.properties");
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
	    
	    return rubrica;
	}
}
