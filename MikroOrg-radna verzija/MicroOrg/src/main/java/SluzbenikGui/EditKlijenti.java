package SluzbenikGui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.GroupLayout;
import javax.swing.ListSelectionModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import aplikacija.MicroOrg.Spremnik;
import viewModels.KlijentSluzbenik;
import viewModels.KlijentTableModel;
import viewModels.KreditniSluzbenik;
import viewModels.SluzbenikTableModel;
import logic.KlijentLogika;
import logic.UposlenikLogika;
import domainModels.Klijent;
import domainModels.Uposlenik;

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

public class EditKlijenti extends JFrame {

	private JPanel contentPane;
	private JTextField tf_adresa;
	private JTextField tf_ime;
	private JTextField tf_prezime;
	private JTextField tf_datum;
	private JTextField tf_email;
	private JTextField tf_telefon;
	private Uposlenik trenutni;
	JScrollPane _scrollPane = null;
	private  List<KlijentSluzbenik> _sviKlijenti=null;
	JTable _table = null;
	static EditKlijenti frame;
	/**
	 * Launch the application.
	 */
	//ovaj nam konstruktor ne treba jer se aplikacija ne pokrece iz ove forme
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditKlijenti frame = new EditKlijenti(new KlijentSluzbenik());
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
	public EditKlijenti(KlijentSluzbenik _klijentSluzbenik) {
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosed(WindowEvent e) {
				SluzbenikGui.Klijenti n =new SluzbenikGui.Klijenti();  //kreira novi po�etni gui za sluzbenika
				 
			}
		});
		trenutni=Spremnik.getTrenutni();
		setTitle("MikroOrg - Klijenti");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 460, 468);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 434, 419);
		tabbedPane.setBackground(Color.WHITE);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Unos klijenta", null, panel, null);
		
		JButton btnNazad = new JButton("Poništi");
		btnNazad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SluzbenikGui.EditKlijenti.this.dispose();
			}
		});
		btnNazad.setBounds(294, 295, 95, 23);
		final JLabel lbl_jmbg = new JLabel(_klijentSluzbenik.getJmbg());
		JButton btnSpasiPromjene = new JButton("Spasi ");
		btnSpasiPromjene.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KlijentLogika _klijentLogika = new KlijentLogika();

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

				KlijentSluzbenik _klijent= new KlijentSluzbenik
						(
							tf_ime.getText()+" "+tf_prezime.getText(),
							lbl_jmbg.getText(),
							_datum,
							tf_telefon.getText(),
							tf_adresa.getText(),
							tf_email.getText(),
							"null"
						);
				try {
						_klijentLogika.promijeniKlijenta(_klijent);
						JOptionPane.showMessageDialog(null, "Promjene uspješno spašene !");
						SluzbenikGui.EditKlijenti.this.dispose();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Nešto je pošlo naopako ! ERROR: pr0mij3n1 Up0sl3n1k4");
				}	
			}
			});
		btnSpasiPromjene.setBounds(189, 295, 95, 23);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(45, 55, 344, 202);
		panel_1.setBorder(new LineBorder(new Color(165, 42, 42), 1, true));
		panel_1.setBackground(Color.WHITE);
		
		JLabel label = new JLabel("Adresa:");
		
		JLabel label_1 = new JLabel("Telefon:");
		
		JLabel label_2 = new JLabel("Email:");
		
		JLabel label_3 = new JLabel("JMBG:");
		
		JLabel label_4 = new JLabel("Datum ro\u0111enja:");
		
		JLabel label_5 = new JLabel("Ime:");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel label_6 = new JLabel("Prezime:");
		
		tf_adresa = new JTextField();
		tf_adresa.setColumns(10);
		tf_adresa.setText(_klijentSluzbenik.getAdresa());
		
		String[] split = _klijentSluzbenik.getImePrezime().split(" ");
		
		tf_ime = new JTextField();
		tf_ime.setColumns(10);
		tf_ime.setText(split[0]);
		
		tf_prezime = new JTextField();
		tf_prezime.setColumns(10);
		String _help;
		if(split.length>1) 
			_help=split[1]; 
		else
			_help="";
		tf_prezime.setText(_help);
		tf_prezime.setText(split[1]);
		
		tf_datum = new JTextField();
		tf_datum.setColumns(10);
		DateFormat _df = new SimpleDateFormat("dd-MM-yyyy");  
		String _text = _df.format(_klijentSluzbenik.getDatumRodjenja()); 
		tf_datum.setText(_text);
		
		
		tf_email = new JTextField();
		tf_email.setColumns(10);
		tf_email.setText(_klijentSluzbenik.getEmail());
		
		tf_telefon = new JTextField();
		tf_telefon.setColumns(10);
		tf_telefon.setText(_klijentSluzbenik.getTelefon());
		
	
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addComponent(label)
								.addComponent(label_1)
								.addComponent(label_2)
								.addComponent(label_3)
								.addComponent(label_4))
							.addPreferredGap(ComponentPlacement.UNRELATED))
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
							.addComponent(label_5, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(label_6, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
						.addComponent(tf_adresa, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
						.addComponent(tf_ime, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
						.addComponent(tf_prezime, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
						.addComponent(tf_datum, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
						.addComponent(tf_email, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
						.addComponent(tf_telefon, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
						.addComponent(lbl_jmbg, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(41))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(tf_ime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_5))
					.addGap(5)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(tf_prezime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_6))
					.addGap(6)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(tf_datum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_4))
					.addGap(6)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(lbl_jmbg))
					.addGap(6)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(tf_email, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2))
					.addGap(6)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(tf_telefon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(tf_adresa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		panel.add(panel_1);
		panel.add(btnNazad);
		panel.add(btnSpasiPromjene);
	}
}
