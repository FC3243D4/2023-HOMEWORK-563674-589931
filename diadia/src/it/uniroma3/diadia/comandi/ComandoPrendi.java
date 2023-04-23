package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando{
	private static final String NOME_COMANDO = "comandoPrendi";
	
	private String nomeAttrezzo;
	private IO IO;
	
	public ComandoPrendi() {
		this.IO = new IOConsole();
	}

	
	/**controlla se nella stanza è presente un attrezzo di nome 
	 * nomeAttrezzo e se si lo aggiunge alla borsa e lo toglie dalla stanza*/
	@Override
	public void esegui(Partita partita) {
		Attrezzo attrezzi[]=partita.getStanzaCorrente().getAttrezzi();
		Attrezzo a=null;
		
		for(int i=0;i<attrezzi.length;i++) {
			if(attrezzi[i] != null) {
				if(attrezzi[i].getNome().equals(this.nomeAttrezzo)) {
					a=attrezzi[i];
					partita.getStanzaCorrente().removeAttrezzo(attrezzi[i].getNome());
					partita.getGiocatore().getBorsa().addAttrezzo(a);
					IO.mostraMessaggio("oggetto "+nomeAttrezzo+" messo in borsa");
				}
			}
		}
		if(a==null) IO.mostraMessaggio("oggetto non presente nella stanza");

	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
		
	}

	@Override
	public String getNome() {
		return NOME_COMANDO;
	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

}
