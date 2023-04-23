package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoFine implements Comando{
	private static final String NOME_COMANDO = "comandoFine";
	private IO IO;

	public ComandoFine() {
		this.IO = new IOConsole();
	}

	/**
	 * Chiude la partita stampando messaggio
	 */
	@Override
	public void esegui(Partita partita) {
			partita.setFinita();
			IO.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere		
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
