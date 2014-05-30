package SluzbenikGui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.ListSelectionModel;

import viewModels.KlijentSluzbenik;
import viewModels.KreditnaPonuda;
import viewModels.TipKreditaSluzbenik;
import viewModels.PonudaTableModel;
import aplikacija.MicroOrg.Spremnik;
import domainModels.Uposlenik;
import logic.KlijentLogika;
import logic.PonudeLogika;
import logic.TipKreditaLogika;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.FlowLayout;

public class Ponude extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private Uposlenik trenutni;
	
	private JTable _table = null;
	private JScrollPane _scrollPane = null;
	List<KlijentSluzbenik> klijenti = new ArrayList<KlijentSluzbenik>();
	/**
	 * Launch the application.
	 */
	//ovaj nam konstruktor ne treba jer se aplikacija ne pokrece iz ove forme
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ponude frame = new Ponude();
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
	public Ponude() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				SluzbenikGui.Pocetni n =new SluzbenikGui.Pocetni();  //kreira novi po�etni gui za sluzbenika
				n.setLocationRelativeTo(null);   // postavlja ga na sredinu
				n.setVisible(true);  // upali vidljivost
				n.setResizable(false);
			}
		});
		trenutni=Spremnik.getTrenutni();
		setTitle("MicroOrg - Ponude");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 554, 441);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 528, 398);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Unos nove ponude", null, panel, null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(139, 0, 0), 1, true));
		panel_1.setBackground(Color.WHITE);
		
		JLabel label = new JLabel("Instrumenti obezbje\u0111enja:");
		label.setHorizontalAlignment(SwingConstants.TRAILING);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("Tro\u0161kovi obrade:");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel label_2 = new JLabel("Tro\u0161kovi obrade:");
		
		JLabel label_3 = new JLabel("Iznos kredita:");
		
		JLabel label_4 = new JLabel("Rok vra\u0107anja kredita:");
		
		JLabel label_5 = new JLabel("Namjena kredita:");
		
		JLabel label_6 = new JLabel("Izbor tipa kredita:");
		
		JLabel label_7 = new JLabel("Prezime klijenta:");
		
		JLabel label_8 = new JLabel("Ime klijenta:");
		
		JLabel label_9 = new JLabel("Garancija:");
		
		JLabel label_10 = new JLabel("Grace period:");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		
		//dodavanje tipovaKredita iz baze u comboBox
		final TipKreditaLogika a = new TipKreditaLogika();
		List<TipKreditaSluzbenik> tipovi = a.getAll();
		Vector comboBoxItems=new Vector();
		for (int i=0;i<tipovi.size();i++)
		{
			comboBoxItems.add(tipovi.get(i).getNaziv());
		}
		final DefaultComboBoxModel model = new DefaultComboBoxModel(comboBoxItems); 
		final JComboBox comboBox = new JComboBox(model);
		comboBox.setSelectedIndex(-1);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 395, Short.MAX_VALUE)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addComponent(label_2)
								.addComponent(label_3)
								.addComponent(label_4)
								.addComponent(label_5)
								.addComponent(label_6)
								.addComponent(label_7)
								.addComponent(label_8)
								.addComponent(label_9)
								.addComponent(label_10))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField_2)
								.addComponent(textField_3)
								.addComponent(textField_4)
								.addComponent(textField_5)
								.addComponent(textField_6)
								.addComponent(textField_7)
								.addComponent(textField_8)
								.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(textField_9, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(82, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 288, Short.MAX_VALUE)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_8)
						.addComponent(textField_9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_7)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_6)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_5)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_4)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_9)
						.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_10)
						.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(3)
							.addComponent(label)))
					.addGap(213)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(190)
							.addComponent(label_1))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(187)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JButton btnNazad = new JButton("Nazad");
		btnNazad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SluzbenikGui.Ponude.this.dispose();
			}
		});
		
		JButton button_1 = new JButton("Kreiraj ponudu");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PonudeLogika p = new PonudeLogika();
				if(!p.postojiKlijent(textField_9.getText(), textField_2.getText())) {
					JOptionPane.showMessageDialog(null, "Ne postoji uneseni klijent !");
				}
				else {
					if(comboBox.getSelectedIndex() != -1) {
						String nazivTipaKredita = comboBox.getSelectedItem().toString();
						KreditnaPonuda kp = new KreditnaPonuda(Spremnik.getTrenutni(), p.dajKlijenta((String)(textField_9.getText()), (String)(textField_2.getText())) , p.dajTipKredita(nazivTipaKredita), "25.5.2014.");
						p.dodajPonudu(kp);
						JOptionPane.showMessageDialog(null, "Unos uspjesan !");
						
					}
					else {
						if(!(textField_3.getText().equals("") || textField_4.getText().equals("") || textField_5.getText().equals("") || textField_6.getText().equals("") || textField_7.getText().equals("") || textField_8.getText().equals("") || textField.getText().equals(""))){
						String namjenaTipaKredita = textField_3.getText();
						Double iznosTipaKredita = Double.parseDouble(textField_4.getText());
						String rokTipaKredita = textField_5.getText();
						String garancijaTipaKredita = textField_6.getText();
						String graceTipaKredita = textField_7.getText();
						Double troskoviTipaKredita = Double.parseDouble(textField_8.getText());
						String instrumeniTipaKredita = textField.getText();
						
						
						TipKreditaLogika _tk = new TipKreditaLogika();
						
						TipKreditaSluzbenik _tipKredita = new TipKreditaSluzbenik(
								null,
								namjenaTipaKredita,
								iznosTipaKredita,
								rokTipaKredita,
								null,
								garancijaTipaKredita,
								graceTipaKredita,
								troskoviTipaKredita
								);

						try {
						 
								Long idTipaKredita = _tk.dodajTipKredita(_tipKredita);
								//ocisti formu
								textField_3.setText("");
								textField_4.setText("");
								textField_5.setText("");
								textField_6.setText("");
								textField_7.setText("");
								textField_8.setText("");
								textField.setText("");
								
								KreditnaPonuda kp = new KreditnaPonuda(Spremnik.getTrenutni(), p.dajKlijenta((String)(textField_9.getText()), (String)(textField_2.getText())) , p.dajTipKredita(idTipaKredita), "");
								p.dodajPonudu(kp);
								JOptionPane.showMessageDialog(null, "Unos uspjesan !");
								}
						catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "Nešto je pošlo naopako ! ERROR: d0d4jUp0sl3n1k4");
						}
						
						}
						else {
							JOptionPane.showMessageDialog(null, "Sva polja moraju biti popunjena!");
						}
					}
				}
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(68, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(button_1)
							.addGap(18)
							.addComponent(btnNazad)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 395, GroupLayout.PREFERRED_SIZE)
							.addGap(60))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addGap(22)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 288, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNazad)
						.addComponent(button_1))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(Color.WHITE);
		tabbedPane.addTab("Pregled i prikaz ponude", null, panel_2, null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new LineBorder(new Color(165, 42, 42), 1, true));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(10, 11, 378, 32);
		panel_2.add(panel_3);
		
		JLabel label_11 = new JLabel("Unesi podatke:");
		label_11.setBounds(6, 9, 95, 14);
		panel_3.add(label_11);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(98, 6, 268, 20);
		panel_3.add(textField_10);
		
		JLabel label_12 = new JLabel("");
		label_12.setBounds(174, 16, 0, 0);
		panel_3.add(label_12);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBounds(398, 67, 121, 91);
		panel_2.add(panel_4);
		
		ButtonGroup grupa = new ButtonGroup();
		
		final JRadioButton radioButton = new JRadioButton("Ime i Prezime");
		radioButton.setBounds(6, 7, 109, 23);
		panel_4.add(radioButton);
		
		final JRadioButton radioButton_1 = new JRadioButton("Kredit");
		radioButton_1.setBounds(6, 33, 109, 23);
		panel_4.add(radioButton_1);
		
		final JRadioButton radioButton_2 = new JRadioButton("Datum upisa");
		radioButton_2.setBounds(6, 59, 109, 23);
		panel_4.add(radioButton_2);
		
		grupa.add(radioButton);
		grupa.add(radioButton_1);
		grupa.add(radioButton_2);
		
		final JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(128, 0, 0), 1, true));
		panel_5.setBackground(Color.WHITE);
		panel_5.setBounds(10, 54, 378, 283);
		panel_2.add(panel_5);
		
		JButton btnPretraiPo = new JButton("Pretra\u017Ei po:");
		btnPretraiPo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					PonudeLogika _pl = new PonudeLogika();
					List<KreditnaPonuda> _ponude = new ArrayList<KreditnaPonuda>();
					if(radioButton.isSelected()) {
						_ponude = _pl.traziPoImenuKlijenta(textField_10.getText());
					}
					else if(radioButton_1.isSelected()) {
						_ponude = _pl.traziPoTipuKredita(textField_10.getText());
					}
					else if(radioButton_2.isSelected()) {
						_ponude = _pl.traziPoDatumu(textField_10.getText());
					}
					/*_svePonude = new ArrayList<KreditnaPonuda>();
					for(KlijentSluzbenik k : _klijenti){
						_svePonude.add(k);
					}*/
									
					if(_ponude.size() != 0) {
										
					if(_table == null) { 
						_table = new JTable();
						_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					}
					_table.setModel(new PonudaTableModel(_ponude));

					if(_scrollPane == null){
						_scrollPane = new JScrollPane(_table);
											

						//_table.setFillsViewportHeight(true);
											

						_scrollPane.setViewportView(_table);
						panel_5.add(_scrollPane);
					}
										
					panel_5.revalidate();
					panel_5.repaint();
					}
					else JOptionPane.showMessageDialog(null, "Ne postoji ponuda izdata unesenom klijentu.");
				}
				catch(HeadlessException e1) {
					JOptionPane.showMessageDialog(null, "Nešto je pošlo po zlu! ERROR: pr3tr4g4");
				} 
			}
			
		});
		btnPretraiPo.setBounds(398, 11, 121, 23);
		panel_2.add(btnPretraiPo);
		
		JButton btnNazad_1 = new JButton("Nazad");
		btnNazad_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SluzbenikGui.Ponude.this.dispose();
			}
		});
		btnNazad_1.setBounds(426, 340, 93, 23);
		panel_2.add(btnNazad_1);
		
		JButton button_4 = new JButton("Izbri\u0161i");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Nije implementirano !");
			}
		});
		button_4.setBounds(256, 340, 78, 23);
		panel_2.add(button_4);
		
		JButton btnPdfPrikaz = new JButton("PDF prikaz");
		btnPdfPrikaz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Nije implementirano !");
			}
		});
		btnPdfPrikaz.setBounds(159, 340, 87, 23);
		panel_2.add(btnPdfPrikaz);
		
		JPanel panel_6 = new JPanel();
		panel_6.setLayout(null);
		
		JRadioButton radioButton_3 = new JRadioButton("Ime i Prezime");
		radioButton_3.setBounds(6, 7, 109, 23);
		panel_6.add(radioButton_3);
		
		JRadioButton radioButton_4 = new JRadioButton("Kredit");
		radioButton_4.setBounds(6, 33, 109, 23);
		panel_6.add(radioButton_4);
		
		JRadioButton radioButton_5 = new JRadioButton("Uposlenik");
		radioButton_5.setBounds(6, 59, 109, 23);
		panel_6.add(radioButton_5);
		
		JRadioButton radioButton_6 = new JRadioButton("ID");
		radioButton_6.setBounds(6, 85, 109, 23);
		panel_6.add(radioButton_6);
		
		JRadioButton radioButton_7 = new JRadioButton("Datum upisa");
		radioButton_7.setBounds(6, 111, 109, 23);
		panel_6.add(radioButton_7);
		
		JRadioButton radioButton_8 = new JRadioButton("E-mail");
		radioButton_8.setBounds(6, 137, 109, 23);
		panel_6.add(radioButton_8);
		
		JRadioButton radioButton_9 = new JRadioButton("Adresa");
		radioButton_9.setBounds(6, 163, 109, 23);
		panel_6.add(radioButton_9);
		
		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);
		
		JRadioButton radioButton_10 = new JRadioButton("Ime i Prezime");
		radioButton_10.setBounds(6, 7, 109, 23);
		panel_7.add(radioButton_10);
		
		JRadioButton radioButton_11 = new JRadioButton("Kredit");
		radioButton_11.setBounds(6, 33, 109, 23);
		panel_7.add(radioButton_11);
		
		JRadioButton radioButton_12 = new JRadioButton("Uposlenik");
		radioButton_12.setBounds(6, 59, 109, 23);
		panel_7.add(radioButton_12);
		
		JRadioButton radioButton_13 = new JRadioButton("ID");
		radioButton_13.setBounds(6, 85, 109, 23);
		panel_7.add(radioButton_13);
		
		JRadioButton radioButton_14 = new JRadioButton("Datum upisa");
		radioButton_14.setBounds(6, 111, 109, 23);
		panel_7.add(radioButton_14);
		
		JRadioButton radioButton_15 = new JRadioButton("E-mail");
		radioButton_15.setBounds(6, 137, 109, 23);
		panel_7.add(radioButton_15);
		
		JRadioButton radioButton_16 = new JRadioButton("Adresa");
		radioButton_16.setBounds(6, 163, 109, 23);
		panel_7.add(radioButton_16);
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_5.add(panel_6);
		panel_5.add(panel_7);
		
		JButton btnEmail = new JButton("E-mail");
		btnEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Nije implementirano !");
			}
		});
		btnEmail.setBounds(10, 340, 139, 23);
		panel_2.add(btnEmail);
	}

}
