package graphicalInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;

import exceptions.IncorrectCrewNumberException;
import exceptions.TooManyCharactersException;

import javax.swing.event.ChangeEvent;


public class StartScreen {
	private GameManager manager;
	private JFrame frame;
	private JTextField nameShipTextField;

	/**
	 * Create the application.
	 */
	public StartScreen(GameManager incomingManager) {
		manager = incomingManager;
		initialize();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
	}
	public void closeWindow() {
		frame.dispose();
	}
	public void finishedWindow() {
		manager.closeStartScreen(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(StartScreen.class.getResource("/Images/StartScreenBackground.jpg")));
		frame.setResizable(false);
		
		frame.setBounds(100, 100, 1127, 696);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
			
		JLabel shipNameLabel = new JLabel("Spaceship Name:");
		shipNameLabel.setForeground(new Color(255, 255, 255));
		shipNameLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 50));
		shipNameLabel.setBounds(88, 19, 380, 104);
		frame.getContentPane().add(shipNameLabel);
		
		JLabel numDaysTotalLabel = new JLabel("0");
		numDaysTotalLabel.setForeground(new Color(255, 255, 255));
		numDaysTotalLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 50));
		numDaysTotalLabel.setBounds(480, 150, 65, 105);
		frame.getContentPane().add(numDaysTotalLabel);
		
		nameShipTextField = new JTextField();
		nameShipTextField.setForeground(Color.WHITE);
		nameShipTextField.setBackground(Color.DARK_GRAY);
		nameShipTextField.setFont(new Font("Eras Medium ITC", Font.PLAIN, 50));
		nameShipTextField.setBounds(577, 31, 519, 96);
		frame.getContentPane().add(nameShipTextField);
		nameShipTextField.setColumns(10);
		
		JLabel numDaysLabel = new JLabel("Number of Days:");
		numDaysLabel.setForeground(new Color(255, 255, 255));
		numDaysLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 50));
		numDaysLabel.setBounds(88, 150, 391, 105);
		frame.getContentPane().add(numDaysLabel);;
		
		
		JSlider daysSlider = new JSlider();
		daysSlider.setBackground(Color.DARK_GRAY);
		daysSlider.setValue(3);
		daysSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				numDaysTotalLabel.setText(Integer.toString(daysSlider.getValue()));
			}
		});
		daysSlider.setPaintTicks(true);
		daysSlider.setMinorTickSpacing(1);
		daysSlider.setMinimum(3);
		daysSlider.setMaximum(10);
		daysSlider.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		daysSlider.setBounds(577, 218, 519, 64);
		frame.getContentPane().add(daysSlider);
		daysSlider.setPaintLabels(true);
		
		
		JLabel days3Label = new JLabel("3");
		days3Label.setForeground(new Color(255, 255, 255));
		days3Label.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		days3Label.setBounds(577, 177, 25, 30);
		frame.getContentPane().add(days3Label);
		
		JLabel days10Label = new JLabel("10");
		days10Label.setForeground(new Color(255, 255, 255));
		days10Label.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		days10Label.setBounds(1049, 171, 47, 42);
		frame.getContentPane().add(days10Label);
		
		JButton prepCrewButton = new JButton("Select Spaceship");
		prepCrewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nameShipTextField.getText().equals("")) { 
					JOptionPane.showMessageDialog(null, "Please enter something.");
				} else {
					try {
					manager.setTotalDays(daysSlider.getValue());
					manager.getGame().setTotalDays(daysSlider.getValue());
					manager.setShipName(nameShipTextField.getText());
					closeWindow();
					finishedWindow();
					} catch(TooManyCharactersException message) {
						JOptionPane.showMessageDialog(null, message);
					}
				}
			}
		});
		prepCrewButton.setForeground(Color.WHITE);
		prepCrewButton.setBackground(Color.DARK_GRAY);
		prepCrewButton.setFont(new Font("Eras Medium ITC", Font.PLAIN, 50));
		prepCrewButton.setBounds(577, 358, 519, 198);
		frame.getContentPane().add(prepCrewButton);
		
		JLabel spaceShipImage = new JLabel("");
		spaceShipImage.setIcon(new ImageIcon(StartScreen.class.getResource("/Images/startSpaceShip.png")));
		spaceShipImage.setBounds(43, 264, 507, 378);
		frame.getContentPane().add(spaceShipImage);
		
		JLabel backgroundImage = new JLabel("");
		backgroundImage.setIcon(new ImageIcon(StartScreen.class.getResource("/Images/startScreenBackground.jpg")));
		backgroundImage.setBounds(0, 0, 1121, 667);
		frame.getContentPane().add(backgroundImage);
	}
	
}
