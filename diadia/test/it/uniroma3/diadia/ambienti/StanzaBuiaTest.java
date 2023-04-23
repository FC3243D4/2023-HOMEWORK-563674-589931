package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest {
	private StanzaBuia buia;
	private Attrezzo nonFaLuce;
	private Attrezzo lanterna;

	@BeforeEach
	void setUp(){
		this.nonFaLuce = new Attrezzo("nonFaLuce", 0);
		this.buia = new StanzaBuia("buia"); // attrezzo di default che fa luce è lanterna
		this.lanterna = new Attrezzo("lanterna", 0);
	}
	
	@Test
	void testGetDescrizioneSuStanzaBuiaVuotaNonStampaDescrizione() {
		assertEquals("qui c'è buio pesto...", this.buia.getDescrizione());
	}

	@Test
	void testGetDescrizioneSuStanzaBuiaConAttrezzoCheNonFaLuceNonStampaDescrizione() {
		this.buia.addAttrezzo(this.nonFaLuce);
		assertEquals("qui c'è buio pesto...", this.buia.getDescrizione());
	}

	@Test
	void testGetDescrizioneSuStanzaBuiaConAttrezzoCheFaLuceStampaDescrizione() {
		this.buia.addAttrezzo(this.lanterna);
		assertNotEquals("qui c'è buio pesto...", this.buia.getDescrizione());	
		}

	@Test
	void testGetDescrizioneSuStanzaBuiaDopoAttrezzoCheFaLuceRimossoDallaStanzaNonStampaDescrizione() {
		this.buia.addAttrezzo(this.lanterna);
		this.buia.removeAttrezzo("lanterna");
		assertEquals("qui c'è buio pesto...", this.buia.getDescrizione());	
	}

}
