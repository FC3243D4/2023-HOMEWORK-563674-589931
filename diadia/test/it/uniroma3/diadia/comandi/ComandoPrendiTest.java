package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPrendiTest {
	private ComandoPrendi comando;
	private Partita partita;
	private Stanza stanza;
	private Attrezzo attrezzo;
	

	@BeforeEach
	void setUp(){
		this.comando = new ComandoPrendi();
		this.partita = new Partita();
		this.stanza = new Stanza("stanza");
		this.partita.setStanzaCorrente(stanza);
		this.attrezzo = new Attrezzo("attrezzo", 1);
		stanza.addAttrezzo(attrezzo);
		
	}

	@Test
	void testComandoPrendiNonAgisceSeIlNomeDellAttrezzoIsLaStringaNulla() {
		this.comando.setParametro(null);
		this.comando.esegui(partita);
		assertTrue(this.partita.getGiocatore().getBorsa().isEmpty());
	}
	
	@Test
	void testComandoPrendiSuAttrezzoNonInStanzaNonAgisce() {
		this.comando.setParametro("nonInStanza");
		this.comando.esegui(partita);
		assertTrue(this.partita.getGiocatore().getBorsa().isEmpty());
	}

	@Test
	void testComandoPrendiSuAttrezzoInStanzaAggiungeLAttrezzoAllaBorsa() {
		this.comando.setParametro("attrezzo");
		this.comando.esegui(partita);
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo("attrezzo"));	
	}
	
	@Test
	void testComandoPrendiSuAttrezzoInStanzaRimuoveLAttrezzoDallaStanza() {
		this.comando.setParametro("attrezzo");
		this.comando.esegui(partita);
		assertFalse(this.stanza.hasAttrezzo("attrezzo"));
	}

	
}
