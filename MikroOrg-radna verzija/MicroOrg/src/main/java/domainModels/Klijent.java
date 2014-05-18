
package domainModels;

import java.io.Serializable; 

import javax.persistence.Entity; 
import javax.persistence.GeneratedValue; 
import javax.persistence.Id; 
import javax.persistence.Table;
import java.sql.Date;

import javax.persistence.Column;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity 
@Table
public class Klijent implements Serializable {
		/**
	 * 
	 */
	private static final long serialVersionUID = -372582091340246106L;
		@Id 
	 @GeneratedValue
	private Long id;
	private Kredit kredit;
	private String status;
	public Klijent(){
		
	}
	public Klijent(
		Long id,
		Kredit kredit,
		String status){
		this.id=id;
		this.kredit=kredit;
		this.status=status;
	}
	public Klijent(Kredit kredit, String status){
		this.kredit=kredit;
		this.status=status;
	}

	/**
	 * @return the kredit
	 */
	public Kredit getKredit() {
		return kredit;
	}
	/**
	 * @param kredit the kredit to set
	 */
	public void setKredit(Kredit kredit) {
		this.kredit = kredit;
	}
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
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
}
