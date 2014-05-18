package domainModels;

import java.util.Scanner; 

import org.hibernate.Transaction; 
import org.hibernate.Session; 
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
 
 Osoba o = new Osoba(); 
 o.setImePrezime("Mirza Sehovic");
 o.setEmail("msehovic92@gmail.com");
 
 Uposlenik u= new Uposlenik();
 u.setUkupanBrKredita(200);
 session.save(u);
 o.setUposlenik(u);
 
 Long id = (Long) session.save(o); 
 System.out.println("Dodana osoba sa IDom "+id); 
 t.commit(); 
 } 
 
 private static void nadjiOsobu(Session session) { 
 Transaction t = session.beginTransaction(); 
 
 System.out.println("Unesite id osobe"); 
 long id = sc.nextLong(); 
 Osoba o = (Osoba) session.get(Osoba.class, id); 
 if (o==null) { 
 System.out.println("Nema osobe sa tim IDom u bazi"); 
 } else { 
 System.out.println("Student: "+o.getImePrezime()+" "+o.getEmail()); 
 } 
 
 t.commit(); 
 } 
} 
