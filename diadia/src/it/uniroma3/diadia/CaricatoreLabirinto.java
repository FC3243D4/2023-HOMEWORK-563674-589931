package it.uniroma3.diadia;

import java.io.*;
import java.util.*;
import it.uniroma3.diadia.attrezzi.*;
import it.uniroma3.diadia.personaggi.*;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze:";             

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";  

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi:";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite:";

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeMago> <presentazione> <attrezzo> */
	private static final String PERSONAGGI_MARKER_MAGO = "Mago:";

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeStrega> <presentazione> */
	private static final String PERSONAGGI_MARKER_STREGA = "Strega:";

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeCane> <presentazione> */
	private static final String PERSONAGGI_MARKER_CANE = "Cane:";

	/* prefisso della riga contenente il nome stanza buia */
	private static final String STANZE_BUIE_MARKER = "Buia:";  

	/* prefisso della riga contenente il nome stanza bloccata */
	private static final String STANZE_BLOCCATE_MARKER = "Bloccata:";  

	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Inizio: N10
		Vincente: N11
		Attrezzi: martello 10 biblioteca, pinza 2 N10
		Uscite: biblioteca nord N10, biblioteca sud N11

	 */
	private LineNumberReader reader;
	private LabirintoBuilder builder;

	public Labirinto getLabirinto() {
		return this.builder.getLabirinto();
	}


	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.reader = new LineNumberReader(new FileReader(nomeFile));
		this.builder= new LabirintoBuilder();
	}

	public void carica() throws FormatoFileNonValidoException, FileNotFoundException {
		try {
			this.leggiECreaStanze();
			this.leggiInizialeEvincente();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();
			this.leggiECreaMaghi();
			this.leggiECreaStreghe();
			this.leggiECreaCani();
			this.leggiECreaStanzeBuie();
			this.leggiECreaStanzeBloccate();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException, FileNotFoundException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			this.builder.addStanza(nomeStanza);
		}
	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(",");
		try (Scanner scannerDiParole = scanner) {
			while(scannerDiParole.hasNext())result.add(scannerDiParole.next());
		}
		return result;
	}


	private void leggiInizialeEvincente() throws FormatoFileNonValidoException, FileNotFoundException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.builder.setStanzaIniziale(nomeStanzaIniziale);// = this.nome2stanza.get(nomeStanzaIniziale);
		this.builder.setStanzaVincente(nomeStanzaVincente);// = this.nome2stanza.get(nomeStanzaVincente);
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();
			}				
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.builder.addAttrezzoAStanza(nomeAttrezzo, peso, nomeStanza);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}


	private boolean isStanzaValida(String nomeStanza) {
		return this.builder.getMappaStanze().containsKey(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		for(String specificheUscita : separaStringheAlleVirgole(specificheUscite)){
			try (Scanner scannerDiLinea = new Scanner(specificheUscita)) {			

				while (scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
					String stanzaPartenza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
					String dir = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
					String stanzaDestinazione = scannerDiLinea.next();

					impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
				}
			} 
		}
	}

	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
		//Stanza partenzaDa = this.builder.getMappaStanze().get(stanzaDa);
		//Stanza arrivoA = this.builder.getMappaStanze().get(nomeA);
		//partenzaDa.impostaStanzaAdiacente(dir, arrivoA);
		this.builder.addAdiacenza(stanzaDa, nomeA, dir);
	}


	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.builder.getLabirinto().getStanzaIniziale();
	}

	public Stanza getStanzaVincente() {
		return this.builder.getLabirinto().getStanzaVincente();
	}

	private void leggiECreaMaghi() throws FormatoFileNonValidoException {
		String specificheMaghi = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER_MAGO);
		for(String specificaMago : separaStringheAlleVirgole(specificheMaghi)) {
			try (Scanner scannerLinea = new Scanner(specificaMago)){
				String nomeStanza=null;
				String nomeMago=null;
				String presentazione=null;
				String nomeAttrezzo=null;
				String pesoAttrezzo=null;
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la stanza "+specificaMago+" per aggiungere il mago non esiste\n"));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("problemi nella creazione del mago\n"));
				nomeMago = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("inserire una presentazione del mago\n"));
				presentazione = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				int peso = Integer.parseInt(pesoAttrezzo);


				AbstractPersonaggio Mago = new Mago(nomeMago, presentazione, new Attrezzo(nomeAttrezzo,peso));
				check(isStanzaValida(nomeStanza),"stanza in cui inserire il mago sconosciuta\n");
				this.builder.addPersonaggioAStanza(Mago, nomeStanza);
			}
		}
	}

	private void leggiECreaStreghe() throws FormatoFileNonValidoException {
		String specificheStreghe = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER_STREGA);
		for(String specificaStrega : separaStringheAlleVirgole(specificheStreghe)) {
			try (Scanner scannerLinea = new Scanner(specificaStrega)){
				String nomeStanza=null;
				String nomeStrega=null;
				String presentazione=null;
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la stanza "+specificaStrega+" per aggiungere la strega non esiste\n"));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("problemi nella creazione della strega\n"));
				nomeStrega = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("inserire una presentazione della strega\n"));
				presentazione = scannerLinea.next();


				AbstractPersonaggio Strega = new Strega(nomeStrega, presentazione);
				check(isStanzaValida(nomeStanza),"stanza in cui inserire la strega sconosciuta\n");
				this.builder.addPersonaggioAStanza(Strega, nomeStanza);
			}
		}
	}

	private void leggiECreaCani() throws FormatoFileNonValidoException {
		String specificheCani = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER_CANE);
		for(String specificaCane : separaStringheAlleVirgole(specificheCani)) {
			try (Scanner scannerLinea = new Scanner(specificaCane)){
				String nomeStanza=null;
				String nomeCane=null;
				String presentazione=null;
				String nomeAttrezzo=null;
				String pesoAttrezzo=null;
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la stanza "+specificaCane+" per aggiungere il cane non esiste\n"));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("problemi nella creazione del cane\n"));
				nomeCane = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("inserire una presentazione del cane\n"));
				presentazione = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				int peso = Integer.parseInt(pesoAttrezzo);


				AbstractPersonaggio Cane = new Cane(nomeCane, presentazione, new Attrezzo(nomeAttrezzo,peso));
				check(isStanzaValida(nomeStanza),"stanza in cui inserire il mago sconosciuta\n");
				this.builder.addPersonaggioAStanza(Cane, nomeStanza);
			}
		}
	}

	private void leggiECreaStanzeBuie() throws FileNotFoundException, FormatoFileNonValidoException {
		String specificheStanzeBuie = this.leggiRigaCheCominciaPer(STANZE_BUIE_MARKER);
		for(String specificheStanzaBuia : separaStringheAlleVirgole(specificheStanzeBuie)) {
			try(Scanner ScannerLinea = new Scanner(specificheStanzaBuia)){
				String nomeStanza=null;
				String nomeAttrezzo=null;


				check(ScannerLinea.hasNext(),msgTerminazionePrecoce("la stanza "+specificheStanzaBuia+" non esiste\n"));
				nomeStanza=ScannerLinea.next();
				check(ScannerLinea.hasNext(),msgTerminazionePrecoce("errore nella creazione dell'attrezzo per vedere\n"));
				nomeAttrezzo=ScannerLinea.next();

				this.builder.addStanzaBuia(nomeStanza, nomeAttrezzo);

			}
		}
	}

	private void leggiECreaStanzeBloccate() throws FormatoFileNonValidoException, FileNotFoundException{
		String specificheStanzeBloccate = this.leggiRigaCheCominciaPer(STANZE_BLOCCATE_MARKER);
		for(String specificheStanzaBloccata : separaStringheAlleVirgole(specificheStanzeBloccate)) {
			try(Scanner ScannerLinea = new Scanner(specificheStanzaBloccata)){
				String nomeStanza=null;
				String direzione=null;
				String nomeAttrezzo=null;
				check(ScannerLinea.hasNext(),msgTerminazionePrecoce("la stanza "+specificheStanzaBloccata+" non esiste\n"));
				nomeStanza=ScannerLinea.next();
				check(ScannerLinea.hasNext(),msgTerminazionePrecoce("errore nella creazione della direzione bloccata"));
				direzione=ScannerLinea.next();
				check(ScannerLinea.hasNext(),msgTerminazionePrecoce("errore nella creazione dell'attrezzo per sbloccare\n"));
				nomeAttrezzo=ScannerLinea.next();

				this.builder.addStanzaBloccata(nomeStanza, direzione, nomeAttrezzo);
			}
		}
	}
}
