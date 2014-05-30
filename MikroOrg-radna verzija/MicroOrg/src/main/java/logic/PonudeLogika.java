package logic;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;








import javax.swing.JOptionPane;

import org.hibernate.Criteria;
import org.hibernate.Query;
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

public class PonudeLogika {
	
	public Klijent dajKlijenta(String ime, String prezime) {
		Session _session= HibernateUtil.getSessionFactory().openSession();
		 Transaction _t = _session.beginTransaction(); 
	
		 Criteria criteria = _session.createCriteria(Osoba.class);
		 List<Osoba> _osobe = (List<Osoba>)criteria.add(Restrictions.eq("imePrezime", ime + " " + prezime)).list();
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
	
	public List<KreditnaPonuda> traziPoImenuKlijenta(String imePrezime) {
		Session _session= HibernateUtil.getSessionFactory().openSession();
		Transaction _t = _session.beginTransaction();
		
		Criteria criteria = _session.createCriteria(Osoba.class);
		List<Osoba> _osobe = (List<Osoba>)criteria.add(Restrictions.eq("imePrezime", imePrezime)).list();
		Klijent _kl = new Klijent();
		List<Klijent> klijenti = new ArrayList<Klijent>();
		for(Osoba _o : _osobe) {
			criteria = _session.createCriteria(Klijent.class);
			_kl = (Klijent) criteria.add(Restrictions.eq("osobaId", _o.getOsobaId())).uniqueResult();
			if(_kl != null) klijenti.add(_kl);			 
		}
		Kredit _kr = new Kredit();
		List<List<Kredit> > _kreditici = new ArrayList<List<Kredit> >();
		List<Kredit> krediti = new ArrayList<Kredit>();
		for(Klijent k : klijenti) {
			criteria = _session.createCriteria(Kredit.class);
			_kreditici.add((List<Kredit>)criteria.add(Restrictions.eq("klijent", k)).list());
		}
		List<KreditnaPonuda> _kp = new ArrayList<KreditnaPonuda>();
		for(List<Kredit> kreditiTmp : _kreditici) {
			for(Kredit kredit : kreditiTmp) {
				_kp.add(new KreditnaPonuda(kredit.getKreditniSluzbenik(), kredit.getKlijent(), kredit.getTipKredita(), kredit.getDatumUpisa()));
			}
		}
		
		_t.commit();
		_session.close();
		return _kp;
	}
	
	public List<KreditnaPonuda> traziPoTipuKredita(String nazivTipa){
		Session _session = HibernateUtil.getSessionFactory().openSession();
		Transaction _t = _session.beginTransaction();
		
		Criteria _criteria = _session.createCriteria(TipKredita.class);
		List<TipKredita> _tipovi = (List<TipKredita>)_criteria.add(Restrictions.eq("naziv", nazivTipa)).list();
		
		List<Kredit> _krediti = new ArrayList<Kredit>();
		Kredit _kredit_ = new Kredit();
		for(TipKredita tk : _tipovi) {
			Criteria criteria = _session.createCriteria(Kredit.class);
			_kredit_ = (Kredit)criteria.add(Restrictions.eq("tipKredita",tk)).uniqueResult();
			_krediti.add(_kredit_);
		}
		List<KreditnaPonuda> _kp = new ArrayList<KreditnaPonuda>();
		for(Kredit kredit : _krediti) {
			_kp.add(new KreditnaPonuda(kredit.getKreditniSluzbenik(), kredit.getKlijent(), kredit.getTipKredita(), kredit.getDatumUpisa()));
		}
		
		_t.commit();
		_session.close();
		return _kp;
	}
	
		public List<KreditnaPonuda> traziPoDatumu(String datum){
			Session _session = HibernateUtil.getSessionFactory().openSession();
			Transaction _t = _session.beginTransaction();
			
			Criteria criteria = _session.createCriteria(Kredit.class);
			List<Kredit> _krediti = (List<Kredit>)criteria.add(Restrictions.eq("datumUpisa", datum)).list();
			List<KreditnaPonuda> _kp = new ArrayList<KreditnaPonuda>();
			for(Kredit kredit : _krediti) {
				_kp.add(new KreditnaPonuda(kredit.getKreditniSluzbenik(), kredit.getKlijent(), kredit.getTipKredita(), kredit.getDatumUpisa()));
			}
			
			_t.commit();
			_session.close();
			return _kp;
		}
	/*
	public Uposlenik dajUposlenika() {
		Session _session= HibernateUtil.getSessionFactory().openSession();
		 Transaction _t = _session.beginTransaction(); 
		 
		 Criteria criteria = _session.createCriteria(Uposlenik.class);
		 Uposlenik _u =(Uposlenik) criteria.add(Restrictions.eq("uposlenikId", 2L)).uniqueResult();
		 
		 _t.commit();
		 _session.close();
		 return _u;
	} */
	public TipKredita dajTipKredita(String naziv) {
		Session _session= HibernateUtil.getSessionFactory().openSession();
		 Transaction _t = _session.beginTransaction(); 
		 
		 Criteria criteria = _session.createCriteria(TipKredita.class);
		 TipKredita _tk =(TipKredita) criteria.add(Restrictions.eq("naziv", naziv)).uniqueResult();
		 
		 _t.commit();
		 _session.close();
		 return _tk;
	} 
	
	public TipKredita dajTipKredita(Long id) {
		Session _session= HibernateUtil.getSessionFactory().openSession();
		 Transaction _t = _session.beginTransaction(); 
		 
		 Criteria criteria = _session.createCriteria(TipKredita.class);
		 TipKredita _tk =(TipKredita) criteria.add(Restrictions.eq("tipKreditaId", id)).uniqueResult();
		 
		 _t.commit();
		 _session.close();
		 return _tk;
	} 
	
	public void dodajPonudu(KreditnaPonuda k){
	Session _session= HibernateUtil.getSessionFactory().openSession();
	Transaction _t = _session.beginTransaction(); 
	
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
	Calendar cal = Calendar.getInstance();
	String _datum = dateFormat.format(cal.getTime());
	Kredit kredit = new Kredit(Spremnik.getTrenutni(), k.getK(), k.getTk(), _datum);
	
	_session.save(kredit);
	_t.commit(); 
	_session.close();
	
}

public Boolean postojiKlijent (String ime, String prezime){
	KlijentLogika _klijentLogika= new KlijentLogika();
	List<KlijentSluzbenik> _klijenti=_klijentLogika.getByName(ime + " " + prezime);
	return !(_klijenti.isEmpty());
}

public String validirajPodatke(String namjena,
		String iznos, String rok, String garancija, String grace, String troskovi, String instrumenti) {
	
	SharedLogika _sharedLogika= new SharedLogika();
	//provjera da li su popunjena sva polja
	if(
		namjena.equals("")||iznos.equals("")||
		rok.equals("")||garancija.equals("")||grace.equals("")||troskovi.equals("") || instrumenti.equals("")
			) 
		return "Nisu popunjena sva polja";
	
	if(!_sharedLogika.validirajIznosKredita(iznos))
		return "Iznos nije validan";
	if(!isNumeric(troskovi))
		return "Troskovi obrade nisu validni";

	return "OK";
}

public void hardDeletePoNecemu(KreditnaPonuda _kp){
	Session _session= HibernateUtil.getSessionFactory().openSession();
	 Transaction _t = _session.beginTransaction(); 
	 
	 Criteria criteria = _session.createCriteria(Kredit.class); 
	Kredit _tk = (Kredit) criteria.add(Restrictions.eq("datumUpisa", _kp.getDatumUpisa())).
			add(Restrictions.eq("klijent.klijentId", _kp.getK().getKlijentId())).
			 add(Restrictions.eq("tipKredita.tipKreditaId", _kp.getTk().getTipKreditaId())).uniqueResult();
	_session.delete(_tk);
	 _t.commit();
	 _session.close();
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