package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;


/**Una classe che modella un comando attraverso
il quale il giocatore può regalare un attrezzo
al personaggio presente nella stanza
@author EFFE
@see AbstractPersonaggio
@see Comando
@version homework4
*/
public class ComandoRegala extends AbstractComando {

	private static final String NOME_COMANDO = "ComandoRegala";

	public ComandoRegala() {
		super(NOME_COMANDO);
	}
	
	@Override
	public void esegui(Partita partita) {

		if(partita.getStanzaCorrente().getPersonaggio()!=null) {
			Attrezzo regalo = partita.getGiocatore().getBorsa().getAttrezzo(this.getParametro());
			
			if(regalo!=null) {
				partita.getStanzaCorrente().getPersonaggio().riceviRegalo(regalo, partita);
				partita.getGiocatore().getBorsa().removeAttrezzo(regalo.getNome());
			}else {
				this.getIO().mostraMessaggio("oggetto non presente in borsa...");
			}
				
		}else {
			this.getIO().mostraMessaggio("non c'è nessuno a cui regalare nulla...");
		}
		
	}

}