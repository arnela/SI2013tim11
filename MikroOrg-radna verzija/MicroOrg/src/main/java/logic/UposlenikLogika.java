package logic;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.HibernateException;

import viewModels.KreditniSluzbenik;
import domainModels.HibernateUtil;
import domainModels.Osoba;
import domainModels.Uposlenik;

public class UposlenikLogika {

	public Boolean daLiPostoji(String jmbg) {
		Session _session= HibernateUtil.getSessionFactory().openSession();
		 Transaction _t = _session.beginTransaction(); 
		 
		 Criteria criteria = _session.createCriteria(Uposlenik.class);
		 Uposlenik _u =(Uposlenik) criteria.add(Restrictions.eq("username", jmbg)).uniqueResult();
		 
		 _t.commit();
		 _session.close();
		 return _u!=null;
	} 
	public Osoba getOsobaByJMBG(String jmbg) {
		Session _session= HibernateUtil.getSessionFactory().openSession();
		 Transaction _t = _session.beginTransaction(); 
		 
		 Criteria criteria = _session.createCriteria(Osoba.class);
		 Osoba _o =(Osoba) criteria.add(Restrictions.eq("jmbg", jmbg)).uniqueResult();
		 
		 _t.commit();
		 _session.close();
		 return _o;
		 
	} 
	
	public Long dodajUposlenika(KreditniSluzbenik kreditniSluzbenik) {
		
		//TODO: Uraditi hashiranje passworda
		Session _session= HibernateUtil.getSessionFactory().openSession();
		
		
		Transaction _t = _session.beginTransaction(); 
		 Osoba _o= new Osoba(
				 	kreditniSluzbenik.getImePrezime(),
				 	kreditniSluzbenik.getJmbg(),
				 	kreditniSluzbenik.getDatumRodjenja(),
				 	kreditniSluzbenik.getObrazovanje(),
					kreditniSluzbenik.getTelefon(),
					kreditniSluzbenik.getAdresa(),
					kreditniSluzbenik.getEmail(),
					// tek kad sluzbenik napusti firmu onda je status aktivan = false
					true,
					new java.sql.Date(new java.util.Date().getTime())
				 );
		 Long _osobaId= (Long) _session.save(_o);
		 
		 Uposlenik _u= new Uposlenik(
				 // privilegije su true jedino ako je u pitanju šef 
				 false,
				 //IMPORTANT: u srsu pise da se pw treba biti ime+pr+jmbg ali ja sam ostavio da bude samo jmbg ( logicnije je )
				 kreditniSluzbenik.getJmbg(),
				 kreditniSluzbenik.getPassword(),
				 //broj evidentiranih kredita i trensakcija po defaultu se postavlja na 0
				 0,
				 0,
				 _osobaId,
				 kreditniSluzbenik.getMjestoRodjenja(),
				 kreditniSluzbenik.getPlata()
				 );
		  Long _id = (Long) _session.save(_u); 
		  
		_t.commit(); 
		_session.close();
		return _id;
	}

	public List<KreditniSluzbenik> getByName(String ime) {
		 List<KreditniSluzbenik> _kreditniSluzbenici= new ArrayList<KreditniSluzbenik>();
		
		 Session _session= HibernateUtil.getSessionFactory().openSession();
		 Transaction _t = _session.beginTransaction(); 
		 
		 Criteria criteria = _session.createCriteria(Osoba.class);
		 @SuppressWarnings("unchecked")
		 List<Osoba> _osobe =(List<Osoba>) criteria.add(Restrictions.eq("imePrezime", ime)).list();
		 for(Osoba osoba : _osobe){
				
			 KreditniSluzbenik _kreditniSluzbenik= new KreditniSluzbenik(
					    osoba.getImePrezime(),
					 	osoba.getJmbg(),
					 	osoba.getDatumRodjenja(),
					 	osoba.getObrazovanje(),
						osoba.getTelefon(),
						osoba.getAdresa(),
						osoba.getEmail()
					 );
			 criteria = _session.createCriteria(Uposlenik.class);
			 Uposlenik _u =(Uposlenik) criteria.add(Restrictions.eq("osobaId", osoba.getOsobaId())).uniqueResult();
			if(_u!=null&&osoba.getAktivan()!=false){
				_kreditniSluzbenik.setMjestoRodjenja(_u.getMjestoRodjenja());
				_kreditniSluzbenik.setPlata(_u.getPlata());
				_kreditniSluzbenik.setPassword(_u.getPassword());
				 _kreditniSluzbenik.setBrojKredita(_u.getUkupanBrKredita());
				 _kreditniSluzbenik.setBrojTransakcija(_u.getUkupanBrTransakcija());
				 _kreditniSluzbenici.add(_kreditniSluzbenik);
			}
		 }
		 _t.commit();
		 _session.close();
		 
		 return _kreditniSluzbenici;
	}

	public void softDeleteByJMBG(String jmbg) {
		 Session _session= HibernateUtil.getSessionFactory().openSession();
		 Transaction _t = _session.beginTransaction(); 
		 
		 Criteria criteria = _session.createCriteria(Osoba.class);
		 Osoba _o =(Osoba) criteria.add(Restrictions.eq("jmbg", jmbg)).uniqueResult();
		 _o.setAktivan(false);
		 
		 _session.update(_o);
		 _t.commit();
		 _session.close();
	}

	public static List<Uposlenik> dajSveUposlenike(){
		List<Uposlenik> uposlenici = new ArrayList<Uposlenik>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try{
		       	tx = session.beginTransaction();
		       	List up = session.createQuery("FROM Uposlenik").list(); 
		       	for (Iterator iterator = 
		               	up.iterator(); iterator.hasNext();){
		       		Uposlenik uposlenik = (Uposlenik) iterator.next(); 
		       		uposlenici.add(uposlenik);
		       	}
			tx.commit();
		}
		catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}
		finally {
			session.close(); 
		}
		return uposlenici;
	}
	public void promijeniUposlenika(KreditniSluzbenik _kreditniSluzbenik) {
		Session _session= HibernateUtil.getSessionFactory().openSession();
		 Transaction _t = _session.beginTransaction(); 
		 
		 Criteria criteria = _session.createCriteria(Osoba.class);
		 Osoba _o =(Osoba) criteria.add(Restrictions.eq("jmbg", _kreditniSluzbenik.getJmbg())).uniqueResult();
		 _o.setAdresa(_kreditniSluzbenik.getAdresa());
		 _o.setDatumRodjenja(_kreditniSluzbenik.getDatumRodjenja());
		 _o.setEmail(_kreditniSluzbenik.getEmail());
		 _o.setImePrezime(_kreditniSluzbenik.getImePrezime());
		 _o.setJmbg(_kreditniSluzbenik.getJmbg());
		 _o.setObrazovanje(_kreditniSluzbenik.getObrazovanje());
		 _o.setTelefon(_kreditniSluzbenik.getTelefon());
		 _session.update(_o);
		 
		 criteria = _session.createCriteria(Uposlenik.class);
		 Uposlenik _u =(Uposlenik) criteria.add(Restrictions.eq("osobaId", _o.getOsobaId())).uniqueResult();
		 _u.setMjestoRodjenja(_kreditniSluzbenik.getMjestoRodjenja());
		 _u.setPassword(_kreditniSluzbenik.getPassword());
		 _u.setPlata(_kreditniSluzbenik.getPlata());
		 _u.setUsername(_kreditniSluzbenik.getJmbg());
		 _session.update(_u);
		 
		 _t.commit();
		 _session.close();
	}


	public String validirajPodatke(String ime, String prezime, String jmbg,
			String datum, String telefon, String adresa, String email,
			String sifra, String mjestoRodjenja, String plata) {
		
		SharedLogika _sharedLogika= new SharedLogika();
		
		//provjera da li su popunjena sva polja
		if(ime.equals("")||prezime.equals("")||jmbg.equals("")||
			datum.equals("")||telefon.equals("")||adresa.equals("")||
			email.equals("")||sifra.equals("")||mjestoRodjenja.equals("")||plata.equals("")
				) 
			return "Nisu popunjena sva polja";
		
		//provjera Sivrinim metodama iz shared logike
		if(!_sharedLogika.validirajIme(ime))
			return "Ime nije validno";
		if(!_sharedLogika.validirajPrezime(prezime))
			return "Prezime nije validno";
		if(!_sharedLogika.validirajDatum(datum))
			return "Datum nije validan";
		if(!_sharedLogika.validirajEmail(email))
			return "Email nije validan";
		if(!isNumeric(plata))
			return "Plata nije validna";
		if(!_sharedLogika.validirajJMB(jmbg, StringToDate(datum)))
			return "JMBG nije validan";
		if(!_sharedLogika.validirajTelefon(telefon))
			return "Telefon nije validan";

		return "OK";
	}
	private java.util.Date StringToDate(String datum) {
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date _datum=null;
		try {
			_datum=formatter.parse(datum);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return _datum;
	}
	private boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}


	public Osoba getOsoba(long id) {
		Session _session= HibernateUtil.getSessionFactory().openSession();
		 Transaction _t = _session.beginTransaction(); 
		 
		 Criteria criteria = _session.createCriteria(Osoba.class);
		 Osoba _o =(Osoba) criteria.add(Restrictions.eq("id", id)).uniqueResult();
		 
		 _t.commit();
		 _session.close();
		 return _o;
	}


}
