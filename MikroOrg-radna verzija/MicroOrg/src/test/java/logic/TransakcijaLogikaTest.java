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

	/*public void testDodajTransakciju(){
		
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
				"Adnan SIvro",
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
				 _osobaId,
				 "Sarajevo",
				 10000
				 );
		
		KreditnaPonuda _kredit = new KreditnaPonuda();
		
		
		_transakcijaLogika.dodajTransakciju(new Transakcija(
				"22-11-2013",
				111,
				"gotovina",
				_klijent,
				_kredit,
				_u
				));
		
		
	}*/
	
	public void testDaLiPostojiKlijent(){
		TransakcijaLogika _transakcijaLogika =  new TransakcijaLogika();
		assertEquals((Boolean)false,(Boolean) _transakcijaLogika.daLiPostoji("42342345364564767767888990"));		
	}
	public void testDajOsobu(){}
	public void testGetByDate(){}
	public void testGetByKlijent(){}
	public void testGetByID(){}
	public void testGetByTipKredita(){}
	
	
	

	
	
	
}
