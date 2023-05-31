package it.uniroma3.diadia.comandi;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoGuarda extends AbstractComando{
	private static final String NOME_COMANDO = "comandoGuarda";


	public ComandoGuarda() {
		super(NOME_COMANDO);
	}
	
	/**
	 * stampa le informazioni sulla stanza corrente e
	 * sullo stato della partita
	 * */
	@Override
	public void esegui(Partita partita) {
		this.getIO().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		this.getIO().mostraMessaggio("cfu correnti: " + partita.getGiocatore().getCfu());
		this.getIO().mostraMessaggio("gli attrezzi nella borsa sono:");
		Map<Integer,Set<Attrezzo>> mappaBorsa = partita.getGiocatore().getBorsa().getContenutoRaggruppatoPerPeso();
		Set<Integer> pesi = mappaBorsa.keySet();
		Iterator<Integer> iteratore = pesi.iterator();
		while(iteratore.hasNext()) {
			int key = iteratore.next();
			StringBuilder s = new StringBuilder();
			s.append("( "+key+", { ");
			Set<Attrezzo> attrezziPesoKey = mappaBorsa.get(key);
			Iterator<Attrezzo> iteratoreAttrezzi = attrezziPesoKey.iterator();
			while (iteratoreAttrezzi.hasNext()) {s.append(iteratoreAttrezzi.next().getNome()+", ");}
			s.delete(s.length()-2, s.length()-1);
			s.append("} )");
			this.getIO().mostraMessaggio(s.toString());
		}
		
	}

}