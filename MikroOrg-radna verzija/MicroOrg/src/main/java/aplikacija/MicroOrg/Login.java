package aplikacija.MicroOrg;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import SefGui.Pocetni;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import logic.SharedLogika;
import logic.UposlenikLogika;

import javax.swing.JPasswordField;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import domainModels.HibernateUtil;
import domainModels.Uposlenik;

import java.sql.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import viewModels.KreditniSluzbenik;
import domainModels.HibernateUtil;
import domainModels.Osoba;
import domainModels.Uposlenik;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class Login extends JFrame {
	private Uposlenik _u=null;
	private long _userId=0;
	private boolean _userPosition;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private Session sesija;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login novi = new Login();
					novi.setVisible(true); //za svaki slucaj ako bude pozvan iz neke druge forme...
					novi.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("MicroOrg");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 420, 320);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(204, 186, 86, 20);
		contentPane.add(textField);
		
		JLabel lblKorisnikoImejmbg = new JLabel("Korisničko ime(JMBG):");
		lblKorisnikoImejmbg.setHorizontalAlignment(SwingConstants.RIGHT);
		lblKorisnikoImejmbg.setBounds(29, 189, 158, 14);
		contentPane.add(lblKorisnikoImejmbg);
		
		JLabel label_1 = new JLabel("\u0160ifra:");
		label_1.setBounds(161, 220, 33, 14);
		contentPane.add(label_1);
		
		JButton button = new JButton("Prijavi se!");
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				SharedLogika validacija=new SharedLogika();
				if (validacija.validirajJMB(textField.getText())) Ispitaj(textField.getText(),passwordField.getText());
				else {JOptionPane.showMessageDialog(null, "Nepravilan jmbg...","Pogrešan format", JOptionPane.INFORMATION_MESSAGE);}
			} //klik na dugme login
			
		});
		
		button.setBounds(204, 248, 89, 23);
		contentPane.add(button);
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(Login.class.getResource("/slike/logo.jpg")));
		label_2.setBounds(93, 37, 200, 114);
		contentPane.add(label_2);
		passwordField = new JPasswordField();
		passwordField.setBounds(204, 217, 86, 20);
		contentPane.add(passwordField);
	}

//Provjerava u bazi i postavlja varijable
public Boolean Pronadji(String _username, String _jmbg) {
		Session _session=null;
		Transaction _t=null;
		Criteria criteria=null;
		try{
			_session= HibernateUtil.getSessionFactory().openSession();
			_t = _session.beginTransaction();  
			criteria = _session.createCriteria(Uposlenik.class);
		}catch(Exception e){JOptionPane.showMessageDialog(null, "Nije uredu veza sa bazom podataka! Možda server nije uključen, ili baza nije podignuta. Ukoliko imate nejasnoća obratite se administratoru: admin@silaboration.org","Povezivanje s bazom podataka", JOptionPane.INFORMATION_MESSAGE);}

		try
		{
			_u =(Uposlenik) criteria.add(Restrictions.eq("password", _jmbg)).add(Restrictions.eq("username",_username)).uniqueResult();
			_userId=_u.getUposlenikId();
			_userPosition=_u.getPrivilegije();
			_t.commit();
		}
		catch(Exception e){}
		_session.close();
		return _u!=null;
} 

//Odlučuje koja se forma otvara
public void Ispitaj(String username,String sifra){
	//ovdje ubaciti validaciju jos
	
	if(Pronadji(username,sifra)==true) //ispituje polja za unos usernamea i šifre
	{
		Spremnik novi=new Spremnik();
		novi.setTrenutni(_u);
	if (_userPosition==true)
		{
		SefGui.Pocetni n =new SefGui.Pocetni();  //kreira novi početni gui za šefa
		Spremnik.setPocetni(n);
		Spremnik.getPocetni().show();
		Spremnik.getPocetni().setLocationRelativeTo(null);   // postavlja ga na sredinu
		Spremnik.getPocetni().setVisible(true);  // upali vidljivost
		Spremnik.getPocetni().setResizable(false);
		Login.this.dispose(); //zatvara ovu login formu
		}
	else if (_userPosition==false)
		{
		SluzbenikGui.Pocetni n =new SluzbenikGui.Pocetni();  //kreira novi početni gui za sluzbenika
		Spremnik.setPocetni(n);
		Spremnik.getPocetni().show();
		Spremnik.getPocetni().setLocationRelativeTo(null);   // postavlja ga na sredinu
		Spremnik.getPocetni().setVisible(true);  // upali vidljivost
		Spremnik.getPocetni().setResizable(false);
		Login.this.dispose(); //zatvara ovu login formu
		}
	}
	else {JOptionPane.showMessageDialog(null, "Žao nam je, pogrešan unos!","Greška", JOptionPane.INFORMATION_MESSAGE);
	textField.setText(""); passwordField.setText("");}
}

}
