package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.*;

class BorsaTest {
	private Attrezzo attrezzo;
	private Attrezzo attrezzoNull;
	private Attrezzo attrezzoTroppoPesante;
	private Borsa borsa;
	private Borsa borsaDieci;
	
	@BeforeEach
	void setUp(){
		borsa=new Borsa(1);
		borsaDieci=new Borsa();
		attrezzo=new Attrezzo("attrezzo",1);
		attrezzoNull=null;
		attrezzoTroppoPesante=new Attrezzo("pesante",2);
	}

	@Test
	void testAddAttrezzoConAttrezzoNonNull() {
		assertEquals(true,this.borsa.addAttrezzo(attrezzo));
	}

	@Test
	void testAddAttrezzoNull() {
		assertEquals(false,this.borsa.addAttrezzo(attrezzoNull));
	}

	@Test
	void testAddAttrezzoConAttrezzoTroppoPesante() {
		assertEquals(false,this.borsa.addAttrezzo(attrezzoTroppoPesante));
	}

	@Test
	void testAddAttrezzoConTroppiAttrezzi() {
			this.borsa.addAttrezzo(attrezzo);
		assertEquals(false,this.borsa.addAttrezzo(attrezzo));
	}
	
	@Test
	void testIsEmptyFunzionaSeHoUnAttrezzoInBorsaELoRimuovo(){
		this.borsa.addAttrezzo(attrezzo);
		assertEquals(attrezzo,this.borsa.removeAttrezzo("attrezzo"));
		assertEquals(true,this.borsa.isEmpty());
	}
	
	@Test
	void testIsEmptyFallisceSeHoUnAttrezzoInBorsa() {
        this.borsa.addAttrezzo(attrezzo);
        assertFalse(this.borsa.isEmpty());
    }
	
	@Test
    void testGetPesoMaxBorsaAppenaCreataTornaDieci() {
        assertEquals(10, this.borsaDieci.getPesoMax());
    }

    @Test
    void testGetPesoBorsaAppenaCreataTornaZero() {
        assertEquals(0, this.borsa.getPeso());
    }

    @Test
    void testGetPesoDellaBorsaQuandoAggiungoUnAttrezzoDiPesoNoto() {
        this.attrezzo = new Attrezzo("attrezzo",1);
        this.borsa.addAttrezzo(attrezzo);
        assertEquals(1, this.borsa.getPeso());
    }
    
    @Test
    void testHatAttrezzoSuBorsaConUnAttrezzo() {
    	this.borsa.addAttrezzo(attrezzo);
    	assertEquals(true,this.borsa.hasAttrezzo("attrezzo"));
    }
    
    @Test
    void testHasAttrezzoSuBorsaConPiuDiUnAttrezzo() {
    	this.borsaDieci.addAttrezzo(attrezzo);
    	this.borsaDieci.addAttrezzo(attrezzoTroppoPesante);
    	assertEquals(true,this.borsaDieci.hasAttrezzo("pesante"));
    }
    
    @Test
    void testHasAttrezzoSuBorsaVuota() {
    	assertEquals(false,this.borsa.hasAttrezzo("attrezzo"));
    }
    
    @Test
    void testGetAttrezzoSuBorsaVuotaConAttrezzoNonNull() {
    	assertEquals(null,this.borsa.getAttrezzo("attrezzo"));
    }
    
    @Test
    void testGetAttrezzoSuBorsaContenenteAttrezzo() {
    	this.borsa.addAttrezzo(attrezzo);
    	assertEquals(attrezzo,this.borsa.getAttrezzo("attrezzo"));
    }
    
    @Test
    void testGetAttrezzoNullSuBorsaVuota() {
    	assertEquals(null,this.borsa.getAttrezzo(null));
    }
    
    @Test
    void testRemoveAttrezzoSuBorsaVuotaTornaNull() {
        assertNull(this.borsa.removeAttrezzo("attrezzo"));
    }

    @Test
    void testRemoveAttrezzoSuBorsaContenenteUnAttrezzo() {
        this.borsa.addAttrezzo(attrezzo);
        assertEquals(attrezzo, this.borsa.removeAttrezzo("attrezzo"));
    }

    @Test
    void testRemoveAttrezzoSuAttrezzoGiaRimossoTornaNull() {
        this.borsa.addAttrezzo(attrezzo);
        this.borsa.removeAttrezzo("attrezzo");
        assertNull(this.borsa.removeAttrezzo("attrezzo"));
    }
    
    @Test
    void testGetContenutoOrdinatoPerPeso() {
    	Attrezzo a1 = new Attrezzo("osso", 1);
    	Attrezzo a2 = new Attrezzo("arco", 2);
    	Attrezzo a3 = new Attrezzo("martello", 7);
    	this.borsaDieci.addAttrezzo(a3);
    	this.borsaDieci.addAttrezzo(a2);
    	this.borsaDieci.addAttrezzo(a1);
    	
    	List<Attrezzo> contenuto = this.borsaDieci.getContenutoOrdinatoPerPeso();
    	    	
    	assertEquals(a1, contenuto.get(0));
    	assertEquals(a2, contenuto.get(1));
    	assertEquals(a3, contenuto.get(2));
    }
    
    @Test
    void testGetContenutoOrdinatoPerNome() {
    	Attrezzo a1 = new Attrezzo("osso", 1);
    	Attrezzo a2 = new Attrezzo("arco", 2);
    	Attrezzo a3 = new Attrezzo("martello", 7);
    	this.borsaDieci.addAttrezzo(a3);
    	this.borsaDieci.addAttrezzo(a2);
    	this.borsaDieci.addAttrezzo(a1);
    	
    	Set<Attrezzo> contenuto = this.borsaDieci.getContenutoOrdinatoPerNome();
    	    	
    	Iterator<Attrezzo> iter = contenuto.iterator();
    	
    	assertEquals(a2, iter.next());
    	assertEquals(a3, iter.next());
    	assertEquals(a1, iter.next());

    	
    }
    
    @Test
    void testGetContenutoRaggruppatoPerPeso() {
    	Attrezzo a1 = new Attrezzo("osso", 1);
    	Attrezzo a2 = new Attrezzo("arco", 2);
    	Attrezzo a3 = new Attrezzo("orologio", 1);
    	this.borsaDieci.addAttrezzo(a3);
    	this.borsaDieci.addAttrezzo(a2);
    	this.borsaDieci.addAttrezzo(a1);
    	
    	Map<Integer, Set<Attrezzo>> mappa = this.borsaDieci.getContenutoRaggruppatoPerPeso();
    	
    	Set<Attrezzo> contenuto = mappa.get(1);
    	
    	Iterator<Attrezzo> iter = contenuto.iterator();
    	
    	assertEquals(a1, iter.next());
    	assertEquals(a3, iter.next());
    	
    }
    
    @Test
    void testGetSortedSetOrdinatoPerPeso() {
    	Attrezzo a1 = new Attrezzo("osso", 1);
    	Attrezzo a2 = new Attrezzo("arco", 2);
    	Attrezzo a3 = new Attrezzo("martello", 7);
    	this.borsaDieci.addAttrezzo(a3);
    	this.borsaDieci.addAttrezzo(a2);
    	this.borsaDieci.addAttrezzo(a1);
    	
    	Set<Attrezzo> contenuto = this.borsaDieci.getSortedSetOrdinatoPerPeso();
    	    	
    	Iterator<Attrezzo> iter = contenuto.iterator();
    	
    	assertEquals(a1, iter.next());
    	assertEquals(a2, iter.next());
    	assertEquals(a3, iter.next());
    }

}