package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FabbricaDiComandiFisarmonicaTest {
	private static final String DIREZIONE = "direzione";
	private static final String NOME_ATTREZZO = "attrezzo";

	private FabbricaDiComandiFisarmonica factory;
	private Comando comando;
		

	@BeforeEach
	void setUp(){
		this.factory = new FabbricaDiComandiFisarmonica();
		
	}

	@Test
	void testComandoCostruitoConCostruisciComandoisComandoFine() {
		this.comando = this.factory.costruisciComando("fine");
		assertEquals("comandoFine", this.comando.getNome());
	}
	
	@Test
	void testComandoCostruitoConCostruisciComandoisComandoAiuto() {
		this.comando = this.factory.costruisciComando("aiuto");
		assertEquals("comandoAiuto", this.comando.getNome());
	}
	
	@Test
	void testComandoCostruitoConCostruisciComandoisComandoGuarda() {
		this.comando = this.factory.costruisciComando("guarda");
		assertEquals("comandoGuarda", this.comando.getNome());
	}
	
	@Test
	void testComandoCostruitoConCostruisciComandoPassandogliStringaVuotaisComandoNonValido() {
		this.comando = this.factory.costruisciComando("");
		assertEquals("comandoNonValido", this.comando.getNome());
	}
	
	@Test
	void testComandoCostruitoConCostruisciComandoPassandogliComandoSconosciutoisComandoNonValido() {
		this.comando = this.factory.costruisciComando("guida");// guida è comando sconosciuto
		assertEquals("comandoNonValido", this.comando.getNome());
	}
	
	@Test
	void testComandoCostruitoConCostruisciComandoisComandoPosa() {
		this.comando = this.factory.costruisciComando("posa" + " " + NOME_ATTREZZO);
		assertEquals("comandoPosa", this.comando.getNome());
		assertEquals("attrezzo", this.comando.getParametro());
	}
	
	@Test
	void testComandoCostruitoConCostruisciComandoisComandoPrendi() {
		this.comando = this.factory.costruisciComando("prendi" + " " + NOME_ATTREZZO);
		assertEquals("comandoPrendi", this.comando.getNome());
		assertEquals("attrezzo", this.comando.getParametro());
	}
	
	@Test
	void testComandoCostruitoConCostruisciComandoisComandoVai() {
		this.comando = this.factory.costruisciComando("vai" + " " + DIREZIONE);
		assertEquals("comandoVai", this.comando.getNome());
		assertEquals("direzione", this.comando.getParametro());
	}
	

}
