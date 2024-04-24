package com.turing.rubrica.model;

import java.util.Objects;

import com.turing.rubrica.model.Persona;

public class Persona 
{
	private String nome;
	private String cognome;
	private String indirizzo;
	private String telefono;
	private int eta;
	
	public Persona(String nome, String cognome, String indirizzo, String telefono, int eta)
	{
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
		this.telefono = telefono;
		this.eta = eta;
	}
	
	public String getNome() 
	{
		return nome;
	}
	public void setNome(String nome) 
	{
		this.nome = nome;
	}
	public String getCognome() 
	{
		return cognome;
	}
	public void setCognome(String cognome) 
	{
		this.cognome = cognome;
	}
	public String getIndirizzo() 
	{
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) 
	{
		this.indirizzo = indirizzo;
	}
	public String getTelefono() 
	{
		return telefono;
	}
	public void setTelefono(String telefono) 
	{
		this.telefono = telefono;
	}
	public int getEta() 
	{
		return eta;
	}
	public void setEta(int eta) 
	{
		this.eta = eta;
	}
	
	@Override
    public boolean equals(Object o) 
	{
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Persona persona = (Persona)o;
        return Objects.equals(nome, persona.nome) &&
               Objects.equals(cognome, persona.cognome) &&
               Objects.equals(indirizzo, persona.indirizzo) &&
               Objects.equals(telefono, persona.telefono) &&
               eta == persona.eta;
    }

    @Override
    public int hashCode() 
    {
        return Objects.hash(nome, cognome, indirizzo, telefono, eta);
    }

}