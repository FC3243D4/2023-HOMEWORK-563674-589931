package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.*;

class CaricatoreLabirintoTest {
	private CaricatoreLabirinto caricatore;
	
	@BeforeEach
	void setUp() throws Exception {
		
	}

	@Test
	void testMonolocaleStanzaInizialeEVincente() throws FileNotFoundException, FormatoFileNonValidoException {
		caricatore = new CaricatoreLabirinto("LabirintoMonolocale.txt");
		caricatore.carica();
		Labirinto monolocale=caricatore.getLabirinto();
		assertEquals("salotto",monolocale.getStanzaIniziale().getNome());
		assertEquals("salotto",monolocale.getStanzaVincente().getNome());
	}
	
	@Test
	void testBilocaleChiaveInSalottoELettoInCamera() throws FileNotFoundException, FormatoFileNonValidoException {
		caricatore = new CaricatoreLabirinto("LabirintoBilocale.txt");
		caricatore.carica();
		Labirinto bilocale=caricatore.getLabirinto();
		assertEquals("letto",bilocale.getStanzaVincente().getAttrezzi().get(0).getNome());
	}
	
	@Test
	void testBilocaleCaneInSalotto() throws FileNotFoundException, FormatoFileNonValidoException {
		caricatore = new CaricatoreLabirinto("LabirintoBilocale.txt");
		caricatore.carica();
		Labirinto bilocale=caricatore.getLabirinto();
		assertEquals("Rex",bilocale.getStanzaIniziale().getPersonaggio().getNome());
	}
	
	@Test
	void testTrilocaleMagoInSalottoEStregaInCucina() throws FileNotFoundException, FormatoFileNonValidoException {
		caricatore = new CaricatoreLabirinto("LabirintoTrilocale.txt");
		caricatore.carica();
		Labirinto trilocale=caricatore.getLabirinto();
		assertEquals("Merlino",trilocale.getStanzaIniziale().getPersonaggio().getNome());
		assertEquals("Strega",trilocale.getStanzaIniziale().getStanzaAdiacente(Direzioni.nord).getPersonaggio().getNome());
	}
	
	@Test
	void testBilocaleSalottoBloccata() throws FileNotFoundException, FormatoFileNonValidoException {
		caricatore = new CaricatoreLabirinto("LabirintoBilocale.txt");
		caricatore.carica();
		Labirinto bilocale=caricatore.getLabirinto();
		assertEquals("StanzaBloccata",bilocale.getStanzaIniziale().getClass().getSimpleName());
	}
	
	@Test
	void testTrilocaleSalottoBuiaECucinaMagica() throws FileNotFoundException, FormatoFileNonValidoException {
		caricatore = new CaricatoreLabirinto("LabirintoTrilocale.txt");
		caricatore.carica();
		Labirinto trilocale=caricatore.getLabirinto();
		assertEquals("StanzaBuia",trilocale.getStanzaIniziale().getClass().getSimpleName());
		assertEquals("StanzaMagica",trilocale.getStanzaIniziale().getStanzaAdiacente(Direzioni.nord).getClass().getSimpleName());
	}

}
