package logic;

import viewModels.IzvjestajSluzbenika;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import aplikacija.MicroOrg.Spremnik;
import domainModels.HibernateUtil;
import domainModels.Osoba;

public class IzvjestajLogika {
	public IzvjestajLogika(){}
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
        }
}
