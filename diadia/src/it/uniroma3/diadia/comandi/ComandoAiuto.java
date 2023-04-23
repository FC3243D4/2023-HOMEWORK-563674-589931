package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando{
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa", "guarda"};
	private static final String NOME_COMANDO = "comandoAiuto";
	private IO IO;
	
	public ComandoAiuto() {
		this.IO = new IOConsole();
	}
	
	/**
	 * Stampa informazioni di aiuto.
	 */
	@Override
	public void esegui(Partita partita) {
		for(int i=0; i< elencoComandi.length; i++)
			IO.mostraMessaggio(elencoComandi[i]+" ");
		IO.mostraMessaggio("");
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
