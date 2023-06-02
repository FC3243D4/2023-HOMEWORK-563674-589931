package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando{
	
	private static final String NOME_COMANDO = "comandoAiuto";

	
	public ComandoAiuto() {
		super(NOME_COMANDO);
	}
	
	/**
	 * Stampa informazioni di aiuto.
	 */
	@Override
	public void esegui(Partita partita) {
		for(ElencoComandi comando : ElencoComandi.values())
			this.getIO().mostraMessaggio(comando+" ");
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
