package logic;

import junit.framework.TestCase;

public class PonudeLogikaTest extends TestCase {
	public void testValidirajPodatke(){
		PonudeLogika _ponudeLogika= new PonudeLogika();
		String namjena="Namjena";
		String iznos="1000";
		String rok="subota";
		String kamatna="10";
		String garancija="garancija";
		String grace="Sarajevo";
		String troskovi="50";
		String instrumenti="instrumenti";
		assertEquals("OK", _ponudeLogika.validirajPodatke(namjena, iznos, rok, kamatna, garancija, grace, troskovi, instrumenti));
	}
}
