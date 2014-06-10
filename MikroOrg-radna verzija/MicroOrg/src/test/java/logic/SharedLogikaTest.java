package logic;

import junit.framework.TestCase;

public class SharedLogikaTest extends TestCase {


	public void testValidirajDatum() {
		SharedLogika _sharedLogika =  new SharedLogika();
		assertEquals(true, _sharedLogika.validirajDatum("11-10-1990"));

	}

	public void testValidirajEmail() {
		SharedLogika _sharedLogika =  new SharedLogika();
		assertEquals(true, _sharedLogika.validirajEmail("asd@sdaf.com"));

	}

	public void testValidirajImePrezime() {
		SharedLogika _sharedLogika =  new SharedLogika();
		assertEquals(true, _sharedLogika.validirajImePrezime("Adnan Sivro"));

	}

	public void testValidirajIznosKredita() {
		SharedLogika _sharedLogika =  new SharedLogika();
		assertEquals(true, _sharedLogika.validirajIznosKredita("10000"));
	
	}

	public void testValidirajKamatnuStopu() {
		SharedLogika _sharedLogika =  new SharedLogika();
		assertEquals(true, _sharedLogika.validirajKamatnuStopu("10"));

	}

	public void testValidirajIme() {
		SharedLogika _sharedLogika =  new SharedLogika();
		assertEquals(true, _sharedLogika.validirajIme("Adnan"));

	}

	public void testValidirajPrezime() {
		SharedLogika _sharedLogika =  new SharedLogika();
		assertEquals(true, _sharedLogika.validirajPrezime("Sivro"));

	}

	public void testValidirajTelefon() {
		SharedLogika _sharedLogika =  new SharedLogika();
		assertEquals(true, _sharedLogika.validirajTelefon("111-111-111"));
	}



}
