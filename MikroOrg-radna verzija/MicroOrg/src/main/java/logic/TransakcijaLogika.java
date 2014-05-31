package logic;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import aplikacija.MicroOrg.Spremnik;
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
		
		domainModels.Transakcija _tr = new domainModels.Transakcija(
				transakcija.getKlijent(),
				transakcija.getKredit(),
				transakcija.getIznosUplate(),
				transakcija.getDatumUplate(),
				1,
				transakcija.getNacinUplate()
				);

		Double iznos= _tr.getKredit().getTipKredita().getIznos();
		_tr.getKredit().getTipKredita().setIznos(iznos-(_tr.getIznosUplate()));
		int brt = transakcija.getUposlenik().getUkupanBrTransakcija();
		transakcija.getUposlenik().setUkupanBrTransakcija(brt+1);

		Long _id = (Long) _session.save(_tr); 
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
		 //ovo je dalo na osnovu jmba u onoj formi klijenta ispravno // znaci ovo radi?da
		 Criteria criteria = _session.createCriteria(Osoba.class);
		 @SuppressWarnings("unchecked")
		 List<Osoba> _osobe =(List<Osoba>) criteria.add(Restrictions.eq("jmbg", jmbg)).list();
		 Klijent _k = new Klijent();
		 for(Osoba _o : _osobe) {
			 criteria = _session.createCriteria(Klijent.class);
			 _k =(Klijent) criteria.add(Restrictions.eq("osobaId", _o.getOsobaId())).uniqueResult();
			 break;			 
		 }
		 
		 _t.commit();
		 _session.close();
		 
		 return _k;

	} 
	
	public Osoba dajOsobu(String jmbg) {
		Session _session= HibernateUtil.getSessionFactory().openSession();
		 Transaction _t = _session.beginTransaction(); 
		 //ipak ovo
		 Criteria criteria = _session.createCriteria(Osoba.class);
		 Osoba _o =(Osoba) criteria.add(Restrictions.eq("jmbg", jmbg)).uniqueResult();
		 
		 _t.commit();
		 _session.close();
		 return _o;
	} 
	
	public List<Transakcija> getByDate(String datum) {
		 
		 Session _session= HibernateUtil.getSessionFactory().openSession();
		 Transaction _t = _session.beginTransaction(); 
		 
		 Criteria criteria = _session.createCriteria(domainModels.Transakcija.class);
		 @SuppressWarnings("unchecked")
		 List<domainModels.Transakcija> _transakcije =(List<domainModels.Transakcija>) criteria.add(Restrictions.eq("datumUplate", datum)).list();
		
		 List<Transakcija> _lt = new ArrayList<Transakcija>();
		 
		 for(domainModels.Transakcija _t1 : _transakcije){
			 
			 Transakcija _tr = new Transakcija(
						_t1.getDatumUplate(),
						 _t1.getIznosUplate(),
						_t1.getNacinUplate(),
						_t1.getKlijent(),
						_t1.getKredit(),
						Spremnik.getTrenutni()
						 );	 
			 _lt.add(_tr);
		 }
		 
		 _t.commit();
		 _session.close();
		 return _lt;
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
		
		 
		 criteria = _session.createCriteria(domainModels.Transakcija.class);
		 @SuppressWarnings("unchecked")
		 List<domainModels.Transakcija> _transakcije =(List<domainModels.Transakcija>) criteria.add(Restrictions.eq("klijent", _k)).list();
		 List<Transakcija> _lt = new ArrayList<Transakcija>();
		 
		 for(domainModels.Transakcija _t1 : _transakcije){
			 
			 Transakcija _tr = new Transakcija(
						_t1.getDatumUplate(),
						 _t1.getIznosUplate(),
						_t1.getNacinUplate(),
						_t1.getKlijent(),
						_t1.getKredit(),
						Spremnik.getTrenutni()
						 );	 
			 _lt.add(_tr);
		 }
		 _t.commit();
		 _session.close();
		 
		 return _lt;
	}
	
	public Transakcija getByID(String id) {
		Session _session= HibernateUtil.getSessionFactory().openSession();
		 Transaction _t = _session.beginTransaction(); 
		 
		 	 Criteria criteria = _session.createCriteria(domainModels.Transakcija.class);
			 @SuppressWarnings("unchecked")
			 domainModels.Transakcija _tra = (domainModels.Transakcija) criteria.add(Restrictions.eq("transakcijaId", id)).uniqueResult();
			 
		
				 Transakcija _transakcija= new Transakcija(
						 	_tra.getDatumUplate(),
							_tra.getIznosUplate(),
							_tra.getNacinUplate(),
							_tra.getKlijent(),
							_tra.getKredit(),
							Spremnik.getTrenutni()
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
		
		_criteria = _session.createCriteria(Kredit.class);
		Kredit _kredit1 = (Kredit)_criteria.add(Restrictions.eq("tipKredita", _kredit)).uniqueResult();
		
		
		 _criteria = _session.createCriteria(domainModels.Transakcija.class);
		 @SuppressWarnings("unchecked")
		 List<domainModels.Transakcija> _transakcije =(List<domainModels.Transakcija>) _criteria.add(Restrictions.eq("kredit", _kredit1)).list();

		 List<Transakcija> _lt = new ArrayList<Transakcija>();
		 
		 for(domainModels.Transakcija _t1 : _transakcije){
			 
			 Transakcija _tr = new Transakcija(
						_t1.getDatumUplate(),
						 _t1.getIznosUplate(),
						_t1.getNacinUplate(),
						_t1.getKlijent(),
						_t1.getKredit(),
						Spremnik.getTrenutni()
						 );	 
			 _lt.add(_tr);
		 }
		 
		 _t.commit();
		 _session.close();
		 return _lt;
	}
	
	public void softDeleteByMorePar(String datum, Double iznos, String nacin) {
		 Session _session= HibernateUtil.getSessionFactory().openSession();
		 Transaction _t = _session.beginTransaction(); 
		 
		 Criteria criteria = _session.createCriteria(Transakcija.class);
		 domainModels.Transakcija _tr = (domainModels.Transakcija) criteria.add(Restrictions.and(Restrictions.eq("datumUplate", datum), Restrictions.eq("iznosUplate", iznos),Restrictions.eq("nacinUplate", nacin))).uniqueResult(); 
		 
		 //uvecati iznos kredita
		 Double _iznosKredita= _tr.getKredit().getTipKredita().getIznos();
		 _tr.getKredit().getTipKredita().setIznos(_iznosKredita + (_tr.getIznosUplate()));
			
		 _session.delete(_tr);
		 _t.commit();
		 _session.close();

	}

	public void softDeleteByDatum(String datum) {
		 Session _session= HibernateUtil.getSessionFactory().openSession();
		 Transaction _t = _session.beginTransaction(); 
		 
		 Criteria criteria = _session.createCriteria(domainModels.Transakcija.class);
		 domainModels.Transakcija _tr = (domainModels.Transakcija) criteria.add(Restrictions.eq("datumUplate", datum)).uniqueResult(); 
		 
		 //uvecati iznos kredita
		 Double _iznosKredita= _tr.getKredit().getTipKredita().getIznos();
		 _tr.getKredit().getTipKredita().setIznos(_iznosKredita + (_tr.getIznosUplate()));
			
		 _session.delete(_tr);
		 _t.commit();
		 _session.close();

	}
	
	public void softDeleteByNacin(String nacin) {
		 Session _session= HibernateUtil.getSessionFactory().openSession();
		 Transaction _t = _session.beginTransaction(); 
		 
		 Criteria criteria = _session.createCriteria(domainModels.Transakcija.class);
		 domainModels.Transakcija _tr = (domainModels.Transakcija) criteria.add(Restrictions.eq("nacinUplate", nacin)).uniqueResult(); 
		 
		 //uvecati iznos kredita
		 Double _iznosKredita= _tr.getKredit().getTipKredita().getIznos();
		 _tr.getKredit().getTipKredita().setIznos(_iznosKredita + (_tr.getIznosUplate()));
			
		 _session.delete(_tr);
		 _t.commit();
		 _session.close();

	}
	
	public Boolean daLiPostojiT(String nazivTipa) {
		
		Session _session= HibernateUtil.getSessionFactory().openSession();
		Transaction _t = _session.beginTransaction(); 
		 
		Criteria criteria = _session.createCriteria(TipKredita.class);
		TipKredita _kredit = (TipKredita)criteria.add(Restrictions.eq("naziv", nazivTipa)).uniqueResult();
		 criteria = _session.createCriteria(Transakcija.class);
		 @SuppressWarnings("unchecked")
		 List<Transakcija> _transakcije =(List<Transakcija>) criteria.add(Restrictions.eq("kredit", _kredit)).list();
		 		 
		_t.commit();
		_session.close();
		return _transakcije!=null;
	}
	
	public Kredit traziPoTipuKreditaIKlijenta(String jmbg){
		Session _session= HibernateUtil.getSessionFactory().openSession();
		Transaction _t = _session.beginTransaction();
		
		Criteria criteria = _session.createCriteria(Osoba.class);
		Kredit _kr = new Kredit();
		Osoba _o = (Osoba) criteria.add(Restrictions.eq("jmbg", jmbg)).uniqueResult(); //cek

		Klijent _kl = new Klijent();
		List<Klijent> klijenti = new ArrayList<Klijent>();

		try{

			criteria = _session.createCriteria(Klijent.class);
			_kl = (Klijent) criteria.add(Restrictions.eq("osobaId", _o.getOsobaId())).uniqueResult();
			if(_kl != null) klijenti.add(_kl);			 

		}catch(Exception e){JOptionPane.showMessageDialog(null, e.getMessage());}
		Kredit _kr1 = new Kredit();
		List<Kredit> _kre = new ArrayList<Kredit>();
		for(Klijent _k : klijenti) {
		criteria = _session.createCriteria(Kredit.class); 
		_kr1 = (Kredit) criteria.add(Restrictions.eq("klijent", _k)).uniqueResult();
		_kre.add(_kr1); 
		}
		
	
		_kr = _kre.get(0); // koji ti ovdje index treba? stavit cemo 0
		_t.commit();
		_session.close();
		return _kr;
	}
	
	public String validirajPodatke(String datum, String iznos, String nacin) {
		
		SharedLogika _sharedLogika= new SharedLogika();
		
		//provjera da li su popunjena sva polja
		if(datum.equals("")||iznos.equals("")||nacin.equals("")) 
			return "Nisu popunjena sva polja";
		
		if(!_sharedLogika.validirajDatum(datum))
			return "Datum nije validno";
		if(!isNumeric(iznos))
			return "Iznos nije validno";
		
		return "OK";
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