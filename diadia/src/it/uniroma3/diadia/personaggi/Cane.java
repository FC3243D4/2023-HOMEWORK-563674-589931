package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.*;

public class Cane extends AbstractPersonaggio {

	private static final String CIBO_PREFERITO = "osso";

	private Attrezzo attrezzo;
	private String msg="Argh, ti ha morso!";
	private boolean aggressivo;


	public Cane(String nome, String presentaz, Attrezzo attrezzo) {
		super(nome, presentaz);
		this.attrezzo = attrezzo;
		this.aggressivo=true;
	}

	@Override
	public String agisci(Partita partita) {
		if(this.aggressivo==true)partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		return this.msg;
	}

	@Override
	public String riceviRegalo(Attrezzo a, Partita partita) {
		if(a!=null) {
			if(a.getNome().equals(CIBO_PREFERITO)) {
				if(this.attrezzo!=null) {
					partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
					this.attrezzo = null;
					this.msg = "arf arf auuuuh!";
					this.aggressivo=false;
					return msg;
				}
			}
		}

		return this.agisci(partita);
	}

}
