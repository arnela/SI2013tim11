package viewModels;

import java.sql.Date;

public class KreditniSluzbenik {

	private String imePrezime;
	private String jmbg;
	private Date datumRodjenja;
	private String obrazovanje;
	private String telefon;
	private String adresa;
	private String email;
	private String password;
	private String mjestoRodjenja;
	private Double plata;
	private int brojKredita;
	private int brojTransakcija;
	
	
	public KreditniSluzbenik(
			String imePrezime,
			String jmbg,
			Date datumRodjenja,
			String obrazovanje,
			String telefon,
			String adresa,
			String email

			){
		this.imePrezime= imePrezime;
		this.jmbg=jmbg;
		this.datumRodjenja=datumRodjenja;
		this.obrazovanje=obrazovanje;
		this.telefon=telefon;
		this.adresa=adresa;
		this.email=email;

	}
	public KreditniSluzbenik(
			String imePrezime,
			String jmbg,
			Date datumRodjenja,
			String obrazovanje,
			String telefon,
			String adresa,
			String email,
			String password,
			String mjestoRodjenja,
			Double plata
			){
		this.imePrezime=imePrezime;
		this.jmbg=jmbg;
		this.datumRodjenja=datumRodjenja;
		this.obrazovanje=obrazovanje;
		this.telefon=telefon;
		this.adresa=adresa;
		this.email=email;
		this.password=password;
		this.mjestoRodjenja=mjestoRodjenja;
		this.plata=plata;
	}
	public KreditniSluzbenik() {
		// TODO Auto-generated constructor stub
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
		return this.datumRodjenja;
	}
	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	public String getObrazovanje() {
		return obrazovanje;
	}
	public void setObrazovanje(String obrazovanje) {
		this.obrazovanje = obrazovanje;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMjestoRodjenja() {
		return mjestoRodjenja;
	}
	public void setMjestoRodjenja(String mjestoRodjenja) {
		this.mjestoRodjenja = mjestoRodjenja;
	}
	public Double getPlata() {
		return plata;
	}
	public void setPlata(Double plata) {
		this.plata = plata;
	}
	public int getBrojKredita() {
		return brojKredita;
	}
	public void setBrojKredita(int brojKredita) {
		this.brojKredita = brojKredita;
	}
	public int getBrojTransakcija() {
		return brojTransakcija;
	}
	public void setBrojTransakcija(int brojTransakcija) {
		this.brojTransakcija = brojTransakcija;
	}


}
