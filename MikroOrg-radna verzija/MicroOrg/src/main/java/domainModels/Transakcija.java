package domainModels;

public class Transakcija {
	private int transakcijaId;
	private Klijent klijent;
	private Kredit kredit;
	private Uposlenik kreditniSluzbenik;
	private Double iznosUplate;
	private String datumUplate;
	//gotovina ili uplatnica iz banke
	private String nacinUplate;
	private int redniBrUplate;
	public Transakcija(){
		
	}
	public Transakcija(
			int transakcijaId,
			Klijent klijent,
			Kredit kredit,
			Double iznosUplate,
			String datumUplate,
			int redniBrUplate,
			Uposlenik kreditniSluzbenik,
			String nacinUplate
			)
	{
		this.transakcijaId=transakcijaId;
		this.klijent=klijent;
		this.kredit=kredit;
		this.iznosUplate=iznosUplate;
		this.datumUplate=datumUplate;
		this.redniBrUplate=redniBrUplate;
		this.kreditniSluzbenik=kreditniSluzbenik;
		this.nacinUplate=nacinUplate;
	}
	public Transakcija(
			Klijent klijent,
			Kredit kredit,
			Double iznosUplate,
			String datumUplate,
			int redniBrUplate,
			Uposlenik kreditniSluzbenik,
			String nacinUplate
			)
	{
		this.klijent=klijent;
		this.kredit=kredit;
		this.iznosUplate=iznosUplate;
		this.datumUplate=datumUplate;
		this.redniBrUplate=redniBrUplate;
		this.kreditniSluzbenik=kreditniSluzbenik;
		this.nacinUplate=nacinUplate;
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
	 * @return the iznosUplate
	 */
	public Double getIznosUplate() {
		return iznosUplate;
	}
	/**
	 * @param iznosUplate the iznosUplate to set
	 */
	public void setIznosUplate(Double iznosUplate) {
		this.iznosUplate = iznosUplate;
	}
	/**
	 * @return the datumUplate
	 */
	public String getDatumUplate() {
		return datumUplate;
	}
	/**
	 * @param datumUplate the datumUplate to set
	 */
	public void setDatumUplate(String datumUplate) {
		this.datumUplate = datumUplate;
	}
	/**
	 * @return the redniBrUplate
	 */
	public int getRedniBrUplate() {
		return redniBrUplate;
	}
	/**
	 * @param redniBrUplate the redniBrUplate to set
	 */
	public void setRedniBrUplate(int redniBrUplate) {
		this.redniBrUplate = redniBrUplate;
	}
	/**
	 * @return the transakcijaId
	 */
	public int getTransakcijaId() {
		return transakcijaId;
	}
	/**
	 * @param transakcijaId the transakcijaId to set
	 */
	public void setTransakcijaId(int transakcijaId) {
		this.transakcijaId = transakcijaId;
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
	 * @return the nacinUplate
	 */
	public String getNacinUplate() {
		return nacinUplate;
	}
	/**
	 * @param nacinUplate the nacinUplate to set
	 */
	public void setNacinUplate(String nacinUplate) {
		this.nacinUplate = nacinUplate;
	}
}
