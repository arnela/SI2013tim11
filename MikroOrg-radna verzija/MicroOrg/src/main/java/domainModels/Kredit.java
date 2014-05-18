package domainModels;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table
public class Kredit {
		@Id
	    @GeneratedValue
	private Long kreditId;
		@ManyToOne
	    @JoinColumn(name="uposlenikId")
	private Uposlenik kreditniSluzbenik;
		@ManyToOne
	    @JoinColumn(name="klijentId")
	private Klijent klijent;
		@ManyToOne
	    @JoinColumn(name="tipKreditaId")
	private TipKredita tipKredita;
	private String datumUpisa;
	@OneToMany(mappedBy="kredit")
    private Set<Transakcija> transakcije;
	public Kredit(){
		
	}
	public Kredit(

			Uposlenik kreditniSluzbenik,
			Klijent klijent,
			TipKredita tipKredita,
			String datumUpisa
			){

		this.kreditniSluzbenik=kreditniSluzbenik;
		this.klijent=klijent;
		this.tipKredita=tipKredita;
		this.datumUpisa=datumUpisa;
	}


	/**
	 * @return the kreditniSluzbenik
	 */
	public Uposlenik getKreditniSluzbenik() {
		return kreditniSluzbenik;
	}
	/**
	 * @param kreditniSluzbenik the kreditniSluzbenik to set
	 */
	public void setKreditniSluzbenik(Uposlenik kreditniSluzbenik) {
		this.kreditniSluzbenik = kreditniSluzbenik;
	}
	/**
	 * @return the klijent
	 */
	public Klijent getKlijent() {
		return klijent;
	}
	/**
	 * @param klijent the klijent to set
	 */
	public void setKlijent(Klijent klijent) {
		this.klijent = klijent;
	}
	/**
	 * @return the tipKredita
	 */
	public TipKredita getTipKredita() {
		return tipKredita;
	}
	/**
	 * @param tipKredita the tipKredita to set
	 */
	public void setTipKredita(TipKredita tipKredita) {
		this.tipKredita = tipKredita;
	}
	/**
	 * @return the kreditId
	 */

	/**
	 * @return the datumUpisa
	 */
	public String getDatumUpisa() {
		return datumUpisa;
	}
	/**
	 * @param datumUpisa the datumUpisa to set
	 */
	public void setDatumUpisa(String datumUpisa) {
		this.datumUpisa = datumUpisa;
	}
	public Long getKreditId() {
		return kreditId;
	}
	public void setKreditId(Long kreditId) {
		this.kreditId = kreditId;
	}
}
