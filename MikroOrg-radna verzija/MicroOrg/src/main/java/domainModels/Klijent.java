
package domainModels;

import java.io.Serializable; 

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity; 
import javax.persistence.GeneratedValue; 
import javax.persistence.Id; 
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity 
@Table
public class Klijent{
		/**
	 * 
	 */
	private static final long serialVersionUID = -372582091340246106L;
	private String status;
	@OneToMany(mappedBy="klijent")
    private Set<Kredit> krediti;
	@Id
	@GeneratedValue
	private Long klijentId;
	private Long osobaId;
	@OneToMany(mappedBy="kredit")
    private Set<Transakcija> transakcije;
	public Klijent(){
		
	}
	public Klijent(
		String status,
		Long osobaId
			){
		this.status=status;
		this.osobaId=osobaId;
	}


	/**
	 * @return the kredit
	 */

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the id
	 */
	public Long getKlijentId() {
		return klijentId;
	}
	public void setKlijentId(Long klijentId) {
		this.klijentId = klijentId;
	}
	public Long getOsobaId() {
		return osobaId;
	}
	public void setOsobaId(Long osobaId) {
		this.osobaId = osobaId;
	}
	public Set<Transakcija> getTransakcije() {
		return transakcije;
	}
	public void setTransakcije(Set<Transakcija> transakcije) {
		this.transakcije = transakcije;
	}

}
