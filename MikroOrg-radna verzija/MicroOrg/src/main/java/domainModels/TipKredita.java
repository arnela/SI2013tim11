package domainModels;

import java.io.Serializable; 
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity; 
import javax.persistence.GeneratedValue; 
import javax.persistence.Id; 
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
@Entity
@Table
public class TipKredita {
	@Id
	@GeneratedValue
	private Long tipKreditaId;
	private String naziv;
	private String namjena;
	private Double iznos;
	private String rok;
	private Double kamatnaStopa;
	private String garancija;
	private String instrumentiObezbjedjenja;
	private String gracePeriod;
	private Double troskoviObrade;
	@OneToMany(mappedBy="tipKredita")
    private Set<Kredit> krediti;
	public TipKredita(){
		
	}
	public TipKredita(

			String naziv,
			String namjena,
			Double iznos,
			String rok,
			Double kamatnaStopa,
			String garancija,
			String instrumentiObezbjedjenja,
			String gracePeriod,
			Double troskoviObrade
			)
	{
		this.naziv=naziv;
		this.namjena=namjena;
		this.iznos=iznos;
		this.rok=rok;
		this.kamatnaStopa=kamatnaStopa;
		this.garancija=garancija;
		this.instrumentiObezbjedjenja=instrumentiObezbjedjenja;
		this.gracePeriod=gracePeriod;
		this.troskoviObrade=troskoviObrade;
	}


	/**
	 * @return the naziv
	 */
	public String getNaziv() {
		return naziv;
	}
	/**
	 * @param naziv the naziv to set
	 */
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	/**
	 * @return the namjena
	 */
	public String getNamjena() {
		return namjena;
	}
	/**
	 * @param namjena the namjena to set
	 */
	public void setNamjena(String namjena) {
		this.namjena = namjena;
	}
	/**
	 * @return the iznos
	 */
	public Double getIznos() {
		return iznos;
	}
	/**
	 * @param iznos the iznos to set
	 */
	public void setIznos(Double iznos) {
		this.iznos = iznos;
	}
	/**
	 * @return the rok
	 */
	public String getRok() {
		return rok;
	}
	/**
	 * @param rok the rok to set
	 */
	public void setRok(String rok) {
		this.rok = rok;
	}
	/**
	 * @return the kamatnaStopa
	 */
	public Double getKamatnaStopa() {
		return kamatnaStopa;
	}
	/**
	 * @param kamatnaStopa the kamatnaStopa to set
	 */
	public void setKamatnaStopa(Double kamatnaStopa) {
		this.kamatnaStopa = kamatnaStopa;
	}
	/**
	 * @return the garancija
	 */
	public String getGarancija() {
		return garancija;
	}
	/**
	 * @param garancija the garancija to set
	 */
	public void setGarancija(String garancija) {
		this.garancija = garancija;
	}
	/**
	 * @return the instrumentiObezbjedjenja
	 */
	public String getInstrumentiObezbjedjenja() {
		return instrumentiObezbjedjenja;
	}
	/**
	 * @param instrumentiObezbjedjenja the instrumentiObezbjedjenja to set
	 */
	public void setInstrumentiObezbjedjenja(String instrumentiObezbjedjenja) {
		this.instrumentiObezbjedjenja = instrumentiObezbjedjenja;
	}
	/**
	 * @return the gracePeriod
	 */
	public String getGracePeriod() {
		return gracePeriod;
	}
	/**
	 * @param gracePeriod the gracePeriod to set
	 */
	public void setGracePeriod(String gracePeriod) {
		this.gracePeriod = gracePeriod;
	}
	/**
	 * @return the troskoviObrade
	 */
	public Double getTroskoviObrade() {
		return troskoviObrade;
	}
	/**
	 * @param troskoviObrade the troskoviObrade to set
	 */
	public void setTroskoviObrade(Double troskoviObrade) {
		this.troskoviObrade = troskoviObrade;
	}
	/**
	 * @return the tipKreditaId
	 */
	public Long getTipKreditaId() {
		return tipKreditaId;
	}
	/**
	 * @param tipKreditaId the tipKreditaId to set
	 */
	public void setTipKreditaId(Long tipKreditaId) {
		this.tipKreditaId = tipKreditaId;
	}
}
