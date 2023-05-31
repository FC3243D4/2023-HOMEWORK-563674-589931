package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {

	private static final String CIBO_PREFERITO = "cibo";

	private Attrezzo attrezzo;


	public Cane(String nome, String presentaz, Attrezzo attrezzo) {
		super(nome, presentaz);
		this.attrezzo = attrezzo;
	}

	@Override
	public String agisci(Partita partita) {
		String msg;

		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		msg = "Argh, ti ha morso!";
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo cibo, Partita partita) {

		if(cibo.getNome().equals(CIBO_PREFERITO)) {
			if(this.attrezzo!=null) {
				partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
				this.attrezzo =  null;
				String msg = "arf arf auuuuh!";
				return msg;
			}
		}

		return this.agisci(partita);
	}

}
