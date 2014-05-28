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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import logic.UposlenikLogika;

import javax.swing.JTable;
import javax.swing.JTextPane;

public class EditUposlenici extends JFrame {

	private JPanel contentPane;
	private JTextField tf_adresa;
	private static JTextField tf_ime;
	private JTextField tf_prezime;
	private JTextField tf_datum;
//	private JTextField tf_jmbg;
	private JTextField tf_email;
	private JTextField tf_telefon;
	private JTextField tf_sifra;
	private JTextField tf_plata;
	private JTextField tf_mjestoRodjenja;
	private  JTable _table=null;
	private  List<KreditniSluzbenik> _kreditniSluzbenici=null;
	private JScrollPane _scrollPane=null;
	private Uposlenik trenutni;
	private JLabel lbl_jmbg=null;

	/**
	 * Launch the application.
	 */
	//ovaj konstruktor je samo u slucaju da se aplikacija pokrece iz ove forme a nama to ne treba
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						EditUposlenici frame = new EditUposlenici(new KreditniSluzbenik());
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
	public EditUposlenici(KreditniSluzbenik _kreditniSluzbenik) {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				SefGui.Uposlenici.frame.setEnabled(true);
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
		tabbedPane.addTab("Izmjena podataka", null, panel, null);
		
		JButton btnNazad = new JButton("Nazad");
		btnNazad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SefGui.EditUposlenici.this.dispose();
				
			}
		});
		btnNazad.setBounds(421, 324, 98, 23);
		panel.add(btnNazad);
		
		JButton btnSpasiPromjene = new JButton("Spasi promjene");
		btnSpasiPromjene.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
			
				//TODO: Uraditi validaciju jmbg-a, datuma , email-a
				
				
				UposlenikLogika _uposlenikLogika = new UposlenikLogika();

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
							lbl_jmbg.getText(),
							_datum,
							"null",
							tf_telefon.getText(),
							tf_adresa.getText(),
							tf_email.getText(),
							tf_sifra.getText(),
							tf_mjestoRodjenja.getText(),
							Double.parseDouble(tf_plata.getText())
						);
				_kreditniSluzbenik.setPlata(Double.parseDouble(tf_plata.getText()));
				try {
						_uposlenikLogika.promijeniUposlenika(_kreditniSluzbenik);
						JOptionPane.showMessageDialog(null, "Promjene uspješno spašene !");
						SefGui.EditUposlenici.this.dispose();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Nešto je pošlo naopako ! ERROR: pr0mij3n1 Up0sl3n1k4");
				}	

			}
		});
		btnSpasiPromjene.setBounds(10, 324, 142, 23);
		panel.add(btnSpasiPromjene);
		
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
		tf_adresa.setText(_kreditniSluzbenik.getAdresa());
		panel_1.add(tf_adresa);
		//razdvoji ime i prezime
		String[] split = _kreditniSluzbenik.getImePrezime().split(" ");
		tf_ime = new JTextField();
		tf_ime.setColumns(10);
		tf_ime.setBounds(92, 56, 155, 20);
		tf_ime.setText(split[0]);
		panel_1.add(tf_ime);
		
		tf_prezime = new JTextField();
		tf_prezime.setColumns(10);
		tf_prezime.setBounds(341, 56, 155, 20);
		String _help;
		if(split.length>1) 
			_help=split[1]; 
		else
			_help="";
		tf_prezime.setText(_help);
		panel_1.add(tf_prezime);
		
		tf_datum = new JTextField();
		tf_datum.setColumns(10);
		tf_datum.setBounds(92, 106, 155, 20);
		//date to string
		DateFormat _df = new SimpleDateFormat("dd-MM-yyyy");  
		String _text = _df.format(_kreditniSluzbenik.getDatumRodjenja()); 
		tf_datum.setText(_text);
		panel_1.add(tf_datum);
		

		
		tf_email = new JTextField();
		tf_email.setColumns(10);
		tf_email.setBounds(92, 154, 155, 20);
		tf_email.setText(_kreditniSluzbenik.getEmail());
		panel_1.add(tf_email);
		
		tf_telefon = new JTextField();
		tf_telefon.setColumns(10);
		tf_telefon.setBounds(341, 198, 155, 20);
		tf_telefon.setText(_kreditniSluzbenik.getTelefon());
		panel_1.add(tf_telefon);
		
		JLabel label_7 = new JLabel("\u0160ifra:");
		label_7.setHorizontalAlignment(SwingConstants.TRAILING);
		label_7.setBounds(288, 246, 46, 19);
		panel_1.add(label_7);
		
		tf_sifra = new JTextField();
		tf_sifra.setColumns(10);
		tf_sifra.setBounds(341, 244, 86, 22);
		tf_sifra.setText(_kreditniSluzbenik.getPassword());
		panel_1.add(tf_sifra);
		
		JLabel label_8 = new JLabel("Plata:");
		label_8.setHorizontalAlignment(SwingConstants.TRAILING);
		label_8.setBounds(19, 204, 70, 14);
		panel_1.add(label_8);
		
		tf_plata = new JTextField();
		tf_plata.setColumns(10);
		tf_plata.setBounds(92, 198, 155, 20);
		tf_plata.setText(String.valueOf(_kreditniSluzbenik.getPlata()));
		panel_1.add(tf_plata);
		
		JLabel label_9 = new JLabel("Mjesto ro\u0111enja:");
		label_9.setHorizontalAlignment(SwingConstants.TRAILING);
		label_9.setBounds(232, 112, 102, 14);
		panel_1.add(label_9);
		
		tf_mjestoRodjenja = new JTextField();
		tf_mjestoRodjenja.setColumns(10);
		tf_mjestoRodjenja.setBounds(341, 106, 155, 20);
		tf_mjestoRodjenja.setText(_kreditniSluzbenik.getMjestoRodjenja());
		panel_1.add(tf_mjestoRodjenja);
		
		JLabel lblPodaciONovom = new JLabel("Podaci o odabranom uposleniku:");
		lblPodaciONovom.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPodaciONovom.setBounds(151, 0, 233, 33);
		panel_1.add(lblPodaciONovom);
		
		JLabel label_12 = new JLabel("Datum rođenja:");
		label_12.setHorizontalAlignment(SwingConstants.TRAILING);
		label_12.setBounds(-14, 109, 102, 14);
		panel_1.add(label_12);
		
		lbl_jmbg = new JLabel(_kreditniSluzbenik.getJmbg());
		lbl_jmbg.setHorizontalAlignment(SwingConstants.TRAILING);
		lbl_jmbg.setBounds(101, 251, 146, 14);
		panel_1.add(lbl_jmbg);
	}
}
