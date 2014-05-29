package logic;

import java.sql.Date;
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
					 	null,
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
					 	null,
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
						 	null,
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
					 	null,
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
}
