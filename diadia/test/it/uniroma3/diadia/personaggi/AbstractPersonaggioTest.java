package it.uniroma3.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.AbstractComando;
import it.uniroma3.diadia.comandi.ComandoInteragisci;

class AbstractPersonaggioTest {
	
	private AbstractPersonaggio personaggioAstratto;
	private Attrezzo attrezzo;
	private AbstractComando comandoAstratto;
	private Partita partita;
	private Labirinto labirinto;
	private Stanza corrente;

	@BeforeEach
	void setUp(){
		this.corrente = new Stanza("corrente");
		this.labirinto = new Labirinto();
		this.partita = new Partita(this.labirinto);
		this.partita.setStanzaCorrente(this.corrente);
		this.attrezzo = new Attrezzo("osso",1);
		this.personaggioAstratto = new Mago("nome", "presentazione", this.attrezzo);
		this.partita.getStanzaCorrente().setPersonaggio(this.personaggioAstratto);
	}
	
	
	@Test
	void testAbstractPersonaggioIstanziatoIsNotNull() {
		assertNotNull(this.personaggioAstratto);
	}
	
	@Test
	void testAgisci() {
		this.comandoAstratto = new ComandoInteragisci();
		this.comandoAstratto.esegui(this.partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("osso"));
	}


	@Test
	void testSaluta() {
		this.personaggioAstratto.saluta();
		assertTrue(this.partita.getStanzaCorrente().getPersonaggio().haSalutato());	
	}


	@Test
	void testToString() {
		assertEquals("nome", this.personaggioAstratto.getNome());
	}

	

}
