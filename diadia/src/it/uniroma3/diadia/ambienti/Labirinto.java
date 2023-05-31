package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;

import it.uniroma3.diadia.FormatoFileNonValidoException;

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
		return new LabirintoBuilder()
				.addStanzaIniziale("Atrio").addAttrezzo("osso", 1)
				.addStanzaVincente("Biblioteca")
				.addStanza("Aula N11")
				.addStanza("Aula N10").addAttrezzo("lanterna", 3)
				.addStanza("Laboratorio Campus")
				
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.addAdiacenza("Atrio", "Aula N11", "est")
				.addAdiacenza("Atrio", "Aula N10", "sud")
				.addAdiacenza("Atrio", "Laboratorio Campus", "ovest")
				
				.getLabirinto();
		
	}
}