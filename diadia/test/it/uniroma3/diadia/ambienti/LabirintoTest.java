package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class LabirintoTest {
	private Labirinto labirinto;
	private Stanza stanzaCorrente;
	@BeforeEach
	void setUp(){
		this.labirinto=new Labirinto();
		this.stanzaCorrente=labirinto.creaStanze();
	}

	@Test
	void testAtrioSudBiblioteca(){
		assertEquals("Atrio",stanzaCorrente.getStanzaAdiacente("nord").getStanzaAdiacente("sud").getNome());
	}
	
	@Test
	void testBibliotecaNordAtrio(){
		assertEquals("Biblioteca",stanzaCorrente.getStanzaAdiacente("nord").getNome());
	}
	
	@Test
	void testBibliotecaStanzaVincente() {
		assertEquals("Biblioteca",this.labirinto.getStanzaVincente().getNome());
	}

}