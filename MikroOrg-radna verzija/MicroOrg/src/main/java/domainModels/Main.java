package domainModels;

import java.util.Scanner; 

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
 
Osoba o= new Osoba();
o.setImePrezime("Enver");
o.setEmail("2msehovic92@gmail.com");
Long osobaId= (Long) session.save(o);

Uposlenik u= new Uposlenik();
 u.setOsobaId(osobaId);
 u.setUkupanBrKredita(200);

 
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
