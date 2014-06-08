package SluzbenikGui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import java.util.ArrayList;
import java.util.List;

import aplikacija.MicroOrg.Spremnik;
import viewModels.TipKreditaSluzbenik;
import viewModels.TipKreditaTableModel;
import domainModels.TipKredita;
import domainModels.Uposlenik;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import logic.TipKreditaLogika;

public class Krediti extends JFrame {

	private JPanel contentPane;
	private JTextField tf_naziv;
	private JTextField tf_namjena;
	private JTextField tf_iznos;
	private JTextField tf_rok;
	private JTextField tf_stopa;
	private JTextField tf_garancija;
	private JTextField tf_grace;
	private JTextField tf_troskovi;
	private JTextField tf_pretraga;
	private Uposlenik trenutni;
	private List<TipKreditaSluzbenik> _krediti = null;
	private JTable _table = null;
	private JScrollPane _scrollPane = null;
	private JTextField tf_instrumenti;
	
	/**
	 * Launch the application.
	 */
	//ovaj konstruktor nam ne treba jer se aplikacija ne pokreće iz ove forme
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Krediti frame = new Krediti();
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
	public Krediti() {
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosed(WindowEvent e) {
				Spremnik.setKrediti(SluzbenikGui.Krediti.this);
				Spremnik.getKrediti().hide();
				Spremnik.getPocetni().show();
			}
		});
		trenutni=Spremnik.getTrenutni();
		setTitle("MicroOrg - Krediti");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 607, 466);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 581, 417);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Unos tipa kredita", null, panel, null);
		
		JButton btnNazad = new JButton("Nazad");
		btnNazad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SluzbenikGui.Krediti.this.dispose();
			}
		});
		
		JButton button_1 = new JButton("Kreiraj tip kredita");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TipKreditaLogika _tk = new TipKreditaLogika();
				String _status="Nije ok";
				
				//VALIDACIJA
				try {
					 _status=_tk.validirajPodatke(
							 	tf_naziv.getText(),
							 	tf_namjena.getText(),
								tf_iznos.getText(),
								tf_rok.getText(),
								tf_stopa.getText(),
								tf_garancija.getText(),
								tf_grace.getText(),
								tf_troskovi.getText(),
								tf_instrumenti.getText()
							 );
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Validacija error !");
				}
				
				if (_status == "OK"){
				TipKreditaSluzbenik _tipKredita = new TipKreditaSluzbenik(
						tf_naziv.getText(),
						tf_namjena.getText(),
						Double.parseDouble(tf_iznos.getText()),
						tf_rok.getText(),
						Double.parseDouble(tf_stopa.getText()),
						tf_garancija.getText(),
						tf_grace.getText(),
						Double.parseDouble(tf_troskovi.getText()),
						tf_instrumenti.getText()
						);

				try {
				 
							_tk.dodajTipKredita(_tipKredita);
						//ocisti formu
						tf_naziv.setText("");
						tf_namjena.setText("");
						tf_iznos.setText("");
						tf_rok.setText("");
						tf_stopa.setText("");
						tf_garancija.setText("");
						tf_grace.setText("");
						tf_troskovi.setText("");
						tf_instrumenti.setText("");
						JOptionPane.showMessageDialog(null, "Uspješno evidentirano !");
			//		}
			//		else{
			//			JOptionPane.showMessageDialog(null, "Klijent sa unesenim jmbg-om već postoji !");
			//		}
						}
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Nešto je pošlo naopako ! ERROR: d0d4jUp0sl3n1k4");
				}}
				else JOptionPane.showMessageDialog(null, _status);
			}
			});
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(128, 0, 0), 1, true));
		panel_1.setBackground(Color.WHITE);
		
		JLabel label = new JLabel("Naziv tipa kredita:");
		label.setToolTipText("Počinje sa velikim slovom, sastoji se samo od slova i razmaka");
		
		JLabel lblKamatnaStopa = new JLabel("Kamatna stopa(%):");
		lblKamatnaStopa.setToolTipText("Mora biti manja od 20%");
		
		JLabel label_2 = new JLabel("Rok vra\u0107anja kredita:");
		label_2.setToolTipText("X mjeseci/godina");
		
		JLabel label_3 = new JLabel("Garancija:");
		label_3.setToolTipText("X mjeseci/godina");
		
		JLabel label_4 = new JLabel("Grace period:");
		label_4.setToolTipText("X dana");
		
		JLabel lblTrokoviObradekm = new JLabel("Troškovi obrade(KM):");
		
		JLabel lblIznosKreditakm = new JLabel("Iznos kredita(KM):");
		lblIznosKreditakm.setToolTipText("Iznos kredita u KM, mora biti manji od 50000");
		
		JLabel label_7 = new JLabel("Namjena kredita:");
		
		tf_naziv = new JTextField();
		tf_naziv.setColumns(10);
		
		tf_namjena = new JTextField();
		tf_namjena.setColumns(10);
		
		tf_iznos = new JTextField();
		tf_iznos.setColumns(10);
		
		tf_rok = new JTextField();
		tf_rok.setColumns(10);
		
		tf_stopa = new JTextField();
		tf_stopa.setColumns(10);
		
		tf_garancija = new JTextField();
		tf_garancija.setColumns(10);
		
		tf_grace = new JTextField();
		tf_grace.setColumns(10);
		
		tf_troskovi = new JTextField();
		tf_troskovi.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Instrumenti obezbjeđenja:");
		
		tf_instrumenti = new JTextField();
		tf_instrumenti.setColumns(10);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(20))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(26)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addComponent(label_4)
								.addComponent(lblTrokoviObradekm)
								.addComponent(label_3)
								.addComponent(lblKamatnaStopa)
								.addComponent(label_2)
								.addComponent(lblIznosKreditakm)
								.addComponent(label_7)
								.addComponent(label))
							.addGap(18)))
					.addGap(0, 0, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
						.addComponent(tf_instrumenti)
						.addComponent(tf_namjena)
						.addComponent(tf_iznos)
						.addComponent(tf_rok)
						.addComponent(tf_stopa)
						.addComponent(tf_garancija)
						.addComponent(tf_grace)
						.addComponent(tf_troskovi)
						.addComponent(tf_naziv, GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE))
					.addGap(49))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(tf_naziv, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(tf_namjena, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_7))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(tf_iznos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblIznosKreditakm))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(tf_rok, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(tf_stopa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblKamatnaStopa))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(tf_garancija, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(tf_grace, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_4))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(tf_troskovi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTrokoviObradekm))
					.addGap(8)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(tf_instrumenti, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(73, Short.MAX_VALUE)
					.addComponent(button_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNazad)
					.addGap(23))
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addGap(73)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 452, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(51, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(61, Short.MAX_VALUE)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNazad)
						.addComponent(button_1))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		tabbedPane.addTab("Brisanje tipova kredita", null, panel_2, null);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(10, 11, 556, 378);
		panel_2.add(panel_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(128, 0, 0), 1, true));
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(10, 11, 408, 32);
		panel_3.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel label_8 = new JLabel("Naziv tipa kredita:");
		label_8.setHorizontalAlignment(SwingConstants.TRAILING);
		label_8.setBounds(10, 9, 100, 14);
		panel_4.add(label_8);
		
		tf_pretraga = new JTextField();
		tf_pretraga.setBounds(120, 6, 278, 20);
		tf_pretraga.setColumns(10);
		panel_4.add(tf_pretraga);
		final JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(128, 0, 0), 1, true));
		panel_5.setBackground(Color.WHITE);
		panel_5.setBounds(10, 54, 536, 279);
		panel_3.add(panel_5);
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGap(0, 355, Short.MAX_VALUE)
				.addGap(0, 353, Short.MAX_VALUE)
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGap(0, 214, Short.MAX_VALUE)
				.addGap(0, 212, Short.MAX_VALUE)
		);
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		
		JButton button_2 = new JButton("Pretra\u017Ei");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					_krediti=new TipKreditaLogika().getByName2(tf_pretraga.getText());
					
					if(_krediti.size()!=0){

						if(_table==null){ 
							_table = new JTable();
							_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						}
						_table.setModel(new TipKreditaTableModel(_krediti));
						if(_scrollPane==null){
							_scrollPane = new JScrollPane(_table);
							_scrollPane.setViewportView(_table);
							panel_5.add(_scrollPane);
						}
						panel_5.revalidate();
						panel_5.repaint();
					}
					else JOptionPane.showMessageDialog(null, "Ne postoji kredit sa tim nazivom.");
				} catch (HeadlessException e1) {
					 JOptionPane.showMessageDialog(null, "Nešto je pošlo po zlu! ERROR: pr3tr4g4");
				}
			}
		});
		button_2.setBounds(428, 20, 93, 23);
		panel_3.add(button_2);
		
		JButton btnNazad_1 = new JButton("Nazad");
		btnNazad_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SluzbenikGui.Krediti.this.dispose();
			}
		});
		btnNazad_1.setBounds(435, 344, 89, 23);
		panel_3.add(btnNazad_1);
		
		JButton button_4 = new JButton("Izbri\u0161i");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int _foo= _table.getSelectedRow();
					if(_foo==-1) throw new NullPointerException();
					//pomocna varijabla jer se remove ne moze uraditi kako treba unutar foreach petlje !
					TipKreditaSluzbenik _toBeDeleted=null;
					
						for(TipKreditaSluzbenik k : _krediti){
							if(k.getNaziv().equals((String)_table.getValueAt(_foo, 0))){
								_toBeDeleted=k;
								new TipKreditaLogika().deleteByName(k.getNaziv());
							}

						}
						_krediti.remove(_toBeDeleted);
						_table.setModel(new TipKreditaTableModel(_krediti));
						JOptionPane.showMessageDialog(null, "Uspješno obrisano!");
						tf_pretraga.setText("");
						//refresh tabele
						_table.invalidate();
						_table.revalidate();
						_table.repaint();
				}
				catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(null, "Niste odabrali tip kredita koji želite obrisati!");
				} 
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Nešto je krenulo po zlu! ERROR: d3l3t4 3rr0r");
				}
			}
		});
		button_4.setBounds(334, 344, 91, 23);
		panel_3.add(button_4);
		
		
	}

}
