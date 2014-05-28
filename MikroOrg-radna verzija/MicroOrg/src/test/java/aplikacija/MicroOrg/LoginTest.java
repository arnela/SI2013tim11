package aplikacija.MicroOrg;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JFrame;

import SluzbenikGui.*;
import viewModels.KreditniSluzbenik;
import junit.framework.Assert;
import junit.framework.TestCase;
import logic.UposlenikLogika;
import aplikacija.MicroOrg.Spremnik;
public class LoginTest extends TestCase {
	
	public void testPronadji() {
		Login novi =  new Login();
		assertEquals((Boolean)false,(Boolean) novi.Pronadji("1235","42367888990"));
	}

	public void testIspitaj(){
		Login novi= new Login();
		if(novi.Pronadji("elvis","122")){assertTrue(Spremnik.getTrenutni().getPrivilegije());assertTrue(new SluzbenikGui.Pocetni().isShowing());}
		else{
				UposlenikLogika _uposlenikLogika=new UposlenikLogika();
				SimpleDateFormat _sdf1 = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date _date = null;
				try {
					_date = _sdf1.parse("07-09-1992");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				_uposlenikLogika.dodajUposlenika(new KreditniSluzbenik("elvis","122",new java.sql.Date(_date.getTime()),"null","061-113-322","Neka Adresa 1337","mail@mail.mail","122","Sarajevo",new Double(1337)));
				novi.Ispitaj("elvis", "122");
				assertTrue(Spremnik.getTrenutni().getPrivilegije());
				assertTrue(new SluzbenikGui.Pocetni().isShowing());
		}

	}
	


}
