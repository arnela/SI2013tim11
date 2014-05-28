package SefGui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

import domainModels.Uposlenik;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import aplikacija.MicroOrg.Spremnik;
import logic.TransakcijaLogika;
import logic.UposlenikLogika;

public class Transakcije extends JFrame {

	private JPanel contentPane;
	private JTextField tf_podaciPretrage;
	private Uposlenik trenutni;
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
				SefGui.Pocetni n =new SefGui.Pocetni();  //kreira novi po�etni gui za �efa
				n.setLocationRelativeTo(null);   // postavlja ga na sredinu
				n.setVisible(true);  // upali vidljivost
				n.setResizable(false);
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
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(128, 0, 0), 1, true));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 54, 385, 311);
		panel.add(panel_1);
		
		JButton button = new JButton("PDF prikaz");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Nije implementirano !");
			}
		});
		button.setBounds(10, 376, 112, 23);
		panel.add(button);
		
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
		
		final JRadioButton radioButton = new JRadioButton("Ime i Prezime uposlenika");
		radioButton.setBounds(6, 7, 170, 23);
		panel_3.add(radioButton);
		
		final JRadioButton radioButton_1 = new JRadioButton("Klijent - Ime&Prezime");
		radioButton_1.setBounds(6, 33, 160, 23);
		panel_3.add(radioButton_1);
		
		final JRadioButton radioButton_2 = new JRadioButton("Naziv tipa kredita");
		radioButton_2.setBounds(6, 58, 160, 23);
		panel_3.add(radioButton_2);
		
		JButton button_1 = new JButton("Pretra\u017Ei po:");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TransakcijaLogika _transakcijaLogika = new TransakcijaLogika();
				
				if(radioButton.isSelected()){
					
					
				}
				else if(radioButton_1.isSelected()){}
				else if(radioButton_2.isSelected()){}
				
				
			//	if(_transakcijaLogika.daLiPostoji(tf_podaciPretrage.getText())){	
			//	}
					
				
				
				JOptionPane.showMessageDialog(null, "Nije implementirano !");
			}
		});
		button_1.setBounds(405, 11, 134, 32);
		panel.add(button_1);
		
		
		
		
		
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
