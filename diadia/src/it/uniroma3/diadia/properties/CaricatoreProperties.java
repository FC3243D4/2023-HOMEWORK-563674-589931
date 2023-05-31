package it.uniroma3.diadia.properties;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.FormatoFileNonValidoException;

public class CaricatoreProperties {
	
	/* prefisso di una singola riga di testo contenente il numero di CFU */
	private static final String CFU_MARKER = "CFU:";
	/* prefisso di una singola riga di testo contenente il peso della borsa */
	private static final String PESO_BORSA_MARKER = "Peso borsa:";
	/* prefisso di una singola riga di testo contenente il messaggio di benvenuto */
	private static final String MESSAGGIO_BENVENUTO_MARKER = "Messaggio di benvenuto:";
	/* prefisso di una singola riga di testo contenente il numero massimo di direzioni per le stanze */
	private static final String NUMERO_MASSIMO_DIREZIONI_MARKER = "Numero massimo direzioni:";
	/* prefisso di una singola riga di testo contenente il numero massimo di attrezzi per le stanze */
	private static final String NUMERO_MASSIMO_ATTREZZI_MARKER = "Numero massimo attrezzi";
	/* prefisso di una singola riga di testo contenente l'attrezzo sbloccante delle stanze bloccate */
	private static final String ATTREZZO_SBLOCCANTE_MARKER = "Attrezzo sbloccante:";
	/* prefisso di una singola riga di testo contenente l'attrezzo per fare luce nelle stanze buie */
	private static final String ATTREZZO_CHE_FA_LUCE_MARKER = "Attrezzo che fa luce:";
	/* prefisso di una singola riga di testo contenente la soglia magica delle stanze magiche */
	private static final String SOGLIA_MAGICA_MARKER = "Soglia magica:";
	
	private LineNumberReader reader;
	
	public CaricatoreProperties(String nomeFile) throws FileNotFoundException{
		this.reader = new LineNumberReader(new FileReader(nomeFile));
	}
	
	public void carica()throws FormatoFileNonValidoException {
		try {
			this.getCFU();
			this.getPesoBorsa();
			
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
	
	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}
	
	public int getCFU() throws FormatoFileNonValidoException{
		String SpecificaCFU=leggiRigaCheCominciaPer(CFU_MARKER);
		int CFU = Integer.parseInt(SpecificaCFU);
		return CFU;
	}

	public int getPesoBorsa() throws FormatoFileNonValidoException{
		String SpecificaPesoBorsa=leggiRigaCheCominciaPer(PESO_BORSA_MARKER);
		int PesoBorsa = Integer.parseInt(SpecificaPesoBorsa);
		return PesoBorsa;
	}
	
	public String getMessaggioBenvenuto() throws FormatoFileNonValidoException{
		String MessaggioBenvenuto=leggiRigaCheCominciaPer(MESSAGGIO_BENVENUTO_MARKER);
		return MessaggioBenvenuto;
	}
	
	public int getNumeroMassimoDirezioni() throws FormatoFileNonValidoException{
		String SpecificaDirezioni=leggiRigaCheCominciaPer(NUMERO_MASSIMO_DIREZIONI_MARKER);
		int direzioniMax = Integer.parseInt(SpecificaDirezioni);
		return direzioniMax;
	}
	
	public int getNumeroMassimoAttrezzi() throws FormatoFileNonValidoException{
		String SpecificaMaxAttrezzi=leggiRigaCheCominciaPer(NUMERO_MASSIMO_ATTREZZI_MARKER);
		int attrezziMax = Integer.parseInt(SpecificaMaxAttrezzi);
		return attrezziMax;
	}
	
	public String getAttrezzoSbloccante() throws FormatoFileNonValidoException{
		String SpecificaAttrezzoSbloccante=leggiRigaCheCominciaPer(ATTREZZO_SBLOCCANTE_MARKER);
		return SpecificaAttrezzoSbloccante;
	}
	
	public String getAttrezzoLuminoso() throws FormatoFileNonValidoException{
		String SpecificaAttrezzoluminoso=leggiRigaCheCominciaPer(ATTREZZO_CHE_FA_LUCE_MARKER);
		return SpecificaAttrezzoluminoso;
	}
	
	public int getSogliaMagica() throws FormatoFileNonValidoException{
		String SpecificaSogliaMagica=leggiRigaCheCominciaPer(SOGLIA_MAGICA_MARKER);
		int SogliaMagica = Integer.parseInt(SpecificaSogliaMagica);
		return SogliaMagica;
	}
}
