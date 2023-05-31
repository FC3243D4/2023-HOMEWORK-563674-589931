package it.uniroma3.diadia.properties;

import java.io.*;

import it.uniroma3.diadia.FormatoFileNonValidoException;

public class CaricatoreProperties {
	
	/* prefisso di una singola riga di testo contenente il numero di CFU */
	private static final String CFU_MARKER = "CFU:";
	/* prefisso di una singola riga di testo contenente il peso della borsa */
	private static final String PESO_BORSA_MARKER = "PesoMaxBorsa:";
	
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
}
