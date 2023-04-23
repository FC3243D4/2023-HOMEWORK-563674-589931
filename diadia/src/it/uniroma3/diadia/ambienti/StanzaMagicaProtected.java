package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**Una classe che modella una stanza che diventa "magica" 
 * (versione alternativa con i campi protected)
 * una volta che si è posato un numero di attrezzi
 * oltre la soglia per renderla tale
 * @author EFFE
 * @see Stanza
 * @see Attrezzo
 * @version homework2
 * 
 * */

public class StanzaMagicaProtected extends StanzaProtected {
	final static protected int SOGLIA_MAGICA_DEFAULT = 3;

	protected int contatoreAttrezziPosati;
	protected int sogliaMagica;

	
	/**crea una stanza magica a partire dal costruttore primario
	 * @param nome della stanza magica
	 * */
	public StanzaMagicaProtected(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}
	
	/**crea una stanza magica
	 *@param nome della stanza da creare
	 *@param intero che indica soglia magica 
	 * */
	public StanzaMagicaProtected(String nome, int soglia) {
		super(nome);
		this.contatoreAttrezziPosati = 0;
		this.sogliaMagica = soglia;
	}
	
	/** metodo che modifica il nome dell' attrezzo passato invertendolo
	 * e ne raddoppia il peso
	 * @param attrezzo da modificare
	 * @return attrezzo modificato
	 * 
	 * */
	private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder nomeInvertito;
		int pesoX2 = attrezzo.getPeso() * 2;
		nomeInvertito = new StringBuilder(attrezzo.getNome());
		nomeInvertito = nomeInvertito.reverse();
		attrezzo = new Attrezzo(nomeInvertito.toString(),
				pesoX2);
		return attrezzo;
	}
	
	/**metodo che sovrascrive quello della superclasse StanzaProtected.
	 * Rappresenta una versione alternativa di quello della classe Stanza Magica, essendo
	 * i campi di stanza protected accessibili anche alle sottoclassi.
	 * Se il numero di attrezzi posati è superiore alla soglia
	 * aggiunge l' attrezzo alla stanza magica modificandone il nome e il peso
	 * @param attrezzo da aggiungere alla stanza
	 * @return boolean che indica se l' attrezzo è stato aggiunto
	 * 
	 * */
	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		this.contatoreAttrezziPosati++;
		if (this.contatoreAttrezziPosati > this.sogliaMagica)
			attrezzo = this.modificaAttrezzo(attrezzo);
		if (this.numeroAttrezzi<this.attrezzi.length) {
			this.attrezzi[this.numeroAttrezzi] = attrezzo;
			this.numeroAttrezzi++;
			return true;
		}
		else return false;
	}

}
