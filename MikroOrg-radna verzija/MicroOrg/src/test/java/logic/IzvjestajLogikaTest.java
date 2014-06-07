package logic;

import domainModels.Uposlenik;
import aplikacija.MicroOrg.Spremnik;
import viewModels.IzvjestajOrganizacije;
import viewModels.IzvjestajSluzbenika;
import junit.framework.TestCase;

public class IzvjestajLogikaTest extends TestCase {
	IzvjestajLogika nova= new IzvjestajLogika();
	public void testGenerisiIzvjestajSluzbenika() {
		IzvjestajSluzbenika k1=new IzvjestajSluzbenika();
		IzvjestajSluzbenika k2=new IzvjestajSluzbenika();
		IzvjestajSluzbenika k3=new IzvjestajSluzbenika();
		Spremnik sesija=new Spremnik();
		Uposlenik u=new Uposlenik();
		long b=(long) 1;
		u.setOsobaId(b);
		sesija.setTrenutni(u);
		k1 = nova.generisiIzvjestajSluzbenika("mjesec");
		k2 = nova.generisiIzvjestajSluzbenika("sest mjeseci");
		k3 = nova.generisiIzvjestajSluzbenika("godina");
		assertNotNull(k1);
		assertNotNull(k1.getDatumGenerisanja());
		assertNotNull(k2);
		assertNotNull(k2.getDatumGenerisanja());
		assertNotNull(k3);
		assertNotNull(k3.getDatumGenerisanja());
		
	}
	public void testGenerisiIzvjestajOrganizacije() {
		IzvjestajOrganizacije k1=new IzvjestajOrganizacije();
		IzvjestajOrganizacije k2=new IzvjestajOrganizacije();
		IzvjestajOrganizacije k3=new IzvjestajOrganizacije();
		Spremnik sesija=new Spremnik();
		Uposlenik u=new Uposlenik();
		long b=(long) 1;
		u.setOsobaId(b);
		sesija.setTrenutni(u);
		k1 = nova.generisiIzvjestajOrganizacije("mjesec");
		k2 = nova.generisiIzvjestajOrganizacije("sest mjeseci");
		k3 = nova.generisiIzvjestajOrganizacije("godina");
		assertNotNull(k1);
		assertNotNull(k1.getDatumGenerisanja());
		assertNotNull(k2);
		assertNotNull(k2.getDatumGenerisanja());
		assertNotNull(k3);
		assertNotNull(k3.getDatumGenerisanja());
		
	}
}
