package it.uniroma3.diadia;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.*;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};

	private Partita partita;
	private IOConsole IO;

	public DiaDia() {
		this.partita = new Partita();
		this.IO=new IOConsole();
	}

	public void gioca() {
		String istruzione;

		IO.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do		
			istruzione = IO.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando daEseguire = new Comando(istruzione);
		String nomeComando=daEseguire.getNome();
		String parametroComando=daEseguire.getParametro();

		if(nomeComando==null)IO.mostraMessaggio("Comando sconosciuto");
			if ("fine".equals(nomeComando)) {
				this.fine(); 
				return true;
			} else if ("vai".equals(nomeComando))
				this.vai(parametroComando);
			else if ("aiuto".equals(nomeComando))
				this.aiuto();
			else if("prendi".equals(nomeComando))
				this.prendi(parametroComando);
			else if("posa".equals(nomeComando))
				this.posa(parametroComando);
			else
				IO.mostraMessaggio("Comando sconosciuto");
		if (this.partita.vinta()) {
			IO.mostraMessaggio("Hai vinto!");
			return true;
		} else
			return false;
	}  


	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			IO.mostraMessaggio(elencoComandi[i]+" ");
		IO.mostraMessaggio("");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			IO.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			IO.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(cfu--);
		}
		IO.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		IO.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}

	public void prendi(String nomeAttrezzo) {
		Attrezzo attrezzi[]=this.partita.getStanzaCorrente().getAttrezzi();
		Attrezzo a=null;
		for(int i=0;i<attrezzi.length;i++) {
			if(attrezzi[i] != null) {
				if(attrezzi[i].getNome().equals(nomeAttrezzo)) {
					a=attrezzi[i];
					this.partita.getStanzaCorrente().removeAttrezzo(attrezzi[i].getNome());
					this.partita.getGiocatore().getBorsa().addAttrezzo(a);
					IO.mostraMessaggio("oggetto "+nomeAttrezzo+" messo in borsa");
				}
			}
		}
		if(a==null) IO.mostraMessaggio("oggetto non presente nella stanza");

	}

	public void posa(String nomeAttrezzo) {
		boolean c=this.partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo);
		Attrezzo a;
		if(c==true) {
			a=this.partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
			this.partita.getStanzaCorrente().addAttrezzo(a);
			IO.mostraMessaggio("oggetto "+nomeAttrezzo+" tolto dalla borsa e posato nella stanza");
		}
		if(c==false) IO.mostraMessaggio("oggetto non presente in borsa");
	}
}