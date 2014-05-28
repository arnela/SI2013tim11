package viewModels;

import domainModels.Klijent;
import domainModels.Kredit;



public class Transakcija {
	
	private String datumUplate;
	private Double iznosUplate;
	private String nacinUplate;
	private Klijent klijent;
	private Kredit kredit;
	
	public Transakcija(){
		
	}
	
	public Transakcija(
			String datumUplate, 
			Double iznosUplate, 
			String nacinUplate, 
			Klijent klijent, 
			Kredit kredit){
		this.setDatumUplate(datumUplate);
		this.setIznosUplate(iznosUplate);
		this.setNacinUplate(nacinUplate);
		this.setKlijent(klijent);
		this.setKredit(kredit);
	}

	public String getDatumUplate() {
		return datumUplate;
	}

	public void setDatumUplate(String datumUplate) {
		this.datumUplate = datumUplate;
	}

	public Double getIznosUplate() {
		return iznosUplate;
	}

	public void setIznosUplate(Double iznosUplate) {
		this.iznosUplate = iznosUplate;
	}

	public String getNacinUplate() {
		return nacinUplate;
	}

	public void setNacinUplate(String nacinUplate) {
		this.nacinUplate = nacinUplate;
	}

	public Klijent getKlijent() {
		return klijent;
	}

	public void setKlijent(Klijent klijent) {
		this.klijent = klijent;
	}

	public Kredit getKredit() {
		return kredit;
	}

	public void setKredit(Kredit kredit) {
		this.kredit = kredit;
	}

	

	

}
