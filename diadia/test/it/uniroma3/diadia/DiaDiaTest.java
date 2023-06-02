package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Direzioni;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.*;

import java.util.List;
import java.io.FileNotFoundException;
import java.util.ArrayList;

class DiaDiaTest {
	private IOSimulator simulatore;
	private DiaDia gioco;
	private List<String> comandi;
	private Labirinto trilocale;


	@BeforeEach
	void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		this.comandi = new ArrayList<String>();
		this.trilocale = Labirinto.newBuilder()
				.addStanzaIniziale("salotto")
				.addStanza("cucina").addAttrezzo("pentola",1) // dove? fa riferimento all’ultima stanza aggiunta: la “cucina”
				.addStanzaVincente("camera")
				
				.addAdiacenza("salotto", "cucina", Direzioni.nord)
				.addAdiacenza("cucina", "camera", Direzioni.est)
				
				.getLabirinto();
	}

	@Test
	void testDaAtrioABiblioteca() throws Exception {
		comandi.add("vai nord");
		simulatore = new IOSimulator(comandi);
		CaricatoreLabirinto c=new CaricatoreLabirinto("LabirintoDiaDia.txt");
		c.carica();
		Labirinto labirinto = c.getLabirinto();
		labirinto.getStanzaIniziale().addAttrezzo(new Attrezzo("chiave",1));
		this.gioco=new DiaDia(simulatore,labirinto);
		gioco.gioca();
		assertEquals("Hai vinto!", simulatore.getComandiMostrati().get(simulatore.getComandiMostrati().size()-1));
	}

	@Test
	void testFineCfu() throws Exception {
		ArrayList<String> comandi = new ArrayList<String>();
		for(int i=0; i<20; i++) {
			if (i%2==0) comandi.add("vai est");
			else comandi.add("vai ovest");
		}
		simulatore = new IOSimulator(comandi);
		simulatore.setComandiIniettati(comandi);
		CaricatoreLabirinto c=new CaricatoreLabirinto("LabirintoDiaDia.txt");
		c.carica();
		Labirinto labirinto = c.getLabirinto();
		this.gioco=new DiaDia(simulatore,labirinto);
		gioco.gioca();
		assertEquals("Hai esaurito i CFU...", simulatore.getComandiMostrati().get(simulatore.getComandiMostrati().size()-1));
	}
	
	@Test
	void testMessaggioBenvenuto() throws Exception {
		ArrayList<String> comandi = new ArrayList<String>();
		comandi.add("vai nord");
		simulatore = new IOSimulator(comandi);
		simulatore.setComandiIniettati(comandi);
		CaricatoreLabirinto c=new CaricatoreLabirinto("LabirintoDiaDia.txt");
		c.carica();
		Labirinto labirinto = c.getLabirinto();
		labirinto.getStanzaIniziale().addAttrezzo(new Attrezzo("chiave",1));
		this.gioco=new DiaDia(simulatore,labirinto);
		gioco.gioca();
		assertEquals(""+
				"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
				"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
				"I locali sono popolati da strani personaggi, " +
				"alcuni amici, altri... chissa!\n"+
				"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
				"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
				"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
				"Per conoscere le istruzioni usa il comando 'aiuto'.", simulatore.getComandiMostrati().get(0));
	}
	
	@Test
	void testPartitaConTrilocale() throws Exception {
		ArrayList<String> comandi = new ArrayList<String>();
		comandi.add("vai nord");
		comandi.add("vai est");
		simulatore = new IOSimulator(comandi);
		simulatore.setComandiIniettati(comandi);
		this.gioco=new DiaDia(simulatore, trilocale);
		gioco.gioca();
		assertEquals("Hai vinto!",simulatore.getComandiMostrati().get(simulatore.getComandiMostrati().size()-1));
	}
}
