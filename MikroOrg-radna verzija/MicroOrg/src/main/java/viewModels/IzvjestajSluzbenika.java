package viewModels;

public class IzvjestajSluzbenika {
	private String imePrezimeSluzbenika;
	private String vremenskiPeriod;
	private String datumGenerisanja;
	private int brIzdatihKredita;
	private int brEvidentiranihTransakcija;
	public IzvjestajSluzbenika(){
		
	}
	public IzvjestajSluzbenika(
			String imePrezimeSluzbenika,
			String vremenskiPeriod,
			String datumGenerisanja,
			int brIzdatihKredita,
			int brEvidentiranihTransakcija
			)
	{
		this.imePrezimeSluzbenika=imePrezimeSluzbenika;
		this.vremenskiPeriod=vremenskiPeriod;
		this.datumGenerisanja=datumGenerisanja;
		this.brIzdatihKredita=brIzdatihKredita;
		this.brEvidentiranihTransakcija=brEvidentiranihTransakcija;
	}

	/**
	 * @return the imePrezimeSluzbenika
	 */
	public String getImePrezimeSluzbenika() {
		return imePrezimeSluzbenika;
	}
	/**
	 * @param imePrezimeSluzbenika the imePrezimeSluzbenika to set
	 */
	public void setImePrezimeSluzbenika(String imePrezimeSluzbenika) {
		this.imePrezimeSluzbenika = imePrezimeSluzbenika;
	}
	/**
	 * @return the vremenskiPeriod
	 */
	public String getVremenskiPeriod() {
		return vremenskiPeriod;
	}
	/**
	 * @param vremenskiPeriod the vremenskiPeriod to set
	 */
	public void setVremenskiPeriod(String vremenskiPeriod) {
		this.vremenskiPeriod = vremenskiPeriod;
	}
	/**
	 * @return the datumGenerisanja
	 */
	public String getDatumGenerisanja() {
		return datumGenerisanja;
	}
	/**
	 * @param datumGenerisanja the datumGenerisanja to set
	 */
	public void setDatumGenerisanja(String datumGenerisanja) {
		this.datumGenerisanja = datumGenerisanja;
	}
	/**
	 * @return the brIzdatihKredita
	 */
	public int getBrIzdatihKredita() {
		return brIzdatihKredita;
	}
	/**
	 * @param brIzdatihKredita the brIzdatihKredita to set
	 */
	public void setBrIzdatihKredita(int brIzdatihKredita) {
		this.brIzdatihKredita = brIzdatihKredita;
	}
	/**
	 * @return the brEvidentiranihTransakcija
	 */
	public int getBrEvidentiranihTransakcija() {
		return brEvidentiranihTransakcija;
	}
	/**
	 * @param brEvidentiranihTransakcija the brEvidentiranihTransakcija to set
	 */
	public void setBrEvidentiranihTransakcija(int brEvidentiranihTransakcija) {
		this.brEvidentiranihTransakcija = brEvidentiranihTransakcija;
	}
}
