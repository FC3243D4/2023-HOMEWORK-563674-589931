package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaMagicaTest {
	private StanzaMagica magica;
	private Attrezzo attrezzo;
	private Attrezzo altroAttrezzo;

	@BeforeEach
	void setUp(){
		this.magica = new StanzaMagica("magica"); // soglia attrezzi posati 3
		this.attrezzo = new Attrezzo("attrezzo",1);
		this.altroAttrezzo = new Attrezzo("altro", 2);
	}
	
	void rimuoviEAggiungiAttrezzo(StanzaMagica magica, Attrezzo attrezzo, int n) {
		for(int i = 0; i < n; i++) {
			this.magica.removeAttrezzo(attrezzo.getNome());
			this.magica.addAttrezzo(attrezzo);
		}
	}
	
	
	@Test
	void testAddAttrezzoFattoUnaSolaVoltaNonRendeLaStanzaMagica() {
		this.magica.addAttrezzo(this.attrezzo);
		assertTrue(this.magica.hasAttrezzo("attrezzo"));
		assertEquals(1,this.magica.getAttrezzo("attrezzo").getPeso());
	}

	@Test
	void testAddAttrezzoFattoTreVolteConLoStessoAttrezzoNonRendeLaStanzaMagica() {
		rimuoviEAggiungiAttrezzo(this.magica, this.attrezzo, 3);
		assertTrue(this.magica.hasAttrezzo("attrezzo"));
		assertEquals(1,this.magica.getAttrezzo("attrezzo").getPeso());
	}

	@Test
	void testAddAttrezzoFattoQuattroVolteConLoStessoAttrezzoRendeLaStanzaMagica() {
		rimuoviEAggiungiAttrezzo(this.magica, this.attrezzo, 4);
		assertTrue(this.magica.hasAttrezzo("ozzertta"));
		assertEquals(2,this.magica.getAttrezzo("ozzertta").getPeso());
		
	}
	
	@Test
	void testAddAttrezzoFattoQuattroVolteConDiversiAttrezziRendeLaStanzaMagica() {
		rimuoviEAggiungiAttrezzo(this.magica, this.attrezzo, 3);
		this.magica.addAttrezzo(this.altroAttrezzo);
		assertTrue(this.magica.hasAttrezzo("ortla"));
		assertEquals(4,this.magica.getAttrezzo("ortla").getPeso());
		
	}

	@Test
	void testAddAttrezzoFattoQuattroVolteAggiungendoAncheAttrezzoNulloNonRendeLaStanzaMagica() {
		rimuoviEAggiungiAttrezzo(this.magica, this.attrezzo, 2);
		this.magica.addAttrezzo(null);
		this.magica.addAttrezzo(this.altroAttrezzo);
		assertTrue(this.magica.hasAttrezzo("altro"));
		assertEquals(2,this.magica.getAttrezzo("altro").getPeso());
	}

}
