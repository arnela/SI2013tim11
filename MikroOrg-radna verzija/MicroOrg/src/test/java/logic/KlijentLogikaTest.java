package logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import viewModels.KlijentSluzbenik;
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
			"Enver",
			"1112223334440",
			new java.sql.Date(_date.getTime()),
			"061-111-333",
			"Neka Adresa 133",
			"mail@mail.mail",
			null
			));
	List<KlijentSluzbenik> _klijentSluzbenici=_klijentLogika.getByName("Enver");
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

public void testGetByDateKlijentTest(){
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
			"Azra",
			"1112223334566",
			new java.sql.Date(_date.getTime()),
			"061-111-333",
			"aaaaaa",
			"arnela@mail.mail",
			null
			));
	List<KlijentSluzbenik> _klijentSluzbenici=_klijentLogika.getByDate(new java.sql.Date(_date.getTime()));
	assertEquals(1,_klijentSluzbenici.size());
}
}
