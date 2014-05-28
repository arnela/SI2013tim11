package viewModels;



import javax.swing.table.AbstractTableModel;

import java.util.ArrayList;
import java.util.List;

public class TransakcijaTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private  List<Transakcija> li = new ArrayList<Transakcija>();
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
		 	Transakcija _transakcije = li.get(rowIndex);
	        switch (columnIndex) {
	            case 0: 
	                return _transakcije.getKlijent();
	            case 1:
	                return _transakcije.getKredit();
	            case 2:
	                return _transakcije.getIznosUplate();
	            case 3:
	                return _transakcije.getNacinUplate();
	            case 4:
	                return _transakcije.getDatumUplate();
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
	             case 4:
	               return String.class;
	             }
	          
	          return null;
	      }

}
