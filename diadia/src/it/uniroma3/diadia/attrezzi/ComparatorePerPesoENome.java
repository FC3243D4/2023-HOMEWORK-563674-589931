package it.uniroma3.diadia.attrezzi;
import java.util.Comparator;

public class ComparatorePerPesoENome implements Comparator<Attrezzo>{
	@Override
	public int compare(Attrezzo a1, Attrezzo a2) {
		if(a1.getPeso()!=a2.getPeso()) return a1.getPeso()-a2.getPeso();
		int l1=a1.getNome().length();
		int l2=a2.getNome().length();
		int l=l1;
		if(l<l2) l=l2;
		for(int i=0; i<l;l++) {
			char ch1=a1.getNome().charAt(i);
			char ch2=a2.getNome().charAt(i);
			if(ch1>ch2) return 1;
			if(ch1<ch2) return -1;
		}
		if(l1<l2) return -1;
		if(l1>l2) return 1;
		return 0;
	}
}
