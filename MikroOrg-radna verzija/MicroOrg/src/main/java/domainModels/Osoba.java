package domainModels;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;


@Entity 
@Table
public class Osoba implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long osobaId;
	private String imePrezime;
	private String jmbg;
	private String datumRodjenja;
	private String obrazovanje;
	private String telefon;
	private String adresa;
	private String email;
	private Boolean aktivan;
	private String datumUnosa;
	
	public Osoba(){
		
	}
	public Osoba(
			String imePrezime,
			String jmbg,
			String datumRodjenja,
			String obrazovanje,
			String telefon,
			String adresa,
			String email,
			Boolean aktivan,
			String datumUnosa
			){
		this.imePrezime= imePrezime;
		this.jmbg=jmbg;
		this.datumRodjenja=datumRodjenja;
		this.obrazovanje=obrazovanje;
		this.telefon=telefon;
		this.adresa=adresa;
		this.email=email;
		this.aktivan=aktivan;
		this.datumUnosa=datumUnosa;
	}


	/**
	 * @return the imePrezime
	 */
	public String getImePrezime() {
		return imePrezime;
	}
	/**
	 * @param imePrezime the imePrezime to set
	 */
	public void setImePrezime(String imePrezime) {
		this.imePrezime = imePrezime;
	}
	/**
	 * @return the jmbg
	 */
	public String getJmbg() {
		return jmbg;
	}
	/**
	 * @param jmbg the jmbg to set
	 */
	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}
	/**
	 * @return the datumRodjenja
	 */
	public String getDatumRodjenja() {
		return datumRodjenja;
	}
	/**
	 * @param datumRodjenja the datumRodjenja to set
	 */
	public void setDatumRodjenja(String datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	/**
	 * @return the obrazovanje
	 */
	public String getObrazovanje() {
		return obrazovanje;
	}
	/**
	 * @param obrazovanje the obrazovanje to set
	 */
	public void setObrazovanje(String obrazovanje) {
		this.obrazovanje = obrazovanje;
	}
	/**
	 * @return the telefon
	 */
	public String getTelefon() {
		return telefon;
	}
	/**
	 * @param telefon the telefon to set
	 */
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	/**
	 * @return the adresa
	 */
	public String getAdresa() {
		return adresa;
	}
	/**
	 * @param adresa the adresa to set
	 */
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the aktivan
	 */
	public Boolean getAktivan() {
		return aktivan;
	}
	/**
	 * @param aktivan the aktivan to set
	 */
	public void setAktivan(Boolean aktivan) {
		this.aktivan = aktivan;
	}
	/**
	 * @return the datumUnosa
	 */
	public String getDatumUnosa() {
		return datumUnosa;
	}
	/**
	 * @param datumUnosa the datumUnosa to set
	 */
	public void setDatumUnosa(String datumUnosa) {
		this.datumUnosa = datumUnosa;
	}
	public Long getOsobaId() {
		return osobaId;
	}
	public void setOsobaId(Long osobaId) {
		this.osobaId = osobaId;
	}

}
