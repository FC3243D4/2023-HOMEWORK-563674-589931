package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPrendiTest {
	private ComandoPrendi comando;
	private Partita partita;
	private Stanza stanza;
	private Attrezzo attrezzo;
	private Labirinto monolocale;
	

	@BeforeEach
	void setUp() throws FileNotFoundException, FormatoFileNonValidoException{
		this.comando = new ComandoPrendi();
		CaricatoreLabirinto c=new CaricatoreLabirinto("LabirintoDiaDia.txt");
		c.carica();
		Labirinto labirinto = c.getLabirinto();
		this.partita=new Partita(labirinto);
		this.stanza = new Stanza("stanza");
		this.partita.setStanzaCorrente(stanza);
		this.attrezzo = new Attrezzo("attrezzo", 1);
		stanza.addAttrezzo(attrezzo);
		
		this.monolocale = Labirinto.newBuilder()
				.addStanzaIniziale("camera").addAttrezzo("letto",10)

				.getLabirinto();
		
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
	
	@Test
	void testComandoPrendiConMonolocale() throws FileNotFoundException, FormatoFileNonValidoException {
		this.partita=new Partita(monolocale);
		this.comando.setParametro("letto");
		this.comando.esegui(this.partita);
		assertEquals(true,this.partita.getGiocatore().getBorsa().hasAttrezzo("letto"));	
	}

	
}