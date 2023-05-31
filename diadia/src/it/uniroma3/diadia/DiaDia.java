package it.uniroma3.diadia;
import java.io.FileNotFoundException;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.comandi.*;
import it.uniroma3.diadia.properties.*;

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

	/*static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";*/

	private Partita partita;
	private IO IO; //tipo astratto IO

	public DiaDia(IO console, Labirinto labirinto) throws FileNotFoundException, FormatoFileNonValidoException {
		this.IO = console;
		this.partita = new Partita(labirinto);
	}

	public void gioca() throws FileNotFoundException, FormatoFileNonValidoException {
		String istruzione;

		IO.mostraMessaggio(new CaricatoreProperties("diadia.properties").getMessaggioBenvenuto());
		do		
			istruzione = IO.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 * @throws Exception 
	 */
	private boolean processaIstruzione(String istruzione) throws Exception{
		AbstractComando comandoDaEseguire;
		FabbricaDiComandiRiflessiva factory = new FabbricaDiComandiRiflessiva();

		comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita);
		
		if (this.partita.vinta())
			IO.mostraMessaggio("Hai vinto!");
		if (!this.partita.giocatoreIsVivo())
			IO.mostraMessaggio("Hai esaurito i CFU...");
		return this.partita.isFinita();
		}
	
	public static void main(String[] argc) throws FileNotFoundException, FormatoFileNonValidoException {
		IO io = new IOConsole();
		Labirinto labirinto = new Labirinto().LabirintoDiaDia();
		DiaDia gioco = new DiaDia(io, labirinto);
		gioco.gioca();
	}


}