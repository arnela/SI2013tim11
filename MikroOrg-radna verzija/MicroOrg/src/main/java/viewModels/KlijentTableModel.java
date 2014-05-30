package viewModels;



import javax.swing.table.AbstractTableModel;

import java.util.ArrayList;
import java.util.List;

public class KlijentTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private  List<KlijentSluzbenik> li = new ArrayList<KlijentSluzbenik>();
	private String[] columnNames = { "imePrezime", "jmbg", "telefon",
            "adresa", "email", "status"};
	private static final long serialVersionUID = 1L;



	public KlijentTableModel(List<KlijentSluzbenik> _klijenti) {
		this.li=_klijenti;
	}
	 
	@Override
    public String getColumnName(int columnIndex){
         return columnNames[columnIndex];
    }
	public int getColumnCount() {
		return 6;
	}
	public int getRowCount() {
		return li.size();
	}
	public Object getValueAt(int rowIndex, int columnIndex) {
		 	KlijentSluzbenik _klijenti = li.get(rowIndex);
	        switch (columnIndex) {
	            case 0: 
	                return _klijenti.getImePrezime();
	            case 1:
	                return _klijenti.getJmbg();
	            case 2:
	                return _klijenti.getTelefon();
	            case 3:
	                return _klijenti.getAdresa();
	            case 4:
	                return _klijenti.getEmail();
	            case 5:
	                return _klijenti.getStatus();
	           
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
	             case 5:
	               return String.class;
	              
	             }
	             return null;
	      }

}
