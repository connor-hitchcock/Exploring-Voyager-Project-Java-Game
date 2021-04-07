package graphicalInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import main.GameEnvironment;
import matter.CrewMember;
import matter.SpaceShip;

import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import exceptions.DeadCrewException;
import exceptions.GameOverException;
import exceptions.RandomEventException;
import exceptions.TakenDamageException;

public class MainShipScreen extends GenericScreenProcessing {
	private GameManager manager;
	private JFrame frame;

	public MainShipScreen(GameManager incomingManager) {
		manager = incomingManager;
		if (manager.getCompletedGame()) {
			finishedWindowToEndScreen();
		} else {
			initialize();
			frame.setVisible(true);
			frame.setResizable(false);
			frame.getContentPane().setLayout(null);
		}
	}
	public void closeWindow() {
		frame.dispose();
	}
	public void finishedWindow() {
		manager.closeMainShipScreen(this);
	}
	public void finishedWindowToGameOver() {
		manager.closeMainShipScreenToGameOver(this);
	}
	public void finishedWindowToEndScreen() {
		manager.closeMainShipScreenToEndScreen(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		generateStatusText(frame);
		JLabel[] healthStatus = generateStatusBar("Health", manager.getPlayerShip().getMaxHealth(), frame);
		JLabel[] shieldStatus = generateStatusBar("Shields", manager.getPlayerShip().getMaxShieldCapacity(), frame);
		JLabel[] fuelStatus = generateStatusBar("Fuel", manager.getPlayerShip().getMaxFuelCapacity(), frame);
		JLabel[] healthBackground = generateBackgroundBarList("Health", manager.getPlayerShip().getMaxHealth(), frame);
		JLabel[] shieldBackground = generateBackgroundBarList("Shield", manager.getPlayerShip().getMaxShieldCapacity(), frame);
		JLabel[] fuelBackground = generateBackgroundBarList("Fuel", manager.getPlayerShip().getMaxFuelCapacity(), frame);
		updateAllBars(fuelStatus, shieldStatus, healthStatus);
		int coordinateX = manager.getPlayerShip().getCoordinateX();
		int coordinateY = manager.getPlayerShip().getCoordinateY();
		
		JLabel currentPlanetUpdatingImage = new JLabel("");
		currentPlanetUpdatingImage.setIcon(new ImageIcon(MainShipScreen.class.getResource("/Images/PlanetRed_400px.png")));
		currentPlanetUpdatingImage.setBounds(452, 142, 400, 400);
		if (manager.getPlayerShip().getPlane()[coordinateX][coordinateY].getCurrentPlanet() != null) {
			currentPlanetUpdatingImage.setVisible(true);
		} else {
			if (manager.getPlayerShip().getPlane()[coordinateX][coordinateY].getCurrentAsteroidBelt() != null) {
				currentPlanetUpdatingImage.setIcon(new ImageIcon(MainShipScreen.class.getResource("/Images/Asteroid2.png")));
			} else {
				currentPlanetUpdatingImage.setVisible(false); 
			}
		}
		frame.getContentPane().add(currentPlanetUpdatingImage);
		
		generateCoordinates(manager.getPlayerShip(), frame);
		JLabel timeLabel = generateTimeLabel(frame);
		
		JButton traverseButton = new JButton("Traverse");
		traverseButton.setBackground(Color.DARK_GRAY);
		traverseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.launchTraverseScreen();	
				closeWindow();
				finishedWindow();
			}
		}); 
		
		traverseButton.setForeground(Color.WHITE);
		traverseButton.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		traverseButton.setBounds(123, 130, 234, 112);
		frame.getContentPane().add(traverseButton);
		
		JButton restButton = new JButton("Rest");
		restButton.setBackground(Color.DARK_GRAY);
		restButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				try {
				String restInformation = manager.getPlayerShip().restAllMembers(manager.getGame().getEvents());
				JLabel restDialog = new JLabel(restInformation);
				restDialog.setFont(new Font("Tahoma", Font.PLAIN, 25));
				JOptionPane.showMessageDialog(null, restDialog);
				} catch (GameOverException message) {
					manager.setDeathMessage("Game Over - Loss of time management skills.");
					closeWindow();
					finishedWindowToGameOver();
				} catch(DeadCrewException message) {
					JOptionPane.showMessageDialog(null, message.toString());
				} catch(RandomEventException message) {
					JOptionPane.showMessageDialog(null, message.toString());
				} catch(TakenDamageException message) {
						JOptionPane.showMessageDialog(null, message.toString());
				} finally {
					updateTimeLabel(timeLabel);
				}
			}
		});
		restButton.setForeground(Color.WHITE);
		restButton.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		restButton.setBounds(123, 300, 234, 112);
		frame.getContentPane().add(restButton);
		
		JButton viewItemsButton = new JButton("View Items");
		viewItemsButton.setBackground(Color.DARK_GRAY);
		viewItemsButton.setForeground(Color.WHITE);
		viewItemsButton.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		viewItemsButton.setBounds(123, 465, 234, 112);
		viewItemsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.launchItemMenuScreen();
				closeWindow();
				finishedWindow();
			}
		});
		frame.getContentPane().add(viewItemsButton);
		
		JButton enterPlanetButton = new JButton("Land");
		enterPlanetButton.setBackground(Color.DARK_GRAY);
		enterPlanetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				manager.launchExploreScreen(); 
				GameEnvironment.addHours(manager.getPlayerShip(), 1);
				} catch (GameOverException message) {
					manager.setDeathMessage("Game Over - Bolstered the Space Tourism industry.");
					closeWindow();
					finishedWindowToGameOver();
				} catch(DeadCrewException message) {
					JOptionPane.showMessageDialog(null, message);
					closeWindow();
					finishedWindow();
				} catch(TakenDamageException message) {
					JOptionPane.showMessageDialog(null, message);
					closeWindow();
					finishedWindow();
				} 
				closeWindow();
				finishedWindow();
				try {
					manager.getGame().getEvents().doRandomEvent(manager.getPlayerShip(), "landing");
				} catch (RandomEventException message) {
					JOptionPane.showMessageDialog(null, message);
				}
			}
		});
		if (manager.getPlayerShip().checkLocation() == false) {
			enterPlanetButton.setEnabled(false);
		}
		enterPlanetButton.setForeground(Color.WHITE);
		enterPlanetButton.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		enterPlanetButton.setBounds(930, 130, 234, 112);
		frame.getContentPane().add(enterPlanetButton);
		
		JButton repairShipButton = new JButton("Repair Ship");
		repairShipButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				findCandidateForRepair(manager.getPlayerShip());
				updateAllBars(fuelStatus, shieldStatus, healthStatus);
				} catch (GameOverException message) {
					manager.setDeathMessage("If only you could fix your time management skills.");
					closeWindow();
					finishedWindowToGameOver();
				} catch(DeadCrewException message) {
					JOptionPane.showMessageDialog(null, message);
			} finally {
				closeWindow();
				finishedWindow();
			}
			}
		});
		repairShipButton.setBackground(Color.DARK_GRAY);
		repairShipButton.setForeground(Color.WHITE);
		repairShipButton.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		repairShipButton.setBounds(930, 300, 234, 112);
		frame.getContentPane().add(repairShipButton);
		
		JButton helpManualButton = new JButton("Help");
		helpManualButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JLabel helpInfo = new JLabel(getHelpManual());
				helpInfo.setFont(new Font("Tahoma", Font.PLAIN, 20));
				JOptionPane.showMessageDialog(null, helpInfo);
			}
		});
		helpManualButton.setBackground(Color.DARK_GRAY);
		helpManualButton.setForeground(Color.WHITE);
		helpManualButton.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		helpManualButton.setBounds(930, 465, 234, 112);
		frame.getContentPane().add(helpManualButton);
		
		JLabel backgroundCordsImage = new JLabel("");
		backgroundCordsImage.setBounds(0, 0, 94, 93);
		frame.getContentPane().add(backgroundCordsImage);
		
		JLabel HUDImage = new JLabel("");
		HUDImage.setIcon(new ImageIcon(MainShipScreen.class.getResource("/Images/view1.png")));
		HUDImage.setBounds(0, 0, 1311, 681);
		frame.getContentPane().add(HUDImage);
		
		JLabel backgroundImage = new JLabel("");
		backgroundImage.setIcon(new ImageIcon(MainShipScreen.class.getResource("/Images/startScreenBackground.jpg")));
		backgroundImage.setBounds(0, 0, 1264, 681);
		frame.getContentPane().add(backgroundImage);
	}
	public void updateAllBars(JLabel[] fuelStatus, JLabel[] shieldStatus, JLabel[] healthStatus) {
		updateStatusBar("Fuel", manager.getPlayerShip().getFuelCapacity(), fuelStatus);
		updateStatusBar("Shields", manager.getPlayerShip().getShieldCapacity(), shieldStatus);
		updateStatusBar("Health", manager.getPlayerShip().getCurrentHealth(), healthStatus);
	}
	
	public String getHelpManual() {
		String helpInfo = "<html>";
		helpInfo += ("=========================GUIDE TO SURVIVING THE UNIVERSE======================<br>");
		helpInfo += ("SHIP: Your ship is equipped slots that can help hold items which may be useful for your travels.<br>");
		helpInfo += ("ITEMS: Items fall under 5 categories; see below:<br>");
		helpInfo += ("      CREWITEMS: A consumable which increases a level of a crew member's stats.<br>");
		helpInfo += ("      FOODITEMS: Increases a crew member's stamina.<br>");
		helpInfo += ("      FUELCANISTERS: Restores a portion of your ship's fuel.<br>");
		helpInfo += ("      MEDICALITEMS: Restores the health of a crew member.<br>");
		helpInfo += ("------------------------------------------------------------------------------------------------------------------------------------------------------<br>");
		helpInfo += ("SHIP SHIELDS: Restores overtime, takes damage before your hull does.<br>");
		helpInfo += ("REPAIRING YOUR SHIP: You can repair your ship through the console in the SHIP MENU. Note: This only restores the health of the ship, not the shields.<br>");
		helpInfo += ("==================================CREW================================<br>");
		helpInfo += ("STATS: A set of a skills which defines a crew member.<br>");
		helpInfo += ("      HEALTH: How much damage a crew member can take before dying.<br>");
		helpInfo += ("      STAMINA: Allows the crew member to perform more actions before having to rest.<br>");
		helpInfo += ("      CHARISMA: Reduces the prices of items in the shop.<br>");
		helpInfo += ("      REPAIR LEVEL: Increases the amount of ship health that gets healed when this crew member repairs.<br>");
		helpInfo += ("      SCAVENGING LEVEL: Increases the money reward when exploring.<br>");
		helpInfo += ("      HEALER LEVEL: Inceases the additional healing done when healing another crew member.<br>");
		helpInfo += ("================================TRAVERSING==============================<br>");
		helpInfo += ("TRAVERSING MENU: Select a set of coordinates to travel to.<br>");
		helpInfo += ("      ASTEROID BELTS: Have a higher chance of getting hit by an asteroid.<br>");
		helpInfo += ("      PLANETS: Where starpieces will drop.<br>");
		helpInfo += ("      SPACE PLAGUE: Causes crew to take damage overtime.<br>");
		helpInfo += ("      PIZZA CUTTER PIRATES: Notorius pirates known for stealing, and not putting enough pepperoni on pizzas.<br>");
		helpInfo += ("=================================EXPLORING==============================<br>");
		helpInfo += ("OUTPOSTS: A place where you can buy items for space credits. To visit one, explore to an outpost you have discovered.<br>");
		helpInfo += ("LANDMARKS: Offers a chance to get money, an item, or possibly sickness.<br>");
		helpInfo += ("======================================================================</html>");
		return helpInfo;
	}
}
	
