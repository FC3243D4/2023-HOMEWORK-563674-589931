package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzioni;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {
	

	public Strega(String nome, String presentaz) {
		super(nome, presentaz);
	}
	
	

	@Override
	public String agisci(Partita partita) {
		
		Stanza destinazione = partita.getStanzaCorrente().getStanzaAdiacente(Direzioni.nord);
		
		while(destinazione==null) {
			for(Direzioni direzione : partita.getStanzaCorrente().getMapStanzeAdiacenti().keySet())
				destinazione = partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		}
		
		if(this.getGiocatoreHaSalutato()) {
			int numeroAttrezziMax = destinazione.getNumeroAttrezzi();
			for(Stanza altraStanza : partita.getStanzaCorrente().getMapStanzeAdiacenti().values()) {
				if(altraStanza.getNumeroAttrezzi() >= numeroAttrezziMax) {
					numeroAttrezziMax=altraStanza.getNumeroAttrezzi();
					destinazione = altraStanza;
				}
			}
		}else {
			int numeroAttrezziMin = destinazione.getNumeroAttrezzi();
			for(Stanza altraStanza : partita.getStanzaCorrente().getMapStanzeAdiacenti().values()) {
				if(altraStanza.getNumeroAttrezzi() <= numeroAttrezziMin) {
					numeroAttrezziMin=altraStanza.getNumeroAttrezzi();
					destinazione = altraStanza;
				}
			}
		}

		partita.setStanzaCorrente(destinazione);
		String msg = "la strega ti ha spostato...";
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo regalo, Partita partita) {
		String msg = "ih-ih-ih-ih-ih!!!";
		
		return msg;
	}

}