package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {
	private static final String DIREZIONE_BLOCCATA = "nord";
	private static final String DIREZIONE_NON_BLOCCATA = "sud";
	
	private StanzaBloccata bloccata;
	private Stanza adiacenteBloccata;
	private Stanza adiacenteNonBloccata;
	private Attrezzo sbloccante;
	private Attrezzo nonSbloccante;

	@BeforeEach
	void setUp(){
		//attrezzo sbloccante di default è chiave
		this.bloccata = new StanzaBloccata("bloccata",DIREZIONE_BLOCCATA); 
		this.adiacenteBloccata = new Stanza("adiacente");
		this.adiacenteNonBloccata = new Stanza("nonBloccata");
		this.sbloccante = new Attrezzo("chiave",1);
		this.nonSbloccante = new Attrezzo("osso",2);
		this.bloccata.impostaStanzaAdiacente(DIREZIONE_BLOCCATA, adiacenteBloccata);
		this.bloccata.impostaStanzaAdiacente(DIREZIONE_NON_BLOCCATA, adiacenteNonBloccata);
	}

	@Test
	void testGetStanzaAdiacenteSuAdiacenteNonBloccataLaRestituisce() {
		assertEquals(this.adiacenteNonBloccata, this.bloccata.getStanzaAdiacente(DIREZIONE_NON_BLOCCATA));
	}
	
	@Test
	void testGetStanzaAdiacenteSuAdiacenteBloccataRestituisceStanzaCorrenteBloccata() {
		assertEquals(this.bloccata, this.bloccata.getStanzaAdiacente(DIREZIONE_BLOCCATA));
	}
	
	@Test
	void testGetStanzaAdiacenteRestituisceStanzaCorrenteBloccataSePresenteAttrezzoNonSbloccante() {
		this.bloccata.addAttrezzo(this.nonSbloccante);
		assertEquals(this.bloccata, this.bloccata.getStanzaAdiacente(DIREZIONE_BLOCCATA));
	}
	
	@Test
	void testGetStanzaAdiacenteRestituisceStanzaAdiacenteSePresenteAttrezzoSbloccanteInStanzaCorrenteBloccata() {
		this.bloccata.addAttrezzo(this.sbloccante);
		assertEquals(this.adiacenteBloccata, this.bloccata.getStanzaAdiacente(DIREZIONE_BLOCCATA));
	}

	@Test
	void testGetStanzaAdiacenteRestituisceStanzaCorrenteBloccataSeAttrezzoSbloccanteRimosso() {
		this.bloccata.addAttrezzo(this.sbloccante);
		this.bloccata.removeAttrezzo("chiave");
		assertEquals(this.bloccata, this.bloccata.getStanzaAdiacente(DIREZIONE_BLOCCATA));

	}

	@Test
	void testGetDescrizioneRitornaDescrizioneDellaStanzaAdiacenteSePresenteAttrezzoSbloccanteInStanzaCorrente() {
		this.bloccata.addAttrezzo(this.sbloccante);
		assertEquals(this.adiacenteBloccata.getDescrizione(),this.bloccata.getStanzaAdiacente(DIREZIONE_BLOCCATA).getDescrizione());
	}

	@Test
	void testGetDescrizioneNonRitornaDescrizioneDellaStanzaAdiacenteSeNonPresenteAttrezzoSbloccanteInStanzaCorrente() {
		assertNotEquals(this.adiacenteBloccata.getDescrizione(),this.bloccata.getStanzaAdiacente(DIREZIONE_BLOCCATA).getDescrizione());
	}

	@Test
	void testGetDescrizioneNonRitornaDescrizioneDellaStanzaAdiacenteSePresenteAttrezzoNonSbloccanteInStanzaCorrente() {
		this.bloccata.addAttrezzo(this.nonSbloccante);
		assertNotEquals(this.adiacenteBloccata.getDescrizione(),this.bloccata.getStanzaAdiacente(DIREZIONE_BLOCCATA).getDescrizione());

	}
}
