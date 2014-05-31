package SefGui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import viewModels.IzvjestajOrganizacije;
import domainModels.HibernateUtil;
import domainModels.Klijent;
import domainModels.Kredit;
import domainModels.Osoba;
import domainModels.Transakcija;
import domainModels.Uposlenik;
import aplikacija.MicroOrg.Spremnik;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import logic.IzvjestajLogika;
import logic.SharedLogika;

public class Poslovanje extends JFrame {

	private JPanel contentPane;
	private Uposlenik trenutni;
	/**
	 * Launch the application.
	 */
	//ovaj konstruktor je samo u slucaju da se aplikacija pokrece iz ove forme a nama to ne treba
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Poslovanje frame = new Poslovanje();
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
	public Poslovanje() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				SefGui.Pocetni n =new SefGui.Pocetni();  //kreira novi po�etni gui za �efa
				n.setLocationRelativeTo(null);   // postavlja ga na sredinu
				n.setVisible(true);  // upali vidljivost
				n.setResizable(false);
			}
		});
		
		Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction t = session.beginTransaction();
	    
	    Criteria _c = session.createCriteria(Kredit.class);
	    List<Kredit> _krediti = (List<Kredit>)_c.list();
	    

	    _c = session.createCriteria(Transakcija.class);
	    List<Transakcija> _transakcije = (List<Transakcija>)_c.list();
	    
	    _c = session.createCriteria(Uposlenik.class);
	    List<Uposlenik> _uposlenici = (List<Uposlenik>)_c.list();
	    
	    _c = session.createCriteria(Klijent.class);
	    List<Klijent> _klijenti = (List<Klijent>)_c.list();
	    
	    List<String> imena = new ArrayList<String>();
	    for(Uposlenik _u : _uposlenici) {
	    	_c = session.createCriteria(Osoba.class);
	    	Osoba _o = (Osoba)_c.add(Restrictions.eq("osobaId", _u.getOsobaId())).uniqueResult();
	    	if(_o != null) imena.add(_o.getImePrezime());
	    }
	    
	    String[] lista = new String[_uposlenici.size()];
	    
	    for(int i = 0; i < _uposlenici.size(); i++) {
	    	lista[i] = imena.get(i) + "    " + Double.toString(_uposlenici.get(i).getPlata()) + "    krediti: " + Integer.toString(_uposlenici.get(i).getUkupanBrKredita()) + "    transakcije: " + Integer.toString(_uposlenici.get(i).getUkupanBrTransakcija());
	    }
	    
	    /*List<Transakcija> _transakcije = (List<Transakcija>) criteria.list();
	    for(Transakcija _t_ : _transakcije){
	    	if(!(_t_.getKreditniSluzbenik().equals(Spremnik.getTrenutni()))) {
	    		_transakcije.remove(_t_);
	    	}
	    }*/
	    
	    //Set<Kredit> _krediti = Spremnik.getTrenutni().getKrediti(); 
	    //Kredit[] _notSet = _krediti.toArray(new Kredit[_krediti.size()]);
	    
	    //Criteria criteria = session.createCriteria(Kredit.class);
	    //List<Kredit> _krediti = (List<Kredit>) criteria.add(Restrictions.eq("uposlenikId", Spremnik.getTrenutni().getUposlenikId())).list();
	    int _brKredita = _krediti.size();
	    int _brTransakcija = _transakcije.size();
	    int _brUposlenih = _uposlenici.size();
	    int _brKlijenata = _klijenti.size();
	    
	    double _rashodi = 0;
	    double _prihodi = 0;
	    //for(Kredit k : _krediti) {
	    	//_rashodi += k.getTipKredita().getIznos();
	    for(int i = 0; i < _krediti.size(); i++) {
	    	//_rashodi += _notSet[i].getTipKredita().getIznos();
	    	_rashodi += _krediti.get(i).getTipKredita().getIznos();
	    }
	    for(Transakcija _t : _transakcije) {
	    	_prihodi += _t.getIznosUplate();
	    }
	    
	    t.commit();
	    session.close();
		
		trenutni=Spremnik.getTrenutni();
		setTitle("MicroOrg - Poslovanje organizacije");
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 581, 382);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 564, 343);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Trenutno stanje", null, panel, null);
		
		DateFormat _f = new SimpleDateFormat("dd.MM.yyyy hh:mm");
		Calendar cal = Calendar.getInstance();
		String datum = _f.format(cal.getTime());
				
		JLabel label = new JLabel("Datum i vrijeme: " + datum);
		label.setHorizontalAlignment(SwingConstants.TRAILING);
		label.setBounds(10, 50, 206, 14);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Podaci o zaposlenicima:");
		label_1.setBounds(307, 39, 231, 14);
		panel.add(label_1);
		
		JList list = new JList(lista);
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setBounds(250, 59, 299, 214);
		//list.add(lista);
		panel.add(list);
		
		JLabel label_2 = new JLabel("Prihodi: " + Double.toString(_prihodi));
		label_2.setHorizontalAlignment(SwingConstants.TRAILING);
		label_2.setBounds(10, 75, 141, 14);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("Rashodi: " + Double.toString(_rashodi));
		label_3.setHorizontalAlignment(SwingConstants.TRAILING);
		label_3.setBounds(10, 100, 141, 14);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("Izvr\u0161ene transakcije: " + Integer.toString(_brTransakcija));
		label_4.setHorizontalAlignment(SwingConstants.TRAILING);
		label_4.setBounds(10, 125, 141, 14);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("Dodijeljeni krediti: " + Integer.toString(_brKredita));
		label_5.setHorizontalAlignment(SwingConstants.TRAILING);
		label_5.setBounds(10, 150, 141, 14);
		panel.add(label_5);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setBounds(188, 8, 176, 20);
		comboBox.addItem("mjesec");
		comboBox.addItem("sest mjeseci");
		comboBox.addItem("godina");
		panel.add(comboBox);
		
		JLabel label_6 = new JLabel("Za protekli vremenski period:");
		label_6.setHorizontalAlignment(SwingConstants.TRAILING);
		label_6.setBounds(10, 11, 176, 14);
		panel.add(label_6);
		
		JLabel label_9 = new JLabel("Broj uposlenih: " + Integer.toString(_brUposlenih));
		label_9.setHorizontalAlignment(SwingConstants.TRAILING);
		label_9.setBounds(10, 175, 141, 14);
		panel.add(label_9);
		
		JLabel label_10 = new JLabel("Broj klijenata: " + Integer.toString(_brKlijenata));
		label_10.setHorizontalAlignment(SwingConstants.TRAILING);
		label_10.setBounds(10, 200, 141, 14);
		panel.add(label_10);
		
		JButton button = new JButton("PDF prikaz");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IzvjestajLogika _i = new IzvjestajLogika();
				IzvjestajOrganizacije izvjestaj = _i.generisiIzvjestajOrganizacije((String)comboBox.getSelectedItem());
				try{
					SharedLogika _sharedLogika=new SharedLogika();
					_sharedLogika.generisiPDF(izvjestaj);
					_sharedLogika.otvoriPDF(izvjestaj);
						
				}
				catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(null, "Niste odabrali ponudu koju želite prikazati u pdf formatu!");
				} 
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Nešto je krenulo po zlu! ERROR: pr1k4z 3rr0r");
				}
			}
		});
		button.setBounds(10, 285, 110, 23);
		panel.add(button);
		
		JButton btnEmail = new JButton("E-mail");
		btnEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IzvjestajLogika _i = new IzvjestajLogika();
				IzvjestajOrganizacije izvjestaj = _i.generisiIzvjestajOrganizacije((String)comboBox.getSelectedItem());
				try {
						SharedLogika _sharedLogika= new SharedLogika();
						Spremnik.setObjekatPDF(izvjestaj);
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
		btnEmail.setBounds(130, 285, 163, 23);
		panel.add(btnEmail);
		
		JButton button_2 = new JButton("Nazad");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SefGui.Poslovanje.this.dispose();
			}
		});
		button_2.setBounds(460, 285, 89, 23);
		panel.add(button_2);
	}

}
