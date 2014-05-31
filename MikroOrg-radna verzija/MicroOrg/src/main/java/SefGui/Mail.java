package SefGui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import logic.SharedLogika;

import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.JPasswordField;

import java.awt.TextArea;
import java.io.File;
import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.JCheckBox;

import aplikacija.MicroOrg.Spremnik;
import viewModels.IzvjestajOrganizacije;
import viewModels.IzvjestajSluzbenika;
import viewModels.KlijentSluzbenik;
import viewModels.KreditnaPonuda;
import viewModels.KreditniSluzbenik;
import domainModels.Klijent;
import domainModels.Kredit;
import domainModels.Transakcija;
public class Mail extends JFrame {

	private JPanel contentPane;
	private JTextField posiljaocfield;
	private JTextField primalacfield;
	private JLabel lblPoiljaoc;
	private JLabel lblPrimalac;
	private JPasswordField sifrafield;
	private JLabel lblTekstPorukenijeNeophodno;
	private JLabel lblNaslov;
	private JTextField naslovfield;
	private Object objekat;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mail frame = new Mail();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Mail() {
		objekat=Spremnik.getObjekatPDF();
		setTitle("Unesite podatke za slanje E-mail poštom:");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 397, 331);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("@gmail.com");
		lblNewLabel.setBounds(107, 27, 81, 14);
		contentPane.add(lblNewLabel);
		
		posiljaocfield = new JTextField();
		posiljaocfield.setBackground(UIManager.getColor("InternalFrame.inactiveBorderColor"));
		posiljaocfield.setBounds(20, 24, 86, 20);
		contentPane.add(posiljaocfield);
		posiljaocfield.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Šifra:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(187, 27, 57, 14);
		contentPane.add(lblNewLabel_1);
		
		primalacfield = new JTextField();
		primalacfield.setBackground(UIManager.getColor("InternalFrame.inactiveBorderColor"));
		primalacfield.setBounds(20, 75, 180, 20);
		contentPane.add(primalacfield);
		primalacfield.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("(unesite cijelu e-mail adresu)");
		lblNewLabel_2.setBounds(210, 78, 168, 14);
		contentPane.add(lblNewLabel_2);
		

		
		sifrafield = new JPasswordField();
		sifrafield.setBackground(UIManager.getColor("InternalFrame.inactiveBorderColor"));
		sifrafield.setBounds(249, 24, 126, 20);
		contentPane.add(sifrafield);
		
		
		
		final TextArea tekstfield = new TextArea();
		tekstfield.setBackground(UIManager.getColor("InternalFrame.inactiveBorderColor"));
		tekstfield.setBounds(20, 166, 355, 97);
		contentPane.add(tekstfield);
		this.setResizable(false);
		
		
		lblTekstPorukenijeNeophodno = new JLabel("Tekst poruke(nije neophodno):");
		lblTekstPorukenijeNeophodno.setBounds(20, 146, 190, 14);
		contentPane.add(lblTekstPorukenijeNeophodno);
		
		lblNaslov = new JLabel("Naslov:");
		lblNaslov.setBounds(20, 121, 57, 14);
		contentPane.add(lblNaslov);
		
		naslovfield = new JTextField();
		naslovfield.setBackground(UIManager.getColor("InternalFrame.inactiveBorderColor"));
		naslovfield.setBounds(76, 115, 299, 20);
		contentPane.add(naslovfield);
		naslovfield.setColumns(10);
		
		final JCheckBox checkboxPrivitak = new JCheckBox("poslati Privitak");
		checkboxPrivitak.setSelected(true);
		checkboxPrivitak.setBounds(20, 269, 116, 23);
		contentPane.add(checkboxPrivitak);
		
		JButton btnNewButton = new JButton("Pošalji");
		btnNewButton.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent arg0) {
			SharedLogika nova=new SharedLogika();
			String posiljaoc=posiljaocfield.getText();
			String sifra=sifrafield.getText();
			String primalac=primalacfield.getText();
			String tekst=tekstfield.getText();
			String naslov=naslovfield.getText();
			
			boolean proba= false;
		
			//ukoliko je odabrano da uposlenik želi da pošalje i privitak...
			if (checkboxPrivitak.isSelected())
			{
				
				if (objekat.getClass()==KreditnaPonuda.class) 
				{
					KreditnaPonuda novi=(KreditnaPonuda) objekat; 
					try 
					{
					nova.generisiPDF(novi);
					proba=nova.posaljiMail(posiljaoc,sifra,primalac,naslov,tekst,Spremnik.getTrenutni(),Spremnik.getObjekatPDF());
					} catch (IOException e){e.printStackTrace();}
				}
				if (objekat.getClass()==IzvjestajSluzbenika.class) 
				{
					IzvjestajSluzbenika novi=(IzvjestajSluzbenika) objekat; 
					nova.generisiPDF(novi);
					proba=nova.posaljiMail(posiljaoc,sifra,primalac,naslov,tekst,Spremnik.getTrenutni(),Spremnik.getObjekatPDF());
				}
				if (objekat.getClass()==IzvjestajOrganizacije.class) 
				{
					IzvjestajOrganizacije novi=(IzvjestajOrganizacije) objekat;
					nova.generisiPDF(novi);
					proba=nova.posaljiMail(posiljaoc,sifra,primalac,naslov,tekst,Spremnik.getTrenutni(),Spremnik.getObjekatPDF());
				}
				if (objekat.getClass()==viewModels.Transakcija.class) 
				{
					viewModels.Transakcija novi=(viewModels.Transakcija) objekat;
					nova.generisiPDF(novi);
					proba=nova.posaljiMail(posiljaoc,sifra,primalac,naslov,tekst,Spremnik.getTrenutni(),Spremnik.getObjekatPDF());
				}
				if (objekat.getClass()==KlijentSluzbenik.class) 
				{
					KlijentSluzbenik novi=(KlijentSluzbenik) objekat;
					nova.generisiPDF(novi);
					proba=nova.posaljiMail(posiljaoc,sifra,primalac,naslov,tekst,Spremnik.getTrenutni(),Spremnik.getObjekatPDF());
				}

			}
			//ako ipak neće da pošalje privitak...
			else{proba=nova.posaljiMail(posiljaoc,sifra,primalac,naslov,tekst,Spremnik.getTrenutni(),null);}

			if (proba==true){JOptionPane.showMessageDialog(null, " Vaša poruka je uspješno poslana!","Izvještaj o poruci", JOptionPane.INFORMATION_MESSAGE);}
		else {JOptionPane.showMessageDialog(null, " Unijeli ste pogrešne podatke, ili je internet konekcija onemogućena!","Greška...", JOptionPane.INFORMATION_MESSAGE);}		
		}});
		btnNewButton.setBounds(142, 269, 115, 23);
		contentPane.add(btnNewButton);
		
		lblPoiljaoc = new JLabel("Pošiljalac:");
		lblPoiljaoc.setBounds(20, 11, 145, 14);
		contentPane.add(lblPoiljaoc);
		
		lblPrimalac = new JLabel("Primalac:");
		lblPrimalac.setBounds(20, 62, 46, 14);
		contentPane.add(lblPrimalac);
		

		JButton btnOdustani = new JButton("Zatvori");
		btnOdustani.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			SefGui.Mail.this.dispose();
			}
		});
		btnOdustani.setBounds(267, 269, 108, 23);
		contentPane.add(btnOdustani);
	}
		
}
