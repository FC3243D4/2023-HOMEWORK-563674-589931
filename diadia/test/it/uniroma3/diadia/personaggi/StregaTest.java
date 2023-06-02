package it.uniroma3.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzioni;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoInteragisci;
import it.uniroma3.diadia.comandi.ComandoRegala;

class StregaTest {
	
	private Partita partita;
	private Labirinto labirinto ;
	private Stanza corrente,adiacente, adiacenteNord, adiacenteSud, adiacenteEst, adiacenteOvest;
	private Attrezzo osso,martello,occhiali, pettine, spada;
	private ComandoInteragisci comandoInteragisci;
	private ComandoRegala comandoRegala;
	private Strega strega;

	@BeforeEach
	void setUp() throws FileNotFoundException, FormatoFileNonValidoException{
		CaricatoreLabirinto c=new CaricatoreLabirinto("LabirintoDiaDia.txt");
		c.carica();
		this.labirinto = c.getLabirinto();
		this.partita=new Partita(labirinto);
		this.strega = new Strega("strega", "ah aha");
		this.strega.setGiocatoreHaSalutato(true);
		this.comandoInteragisci = new ComandoInteragisci();
		this.comandoRegala =new ComandoRegala();
		
		this.corrente = new Stanza("corrente");
		this.adiacente = new Stanza("adiacente");
		this.corrente.impostaStanzaAdiacente(Direzioni.nord, this.adiacente);
		this.corrente.setPersonaggio(this.strega);
		this.partita.setStanzaCorrente(this.corrente);
		
		this.osso = new Attrezzo("osso", 3);
		this.occhiali = new Attrezzo("occhiali", 1);
		this.martello = new Attrezzo("martello", 8);
		this.pettine = new Attrezzo("pettine", 2);
		this.spada = new Attrezzo("spada", 5);
		
		this.adiacenteNord = new Stanza("adiacenteNord");
		this.adiacenteSud = new Stanza("adiacenteSud");
		this.adiacenteEst = new Stanza("adiacenteEst");
		this.adiacenteOvest = new Stanza("adiacenteOvest");
		
		this.adiacenteNord.addAttrezzo(this.martello);
		this.adiacenteSud.addAttrezzo(this.spada);
		this.adiacenteEst.addAttrezzo(this.pettine);
		this.adiacenteEst.addAttrezzo(this.osso);
		this.adiacenteOvest.addAttrezzo(this.occhiali);
		
	}

	@Test
	void testMetodoAgisciSpostaGiocatoreInUnicaStanzaAdiacente() {
		this.comandoInteragisci.esegui(this.partita);
		assertEquals(adiacente,this.partita.getStanzaCorrente());
	}

	@Test
	void testMetodoAgisciSpostaGiocatoreInStanzaConNumeroAttrezziMaxTraDueStanze() {		
		this.corrente.impostaStanzaAdiacente(Direzioni.nord, this.adiacenteNord);
		this.corrente.impostaStanzaAdiacente(Direzioni.est, this.adiacenteEst);
		
		this.comandoInteragisci.esegui(this.partita);
		assertEquals(adiacenteEst,this.partita.getStanzaCorrente());
	}
	
	@Test
	void testMetodoAgisciSpostaGiocatoreInStanzaConNumeroAttrezziMaxConTreAdiacenti() {		
		this.corrente.impostaStanzaAdiacente(Direzioni.nord, this.adiacenteNord);
		this.corrente.impostaStanzaAdiacente(Direzioni.est, this.adiacenteEst);
		this.corrente.impostaStanzaAdiacente(Direzioni.sud, this.adiacenteSud);

		
		this.comandoInteragisci.esegui(this.partita);
		assertEquals(adiacenteEst,this.partita.getStanzaCorrente());
	}
	
	@Test
	void testMetodoAgisciSpostaGiocatoreInStanzaConNumeroAttrezziMaxConQuattroAdiacenti() {		
		this.strega.setGiocatoreHaSalutato(true);
		
		this.corrente.impostaStanzaAdiacente(Direzioni.nord, this.adiacenteNord);
		this.corrente.impostaStanzaAdiacente(Direzioni.est, this.adiacenteEst);
		this.corrente.impostaStanzaAdiacente(Direzioni.sud, this.adiacenteSud);
		this.corrente.impostaStanzaAdiacente(Direzioni.ovest, this.adiacenteOvest);

		this.comandoInteragisci.esegui(this.partita);
		assertEquals(adiacenteEst,this.partita.getStanzaCorrente());
	}
	
	@Test
	void testMetodoAgisciDiStregaNonSalutataSpostaGiocatoreInStanzaConNumeroAttrezziMinConQuattroAdiacenti() {		
		//strega non salutata
		this.strega.setGiocatoreHaSalutato(false);
		
		//arrivo a due attrezzi in tutte tranne in adiacenteOvest
		this.adiacenteSud.addAttrezzo(this.occhiali);
		this.adiacenteNord.addAttrezzo(this.pettine);
		
		this.corrente.impostaStanzaAdiacente(Direzioni.nord, this.adiacenteNord);
		this.corrente.impostaStanzaAdiacente(Direzioni.est, this.adiacenteEst);
		this.corrente.impostaStanzaAdiacente(Direzioni.sud, this.adiacenteSud);
		this.corrente.impostaStanzaAdiacente(Direzioni.ovest, this.adiacenteOvest);

		this.comandoInteragisci.esegui(this.partita);
		assertEquals(this.adiacenteOvest,this.partita.getStanzaCorrente());
	}
	
	@Test
	void testComandoRegalaSuStregaRimuoveAttrezzoDallaBorsaDelGiocatore() {
		this.partita.getGiocatore().getBorsa().addAttrezzo(this.martello);
		this.comandoRegala.setParametro("martello");
		this.comandoRegala.esegui(this.partita);
		assertEquals(false,this.partita.getGiocatore().getBorsa().hasAttrezzo("martello"));
	}
	
}