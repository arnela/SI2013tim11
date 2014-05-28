package aplikacija.MicroOrg;

import domainModels.Uposlenik;

public class Spremnik {
private static Uposlenik trenutni;
private static Object objekatPDF;
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

}