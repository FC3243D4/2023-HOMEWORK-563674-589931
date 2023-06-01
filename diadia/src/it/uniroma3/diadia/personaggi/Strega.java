package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {
	

	public Strega(String nome, String presentaz) {
		super(nome, presentaz);
	}
	
	

	@Override
	public String agisci(Partita partita) {
		
		Stanza destinazione = partita.getStanzaCorrente().getStanzaAdiacente("nord");
		
		while(destinazione==null) {
			for(String direzione : partita.getStanzaCorrente().getDirezioni())
				destinazione = partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		}
		
		if(this.getGiocatoreHaSalutato()) {
			//System.out.println(destinazione);
			int numeroAttrezziMax = destinazione.getNumeroAttrezzi();
			for(Stanza altraStanza : partita.getStanzaCorrente().getMapStanzeAdiacenti().values()) {
				System.out.println(numeroAttrezziMax);
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