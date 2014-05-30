package logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import domainModels.Klijent;
import domainModels.TipKredita;
import domainModels.Uposlenik;
import viewModels.KlijentSluzbenik;
import viewModels.KreditnaPonuda;
import viewModels.KreditniSluzbenik;
import viewModels.TipKreditaSluzbenik;
import viewModels.Transakcija;
import junit.framework.TestCase;

public class TransakcijaLogikaTest extends TestCase{

	public void testDodajTransakciju(){
		TransakcijaLogika _transakcijaLogika = new TransakcijaLogika();
		SimpleDateFormat _sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date _date = null;
		try {
			_date = _sdf1.parse("07-09-1992");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		KlijentSluzbenik _klijent = new KlijentSluzbenik(
				"Adnan Sivro",
				"111222333444",
				new java.sql.Date(_date.getTime()),
				"061-111-222",
				"Neka Adresa 1337",
				"mail@mail.mail",
				null
				);
		
		TipKreditaSluzbenik _tipkredita = new TipKreditaSluzbenik(
				"PrviTip",
				"namjena",
				new Double(5000),
				"neki rok",
				new Double(20),
				"nemagarancije",
				"period",
				new Double(1000)
				);
		
		Uposlenik _u= new Uposlenik(
				 false,
				 "01011990123123",
				 "dsadasd",
				 0,
				 0,
				 Long.parseLong("0", 10),
				 "Sarajevo",
				 10000.00
				 );
		
		KreditnaPonuda _kredit = new KreditnaPonuda();
		
		
		_transakcijaLogika.dodajTransakciju(new Transakcija(
				"22-11-2013",
				111.00,
				"gotovina",
				_klijent,
				_kredit,
				_u
				));
		
		assertEquals((Boolean)true,(Boolean) _transakcijaLogika.daLiPostoji("PrviTip"));
	}
	
	public void testDaLiPostojiKlijent(){
		TransakcijaLogika _transakcijaLogika =  new TransakcijaLogika();
		assertEquals((Boolean)false,(Boolean) _transakcijaLogika.daLiPostoji("42342345364564767767888990"));		
	}
	public void testDajOsobu(){
		TransakcijaLogika _transakcijaLogika= new TransakcijaLogika();
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
	   assertEquals("Mustafa",_transakcijaLogika.dajOsobu("88946546814").getImePrezime());
	}
	
	public void testGetByDate(){
		
		TransakcijaLogika _transakcijaLogika = new TransakcijaLogika();
		SimpleDateFormat _sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date _date = null;
		try {
			_date = _sdf1.parse("07-09-1992");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		KlijentSluzbenik _klijent = new KlijentSluzbenik(
				"Adnan Sivro",
				"111222333444",
				new java.sql.Date(_date.getTime()),
				"061-111-222",
				"Neka Adresa 1337",
				"mail@mail.mail",
				null
				);
		
		TipKreditaSluzbenik _tipkredita = new TipKreditaSluzbenik(
				"PrviTip",
				"namjena",
				new Double(5000),
				"neki rok",
				new Double(20),
				"nemagarancije",
				"period",
				new Double(1000)
				);
		
		Uposlenik _u= new Uposlenik(
				 false,
				 "01011990123123",
				 "dsadasd",
				 0,
				 0,
				 Long.parseLong("0", 10),
				 "Sarajevo",
				 10000.00
				 );
		
		KreditnaPonuda _kredit = new KreditnaPonuda();
		
		
		_transakcijaLogika.dodajTransakciju(new Transakcija(
				"22-11-2013",
				111.00,
				"gotovina",
				_klijent,
				_kredit,
				_u
				));
		List<Transakcija> _transakcije=_transakcijaLogika.getByDate("22-11-2013");
		assertEquals(1,_transakcije.size());
		
	}
	
	public void testGetByKlijent(){
		
		TransakcijaLogika _transakcijaLogika = new TransakcijaLogika();
		SimpleDateFormat _sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date _date = null;
		try {
			_date = _sdf1.parse("07-09-1992");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		KlijentSluzbenik _klijent = new KlijentSluzbenik(
				"Adnan Sivro",
				"111222333444",
				new java.sql.Date(_date.getTime()),
				"061-111-222",
				"Neka Adresa 1337",
				"mail@mail.mail",
				null
				);
		
		TipKreditaSluzbenik _tipkredita = new TipKreditaSluzbenik(
				"PrviTip",
				"namjena",
				new Double(5000),
				"neki rok",
				new Double(20),
				"nemagarancije",
				"period",
				new Double(1000)
				);
		
		Uposlenik _u= new Uposlenik(
				 false,
				 "01011990123123",
				 "dsadasd",
				 0,
				 0,
				 Long.parseLong("0", 10),
				 "Sarajevo",
				 10000.00
				 );
		
		KreditnaPonuda _kredit = new KreditnaPonuda();
		
		
		_transakcijaLogika.dodajTransakciju(new Transakcija(
				"22-11-2013",
				111.00,
				"gotovina",
				_klijent,
				_kredit,
				_u
				));
		List<Transakcija> _transakcije=_transakcijaLogika.getByKlijent("Adnan Sivro");
		assertEquals(1,_transakcije.size());
		
	}
	public void testGetByID(){}
	public void testGetByTipKredita(){
		
		TransakcijaLogika _transakcijaLogika = new TransakcijaLogika();
		SimpleDateFormat _sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date _date = null;
		try {
			_date = _sdf1.parse("07-09-1992");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		KlijentSluzbenik _klijent = new KlijentSluzbenik(
				"Adnan Sivro",
				"111222333444",
				new java.sql.Date(_date.getTime()),
				"061-111-222",
				"Neka Adresa 1337",
				"mail@mail.mail",
				null
				);
		
		TipKreditaSluzbenik _tipkredita = new TipKreditaSluzbenik(
				"PrviTip",
				"namjena",
				new Double(5000),
				"neki rok",
				new Double(20),
				"nemagarancije",
				"period",
				new Double(1000)
				);
		
		Uposlenik _u= new Uposlenik(
				 false,
				 "01011990123123",
				 "dsadasd",
				 0,
				 0,
				 Long.parseLong("0", 10),
				 "Sarajevo",
				 10000.00
				 );
		
		KreditnaPonuda _kredit = new KreditnaPonuda();
		
		
		_transakcijaLogika.dodajTransakciju(new Transakcija(
				"22-11-2013",
				111.00,
				"gotovina",
				_klijent,
				_kredit,
				_u
				));
		List<Transakcija> _transakcije=_transakcijaLogika.getByTipKredita("PrviTip");
		assertEquals(1,_transakcije.size());
		
	}
	
		
}
