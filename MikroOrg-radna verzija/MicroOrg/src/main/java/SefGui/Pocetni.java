package SefGui;

import aplikacija.MicroOrg.Login; 

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.SystemColor;

import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

import domainModels.Uposlenik;
import aplikacija.MicroOrg.Spremnik;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Pocetni extends JFrame {

	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	
	//ovo je konstruktor koji bi sluzio da ova klasa bude runnable from start, a to nam ne treba tako da...
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
				{
				try {
					Pocetni frame = new Pocetni();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {e.printStackTrace();}
				}
		});
	}

	/**
	 * Create the frame.
	 */
	public Pocetni() {
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosed(WindowEvent e) {
				aplikacija.MicroOrg.Login l=new aplikacija.MicroOrg.Login();
				l.setVisible(true);
				l.setResizable(false);
				l.setLocationRelativeTo(null);
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("MicroOrg - \u0160ef");
		setBounds(100, 100, 436, 409);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("Klijenti");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SefGui.Klijenti n =new SefGui.Klijenti();  //kreira klijenti gui za �efa
				n.setLocationRelativeTo(null);   // postavlja ga na sredinu
				n.setVisible(true);  // upali vidljivost
				n.setResizable(false);
				//SefGui.Pocetni.this.dispose(); // zatvara ovu formu
				SefGui.Pocetni.this.hide(); // nije dobro rje�enje jer ga skriva negdje...
			}
		});
		button.setIcon(new ImageIcon(Pocetni.class.getResource("/slike/users (1).png")));
		button.setHorizontalAlignment(SwingConstants.TRAILING);
		button.setBounds(10, 11, 153, 50);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Ponude");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SefGui.Ponude n =new SefGui.Ponude();  //kreira Ponude gui za �efa
				n.setLocationRelativeTo(null);   // postavlja ga na sredinu
				n.setVisible(true);  // upali vidljivost
				n.setResizable(false);
				//SefGui.Pocetni.this.dispose(); // zatvara ovu formu
				SefGui.Pocetni.this.hide(); // nije dobro rje�enje jer ga skriva negdje...
			}
		});
		button_1.setBackground(UIManager.getColor("Button.background"));
		button_1.setIcon(new ImageIcon(Pocetni.class.getResource("/slike/poooonuda.png")));
		button_1.setHorizontalAlignment(SwingConstants.RIGHT);
		button_1.setBounds(10, 72, 153, 50);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("Krediti");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SefGui.Krediti n =new SefGui.Krediti();  //kreira Krediti gui za �efa
				n.setLocationRelativeTo(null);   // postavlja ga na sredinu
				n.setVisible(true);  // upali vidljivost
				n.setResizable(false);
				//SefGui.Pocetni.this.dispose(); // zatvara ovu formu
				SefGui.Pocetni.this.hide(); // nije dobro rje�enje jer ga skriva negdje...
			}
		});
		button_2.setIcon(new ImageIcon(Pocetni.class.getResource("/slike/kredit.png")));
		button_2.setHorizontalAlignment(SwingConstants.TRAILING);
		button_2.setBackground(UIManager.getColor("Button.background"));
		button_2.setBounds(10, 133, 153, 50);
		contentPane.add(button_2);
		
		JButton button_4 = new JButton("Uposlenici");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SefGui.Uposlenici n =new SefGui.Uposlenici();  //kreira Uposlenici gui za �efa
				n.setLocationRelativeTo(null);   // postavlja ga na sredinu
				n.setVisible(true);  // upali vidljivost
				n.setResizable(false);
				//SefGui.Pocetni.this.dispose(); // zatvara ovu formu
				SefGui.Pocetni.this.hide(); // nije dobro rje�enje jer ga skriva negdje...
			}
		});
		button_4.setIcon(new ImageIcon(Pocetni.class.getResource("/slike/Workers.png")));
		button_4.setHorizontalAlignment(SwingConstants.RIGHT);
		button_4.setBackground(UIManager.getColor("Button.background"));
		button_4.setBounds(10, 194, 153, 50);
		contentPane.add(button_4);
		
		JButton button_5 = new JButton("Transakcije");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SefGui.Transakcije n =new SefGui.Transakcije();  //kreira Transakcije gui za �efa
				n.setLocationRelativeTo(null);   // postavlja ga na sredinu
				n.setVisible(true);  // upali vidljivost
				n.setResizable(false);
				//SefGui.Pocetni.this.dispose(); // zatvara ovu formu
				SefGui.Pocetni.this.hide(); // nije dobro rje�enje jer ga skriva negdje...
			}
		});
		button_5.setBackground(UIManager.getColor("Button.background"));
		button_5.setIcon(new ImageIcon(Pocetni.class.getResource("/slike/kredit.jpg")));
		button_5.setHorizontalAlignment(SwingConstants.RIGHT);
		button_5.setBounds(10, 255, 153, 50);
		contentPane.add(button_5);
		
		JButton button_6 = new JButton("Poslovanje organizacije");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SefGui.Poslovanje n =new SefGui.Poslovanje();  //kreira Poslovanje gui za �efa
				n.setLocationRelativeTo(null);   // postavlja ga na sredinu
				n.setVisible(true);  // upali vidljivost
				n.setResizable(false);
				//SefGui.Pocetni.this.dispose(); // zatvara ovu formu
				SefGui.Pocetni.this.hide(); // nije dobro rje�enje jer ga skriva negdje...
			}
		});
		button_6.setBackground(UIManager.getColor("Button.background"));
		button_6.setBounds(10, 316, 190, 50);
		contentPane.add(button_6);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(128, 0, 0));
		panel.setBorder(new LineBorder(new Color(128, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(220, 159, 190, 114);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label_5 = new JLabel("Ime:");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setBounds(10, 11, 79, 14);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("Prezime:");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setBounds(10, 36, 79, 14);
		panel.add(label_6);
		
		JLabel label_7 = new JLabel("Pozicija:");
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		label_7.setBounds(10, 64, 79, 14);
		panel.add(label_7);
		
		JLabel label_8 = new JLabel("Datum:");
		label_8.setHorizontalAlignment(SwingConstants.RIGHT);
		label_8.setBounds(10, 89, 79, 14);
		panel.add(label_8);
		
		JLabel label_9 = new JLabel("/");
		label_9.setHorizontalAlignment(SwingConstants.LEFT);
		label_9.setBounds(99, 11, 79, 14);
		panel.add(label_9);
		
		JLabel label_10 = new JLabel("/");
		label_10.setHorizontalAlignment(SwingConstants.LEFT);
		label_10.setBounds(99, 36, 79, 14);
		panel.add(label_10);
		
		JLabel label_11 = new JLabel("/");
		label_11.setHorizontalAlignment(SwingConstants.LEFT);
		label_11.setBounds(99, 64, 79, 14);
		panel.add(label_11);
		
		JLabel label_12 = new JLabel("/");
		label_12.setHorizontalAlignment(SwingConstants.LEFT);
		label_12.setBounds(99, 89, 79, 14);
		panel.add(label_12);
		
		JButton button_7 = new JButton("Odjavi se!");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SefGui.Pocetni.this.dispose();
			}
		});
		button_7.setIcon(new ImageIcon(Pocetni.class.getResource("/slike/logout (1).png")));
		button_7.setHorizontalAlignment(SwingConstants.TRAILING);
		button_7.setBounds(257, 316, 153, 50);
		contentPane.add(button_7);
		
		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon(Pocetni.class.getResource("/slike/logo.jpg")));
		label_4.setBounds(212, 11, 200, 114);
		contentPane.add(label_4);
	}

}
