package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.properties.CaricatoreProperties;

public class Giocatore {
	
	public final static int DEFAULT_CFU = CaricatoreProperties.getCFU();
	private int cfu;
	private Borsa borsa;
	
	
	public Giocatore() {
		this.cfu = DEFAULT_CFU;
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