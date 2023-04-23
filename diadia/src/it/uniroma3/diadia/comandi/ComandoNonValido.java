package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

/**
 * Permette di modellare un null object, un contenitore che gestisca 
 * implicitamente i casi null o non validi per evitare le verifiche sul null
 * @author EFFE
 * @see Comando
 * @version homework2
 * */
public class ComandoNonValido implements Comando {
	private static final String NOME_COMANDO = "comandoNonValido";
	private IO IO;

	public ComandoNonValido() {
		this.IO = new IOConsole();
	}
	
	@Override
	public void esegui(Partita partita) {
		IO.mostraMessaggio("Devi inserire un comando valido!");
	}

	@Override
	public void setParametro(String parametro) {
	}

	@Override
	public String getNome() {
		return NOME_COMANDO;
	}

	@Override
	public String getParametro() {
		return null;
	}

}
