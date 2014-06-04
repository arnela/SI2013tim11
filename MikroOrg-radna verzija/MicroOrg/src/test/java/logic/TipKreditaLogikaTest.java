package logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import domainModels.TipKredita;
import viewModels.KlijentSluzbenik;
import viewModels.KreditniSluzbenik;
import viewModels.TipKreditaSluzbenik;
import junit.framework.TestCase;

public class TipKreditaLogikaTest extends TestCase {
	
public void testDaLiPostoji() {
		
		TipKreditaLogika _tipKreditaLogika =  new TipKreditaLogika();
		assertEquals((Boolean)false,(Boolean) _tipKreditaLogika.daLiPostoji("aaaaaa"));
	}

	public void testDodajTipKredita() {
		
		TipKreditaLogika _tipKreditaLogika =  new TipKreditaLogika();
		_tipKreditaLogika.dodajTipKredita(new TipKreditaSluzbenik(
				"PrviTip",
				"namjena",
				new Double(5000),
				"neki rok",
				new Double(20),
				"nemagarancije",
				"period",
				new Double(1000),
				"brava"
				));
		
		//ako se ovaj test pozove 2 puta za redom doci ce do neprirodne situacije u bazi a to je da se nalaze 2 tipaKredita
		// sa istim nazivom tipa sto ce izazvati pad testa ( ovaj slucaj je inace sprijecen validacijom ulaznih podataka )
				
		assertEquals((Boolean)true,(Boolean) _tipKreditaLogika.daLiPostoji("PrviTip"));
	}
	
	public void testGetAll() {
		
	TipKreditaLogika _tipKreditaLogika =  new TipKreditaLogika();
	List<TipKreditaSluzbenik> tipovi = _tipKreditaLogika.getAll();
	int duzina=tipovi.size();
	
	_tipKreditaLogika.dodajTipKredita(new TipKreditaSluzbenik(
			"NekiTip",
			"namjena",
			new Double(5000),
			"neki rok",
			new Double(20),
			"nemagarancije",
			"period",
			new Double(1000),
			"nadzor"
			));
	
	List<TipKreditaSluzbenik> _tipovi=_tipKreditaLogika.getAll();
	assertEquals((int)(duzina+1),_tipovi.size());
}
	public void testGetByNameTest(){
		TipKreditaLogika _tipKreditaLogika= new TipKreditaLogika();
				//parsiranje datuma
				SimpleDateFormat _sdf1 = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date _date = null;
				try {
					_date = _sdf1.parse("07-09-1992");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				_tipKreditaLogika.dodajTipKredita(new TipKreditaSluzbenik(
						"TipZ",
						"namjena",
						new Double(5000),
						"neki rok",
						new Double(20),
						"nemagarancije",
						"period",
						new Double(1000),
						"izjava"
						));
		List<TipKreditaSluzbenik> _tipovi=_tipKreditaLogika.getByName2("TipZ");
		assertEquals(1,_tipovi.size());
	}
	public void testDeleteByNameTest(){
		TipKreditaLogika _tipKreditaLogika =  new TipKreditaLogika();
		_tipKreditaLogika.dodajTipKredita(new TipKreditaSluzbenik(
				"AAAATip",
				"namjena",
				new Double(5000),
				"neki rok",
				new Double(20),
				"nemagarancije",
				"period",
				new Double(1000),
				"instrument"
				));
		_tipKreditaLogika.deleteByName("AAAATip");
		assertEquals((Boolean)false,(Boolean) _tipKreditaLogika.daLiPostoji("AAAATip"));	
	}
	
	public void testdajSveKredite() {
		
		TipKreditaLogika _tipKreditaLogika =  new TipKreditaLogika();
		List<TipKredita> tipovi = _tipKreditaLogika.dajSveKredite();
		int duzina=tipovi.size();
		
		_tipKreditaLogika.dodajTipKredita(new TipKreditaSluzbenik(
				"Nekiiii",
				"namjena",
				new Double(5000),
				"neki rok",
				new Double(20),
				"nemagarancije",
				"period",
				new Double(1000),
				"instrumenti"
				));
		
		List<TipKredita> _tipovi=_tipKreditaLogika.dajSveKredite();
		assertEquals((int)(duzina+1),_tipovi.size());
	}
}