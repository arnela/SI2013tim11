package viewModels;



import javax.swing.table.AbstractTableModel;

import domainModels.TipKredita;

import java.util.ArrayList;
import java.util.List;

public class TipKreditaTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private  List<TipKreditaSluzbenik> li = new ArrayList<TipKreditaSluzbenik>();
	public List<TipKreditaSluzbenik> getLi() {
		return li;
	}
	public void setLi(List<TipKreditaSluzbenik> li) {
		this.li = li;
	}

	private String[] columnNames = { "Naziv", "Namjena", "Iznos",
           "Rok", "kamatnaStopa", "Garancija", "gracePeriod", "troskoviObrade", "instrumentiObezbjedjenja"};

	
	private static final long serialVersionUID = 1L;



	public TipKreditaTableModel(List<TipKreditaSluzbenik> _krediti) {
		this.li=_krediti;
	}
	@Override
    public String getColumnName(int columnIndex){
         return columnNames[columnIndex];
    }
	public int getColumnCount() {
		return 9;
	}
	public int getRowCount() {
		return li.size();
	}
	public Object getValueAt(int rowIndex, int columnIndex) {
		TipKreditaSluzbenik _kredit = li.get(rowIndex);
	        switch (columnIndex) {
	            case 0: 
	                return _kredit.getNaziv();
	            case 1:
	                return _kredit.getNamjena();
	            case 2:
	                return _kredit.getIznos();
	            case 3:
	                return _kredit.getRok();
	            case 4:
	                return _kredit.getKamatnaStopa();
	            case 5:
	                return _kredit.getGarancija();
	            case 6:
	                return _kredit.getGracePeriod();
	            case 7:
	            	return _kredit.getTroskoviObrade();
	            case 8:
	            	return _kredit.getInstrumentiObezbjedjenja();
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
	               return Double.class;
	             case 3:
	               return String.class;
	             case 4:
	               return Double.class;
	             case 5:
	               return String.class;
	             case 6:
	               return String.class;
	             case 7:
	               return Double.class;
	             case 8:
	               return String.class;
	             }
	             return null;
	      }


}
