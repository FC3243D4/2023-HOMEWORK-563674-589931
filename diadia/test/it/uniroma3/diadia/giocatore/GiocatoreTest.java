package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.FormatoFileNonValidoException;

class GiocatoreTest {
	private Giocatore giocatore;
	
	@BeforeEach
	void setUp() throws FileNotFoundException, FormatoFileNonValidoException{
		this.giocatore = new Giocatore();
	}

	@Test
	void testNuovoGiocatoreIsNonNull() {
		assertNotNull(this.giocatore);
	}

	@Test
	void testGetCfuAllInizioTOrnaCfuIniziali() {
		assertEquals(20, this.giocatore.getCfu());
	}

	@Test
	void testSetCfuImpostaDiversoNumeroDiCfuRispettoAQuelliIniziali() {
		this.giocatore.setCfu(15);
		assertEquals(15, this.giocatore.getCfu());
	}


}