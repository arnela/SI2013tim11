package viewModels;



import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import domainModels.HibernateUtil;
import domainModels.Kredit;
import domainModels.Osoba;
import domainModels.TipKredita;

import java.util.ArrayList;
import java.util.List;

public class TransakcijaTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private  List<Transakcija> li = new ArrayList<Transakcija>();
	

    public List<Transakcija> getLi() {
        return li;
    }
    public void setLi(List<Transakcija> li) {
        this.li = li;
    }
	
	private String[] columnNames = { "Klijent", "Kredit", "Iznos uplate",
            "Nacin uplate", "Datum uplate"};
	
	private static final long serialVersionUID = 1L;



	public TransakcijaTableModel(List<Transakcija> _transakcije) {
		this.li=_transakcije;
	}
	@Override
    public String getColumnName(int columnIndex){
         return columnNames[columnIndex];
    }
	public int getColumnCount() {
		return 5;
	}
	public int getRowCount() {
		return li.size();
	}
	public Object getValueAt(int rowIndex, int columnIndex) {
		 	//try{
			Transakcija _transakcije = li.get(rowIndex);
		 	
		 	
			switch (columnIndex) {
	            case 0: 
	            	 Session _session = HibernateUtil.getSessionFactory().openSession();
                     Transaction _t = _session.beginTransaction();
                     
                     Criteria _criteria = _session.createCriteria(Osoba.class);
                     Osoba _o = (Osoba) _criteria.add(Restrictions.eq("osobaId", _transakcije.getKlijent().getOsobaId())).uniqueResult();
                     _t.commit();
                     _session.close();
                     if(_o != null)return _o.getImePrezime();
                     else return null;
	            	
	            case 1:
	            	Session session = HibernateUtil.getSessionFactory().openSession();
                    Transaction t = session.beginTransaction();
                    
                    Criteria criteria = session.createCriteria(Kredit.class);
                    Kredit _k = (Kredit) criteria.add(Restrictions.eq("tipKredita", _transakcije.getKredit().getTipKredita())).uniqueResult();
                    
                    criteria = session.createCriteria(TipKredita.class);
                    TipKredita _tk = (TipKredita) criteria.add(Restrictions.eq("naziv", _transakcije.getKredit().getTipKredita().getNaziv())).uniqueResult();
                    if(_tk != null)return _tk.getNaziv();
                    else return null;
                    
                    
	            case 2:
	                return _transakcije.getIznosUplate();
	            case 3:
	                return _transakcije.getNacinUplate();
	            case 4:
	                return _transakcije.getDatumUplate();
	           }
	//}catch(Exception e1) {JOptionPane.showMessageDialog(null, e1);}
	           
	        
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
	             case 4:
	               return String.class;
	             }
	          
	          return null;
	      }

}
