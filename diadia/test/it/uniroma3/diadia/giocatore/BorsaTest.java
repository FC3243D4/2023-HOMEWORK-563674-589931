package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

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

}