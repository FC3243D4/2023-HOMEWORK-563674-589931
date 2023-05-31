package it.uniroma3.diadia.comandi;

import java.util.Iterator;
import java.util.List;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi extends AbstractComando{
	private static final String NOME_COMANDO = "comandoPrendi";
	

	public ComandoPrendi() {
		super(NOME_COMANDO);
	}
	
	/**controlla se nella stanza è presente un attrezzo di nome 
	 * nomeAttrezzo e se si lo aggiunge alla borsa e lo toglie dalla stanza*/
	@Override
	public void esegui(Partita partita) {
		List<Attrezzo> attrezzi=partita.getStanzaCorrente().getAttrezzi();
		Attrezzo cercato=null;
		Iterator<Attrezzo> iteratore = attrezzi.iterator();
		while (iteratore.hasNext() && cercato==null) {
			Attrezzo a =iteratore.next();
			if(a.getNome().equals(this.getParametro())) {
				cercato=a;
				partita.getStanzaCorrente().removeAttrezzo(a.getNome());
				partita.getGiocatore().getBorsa().addAttrezzo(a);
				this.getIO().mostraMessaggio("oggetto "+this.getParametro()+" messo in borsa");
			}
		}
	
		if(cercato==null) this.getIO().mostraMessaggio("oggetto non presente nella stanza");

	}

}