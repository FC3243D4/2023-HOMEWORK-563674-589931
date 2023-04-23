package it.uniroma3.diadia.ambienti;


/** classe che modella una stanza con un comportamento specifico
 * se non è presente in essa un determinato oggetto che fa luce
 * la descrizione della stanza è diversa 
 * @author EFFE
 * @see Stanza
 * @see Attrezzo
 * @version homework2
 * 
 * */
public class StanzaBuia extends Stanza {
	private static final String ATTREZZO_CHE_FA_LUCE_DEFAULT = "lanterna";
	private String AttrezzoCheFaLuce;
	
	
	/**
	 * costruisce una stanza buia a partire dal costruttore primario
	 * impostando l' attrezzo che fa luce come lanterna
	 * @param nome della stanza
	 * */
	public StanzaBuia(String nome) {
		this(nome, ATTREZZO_CHE_FA_LUCE_DEFAULT);
		
	}
	
	/**
	 * costruisce una stanza buia servendosi del costruttore
	 * del supertipo per il nome stanza ed impostando l' attrezzo che fa luce 
	 * @param String nome della stanza
	 * @param String nome dell' attrezzo che fa luce
	 * */
	public StanzaBuia(String nome, String nomeAttrezzo) {
		super(nome);
		this.AttrezzoCheFaLuce = nomeAttrezzo;
	}
	
	
	/**
	 * Restituisce la descrizione della stanza
	 * Se nella stanza è presente l'attrezzo che fa luce stampa la descrizione
	 * altrimenti stampa che non si riesce a vedere
	 * @return la descrizione della stanza
	 */
	@Override
	public String getDescrizione() {
		if(this.hasAttrezzo(this.AttrezzoCheFaLuce))
			return this.toString();
		else
			return "qui c'è buio pesto...";
	}

}
