package viewModels;

import domainModels.Klijent;
import domainModels.Kredit;
import domainModels.Uposlenik;



public class Transakcija {
	
	private String datumUplate;
	private Double iznosUplate;
	private String nacinUplate;
	private KlijentSluzbenik klijent;
	private KreditnaPonuda kredit;
	private Uposlenik u;
	
	public Transakcija(){
		
	}
	
	public Transakcija(
			String datumUplate, 
			Double iznosUplate, 
			String nacinUplate, 
			KlijentSluzbenik klijent, 
			KreditnaPonuda kredit,
			Uposlenik u){
		this.setDatumUplate(datumUplate);
		this.setIznosUplate(iznosUplate);
		this.setNacinUplate(nacinUplate);
		this.setKlijentSluzbenik(klijent);
		this.setKreditnaPonuda(kredit);
		this.setUposlenik(u);
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

	public KlijentSluzbenik getKlijentSluzbenik() {
		return klijent;
	}

	public void setKlijentSluzbenik(KlijentSluzbenik klijent) {
		this.klijent = klijent;
	}

	public KreditnaPonuda getKreditnaPonuda() {
		return kredit;
	}

	public void setKreditnaPonuda(KreditnaPonuda kredit) {
		this.kredit = kredit;
	}

	public Uposlenik getUposlenik() {
		return u;
	}

	public void setUposlenik(Uposlenik u) {
		this.u = u;
	}

	

	

}
