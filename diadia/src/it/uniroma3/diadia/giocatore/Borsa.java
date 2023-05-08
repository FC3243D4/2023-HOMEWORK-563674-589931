package it.uniroma3.diadia.giocatore;
import java.util.List;
import java.util.ListIterator;
import java.util.SortedSet;
import java.util.ArrayList;
//import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import it.uniroma3.diadia.attrezzi.*;


public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private List<Attrezzo> attrezzi;
	private int pesoMax;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new ArrayList<Attrezzo>();
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (attrezzo==null) return false;
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		//if (this.numeroAttrezzi==10)
		//return false;
		this.attrezzi.add(attrezzo);
		return true;
	}

	public int getPesoMax() {
		return pesoMax;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo cercato = null;
		Attrezzo b = null;
		Iterator<Attrezzo> iteratore = this.attrezzi.iterator();
		while(iteratore.hasNext()) {
			b = iteratore.next();
			if (b.getNome().equals(nomeAttrezzo)) cercato=b;
		}

		return cercato;
	}

	public int getPeso() {
		int peso = 0;
		Iterator<Attrezzo> iteratore = this.attrezzi.iterator();
		while (iteratore.hasNext()) {
			Attrezzo a = iteratore.next();
			peso += a.getPeso();
		}

		return peso;
	}

	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}

	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		Iterator<Attrezzo> iteratore = this.attrezzi.iterator();
		while(iteratore.hasNext()) {
			a = iteratore.next();
			if (a.getNome().equals(nomeAttrezzo)) {
				iteratore.remove();
				return a;
			}	
		}
		return null;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		Iterator<Attrezzo> iteratore = this.attrezzi.iterator();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			while(iteratore.hasNext()) {
				Attrezzo a = iteratore.next();
				s.append(a.toString()+" ");
			}

		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}

	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		List<Attrezzo> ordinata = new ArrayList<Attrezzo>(this.attrezzi);
		ComparatorePerPeso comparatore = new ComparatorePerPeso();
		Collections.sort(ordinata, comparatore);
		return ordinata;
	}

	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		SortedSet<Attrezzo> ordinato = new TreeSet<Attrezzo>(attrezzi);
		return ordinato;
	}

	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		int pesoAttrezzo=-1;
		Attrezzo a;
		List<Attrezzo> attrezziSet = new ArrayList<Attrezzo>();
		List<Attrezzo> ordinata = new ArrayList<Attrezzo>(this.attrezzi);
		ComparatorePerPeso comparatore = new ComparatorePerPeso();
		Collections.sort(ordinata, comparatore);//ordino la lista degli attrezzi per peso
		ListIterator<Attrezzo> iteratore = ordinata.listIterator();//creo l'iteratore per scorrere la lista
		Map<Integer,Set<Attrezzo>> mappa = new TreeMap<Integer,Set<Attrezzo>>();
		while(iteratore.hasNext()) {
			a=iteratore.next();
			if(a.getPeso()==pesoAttrezzo || pesoAttrezzo==-1) {
				pesoAttrezzo=a.getPeso();
				attrezziSet.add(a);
			} else {
				attrezziSet.removeAll(attrezzi);
				pesoAttrezzo=a.getPeso();
				attrezziSet.add(a);
			}
			SortedSet<Attrezzo> setOrdinatoPerNome = new TreeSet<Attrezzo>(attrezziSet);
			mappa.put(pesoAttrezzo, setOrdinatoPerNome);
		}
		return mappa;
	}

	SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		ComparatorePerPesoENome comparatore = new ComparatorePerPesoENome();
		SortedSet<Attrezzo> ordinato = new TreeSet<Attrezzo>(comparatore);
		ordinato.addAll(attrezzi);
		/*List<Attrezzo> listaOrdinata = new ArrayList<Attrezzo>(attrezzi);
		Collections.sort(listaOrdinata, comparatore);
		Iterator<Attrezzo> iteratore = listaOrdinata.iterator();
		while(iteratore.hasNext()) ordinato.add(iteratore.next());*/
		return ordinato;
	}

}