package logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

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
				new Double(1000)
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
			new Double(1000)
			));
	
	List<TipKreditaSluzbenik> _tipovi=_tipKreditaLogika.getAll();
	assertEquals((int)(duzina+1),_tipovi.size());
}
}