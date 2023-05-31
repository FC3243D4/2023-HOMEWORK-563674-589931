package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;

class AbstractComandoTest {
	
	private Partita partita;
	private Labirinto labirinto;
	private AbstractComando comandoAstratto;

	@BeforeEach
	void setUp(){
		this.labirinto = new Labirinto();
		this.partita = new Partita(this.labirinto);
		
	}

	@Test
	void testComandoAstrattoIstanziatoIsComandoAiuto() {
		this.comandoAstratto = new ComandoAiuto();
		assertEquals("ComandoAiuto",this.comandoAstratto.getClass().getSimpleName());
	}
	
	@Test
	void testComandoAstrattoIstanziatoIsComandoFine() {
		this.comandoAstratto = new ComandoFine();
		assertEquals("ComandoFine",this.comandoAstratto.getClass().getSimpleName());
		
	}
	
	@Test
	void testComandoAstrattoIstanziatoIsComandoFineEseguitoPortaAllaFineDellaPartita() {
		this.comandoAstratto = new ComandoFine();
		this.comandoAstratto.esegui(this.partita);
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	void testComandoAstrattoIstanziatoIsComandoGuarda() {
		this.comandoAstratto = new ComandoGuarda();
		assertEquals("ComandoGuarda",this.comandoAstratto.getClass().getSimpleName());
	}
	
	@Test
	void testComandoAstrattoIstanziatoIsComandoNonValido() {
		this.comandoAstratto = new ComandoNonValido();
		assertEquals("ComandoNonValido",this.comandoAstratto.getClass().getSimpleName());
	}
	
	
	@Test
	void testComandoAstrattoIstanziatoIsComandoInteragisci() {
		this.comandoAstratto = new ComandoInteragisci();
		assertEquals("ComandoInteragisci",this.comandoAstratto.getClass().getSimpleName());
	}
	
	@Test
	void testComandoAstrattoIstanziatoIsComandoSaluta() {
		this.comandoAstratto = new ComandoSaluta();
		assertEquals("ComandoSaluta",this.comandoAstratto.getClass().getSimpleName());
	}

}
