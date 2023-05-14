package it.uniroma3.diadia.ambienti;


/** classe che modella una stanza con un comportamento specifico
 * se non è presente in essa un determinato oggetto che sblocca
 * la direzione bloccata la descrizione della stanza è diversa 
 * @author EFFE
 * @see Stanza
 * @see Attrezzo
 * @version homework2
 * 
 * */
public class StanzaBloccata extends Stanza {
	private static final String ATTREZZO_SBLOCCANTE = "chiave";

	private String direzioneBloccata;
	private String attrezzoSbloccante;
	
	
	/**
	 * costruisce una stanza bloccata a partire dal costruttore primario
	 * impostando l' attrezzo che sblocca la direzione come chiave
	 * @param String nome della stanza
	 * @param String direzione da impostare bloccata
	 * */
	public StanzaBloccata(String nome, String direzione) {
		this(nome, direzione, ATTREZZO_SBLOCCANTE);
		
	}
	
	/**
	 * costruisce una stanza bloccata servendosi del costruttore
	 * del supertipo per il nome stanza ed impostando la direzione bloccata
	 * e il nome dell' attrezzo che la sblocca
	 * @param String nome della stanza
	 * @param String direzione che verrà bloccata
	 * @param String nome dell' attrezzo sbloccante
	 * */
	public StanzaBloccata(String nome, String direzione, String nomeAttrezzo) {
		super(nome);
		this.direzioneBloccata = direzione;
		this.attrezzoSbloccante = nomeAttrezzo;
		
	}
	
	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * se essa non è nulla o quella bloccata 
	 * @param direzione
	 * @return la stanza corrente se direzione nulla o bloccata altrimenti quella adiacente 
	 */
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if(direzione==null)
			return this;
		if(direzione.equals(this.direzioneBloccata)) {
			if(this.hasAttrezzo(this.attrezzoSbloccante)) {
				return super.getStanzaAdiacente(direzione);
			}else
				return this;
		}
		
		return super.getStanzaAdiacente(direzione);
	}
	
	/**
	 * Restituisce la descrizione della stanza.
	 * Se nella stanza è presente l'attrezzo sbloccante stampa la descrizione
	 * altrimenti stampa l' avviso che la stanza è bloccata
	 * @return la descrizione della stanza
	 */
	@Override
	public String getDescrizione() {
		if(this.hasAttrezzo(this.attrezzoSbloccante))
			return this.toString();
		else
			return "la porta a " + this.direzioneBloccata + " è bloccata\n"
					+ "ti serve l' oggetto " + this.attrezzoSbloccante + " nella stanza per aprirla...";
	}
	

}
