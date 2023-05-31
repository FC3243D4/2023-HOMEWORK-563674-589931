package it.uniroma3.diadia.giocatore;

import java.io.FileNotFoundException;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.properties.CaricatoreProperties;

public class Giocatore {
	
	
	private int cfu;
	private Borsa borsa;
	
	
	public Giocatore() throws FileNotFoundException, FormatoFileNonValidoException {
		this.cfu = new CaricatoreProperties("diadia.properties").getCFU();
		this.borsa = new Borsa();
	}

	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}

	public Borsa getBorsa() {
		return borsa;
	}

	public void setBorsa(Borsa borsa) {
		this.borsa = borsa;
	}
	
	
}