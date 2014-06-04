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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.GroupLayout;
import javax.swing.ListSelectionModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollBar;
import javax.swing.LayoutStyle.ComponentPlacement;

import viewModels.KlijentSluzbenik;
import viewModels.KreditnaPonuda;
import viewModels.PonudaTableModel;
import aplikacija.MicroOrg.Spremnik;
import domainModels.Uposlenik;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import logic.PonudeLogika;
import logic.SharedLogika;

import java.awt.FlowLayout;

public class Ponude extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private Uposlenik trenutni;
	private JTable _table = null;
	private JScrollPane _scrollPane = null;
	private List<KreditnaPonuda> _svePonude = null;
	/**
	 * Launch the application.
	 */
	//ovaj konstruktor je samo u slucaju da se aplikacija pokrece iz ove forme a nama to ne treba
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
				Spremnik.setPonude(SefGui.Ponude.this);
				Spremnik.getPocetni().show();
				Spremnik.getPonude().hide();
			}
		});
		trenutni=Spremnik.getTrenutni();
		
		setTitle("MicroOrg - Ponude");
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 701, 468);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 675, 430);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Prikaz i pretraga", null, panel, null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(165, 42, 42), 1, true));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(23, 11, 506, 32);
		panel.add(panel_1);
		
		JLabel label = new JLabel("Unesi podatke:");
		label.setBounds(6, 9, 95, 14);
		panel_1.add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(98, 6, 383, 20);
		panel_1.add(textField);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(174, 16, 0, 0);
		panel_1.add(label_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(539, 64, 121, 61);
		panel.add(panel_2);
		
		final JRadioButton radioButton = new JRadioButton("Ime klijenta");
		radioButton.setBounds(6, 7, 109, 23);
		panel_2.add(radioButton);
		
		final JRadioButton radioButton_1 = new JRadioButton("Datum upisa");
		radioButton_1.setBounds(6, 33, 109, 23);
		panel_2.add(radioButton_1);
		
		ButtonGroup grupa = new ButtonGroup();
		
		grupa.add(radioButton);
		grupa.add(radioButton_1);
		radioButton.setSelected(true);
		
		
		final JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(128, 0, 0), 1, true));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(23, 64, 506, 298);
		panel.add(panel_3);
		
		JButton button = new JButton("Pretra\u017Ei po:");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					PonudeLogika _pl = new PonudeLogika();
					List<KreditnaPonuda> _ponude = new ArrayList<KreditnaPonuda>();
					if(radioButton.isSelected()) {
						_ponude = _pl.traziPoImenuKlijenta(textField.getText());
						_svePonude=new ArrayList<KreditnaPonuda>();
						for(KreditnaPonuda kp : _ponude){
							_svePonude.add(kp);
						}
					}
					else if(radioButton_1.isSelected()) {
						_ponude = _pl.traziPoDatumu(textField.getText());
						_svePonude=new ArrayList<KreditnaPonuda>();
						for(KreditnaPonuda kp : _ponude){
							_svePonude.add(kp);
						}
					}
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
							panel_3.add(_scrollPane);
						}
											
						panel_3.revalidate();
						panel_3.repaint();
						textField.setText("");
						}
						else JOptionPane.showMessageDialog(null, "Ne postoji ponuda izdata unesenom klijentu.");
				}
				catch(HeadlessException e1) {
					JOptionPane.showMessageDialog(null, "Nešto je pošlo po zlu! ERROR: pr3tr4g4");
				} 
			}
		});
		button.setBounds(539, 11, 121, 23);
		panel.add(button);
		
		
		
		JButton btnNazad = new JButton("Nazad");
		btnNazad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SefGui.Ponude.this.dispose();
			}
		});
		btnNazad.setBounds(567, 373, 93, 23);
		panel.add(btnNazad);
		
		JButton button_2 = new JButton("PDF prikaz");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KreditnaPonuda _toBePDFGenerated=null;
				try{
					int _foo=_table.getSelectedRow();
					if(_foo==-1) throw new NullPointerException();
					for(KreditnaPonuda kp : _svePonude){
						if(kp.getDatumUpisa().equals((String)_table.getValueAt(_foo, 3))){
							_toBePDFGenerated=kp;
					}
					}
					SharedLogika _sharedLogika=new SharedLogika();
					_sharedLogika.generisiPDF(_toBePDFGenerated);
					_sharedLogika.otvoriPDF(_toBePDFGenerated);
						
				}
				catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(null, "Niste odabrali ponudu koju želite prikazati u pdf formatu!");
				} 
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Nešto je krenulo po zlu! ERROR: pr1k4z 3rr0r");
				}
				}
		});
		button_2.setBounds(23, 373, 171, 23);
		panel.add(button_2);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		
		JRadioButton radioButton_2 = new JRadioButton("Ime i Prezime");
		radioButton_2.setBounds(6, 7, 109, 23);
		panel_4.add(radioButton_2);
		
		JRadioButton radioButton_3 = new JRadioButton("Kredit");
		radioButton_3.setBounds(6, 33, 109, 23);
		panel_4.add(radioButton_3);
		
		JRadioButton radioButton_4 = new JRadioButton("Uposlenik");
		radioButton_4.setBounds(6, 59, 109, 23);
		panel_4.add(radioButton_4);
		
		JRadioButton radioButton_5 = new JRadioButton("ID");
		radioButton_5.setBounds(6, 85, 109, 23);
		panel_4.add(radioButton_5);
		
		JRadioButton radioButton_6 = new JRadioButton("Datum upisa");
		radioButton_6.setBounds(6, 111, 109, 23);
		panel_4.add(radioButton_6);
		
		JRadioButton radioButton_7 = new JRadioButton("E-mail");
		radioButton_7.setBounds(6, 137, 109, 23);
		panel_4.add(radioButton_7);
		
		JRadioButton radioButton_8 = new JRadioButton("Adresa");
		radioButton_8.setBounds(6, 163, 109, 23);
		panel_4.add(radioButton_8);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		
		JRadioButton radioButton_9 = new JRadioButton("Ime i Prezime");
		radioButton_9.setBounds(6, 7, 109, 23);
		panel_5.add(radioButton_9);
		
		JRadioButton radioButton_10 = new JRadioButton("Kredit");
		radioButton_10.setBounds(6, 33, 109, 23);
		panel_5.add(radioButton_10);
		
		JRadioButton radioButton_11 = new JRadioButton("Uposlenik");
		radioButton_11.setBounds(6, 59, 109, 23);
		panel_5.add(radioButton_11);
		
		JRadioButton radioButton_12 = new JRadioButton("ID");
		radioButton_12.setBounds(6, 85, 109, 23);
		panel_5.add(radioButton_12);
		
		JRadioButton radioButton_13 = new JRadioButton("Datum upisa");
		radioButton_13.setBounds(6, 111, 109, 23);
		panel_5.add(radioButton_13);
		
		JRadioButton radioButton_14 = new JRadioButton("E-mail");
		radioButton_14.setBounds(6, 137, 109, 23);
		panel_5.add(radioButton_14);
		
		JRadioButton radioButton_15 = new JRadioButton("Adresa");
		radioButton_15.setBounds(6, 163, 109, 23);
		panel_5.add(radioButton_15);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_3.add(panel_4);
		panel_3.add(panel_5);
	}

}
