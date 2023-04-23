package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando{
	private static final String NOME_COMANDO = "comandoPosa";
	
	private String nomeAttrezzo;	
	private IO IO;

	public ComandoPosa() {
		this.IO = new IOConsole();
	}
	
	/*se presente un attrezzo nella borsa del giocatore con nome
	 * nomeAttrezzo lo posa nella stanza corrente*/
	@Override
	public void esegui(Partita partita) {
		
		boolean c=partita.getGiocatore().getBorsa().hasAttrezzo(this.nomeAttrezzo);
		Attrezzo a;
		if(c==true) {
			a=partita.getGiocatore().getBorsa().removeAttrezzo(this.nomeAttrezzo);
			partita.getStanzaCorrente().addAttrezzo(a);
			IO.mostraMessaggio("oggetto "+nomeAttrezzo+" tolto dalla borsa e posato nella stanza");
		}
		if(c==false) IO.mostraMessaggio("oggetto non presente in borsa");
		
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
