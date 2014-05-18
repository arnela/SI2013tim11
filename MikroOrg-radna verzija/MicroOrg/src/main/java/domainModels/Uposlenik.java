package domainModels;
import java.io.Serializable; 

import javax.persistence.CascadeType;
import javax.persistence.Entity; 
import javax.persistence.GeneratedValue; 
import javax.persistence.Id; 
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity 
@Table
public class Uposlenik {
	@Id 
	 @GeneratedValue
	private Long uposlenikId;
	private Boolean privilegije;
	private String username;
	private String password;
	private int ukupanBrKredita;
	private int ukupanBrTransakcija;
	@OneToOne(mappedBy="uposlenik", cascade=CascadeType.ALL)
	private Osoba osoba;
	public Uposlenik(
			Boolean privilegije,
			String username,
			String password,
			int ukupanBrKredita,
			int ukupanBrTransakcija,
			Osoba osoba
			)
	{
		this.privilegije=privilegije;
		this.username=username;
		this.password=password;
		this.ukupanBrKredita=ukupanBrKredita;
		this.ukupanBrTransakcija=ukupanBrTransakcija;
		this.osoba=	osoba;
	}
	public Uposlenik(){
		
	}

	/**
	 * @return the privilegije
	 */
	public Boolean getPrivilegije() {
		return privilegije;
	}
	/**
	 * @param privilegije the privilegije to set
	 */
	public void setPrivilegije(Boolean privilegije) {
		this.privilegije = privilegije;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the ukupanBrKredita
	 */
	public int getUkupanBrKredita() {
		return ukupanBrKredita;
	}
	/**
	 * @param ukupanBrKredita the ukupanBrKredita to set
	 */
	public void setUkupanBrKredita(int ukupanBrKredita) {
		this.ukupanBrKredita = ukupanBrKredita;
	}
	/**
	 * @return the ukupanBrTransakcija
	 */
	public int getUkupanBrTransakcija() {
		return ukupanBrTransakcija;
	}
	/**
	 * @param ukupanBrTransakcija the ukupanBrTransakcija to set
	 */
	public void setUkupanBrTransakcija(int ukupanBrTransakcija) {
		this.ukupanBrTransakcija = ukupanBrTransakcija;
	}
	/**
	 * @return the osoba
	 */
	public Osoba getOsoba() {
		return osoba;
	}
	/**
	 * @param osoba the osoba to set
	 */
	public void setOsoba(Osoba osoba) {
		this.osoba = osoba;
	}
	/**
	 * @return the uposlenikId
	 */
	public Long getUposlenikId() {
		return uposlenikId;
	}
	public void setUposlenikId(Long uposlenikId) {
		this.uposlenikId = uposlenikId;
	}

}
