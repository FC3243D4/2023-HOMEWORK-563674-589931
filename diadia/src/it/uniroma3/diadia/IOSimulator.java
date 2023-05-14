package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class IOSimulator implements IO{
	private List<String> comandiIniettati;
	private List<String> comandiMostrati;
	private Iterator<String> countIN;
	private Iterator<String> countOUT;
	
	public void setComandiIniettati(List<String> comandi) {
		this.comandiIniettati=comandi;
	}
	
	public List<String> getComandiMostrati() {
		return comandiMostrati;
	}
	
	public Iterator<String> getCountOUT() {
		return countOUT;
	}
	
	@Override
	public String leggiRiga() {
		String riga = null;

		if(countIN.hasNext()) riga = countIN.next();
		return riga;
	}
	
	@Override
	public void mostraMessaggio(String messaggio) {
		this.comandiMostrati.add(messaggio);
	}
	
	public IOSimulator(List<String> righeDaLeggere) {
		this.comandiIniettati = righeDaLeggere;
		this.countIN = comandiIniettati.iterator();
		this.comandiMostrati = new ArrayList<String>();
		this.countOUT = comandiMostrati.iterator();
	}
}
