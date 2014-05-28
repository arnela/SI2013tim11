package viewModels;



import javax.swing.table.AbstractTableModel;

import java.util.ArrayList;
import java.util.List;

public class SluzbenikTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private  List<KreditniSluzbenik> li = new ArrayList<KreditniSluzbenik>();
	public List<KreditniSluzbenik> getLi() {
		return li;
	}
	public void setLi(List<KreditniSluzbenik> li) {
		this.li = li;
	}

	private String[] columnNames = { "Ime", "JMBG", "Telefon",
            "Email", "Adresa", "Krediti", "Transakcije"};
	private static final long serialVersionUID = 1L;



	public SluzbenikTableModel(List<KreditniSluzbenik> _kreditniSluzbenici) {
		this.li=_kreditniSluzbenici;
	}
	@Override
    public String getColumnName(int columnIndex){
         return columnNames[columnIndex];
    }
	public int getColumnCount() {
		return 7;
	}
	public int getRowCount() {
		return li.size();
	}
	public Object getValueAt(int rowIndex, int columnIndex) {
		 	KreditniSluzbenik _kreditniSluzbenik = li.get(rowIndex);
	        switch (columnIndex) {
	            case 0: 
	                return _kreditniSluzbenik.getImePrezime();
	            case 1:
	                return _kreditniSluzbenik.getJmbg();
	            case 2:
	                return _kreditniSluzbenik.getTelefon();
	            case 3:
	                return _kreditniSluzbenik.getEmail();
	            case 4:
	                return _kreditniSluzbenik.getAdresa();
	            case 5:
	                return _kreditniSluzbenik.getBrojKredita();
	            case 6:
	                return _kreditniSluzbenik.getBrojTransakcija();
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
	               return Integer.class;
	             case 6:
	               return Integer.class;
	             }
	             return null;
	      }


}
