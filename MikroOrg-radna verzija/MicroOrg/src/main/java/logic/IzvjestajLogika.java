package logic;

import viewModels.IzvjestajOrganizacije;
import viewModels.IzvjestajSluzbenika;


//import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import aplikacija.MicroOrg.Spremnik;
import domainModels.HibernateUtil;
import domainModels.Kredit;
import domainModels.Osoba;
import domainModels.Transakcija;
import domainModels.Uposlenik;

public class IzvjestajLogika {
/*	public IzvjestajLogika(){}
        public IzvjestajSluzbenika generisiIzvjestajSluzbenika(String period) {
                
                Session _session = HibernateUtil.getSessionFactory().openSession();
                Transaction _t = _session.beginTransaction();

                Criteria criteria = _session.createCriteria(Osoba.class);
                Osoba _o = (Osoba) criteria.add(Restrictions.eq("osobaId", Spremnik.getTrenutni().getOsobaId())).uniqueResult();

                _t.commit();
                _session.close();
                
                String ime = _o.getImePrezime();
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyyhh:mm");
                Calendar cal = Calendar.getInstance();
                String datum = dateFormat.format(cal.getTime());
                int brKredita = Spremnik.getTrenutni().getUkupanBrKredita();
                int brTransakcija = Spremnik.getTrenutni().getUkupanBrTransakcija();
                
                IzvjestajSluzbenika _izvjestaj = new IzvjestajSluzbenika(ime, period, datum, brKredita, brTransakcija);
                return _izvjestaj;
        }*/
		public IzvjestajLogika(){}
	        public IzvjestajSluzbenika generisiIzvjestajSluzbenika(String period) {
	        	
	        	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
	            Calendar cal = Calendar.getInstance();
	            String datum = dateFormat.format(cal.getTime());
	            
	            Date max = cal.getTime();
	            
	            if(period.equals("mjesec")) {
	            	cal.set(Calendar.MONTH, -1);
	            }
	            else if(period.equals("sest mjeseci")) {
	            	cal.set(Calendar.MONTH, -6);
	            }
	            else if(period.equals("sest mjeseci")) {
	            	cal.set(Calendar.YEAR, -1);
	            }
	            
	            Date min = cal.getTime();
	                
	                Session _session = HibernateUtil.getSessionFactory().openSession();
	                Transaction _t = _session.beginTransaction();

	                Criteria criteria = _session.createCriteria(Osoba.class);
	                Osoba _o = (Osoba) criteria.add(Restrictions.eq("osobaId", Spremnik.getTrenutni().getOsobaId())).uniqueResult();
	                
	                criteria = _session.createCriteria(Kredit.class);
	                List<Kredit> krediti = (List<Kredit>) criteria.add(Restrictions.eq("kreditniSluzbenik.uposlenikId", Spremnik.getTrenutni().getUposlenikId())).list();
	                for(Kredit k : krediti){
	                	try {
							Date d = dateFormat.parse(k.getDatumUpisa());
							if(!(d.before(max) && d.after(min))) {
								krediti.remove(k);
							}
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                	catch (Exception er){
	                		er.printStackTrace();
	                	}
	                }
	                int brKredita = krediti.size();
	                
	                criteria = _session.createCriteria(Transakcija.class);
	                List<Transakcija> transakcije = (List<Transakcija>) criteria.add(Restrictions.eq("kreditniSluzbenik.uposlenikId", Spremnik.getTrenutni().getUposlenikId())).list();
	                for(Transakcija t : transakcije){
	                	try {
							Date d = dateFormat.parse(t.getDatumUplate());
							if(!(d.before(max) && d.after(min))) {
								transakcije.remove(t);
							}
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                }
	                int brTransakcija = transakcije.size();
	                
	                _t.commit();
	                _session.close();
	                
	                String ime = _o.getImePrezime();
	                
	                
	                /*int brKredita = Spremnik.getTrenutni().getUkupanBrKredita();
	                int brTransakcija = Spremnik.getTrenutni().getUkupanBrTransakcija();*/
	                
	                IzvjestajSluzbenika _izvjestaj = new IzvjestajSluzbenika(ime, period, datum, brKredita, brTransakcija);
	                return _izvjestaj;
	        }
	        
	        public IzvjestajOrganizacije generisiIzvjestajOrganizacije(String period) {
	        	
	        	
	        	//////////////////////////////////////////////////////////////////////
	        	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
	            Calendar cal = Calendar.getInstance();
	            String datum = dateFormat.format(cal.getTime());
	            
	            Date max = cal.getTime();
	            
	            if(period.equals("mjesec")) {
	            	cal.set(Calendar.MONTH, -1);
	            }
	            else if(period.equals("sest mjeseci")) {
	            	cal.set(Calendar.MONTH, -6);
	            }
	            else if(period.equals("sest mjeseci")) {
	            	cal.set(Calendar.YEAR, -1);
	            }
	            
	            Date min = cal.getTime();
	            //////////////////////////////////////////////////////////////////////////
	            
	            Session _session = HibernateUtil.getSessionFactory().openSession();
	            Transaction _t = _session.beginTransaction();

	            /*Criteria criteria = _session.createCriteria(Osoba.class);
	            Osoba _o = (Osoba) criteria.add(Restrictions.eq("osobaId", Spremnik.getTrenutni().getOsobaId())).uniqueResult();*/
	            
	            Criteria criteria = _session.createCriteria(Uposlenik.class);
	            List<Uposlenik> uposlenici = (List<Uposlenik>) criteria.list();
	            
	            criteria = _session.createCriteria(Kredit.class);
	            List<Kredit> krediti = (List<Kredit>) criteria.list();
	            
	            criteria = _session.createCriteria(Transakcija.class);
	            List<Transakcija> transakcije = (List<Transakcija>) criteria.list();
	            
	            _t.commit();
	            _session.close();
	            
	            //String ime = _o.getImePrezime();
	            /*DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyyhh:mm");
	            Calendar cal = Calendar.getInstance();
	            String datum = dateFormat.format(cal.getTime());*/
	            //int brKredita = Spremnik.getTrenutni().getUkupanBrKredita();
	            //int brTransakcija = Spremnik.getTrenutni().getUkupanBrTransakcija();
	            
	            String organizacija = "MicroOrg";
	            int brojUposlenih = uposlenici.size();
	            String vremenskiPeriod = period;
	            
	            
	            //////////////////////////////////////////////////////////////////////
	            for(Transakcija t : transakcije){
                	try {
						Date d = dateFormat.parse(t.getDatumUplate());
						if(!(d.before(max) && d.after(min))) {
							transakcije.remove(t);
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
	            
	            for(Kredit k : krediti){
                	try {
						Date d = dateFormat.parse(k.getDatumUpisa());
						if(!(d.before(max) && d.after(min))) {
							krediti.remove(k);
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
	            ///////////////////////////////////////////////////////////////////
	            
	            
	            
	            int brKredita = krediti.size();
	            int brTransakcija = transakcije.size();
	            
	            int iznos = 0;
	            for(Kredit k : krediti) {
	            	iznos += k.getTipKredita().getIznos();
	            }
	            
	            IzvjestajOrganizacije _izvjestaj = new IzvjestajOrganizacije(organizacija, brojUposlenih, datum, vremenskiPeriod, brKredita, brTransakcija, iznos);
	            return _izvjestaj;
	    }
	}
