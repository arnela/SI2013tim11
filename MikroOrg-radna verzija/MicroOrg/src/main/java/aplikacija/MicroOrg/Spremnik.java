package aplikacija.MicroOrg;

import javax.swing.JFrame;

import domainModels.Uposlenik;

public class Spremnik {

//logovani korisnik	
private static Uposlenik trenutni;
//PDF fajl 
private static Object objekatPDF;
//ekrani
private static JFrame pocetni;
private static JFrame klijenti;
private static JFrame krediti;
private static JFrame ponude;
private static JFrame poslovanje;
private static JFrame transakcije;
private static JFrame uposlenici;
private static JFrame izvjestaji;



public Spremnik(){};
public static Uposlenik getTrenutni() {
	return trenutni;
}

public static void setTrenutni(Uposlenik trenutni) {
	Spremnik.trenutni = trenutni;
}
public static Object getObjekatPDF() {
	return objekatPDF;
}
public static void setObjekatPDF(Object objekatPDF) {
	Spremnik.objekatPDF = objekatPDF;
}
public static JFrame getPocetni() {
	return pocetni;
}
public static void setPocetni(JFrame pocetni) {
	Spremnik.pocetni = pocetni;
}
public static JFrame getKlijenti() {
	return klijenti;
}
public static void setKlijenti(JFrame klijenti) {
	Spremnik.klijenti = klijenti;
}
public static JFrame getKrediti() {
	return krediti;
}
public static void setKrediti(JFrame krediti) {
	Spremnik.krediti = krediti;
}
public static JFrame getPonude() {
	return ponude;
}
public static void setPonude(JFrame ponude) {
	Spremnik.ponude = ponude;
}
public static JFrame getPoslovanje() {
	return poslovanje;
}
public static void setPoslovanje(JFrame poslovanje) {
	Spremnik.poslovanje = poslovanje;
}
public static JFrame getTransakcije() {
	return transakcije;
}
public static void setTransakcije(JFrame transakcije) {
	Spremnik.transakcije = transakcije;
}
public static JFrame getUposlenici() {
	return uposlenici;
}
public static void setUposlenici(JFrame uposlenici) {
	Spremnik.uposlenici = uposlenici;
}
public static JFrame getIzvjestaji() {
	return izvjestaji;
}
public static void setIzvjestaji(JFrame izvjestaji) {
	Spremnik.izvjestaji = izvjestaji;
}

}