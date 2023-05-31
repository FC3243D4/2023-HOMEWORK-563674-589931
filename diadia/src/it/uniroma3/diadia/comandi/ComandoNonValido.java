package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/**
 * Permette di modellare un null object, un contenitore che gestisca 
 * implicitamente i casi null o non validi per evitare le verifiche sul null
 * @author EFFE
 * @see Comando
 * @version homework4
 * */
public class ComandoNonValido extends AbstractComando{
	private static final String NOME_COMANDO = "comandoNonValido";

	public ComandoNonValido() {
		super(NOME_COMANDO);
	}
	
	@Override
	public void esegui(Partita partita) {
		this.getIO().mostraMessaggio("Devi inserire un comando valido!");
	}

}