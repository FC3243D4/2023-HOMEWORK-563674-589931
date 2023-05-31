package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;

public abstract class AbstractComando{
	
	private IO IO;
	private String nome;
	private String parametro;
	
	public AbstractComando(String nome) {
		this.IO = new IOConsole();
		this.nome = nome;
	}

	public IO getIO() {
		return IO;
	}


	public void setIO(IO iO) {
		IO = iO;
	}
	
	public String getNome() {
		return this.nome;
	}
		
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getParametro() {
		return this.parametro;
	}
	
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	

	/**
	 * esecuzione del comando
	 * */
	public abstract void esegui(Partita partita);

	


}
