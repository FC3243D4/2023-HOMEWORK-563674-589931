package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoFine extends AbstractComando{
	
	private static final String NOME_COMANDO = "comandoFine";

	
	public ComandoFine() {
		super(NOME_COMANDO);
	}


	/**
	 * Chiude la partita stampando messaggio
	 */
	@Override
	public void esegui(Partita partita) {
			partita.setFinita();
			this.getIO().mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere		
	}
	
}
