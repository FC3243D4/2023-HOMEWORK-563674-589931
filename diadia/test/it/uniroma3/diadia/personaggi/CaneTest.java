package it.uniroma3.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoInteragisci;
import it.uniroma3.diadia.comandi.ComandoRegala;

class CaneTest {
	
	private Cane cane;
	private ComandoInteragisci comandoInteragisci;
	private ComandoRegala comandoRegala;
	private Partita partita;
	private Labirinto labirinto;
	private Attrezzo martello;

	@BeforeEach
	void setUp() throws FileNotFoundException, FormatoFileNonValidoException{
		CaricatoreLabirinto c=new CaricatoreLabirinto("LabirintoDiaDia.txt");
		c.carica();
		this.labirinto = c.getLabirinto();
		this.partita = new Partita(this.labirinto);
		this.comandoInteragisci = new ComandoInteragisci();
		this.comandoRegala = new ComandoRegala();
		this.martello = new Attrezzo("martello", 8);
		this.cane = new Cane("cane", "bau", martello);
		this.partita.getStanzaCorrente().setPersonaggio(this.cane);
		
		this.partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("cibo",1));
		this.comandoRegala.setParametro("cibo");
		this.comandoRegala.esegui(this.partita);
	}

	@Test
	void testAgisciDiCaneAbbassaDiUnoICfuDelGiocatore() {
		this.comandoInteragisci.esegui(this.partita);
		assertEquals(19,this.partita.getGiocatore().getCfu());
	}
	
	@Test
	void testDopoComandoRegalaSuCaneInStanzaIsNotPresenteRegaloInBorsa() {
		assertEquals(false,this.partita.getGiocatore().getBorsa().hasAttrezzo("cibo"));
	}
	
	@Test
	void testDopoComandoRegalaSuCaneInStanzaIsPresenteAttrezzo() {
		assertEquals(true,this.partita.getStanzaCorrente().hasAttrezzo("martello"));
	}
	
	@Test
	void testNuovoTentativodiComandoRegalaConAttrezzoSbagliatoFaPerdereCfuAlGiocatore() {
		this.partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("occhiali",1));
		this.comandoRegala.setParametro("occhiali");
		this.comandoRegala.esegui(this.partita);
		assertEquals(19,this.partita.getGiocatore().getCfu());
	}

}