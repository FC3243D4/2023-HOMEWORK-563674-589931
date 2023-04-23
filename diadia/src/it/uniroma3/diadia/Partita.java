package it.uniroma3.diadia;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.giocatore.*;


/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {
	private boolean finita;
	private Labirinto labirinto;
	private Giocatore giocatore;
	private Stanza stanzaCorrente;
	
	public Partita(){
		this.labirinto=new Labirinto();
		this.stanzaCorrente=this.labirinto.creaStanze();
		this.giocatore=new Giocatore();
		this.finita = false;
	}
	
	public Stanza getStanzaCorrente() {
		return stanzaCorrente;
	}

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.stanzaCorrente== this.labirinto.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (this.giocatore.getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}

	public Giocatore getGiocatore() {
		return giocatore;
	}

	public Labirinto getLabirinto() {
		return labirinto;
	}

	public boolean giocatoreIsVivo() {
		if(this.giocatore.getCfu()>0)
			return true;
		return false;
	}


	
}