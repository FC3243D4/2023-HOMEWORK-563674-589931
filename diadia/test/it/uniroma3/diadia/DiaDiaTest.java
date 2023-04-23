package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DiaDiaTest {
	private IOSimulator simulatore;
	private DiaDia gioco;


	@BeforeEach
	void setUp() {
		simulatore = new IOSimulator();
		this.gioco = new DiaDia(simulatore);
	}

	@Test
	void testDaAtrioABiblioteca() {
		String[] comandi = {"vai nord"};
		simulatore.setComandiIniettati(comandi);
		gioco.gioca();
		String[] mostrati=simulatore.getComandiMostrati();
		assertEquals("Hai vinto!", mostrati[simulatore.getCountOUT()]);
	}

	@Test
	void test() {
		String[] comandi = new String[21];
		for(int i=0; i<20; i++) {
			if (i%2==0) comandi[i]="vai est";
			else comandi[i]="vai ovest";
		}
		simulatore.setComandiIniettati(comandi);
		gioco.gioca();
		String[] mostrati=simulatore.getComandiMostrati();
		assertEquals("Hai esaurito i CFU...", mostrati[simulatore.getCountOUT()]);
	}
	
	@Test
	void testMessaggioBenvenuto() {
		String[] comandi = {"vai nord"};
		simulatore.setComandiIniettati(comandi);
		gioco.gioca();
		String[] mostrati=simulatore.getComandiMostrati();
		assertEquals(""+
				"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
				"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
				"I locali sono popolati da strani personaggi, " +
				"alcuni amici, altri... chissa!\n"+
				"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
				"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
				"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
				"Per conoscere le istruzioni usa il comando 'aiuto'.", mostrati[0]);
	}
}

