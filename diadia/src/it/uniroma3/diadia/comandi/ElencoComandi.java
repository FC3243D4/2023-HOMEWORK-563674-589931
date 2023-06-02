package it.uniroma3.diadia.comandi;

public enum ElencoComandi {
	aiuto,fine,guarda,interagisci,posa,prendi,regala,saluta,vai;
	private static ElencoComandi[] allValues = values();
	public static ElencoComandi fromOrdinal(int n) {return allValues[n];}
}
