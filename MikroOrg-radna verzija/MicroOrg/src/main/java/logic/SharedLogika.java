package logic;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Spring;

import aplikacija.MicroOrg.Spremnik;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;

import domainModels.Klijent;
import domainModels.Kredit;
import domainModels.Transakcija;
import domainModels.Uposlenik;
import viewModels.*;

import java.util.*;
import java.util.regex.Pattern;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SharedLogika {
	//implementirano
	public void generisiPDF(IzvjestajSluzbenika novi){
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		try {
            PdfWriter.getInstance(document,new FileOutputStream("target/IzvjestajSluzbenika"+novi.getDatumGenerisanja()+".pdf"));
          //SADRZAJ
            document.open();
            //zaglavlje dokumenta

            document.addAuthor("MicroOrg");
            document.addCreationDate();
            document.addLanguage("EN");

            document.add(new Paragraph("MicroOrg - Silaboration team",new Font(Font.FontFamily.HELVETICA  , 5, Font.BOLD)));
            document.add(new Paragraph("________________________________________________________________________"
            		+ "_________________________________________________________________________________"
            		+ "_________________________",new Font(Font.FontFamily.HELVETICA  , 5, Font.BOLD)));
            
            Paragraph naslov=new Paragraph("\n Izvještaj kreditnog službenika",new Font(Font.FontFamily.HELVETICA  , 18, Font.BOLD));
            naslov.setAlignment(Element.ALIGN_CENTER);
            document.add(naslov);
            Font pisanje=new Font(Font.FontFamily.HELVETICA  , 14, Font.NORMAL);
            
            document.add(new Chunk("\n",pisanje));        
            
            UposlenikLogika ulogika=new UposlenikLogika(); 
            
            document.add(new Chunk("\n Ime i Prezime: "+novi.getImePrezimeSluzbenika(),pisanje)); 
            document.add(new Chunk("\n Datum generisanja izvještaja: "+novi.getDatumGenerisanja(),pisanje)); 
            document.add(new Chunk("\n Za vremenski period: "+novi.getVremenskiPeriod(),pisanje));
            document.add(new Chunk("\n Broj izdatih kredita: "+novi.getBrIzdatihKredita(),pisanje));
            document.add(new Chunk("\n Broj evidentiranih transakcija: "+novi.getBrEvidentiranihTransakcija(),pisanje));
            Font pisanje2=new Font(Font.FontFamily.HELVETICA  , 14, Font.ITALIC);
            //spisak svih izdatih kredita i svih evidentiranih transakcija... nije implementirano...
            document.add(new Phrase("\n \n Ovim dokumentom je potvrđena uplata date transakcije za dati kredit, te je odobreno korištenje navedenih podataka u slične svrhe! \n \n \n \n \n  \n \n \n \n \n ",pisanje2)); //tekstualne fraze

            Paragraph potpis=new Paragraph("__________________________________________  \n \n Potpis izdavača ",new Font(Font.FontFamily.HELVETICA  , 5, Font.BOLD));
            potpis.setAlignment(Element.ALIGN_RIGHT);
            document.add(potpis);
            Paragraph footer= new Paragraph("________________________________________________________________________"
            		+ "_________________________________________________________________________________"
            		+ "_________________________",new Font(Font.FontFamily.HELVETICA  , 5, Font.BOLD));
            footer.setAlignment(Element.ALIGN_BASELINE);
            document.add(footer);
            //KRAJ
            document.close();

            //kreiramo objekat myFile koji ćemo smjestit u trenutni objekat u spremniku
            File myFile=new File( "target/IzvjestajSluzbenika"+novi.getDatumGenerisanja()+".pdf");
            Spremnik.setObjekatPDF(myFile);
            
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
		
	}
	//implementirano
	public void generisiPDF(IzvjestajOrganizacije novi){
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		try {
            PdfWriter.getInstance(document,new FileOutputStream("target/IzvjestajOrganizacije"+novi.getDatumGenerisanja()+".pdf"));
            //SADRZAJ
            document.open();
            //zaglavlje dokumenta

            document.addAuthor("MicroOrg");
            document.addCreationDate();
            document.addLanguage("EN");

            document.add(new Paragraph("MicroOrg - Silaboration team",new Font(Font.FontFamily.HELVETICA  , 5, Font.BOLD)));
            document.add(new Paragraph("________________________________________________________________________"
            		+ "_________________________________________________________________________________"
            		+ "_________________________",new Font(Font.FontFamily.HELVETICA  , 5, Font.BOLD)));
            
            Paragraph naslov=new Paragraph("\n Izvještaj poslovanja organizacije",new Font(Font.FontFamily.HELVETICA  , 18, Font.BOLD));
            naslov.setAlignment(Element.ALIGN_CENTER);
            document.add(naslov);
            Font pisanje=new Font(Font.FontFamily.HELVETICA  , 14, Font.NORMAL);
            
            document.add(new Chunk("\n",pisanje));        
            
            UposlenikLogika ulogika=new UposlenikLogika(); 
            
            document.add(new Chunk("\n Naziv: "+novi.getNazivOrganizacije(),pisanje)); 
            document.add(new Chunk("\n Datum generisanja izvještaja: "+novi.getDatumGenerisanja(),pisanje)); 
            document.add(new Chunk("\n Za vremenski period: "+novi.getVremenskiPeriod(),pisanje));
            document.add(new Chunk("\n Broj kredita koji su aktivni: "+novi.getBrojKredita(),pisanje));
            document.add(new Chunk("\n Broj transakcija: "+novi.getBrojTransakcija(),pisanje));
            document.add(new Chunk("\n Količina izdatog novca: "+novi.getKolicinaIzdatogNovca(),pisanje));
            document.add(new Chunk("\n Broj trenutnih uposlenika: "+novi.getBrojUposlenih(),pisanje));
    
            Font pisanje2=new Font(Font.FontFamily.HELVETICA  , 14, Font.ITALIC);
            //spisak svih izdatih kredita i svih evidentiranih transakcija... nije implementirano...
            document.add(new Phrase("\n \n Ovim dokumentom je potvrđena uplata date transakcije za dati kredit, te je odobreno korištenje navedenih podataka u slične svrhe! \n \n \n \n \n  \n \n \n \n \n ",pisanje2)); //tekstualne fraze
            
            Paragraph potpis=new Paragraph("__________________________________________  \n \n Potpis izdavača ",new Font(Font.FontFamily.HELVETICA  , 5, Font.BOLD));
            potpis.setAlignment(Element.ALIGN_RIGHT);
            document.add(potpis);
            Paragraph footer= new Paragraph("________________________________________________________________________"
            		+ "_________________________________________________________________________________"
            		+ "_________________________",new Font(Font.FontFamily.HELVETICA  , 5, Font.BOLD));
            footer.setAlignment(Element.ALIGN_BASELINE);
            document.add(footer);
            //KRAJ
            document.close();
            
            //kreiramo objekat myFile koji ćemo smjestit u trenutni objekat u spremniku
            File myFile=new File( "target/IzvjestajOrganizacije"+novi.getDatumGenerisanja()+".pdf");
            Spremnik.setObjekatPDF(myFile);
            
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
	}
	//implementirano
	public void generisiPDF(KreditnaPonuda novi) throws IOException{
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		try {
            PdfWriter.getInstance(document,new FileOutputStream("target/Kredit"+novi.getTk().getTipKreditaId()+".pdf"));
          //SADRZAJ
            document.open();
            //zaglavlje dokumenta

            document.addAuthor("MicroOrg");
            document.addCreationDate();
            document.addLanguage("EN");

            document.add(new Paragraph("MicroOrg - Silaboration team",new Font(Font.FontFamily.HELVETICA  , 5, Font.BOLD)));
            document.add(new Paragraph("________________________________________________________________________"
            		+ "_________________________________________________________________________________"
            		+ "_________________________",new Font(Font.FontFamily.HELVETICA  , 5, Font.BOLD)));
            
            Paragraph naslov=new Paragraph("\n Ponuda za kredit",new Font(Font.FontFamily.HELVETICA  , 18, Font.BOLD));
            naslov.setAlignment(Element.ALIGN_CENTER);
            document.add(naslov);
            Font pisanje=new Font(Font.FontFamily.HELVETICA  , 14, Font.NORMAL);
            
            document.add(new Chunk("\n",pisanje));        
            
            UposlenikLogika ulogika=new UposlenikLogika(); 
            KlijentLogika klogika=new KlijentLogika();
            
            document.add(new Chunk("\n Naziv: "+novi.getTk().getNaziv(),pisanje)); 
            document.add(new Chunk("\n Namjena: "+novi.getTk().getNamjena(),pisanje)); 
            document.add(new Chunk("\n Iznos: "+novi.getTk().getIznos()+"KM",pisanje));
            document.add(new Chunk("\n Rok otplate: "+novi.getTk().getRok(),pisanje));
            document.add(new Chunk("\n Kamatna stopa: "+novi.getTk().getKamatnaStopa(),pisanje));
            document.add(new Chunk("\n Garancija: "+novi.getTk().getGarancija(),pisanje));
            document.add(new Chunk("\n Grace period: "+novi.getTk().getGracePeriod(),pisanje));
            document.add(new Chunk("\n Instrumenti obezbjeđenja: "+novi.getTk().getInstrumentiObezbjedjenja(),pisanje));
            document.add(new Chunk("\n Troskovi obrade: "+novi.getTk().getTroskoviObrade(),pisanje));
            document.add(new Chunk("\n Datum izdavanja: "+novi.getDatumUpisa(),pisanje));
            
            Paragraph podnaslov=new Paragraph("\n \n Podaci o klijentu",new Font(Font.FontFamily.HELVETICA  , 14, Font.BOLD));
            podnaslov.setAlignment(Element.ALIGN_CENTER);
            document.add(podnaslov);   
            document.add(new Chunk("\n Ime i prezime: "+klogika.getOsoba((novi.getK().getOsobaId())).getImePrezime(),pisanje));
            document.add(new Chunk("\n JMBG: "+klogika.getOsoba((novi.getK().getOsobaId())).getJmbg(),pisanje));
            document.add(new Chunk("\n E-mail: "+klogika.getOsoba((novi.getK().getOsobaId())).getEmail(),pisanje));
            document.add(new Chunk("\n Telefon: "+klogika.getOsoba((novi.getK().getOsobaId())).getTelefon(),pisanje));
            podnaslov=new Paragraph("\n \n Izdavač ponude:",new Font(Font.FontFamily.HELVETICA  , 14, Font.BOLD));
            podnaslov.setAlignment(Element.ALIGN_CENTER);
            document.add(podnaslov);
            document.add(new Chunk("\n Ime i prezime: "+ulogika.getOsoba(novi.getU().getOsobaId()).getImePrezime(),pisanje));
            document.add(new Chunk("\n ID uposlenika: "+novi.getU().getUposlenikId(),pisanje));

            
            Font pisanje2=new Font(Font.FontFamily.HELVETICA  , 14, Font.ITALIC);
            document.add(new Phrase("\n \n Ovim dokumentom je potvrđena uplata date transakcije za dati kredit, te je odobreno korištenje navedenih podataka u slične svrhe! \n \n \n \n \n  \n \n \n \n \n ",pisanje2)); //tekstualne fraze
            
            Paragraph potpis=new Paragraph("__________________________________________  \n \n Potpis izdavača ",new Font(Font.FontFamily.HELVETICA  , 5, Font.BOLD));
            potpis.setAlignment(Element.ALIGN_RIGHT);
            document.add(potpis);
            Paragraph footer= new Paragraph("________________________________________________________________________"
            		+ "_________________________________________________________________________________"
            		+ "_________________________",new Font(Font.FontFamily.HELVETICA  , 5, Font.BOLD));
            footer.setAlignment(Element.ALIGN_BASELINE);
            document.add(footer);
            //KRAJ
            document.close();
            
            //kreiramo objekat myFile koji ćemo smjestit u trenutni objekat u spremniku
            File myFile=new File( "target/Kredit"+novi.getTk().getTipKreditaId()+".pdf");
            Spremnik.setObjekatPDF(myFile);
            
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }		
	}
	//implementirano
	public void generisiPDF(viewModels.Transakcija novi){
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		try {
            PdfWriter.getInstance(document,new FileOutputStream("target/Transakcija"+novi.getKlijent().getKlijentId()+novi.getDatumUplate()+".pdf"));
            //SADRZAJ
            document.open();
            //zaglavlje dokumenta

            document.addAuthor("MicroOrg");
            document.addCreationDate();
            document.addLanguage("EN");

            document.add(new Paragraph("MicroOrg - Silaboration team",new Font(Font.FontFamily.HELVETICA  , 5, Font.BOLD)));
            document.add(new Paragraph("________________________________________________________________________"
            		+ "_________________________________________________________________________________"
            		+ "_________________________",new Font(Font.FontFamily.HELVETICA  , 5, Font.BOLD)));
            
            Paragraph p=new Paragraph("\n Izvještaj o izvršenoj transakciji",new Font(Font.FontFamily.HELVETICA  , 18, Font.BOLD));
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            Font pisanje=new Font(Font.FontFamily.HELVETICA  , 14, Font.NORMAL);
            
            document.add(new Chunk("\n",pisanje));        
            
            KlijentLogika logika=new KlijentLogika(); 
            
            document.add(new Chunk("\n Ime i Prezime: "+logika.getOsoba(novi.getKlijent().getOsobaId()).getImePrezime(),pisanje)); 
            document.add(new Chunk("\n Datum uplate: "+novi.getDatumUplate(),pisanje)); 
            document.add(new Chunk("\n Vrsta uplate: "+novi.getNacinUplate(),pisanje));
            document.add(new Chunk("\n Iznos uplate: "+novi.getIznosUplate()+" KM",pisanje));
            document.add(new Chunk("\n Iznos je uplaćen za: '"+novi.getKredit().getTipKredita().getNaziv()+"'",pisanje));
            document.add(new Chunk(", odobrenog od strane kreditnog službenika:"+novi.getKredit().getKreditniSluzbenik().getUsername(),pisanje));
            document.add(new Chunk(", datuma: "+novi.getKredit().getDatumUpisa(),pisanje));
            
            Font pisanje2=new Font(Font.FontFamily.HELVETICA  , 14, Font.ITALIC);
            document.add(new Phrase("\n \n Ovim dokumentom je potvrđena uplata date transakcije za dati kredit, te je odobreno korištenje navedenih podataka u slične svrhe! \n \n \n \n \n  \n \n \n \n \n ",pisanje2)); //tekstualne fraze
           
            Paragraph potpis=new Paragraph("__________________________________________  \n \n Potpis izdavača ",new Font(Font.FontFamily.HELVETICA  , 5, Font.BOLD));
            potpis.setAlignment(Element.ALIGN_RIGHT);
            document.add(potpis);
            Paragraph footer= new Paragraph("________________________________________________________________________"
            		+ "_________________________________________________________________________________"
            		+ "_________________________",new Font(Font.FontFamily.HELVETICA  , 5, Font.BOLD));
            footer.setAlignment(Element.ALIGN_BASELINE);
            document.add(footer);
            //KRAJ
            document.close();
            
            //kreiramo objekat myFile koji ćemo smjestit u trenutni objekat u spremniku
            File myFile=new File( "target/Transakcija"+novi.getKlijent().getKlijentId()+novi.getDatumUplate()+".pdf");
            Spremnik.setObjekatPDF(myFile);
            
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
		
	}
	//implementirano
	public void generisiPDF(KlijentSluzbenik novi){
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		try {
            PdfWriter.getInstance(document,new FileOutputStream("target/Klijent"+novi.getJmbg()+".pdf"));
          //SADRZAJ
            document.open();
            //zaglavlje dokumenta

            document.addAuthor("MicroOrg");
            document.addCreationDate();
            document.addLanguage("EN");

            document.add(new Paragraph("MicroOrg - Silaboration team",new Font(Font.FontFamily.HELVETICA  , 5, Font.BOLD)));
            document.add(new Paragraph("________________________________________________________________________"
            		+ "_________________________________________________________________________________"
            		+ "_________________________",new Font(Font.FontFamily.HELVETICA  , 5, Font.BOLD)));
            
            Paragraph podnaslov=new Paragraph("\n Podaci o klijentu",new Font(Font.FontFamily.HELVETICA  , 18, Font.BOLD));
            podnaslov.setAlignment(Element.ALIGN_CENTER);
            document.add(podnaslov);
            Font pisanje=new Font(Font.FontFamily.HELVETICA  , 14, Font.NORMAL);
            
            document.add(new Chunk("\n",pisanje));        
            
            document.add(new Chunk("\n Ime i Prezime: "+novi.getImePrezime(),pisanje)); 
            document.add(new Chunk("\n Adresa stanovanja: "+novi.getAdresa(),pisanje)); 
            document.add(new Chunk("\n Datum rođenja: "+novi.getDatumRodjenja(),pisanje));
            document.add(new Chunk("\n JMBG: "+novi.getJmbg(),pisanje));
            document.add(new Chunk("\n Broj telefona: "+novi.getTelefon(),pisanje));
            document.add(new Chunk("\n E-mail adresa: "+novi.getEmail(),pisanje));
            document.add(new Chunk("\n Status klijenta: "+novi.getStatus(),pisanje));
            
            //tabela svih kredita... nije implementirano...
            
            Font pisanje2=new Font(Font.FontFamily.HELVETICA  , 14, Font.ITALIC);
            document.add(new Phrase("\n \n Ovim dokumentom je potvrđena uplata date transakcije za dati kredit, te je odobreno korištenje navedenih podataka u slične svrhe! \n \n \n \n \n  \n \n \n \n \n ",pisanje2)); //tekstualne fraze
            
            Paragraph potpis=new Paragraph("__________________________________________  \n \n Potpis izdavača ",new Font(Font.FontFamily.HELVETICA  , 5, Font.BOLD));
            potpis.setAlignment(Element.ALIGN_RIGHT);
            document.add(potpis);
            Paragraph footer= new Paragraph("________________________________________________________________________"
            		+ "_________________________________________________________________________________"
            		+ "_________________________",new Font(Font.FontFamily.HELVETICA  , 5, Font.BOLD));
            footer.setAlignment(Element.ALIGN_BASELINE);
            document.add(footer);
            //KRAJ
            document.close();
            
            //kreiramo objekat myFile koji ćemo smjestit u trenutni objekat u spremniku
            File myFile=new File( "target/Klijent"+novi.getJmbg()+".pdf");
            Spremnik.setObjekatPDF(myFile);
            
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
		
	}
	//implementirano
	public void generisiPDF(KreditniSluzbenik novi){
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		try {
            PdfWriter.getInstance(document,new FileOutputStream("target/Sluzbenik"+novi.getJmbg()+".pdf"));
            //SADRZAJ
            document.open();
            //zaglavlje dokumenta

            document.addAuthor("MicroOrg");
            document.addCreationDate();
            document.addLanguage("EN");

            document.add(new Paragraph("MicroOrg - Silaboration team",new Font(Font.FontFamily.HELVETICA  , 5, Font.BOLD)));
            document.add(new Paragraph("________________________________________________________________________"
            		+ "_________________________________________________________________________________"
            		+ "_________________________",new Font(Font.FontFamily.HELVETICA  , 5, Font.BOLD)));
            
            Paragraph p=new Paragraph("\n Podaci o uposleniku",new Font(Font.FontFamily.HELVETICA  , 18, Font.BOLD));
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            Font pisanje=new Font(Font.FontFamily.HELVETICA  , 14, Font.NORMAL);
            
            document.add(new Chunk("\n",pisanje));        
            
           UposlenikLogika logika=new UposlenikLogika(); 
            
            document.add(new Chunk("\n Ime i Prezime: "+novi.getImePrezime(),pisanje)); 
            document.add(new Chunk("\n Adresa stanovanja: "+novi.getAdresa(),pisanje)); 
            document.add(new Chunk("\n JMBG: "+novi.getJmbg(),pisanje));
            document.add(new Chunk("\n Datum rođenja: "+novi.getDatumRodjenja(),pisanje));
            document.add(new Chunk("\n Mjesto rođenja: "+novi.getMjestoRodjenja(),pisanje));
            document.add(new Chunk("\n Obrazovanje: "+novi.getObrazovanje(),pisanje));
            document.add(new Chunk("\n Telefon: "+novi.getTelefon(),pisanje));
            document.add(new Chunk("\n E-mail adresa: "+novi.getEmail(),pisanje));
            document.add(new Chunk("\n Password: "+novi.getPassword(),pisanje));
            document.add(new Chunk("\n Plata: "+novi.getPlata(),pisanje));
            document.add(new Chunk("\n Broj izdatih kredita: "+novi.getBrojKredita(),pisanje));
            document.add(new Chunk("\n Broj evidentiranih transakcija: "+novi.getBrojTransakcija(),pisanje));

            Font pisanje2=new Font(Font.FontFamily.HELVETICA  , 14, Font.ITALIC);
            document.add(new Phrase("\n \n Ovim dokumentom je potvrđena uplata date transakcije za dati kredit, te je odobreno korištenje navedenih podataka u slične svrhe! \n \n \n \n \n  \n \n \n \n \n ",pisanje2)); //tekstualne fraze
            
            Paragraph potpis=new Paragraph("__________________________________________  \n \n Potpis izdavača ",new Font(Font.FontFamily.HELVETICA  , 5, Font.BOLD));
            potpis.setAlignment(Element.ALIGN_RIGHT);
            document.add(potpis);
            Paragraph footer= new Paragraph("________________________________________________________________________"
            		+ "_________________________________________________________________________________"
            		+ "_________________________",new Font(Font.FontFamily.HELVETICA  , 5, Font.BOLD));
            footer.setAlignment(Element.ALIGN_BASELINE);
            document.add(footer);
            //KRAJ
            document.close();
            
            //kreiramo objekat myFile koji ćemo smjestit u trenutni objekat u spremniku
            File myFile=new File( "target/Sluzbenik"+novi.getJmbg()+".pdf");
            Spremnik.setObjekatPDF(myFile);
            
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
		
	}
	//implementirano
	public void otvoriPDF(Object o) throws IOException{
		//Otvaranje pdf-a
        File myFile=null;
		//podesavanje adrese koju će otvoriti
        if (o.getClass()==KreditnaPonuda.class) {KreditnaPonuda novi=(KreditnaPonuda) o; myFile = new File( "target/Kredit"+novi.getTk().getTipKreditaId()+".pdf");}
		if (o.getClass()==IzvjestajSluzbenika.class) {IzvjestajSluzbenika novi=(IzvjestajSluzbenika) o; myFile = new File( "target/IzvjestajSluzbenika"+novi.getImePrezimeSluzbenika()+".pdf");}
		if (o.getClass()==IzvjestajOrganizacije.class) {IzvjestajOrganizacije novi=(IzvjestajOrganizacije) o; myFile = new File( "target/IzvjestajOrganizacije"+novi.getDatumGenerisanja()+".pdf");}
		if (o.getClass()==viewModels.Transakcija.class) {viewModels.Transakcija novi=(viewModels.Transakcija) o; myFile = new File( "target/Transakcija"+novi.getKlijent().getKlijentId()+novi.getDatumUplate()+".pdf");}
		if (o.getClass()==KlijentSluzbenik.class) {KlijentSluzbenik novi=(KlijentSluzbenik) o; myFile = new File( "target/Klijent"+novi.getJmbg()+".pdf");}
		if (o.getClass()==KreditniSluzbenik.class) {KreditniSluzbenik novi=(KreditniSluzbenik) o; myFile = new File("target/Sluzbenik"+novi.getJmbg()+".pdf");}
		try {
		Desktop.getDesktop().open(myFile);
        //postavljanje opcije da se pdf obrise kad se zatvori aplikacija
        myFile.deleteOnExit();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Nisi generisao/la PDF. Prvo je potrebno generisati PDF da bi se mogao otvoriti.","Greška...", JOptionPane.INFORMATION_MESSAGE);
        }	
	}
	//implementirano
	public boolean posaljiMail(String posiljaoc,String sifra,String primalac, String naslov, String tekst, Uposlenik _uposlenik, Object privitak)
	{
			
	        //konvertovanje u final tip podataka zbog autentifikacije
	        final String username=posiljaoc+"@gmail.com";
	        final String password=sifra;
	        final String sendTo=primalac;
	        
	        //postavljanje osobina za pristup gmail webmail serveru
	        Properties props = new Properties();
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", "587");

	        //Otvaranje sesije za slanje...
	        Session session = Session.getInstance(props,new javax.mail.Authenticator() {protected PasswordAuthentication getPasswordAuthentication() {return new PasswordAuthentication(username, password);}});

	        
	        try {
	        	//podaci o poruci
	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(username));
	            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(sendTo));
	            
	            //sadržaj poruke
	            message.setSubject("Naslov");
	            message.setText("Poštovani,"+ "\n\n U privitku ove poruke vam šaljemo dokument(u PDF formatu) koji ste zahtjevali.");
	            if (tekst!="") {message.setText("\n \n"+tekst);}
	            message.setText("\n \n \n \n Lijepi pozdravi, \n"+_uposlenik.getUsername());
	            
	            //dodavanje attachmenta ako postoji
	            if (privitak!=null){
	            	File privitak1=(File) privitak;
	            	Multipart multipart = new MimeMultipart();
	            	MimeBodyPart messageBodyPart = new MimeBodyPart();

	            	DataSource source = new FileDataSource(privitak1.getPath());
	            	messageBodyPart.setDataHandler(new DataHandler(source));
	            	messageBodyPart.setFileName(privitak1.getName());
	            	multipart.addBodyPart(messageBodyPart);
	            	message.setContent(multipart);
	            }

	            //pošalji e-mail!
	            Transport.send(message);
	            //ako je sve uredu ... vrati tačno
	            return true;

	        } catch (MessagingException e) {
	            //throw new RuntimeException(e);
	            //ako nije uredu vrati pogresno
	        	return false;
	        }
		
	}
	//implementirano
	public boolean validirajJMB(String _jmbg, Date _datumRodjenja){
			char[] cifre = _jmbg.toCharArray();
			int dan = Character.getNumericValue(cifre[0])*10 + Character.getNumericValue(cifre[1]);
			int mjesec = Character.getNumericValue(cifre[2])*10 + Character.getNumericValue(cifre[3]);
			int godina = Character.getNumericValue(cifre[4])*100 + Character.getNumericValue(cifre[5])*10 + Character.getNumericValue(cifre[6])*1;
			Calendar cal = Calendar.getInstance();
			cal.setTime(_datumRodjenja);
			int d = cal.get(Calendar.DAY_OF_MONTH);
			int m = cal.get(Calendar.MONTH);
			int g = cal.get(Calendar.YEAR);
			if(_jmbg.length()==13 || dan == d || mjesec == m || godina == g)return true;
			else return false;
	}
	//implementirano
	public boolean validirajDatum(String datum){
		 SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		    dateFormat.setLenient(false);
		    try {
		      dateFormat.parse(datum.trim());
		    } catch (java.text.ParseException pe) {
		      return false;
		    }
		    return true;
		  }
	//implementirano
	public boolean validirajEmail(String email){
		boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		   return result;
		}
	//implementirano
	public boolean validirajImePrezime(String imePrezime){
		String[] par = imePrezime.split(" ");
		String ime = par[0];
		String prezime = par[1];		
		return (ime.matches("[A-Z][a-zA-Z]*") && prezime.matches("[A-Z][a-zA-Z]*")); 
	}
	//implementirano
	public boolean validirajIznosKredita(String iznos){
		
		try {
	        Double.parseDouble(iznos);
	        return (Double.parseDouble(iznos)<50000);
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
	//implementirano
	public boolean validirajKamatnuStopu(String kamata){
		
		try {
	        Double.parseDouble(kamata);
	        return (Double.parseDouble(kamata)<20);
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
	//implementirano
	public boolean validirajIme(String ime){		
		return ime.matches("[A-Z][a-zA-Z]*"); 
	}
	//implementirano
	public boolean validirajPrezime(String prezime){	
		return prezime.matches("[A-Z][a-zA-Z]*"); 
	}
	//implementirano
	public boolean validirajTelefon(String telefon){
		return telefon.matches("\\d{3}-\\d{3}-\\d{3}");
	}
	
	public void ispitajSQL(String upad){}
	
}
