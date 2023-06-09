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

class ComandoPosaTest {
	private ComandoPosa comando;
	private Partita partita;
	private Stanza stanza;
	//private Attrezzo attrezzoNonInBorsa;
	private Attrezzo attrezzoInBorsa;
	private Labirinto monolocale;
	

	@BeforeEach
	void setUp() throws FileNotFoundException, FormatoFileNonValidoException{
		this.comando = new ComandoPosa();
		CaricatoreLabirinto c=new CaricatoreLabirinto("LabirintoDiaDia.txt");
		c.carica();
		Labirinto labirinto = c.getLabirinto();
		this.partita=new Partita(labirinto);
		this.stanza = new Stanza("stanza");
		this.partita.setStanzaCorrente(stanza);
		//this.attrezzoNonInBorsa = new Attrezzo("nonInBorsa", 1);
		this.attrezzoInBorsa = new Attrezzo("attrezzo", 1);
		this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzoInBorsa);
		
		this.monolocale = Labirinto.newBuilder()
				.addStanzaIniziale("salotto")
				.addStanzaVincente("salotto") 
				.getLabirinto();
	}

	@Test
	void testComandoPosaNonAgisceSeIlNomeDellAttrezzoIsLaStringaNulla() {
		this.comando.setParametro(null);
		this.comando.esegui(partita);
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo("attrezzo"));
	}
	
	@Test
	void testComandoPosaSuAttrezzoNonInBorsaNonAgisce() {
		this.comando.setParametro("nonInBorsa");
		this.comando.esegui(partita);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("nonInBorsa"));
	}

	@Test
	void testComandoPosaSuAttrezzoInBorsaAggiungeLAttrezzoAllaStanza() {
		this.comando.setParametro("attrezzo");
		this.comando.esegui(partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("attrezzo"));	
	}
	
	@Test
	void testComandoPosaSuAttrezzoInBorsaLoRimuove() {
		this.comando.setParametro("attrezzo");
		this.comando.esegui(partita);
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("attrezzo"));
	}
	
	@Test
	void testConMonolocale() throws FileNotFoundException, FormatoFileNonValidoException {
		this.partita=new Partita(monolocale);
		this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzoInBorsa);
		this.comando.setParametro("attrezzo");
		this.comando.esegui(this.partita);
		assertEquals(true,this.partita.getStanzaCorrente().hasAttrezzo("attrezzo"));
	}

}