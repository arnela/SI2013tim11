package viewModels;
import java.sql.Date;

import domainModels.Klijent;
import domainModels.TipKredita;
import domainModels.Uposlenik;
public class KreditnaPonuda {
	
	

	private Klijent k;
	private Uposlenik u;
	private TipKredita tk;
	private String datumUpisa;
	
	
	public KreditnaPonuda(){}
	
	public KreditnaPonuda(Uposlenik u1, Klijent k1, TipKredita tk1, String s){
		k=k1;
		u=u1;
		tk=tk1;
		datumUpisa=s;
	}
	public Klijent getK() {
		return k;
	}

	public void setK(Klijent k) {
		this.k = k;
	}

	public Uposlenik getU() {
		return u;
	}

	public void setU(Uposlenik u) {
		this.u = u;
	}

	public TipKredita getTk() {
		return tk;
	}

	public void setTk(TipKredita tk) {
		this.tk = tk;
	}

	public String getDatumUpisa() {
		return datumUpisa;
	}

	public void setDatumUpisa(String datumUpisa) {
		this.datumUpisa = datumUpisa;
	}
}
