package it.uniroma3.diadia.ambienti;

public enum Direzioni {
	nord, est, ovest, sud;
	private static Direzioni[] allValues = values();
	public static Direzioni fromOrdinal(int n) {return allValues[n];}
	public static Direzioni fromName(String nome) {
		if(nome.equals("nord")) return nord;
		if(nome.equals("est")) return est;
		if(nome.equals("sud")) return sud;
		if(nome.equals("ovest")) return ovest;
		return null;
	}
}
