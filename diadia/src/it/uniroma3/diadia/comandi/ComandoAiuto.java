package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando{
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa", "guarda"};
	private static final String NOME_COMANDO = "comandoAiuto";

	
	public ComandoAiuto() {
		super(NOME_COMANDO);
	}
	
	/**
	 * Stampa informazioni di aiuto.
	 */
	@Override
	public void esegui(Partita partita) {
		for(int i=0; i< elencoComandi.length; i++)
			this.getIO().mostraMessaggio(elencoComandi[i]+" ");
		this.getIO().mostraMessaggio("");
	}
	
	/**
	 * Stampa informazioni di aiuto.
	 */
//	@Override
//	public void esegui(Partita partita) {
//				
//		Class<?>[] interfaces = this.getClass().getInterfaces();
//		Class<?> i = interfaces[0];
//		
//		for(Class<?> c : i.get)
//			IO.mostraMessaggio(c.getSimpleName());
//		
//	}

}
