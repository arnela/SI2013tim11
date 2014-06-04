package SefGui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import aplikacija.MicroOrg.Spremnik;
import viewModels.KreditniSluzbenik;
import viewModels.SluzbenikTableModel;
//import viewModels.SluzbenikTableModel;
import domainModels.Uposlenik;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import logic.SharedLogika;
import logic.UposlenikLogika;

import javax.swing.JTable;
import javax.swing.JTextPane;

public class Uposlenici extends JFrame {

	private JPanel contentPane;
	private JTextField tf_adresa;
	private JTextField tf_ime;
	private JTextField tf_prezime;
	private JTextField tf_datum;
	private JTextField tf_jmbg;
	private JTextField tf_email;
	private JTextField tf_telefon;
	private JTextField tf_sifra;
	private JTextField tf_plata;
	private JTextField tf_mjestoRodjenja;
	private JTextField tf_pretraga;
	private  JTable _table=null;
	private  List<KreditniSluzbenik> _kreditniSluzbenici=null;
	private JScrollPane _scrollPane=null;
	private Uposlenik trenutni;
	static Uposlenici frame; 
	private KreditniSluzbenik _kreditniSluzbenik=null;

	/**
	 * Launch the application.
	 */
	//ovaj konstruktor je samo u slucaju da se aplikacija pokrece iz ove forme a nama to ne treba
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						frame = new Uposlenici();
						frame.setVisible(true);
						frame.setResizable(false);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

	/**
	 * Create the frame.
	 */
	public Uposlenici() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				Spremnik.setUposlenici(SefGui.Uposlenici.this);
				Spremnik.getPocetni().show();
				Spremnik.getUposlenici().hide();
			}
		});
		trenutni=Spremnik.getTrenutni();
		setTitle("MicroOrg - Uposlenici");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 427);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 534, 386);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Zapo\u0161ljavanje", null, panel, null);
		
		JButton btnNazad = new JButton("Nazad");
		btnNazad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SefGui.Uposlenici.this.dispose();
			}
		});
		btnNazad.setBounds(421, 324, 98, 23);
		panel.add(btnNazad);
		
		JButton button_1 = new JButton("Unesi zaposlenika ");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
	
				
				String _status="Nije ok";
				UposlenikLogika _uposlenikLogika = new UposlenikLogika();
				//VALIDACIJA
				try {
					 _status=_uposlenikLogika.validirajPodatke(
							 	tf_ime.getText(),
							 	tf_prezime.getText(),
								tf_jmbg.getText(),
								tf_datum.getText(),
								tf_telefon.getText(),
								tf_adresa.getText(),
								tf_email.getText(),
								tf_sifra.getText(),
								tf_mjestoRodjenja.getText(),
								tf_plata.getText()
							 );
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "validacija error");
				}
				//END VALIDACIJA
				if(_status=="OK"){
				//formatiranje i parsiranje datuma
				java.sql.Date _datum = null;
				SimpleDateFormat _sdf1 = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date _date;
				try 
				{
					_date = _sdf1.parse(tf_datum.getText());
					_datum=new java.sql.Date(_date.getTime());  
				}
				
				catch (ParseException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				KreditniSluzbenik _kreditniSluzbenik= new KreditniSluzbenik
						(
							tf_ime.getText()+" "+tf_prezime.getText(),
							tf_jmbg.getText(),
							_datum,
							"null",
							tf_telefon.getText(),
							tf_adresa.getText(),
							tf_email.getText(),
							tf_sifra.getText(),
							tf_mjestoRodjenja.getText(),
							Double.parseDouble(tf_plata.getText())
						);
				
				try {
						if(!_uposlenikLogika.daLiPostoji(tf_jmbg.getText())){
							_uposlenikLogika.dodajUposlenika(_kreditniSluzbenik);
						//ocisti formu
						tf_ime.setText("");
						tf_prezime.setText("");
						tf_jmbg.setText("");
						tf_datum.setText("");
						tf_telefon.setText("");
						tf_adresa.setText("");
						tf_email.setText("");
						tf_sifra.setText("");
						tf_mjestoRodjenja.setText("");
						tf_plata.setText("");
						JOptionPane.showMessageDialog(null, "Uspješno evidentirano !");
					}
					else{
						JOptionPane.showMessageDialog(null, "Uposlenik sa unesenim jmbg-om već postoji !");
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Nešto je pošlo naopako ! ERROR: d0d4jUp0sl3n1k4");
				}
				}
				else{
					JOptionPane.showMessageDialog(null, _status);
				}
			}
		});
		button_1.setBounds(10, 324, 142, 23);
		panel.add(button_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(165, 42, 42), 1, true));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 11, 509, 302);
		panel.add(panel_1);
		
		JLabel label = new JLabel("Adresa:");
		label.setHorizontalAlignment(SwingConstants.TRAILING);
		label.setBounds(272, 160, 62, 14);
		panel_1.add(label);
		
		JLabel label_1 = new JLabel("Telefon:");
		label_1.setHorizontalAlignment(SwingConstants.TRAILING);
		label_1.setBounds(280, 204, 54, 14);
		panel_1.add(label_1);
		
		JLabel label_2 = new JLabel("Email:");
		label_2.setHorizontalAlignment(SwingConstants.TRAILING);
		label_2.setBounds(19, 160, 70, 14);
		panel_1.add(label_2);
		
		JLabel label_3 = new JLabel("JMBG:");
		label_3.setHorizontalAlignment(SwingConstants.TRAILING);
		label_3.setBounds(25, 251, 64, 14);
		panel_1.add(label_3);
		
		JLabel lblDdmmyyyy = new JLabel("dd-MM-yyyy");
		lblDdmmyyyy.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDdmmyyyy.setBounds(92, 87, 102, 14);
		panel_1.add(lblDdmmyyyy);
		
		JLabel label_5 = new JLabel("Ime:");
		label_5.setHorizontalAlignment(SwingConstants.TRAILING);
		label_5.setBounds(28, 62, 61, 14);
		panel_1.add(label_5);
		
		JLabel label_6 = new JLabel("Prezime:");
		label_6.setHorizontalAlignment(SwingConstants.TRAILING);
		label_6.setBounds(270, 62, 64, 14);
		panel_1.add(label_6);
		
		tf_adresa = new JTextField();
		tf_adresa.setColumns(10);
		tf_adresa.setBounds(341, 154, 155, 20);
		panel_1.add(tf_adresa);
		
		tf_ime = new JTextField();
		tf_ime.setColumns(10);
		tf_ime.setBounds(92, 56, 155, 20);
		panel_1.add(tf_ime);
		
		tf_prezime = new JTextField();
		tf_prezime.setColumns(10);
		tf_prezime.setBounds(341, 56, 155, 20);
		panel_1.add(tf_prezime);
		
		tf_datum = new JTextField();
		tf_datum.setColumns(10);
		tf_datum.setBounds(92, 106, 155, 20);
		panel_1.add(tf_datum);
		
		tf_jmbg = new JTextField();
		tf_jmbg.setColumns(10);
		tf_jmbg.setBounds(92, 245, 155, 20);
		panel_1.add(tf_jmbg);
		
		tf_email = new JTextField();
		tf_email.setColumns(10);
		tf_email.setBounds(92, 154, 155, 20);
		panel_1.add(tf_email);
		
		tf_telefon = new JTextField();
		tf_telefon.setColumns(10);
		tf_telefon.setBounds(341, 198, 155, 20);
		panel_1.add(tf_telefon);
		
		JLabel label_7 = new JLabel("\u0160ifra:");
		label_7.setHorizontalAlignment(SwingConstants.TRAILING);
		label_7.setBounds(288, 246, 46, 19);
		panel_1.add(label_7);
		
		tf_sifra = new JTextField();
		tf_sifra.setColumns(10);
		tf_sifra.setBounds(341, 244, 86, 22);
		panel_1.add(tf_sifra);
		
		JLabel label_8 = new JLabel("Plata:");
		label_8.setHorizontalAlignment(SwingConstants.TRAILING);
		label_8.setBounds(19, 204, 70, 14);
		panel_1.add(label_8);
		
		tf_plata = new JTextField();
		tf_plata.setColumns(10);
		tf_plata.setBounds(92, 198, 155, 20);
		panel_1.add(tf_plata);
		
		JLabel label_9 = new JLabel("Mjesto ro\u0111enja:");
		label_9.setHorizontalAlignment(SwingConstants.TRAILING);
		label_9.setBounds(232, 112, 102, 14);
		panel_1.add(label_9);
		
		tf_mjestoRodjenja = new JTextField();
		tf_mjestoRodjenja.setColumns(10);
		tf_mjestoRodjenja.setBounds(341, 106, 155, 20);
		panel_1.add(tf_mjestoRodjenja);
		
		JLabel lblPodaciONovom = new JLabel("Podaci o novom uposleniku:");
		lblPodaciONovom.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPodaciONovom.setBounds(162, 0, 208, 33);
		panel_1.add(lblPodaciONovom);
		
		JLabel label_12 = new JLabel("Datum rođenja:");
		label_12.setHorizontalAlignment(SwingConstants.TRAILING);
		label_12.setBounds(-14, 109, 102, 14);
		panel_1.add(label_12);
		
		JLabel label_4 = new JLabel("061-123-123");
		label_4.setBounds(375, 186, 79, 14);
		panel_1.add(label_4);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(Color.WHITE);
		tabbedPane.addTab("Pretraga i prikaz uposlenika", null, panel_2, null);
		
		final JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(128, 0, 0), 1, true));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(10, 54, 503, 261);
		panel_2.add(panel_3);
		
		JButton button = new JButton("PDF prikaz");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				KreditniSluzbenik _toBePDFGenerated=null;
				try {
					int _foo= _table.getSelectedRow();
					if(_foo==-1) throw new NullPointerException();
						//JOptionPane.showMessageDialog(null, "Niste odabrali uposlenika čije podatke želite promijeniti!");
					//pomocna varijabla jer se remove ne moze uraditi kako treba unutar foreach petlje !
					
					
						for(KreditniSluzbenik k : _kreditniSluzbenici){
							if(k.getJmbg().equals((String)_table.getValueAt(_foo, 1))){
								_toBePDFGenerated=k;
							}

						}
						SharedLogika _sharedLogika= new SharedLogika();
						_sharedLogika.generisiPDF(_toBePDFGenerated);
						_sharedLogika.otvoriPDF(_toBePDFGenerated);
							
				}
				catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(null, "Niste odabrali uposlenika čije podatke želite prikazati u pdf formatu!");
				} 
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Nešto je krenulo po zlu! ERROR: pr1k4z 3rr0r");
				}
				
				//JOptionPane.showMessageDialog(null, "Nije implementirano !");
			}
		});
		button.setBounds(10, 326, 101, 23);
		panel_2.add(button);
		
		JButton button_2 = new JButton("Promijeni informacije");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				java.sql.Date _datum = null;
				SimpleDateFormat _sdf1 = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date _date;
				try 
				{
					_date = _sdf1.parse("07-09-1992");
					_datum=new java.sql.Date(_date.getTime());  
				}
				
				catch (ParseException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				KreditniSluzbenik _toBeEdited=null;
				try {
					int _foo= _table.getSelectedRow();
					if(_foo==-1) throw new NullPointerException();
						//JOptionPane.showMessageDialog(null, "Niste odabrali uposlenika čije podatke želite promijeniti!");
					//pomocna varijabla jer se remove ne moze uraditi kako treba unutar foreach petlje !
					
					
						for(KreditniSluzbenik k : _kreditniSluzbenici){
							if(k.getJmbg().equals((String)_table.getValueAt(_foo, 1))){
								_toBeEdited=k;
							}

						}
						SefGui.EditUposlenici n =new SefGui.EditUposlenici(_toBeEdited);  //kreira Uposlenici gui za �efa
						n.setLocationRelativeTo(null);   // postavlja ga na sredinu
						n.setVisible(true);  // upali vidljivost
						n.setResizable(false);
						frame.setEnabled(false);
		
				}
				catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(null, "Niste odabrali uposlenika čije podatke želite promijeniti!");
				} 
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Nešto je krenulo po zlu! ERROR: pr0mjen4 3rr0r");
				}
		
	
				
				

			}
		});
		button_2.setBounds(121, 326, 202, 23);
		panel_2.add(button_2);
		
		JButton button_3 = new JButton("Izbri\u0161i ");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					int _foo= _table.getSelectedRow();
					if(_foo==-1)JOptionPane.showMessageDialog(null, "Niste odabrali uposlenika kojeg želite obrisati!");
					//pomocna varijabla jer se remove ne moze uraditi kako treba unutar foreach petlje !
					KreditniSluzbenik _toBeDeleted=null;
					
						for(KreditniSluzbenik k : _kreditniSluzbenici){
							if(k.getJmbg().equals((String)_table.getValueAt(_foo, 1))){
								_toBeDeleted=k;
								new UposlenikLogika().softDeleteByJMBG(k.getJmbg());
							}

						}
						_kreditniSluzbenici.remove(_toBeDeleted);
						_table.setModel(new SluzbenikTableModel(_kreditniSluzbenici));
						JOptionPane.showMessageDialog(null, "Uspješno obrisano!");
						
						//refresh tabele
						_table.invalidate();
						_table.revalidate();
						_table.repaint();
				}
				catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(null, "Niste odabrali uposlenika kojeg želite obrisati!");
				} 
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Nešto je krenulo po zlu! ERROR: d3l3t4 3rr0r");
				}
			}
		});
		button_3.setBounds(333, 326, 79, 23);
		panel_2.add(button_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new LineBorder(new Color(165, 42, 42), 1, true));
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(10, 11, 380, 32);
		panel_2.add(panel_4);
		
		JLabel label_10 = new JLabel("Unesi ime i prezime:");
		label_10.setBounds(6, 9, 131, 14);
		panel_4.add(label_10);
		
		tf_pretraga = new JTextField();
		tf_pretraga.setColumns(10);
		tf_pretraga.setBounds(125, 6, 245, 20);
		panel_4.add(tf_pretraga);
		
		JLabel label_11 = new JLabel("");
		label_11.setBounds(196, 16, 0, 0);
		panel_4.add(label_11);
		
		JButton button_4 = new JButton("Pretraga");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(!tf_pretraga.getText().equals("")){
					_kreditniSluzbenici=new UposlenikLogika().getByName(tf_pretraga.getText());
					if(_kreditniSluzbenici.size()!=0){

						if(_table==null){ 
							_table = new JTable();
							_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						}
						_table.setModel(new SluzbenikTableModel(_kreditniSluzbenici));
						if(_scrollPane==null){
							_scrollPane = new JScrollPane(_table);
							_scrollPane.setViewportView(_table);
							panel_3.add(_scrollPane);
						}
						panel_3.revalidate();
						panel_3.repaint();
					}
					else JOptionPane.showMessageDialog(null, "Ne postoji uposlenik sa tim imenom.");
					}
					else JOptionPane.showMessageDialog(null, "Niste unijeli ime i prezime uposlenika.");
				} catch (HeadlessException e1) {
					 JOptionPane.showMessageDialog(null, "Nešto je pošlo po zlu! ERROR: pr3tr4g4");
				}
	
			}
		});
		button_4.setBounds(400, 11, 112, 32);
		panel_2.add(button_4);
		
		JButton button_5 = new JButton("Nazad");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SefGui.Uposlenici.this.dispose();
			}
		});
		button_5.setBounds(424, 326, 89, 23);
		panel_2.add(button_5);
	}
}
