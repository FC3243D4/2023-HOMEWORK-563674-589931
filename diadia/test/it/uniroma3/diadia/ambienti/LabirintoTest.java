package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LabirintoTest {
	private Labirinto labirinto;
	@BeforeEach
	void setUp(){
		labirinto=new Labirinto();
		labirinto.creaStanze();
	}

	@Test
	void testOssoInAtrio() {
		assertEquals(true,this.labirinto.getStanzaCorrente().hasAttrezzo("osso"));
	}
	
	@Test
	void testAtrioStanzaIniziale() {
		assertEquals("Atrio",this.labirinto.getStanzaCorrente().getNome());
	}
	
	@Test
	void testBibliotecaStanzaVincente() {
		assertEquals("Biblioteca",this.labirinto.getStanzaVincente().getNome());
	}

}
