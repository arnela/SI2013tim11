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
import logic.SharedLogika;
import logic.UposlenikLogika;
import domainModels.Klijent;
import domainModels.Uposlenik;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Klijenti extends JFrame {

	private JPanel contentPane;
	private JTextField tf_adresa;
	private JTextField tf_ime;
	private JTextField tf_prezime;
	private JTextField tf_datum;
	private JTextField tf_jmbg;
	private JTextField tf_email;
	private JTextField tf_telefon;
	private JTextField tf_pretraga;
	private Uposlenik trenutni;
	JScrollPane _scrollPane = null;
	private  List<KlijentSluzbenik> _sviKlijenti=null;
	JTable _table = null;
	static Klijenti frame;
	private JTextField tf_status;
	/**
	 * Launch the application.
	 */
 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					 frame = new Klijenti();
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
	public Klijenti() {
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosed(WindowEvent e) {
				Spremnik.setKlijenti(SluzbenikGui.Klijenti.this);
				Spremnik.getKlijenti().hide();
				Spremnik.getPocetni().show();
			}
		});
		trenutni=Spremnik.getTrenutni();
		setTitle("MicroOrg - Klijenti");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 516, 468);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 500, 419);
		tabbedPane.setBackground(Color.WHITE);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Unos klijenta", null, panel, null);
		
		JButton btnNazad = new JButton("Nazad");
		btnNazad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SluzbenikGui.Klijenti.this.dispose();
			}
		});
		btnNazad.setBounds(333, 312, 95, 23);
		
		JButton button_1 = new JButton("Unesi klijenta");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KlijentLogika _klijentLogika = new KlijentLogika();
				String _status="Nije ok";
				
				//VALIDACIJA
				try {
					 _status=_klijentLogika.validirajPodatke(
							 	tf_ime.getText(),
							 	tf_prezime.getText(),
								tf_jmbg.getText(),
								tf_datum.getText(),
								tf_telefon.getText(),
								tf_adresa.getText(),
								tf_email.getText(),
								tf_status.getText()
							 );
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Validacija error !");
				}
				
				if (_status == "OK"){
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
				
				KlijentSluzbenik _klijenti = new KlijentSluzbenik(
						tf_ime.getText()+" " + tf_prezime.getText(),
						tf_jmbg.getText(),
						_datum,
						tf_telefon.getText(),
						tf_adresa.getText(),
						tf_email.getText(),
						tf_status.getText()
						);

				try {
				//		if(!_klijentLogika.daLiPostoji(tf_jmbg.getText())){
							_klijentLogika.dodajKlijenta(_klijenti);
						//ocisti formu
						tf_ime.setText("");
						tf_prezime.setText("");
						tf_jmbg.setText("");
						tf_datum.setText("");
						tf_telefon.setText("");
						tf_adresa.setText("");
						tf_email.setText("");
						tf_status.setText("");
						JOptionPane.showMessageDialog(null, "Uspješno evidentirano !");
			//		}
			//		else
			//			JOptionPane.showMessageDialog(null, "Klijent sa unesenim jmbg-om već postoji !");
			//		}
						}
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Nešto je pošlo naopako !");
				}
				}
				else JOptionPane.showMessageDialog(null, _status);
			}
			});
		button_1.setBounds(205, 312, 124, 23);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(84, 54, 344, 229);
		panel_1.setBorder(new LineBorder(new Color(165, 42, 42), 1, true));
		panel_1.setBackground(Color.WHITE);
		
		JLabel label = new JLabel("Adresa:");
		
		JLabel label_1 = new JLabel("Telefon:");
		label_1.setToolTipText("111-222-333");
		
		JLabel label_2 = new JLabel("Email:");
		label_2.setToolTipText("Sadrzi '@' i '.' karaktere");
		
		JLabel label_3 = new JLabel("JMBG:");
		
		JLabel label_4 = new JLabel("Datum ro\u0111enja:");
		label_4.setToolTipText("dd-MM-yyyy");
		
		JLabel label_5 = new JLabel("Ime:");
		label_5.setToolTipText("Početno slovo je veliko, ne smije sadržavati karaktere koji nisu slova ");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel label_6 = new JLabel("Prezime:");
		label_6.setToolTipText("Početno slovo je veliko, ne smije sadržavati karaktere koji nisu slova ");
		
		tf_adresa = new JTextField();
		tf_adresa.setColumns(10);
		
		tf_ime = new JTextField();
		tf_ime.setColumns(10);
		
		tf_prezime = new JTextField();
		tf_prezime.setColumns(10);
		
		tf_datum = new JTextField();
		tf_datum.setColumns(10);
		
		tf_jmbg = new JTextField();
		tf_jmbg.setColumns(10);
		
		tf_email = new JTextField();
		tf_email.setColumns(10);
		
		tf_telefon = new JTextField();
		tf_telefon.setColumns(10);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setToolTipText("Alfanumerički opis statusa klijenta");
		
		tf_status = new JTextField();
		tf_status.setColumns(10);
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
								.addComponent(label_4)
								.addComponent(lblStatus))
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
						.addComponent(tf_jmbg, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
						.addComponent(tf_email, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
						.addComponent(tf_telefon, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
						.addComponent(tf_status))
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
						.addComponent(tf_jmbg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3))
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
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStatus)
						.addComponent(tf_status, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		panel.add(panel_1);
		panel.add(btnNazad);
		panel.add(button_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		tabbedPane.addTab("Prikaz i pretraga klijenata", null, panel_2, null);
		panel_2.setLayout(null);
		
		final JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 54, 475, 261);
		panel_3.setBorder(new LineBorder(new Color(128, 0, 0), 1, true));
		panel_3.setBackground(Color.WHITE);
		panel_2.add(panel_3);
		
		JButton btnPdfPrikaz = new JButton("PDF prikaz");
		btnPdfPrikaz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KlijentSluzbenik _toBePDFGenerated=null;
				try {
					int _foo= _table.getSelectedRow();
					if(_foo==-1) throw new NullPointerException();
						
						for(KlijentSluzbenik k : _sviKlijenti){
							if(k.getJmbg().equals((String)_table.getValueAt(_foo, 1))){
								_toBePDFGenerated=k;
							}

						}
						SharedLogika _sharedLogika= new SharedLogika();
						_sharedLogika.generisiPDF(_toBePDFGenerated);
						_sharedLogika.otvoriPDF(_toBePDFGenerated);
							
				}
				catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(null, "Niste odabrali klijenta čije podatke želite prikazati u pdf formatu!");
				} 
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Nešto je krenulo po zlu! ERROR: pr1k4z 3rr0r");
				}
				
			}
		});
		btnPdfPrikaz.setBounds(10, 326, 129, 23);
		panel_2.add(btnPdfPrikaz);
		
		JButton btnEmail = new JButton("E-mail");
		btnEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				KlijentSluzbenik _toBePDFGenerated=null;
				try {
					int _foo= _table.getSelectedRow();
					if(_foo==-1) throw new NullPointerException();
						
						for(KlijentSluzbenik k : _sviKlijenti){
							if(k.getJmbg().equals((String)_table.getValueAt(_foo, 1))){
								_toBePDFGenerated=k;
							}

						}
						SharedLogika _sharedLogika= new SharedLogika();
						Spremnik.setObjekatPDF(_toBePDFGenerated);
						Mail m = new Mail();
						m.setVisible(true);
							
				}
				catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(null, "Niste odabrali klijenta čije podatke želite prikazati u pdf formatu!");
				} 
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Nešto je krenulo po zlu! ERROR: pr1k4z 3rr0r");
				}
				
				 
			}
		});
		btnEmail.setBounds(10, 357, 129, 23);
		panel_2.add(btnEmail);
		
		JButton button_4 = new JButton("Promjeni podatke");
		button_4.addActionListener(new ActionListener() {
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
				KlijentSluzbenik _toBeEdited= null;
				try {
					int _foo= _table.getSelectedRow();
					if(_foo==-1) throw new NullPointerException();

					
						for(KlijentSluzbenik k : _sviKlijenti){
							if(k.getJmbg().equals((String)_table.getValueAt(_foo, 1))){
								_toBeEdited=k;
							}

						}
						SluzbenikGui.EditKlijenti n =new SluzbenikGui.EditKlijenti(_toBeEdited); 
						n.setLocationRelativeTo(null);   // postavlja ga na sredinu
						n.setVisible(true);  // upali vidljivost
						n.setResizable(false);
						frame.setEnabled(false); 
		
				}
				catch (NullPointerException e1) {
				
					JOptionPane.showMessageDialog(null, "Niste odabrali klijenta čije podatke želite promijeniti!");
				} 
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Nešto je krenulo po zlu! ERROR: pr0mjen4 3rr0r");
				}
				
	
			}
		});
		button_4.setBounds(190, 326, 139, 23);
		panel_2.add(button_4);
		
		JButton button_5 = new JButton("Izbri\u0161i ");
		
		button_5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					int _foo= _table.getSelectedRow();
					if(_foo==-1)JOptionPane.showMessageDialog(null, "Niste odabrali klijenta kojeg želite obrisati!");
					//pomocna varijabla jer se remove ne moze uraditi kako treba unutar foreach petlje !
					KlijentSluzbenik _toBeDeleted=null;
					
						for(KlijentSluzbenik k : _sviKlijenti){
							if(k.getJmbg().equals((String)_table.getValueAt(_foo, 1))){
								_toBeDeleted=k;
								new KlijentLogika().softDeleteByJMBG(k.getJmbg());
							}

						}
						_sviKlijenti.remove(_toBeDeleted);
						_table.setModel(new KlijentTableModel(_sviKlijenti));
						JOptionPane.showMessageDialog(null, "Uspješno obrisano!");
						
						//refresh tabele
						_table.invalidate();
						_table.revalidate();
						_table.repaint();
				}
				catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(null, "Niste odabrali klijenta kojeg želite obrisati!");
				} 
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Nešto je krenulo po zlu! ERROR: d3l3t4 3rr0r");
				}
			}
			
		});
		button_5.setBounds(396, 326, 89, 23);
		panel_2.add(button_5);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 11, 344, 32);
		panel_4.setBorder(new LineBorder(new Color(165, 42, 42), 1, true));
		panel_4.setBackground(Color.WHITE);
		panel_2.add(panel_4);
		
		JLabel label_7 = new JLabel("Unesi ime i prezime:");
		panel_4.add(label_7);
		
		tf_pretraga= new JTextField();
		tf_pretraga.setHorizontalAlignment(SwingConstants.LEFT);
		tf_pretraga.setColumns(10);
		panel_4.add(tf_pretraga);
		
		JLabel label_8 = new JLabel("");
		panel_4.add(label_8);
		
		JButton button_6 = new JButton("Pretraga");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String _status="Nije ok";
				KlijentLogika _klijentLogika= new KlijentLogika();
				//VALIDACIJA
				try {
					 _status=_klijentLogika.validirajPretragu(tf_pretraga.getText());
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Validacija error !");
				}
				
				if (_status == "OK"){
				try{
				
				List<KlijentSluzbenik> _klijenti=_klijentLogika.getByName(tf_pretraga.getText());
				_sviKlijenti= new ArrayList<KlijentSluzbenik>();
				for(KlijentSluzbenik k : _klijenti){
					 _sviKlijenti.add(k);
				}
				
				if(_klijenti.size()!=0){
					
					if(_table==null){ 
						_table = new JTable();
						_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					}
					_table.setModel(new KlijentTableModel(_klijenti));
			
					if(_scrollPane==null){
						_scrollPane = new JScrollPane(_table);
						_scrollPane.setViewportView(_table);
						panel_3.add(_scrollPane);
					}
					    
 
				panel_3.revalidate();
				panel_3.repaint();
				}
				else JOptionPane.showMessageDialog(null, "Ne postoji klijent sa tim imenom.");
			}
				
			catch(HeadlessException e1)
			{
			 JOptionPane.showMessageDialog(null, "Nešto je pošlo po zlu! ERROR: pr3tr4g4");
			}}
				else JOptionPane.showMessageDialog(null, _status);
			}
			});
		button_6.setBounds(364, 20, 121, 23);
		panel_2.add(button_6);
		
		JButton button_7 = new JButton("Nazad");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SluzbenikGui.Klijenti.this.dispose();
			}
		});
		button_7.setBounds(396, 357, 89, 23);
		panel_2.add(button_7);
	}
}
