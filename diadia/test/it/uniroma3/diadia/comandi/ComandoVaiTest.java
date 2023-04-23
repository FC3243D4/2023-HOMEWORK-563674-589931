package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

class ComandoVaiTest {
	
	private static final String DIREZIONE_NULLA = null;
	private static final String DIREZIONE = "direzione";
	private static final Stanza NULLA = null;

	private ComandoVai comando;
	private Partita partita;
	private Stanza corrente,adiacente;

	@BeforeEach
	void setUp(){
		this.comando = new ComandoVai();
		this.partita = new Partita();
		this.corrente = new Stanza("corrente");
		this.adiacente = new Stanza("adiacente");
		this.corrente.impostaStanzaAdiacente(DIREZIONE, this.adiacente);
		this.partita.setStanzaCorrente(this.corrente);

	}
	
	
	@Test
	void testComandoVaiNonAgisceSeLaStanzaDoveVoglioSpostarmiIsLaStanzaNulla() {
		this.corrente.impostaStanzaAdiacente(DIREZIONE, NULLA);
		this.comando.setParametro(DIREZIONE);
		this.comando.esegui(this.partita);
		assertEquals("corrente", this.partita.getStanzaCorrente().getNome());
	}

	@Test
	void testComandoVaiNonAgisceSeIlParametroImpostatoIsLaDirezioneNulla() {
		this.comando.setParametro(DIREZIONE_NULLA);
		this.comando.esegui(this.partita);
		assertEquals("corrente", this.partita.getStanzaCorrente().getNome());
	}
	
	@Test
	void testComandoVaiAgisceSeIlParametroImpostatoIsLaDirezioneNonNullaEStanzaNonNullaVediSetUp() {
		this.comando.setParametro(DIREZIONE);
		this.comando.esegui(this.partita);
		assertEquals("adiacente", this.partita.getStanzaCorrente().getNome());
	}

	/*I CFU iniziali del giocatore sono 20*/
	@Test 
	void testComandoVaiValidoModificaICfuDelGiocatoreDellaPartita() {
		this.comando.setParametro(DIREZIONE);
		this.comando.esegui(this.partita);
		assertEquals(19, this.partita.getGiocatore().getCfu());

	}
	
}
