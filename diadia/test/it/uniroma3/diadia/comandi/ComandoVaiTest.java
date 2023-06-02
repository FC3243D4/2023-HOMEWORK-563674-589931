package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzioni;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

class ComandoVaiTest {

	private static final Direzioni DIREZIONE = Direzioni.nord;
	private static final Stanza NULLA = null;

	private ComandoVai comando;
	private Partita partita;
	private Stanza corrente,adiacente;
	private Labirinto bilocale;

	@BeforeEach
	void setUp() throws FileNotFoundException, FormatoFileNonValidoException{
		this.comando = new ComandoVai();
		CaricatoreLabirinto c=new CaricatoreLabirinto("LabirintoDiaDia.txt");
		c.carica();
		Labirinto labirinto = c.getLabirinto();
		this.partita=new Partita(labirinto);
		this.corrente = new Stanza("corrente");
		this.adiacente = new Stanza("adiacente");
		this.corrente.impostaStanzaAdiacente(DIREZIONE, this.adiacente);
		this.partita.setStanzaCorrente(this.corrente);

		this.bilocale = Labirinto.newBuilder()
				.addStanzaIniziale("salotto")
				.addStanzaVincente("camera").addAttrezzo("letto",10) 

				.addAdiacenza("salotto", "camera", Direzioni.nord) 

				.getLabirinto();

	}


	@Test
	void testComandoVaiNonAgisceSeLaStanzaDoveVoglioSpostarmiIsLaStanzaNulla() {
		this.corrente.impostaStanzaAdiacente(DIREZIONE, NULLA);
		this.comando.setParametro(DIREZIONE.name());
		this.comando.esegui(this.partita);
		assertEquals("corrente", this.partita.getStanzaCorrente().getNome());
	}

	@Test
	void testComandoVaiNonAgisceSeIlParametroImpostatoIsLaDirezioneNulla() {
		this.comando.setParametro(null);
		this.comando.esegui(this.partita);
		assertEquals("corrente", this.partita.getStanzaCorrente().getNome());
	}

	@Test
	void testComandoVaiAgisceSeIlParametroImpostatoIsLaDirezioneNonNullaEStanzaNonNullaVediSetUp() {
		this.comando.setParametro(DIREZIONE.name());
		this.comando.esegui(this.partita);
		assertEquals("adiacente", this.partita.getStanzaCorrente().getNome());
	}

	/*I CFU iniziali del giocatore sono 20*/
	@Test 
	void testComandoVaiValidoModificaICfuDelGiocatoreDellaPartita() {
		this.comando.setParametro(DIREZIONE.name());
		this.comando.esegui(this.partita);
		assertEquals(19, this.partita.getGiocatore().getCfu());

	}
	
	@Test
	void testComandoVaiConBilocale() throws FileNotFoundException, FormatoFileNonValidoException {
		this.partita=new Partita(bilocale);
		this.comando.setParametro("nord");
		this.comando.esegui(this.partita);
		assertEquals(this.partita.getStanzaCorrente().getNome(),"camera");
	}

}