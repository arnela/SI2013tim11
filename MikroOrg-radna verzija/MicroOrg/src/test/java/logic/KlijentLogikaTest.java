package logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import viewModels.KlijentSluzbenik;
import viewModels.KreditniSluzbenik;
import junit.framework.TestCase;

public class KlijentLogikaTest extends TestCase {
	
public void testDaLiPostojiKlijent() {
		
		KlijentLogika _klijentLogika =  new KlijentLogika();
		assertEquals((Boolean)false,(Boolean) _klijentLogika.daLiPostoji("12034567891234"));
		}

public void testDodajKlijenta() {
	KlijentLogika _klijentLogika =  new KlijentLogika();
	//parsiranje datuma
	SimpleDateFormat _sdf1 = new SimpleDateFormat("dd-MM-yyyy");
	java.util.Date _date = null;
	try {
		_date = _sdf1.parse("07-09-1992");
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	_klijentLogika.dodajKlijenta(new KlijentSluzbenik(
			"Ajla Turković",
			"111222333444",
			new java.sql.Date(_date.getTime()),
			"061-111-222",
			"Neka Adresa 1337",
			"mail@mail.mail",
			null
			));
	//ako se ovaj test pozove 2 puta za redom doci ce do neprirodne situacije u bazi a to je da se nalaze 2 korisnika
	// sa istim jmbg-om sto ce izazvati pad testa ( ovaj slucaj je inace sprijecen validacijom ulaznih podataka )
	assertEquals((Boolean)true,(Boolean) _klijentLogika.daLiPostoji("111222333444"));
} 

public void testGetByNameKlijentTest(){
	KlijentLogika _klijentLogika= new KlijentLogika();
			//parsiranje datuma
			SimpleDateFormat _sdf1 = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date _date = null;
			try {
				_date = _sdf1.parse("07-09-1992");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	_klijentLogika.dodajKlijenta(new KlijentSluzbenik(
			"Envera",
			"1112223334498",
			new java.sql.Date(_date.getTime()),
			"061-111-333",
			"Neka Adresa 133",
			"mail@mail.mail",
			null
			));
	List<KlijentSluzbenik> _klijentSluzbenici=_klijentLogika.getByName("Envera");
	assertEquals(1,_klijentSluzbenici.size());
}

public void testGetByEmailKlijentTest(){
	KlijentLogika _klijentLogika= new KlijentLogika();
			//parsiranje datuma
			SimpleDateFormat _sdf1 = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date _date = null;
			try {
				_date = _sdf1.parse("07-09-1992");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	_klijentLogika.dodajKlijenta(new KlijentSluzbenik(
			"Arnela",
			"001112223334442",
			new java.sql.Date(_date.getTime()),
			"061-111-333",
			"Neka Adresa 1",
			"aaaa@mail.mail",
			null
			));
	List<KlijentSluzbenik> _klijentSluzbenici=_klijentLogika.getByEmail("aaaa@mail.mail");
	assertEquals(1,_klijentSluzbenici.size());
}

public void testGetByAdressKlijentTest(){
	KlijentLogika _klijentLogika= new KlijentLogika();
			//parsiranje datuma
			SimpleDateFormat _sdf1 = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date _date = null;
			try {
				_date = _sdf1.parse("07-09-1992");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	_klijentLogika.dodajKlijenta(new KlijentSluzbenik(
			"Elvis",
			"01112223334442",
			new java.sql.Date(_date.getTime()),
			"061-111-333",
			"adresaa",
			"arnela@mail.mail",
			null
			));
	List<KlijentSluzbenik> _klijentSluzbenici=_klijentLogika.getByAddress("adresaa");
	assertEquals(1,_klijentSluzbenici.size());
}


	public void testisNumeric(){
		KlijentLogika _klijentLogika= new KlijentLogika();
		String s="1768";
		assertEquals((Boolean)true,(Boolean) _klijentLogika.isNumeric(s));
	}
	public void testPromijeniKlijenta(){
		KlijentLogika _klijentLogika= new KlijentLogika();
		//parsiranje datuma
			SimpleDateFormat _sdf1 = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date _date = null;
			try {
				_date = _sdf1.parse("01-09-1991");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			KlijentSluzbenik _klijentSluzbenik= new KlijentSluzbenik(
					"Jaa",
					"1234567891011",
					new java.sql.Date(_date.getTime()),
					"061-111-333",
					"aaaaaa",
					"arnela@mail.mail",
					null
					);
	   _klijentLogika.dodajKlijenta(_klijentSluzbenik);
	   _klijentSluzbenik.setImePrezime("Ena");
	   _klijentSluzbenik.setTelefon("061-133 371");
	   _klijentLogika.promijeniKlijenta(_klijentSluzbenik);
	   assertEquals("Ena",_klijentLogika.getOsobaByJMBG("1234567891011").getImePrezime());
	}
	public void testSoftDeleteByJMBGTest(){
		KlijentLogika _klijentLogika= new KlijentLogika();
		//parsiranje datuma
			SimpleDateFormat _sdf1 = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date _date = null;
			try {
				_date = _sdf1.parse("07-09-1992");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   _klijentLogika.dodajKlijenta(new KlijentSluzbenik(
			   "Hanifa",
				"9876543212125",
				new java.sql.Date(_date.getTime()),
				"061-111-333",
				"aaaaaa",
				"arnela@mail.mail",
				null
			));
	   _klijentLogika.softDeleteByJMBG("9876543212125");
	   assertEquals((Boolean)false,(Boolean)_klijentLogika.getOsobaByJMBG("9876543212125").getAktivan());
	}
	public void testGetOsobaByJMBG(){
		KlijentLogika _klijentLogika= new KlijentLogika();
		//parsiranje datuma
			SimpleDateFormat _sdf1 = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date _date = null;
			try {
				_date = _sdf1.parse("07-09-1992");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   _klijentLogika.dodajKlijenta(new KlijentSluzbenik(
			   "Merima",
				"5432112345999",
				new java.sql.Date(_date.getTime()),
				"061-111-333",
				"aaaaaa",
				"arnela@mail.mail",
				null
			));
	   assertEquals("Merima",_klijentLogika.getOsobaByJMBG("5432112345999").getImePrezime());
	}
	public void testValidirajPodatke(){
		KlijentLogika _klijentLogika= new KlijentLogika();
		String ime="Ajla";
		String prezime="Turkovic";
		String jmbg="1110990193053";
		String datum="11-10-1990";
		String telefon="111-111-111";
		String adresa="Sarajevo";
		String email="asd@sda.com";
		String status="aktivan";
		assertEquals("OK", _klijentLogika.validirajPodatke(ime, prezime, jmbg,
				datum, telefon, adresa, email, status));
	}
	public void testValidirajPodatke2(){
		KlijentLogika _klijentLogika= new KlijentLogika();
		String ime="Ajla";
		String prezime="Turkovic";
		String datum="11-10-1990";
		String telefon="111-111-111";
		String adresa="Sarajevo";
		String email="asd@sdaf.com";
		String status="aktivan";
		assertEquals("OK", _klijentLogika.validirajPodatke2(ime, prezime,
				datum, telefon, adresa, email, status));
	}
	
}
