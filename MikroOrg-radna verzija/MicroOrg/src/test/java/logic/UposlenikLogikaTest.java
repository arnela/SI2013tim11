package logic;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import viewModels.KreditniSluzbenik;
import junit.framework.Assert;
import junit.framework.TestCase;

public class UposlenikLogikaTest extends TestCase {

	public void testDaLiPostoji() {
		
		UposlenikLogika _uposlenikLogika =  new UposlenikLogika();
		assertEquals((Boolean)false,(Boolean) _uposlenikLogika.daLiPostoji("42342345364564767767888990"));
	}

	public void testDodajUposlenika() {
		UposlenikLogika _uposlenikLogika =  new UposlenikLogika();
		
		//parsiranje datuma
		SimpleDateFormat _sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date _date = null;
		try {
			_date = _sdf1.parse("07-09-1992");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		_uposlenikLogika.dodajUposlenika(new KreditniSluzbenik(
				"Mirza Šehović",
				"111222333444",
				new java.sql.Date(_date.getTime()),
				"null",
				"061-111-222",
				"Neka Adresa 1337",
				"mail@mail.mail",
				"magicniPassword",
				"Sarajevo",
				new Double(1337)
				));
		//ako se ovaj test pozove 2 puta za redom doci ce do neprirodne situacije u bazi a to je da se nalaze 2 korisnika
		// sa istim jmbg-om sto ce izazvati pad testa ( ovaj slucaj je inace sprijecen validacijom ulaznih podataka )
		assertEquals((Boolean)true,(Boolean) _uposlenikLogika.daLiPostoji("111222333444"));
	}
	public void testGetByNameTest(){
		UposlenikLogika _uposlenikLogika= new UposlenikLogika();
				//parsiranje datuma
				SimpleDateFormat _sdf1 = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date _date = null;
				try {
					_date = _sdf1.parse("07-09-1992");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		_uposlenikLogika.dodajUposlenika(new KreditniSluzbenik(
				"Sakib",
				"4234243223535",
				new java.sql.Date(_date.getTime()),
				"null",
				"061-113-322",
				"Neka Adresa 1337",
				"mail@mail.mail",
				"magicniPassword",
				"Sarajevo",
				new Double(1337)
				));
		List<KreditniSluzbenik> _kreditniSluzbenici=_uposlenikLogika.getByName("Sakib");
		assertEquals(1,_kreditniSluzbenici.size());
	}
	public void testSoftDeleteByJMBGTest(){
		UposlenikLogika _uposlenikLogika= new UposlenikLogika();
		//parsiranje datuma
			SimpleDateFormat _sdf1 = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date _date = null;
			try {
				_date = _sdf1.parse("07-09-1992");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  try{ _uposlenikLogika.dodajUposlenika(new KreditniSluzbenik(
			"Ćazim",
			"9876543212125",
			new java.sql.Date(_date.getTime()),
			"null",
			"061-113-322",
			"Neka Adresa 1337",
			"mail@mail.mail",
			"magicniPassword",
			"Sarajevo",
			new Double(1337)
			));
	   _uposlenikLogika.softDeleteByJMBG("9876543212125");
	   assertEquals((Boolean)false,(Boolean)_uposlenikLogika.getOsobaByJMBG("9876543212125").getAktivan());
	  }catch(Exception e){}
	  }
	public void testGetOsobaByJMBG(){
		UposlenikLogika _uposlenikLogika= new UposlenikLogika();
		//parsiranje datuma
			SimpleDateFormat _sdf1 = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date _date = null;
			try {
				_date = _sdf1.parse("07-09-1992");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   _uposlenikLogika.dodajUposlenika(new KreditniSluzbenik(
			"Mustafa",
			"88946546814",
			new java.sql.Date(_date.getTime()),
			"null",
			"061-113-322",
			"Neka Adresa 1337",
			"mail@mail.mail",
			"magicniPassword",
			"Sarajevo",
			new Double(1337)
			));
	   assertEquals("Mustafa",_uposlenikLogika.getOsobaByJMBG("88946546814").getImePrezime());
	}
	public void testPromijeniUposlenika(){
		UposlenikLogika _uposlenikLogika= new UposlenikLogika();
		//parsiranje datuma
			SimpleDateFormat _sdf1 = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date _date = null;
			try {
				_date = _sdf1.parse("07-09-1992");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			KreditniSluzbenik _kreditniSluzbenik= new KreditniSluzbenik(
					"Ramiz",
					"2354253454465",
					new java.sql.Date(_date.getTime()),
					"null",
					"061-113-322",
					"Neka Adresa 1337",
					"mail@mail.mail",
					"magicniPassword",
					"Sarajevo",
					new Double(1337)
					);
	   _uposlenikLogika.dodajUposlenika(_kreditniSluzbenik);
	   _kreditniSluzbenik.setImePrezime("Fikret");
	   _kreditniSluzbenik.setTelefon("061-133 371");
	   _uposlenikLogika.promijeniUposlenika(_kreditniSluzbenik);
	   assertEquals("Fikret",_uposlenikLogika.getOsobaByJMBG("2354253454465").getImePrezime());
	}

}
