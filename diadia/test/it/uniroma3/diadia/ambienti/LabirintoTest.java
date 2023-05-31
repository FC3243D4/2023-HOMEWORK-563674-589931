package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.FormatoFileNonValidoException;


class LabirintoTest {
	private Labirinto labirinto;
	private Stanza stanzaCorrente;
	@BeforeEach
	void setUp() throws FileNotFoundException, FormatoFileNonValidoException{
		this.labirinto=new Labirinto().LabirintoDiaDia();
		this.stanzaCorrente=labirinto.getStanzaIniziale();
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