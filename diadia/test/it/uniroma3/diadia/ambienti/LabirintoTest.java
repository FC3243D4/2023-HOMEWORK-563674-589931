package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.attrezzi.Attrezzo;


class LabirintoTest {
	private Labirinto labirinto;
	private Stanza stanzaCorrente;
	
	@BeforeEach
	void setUp() throws FileNotFoundException, FormatoFileNonValidoException{
		CaricatoreLabirinto c=new CaricatoreLabirinto("LabirintoDiaDia.txt");
		c.carica();
		this.labirinto = c.getLabirinto();
		this.labirinto.getStanzaIniziale().addAttrezzo(new Attrezzo("chiave",1));
		this.stanzaCorrente=this.labirinto.getStanzaIniziale();
	}

	@Test
	void testAtrioSudBiblioteca(){
		assertEquals("Atrio",stanzaCorrente.getStanzaAdiacente(Direzioni.nord).getStanzaAdiacente(Direzioni.sud).getNome());
	}
	
	@Test
	void testBibliotecaNordAtrio(){
		assertEquals("Biblioteca",stanzaCorrente.getStanzaAdiacente(Direzioni.nord).getNome());
	}
	
	@Test
	void testBibliotecaStanzaVincente() {
		assertEquals("Biblioteca",this.labirinto.getStanzaVincente().getNome());
	}

}