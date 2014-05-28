package viewModels;

public class TipKreditaSluzbenik {
	
	private String naziv;
	private String namjena;
	private Double iznos;
	private String rok;
	private Double kamatnaStopa;
	private String garancija;
	private String gracePeriod;
	private Double troskoviObrade;

public TipKreditaSluzbenik(){}
public TipKreditaSluzbenik (
		String naziv,
		String namjena,
		Double iznos,
		String rok,
		Double kamatnaStopa,
		String garancija,
		String gracePeriod,
		Double troskoviObrade
		){
		this.setNaziv(naziv);
		this.setNamjena(namjena);
		this.setIznos(iznos);
		this.setRok(rok);
		this.setKamatnaStopa(kamatnaStopa);
		this.setGarancija(garancija);
		this.setGracePeriod(gracePeriod);
		this.setTroskoviObrade(troskoviObrade);
	
}


public String getNaziv() {
	return naziv;
}


public void setNaziv(String naziv) {
	this.naziv = naziv;
}


public String getNamjena() {
	return namjena;
}


public void setNamjena(String namjena) {
	this.namjena = namjena;
}


public Double getIznos() {
	return iznos;
}


public void setIznos(Double iznos) {
	this.iznos = iznos;
}


public String getRok() {
	return rok;
}


public void setRok(String rok) {
	this.rok = rok;
}


public Double getKamatnaStopa() {
	return kamatnaStopa;
}


public void setKamatnaStopa(Double kamatnaStopa) {
	this.kamatnaStopa = kamatnaStopa;
}


public String getGarancija() {
	return garancija;
}


public void setGarancija(String garancija) {
	this.garancija = garancija;
}


public String getGracePeriod() {
	return gracePeriod;
}


public void setGracePeriod(String gracePeriod) {
	this.gracePeriod = gracePeriod;
}


public Double getTroskoviObrade() {
	return troskoviObrade;
}


public void setTroskoviObrade(Double troskoviObrade) {
	this.troskoviObrade = troskoviObrade;
}
}
