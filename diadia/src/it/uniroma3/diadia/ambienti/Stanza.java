package it.uniroma3.diadia.ambienti;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.attrezzi.*;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.properties.*;


/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
 */

public class Stanza {


	private String nome;
	private List<Attrezzo> attrezzi;
	private int numeroAttrezzi;
	private int numeroStanzeAdiacenti;
	private List<String> direzioni;
	private Map<String, Stanza> mapStanzeAdiacenti;
	private AbstractPersonaggio personaggio;
	private CaricatoreProperties caricatore;
	
	public void setPersonaggio (AbstractPersonaggio personaggio) {
		this.personaggio=personaggio;
	}
	
	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}

	public Map<String, Stanza> getMapStanzeAdiacenti() {
		return mapStanzeAdiacenti;
	}
	

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * @param nome il nome della stanza
	 * @throws FormatoFileNonValidoException 
	 * @throws FileNotFoundException 
	 */
	public Stanza(String nome) throws FileNotFoundException, FormatoFileNonValidoException {
		this.nome = nome;
		this.numeroStanzeAdiacenti = 0;
		this.numeroAttrezzi = 0;
		this.caricatore=new CaricatoreProperties("diadia.properties");
		this.attrezzi = new ArrayList<Attrezzo>();
		this.direzioni = new ArrayList<String>(caricatore.getNumeroMassimoDirezioni());
		this.mapStanzeAdiacenti = new HashMap<String, Stanza>();
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
	 */
	
//	public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
//		boolean aggiornato = false;
//		for(int i=0; i<this.direzioni.length; i++)
//			if (direzione.equals(this.direzioni[i])) {
//				this.stanzeAdiacenti[i] = stanza;
//				aggiornato = true;
//			}
//		if (!aggiornato)
//			if (this.numeroStanzeAdiacenti < NUMERO_MASSIMO_DIREZIONI) {
//				this.direzioni[numeroStanzeAdiacenti] = direzione;
//				this.stanzeAdiacenti[numeroStanzeAdiacenti] = stanza;
//				this.numeroStanzeAdiacenti++;
//			}
//	}
	
	public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
		if(this.mapStanzeAdiacenti.put(direzione, stanza)==null)
			numeroStanzeAdiacenti++;
		else
			this.mapStanzeAdiacenti.put(direzione, stanza);

	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * @param direzione
	 */
	
//	public Stanza getStanzaAdiacente(String direzione) {
//		Stanza stanza = null;
//		for(int i=0; i<this.numeroStanzeAdiacenti; i++)
//			if (this.direzioni[i].equals(direzione))
//				stanza = this.stanzeAdiacenti[i];
//		return stanza;
//	}
	
	public Stanza getStanzaAdiacente(String direzione) {
		return this.mapStanzeAdiacenti.get(direzione);
	}

	/**
	 * Restituisce la nome della stanza.
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * @return la collezione di attrezzi nella stanza.
	 */
	public List<Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 * @throws FormatoFileNonValidoException 
	 */
	
//	public boolean addAttrezzo(Attrezzo attrezzo) {
//		if (attrezzo!=null && this.numeroAttrezzi < NUMERO_MASSIMO_ATTREZZI) {
//			this.attrezzi[numeroAttrezzi] = attrezzo;
//			this.numeroAttrezzi++;
//			return true;
//		}
//		else {
//			return false;
//		}
//	}
	
	public boolean addAttrezzo(Attrezzo attrezzo) throws FormatoFileNonValidoException {
		
		if (attrezzo!=null && this.numeroAttrezzi < caricatore.getNumeroMassimoAttrezzi()) {
			return this.attrezzi.add(attrezzo);
		}
		
		return false;
		
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza,
	 * stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite: ");
		for (String direzione : this.direzioni)
			if (direzione!=null)
				risultato.append(" " + direzione);
		risultato.append("\nAttrezzi nella stanza: ");
		for (Attrezzo attrezzo : this.attrezzi) {
			if (attrezzo!=null)
				risultato.append(attrezzo.toString()+" ");
		}
		return risultato.toString();
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		boolean trovato;
		trovato = false;
		for (Attrezzo attrezzo : this.attrezzi) {
			if (attrezzo!=null) {
				if (attrezzo.getNome().equals(nomeAttrezzo))
					trovato = true;
			}
		}
		return trovato;
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
	 * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo attrezzoCercato;
		attrezzoCercato = null;
		for (Attrezzo attrezzo : this.attrezzi) {
			if (attrezzo!=null) {
				if (attrezzo.getNome().equals(nomeAttrezzo))
					attrezzoCercato = attrezzo;
			}
		}
		return attrezzoCercato;	
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a;
		Iterator<Attrezzo> iter = this.attrezzi.iterator();
		
		while(iter.hasNext()) {
			a = iter.next();
			if(a.getNome().equals(nomeAttrezzo)) {
				iter.remove();
				return true;
			}
		}
		
		return false;
	}


//	public String[] getDirezioni() {
//		String[] direzioni = new String[this.numeroStanzeAdiacenti];
//		for(int i=0; i<this.numeroStanzeAdiacenti; i++)
//			direzioni[i] = this.direzioni[i];
//		return direzioni;
//	}
	
	public List<String> getDirezioni() {
		List<String> direzioni = new ArrayList<String>(this.numeroStanzeAdiacenti);
		for(String d : this.direzioni)
			direzioni.add(d);
		return direzioni;
	}
	
	public boolean isMagica() throws FileNotFoundException, FormatoFileNonValidoException {
		StanzaMagica StanzaMagicaTest = new StanzaMagica("test");
		if (this.getClass()==StanzaMagicaTest.getClass()) return true;
		return false;
	}
}