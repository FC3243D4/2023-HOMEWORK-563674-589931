package it.uniroma3.diadia;

public class IOSimulator implements IO{
	private String[] comandiIniettati;
	private String[] comandiMostrati;
	private int countIN;
	private int countOUT;
	
	public IOSimulator() {
		comandiMostrati = new String[100];
		countIN=0;	
		countOUT=0;
	}
	
	public void setComandiIniettati(String[] comandi) {
		this.comandiIniettati=comandi;
	}
	
	public String[] getComandiMostrati() {
		return comandiMostrati;
	}
	
	public int getCountOUT() {
		return countOUT-1;
	}
	
	public String leggiRiga() {
		String comando=comandiIniettati[this.countIN];
		this.countIN++;
		return comando;
	}
	
	public void mostraMessaggio(String messaggio) {
		this.comandiMostrati[this.countOUT]=messaggio;
		this.countOUT++;
	}
}
