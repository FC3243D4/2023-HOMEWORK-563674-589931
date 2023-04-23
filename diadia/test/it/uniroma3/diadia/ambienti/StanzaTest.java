package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.*;

class StanzaTest {
	private Stanza stanza;
	private Stanza adiacente;
	private static final String NOME = "nomeStanza";
	private static final String DIREZIONE = "direzione";
	private static final String ADIACENTE = "adiacente";
	private static final String NOME_ATTREZZO = "nomeAttrezzo";
	private static final int PESO = 1;
	private Attrezzo attrezzoNull;
	private Attrezzo attrezzo;

	@BeforeEach
	void setUp(){
		this.stanza = new Stanza(NOME);
		this.adiacente = new Stanza(ADIACENTE);
		attrezzoNull=null;
		attrezzo=new Attrezzo(NOME_ATTREZZO,PESO);
	}

	// 3 test su impostaStanzaAdiacente e getStanzaAdiacente 
	@Test
	void testStanzaAdiacenteInizializzataNonENull() {
		this.stanza.impostaStanzaAdiacente(DIREZIONE, this.adiacente);
		assertNotNull(this.stanza.getStanzaAdiacente(DIREZIONE));
	}

	@Test
	void testNuovaStanzaCreataImpostataAdiacenteIsLaStanzaAdiacente() {
		Stanza nuova = new Stanza(NOME);
		this.stanza.impostaStanzaAdiacente(DIREZIONE, nuova);
		assertEquals(nuova, this.stanza.getStanzaAdiacente(DIREZIONE));
	}

	@Test
	void testStanzaConAltraDirezioneImpostataAdiacenteIsLaStanzaAdiacente() {
		Stanza nuova = new Stanza(NOME);
		this.stanza.impostaStanzaAdiacente(DIREZIONE, nuova);
		this.stanza.impostaStanzaAdiacente("nuovaDrezione", nuova);
		assertEquals(nuova, this.stanza.getStanzaAdiacente(DIREZIONE));
	}
	
	// 5 test per addAttrezzo, hasAttrezzo, getAttrezzo, removeAttrezzo
	
	//testa addAttrezzo
    @Test
    void testMetodoAddAttrezzoRitornaVeroSeAggiungeAttrezzoNellaStanza(){
        assertTrue(this.stanza.addAttrezzo(attrezzo));
    }
    
    //testa AddAttrezzo e getAttrezzo
    @Test
    void testAttrezzoAggiuntoConAddAttrezzoIsNonNull(){
        this.stanza.addAttrezzo(attrezzo);
        assertEquals(attrezzo,this.stanza.getAttrezzo(NOME_ATTREZZO));
    }
    
    //testa addAttrezzo
    @Test
    void testAddAttrezzoNull() {
    	assertEquals(false,this.stanza.addAttrezzo(attrezzoNull));
    }

    //testa addAttrezzo e hasAttrezzo
    @Test
    void testAttrezzoAggiuntoConAddAttrezzoIsPresenteNellaStanza() {
        Attrezzo aggiunto = new Attrezzo(NOME_ATTREZZO, PESO);
        this.stanza.addAttrezzo(aggiunto);
        assertTrue(this.stanza.hasAttrezzo(NOME_ATTREZZO));
    }

    //testa addAttrezzo e hasAttrezzo
    @Test
    void testAttrezzoAggiuntoConAddAttrezzoIsInStanzaConGiaUnAttrezzo() {
        Attrezzo nuovo = new Attrezzo("nuovo", 6);
        this.stanza.addAttrezzo(nuovo);
        assertTrue(this.stanza.hasAttrezzo("nuovo"));
    }

    //testa addAttrezzo removeAttrezzo e getAttrezzo
    @Test
    void testAttrezzoRimossoConRemoveAttrezzoIsNotNellaStanza() {
        Attrezzo aggiunto = new Attrezzo(NOME_ATTREZZO, PESO);
        this.stanza.addAttrezzo(aggiunto);
        this.stanza.removeAttrezzo(NOME_ATTREZZO);
        assertNull(this.stanza.getAttrezzo(NOME_ATTREZZO));
    }
    
    //testa removeAttrezzo
    @Test
    void testAttrezzoNonInStanzaRimossoConRemoveAttrezzoTornaFalse() {
        assertEquals(false,this.stanza.removeAttrezzo(NOME_ATTREZZO));
    }
    
    //testa addAttrezzo removeAttrezzo e hasAttrezzo
    @Test
    void testStanzaNonHaAttrezzoRimossoConRemoveAttrezzo() {
        Attrezzo aggiunto = new Attrezzo(NOME_ATTREZZO, PESO);
        this.stanza.addAttrezzo(aggiunto);
        this.stanza.removeAttrezzo(NOME_ATTREZZO);
        assertEquals(false,this.stanza.hasAttrezzo(NOME_ATTREZZO));
    }
}