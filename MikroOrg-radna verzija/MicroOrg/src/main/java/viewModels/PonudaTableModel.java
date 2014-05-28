package viewModels;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import domainModels.HibernateUtil;
import domainModels.Osoba;

import java.util.ArrayList;
import java.util.List;

public class PonudaTableModel extends AbstractTableModel {
        private List<KreditnaPonuda> li = new ArrayList<KreditnaPonuda>();
        
        public List<KreditnaPonuda> getLi() {
            return li;
        }
        public void setLi(List<KreditnaPonuda> li) {
            this.li = li;
        }

        private String[] columnNames = { "Kreditni sluzbenik", "Klijent", "Tip kredita",
            "Datum izdavanja"};
        private static final long serialVersionUID = 1L;

        public PonudaTableModel(List<KreditnaPonuda> _kreditnePonude) {
                this.li = _kreditnePonude;
        }
        @Override
        public String getColumnName(int columnIndex){
            	return columnNames[columnIndex];
        }
        public int getColumnCount() {
        		return 4;
        }
        public int getRowCount() {
            	return li.size();
        }
        public Object getValueAt(int rowIndex, int columnIndex) {
                KreditnaPonuda _kreditnaPonuda = li.get(rowIndex);
                switch (columnIndex) {
                case 0:
                
                        Session _session = HibernateUtil.getSessionFactory().openSession();
                        Transaction _t = _session.beginTransaction();

                        Criteria _criteria = _session.createCriteria(Osoba.class);
                        Osoba _o = (Osoba) _criteria.add(Restrictions.eq("osobaId", _kreditnaPonuda.getU().getOsobaId())).uniqueResult();

                        _t.commit();
                        _session.close();
                        if(_o != null)return _o.getImePrezime();
                        else return null;
                case 1:
                        Session session = HibernateUtil.getSessionFactory().openSession();
                        Transaction t = session.beginTransaction();

                        Criteria criteria = session.createCriteria(Osoba.class);
                        Osoba o = (Osoba) criteria.add(Restrictions.eq("osobaId", _kreditnaPonuda.getK().getOsobaId())).uniqueResult();

                        t.commit();
                        session.close();
                        if(o != null)return o.getImePrezime();
                        else return null;
                case 2:
                        String s = _kreditnaPonuda.getTk().getNaziv() + " " + _kreditnaPonuda.getTk().getNamjena() + " " + Double.toString(_kreditnaPonuda.getTk().getIznos());
                        return s;
                case 3:
                        return _kreditnaPonuda.getDatumUpisa();
                }
                return null;
        }

        @Override
        public Class<?> getColumnClass(int columnIndex){
                switch (columnIndex){
                case 0:
                        return String.class;
                case 1:
                        return String.class;
                case 2:
                        return String.class;
                case 3:
                        return String.class;
                }
                return null;
        }
}
