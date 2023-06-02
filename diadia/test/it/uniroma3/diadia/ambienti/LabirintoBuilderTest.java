package it.uniroma3.diadia.ambienti;


import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.FormatoFileNonValidoException;

class LabirintoBuilderTest {
	private Labirinto.LabirintoBuilder builder;
	/*private Labirinto monolocale;
	private Labirinto bilocale;
	private Labirinto trilocale;*/
	
	@BeforeEach
	void setUp() throws FileNotFoundException, FormatoFileNonValidoException{
		this.builder = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio").addAttrezzo("osso", 1)
				.addStanzaVincente("Biblioteca")
				.addStanzaBloccata("Bloccata", Direzioni.est, "chiave")
				.addStanzaBuia("Buia", "lanterna")
				.addStanzaMagica("Magica").addAttrezzo("Attrezzo",1).addAttrezzo("Attrezzo",1).addAttrezzo("Attrezzo",1).addAttrezzo("Attrezzo",1)

				.addAdiacenza("Atrio", "Biblioteca", Direzioni.nord)
				.addAdiacenza("Atrio", "Bloccata", Direzioni.est)
				.addAdiacenza("Atrio", "Magica", Direzioni.ovest)
				.addAdiacenza("Atrio", "Buia", Direzioni.sud);
		
		/*this.monolocale = new LabirintoBuilder()
				.addStanzaIniziale("salotto") // aggiunge una stanza, che sarà anche iniziale
				.addStanzaVincente("salotto") // specifica quala stanza sarà vincente
				.getLabirinto(); // restituisce il Labirinto così specificato
		
		this.bilocale = new LabirintoBuilder()
				.addStanzaIniziale("salotto")
				.addStanzaVincente("camera").addAttrezzo("letto",10) // dove? fa riferimento all’ultima stanza aggiunta: la “camera”
				
				.addAdiacenza("salotto", "camera", "nord") // camera si trova a nord di salotto
				
				.getLabirinto(); // restituisce il Labirinto così specificato
		
		this.trilocale = new LabirintoBuilder()
				.addStanzaIniziale("salotto")
				.addStanza("cucina").addAttrezzo("pentola",1) // dove? fa riferimento all’ultima stanza aggiunta: la “cucina”
				.addStanzaVincente("camera")
				
				.addAdiacenza("salotto", "cucina", "nord")
				.addAdiacenza("cucina", "camera", "est")
				
				.getLabirinto(); // restituisce il Labirinto così specificato*/
	}

	@Test
	void testStanzaIniziale() {
		assertEquals(builder.getLabirinto().getStanzaIniziale().getNome(),"Atrio");
	}

	@Test
	void testStanzaVincente() {
		assertEquals(builder.getLabirinto().getStanzaVincente().getNome(),"Biblioteca");
	}

	@Test
	void testAdiacenza() {
		assertEquals(builder.getLabirinto().getStanzaIniziale().getStanzaAdiacente(Direzioni.nord).getNome(), "Biblioteca");
	}

	@Test
	void testAdiacenzaReciproca() {
		assertEquals(builder.getLabirinto().getStanzaVincente().getStanzaAdiacente(Direzioni.sud).getNome(), "Atrio");
	}

	@Test
	void testOssoInAtrio() {
		assertEquals(builder.getLabirinto().getStanzaIniziale().hasAttrezzo("osso"), true);
		assertEquals(builder.getLabirinto().getStanzaIniziale().getAttrezzo("osso").getPeso(), 1);
	}

	@Test
	void testStanzaBloccataInLabirinto() {
		assertEquals(builder.getLabirinto().getStanzaIniziale().getStanzaAdiacente(Direzioni.est).getDescrizione(),"la porta a " + "est" + " è bloccata\n"
				+ "ti serve l' oggetto " + "chiave" + " nella stanza per aprirla...");
	}

	@Test
	void testStanzaMagicaInLabirinto() {
		assertEquals(builder.getLabirinto().getStanzaIniziale().getStanzaAdiacente(Direzioni.ovest).hasAttrezzo("ozzerttA"),true);
	}

	@Test
	void testStanzaBuiaInLabirinto() {
		assertEquals(builder.getLabirinto().getStanzaIniziale().getStanzaAdiacente(Direzioni.sud).getDescrizione(),"qui c'è buio pesto...");
	}

}



