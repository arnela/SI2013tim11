package domainModels;
import java.io.Serializable; 
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity; 
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue; 
import javax.persistence.Id; 
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity 
@Table
public class Uposlenik implements Serializable{
	/**
	 * 
	 */
	@Id
	@GeneratedValue
	private Long uposlenikId;
	private static final Long serialVersionUID = 1L;
	private Boolean privilegije;
	private String username;
	private String password;
	private int ukupanBrKredita;
	private int ukupanBrTransakcija;
	private Long osobaId;
	@OneToMany(mappedBy="kreditniSluzbenik" , targetEntity = Kredit.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Kredit> krediti;
	
	@OneToMany(mappedBy="kreditniSluzbenik" , targetEntity = Transakcija.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Transakcija> transakcije;
	public Uposlenik(
			Boolean privilegije,
			String username,
			String password,
			int ukupanBrKredita,
			int ukupanBrTransakcija,
			Long osobaId
			)
	{
		this.privilegije=privilegije;
		this.username=username;
		this.password=password;
		this.ukupanBrKredita=ukupanBrKredita;
		this.ukupanBrTransakcija=ukupanBrTransakcija;
		this.osobaId=osobaId;
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

	public Set<Kredit> getKrediti() {
		return krediti;
	}
	public void setKrediti(Set<Kredit> krediti) {
		this.krediti = krediti;
	}
	public Long getOsobaId() {
		return osobaId;
	}
	public void setOsobaId(Long osobaId) {
		this.osobaId = osobaId;
	}
	public Long getUposlenikId() {
		return uposlenikId;
	}
	public void setUposlenikId(Long uposlenikId) {
		this.uposlenikId = uposlenikId;
	}

}
