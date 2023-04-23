package it.uniroma3.diadia;
import it.uniroma3.diadia.ambienti.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PartitaTest {
	private Stanza stanzaNull;
	private Stanza stanza1;
	private Stanza stanza2;
	private Partita partita;
	
	@BeforeEach
	void setUp() {
		this.stanzaNull=null;
		this.stanza1=new Stanza("stanza 1");
		this.stanza2=new Stanza("stanza 2");
		this.partita=new Partita();
		}

	@Test
	void stanzaVincenteVuota() {
		this.partita.getLabirinto().setStanzaVincente(stanzaNull);
		this.partita.setStanzaCorrente(stanza1);
		assertEquals(false,this.partita.vinta());
	}
	
	@Test
	void stanzaCorrenteVuota() {
		this.partita.getLabirinto().setStanzaVincente(stanza1);
		this.partita.setStanzaCorrente(stanzaNull);
		assertEquals(false,this.partita.vinta());
	}
	
	@Test
	void stanzaVincenteECorrenteVuote() {
		this.partita.getLabirinto().setStanzaVincente(stanzaNull);
		this.partita.setStanzaCorrente(stanzaNull);
		assertEquals(true,this.partita.vinta());
	}
	
	@Test
	void stanzaVincenteDiversaStanzaCorrente() {
		this.partita.setStanzaCorrente(stanza1);
		this.partita.getLabirinto().setStanzaVincente(stanza2);
		assertEquals(false,this.partita.vinta());
	}
	
	@Test
	void stanzaVincenteUgualeStanzaCorrente() {
		this.partita.setStanzaCorrente(stanza1);
		this.partita.getLabirinto().setStanzaVincente(stanza1);
		assertEquals(true,this.partita.vinta());
	}
	
	@Test
	void finitaTrueVintaTrueCfu0() {
		this.partita.getGiocatore().setCfu(0);
		this.partita.setStanzaCorrente(stanza1);
		this.partita.getLabirinto().setStanzaVincente(stanza1);
		this.partita.setFinita();
		if(this.partita.vinta()!=true) fail("il metodo vinta() non funziona come ci si aspetta");
		else assertEquals(true,this.partita.isFinita());
	}
	
	@Test
	void finitaTrueVintaTrueCfuDiversiDa0() {
		this.partita.getGiocatore().setCfu(1);
		this.partita.setStanzaCorrente(stanza1);
		this.partita.getLabirinto().setStanzaVincente(stanza1);
		this.partita.setFinita();
		if(this.partita.vinta()!=true) fail("il metodo vinta() non funziona come ci si aspetta");
		else assertEquals(true,this.partita.isFinita());
	}
	
	@Test
	void finitaTrueVintaFalseCfu0() {
		this.partita.getGiocatore().setCfu(0);
		this.partita.setStanzaCorrente(stanza1);
		this.partita.getLabirinto().setStanzaVincente(stanza2);
		this.partita.setFinita();
		if(this.partita.vinta()!=false) fail("il metodo vinta() non funziona come ci si aspetta");
		else assertEquals(true,this.partita.isFinita());
	}
	
	@Test
	void finitaTrueVintaFalseCfuDiversiDa0() {
		this.partita.getGiocatore().setCfu(1);
		this.partita.setStanzaCorrente(stanza1);
		this.partita.getLabirinto().setStanzaVincente(stanza2);
		this.partita.setFinita();
		if(this.partita.vinta()!=false) fail("il metodo vinta() non funziona come ci si aspetta");
		else assertEquals(true,this.partita.isFinita());
	}
	
	@Test
	void finitaFalseVintaTrueCfu0() {
		this.partita.getGiocatore().setCfu(0);
		this.partita.setStanzaCorrente(stanza1);
		this.partita.getLabirinto().setStanzaVincente(stanza1);
		if(this.partita.vinta()!=true) fail("il metodo vinta() non funziona come ci si aspetta");
		else assertEquals(true,this.partita.isFinita());
	}
	
	@Test
	void finitaFalseVintaTrueCfuDiversiDa0() {
		this.partita.getGiocatore().setCfu(1);
		this.partita.setStanzaCorrente(stanza1);
		this.partita.getLabirinto().setStanzaVincente(stanza1);
		if(this.partita.vinta()!=true) fail("il metodo vinta() non funziona come ci si aspetta");
		else assertEquals(true,this.partita.isFinita());
	}
	
	@Test
	void finitaFalseVintaFalseCfu0() {
		this.partita.getGiocatore().setCfu(0);
		this.partita.setStanzaCorrente(stanza1);
		this.partita.getLabirinto().setStanzaVincente(stanza1);
		if(this.partita.vinta()!=true) fail("il metodo vinta() non funziona come ci si aspetta");
		else assertEquals(true,this.partita.isFinita());
	}
	
	@Test
	void finitaFalseVintaFalseCfuDiversiDa0() {
		this.partita.getGiocatore().setCfu(1);
		this.partita.setStanzaCorrente(stanza1);
		this.partita.getLabirinto().setStanzaVincente(stanza2);
		if(this.partita.vinta()!=false) fail("il metodo vinta() non funziona come ci si aspetta");
		else assertEquals(false,this.partita.isFinita());
	}
}