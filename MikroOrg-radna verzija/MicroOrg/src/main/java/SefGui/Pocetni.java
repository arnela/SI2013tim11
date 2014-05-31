package SefGui;

import aplikacija.MicroOrg.Login; 

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.SystemColor;

import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

import domainModels.Uposlenik;
import aplikacija.MicroOrg.Spremnik;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import logic.UposlenikLogika;

public class Pocetni extends JFrame {

	private JPanel contentPane;
	private UposlenikLogika ulogika;

	private Uposlenik trenutni;
	/**
	 * Launch the application.
	 */
	
	//ovo je konstruktor koji bi sluzio da ova klasa bude runnable from start, a to nam ne treba tako da...
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
				{
				try {
					Pocetni frame = new Pocetni();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {e.printStackTrace();}
				}
		});
	}

	/**
	 * Create the frame.
	 */
	public Pocetni() {
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosed(WindowEvent e) {
				aplikacija.MicroOrg.Login l=new aplikacija.MicroOrg.Login();
				l.setVisible(true);
				l.setResizable(false);
				l.setLocationRelativeTo(null);
				l.show();
				Spremnik.getPocetni().hide();
				//brisanje sadržaja iz Spremnika
				Spremnik.setIzvjestaji(null);
				Spremnik.setKlijenti(null);
				Spremnik.setKrediti(null);
				Spremnik.setObjekatPDF(null);
				Spremnik.setPocetni(null);
				Spremnik.setPonude(null);
				Spremnik.setPoslovanje(null);
				Spremnik.setTransakcije(null);
				Spremnik.setTrenutni(null);
				Spremnik.setUposlenici(null);
			}
		});
		trenutni=Spremnik.getTrenutni();
		ulogika=new UposlenikLogika();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("MicroOrg - \u0160ef");
		setBounds(100, 100, 436, 429);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("Klijenti");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (Spremnik.getKlijenti()==null){
					SefGui.Klijenti n =new SefGui.Klijenti();  //kreira klijenti gui za �efa
					n.setLocationRelativeTo(null);   // postavlja ga na sredinu
					n.setVisible(true);  // upali vidljivost
					n.setResizable(false);
					Spremnik.setKlijenti(n);
					}
					else {Spremnik.getKlijenti().show();} // ako ipak postoji samo je otvori}
					Spremnik.setPocetni(SefGui.Pocetni.this); //postavlja formu u spremnik ...
					Spremnik.getPocetni().hide(); //sakriva pocetnu formu koja se nalazi u spremniku
			}
		});
		button.setIcon(new ImageIcon(Pocetni.class.getResource("/slike/users (1).png")));
		button.setHorizontalAlignment(SwingConstants.TRAILING);
		button.setBounds(10, 11, 153, 50);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Ponude");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Spremnik.getPonude()==null){
					SefGui.Ponude n =new SefGui.Ponude();  //kreira klijenti gui za �efa
					n.setLocationRelativeTo(null);   // postavlja ga na sredinu
					n.setVisible(true);  // upali vidljivost
					n.setResizable(false);
					Spremnik.setPonude(n);
					}
					else {Spremnik.getPonude().show();} // ako ipak postoji samo je otvori
					Spremnik.setPocetni(SefGui.Pocetni.this); //postavlja formu u spremnik ...
					Spremnik.getPocetni().hide(); //sakriva pocetnu formu koja se nalazi u spremniku
			}
		});
		button_1.setBackground(UIManager.getColor("Button.background"));
		button_1.setIcon(new ImageIcon(Pocetni.class.getResource("/slike/poooonuda.png")));
		button_1.setHorizontalAlignment(SwingConstants.RIGHT);
		button_1.setBounds(10, 72, 153, 50);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("Krediti");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Spremnik.getKrediti()==null){
					SefGui.Krediti n =new SefGui.Krediti();  //kreira klijenti gui za �efa
					n.setLocationRelativeTo(null);   // postavlja ga na sredinu
					n.setVisible(true);  // upali vidljivost
					n.setResizable(false);
					Spremnik.setKrediti(n);
					}
					else {Spremnik.getKrediti().show();} // ako ipak postoji samo je otvori
					Spremnik.setPocetni(SefGui.Pocetni.this); //postavlja formu u spremnik ...
					Spremnik.getPocetni().hide(); //sakriva pocetnu formu koja se nalazi u spremniku
			}
		});
		button_2.setIcon(new ImageIcon(Pocetni.class.getResource("/slike/kredit.png")));
		button_2.setHorizontalAlignment(SwingConstants.TRAILING);
		button_2.setBackground(UIManager.getColor("Button.background"));
		button_2.setBounds(10, 133, 153, 50);
		contentPane.add(button_2);
		
		JButton button_4 = new JButton("Uposlenici");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Spremnik.getUposlenici()==null){
					SefGui.Uposlenici n =new SefGui.Uposlenici();  //kreira klijenti gui za �efa
					n.setLocationRelativeTo(null);   // postavlja ga na sredinu
					n.setVisible(true);  // upali vidljivost
					n.setResizable(false);
					Spremnik.setUposlenici(n);
					}
					else {Spremnik.getUposlenici().show();} // ako ipak postoji samo je otvori
					Spremnik.setPocetni(SefGui.Pocetni.this); //postavlja formu u spremnik ...
					Spremnik.getPocetni().hide(); //sakriva pocetnu formu koja se nalazi u spremniku
			}
		});
		button_4.setIcon(new ImageIcon(Pocetni.class.getResource("/slike/Workers.png")));
		button_4.setHorizontalAlignment(SwingConstants.RIGHT);
		button_4.setBackground(UIManager.getColor("Button.background"));
		button_4.setBounds(10, 203, 153, 50);
		contentPane.add(button_4);
		
		JButton button_5 = new JButton("Transakcije");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Spremnik.getTransakcije()==null){
					SefGui.Transakcije n =new SefGui.Transakcije();  //kreira klijenti gui za �efa
					n.setLocationRelativeTo(null);   // postavlja ga na sredinu
					n.setVisible(true);  // upali vidljivost
					n.setResizable(false);
					Spremnik.setTransakcije(n);
					}
					else {Spremnik.getTransakcije().show();} // ako ipak postoji samo je otvori
					Spremnik.setPocetni(SefGui.Pocetni.this); //postavlja formu u spremnik ...
					Spremnik.getPocetni().hide(); //sakriva pocetnu formu koja se nalazi u spremniku
			}
		});
		button_5.setBackground(UIManager.getColor("Button.background"));
		button_5.setIcon(new ImageIcon(Pocetni.class.getResource("/slike/kredit.jpg")));
		button_5.setHorizontalAlignment(SwingConstants.RIGHT);
		button_5.setBounds(10, 264, 153, 50);
		contentPane.add(button_5);
		
		JButton button_6 = new JButton("Poslovanje organizacije");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Spremnik.getPoslovanje()==null){
					SefGui.Poslovanje n =new SefGui.Poslovanje();  //kreira klijenti gui za �efa
					n.setLocationRelativeTo(null);   // postavlja ga na sredinu
					n.setVisible(true);  // upali vidljivost
					n.setResizable(false);
					Spremnik.setPoslovanje(n);
					}
					else {Spremnik.getPoslovanje().show();} // ako ipak postoji samo je otvori
					Spremnik.setPocetni(SefGui.Pocetni.this); //postavlja formu u spremnik ...
					Spremnik.getPocetni().hide(); //sakriva pocetnu formu koja se nalazi u spremniku
			}
		});
		button_6.setBackground(UIManager.getColor("Button.background"));
		button_6.setBounds(10, 335, 190, 50);
		contentPane.add(button_6);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(128, 0, 0));
		panel.setBorder(new LineBorder(new Color(128, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(220, 185, 190, 114);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label_5 = new JLabel("Ime:");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setBounds(10, 11, 79, 14);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("Prezime:");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setBounds(10, 36, 79, 14);
		panel.add(label_6);
		
		JLabel label_7 = new JLabel("Pozicija:");
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		label_7.setBounds(10, 64, 79, 14);
		panel.add(label_7);
		
		JLabel label_8 = new JLabel("Datum:");
		label_8.setHorizontalAlignment(SwingConstants.RIGHT);
		label_8.setBounds(10, 89, 79, 14);
		panel.add(label_8);
		
		String[] imena =ulogika.getOsoba(trenutni.getOsobaId()).getImePrezime().split(" ");
		JLabel label_9 = new JLabel(imena[0]);
		label_9.setHorizontalAlignment(SwingConstants.LEFT);
		label_9.setBounds(99, 11, 79, 14);
		panel.add(label_9);
		
		JLabel label_10 = new JLabel(imena[1]);
		label_10.setHorizontalAlignment(SwingConstants.LEFT);
		label_10.setBounds(99, 36, 79, 14);
		panel.add(label_10);
		
		JLabel label_11 = new JLabel("Šef");
		label_11.setHorizontalAlignment(SwingConstants.LEFT);
		label_11.setBounds(99, 64, 79, 14);
		panel.add(label_11);
		
		Calendar currentDate = Calendar.getInstance(); //Get the current date
		SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yyyy"); //format it as per your requirement
		String dateNow = formatter.format(currentDate.getTime());

		JLabel label_12 = new JLabel(dateNow);
		label_12.setHorizontalAlignment(SwingConstants.LEFT);
		label_12.setBounds(99, 89, 79, 14);
		panel.add(label_12);
		
		JButton button_7 = new JButton("Odjavi se!");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SefGui.Pocetni.this.dispose();
			}
		});
		button_7.setIcon(new ImageIcon(Pocetni.class.getResource("/slike/logout (1).png")));
		button_7.setHorizontalAlignment(SwingConstants.TRAILING);
		button_7.setBounds(259, 335, 153, 50);
		contentPane.add(button_7);
		
		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon(Pocetni.class.getResource("/slike/logo.jpg")));
		label_4.setBounds(210, 47, 200, 114);
		contentPane.add(label_4);
	}

}
