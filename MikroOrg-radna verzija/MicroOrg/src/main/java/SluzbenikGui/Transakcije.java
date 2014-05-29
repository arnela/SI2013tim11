package SluzbenikGui;

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
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import aplikacija.MicroOrg.Spremnik;
import domainModels.Klijent;
import domainModels.Kredit;
import viewModels.KlijentSluzbenik;
import viewModels.KlijentTableModel;
import viewModels.KreditnaPonuda;
import viewModels.Transakcija;
import viewModels.TransakcijaTableModel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import logic.KlijentLogika;
import logic.PonudeLogika;
import logic.SharedLogika;
import logic.TransakcijaLogika;

import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

public class Transakcije extends JFrame {

	private JPanel contentPane;
	private JTextField tf_jmbg;
	private JTextField tf_iznos;
	private JTextField tf_pretraga;
	private JLabel lb_datum;
	private  List<Transakcija> _sveTransakcije=null;
	JTable _table = null;




	/**
	 * Launch the application.
	 */
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
		
		setTitle("MicroOrg - Transakcije");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 625, 384);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 609, 342);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(165, 42, 42), 1, true));
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Unos nove transakcije", null, panel, null);
		
		JLabel label = new JLabel("JMBG Klijenta:");
		label.setHorizontalAlignment(SwingConstants.TRAILING);
		label.setBounds(1, 98, 97, 14);
		panel.add(label);
		
		tf_jmbg = new JTextField();
		tf_jmbg.setColumns(10);
		tf_jmbg.setBounds(108, 95, 103, 20);
		panel.add(tf_jmbg);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(174, 16, 0, 0);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Uposlenik:");
		label_2.setHorizontalAlignment(SwingConstants.TRAILING);
		label_2.setBounds(28, 16, 77, 14);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("Datum i vrijeme:");
		label_3.setHorizontalAlignment(SwingConstants.TRAILING);
		label_3.setBounds(-42, 41, 147, 14);
		panel.add(label_3);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setBounds(108, 132, 164, 28);
		panel.add(comboBox);
		
		
		JLabel label_4 = new JLabel("ID Transakcije:");
		label_4.setHorizontalAlignment(SwingConstants.TRAILING);
		label_4.setBounds(1, 65, 103, 14);
		panel.add(label_4);
		
		JButton button = new JButton("Provjeri");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TransakcijaLogika _transakcijaLogika = new TransakcijaLogika();
				try{
				if(!(_transakcijaLogika.daLiPostoji(tf_jmbg.getText()))){
					JOptionPane.showMessageDialog(null, "Klijent sa ovim maticnim brojem ne postoji!");
				}else
				{
					PonudeLogika _ponudeLogika = new PonudeLogika();
					List<KreditnaPonuda> _krediti = _ponudeLogika.traziPoImenuKlijenta(_transakcijaLogika.dajOsobu("").getImePrezime());
					for (int i=0;i<_krediti.size();i++)
					{
						comboBox.addItem(_krediti.get(i));
						//comboBox.addItem(_krediti.get(i).getTk().getNaziv());
					}
				
				}
				}catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Nešto je pošlo naopako !");
			}
			}
		});
		
		button.setBounds(234, 94, 89, 23);
		panel.add(button);
		
		JLabel label_5 = new JLabel("Kredit:");
		label_5.setHorizontalAlignment(SwingConstants.TRAILING);
		label_5.setBounds(1, 139, 97, 14);
		panel.add(label_5);
		
		
		JLabel label_6 = new JLabel("Nov\u010Dani iznos:");
		label_6.setHorizontalAlignment(SwingConstants.TRAILING);
		label_6.setBounds(1, 186, 97, 14);
		panel.add(label_6);
		
		tf_iznos = new JTextField();
		tf_iznos.setColumns(10);
		tf_iznos.setBounds(108, 183, 103, 20);
		panel.add(tf_iznos);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(128, 0, 0)));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(83, 214, 435, 48);
		panel.add(panel_1);
		
		ButtonGroup grupa = new ButtonGroup();

		
		final JRadioButton radioButton = new JRadioButton("uplatnica iz banke");
		radioButton.setBounds(260, 7, 153, 23);
		panel_1.add(radioButton);
		
		final JRadioButton radioButton_1 = new JRadioButton("gotovina");
		radioButton_1.setBounds(6, 7, 153, 23);
		panel_1.add(radioButton_1);
		
		grupa.add(radioButton);
		grupa.add(radioButton_1);
		
		JButton button_1 = new JButton("PDF prikaz");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(null, "Nije implementirano !");
			}
		});
		button_1.setBounds(10, 280, 125, 23);
		panel.add(button_1);
		
		JButton button_2 = new JButton("Unesi transakciju");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransakcijaLogika _transakcijaLogika = new TransakcijaLogika();
                PonudeLogika _ponudeLogika = new PonudeLogika();                                                                                                                    
 				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				String _datum = dateFormat.format(cal.getTime());
				
				double _iznos = Double.parseDouble(tf_iznos.getText());
				
				String _nacin;
				if(radioButton_1.isSelected()) _nacin="gotovina";
				else _nacin="uplatnica iz banke";
				//TODO:još kredit dobaviti iz comboBoxa! a prije toga smjestiti u comboBox :) 

				KlijentSluzbenik _k = _transakcijaLogika.dajKlijenta(tf_jmbg.getText());
				
				
				KreditnaPonuda _kredit =  (KreditnaPonuda) comboBox.getSelectedItem();
				
				Transakcija _transakcija = new Transakcija(_datum, _iznos, _nacin, _k, _kredit, Spremnik.getTrenutni()); 
				
				try {
					_transakcijaLogika.dodajTransakciju(_transakcija);
					//ocisti formu
					tf_jmbg.setText("");
					tf_iznos.setText("");
					radioButton.setSelected(false);
					radioButton_1.setSelected(false);
					JOptionPane.showMessageDialog(null, "Uspješno evidentirano !");
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Nešto je pošlo naopako !");
			}
				
				
			}
		});
		button_2.setBounds(145, 280, 147, 23);
		panel.add(button_2);
		
		JButton button_3 = new JButton("Nazad");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SluzbenikGui.Transakcije.this.dispose();
			}
		});
		button_3.setBounds(505, 280, 89, 23);
		panel.add(button_3);
		
		final JLabel lb_datum = new JLabel("");
		lb_datum.setBounds(108, 41, 103, 14);
		panel.add(lb_datum);
		
		final JLabel lb_uposlenik = new JLabel("");
		lb_uposlenik.setBounds(108, 16, 103, 14);
		panel.add(lb_uposlenik);
		
		final JLabel lb_id = new JLabel("");
		lb_id.setBounds(108, 65, 103, 14);
		panel.add(lb_id);
		
	
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(Color.WHITE);
		tabbedPane.addTab("Pretraga transakcija", null, panel_2, null);
		
		final JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(128, 0, 0), 1, true));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(10, 54, 385, 213);
		panel_2.add(panel_3);
		
		JButton button_4 = new JButton("PDF prikaz");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Transakcija _toBePDFGenerated=null;
				try{
					int _foo= _table.getSelectedRow();
					if(_foo==-1) throw new NullPointerException();
					
					
					for(Transakcija t : _sveTransakcije){
						if( (t.getDatumUplate().equals((String)_table.getValueAt(_foo, 4))) && (t.getIznosUplate().equals((Double)_table.getValueAt(_foo, 2))) && (t.getNacinUplate().equals((String)_table.getValueAt(_foo, 3)))){
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
		button_4.setBounds(10, 278, 112, 23);
		panel_2.add(button_4);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new LineBorder(new Color(165, 42, 42), 1, true));
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(10, 11, 385, 32);
		panel_2.add(panel_4);
		
		JLabel label_7 = new JLabel("Podaci za pretragu:");
		label_7.setBounds(6, 9, 119, 14);
		panel_4.add(label_7);
		
		tf_pretraga = new JTextField();
		tf_pretraga.setColumns(10);
		tf_pretraga.setBounds(135, 6, 246, 20);
		panel_4.add(tf_pretraga);
		
		JLabel label_8 = new JLabel("");
		label_8.setBounds(174, 16, 0, 0);
		panel_4.add(label_8);
		
		JButton button_5 = new JButton("Pretra\u017Ei po:");
		
		
		button_5.setBounds(405, 11, 121, 32);
		panel_2.add(button_5);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBounds(405, 54, 189, 118);
		panel_2.add(panel_5);
		
		ButtonGroup grupa2 = new ButtonGroup();

		
		final JRadioButton radioButton_2 = new JRadioButton("Datumu unosa");
		radioButton_2.setBounds(6, 7, 177, 23);
		panel_5.add(radioButton_2);
		
		final JRadioButton radioButton_3 = new JRadioButton("ID transakcije");
		radioButton_3.setBounds(6, 59, 143, 23);
		panel_5.add(radioButton_3);
		
		final JRadioButton radioButton_4 = new JRadioButton("Naziv tipa kredita");
		radioButton_4.setBounds(6, 85, 177, 23);
		panel_5.add(radioButton_4);
		
		final JRadioButton radioButton_5 = new JRadioButton("Klijent - Ime&Prezime");
		radioButton_5.setBounds(6, 33, 177, 23);
		panel_5.add(radioButton_5);
		
		grupa2.add(radioButton_2);
		grupa2.add(radioButton_3);
		grupa2.add(radioButton_4);
		grupa2.add(radioButton_5);
		
		JButton button_6 = new JButton("Nazad");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SluzbenikGui.Transakcije.this.dispose();
			}
		});
		button_6.setBounds(505, 278, 89, 23);
		panel_2.add(button_6);
		
		JButton button_7 = new JButton("Obri\u0161i");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 try {
					int _foo= _table.getSelectedRow();
					if(_foo==-1)JOptionPane.showMessageDialog(null, "Niste odabrali transakciju koju želite obrisati!");
					Transakcija _toBeDeleted=null;
					
						for(Transakcija t : _sveTransakcije){
							if( (t.getDatumUplate().equals((String)_table.getValueAt(_foo, 4))) && (t.getIznosUplate().equals((Double)_table.getValueAt(_foo, 2))) && (t.getNacinUplate().equals((String)_table.getValueAt(_foo, 3)))){
								_toBeDeleted=t;
							new TransakcijaLogika().softDeleteByMorePar(t.getDatumUplate(), t.getIznosUplate(), t.getNacinUplate());
							}

						}
						_sveTransakcije.remove(_toBeDeleted);
						_table.setModel(new TransakcijaTableModel(_sveTransakcije));
						JOptionPane.showMessageDialog(null, "Uspješno obrisano!");
						
						_table.invalidate();
						_table.revalidate();
						_table.repaint();
				}
				catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(null, "Niste odabrali transakciju kojeg želite obrisati!");
				} 
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Nešto je krenulo po zlu! ERROR: d3l3t4 3rr0r");
				}
			}
		});
		button_7.setBounds(283, 278, 112, 23);
		panel_2.add(button_7);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				SluzbenikGui.Pocetni n =new SluzbenikGui.Pocetni();  //kreira novi po�etni gui za sluzbenika
				n.setLocationRelativeTo(null);   // postavlja ga na sredinu
				n.setVisible(true);  // upali vidljivost
				n.setResizable(false);
			}
			
			@Override
			public void windowActivated(WindowEvent arg0) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
				Calendar cal = Calendar.getInstance();
				String _datum = dateFormat.format(cal.getTime());
				lb_datum.setText(_datum);
				lb_uposlenik.setText(Spremnik.getTrenutni().getUsername());
			}
			
			
		});
		
		
		button_5.addActionListener(new ActionListener() {
			@SuppressWarnings("null")
			public void actionPerformed(ActionEvent e) {
				
				TransakcijaLogika _transakcijaLogika= new TransakcijaLogika();
				
				if(radioButton_2.isSelected()){
				try{
				List<Transakcija> _transakcije=_transakcijaLogika.getByDate(tf_pretraga.getText());
				if(_transakcije.size()!=0){
				JTable _table = new JTable();
				_table.setModel(new TransakcijaTableModel(_transakcije));
				JScrollPane _scrollPane = new JScrollPane(_table);
			    _scrollPane.setViewportView(_table);
			    
				panel_3.add(_scrollPane);
				panel_3.revalidate();
				panel_3.repaint();
				}
				else JOptionPane.showMessageDialog(null, "Ne postoje transakcije sa tim datumom.");
				}catch(HeadlessException e1)
				{
					 JOptionPane.showMessageDialog(null, "Nešto je pošlo po zlu! ERROR: pr3tr4g4");
					}
				}else if(radioButton_5.isSelected()){
					try{
					List<Transakcija> _transakcije=_transakcijaLogika.getByKlijent(tf_pretraga.getText());
					if(_transakcije.size()!=0){
					JTable _table = new JTable();
					_table.setModel(new TransakcijaTableModel(_transakcije));
					JScrollPane _scrollPane = new JScrollPane(_table);
				    _scrollPane.setViewportView(_table);
				    
					panel_3.add(_scrollPane);
					panel_3.revalidate();
					panel_3.repaint();
					}
					else JOptionPane.showMessageDialog(null, "Ne postoje transakcije sa tim klijentom.");
					}catch(HeadlessException e1)
					{
						 JOptionPane.showMessageDialog(null, "Nešto je pošlo po zlu! ERROR: pr3tr4g4");
						}
				}/*else if(radioButton_3.isSelected()){
					try{
					List<Transakcija> _transakcije = null;
					_transakcije.add(_transakcijaLogika.getByID(tf_pretraga.getText()));
					if(_transakcije.size()!=0){
					JTable _table = new JTable();
					_table.setModel(new TransakcijaTableModel(_transakcije));
					JScrollPane _scrollPane = new JScrollPane(_table);
				    _scrollPane.setViewportView(_table);
				    
					panel_3.add(_scrollPane);
					panel_3.revalidate();
					panel_3.repaint();
					
					}
					else JOptionPane.showMessageDialog(null, "Nema transakcija sa tim ID-om.");
					}catch(HeadlessException e1)
					{
						 JOptionPane.showMessageDialog(null, "Nešto je pošlo po zlu! ERROR: pr3tr4g4");
						}
				}*/else if(radioButton_4.isSelected()){
					try{
					List<Transakcija> _transakcije = _transakcijaLogika.getByTipKredita(tf_pretraga.getText());
					if(_transakcije.size()!=0){
					JTable _table = new JTable();
					_table.setModel(new TransakcijaTableModel(_transakcije));
					JScrollPane _scrollPane = new JScrollPane(_table);
				    _scrollPane.setViewportView(_table);
				    
					panel_3.add(_scrollPane);
					panel_3.revalidate();
					panel_3.repaint();
					}
					else JOptionPane.showMessageDialog(null, "Ne transakcija sa tim nazivom tipa kredita.");
					}catch(HeadlessException e1)
					{
						 JOptionPane.showMessageDialog(null, "Nešto je pošlo po zlu! ERROR: pr3tr4g4");
						}
				}
			
			}
		});
		
		JButton button_8 = new JButton("Po\u0161alji na E-mail");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Nije implementirano !");
			}
		});
		button_8.setBounds(132, 278, 135, 23);
		panel_2.add(button_8);
	}
}
