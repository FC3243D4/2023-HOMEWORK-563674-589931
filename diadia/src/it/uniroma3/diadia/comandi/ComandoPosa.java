package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa extends AbstractComando{
	private static final String NOME_COMANDO = "comandoPosa";
		
	public ComandoPosa() {
		super(NOME_COMANDO);
	}

	
	/*se presente un attrezzo nella borsa del giocatore con nome
	 * nomeAttrezzo lo posa nella stanza corrente*/
	@Override
	public void esegui(Partita partita) {
		
		boolean c=partita.getGiocatore().getBorsa().hasAttrezzo(this.getParametro());
		Attrezzo a;
		if(c==true) {
			a=partita.getGiocatore().getBorsa().removeAttrezzo(this.getParametro());
			partita.getStanzaCorrente().addAttrezzo(a);
			this.getIO().mostraMessaggio("oggetto "+this.getParametro()+" tolto dalla borsa e posato nella stanza");
		}
		if(c==false) this.getIO().mostraMessaggio("oggetto non presente in borsa");
		
	}

}