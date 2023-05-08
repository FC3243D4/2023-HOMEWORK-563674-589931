package it.uniroma3.diadia.comandi;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoGuarda implements Comando {
	private static final String NOME_COMANDO = "comandoGuarda";
	private IO IO;
	
	public ComandoGuarda() {
		this.IO = new IOConsole();
	}

	
	/**
	 * stampa le informazioni sulla stanza corrente e
	 * sullo stato della partita
	 * */
	@Override
	public void esegui(Partita partita) {
		IO.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		IO.mostraMessaggio("cfu correnti: " + partita.getGiocatore().getCfu());
		IO.mostraMessaggio("gli attrezzi nella borsa sono:");
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
			IO.mostraMessaggio(s.toString());
		}
		
	}

	@Override
	public void setParametro(String parametro) {
	}

	@Override
	public String getNome() {
		return NOME_COMANDO;
	}

	@Override
	public String getParametro() {
		return null;
	}

}
