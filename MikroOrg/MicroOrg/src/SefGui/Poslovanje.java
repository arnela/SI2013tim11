package SefGui;

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
import javax.swing.JTextField;
import javax.swing.JRadioButton;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Poslovanje extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
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
		
		JLabel label = new JLabel("Datum i vrijeme:");
		label.setHorizontalAlignment(SwingConstants.TRAILING);
		label.setBounds(10, 50, 141, 14);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Podaci o zaposlenicima:");
		label_1.setBounds(307, 39, 231, 14);
		panel.add(label_1);
		
		JList list = new JList();
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setBounds(307, 59, 242, 214);
		panel.add(list);
		
		JLabel label_2 = new JLabel("Prihodi:");
		label_2.setHorizontalAlignment(SwingConstants.TRAILING);
		label_2.setBounds(10, 75, 141, 14);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("Rashodi:");
		label_3.setHorizontalAlignment(SwingConstants.TRAILING);
		label_3.setBounds(10, 100, 141, 14);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("Izvr\u0161ene transakcije:");
		label_4.setHorizontalAlignment(SwingConstants.TRAILING);
		label_4.setBounds(10, 125, 141, 14);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("Dodijeljeni krediti:");
		label_5.setHorizontalAlignment(SwingConstants.TRAILING);
		label_5.setBounds(10, 150, 141, 14);
		panel.add(label_5);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(188, 8, 176, 20);
		panel.add(comboBox);
		
		JLabel label_6 = new JLabel("Za protekli vremenski period:");
		label_6.setHorizontalAlignment(SwingConstants.TRAILING);
		label_6.setBounds(10, 11, 176, 14);
		panel.add(label_6);
		
		JLabel label_7 = new JLabel("Kreirano izvje\u0161taja:");
		label_7.setHorizontalAlignment(SwingConstants.TRAILING);
		label_7.setBounds(10, 175, 141, 14);
		panel.add(label_7);
		
		JLabel label_8 = new JLabel("Kreirano tipova kredita:");
		label_8.setHorizontalAlignment(SwingConstants.TRAILING);
		label_8.setBounds(10, 200, 141, 14);
		panel.add(label_8);
		
		JLabel label_9 = new JLabel("Broj uposlenih:");
		label_9.setHorizontalAlignment(SwingConstants.TRAILING);
		label_9.setBounds(10, 225, 141, 14);
		panel.add(label_9);
		
		JLabel label_10 = new JLabel("Broj novih klijenata:");
		label_10.setHorizontalAlignment(SwingConstants.TRAILING);
		label_10.setBounds(10, 250, 141, 14);
		panel.add(label_10);
		
		JButton button = new JButton("PDF prikaz");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Nije implementirano !");
			}
		});
		button.setBounds(10, 285, 110, 23);
		panel.add(button);
		
		JButton button_1 = new JButton("Kreiraj&Spasi");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Nije implementirano !");
			}
		});
		button_1.setBounds(130, 285, 163, 23);
		panel.add(button_1);
		
		JButton button_2 = new JButton("Nazad");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SefGui.Poslovanje.this.dispose();
			}
		});
		button_2.setBounds(460, 285, 89, 23);
		panel.add(button_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		tabbedPane.addTab("Prikaz i pretraga", null, panel_1, null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(128, 0, 0), 1, true));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(10, 54, 358, 219);
		panel_1.add(panel_2);
		
		JButton button_3 = new JButton("PDF prikaz");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Nije implementirano !");
			}
		});
		button_3.setBounds(10, 284, 112, 23);
		panel_1.add(button_3);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new LineBorder(new Color(165, 42, 42), 1, true));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(10, 11, 358, 32);
		panel_1.add(panel_3);
		
		JLabel label_11 = new JLabel("Podaci za pretragu:");
		label_11.setHorizontalAlignment(SwingConstants.TRAILING);
		label_11.setBounds(-2, 9, 119, 14);
		panel_3.add(label_11);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(127, 6, 221, 20);
		panel_3.add(textField);
		
		JLabel label_12 = new JLabel("");
		label_12.setBounds(174, 16, 0, 0);
		panel_3.add(label_12);
		
		JButton button_4 = new JButton("Pretra\u017Ei po:");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Nije implementirano !");
			}
		});
		button_4.setBounds(405, 11, 121, 32);
		panel_1.add(button_4);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBounds(376, 54, 179, 114);
		panel_1.add(panel_4);
		
		JRadioButton radioButton = new JRadioButton("Datumu unosa");
		radioButton.setBounds(6, 7, 177, 23);
		panel_4.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("Proteklih (broj) dana");
		radioButton_1.setBounds(6, 85, 177, 23);
		panel_4.add(radioButton_1);
		
		JRadioButton radioButton_2 = new JRadioButton("Proteklih (broj) mjeseci");
		radioButton_2.setBounds(6, 33, 177, 23);
		panel_4.add(radioButton_2);
		
		JRadioButton radioButton_3 = new JRadioButton("Proteklih (broj) godina");
		radioButton_3.setBounds(6, 59, 177, 23);
		panel_4.add(radioButton_3);
		
		JButton button_5 = new JButton("Nazad");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SefGui.Poslovanje.this.dispose();
			}
		});
		button_5.setBounds(466, 284, 89, 23);
		panel_1.add(button_5);
		
		JButton button_6 = new JButton("Po\u0161alji na E-mail");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Nije implementirano !");
			}
		});
		button_6.setBounds(132, 284, 142, 23);
		panel_1.add(button_6);
	}

}
