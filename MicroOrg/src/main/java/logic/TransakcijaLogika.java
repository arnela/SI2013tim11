package logic;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import viewModels.KlijentSluzbenik;
import viewModels.KreditnaPonuda;
import viewModels.KreditniSluzbenik;
import domainModels.HibernateUtil;
import domainModels.Klijent;
import domainModels.Kredit;
import domainModels.Osoba;
import domainModels.TipKredita;
import domainModels.Uposlenik;
import viewModels.Transakcija;



public class TransakcijaLogika {
	
	public Long dodajTransakciju(Transakcija transakcija){
		
		Session _session= HibernateUtil.getSessionFactory().openSession();
		Transaction _t = _session.beginTransaction(); 
		
		Transakcija _tr = new Transakcija(
				
				transakcija.getDatumUplate(),
				transakcija.getIznosUplate(),
				transakcija.getNacinUplate(),
				
				transakcija.getKlijent(),
				transakcija.getKredit(),
				
				transakcija.getUposlenik()
				
				);
	
		Long _id = (Long) _session.save(_tr); 
		//umanjiti iznos kredita za iznos uplate  
		_t.commit(); 
		_session.close();
		return _id;	
		
		
	}
	
	
	
	public Boolean daLiPostoji(String jmbg) {
		Session _session= HibernateUtil.getSessionFactory().openSession();
		 Transaction _t = _session.beginTransaction(); 
		 
		 Criteria criteria = _session.createCriteria(Osoba.class);
		 Osoba _o =(Osoba) criteria.add(Restrictions.eq("jmbg", jmbg)).uniqueResult();
		 
		 _t.commit();
		 _session.close();
		 return _o!=null;
	} 
	
	
	
	public Klijent dajKlijenta(String jmbg) {
		Session _session= HibernateUtil.getSessionFactory().openSession();
		 Transaction _t = _session.beginTransaction(); 
		 
		 Criteria criteria = _session.createCriteria(Klijent.class);
		 Klijent _k =(Klijent) criteria.add(Restrictions.eq("jmbg", jmbg)).uniqueResult();
		 
		 _t.commit();
		 _session.close();
		 return _k;
	} 
	
	
	
	public List<Transakcija> getByDate(String datum) {
		 
		 Session _session= HibernateUtil.getSessionFactory().openSession();
		 Transaction _t = _session.beginTransaction(); 
		 
		 Criteria criteria = _session.createCriteria(Transakcija.class);
		 @SuppressWarnings("unchecked")
		 List<Transakcija> _transakcije =(List<Transakcija>) criteria.add(Restrictions.eq("datumUplate", datum)).list();
		 
		 _t.commit();
		 _session.close();
		 
		 return _transakcije;
	}

	
	public List<Transakcija> getByKlijent(String ime) {
		 
		 Session _session= HibernateUtil.getSessionFactory().openSession();
		 Transaction _t = _session.beginTransaction(); 
		 
		 	 Criteria criteria = _session.createCriteria(Osoba.class);
			 @SuppressWarnings("unchecked")
			 Osoba _o =(Osoba) criteria.add(Restrictions.eq("imePrezime", ime)).uniqueResult();
			 
		
				 KlijentSluzbenik _klijent= new KlijentSluzbenik(
						    _o.getImePrezime(),
						 	_o.getJmbg(),
						 	_o.getDatumRodjenja(),
						 	null,
							_o.getTelefon(),
							_o.getAdresa(),
							_o.getEmail()
						 );
				 criteria = _session.createCriteria(Klijent.class);
				 Klijent _k =(Klijent) criteria.add(Restrictions.eq("osobaId", _o.getOsobaId())).uniqueResult();
		
		 
		 criteria = _session.createCriteria(Transakcija.class);
		 @SuppressWarnings("unchecked")
		 List<Transakcija> _transakcije =(List<Transakcija>) criteria.add(Restrictions.eq("klijent", _k)).list();
		 
		 _t.commit();
		 _session.close();
		 
		 return _transakcije;
	}
	
	
	
	public Transakcija getByID(String id) {
		Session _session= HibernateUtil.getSessionFactory().openSession();
		 Transaction _t = _session.beginTransaction(); 
		 
		 	 Criteria criteria = _session.createCriteria(domainModels.Transakcija.class);
			 @SuppressWarnings("unchecked")
			 domainModels.Transakcija _tra =(domainModels.Transakcija) criteria.add(Restrictions.eq("transakcijaId", id)).uniqueResult();
			 
		
				 Transakcija _transakcija= new Transakcija(
						 	_tra.getDatumUplate(),
							_tra.getIznosUplate(),
							_tra.getNacinUplate(),
							_tra.getKlijent(),
							_tra.getKredit(),
							_tra.getKreditniSluzbenik()
						 );
				
		 _t.commit();
		 _session.close();
		 
		 return _transakcija;
	}
	
	
	
	public List<Transakcija> getByTipKredita(String nazivTipa){
		Session _session = HibernateUtil.getSessionFactory().openSession();
		Transaction _t = _session.beginTransaction();
		
		Criteria _criteria = _session.createCriteria(TipKredita.class);
		TipKredita _kredit = (TipKredita)_criteria.add(Restrictions.eq("naziv", nazivTipa)).uniqueResult();
		
		 _criteria = _session.createCriteria(Transakcija.class);
		 @SuppressWarnings("unchecked")
		 List<Transakcija> _transakcije =(List<Transakcija>) _criteria.add(Restrictions.eq("kredit", _kredit)).list();
		 
		 _t.commit();
		 _session.close();
		 
		 return _transakcije;
	}
	
	
	
	
	
	public void softDeleteByMorePar(String datum, Double iznos, String nacin) {
		 Session _session= HibernateUtil.getSessionFactory().openSession();
		 Transaction _t = _session.beginTransaction(); 
		 
		 Criteria criteria = _session.createCriteria(Transakcija.class);
		 Transakcija _tr = (Transakcija) criteria.add(Restrictions.and(Restrictions.eq("datumUplate", datum), Restrictions.eq("iznosUplate", iznos),Restrictions.eq("nacinUplate", nacin))); 
		 //uvecati iznos kredita
		
		 _session.update(_tr);
		 _t.commit();
		 _session.close();
	}

	
	
	
	}