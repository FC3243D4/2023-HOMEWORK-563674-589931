package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando {
	private static final String NOME_COMANDO = "comandoGuarda";
	private IO IO;
	
	public ComandoGuarda() {
		this.IO = new IOConsole();
	}

	
	/**
	 * stampa le informazioni sulla stanza corrente e
	 * sullo stato della partita
	 * */
	@Override
	public void esegui(Partita partita) {
		IO.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		IO.mostraMessaggio("cfu correnti: " + partita.getGiocatore().getCfu());
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
