package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoSaluta extends AbstractComando{
	
	private static final String NOME_COMANDO = "ComandoSaluta";
	
	public ComandoSaluta() {
		super(NOME_COMANDO);
	}

	@Override
	public void esegui(Partita partita) {
		
		if(partita.getStanzaCorrente().getPersonaggio()==null)
			this.getIO().mostraMessaggio("non c'è nessuno da salutare...");
		else {
			if(partita.getStanzaCorrente().getPersonaggio().getClass().getSimpleName().equals("Strega"))
				partita.getStanzaCorrente().getPersonaggio().setGiocatoreHaSalutato(true);
		}
	}

}
