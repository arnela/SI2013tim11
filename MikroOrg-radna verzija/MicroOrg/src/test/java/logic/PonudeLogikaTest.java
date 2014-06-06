package logic;

import junit.framework.TestCase;

public class PonudeLogikaTest extends TestCase {
	public void testValidirajPodatke(){
		PonudeLogika _ponudeLogika= new PonudeLogika();
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
		PonudeLogika _ponudeLogika= new PonudeLogika();
		assertEquals((Object)false, _ponudeLogika.postojiKlijent("11111", "11111")); //ne postoji klijent sa ovakvim imenom sigurno
		assertEquals((Object)false, _ponudeLogika.postojiKlijent("12345", " "));
		assertEquals((Object)false, _ponudeLogika.postojiKlijent("1sadasd", "habir14567"));
		assertEquals((Object)false, _ponudeLogika.postojiKlijent("aaaaaaaaaaaaaaaaaa", "bbbbbbbbbbbb"));
	}
}
