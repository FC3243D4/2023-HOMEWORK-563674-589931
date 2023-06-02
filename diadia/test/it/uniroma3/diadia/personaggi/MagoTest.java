package it.uniroma3.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoRegala;

class MagoTest {
	private Mago mago;
	private Partita partita;
	private Labirinto labirinto;
	private Attrezzo delMago;
	private ComandoRegala comandoRegala;

	@BeforeEach
	void setUp() throws FileNotFoundException, FormatoFileNonValidoException{
		this.delMago = new Attrezzo("attrezzo", 5);
		this.mago = new Mago("mago", "uhuu", this.delMago);
		CaricatoreLabirinto c=new CaricatoreLabirinto("LabirintoDiaDia.txt");
		c.carica();
		this.labirinto = c.getLabirinto();
		this.partita = new Partita(this.labirinto);
		this.partita.getStanzaCorrente().setPersonaggio(this.mago);
		this.comandoRegala = new ComandoRegala();
	}

	@Test
	void testDopoComandaRegalasulMagoIsPresenteAttrezzoInStanza() {
		this.partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("regalo",1));
		this.comandoRegala.setParametro("regalo");
		this.comandoRegala.esegui(this.partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("attrezzo"));
	}

	@Test
	void testDopoComandaRegalasulMagoIsPresenteAttrezzoInStanzaConPesoDimezzato() {
		this.partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("regalo",1));
		this.comandoRegala.setParametro("regalo");
		this.comandoRegala.esegui(this.partita);
		assertEquals(2,this.partita.getStanzaCorrente().getAttrezzo("attrezzo").getPeso());
	}
	
	@Test
	void testDopoComandaRegalaAttrezzoDelMagoIsNull() {
		this.partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("regalo",1));
		this.comandoRegala.setParametro("regalo");
		this.comandoRegala.esegui(this.partita);
		assertNull(this.mago.getAttrezzo());
	}
	
}