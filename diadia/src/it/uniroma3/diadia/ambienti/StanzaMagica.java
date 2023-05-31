package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.properties.*;

/**Una classe che modella una stanza che diventa "magica" 
 * una volta che si è posato un numero di attrezzi
 * oltre la soglia per renderla tale
 * @author EFFE
 * @see Stanza
 * @see Attrezzo
 * @version homework2
 * 
 * */

public class StanzaMagica extends Stanza {
	
	//final static private int SOGLIA_MAGICA_DEFAULT = 3;
	private int contatoreAttrezziPosati;
	private int sogliaMagica;

	/**crea una stanza magica a partire dal costruttore primario
	 * @param nome della stanza magica
	 * @throws FormatoFileNonValidoException 
	 * @throws FileNotFoundException 
	 * */
	public StanzaMagica(String nome) throws FileNotFoundException, FormatoFileNonValidoException {
		this(nome, new CaricatoreProperties("diadia.properties").getSogliaMagica());
	}
	
	/**crea una stanza magica
	 *@param nome della stanza da creare
	 *@param intero che indica soglia magica 
	 * @throws FormatoFileNonValidoException 
	 * @throws FileNotFoundException 
	 * */
	public StanzaMagica(String nome, int soglia) throws FileNotFoundException, FormatoFileNonValidoException {
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
	
	/**metodo che sovrascrive quello della superclasse Stanza.
	 * Se il numero di attrezzi posati è superiore alla soglia
	 * aggiunge l' attrezzo alla stanza magica modificandone il nome e il peso
	 * @param attrezzo da aggiungere alla stanza
	 * @return boolean che indica se l' attrezzo è stato aggiunto
	 * @throws FormatoFileNonValidoException 
	 * 
	 * */
	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) throws FormatoFileNonValidoException {
		if(attrezzo==null)
			return false;
		else {
			this.contatoreAttrezziPosati++;
			if (this.contatoreAttrezziPosati>this.sogliaMagica)
				attrezzo = this.modificaAttrezzo(attrezzo);
			return super.addAttrezzo(attrezzo);
		}
	}
	
}
