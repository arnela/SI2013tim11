package SluzbenikGui;

import java.awt.BorderLayout;
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

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import viewModels.IzvjestajSluzbenika;
import aplikacija.MicroOrg.Spremnik;
import domainModels.HibernateUtil;
import domainModels.Kredit;
import domainModels.Transakcija;
import domainModels.Uposlenik;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import logic.IzvjestajLogika;
import logic.SharedLogika;

public class Izvjestaji extends JFrame {

	private JPanel contentPane;
	private Uposlenik trenutni;
	/**
	 * Launch the application.
	 */
	//ovaj konstruktor nam ne treba jer se ne pokrece aplikacija iz ove forme
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Izvjestaji frame = new Izvjestaji();
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
	public Izvjestaji() {
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosed(WindowEvent e) {
				Spremnik.setIzvjestaji(SluzbenikGui.Izvjestaji.this);
				Spremnik.getIzvjestaji().hide();
				Spremnik.getPocetni().show();
			}
		});
		
		Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction t = session.beginTransaction();
	    
	    /*Query q = session.createQuery("FROM kredit");
	    List<Kredit> listaKredita = q.list();
	    for(Kredit _k_ : listaKredita){
	    	if(!(_k_.getKreditniSluzbenik().equals(Spremnik.getTrenutni()))) {
	    		listaKredita.remove(_k_);
	    	}
	    }*/
	    
	    Criteria _c = session.createCriteria(Kredit.class);
	    List<Kredit> _krediti = (List<Kredit>)_c.add(Restrictions.eq("kreditniSluzbenik.uposlenikId", Spremnik.getTrenutni().getUposlenikId())).list();
	    

	    Criteria criteria = session.createCriteria(Transakcija.class);
	    List<Transakcija> _transakcije = (List<Transakcija>)criteria.add(Restrictions.eq("kreditniSluzbenik.uposlenikId", Spremnik.getTrenutni().getUposlenikId())).list();
	    
	    /*List<Transakcija> _transakcije = (List<Transakcija>) criteria.list();
	    for(Transakcija _t_ : _transakcije){
	    	if(!(_t_.getKreditniSluzbenik().equals(Spremnik.getTrenutni()))) {
	    		_transakcije.remove(_t_);
	    	}
	    }*/
	    
	    //Set<Kredit> _krediti = Spremnik.getTrenutni().getKrediti(); 
	    Kredit[] _notSet = _krediti.toArray(new Kredit[_krediti.size()]);
	    
	    //Criteria criteria = session.createCriteria(Kredit.class);
	    //List<Kredit> _krediti = (List<Kredit>) criteria.add(Restrictions.eq("uposlenikId", Spremnik.getTrenutni().getUposlenikId())).list();
	    double _rashodi = 0;
	    double _prihodi = 0;
	    //for(Kredit k : _krediti) {
	    	//_rashodi += k.getTipKredita().getIznos();
	    for(int i = 0; i < _krediti.size(); i++) {
	    	_rashodi += _notSet[i].getTipKredita().getIznos();
	    }
	    for(Transakcija _t : _transakcije) {
	    	_prihodi += _t.getIznosUplate();
	    }
	    
	    t.commit();
	    session.close();
		
		trenutni=Spremnik.getTrenutni();
		setBackground(Color.WHITE);
		setTitle("MicroOrg - Izvje\u0161taji");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 574, 385);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setBounds(0, 0, 559, 347);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Novi izvje\u0161taj", null, panel, null);
		
		JLabel label_1 = new JLabel("Lista transakcija:");
		label_1.setBounds(307, 39, 231, 14);
		panel.add(label_1);
		
		Transakcija[] listaTransakcija = new Transakcija[_transakcije.size()];
		String[] lista = new String[_transakcije.size()];
		for (int i = 0; i < _transakcije.size(); i++) {
			listaTransakcija[i] = _transakcije.get(i);
			lista[i] = Long.toString(listaTransakcija[i].getTransakcijaId()) + "    " + listaTransakcija[i].getDatumUplate() + "    " + Double.toString(listaTransakcija[i].getIznosUplate());
		}
				
		//JList list = new JList(listaTransakcija);
		JList list = new JList(lista);
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setBounds(307, 59, 242, 214);
		
		panel.add(list);
		
		/*JList list = new JList();
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setBounds(307, 59, 242, 214);
		panel.add(list);*/
		
		JLabel label_2 = new JLabel("Prihodi: " + String.valueOf(_prihodi));
		label_2.setHorizontalAlignment(SwingConstants.TRAILING);
		label_2.setBounds(10, 75, 141, 14);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("Rashodi: " + String.valueOf(_rashodi));
		label_3.setHorizontalAlignment(SwingConstants.TRAILING);
		label_3.setBounds(10, 100, 141, 14);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("Izvr\u0161ene transakcije: " + String.valueOf(Spremnik.getTrenutni().getUkupanBrTransakcija()));
		label_4.setHorizontalAlignment(SwingConstants.TRAILING);
		label_4.setBounds(10, 125, 141, 14);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("Dodijeljeni krediti: " + String.valueOf(Spremnik.getTrenutni().getUkupanBrKredita()));
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
		
		JButton button = new JButton("PDF prikaz");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				IzvjestajLogika _i = new IzvjestajLogika();
				IzvjestajSluzbenika izvjestaj = _i.generisiIzvjestajSluzbenika((String)comboBox.getSelectedItem());
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
		button.setBounds(10, 285, 121, 23);
		panel.add(button);
		
		JButton btnEmail = new JButton("E-mail");
		btnEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				IzvjestajLogika _i = new IzvjestajLogika();
				IzvjestajSluzbenika izvjestaj = _i.generisiIzvjestajSluzbenika((String)comboBox.getSelectedItem());
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
		btnEmail.setBounds(156, 285, 149, 23);
		panel.add(btnEmail);
		
		JButton button_2 = new JButton("Nazad");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SluzbenikGui.Izvjestaji.this.dispose();
			}
		});
		button_2.setBounds(460, 285, 89, 23);
		panel.add(button_2);
		
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
		Calendar cal = Calendar.getInstance();
		String datum = dateFormat.format(cal.getTime());
		
		JLabel label_8 = new JLabel("Datum i vrijeme: " + datum);
		label_8.setHorizontalAlignment(SwingConstants.TRAILING);
		label_8.setBounds(10, 175, 204, 14);
		panel.add(label_8);
	}

}
