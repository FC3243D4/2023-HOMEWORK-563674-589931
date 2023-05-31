package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.*;

public class Labirinto {
	private Stanza stanzaVincente;
	private Stanza stanzaIniziale;

	public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}

	public void setStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}

	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}
	
	public static LabirintoBuilder newBuilder() {
		return new LabirintoBuilder();
	}
	
	public Labirinto LabirintoDiaDia() throws FileNotFoundException, FormatoFileNonValidoException {
		CaricatoreLabirinto caricatore = new CaricatoreLabirinto("LabirintoDiaDia.txt");
		caricatore.carica();
		return caricatore.getLabirinto();
		
	}
}