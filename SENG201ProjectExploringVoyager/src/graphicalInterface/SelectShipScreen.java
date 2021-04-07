package graphicalInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import matter.SpaceShip;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SelectShipScreen {
	private GameManager manager;
	private JFrame frame;

	/**
	 * Create the application.
	 */
	public SelectShipScreen(GameManager incomingManager) {
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
		manager.closeSelectShipScreen(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		SpaceShip[] shipHangar = manager.getShipHangar();
		
		JLabel SpaceShipPilotLabel = new JLabel("Select Which Space Ship To Pilot:");
		SpaceShipPilotLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 50));
		SpaceShipPilotLabel.setForeground(Color.WHITE);
		SpaceShipPilotLabel.setBounds(257, 47, 768, 58);
		frame.getContentPane().add(SpaceShipPilotLabel);
		
		
		JLabel spaceShip1StatsLabel = new JLabel("<html>Design: " + shipHangar[0].getName() +  "<br/>\r\nHealth: " + shipHangar[0].getMaxHealth() + "<br/>\r\nShield Capacity: " + shipHangar[0].getMaxShieldCapacity() +
												"<br/>\r\nFuel Capacity: " + shipHangar[0].getMaxFuelCapacity() + "<br/>\r\nInventory Slots: " + shipHangar[0].getItemInventoryLimit() + "<br/>\r\n</html>");
		spaceShip1StatsLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		spaceShip1StatsLabel.setForeground(Color.WHITE);
		spaceShip1StatsLabel.setBounds(41, 381, 220, 210);
		frame.getContentPane().add(spaceShip1StatsLabel);
		JLabel spaceShip2StatsLabel = new JLabel("<html>Design: " + shipHangar[1].getName() +  "<br/>\r\nHealth: " + shipHangar[1].getMaxHealth() + "<br/>\r\nShield Capacity: " + shipHangar[1].getMaxShieldCapacity() +
												"<br/>\r\nFuel Capacity: " + shipHangar[1].getMaxFuelCapacity() + "<br/>\r\nInventory Slots: " + shipHangar[1].getItemInventoryLimit() + "<br/>\r\n</html>");
		spaceShip2StatsLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		spaceShip2StatsLabel.setForeground(Color.WHITE);
		spaceShip2StatsLabel.setBounds(281, 401, 220, 177);
		frame.getContentPane().add(spaceShip2StatsLabel);
		JLabel spaceShip3StatsLabel = new JLabel("<html>Design: " + shipHangar[2].getName() +  "<br/>\r\nHealth: " + shipHangar[2].getMaxHealth() + "<br/>\r\nShield Capacity: " + shipHangar[2].getMaxShieldCapacity() +
												"<br/>\r\nFuel Capacity: " + shipHangar[2].getMaxFuelCapacity() + "<br/>\r\nInventory Slots: " + shipHangar[2].getItemInventoryLimit() + "<br/>\r\n</html>");
		spaceShip3StatsLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		spaceShip3StatsLabel.setForeground(Color.WHITE);
		spaceShip3StatsLabel.setBounds(521, 401, 220, 177);
		frame.getContentPane().add(spaceShip3StatsLabel);
		JLabel spaceShip4StatsLabel = new JLabel("<html>Design: " + shipHangar[3].getName() +  "<br/>\r\nHealth: " + shipHangar[3].getMaxHealth() + "<br/>\r\nShield Capacity: " + shipHangar[3].getMaxShieldCapacity() +
												"<br/>\r\nFuel Capacity: " + shipHangar[3].getMaxFuelCapacity() + "<br/>\r\nInventory Slots: " + shipHangar[3].getItemInventoryLimit() + "<br/>\r\n</html>");
		spaceShip4StatsLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		spaceShip4StatsLabel.setForeground(Color.WHITE);
		spaceShip4StatsLabel.setBounds(761, 401, 220, 177);
		frame.getContentPane().add(spaceShip4StatsLabel);
		JLabel spaceShip5StatsLabel = new JLabel("<html>Design: " + shipHangar[4].getName() +  "<br/>\r\nHealth: " + shipHangar[4].getMaxHealth() + "<br/>\r\nShield Capacity: " + shipHangar[4].getMaxShieldCapacity() +
											"<br/>\r\nFuel Capacity: " + shipHangar[4].getMaxFuelCapacity() + "<br/>\r\nInventory Slots: " + shipHangar[4].getItemInventoryLimit() + "<br/>\r\n</html>");
		spaceShip5StatsLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		
		spaceShip5StatsLabel.setForeground(Color.WHITE);
		spaceShip5StatsLabel.setBounds(1001, 401, 220, 177);
		frame.getContentPane().add(spaceShip5StatsLabel);
		
		JButton selectShip1ButtonImage = new JButton("");
		selectShip1ButtonImage.setIcon(new ImageIcon(SelectShipScreen.class.getResource("/Images/spaceCraft1_210px.png")));
		selectShip1ButtonImage.setBackground(Color.DARK_GRAY);
		selectShip1ButtonImage.setBounds(41, 158, 220, 220);
		frame.getContentPane().add(selectShip1ButtonImage);
		selectShip1ButtonImage.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				manager.setPlayerShip(shipHangar[0]);
				closeWindow();
				finishedWindow();
			}
		});
		
		JButton selectShip2ButtonImage = new JButton("");
		selectShip2ButtonImage.setIcon(new ImageIcon(SelectShipScreen.class.getResource("/Images/spaceCraft2_210px.png")));
		selectShip2ButtonImage.setBackground(Color.DARK_GRAY);
		selectShip2ButtonImage.setBounds(279, 158, 220, 220);
		frame.getContentPane().add(selectShip2ButtonImage);
		selectShip2ButtonImage.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				manager.setPlayerShip(shipHangar[1]);
				closeWindow();
				finishedWindow();
			}
		});
		
		JButton selectShip3ButtonImage = new JButton("");
		selectShip3ButtonImage.setIcon(new ImageIcon(SelectShipScreen.class.getResource("/Images/spaceCraft3_210px.png")));
		selectShip3ButtonImage.setBackground(Color.DARK_GRAY);
		selectShip3ButtonImage.setBounds(521, 158, 220, 220);
		frame.getContentPane().add(selectShip3ButtonImage);
		selectShip3ButtonImage.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				manager.setPlayerShip(shipHangar[2]);
				closeWindow();
				finishedWindow();
			}
		});
		
		JButton selectShip4ButtonImage = new JButton("");
		selectShip4ButtonImage.setIcon(new ImageIcon(SelectShipScreen.class.getResource("/Images/spaceCraft4_210px.png")));
		selectShip4ButtonImage.setBackground(Color.DARK_GRAY);
		selectShip4ButtonImage.setBounds(761, 158, 220, 220);
		frame.getContentPane().add(selectShip4ButtonImage);
		selectShip4ButtonImage.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				manager.setPlayerShip(shipHangar[3]);
				closeWindow();
				finishedWindow();
			}
		});
		
		JButton selectShip5ButtonImage = new JButton("");
		selectShip5ButtonImage.setIcon(new ImageIcon(SelectShipScreen.class.getResource("/Images/spaceCraft6_210px.png")));
		selectShip5ButtonImage.setBackground(Color.DARK_GRAY);
		selectShip5ButtonImage.setBounds(1001, 158, 220, 220);
		frame.getContentPane().add(selectShip5ButtonImage);
		selectShip5ButtonImage.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				manager.setPlayerShip(shipHangar[4]);
				closeWindow();
				finishedWindow();
			}
		});
		
		JLabel backgroundImage = new JLabel("");
		backgroundImage.setForeground(Color.DARK_GRAY);
		backgroundImage.setIcon(new ImageIcon(SelectShipScreen.class.getResource("/Images/startScreenBackground.jpg")));
		backgroundImage.setBounds(0, 0, 1264, 681);
		frame.getContentPane().add(backgroundImage);
	}
}