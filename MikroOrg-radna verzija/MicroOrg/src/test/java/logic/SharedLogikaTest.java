package logic;

import java.sql.Date;
import java.util.Calendar;
import java.text.ParseException;

import junit.framework.TestCase;

public class SharedLogikaTest extends TestCase{

	public void validirajJMBTest(){
		SharedLogika _sharedLogika =  new SharedLogika();
		
		Calendar cal = Calendar.getInstance();
		cal.set(1990, 10, 11);
		java.util.Date dt = cal.getTime();
		
		assertEquals(true, _sharedLogika.validirajJMB("1110990193053", dt));
	}
	
	public void validirajDatumTest(){
		SharedLogika _sharedLogika =  new SharedLogika();
		assertEquals(true, _sharedLogika.validirajDatum("11-10-1990"));
	}
	
	public void validirajEmailTest(){
		SharedLogika _sharedLogika =  new SharedLogika();
		assertEquals(true, _sharedLogika.validirajEmail("asd@sdaf.com"));
	}
	
	public void validirajImePrezimeTest(){
		SharedLogika _sharedLogika =  new SharedLogika();
		assertEquals(true, _sharedLogika.validirajDatum("Adnan Sivro"));
	}
	
	public void validirajIznosKreditaTest(){
		SharedLogika _sharedLogika =  new SharedLogika();
		assertEquals(true, _sharedLogika.validirajIznosKredita("10000"));
	}
	
	public void validirajKamatnuStopuTest(){
		SharedLogika _sharedLogika =  new SharedLogika();
		assertEquals(true, _sharedLogika.validirajKamatnuStopu("10"));
	}
	
	public void validirajImeTest(){
		SharedLogika _sharedLogika =  new SharedLogika();
		assertEquals(true, _sharedLogika.validirajIme("Adnan"));
	}
	
	public void validirajPrezimeTest(){
		SharedLogika _sharedLogika =  new SharedLogika();
		assertEquals(true, _sharedLogika.validirajPrezime("Sivro"));
	}
	
	public void validirajTelefonTest(){
		SharedLogika _sharedLogika =  new SharedLogika();
		assertEquals(true, _sharedLogika.validirajTelefon("111-111-111"));
	}
	
	
	
}
