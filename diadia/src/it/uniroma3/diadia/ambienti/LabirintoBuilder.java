package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;
//import java.util.List;
import java.io.FileNotFoundException;
//import java.util.ArrayList;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.*;

public class LabirintoBuilder {

	private Labirinto labirinto;
	private Stanza ultimaStanzaAggiunta;
	private Map<String, Stanza> mappaStanze;

	public LabirintoBuilder() {
		this.labirinto = new Labirinto();
		this.mappaStanze = new HashMap<String, Stanza>();
	}
	
	public Map<String, Stanza> getMappaStanze() {
		return mappaStanze;
	}

	public Labirinto getLabirinto() {
		return this.labirinto;
	}

	public LabirintoBuilder addStanzaIniziale(String stanzaIniziale) throws FileNotFoundException, FormatoFileNonValidoException {
		Stanza i = new Stanza(stanzaIniziale);
		this.labirinto.setStanzaIniziale(i);
		this.UltimaStanzaAggiuntaEAggiorna(i);
		return this;
	}
	
	public LabirintoBuilder setStanzaIniziale(String stanzaIniziale) throws FileNotFoundException, FormatoFileNonValidoException {
		for(String nomeStanza : this.mappaStanze.keySet()) {
			if(nomeStanza.equals(stanzaIniziale)) {
				this.labirinto.setStanzaIniziale(mappaStanze.get(stanzaIniziale));
				this.ultimaStanzaAggiunta = mappaStanze.get(stanzaIniziale);
			}
			else addStanzaIniziale(stanzaIniziale);
		}
		return this;
	}

	public LabirintoBuilder addStanzaVincente(String stanzaVincente) throws FileNotFoundException, FormatoFileNonValidoException {
		Stanza s = new Stanza(stanzaVincente);
		this.labirinto.setStanzaVincente(s);
		this.UltimaStanzaAggiuntaEAggiorna(s);
		return this;
	}
	
	public LabirintoBuilder setStanzaVincente(String stanzaVincente) throws FileNotFoundException, FormatoFileNonValidoException {
		for(String nomeStanza : this.mappaStanze.keySet()) {
			if(nomeStanza.equals(stanzaVincente)) {
				this.labirinto.setStanzaVincente(mappaStanze.get(stanzaVincente));
				this.ultimaStanzaAggiunta = mappaStanze.get(stanzaVincente);
			}
			else addStanzaVincente(stanzaVincente);
		}
		return this;
	}

	public LabirintoBuilder addStanza(String stanza) throws FileNotFoundException, FormatoFileNonValidoException {
		Stanza s = new Stanza(stanza);
		this.UltimaStanzaAggiuntaEAggiorna(s);
		return this;
	}	

	public LabirintoBuilder addAttrezzo(String attrezzo, int peso) throws FormatoFileNonValidoException {
		Attrezzo a = new Attrezzo(attrezzo, peso);
		if(this.ultimaStanzaAggiunta==null)
			return this;
		this.ultimaStanzaAggiunta.addAttrezzo(a);
		return this;
	}
	
	public LabirintoBuilder addAttrezzoAStanza(String attrezzo, int peso, String nomeStanza) throws FormatoFileNonValidoException {
		Attrezzo a = new Attrezzo(attrezzo, peso);
		for(String nomeStanzaRicerca : this.mappaStanze.keySet()) {
			if(nomeStanzaRicerca.equals(nomeStanza)) {
				this.mappaStanze.get(nomeStanza).addAttrezzo(a);
			}
		}
		return this;
	}

	public LabirintoBuilder addAdiacenza(String stanzaCorrente, String stanzaAdiecente, String direzione) {
		Stanza c = this.mappaStanze.get(stanzaCorrente);
		Stanza a = this.mappaStanze.get(stanzaAdiecente);
		String direzioneOpposta = new String();
		if(direzione.equals("nord")) direzioneOpposta="sud";
		if(direzione.equals("sud")) direzioneOpposta="nord";
		if(direzione.equals("ovest")) direzioneOpposta="est";
		if(direzione.equals("est")) direzioneOpposta="ovest";
		c.impostaStanzaAdiacente(direzione, a);
		a.impostaStanzaAdiacente(direzioneOpposta, c);
		return this;
	}
	
	public LabirintoBuilder addStanzaMagica(String nome) throws FileNotFoundException, FormatoFileNonValidoException {
		Stanza stanza = new StanzaMagica(nome);
		this.UltimaStanzaAggiuntaEAggiorna(stanza);
		return this;
	}
	
	public LabirintoBuilder addStanzaMagica(String nome, int capacita) throws FileNotFoundException, FormatoFileNonValidoException {
		Stanza stanza = new StanzaMagica(nome,capacita);
		this.UltimaStanzaAggiuntaEAggiorna(stanza);
		return this;
	}
	
	public LabirintoBuilder addStanzaBuia(String nome, String attrezzoPerVedere) throws FileNotFoundException, FormatoFileNonValidoException {
		Stanza stanza = new StanzaBuia(nome,attrezzoPerVedere);
		this.UltimaStanzaAggiuntaEAggiorna(stanza);
		return this;
	}
	
	public LabirintoBuilder addStanzaBloccata(String nome, String direzioneBloccata, String attrezzoSbloccante) throws FileNotFoundException, FormatoFileNonValidoException {
		Stanza stanza = new StanzaBloccata(nome, direzioneBloccata, attrezzoSbloccante);
		this.UltimaStanzaAggiuntaEAggiorna(stanza);
		return this;
	}
	
	public void UltimaStanzaAggiuntaEAggiorna(Stanza stanza) {
		this.ultimaStanzaAggiunta = stanza;
		this.mappaStanze.put(stanza.getNome(), stanza);
	}
	
	public LabirintoBuilder addPersonaggioAStanza(AbstractPersonaggio mago, String nomeStanza) {
		this.mappaStanze.get(nomeStanza).setPersonaggio(mago);
		return this;
	}
	/*
	public LabirintoBuilder addStregaAStanza(Strega strega, String nomeStanza) {
		this.mappaStanze.get(nomeStanza).setPersonaggio(strega);
		return this;
	}
	
	public LabirintoBuilder addCaneAStanza(Cane cane, String nomeStanza) {
		this.mappaStanze.get(nomeStanza).setPersonaggio(cane);
		return this;
	}*/
}
