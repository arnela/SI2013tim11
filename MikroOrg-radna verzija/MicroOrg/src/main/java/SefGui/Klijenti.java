package SefGui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ListSelectionModel;

import domainModels.Uposlenik;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.security.acl.Owner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import aplikacija.MicroOrg.Spremnik;
import logic.KlijentLogika;
import logic.SharedLogika;
import logic.UposlenikLogika;
import viewModels.KlijentSluzbenik;
import viewModels.KlijentTableModel;
import viewModels.KreditniSluzbenik;
import viewModels.SluzbenikTableModel;

import java.util.ArrayList;
import java.util.List;

public class Klijenti extends JFrame {

	private JPanel contentPane;
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private JTextField tf_pretraga;
	private Uposlenik trenutni;
	JTable _table = null;
	private List<KlijentSluzbenik> _sviKlijenti = null;
	JScrollPane _scrollPane = null;
	/**
	 * Launch the application.
	 */
	//ovaj konstruktor je samo u slucaju da se aplikacija pokrece iz ove forme a nama to ne treba
		
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Klijenti frame = new Klijenti();
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
				SefGui.Pocetni n =new SefGui.Pocetni();  //kreira novi po�etni gui za �efa
				n.setLocationRelativeTo(null);   // postavlja ga na sredinu
				n.setVisible(true);  // upali vidljivost
				n.setResizable(false);
			}
		});
		trenutni=Spremnik.getTrenutni(); //postavlja uposlenika forme na trenutnog koji je logovan
		setTitle("MicroOrg - Klijenti");
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 613, 418);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		tabbedPane.setBounds(0, 0, 592, 380);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Prikaz i pretraga klijenata", null, panel, null);
		
		final JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(128, 0, 0), 1, true));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 54, 435, 250);
		panel.add(panel_1);
		
		JButton button = new JButton("PDF prikaz");
		button.addActionListener(new ActionListener() {
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
					JOptionPane.showMessageDialog(null, "Niste odabrali uposlenika čije podatke želite prikazati u pdf formatu!");
				} 
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Nešto je krenulo po zlu! ERROR: pr1k4z 3rr0r");
				}
			}
		});
		button.setBounds(10, 315, 112, 23);
		panel.add(button);
		
		JButton button_1 = new JButton("Po\u0161alji na E-mail");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mail m = new Mail();
				m.setVisible(true);
			}
		});
		button_1.setBounds(132, 315, 150, 23);
		panel.add(button_1);
		
		JButton button_2 = new JButton("Izbri\u0161i ");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int _foo= _table.getSelectedRow();
					if(_foo==-1)JOptionPane.showMessageDialog(null, "Niste odabrali uposlenika kojeg želite obrisati!");
					//pomocna varijabla jer se remove ne moze uraditi kako treba unutar foreach petlje !
					KlijentSluzbenik _toBeDeleted=null;
					
						for(KlijentSluzbenik k : _sviKlijenti){
							if(k.getJmbg().equals((String)_table.getValueAt(_foo, 1))){
								_toBeDeleted=k;
								new UposlenikLogika().softDeleteByJMBG(k.getJmbg());
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
					JOptionPane.showMessageDialog(null, "Niste odabrali uposlenika kojeg želite obrisati!");
				} 
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Nešto je krenulo po zlu! ERROR: d3l3t4 3rr0r");
				}
			}
		});
		button_2.setBounds(294, 315, 79, 23);
		panel.add(button_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(new Color(165, 42, 42), 1, true));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(10, 11, 429, 32);
		panel.add(panel_2);
		
		JLabel label = new JLabel("Unesi podatke:");
		label.setBounds(6, 9, 95, 14);
		panel_2.add(label);
		
		tf_pretraga = new JTextField();
		tf_pretraga.setColumns(10);
		tf_pretraga.setBounds(98, 6, 321, 20);
		panel_2.add(tf_pretraga);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(174, 16, 0, 0);
		panel_2.add(label_1);
		
		
		final JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBounds(455, 54, 121, 87);
		panel.add(panel_3);
		
		JRadioButton rb1 = new JRadioButton("Ime i Prezime");
		rb1.setBounds(6, 7, 109, 23);
		panel_3.add(rb1);
		
		JRadioButton rb4 = new JRadioButton("E-mail");
		rb4.setBounds(6, 33, 109, 23);
		panel_3.add(rb4);
		
		JRadioButton rb5 = new JRadioButton("Adresa");
		rb5.setBounds(6, 59, 109, 23);
		panel_3.add(rb5);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rb1);
		group.add(rb4);
		group.add(rb5);
		
		 final JRadioButton rb_ime = rb1;
		 final JRadioButton rb_email = rb4;
		 final JRadioButton rb_adresa = rb5;

			JButton btnPretraziPo = new JButton("Pretra\u017Ei po:");
			btnPretraziPo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 
					 try{
						 KlijentLogika _klijentLogika= new KlijentLogika();
					 if (rb_ime.isSelected() == true) {
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
									panel_1.add(_scrollPane);
								}
								    
							panel_1.revalidate();
							panel_1.repaint();
					   }
							else JOptionPane.showMessageDialog(null, "Ne postoji klijent sa tim imenom.");
					 }
					 
					 else if (rb_email.isSelected()==true) {
					
						 List<KlijentSluzbenik> _klijenti=_klijentLogika.getByEmail(tf_pretraga.getText());
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
									panel_1.add(_scrollPane);
								}
								    
							panel_1.revalidate();
							panel_1.repaint();
					   }
							else JOptionPane.showMessageDialog(null, "Ne postoji klijent sa tim imenom.");
						 
					 }
					 
					 else if (rb_adresa.isSelected()==true) {
						 
						 List<KlijentSluzbenik> _klijenti=_klijentLogika.getByAddress(tf_pretraga.getText());
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
									panel_1.add(_scrollPane);
								}
								    
							panel_1.revalidate();
							panel_1.repaint();
					   }
							else JOptionPane.showMessageDialog(null, "Ne postoji klijent sa tim imenom.");
							}}
					 catch(HeadlessException e1)
						{
						 JOptionPane.showMessageDialog(null, "Nešto je pošlo po zlu! ERROR: pr3tr4g4");
						}
					 }
					 
				
			});
			btnPretraziPo.setBounds(455, 11, 121, 23);
			panel.add(btnPretraziPo);
	
		
		JButton button_4 = new JButton("Nazad");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SefGui.Klijenti.this.dispose();
			}
		});
		button_4.setBounds(487, 315, 89, 23);
		panel.add(button_4);
	}

}
