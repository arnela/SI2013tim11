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

import viewModels.Transakcija;
import viewModels.TransakcijaTableModel;
import domainModels.Uposlenik;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

import aplikacija.MicroOrg.Spremnik;
import logic.SharedLogika;
import logic.TransakcijaLogika;
import logic.UposlenikLogika;

public class Transakcije extends JFrame {

	private JPanel contentPane;
	private JTextField tf_podaciPretrage;
	private Uposlenik trenutni;
	private  List<Transakcija> _transakcije=null;
	JTable _table = null;
	
	/**
	 * Launch the application.
	 */
	
	//ovaj konstruktor je samo u slucaju da se aplikacija pokrece iz ove forme a nama to ne treba
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Transakcije frame = new Transakcije();
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
	public Transakcije() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				Spremnik.setTransakcije(SefGui.Transakcije.this);
				Spremnik.getPocetni().show();
				Spremnik.getTransakcije().hide();
			}
		});
		trenutni=Spremnik.getTrenutni();
		setTitle("MicroOrg - Transakcije");
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 615, 480);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 597, 438);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Prikaz i pretraga", null, panel, null);
		
		final JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(128, 0, 0), 1, true));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 54, 385, 311);
		panel.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(new Color(165, 42, 42), 1, true));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(10, 11, 385, 32);
		panel.add(panel_2);
		
		JLabel label = new JLabel("Podaci za pretragu:");
		label.setBounds(6, 9, 119, 14);
		panel_2.add(label);
		
		tf_podaciPretrage = new JTextField();
		tf_podaciPretrage.setColumns(10);
		tf_podaciPretrage.setBounds(135, 6, 246, 20);
		panel_2.add(tf_podaciPretrage);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(174, 16, 0, 0);
		panel_2.add(label_1);
		
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBounds(405, 54, 182, 88);
		panel.add(panel_3);
		
		ButtonGroup grupa = new ButtonGroup();

		
		final JRadioButton radioButton = new JRadioButton("Ime i Prezime uposlenika");
		radioButton.setBounds(6, 7, 170, 23);
		panel_3.add(radioButton);
		
		final JRadioButton radioButton_1 = new JRadioButton("Klijent - Ime&Prezime");
		radioButton_1.setBounds(6, 33, 160, 23);
		panel_3.add(radioButton_1);
		
		final JRadioButton radioButton_2 = new JRadioButton("Naziv tipa kredita");
		radioButton_2.setBounds(6, 58, 160, 23);
		panel_3.add(radioButton_2);
		
		grupa.add(radioButton);
		grupa.add(radioButton_1);
		grupa.add(radioButton_2);
		
		JButton button_1 = new JButton("Pretra\u017Ei po:");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TransakcijaLogika _transakcijaLogika = new TransakcijaLogika();
				
				if(radioButton.isSelected()){
					
					
				}
				else if(radioButton_1.isSelected()){
					try{
						List<Transakcija> _transakcije=_transakcijaLogika.getByKlijent(tf_podaciPretrage.getText());
						if(_transakcije.size()!=0){
						 _table = new JTable();
						_table.setModel(new TransakcijaTableModel(_transakcije));
						JScrollPane _scrollPane = new JScrollPane(_table);
					    _scrollPane.setViewportView(_table);
					    
						panel_1.add(_scrollPane);
						panel_1.revalidate();
						panel_1.repaint();
						}
						else JOptionPane.showMessageDialog(null, "Ne postoje transakcije sa tim klijentom.");
						}catch(HeadlessException e1)
						{
							 JOptionPane.showMessageDialog(null, "Nešto je pošlo po zlu! ERROR: pr3tr4g4");
							}
					
					
				}
				else if(radioButton_2.isSelected()){
					try{
						List<Transakcija> _transakcije = _transakcijaLogika.getByTipKredita(tf_podaciPretrage.getText());
						if(_transakcije.size()!=0){
						_table = new JTable();
						_table.setModel(new TransakcijaTableModel(_transakcije));
						JScrollPane _scrollPane = new JScrollPane(_table);
					    _scrollPane.setViewportView(_table);
					    
						panel_1.add(_scrollPane);
						panel_1.revalidate();
						panel_1.repaint();
						}
						else JOptionPane.showMessageDialog(null, "Ne transakcija sa tim nazivom tipa kredita.");
						}catch(HeadlessException e1)
						{
							 JOptionPane.showMessageDialog(null, "Nešto je pošlo po zlu! ERROR: pr3tr4g4");
							}
					}
				
				
			}
		});
		button_1.setBounds(405, 11, 134, 32);
		panel.add(button_1);
		

		JButton button = new JButton("PDF prikaz");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Transakcija _toBePDFGenerated=null;
				try{
					int _foo= _table.getSelectedRow();
					if(_foo==-1) throw new NullPointerException();
					
					
					for(Transakcija t : _transakcije){
						if( (t.getDatumUplate().equals((String)_table.getValueAt(_foo, 4)))){
							_toBePDFGenerated=t;
						}	
					}
					SharedLogika _sharedLogika= new SharedLogika();
					_sharedLogika.generisiPDF(_toBePDFGenerated);
					_sharedLogika.otvoriPDF(_toBePDFGenerated);
					
				}
				catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(null, "Niste odabrali transakciju čije podatke želite prikazati u pdf formatu!");
				} 
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Nešto je krenulo po zlu! ERROR: pr1k4z 3rr0r");
				}
				
			}
		});
		button.setBounds(10, 376, 112, 23);
		panel.add(button);
		
		
		
		
		JButton button_2 = new JButton("Nazad");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SefGui.Transakcije.this.dispose();
			}
		});
		button_2.setBounds(479, 376, 103, 23);
		panel.add(button_2);
	}

}
