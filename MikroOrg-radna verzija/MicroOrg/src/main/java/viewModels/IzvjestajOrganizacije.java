package viewModels;

public class IzvjestajOrganizacije {
	private String nazivOrganizacije;
	private int brojUposlenih;
	private String datumGenerisanja;
	private String vremenskiPeriod;
	private int brojKredita;
	private int brojTransakcija;
	private int kolicinaIzdatogNovca;
	public IzvjestajOrganizacije(){
		
	}
	public IzvjestajOrganizacije(
			String nazivOrganizacije,
			int brojUposlenih,
			String datumGenerisanja,
			String vremenskiPeriod,
			int brojKredita,
			int brojTransakcija,
			int kolicinaIzdatogNovca
			)
	{
		this.nazivOrganizacije=nazivOrganizacije;
		this.brojUposlenih=brojUposlenih;
		this.datumGenerisanja=datumGenerisanja;
		this.vremenskiPeriod=vremenskiPeriod;
		this.brojKredita=brojKredita;
		this.brojTransakcija=brojTransakcija;
		this.kolicinaIzdatogNovca=kolicinaIzdatogNovca;
	}
	/**
	 * @return the nazivOrganizacije
	 */
	public String getNazivOrganizacije() {
		return nazivOrganizacije;
	}
	/**
	 * @param nazivOrganizacije the nazivOrganizacije to set
	 */
	public void setNazivOrganizacije(String nazivOrganizacije) {
		this.nazivOrganizacije = nazivOrganizacije;
	}
	/**
	 * @return the brojUposlenih
	 */
	public int getBrojUposlenih() {
		return brojUposlenih;
	}
	/**
	 * @param brojUposlenih the brojUposlenih to set
	 */
	public void setBrojUposlenih(int brojUposlenih) {
		this.brojUposlenih = brojUposlenih;
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
	 * @return the brojKredita
	 */
	public int getBrojKredita() {
		return brojKredita;
	}
	/**
	 * @param brojKredita the brojKredita to set
	 */
	public void setBrojKredita(int brojKredita) {
		this.brojKredita = brojKredita;
	}
	/**
	 * @return the brojTransakcija
	 */
	public int getBrojTransakcija() {
		return brojTransakcija;
	}
	/**
	 * @param brojTransakcija the brojTransakcija to set
	 */
	public void setBrojTransakcija(int brojTransakcija) {
		this.brojTransakcija = brojTransakcija;
	}
	/**
	 * @return the kolicinaIzdatogNovca
	 */
	public int getKolicinaIzdatogNovca() {
		return kolicinaIzdatogNovca;
	}
	/**
	 * @param kolicinaIzdatogNovca the kolicinaIzdatogNovca to set
	 */
	public void setKolicinaIzdatogNovca(int kolicinaIzdatogNovca) {
		this.kolicinaIzdatogNovca = kolicinaIzdatogNovca;
	}
}
