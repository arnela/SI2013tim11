package domainModels;

public class Kredit {
	private int kreditId;
	private Uposlenik kreditniSluzbenik;
	private Klijent klijent;
	private TipKredita tipKredita;
	private String datumUpisa;
	public Kredit(){
		
	}
	public Kredit(
			int kreditId,
			Uposlenik kreditniSluzbenik,
			Klijent klijent,
			TipKredita tipKredita,
			String datumUpisa
			){
		this.kreditId=kreditId;
		this.kreditniSluzbenik=kreditniSluzbenik;
		this.klijent=klijent;
		this.tipKredita=tipKredita;
		this.datumUpisa=datumUpisa;
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
	public int getKreditId() {
		return kreditId;
	}
	/**
	 * @param kreditId the kreditId to set
	 */
	public void setKreditId(int kreditId) {
		this.kreditId = kreditId;
	}
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
}
