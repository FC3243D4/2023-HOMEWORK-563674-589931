package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.*;

public class Cane extends AbstractPersonaggio {

	private static final String CIBO_PREFERITO = "osso";

	private Attrezzo attrezzo;
	private String msg="Argh, ti ha morso!";


	public Cane(String nome, String presentaz, Attrezzo attrezzo) {
		super(nome, presentaz);
		this.attrezzo=attrezzo;
	}

	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		return this.msg;
	}

	@Override
	public String riceviRegalo(Attrezzo a, Partita partita) {
		if(a!=null) {
			if(a.getNome().equals(CIBO_PREFERITO)) {
				if(this.attrezzo!=null) {
					String messaggio = "arf arf auuuuh!\noggetto "+this.attrezzo.getNome()+" precedentemente detenuto da "+this.getNome()+" lasciato nella stanza";
					partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
					this.attrezzo = null;
					return messaggio;
				}
			}
		}

		return this.agisci(partita);
	}

}
