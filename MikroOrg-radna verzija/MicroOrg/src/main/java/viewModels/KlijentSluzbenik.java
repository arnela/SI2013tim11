package viewModels;

import java.sql.Date;

public class KlijentSluzbenik {

	private String imePrezime;
	private String jmbg;
	private Date datumRodjenja;
	private String telefon;
	private String adresa;
	private String email;
	private String status;
	
	public KlijentSluzbenik() {
		
	}
	
	public KlijentSluzbenik(
			String imePrezime,
			String jmbg,
			Date datumRodjenja,
			String telefon,
			String adresa,
			String email,
			String status
			){
		this.setImePrezime(imePrezime);
		this.setJmbg(jmbg);
		this.setDatumRodjenja(datumRodjenja);
		this.setTelefon(telefon);
		this.setAdresa(adresa);
		this.setEmail(email);	
		this.setStatus(status);
	}
	public KlijentSluzbenik(
			String imePrezime,
			String jmbg,
			Date datumRodjenja,
			String telefon,
			String adresa,
			String email
			){
		this.setImePrezime(imePrezime);
		this.setJmbg(jmbg);
		this.setDatumRodjenja(datumRodjenja);
		this.setTelefon(telefon);
		this.setAdresa(adresa);
		this.setEmail(email);	
	}
	public String getImePrezime() {
		return imePrezime;
	}

	public void setImePrezime(String imePrezime) {
		this.imePrezime = imePrezime;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public Date getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}


	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
