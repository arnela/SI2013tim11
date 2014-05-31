package domainModels;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner; 
import java.sql.Date;
import org.hibernate.Criteria;
import org.hibernate.Transaction; 
import org.hibernate.Session; 
import org.hibernate.criterion.Restrictions;

import domainModels.HibernateUtil; 

 
public class Main { 
 private static Scanner sc = new Scanner(System.in); 

 public static void main(String[] args) {Session session =   HibernateUtil.getSessionFactory().openSession();

 dodajOsobu(session);
 nadjiOsobu(session); 
 session.close(); 
 } 
 
 
 private static void dodajOsobu(Session session) { 
 Transaction t = session.beginTransaction(); 
 java.sql.Date _datum = null;
	SimpleDateFormat _sdf1 = new SimpleDateFormat("dd-MM-yyyy");
	java.util.Date _date;
	try 
	{
		_date = _sdf1.parse("31-03-1992");
		_datum=new java.sql.Date(_date.getTime());  
	}
	
	catch (ParseException e1) 
	{
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
Osoba o= new Osoba();
o.setImePrezime("Arnela Tumbul");
o.setEmail("artmob49@gmail.com");
o.setJmbg("3103992175103");
o.setAdresa("Vrbovska 178");
o.setObrazovanje("BoEE");
o.setTelefon("061-916-987");
o.setDatumRodjenja(_datum); 
o.setAktivan(true);
o.setDatumUnosa(new java.sql.Date(new java.util.Date().getTime()));
Long osobaId= (Long) session.save(o);

Uposlenik u= new Uposlenik();
 u.setOsobaId(osobaId);
 u.setUkupanBrKredita(200);
 u.setPrivilegije(true);
 u.setUsername(o.getJmbg());
 u.setPassword("pass"); 
 u.setMjestoRodjenja("Sarajevo");
 u.setPlata(2200.50);
 u.setUkupanBrTransakcija(350);
 
 
 Long id = (Long) session.save(u); 
 System.out.println("Dodana osoba sa IDom "+osobaId); 
 t.commit(); 
 } 
 
 private static void nadjiOsobu(Session session) { 
 Transaction t = session.beginTransaction(); 
 
 System.out.println("Unesite ime osobe"); 
 String _ime = sc.next();
 Criteria criteria = session.createCriteria(Osoba.class);
 Osoba _o =(Osoba) criteria.add(Restrictions.eq("imePrezime", _ime)).uniqueResult();
                              
 if (_o==null) { 
 System.out.println("Nema osobe sa tim imenom u bazi"); 
 } else { 
 System.out.println("Student: "+_o.getImePrezime()+" "+_o.getEmail()); 
 } 
 
 /*System.out.println("Unesite id osobe"); 
 long id = sc.nextLong(); 
 Osoba o = (Osoba) session.get(Osoba.class, id); 
 if (o==null) { 
 System.out.println("Nema osobe sa tim IDom u bazi"); 
 } else { 
 System.out.println("Student: "+o.getImePrezime()+" "+o.getEmail()); 
 } */
 
 t.commit(); 
 } 
} 
