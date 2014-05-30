package logic;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import viewModels.KlijentSluzbenik;
import viewModels.KreditniSluzbenik;
import domainModels.HibernateUtil;
import domainModels.Osoba;
import domainModels.Klijent;
import domainModels.Uposlenik;


public class KlijentLogika {
	
	public Boolean daLiPostoji(String jmbg) {
		Session _session= HibernateUtil.getSessionFactory().openSession();
		 Transaction _t = _session.beginTransaction(); 
		 
		 Criteria criteria = _session.createCriteria(Osoba.class);
		 Osoba _o =(Osoba) criteria.add(Restrictions.eq("jmbg", jmbg)).uniqueResult();
		 
		 _t.commit();
		 _session.close();
		 return _o!=null;
	} 
	
	
	public Long dodajKlijenta (KlijentSluzbenik klijentSluzbenik)
	{
		Session _session= HibernateUtil.getSessionFactory().openSession();
		Transaction _t = _session.beginTransaction(); 
		 Osoba _o= new Osoba(
				 klijentSluzbenik.getImePrezime(),
				 	klijentSluzbenik.getJmbg(),
				 	klijentSluzbenik.getDatumRodjenja(),
				 	null,
				 	klijentSluzbenik.getTelefon(),
				 	klijentSluzbenik.getAdresa(),
				 	klijentSluzbenik.getEmail(),
					true,
					new java.sql.Date(new java.util.Date().getTime())
				 );
		 Long _osobaId= (Long) _session.save(_o);
		 
		 Klijent _k = new Klijent(
				 klijentSluzbenik.getStatus(),
				 _osobaId
				 );
		 Long _id = (Long) _session.save(_k); 
		  
			_t.commit(); 
			_session.close();
			return _id;
	}
	
	public List<KlijentSluzbenik> getByName(String ime) {
		 List<KlijentSluzbenik> _klijenti= new ArrayList<KlijentSluzbenik>();
		
		 Session _session= HibernateUtil.getSessionFactory().openSession();
		 Transaction _t = _session.beginTransaction(); 
		 
		 Criteria criteria = _session.createCriteria(Osoba.class);
		 @SuppressWarnings("unchecked")
		 List<Osoba> _osobe =(List<Osoba>) criteria.add(Restrictions.eq("imePrezime", ime)).list();
		 for(Osoba osoba : _osobe){
	
			 KlijentSluzbenik _klijent= new KlijentSluzbenik(
					    osoba.getImePrezime(),
					 	osoba.getJmbg(),
						osoba.getDatumRodjenja(),
						osoba.getTelefon(),
						osoba.getAdresa(),
						osoba.getEmail()
					 );
			 _klijent.setDatumRodjenja(osoba.getDatumRodjenja());
			 criteria = _session.createCriteria(Klijent.class);
			 Klijent _k =(Klijent) criteria.add(Restrictions.eq("osobaId", osoba.getOsobaId())).uniqueResult();
			 if(_k!=null&&osoba.getAktivan()!=false){
				 _klijent.setStatus(_k.getStatus());
				 _klijenti.add(_klijent);
			} 
		 }
		 _t.commit();
		 _session.close();
		 
		 return _klijenti;
	}

	public List<KlijentSluzbenik> getByDate(Date datum) {
		 List<KlijentSluzbenik> _klijenti= new ArrayList<KlijentSluzbenik>();
			
		 Session _session= HibernateUtil.getSessionFactory().openSession();
		 Transaction _t = _session.beginTransaction(); 
		 
		 Criteria criteria = _session.createCriteria(Osoba.class);
		 @SuppressWarnings("unchecked")
		 List<Osoba> _osobe =(List<Osoba>) criteria.add(Restrictions.eq("datumUnosa", datum)).list();
		 for(Osoba osoba : _osobe){
	
			 KlijentSluzbenik _klijent= new KlijentSluzbenik(
					    osoba.getImePrezime(),
					 	osoba.getJmbg(),
					 	osoba.getDatumRodjenja(),
						osoba.getTelefon(),
						osoba.getAdresa(),
						osoba.getEmail()
					 );
			 criteria = _session.createCriteria(Klijent.class);
			 Klijent _k =(Klijent) criteria.add(Restrictions.eq("osobaId", osoba.getOsobaId())).uniqueResult();
			 if(_k!=null&&osoba.getAktivan()!=false){
				 _klijent.setStatus(_k.getStatus());
				 _klijenti.add(_klijent);
			} 
		 }
		 _t.commit();
		 _session.close();
		 
		 return _klijenti;
		
	}
	public List<KlijentSluzbenik> getByEmail(String email) {
		 List<KlijentSluzbenik> _klijenti= new ArrayList<KlijentSluzbenik>();
		
		 Session _session= HibernateUtil.getSessionFactory().openSession();
		 Transaction _t = _session.beginTransaction(); 
		 
		 Criteria criteria = _session.createCriteria(Osoba.class);
		 @SuppressWarnings("unchecked")
		 
		 List<Osoba> _osobe =(List<Osoba>) criteria.add(Restrictions.eq("email", email)).list();
	
			 for(Osoba osoba : _osobe){
					
				 KlijentSluzbenik _klijent= new KlijentSluzbenik(
						    osoba.getImePrezime(),
						 	osoba.getJmbg(),
						 	osoba.getDatumRodjenja(),
							osoba.getTelefon(),
							osoba.getAdresa(),
							osoba.getEmail()
						 );
				 criteria = _session.createCriteria(Klijent.class);
				 Klijent _k =(Klijent) criteria.add(Restrictions.eq("osobaId", osoba.getOsobaId())).uniqueResult();
				 if(_k!=null&&osoba.getAktivan()!=false){
					 _klijent.setStatus(_k.getStatus());
					 _klijenti.add(_klijent);
				} 
			 }
		 _t.commit();
		 _session.close();
		 
		 return _klijenti;
	}
	public List<KlijentSluzbenik> getByAddress(String adresa) {
		 List<KlijentSluzbenik> _klijenti= new ArrayList<KlijentSluzbenik>();
		
		 Session _session= HibernateUtil.getSessionFactory().openSession();
		 Transaction _t = _session.beginTransaction(); 
		 
		 Criteria criteria = _session.createCriteria(Osoba.class);
		 @SuppressWarnings("unchecked")
		 
		 List<Osoba> _osobe =(List<Osoba>) criteria.add(Restrictions.eq("adresa", adresa)).list();
		 for(Osoba osoba : _osobe){
				
			 KlijentSluzbenik _klijent= new KlijentSluzbenik(
					    osoba.getImePrezime(),
					 	osoba.getJmbg(),
					 	osoba.getDatumRodjenja(),
						osoba.getTelefon(),
						osoba.getAdresa(),
						osoba.getEmail()
					 );
			 criteria = _session.createCriteria(Klijent.class);
			 Klijent _k =(Klijent) criteria.add(Restrictions.eq("osobaId", osoba.getOsobaId())).uniqueResult();
			 if(_k!=null&&osoba.getAktivan()!=false){
				 _klijent.setStatus(_k.getStatus());
				 _klijenti.add(_klijent);
			} 
		 }
		 _t.commit();
		 _session.close();
		 
		 return _klijenti;
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
	public void promijeniKlijenta(KlijentSluzbenik _klijent) {
		Session _session= HibernateUtil.getSessionFactory().openSession();
		 Transaction _t = _session.beginTransaction(); 
		 
		 Criteria criteria = _session.createCriteria(Osoba.class);
		 Osoba _o =(Osoba) criteria.add(Restrictions.eq("jmbg", _klijent.getJmbg())).uniqueResult();
		 _o.setAdresa(_klijent.getAdresa());
		 _o.setDatumRodjenja(_klijent.getDatumRodjenja());
		 _o.setEmail(_klijent.getEmail());
		 _o.setImePrezime(_klijent.getImePrezime());
		 _o.setJmbg(_klijent.getJmbg());
		 _o.setTelefon(_klijent.getTelefon());
		 _session.update(_o);
		 
		 criteria = _session.createCriteria(Klijent.class);
		 Klijent _k =(Klijent) criteria.add(Restrictions.eq("osobaId", _o.getOsobaId())).uniqueResult();
		 _k.setStatus(_klijent.getStatus());
		  
		 _session.update(_k);
		 
		 _t.commit();
		 _session.close();
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
	public String validirajPodatke(String ime, String prezime, String jmbg,
			String datum, String telefon, String adresa, String email,
			String status) {
		
		SharedLogika _sharedLogika= new SharedLogika();
		
		//provjera da li su popunjena sva polja
		if(ime.equals("")||prezime.equals("")||jmbg.equals("")||
			datum.equals("")||telefon.equals("")||adresa.equals("")||
			email.equals("")||status.equals("")) 
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


}
