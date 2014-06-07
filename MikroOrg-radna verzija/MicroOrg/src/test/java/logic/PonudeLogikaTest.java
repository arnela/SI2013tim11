package logic;

import java.util.List;

import viewModels.KlijentSluzbenik;
import viewModels.KreditnaPonuda;
import viewModels.TipKreditaSluzbenik;
import domainModels.Klijent;
import domainModels.Osoba;
import domainModels.TipKredita;
import junit.framework.TestCase;

public class PonudeLogikaTest extends TestCase {
	PonudeLogika _ponudeLogika= new PonudeLogika();
	public void testValidirajPodatke(){
		
		String naziv="Nekii";
		String namjena="Namjena";
		String iznos="1000";
		String rok="subota";
		String kamatna="10";
		String garancija="garancija";
		String grace="Sarajevo";
		String troskovi="50";
		String instrumenti="instrumenti";
		assertEquals("OK", _ponudeLogika.validirajPodatke(naziv, namjena, iznos, rok, kamatna, garancija, grace, troskovi, instrumenti));
	}
	public void testPostojiKlijent(){
		// Klijent k=new Klijent() nećemo dodavati novog klijenta zbog baze jer kad bi se test ponovio 2 puta desio bi se error u bazi
		//PonudeLogika _ponudeLogika= new PonudeLogika();
		assertEquals((Object)false, _ponudeLogika.postojiKlijent("11111", "11111")); //ne postoji klijent sa ovakvim imenom sigurno
		assertEquals((Object)false, _ponudeLogika.postojiKlijent("12345", " "));
		assertEquals((Object)false, _ponudeLogika.postojiKlijent("1sadasd", "habir14567"));
		assertEquals((Object)false, _ponudeLogika.postojiKlijent("aaaaaaaaaaaaaaaaaa", "bbbbbbbbbbbb"));
	}
	public void testDodajPonudu(){
		TipKredita tip=new TipKredita();
		KlijentSluzbenik klijent=new KlijentSluzbenik();
		
		KlijentLogika klijentLogika=new KlijentLogika();
		Osoba osoba=new Osoba();
		
		if(klijentLogika.daLiPostoji("0000000000000") && klijentLogika.daLiPostojiEmail("testPonudeLogika"))
		{
		osoba=klijentLogika.getOsobaByJMBG("0000000000000");
		List<KlijentSluzbenik> lista=klijentLogika.getByName(osoba.getImePrezime());
		}
		else{
		osoba.setImePrezime("Test PonudeLogika");
		osoba.setJmbg("0000000000000");
		osoba.setEmail("testPonudeLogika");
		osoba.setAdresa("Test adresa");
		osoba.setAktivan(true);
		osoba.setObrazovanje("Test obrazovanje");
		
		}
		klijent.setAdresa(osoba.getAdresa());
		klijent.setDatumRodjenja(osoba.getDatumRodjenja());
		klijent.setStatus("aktivan");
		klijent.setEmail(osoba.getEmail());
		klijent.setImePrezime(osoba.getImePrezime());
		klijent.setJmbg(osoba.getJmbg());
		klijent.setTelefon(osoba.getTelefon());
		if (klijentLogika.daLiPostoji("0000000000000")==false) {klijentLogika.dodajKlijenta(klijent);}
		Klijent pravi=new Klijent();
		TipKreditaLogika kreditLogika=new TipKreditaLogika();
		TipKreditaSluzbenik tipkredita=new TipKreditaSluzbenik();
		int tmp=0;
		for(int i=0;i<100;i++)
		{
			
			tip.setGarancija("test br"+i);
			tip.setNaziv("Test"+i);
			if ((_ponudeLogika.dajTipKredita("Test"+i)==null))
			{
				tmp=i;
				tipkredita.setGarancija(tip.getGarancija());
				tipkredita.setGracePeriod(tip.getGracePeriod());
				tipkredita.setNaziv(tip.getNaziv());
				tipkredita.setNamjena(tip.getNamjena());
				kreditLogika.dodajTipKredita(tipkredita);
				
				KreditnaPonuda k=new KreditnaPonuda(null,null,null,"2014.07.06");
				_ponudeLogika.dodajPonudu(k);

				break;
			}
		}
		assertNotNull(_ponudeLogika.dajTipKredita("Test"+tmp));
		
	}
	public void testDajKlijenta(){
		KlijentSluzbenik novi=new KlijentSluzbenik();
		long b=(long) 150;
		novi.setAdresa("test");
		novi.setDatumRodjenja(null);
		novi.setEmail("test");
		novi.setImePrezime("Test Test");
		novi.setJmbg("111111111111");
		novi.setStatus("aktivan");
	KlijentLogika klogika=new KlijentLogika();
	klogika.dodajKlijenta(novi);
	PonudeLogika log=new PonudeLogika();
	UposlenikLogika ulog=new UposlenikLogika();
	assertNotNull(log.dajKlijenta("Test", "Test"));
	assertEquals("Test Test", ulog.getOsoba(log.dajKlijenta("Test", "Test").getOsobaId()).getImePrezime());
		
		
	}
	public void testTraziPoImenuKlijenta(){
		long b=(long) 150;
		Osoba o=new Osoba();
		o.setImePrezime("Test1 Test1");
		KlijentLogika l=new KlijentLogika();
		KlijentSluzbenik kl=new KlijentSluzbenik();
		kl.setImePrezime(o.getImePrezime());
		l.dodajKlijenta(kl);
		PonudeLogika plogika=new PonudeLogika();
		assertNotNull(plogika.traziPoImenuKlijenta("Test1 Test1"));
	}
}
