package graphicalInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import allitems.Item;
import exceptions.DeadCrewException;
import exceptions.GameOverException;
import exceptions.TakenDamageException;
import matter.Outpost;
import matter.PlanetLocation;
import matter.SpaceShip;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class OutpostScreen extends GenericScreenProcessing {
	private GameManager manager;
	private JFrame frame;

	public OutpostScreen(GameManager incomingManager) {
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
		manager.closeOutpostScreen(this);
	}
	public void finishedWindowToViewItems() {
		manager.closeOutpostScreenToViewItems(this);
	}
	public void finishedWindowToGameOver() {
		manager.closeOutpostScreenToGameOver(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel welcomeLabel = new JLabel("HELLO LIFEFORM. WELCOME TO MY BAZAAR BZZT");
		welcomeLabel.setFont(new Font("Source Code Pro Semibold", Font.PLAIN, 25));
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		JOptionPane.showMessageDialog(null, welcomeLabel);
		
		Outpost currentOutpost = manager.getCurrentOutpost();
		ArrayList<Item> itemList = currentOutpost.getShopInventory();
		JButton itemButton;
		JButton[] itemButtonArray = new JButton[9];
		System.out.println(itemList.size());
		
		
		JLabel currentCurrencyLabel = new JLabel("Current Space Credits: " + manager.getPlayerShip().getCurrency());
		currentCurrencyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		currentCurrencyLabel.setForeground(Color.WHITE);
		currentCurrencyLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		currentCurrencyLabel.setBounds(380, 615, 480, 49);
		frame.getContentPane().add(currentCurrencyLabel);
		
		int xModifier = 330;
		for (int i=0; i < 3; i++) {
			int yModifier = 65;
			for (int j=0; j < 3; j++) {
				int tempI = i * 3;
				int total = tempI + j;
				if ((total) < itemList.size()) {
					if (itemList.get(total) != null) {
						itemButton = new JButton(itemList.get(total).getName());
					} else {
						itemButton = new JButton("<html>SOLD TO A<br> FINE SPECIMEN</html>");
						itemButton.setEnabled(false);
					}
					itemButton.setBounds(xModifier, yModifier, 208, 160);
					itemButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							long itemPrice = currentOutpost.calculateFinalItemPrice(manager.getPlayerShip(), itemList.get(total));
							JLabel infoLabel = new JLabel("<html>" + itemList.get(total).getName() +
									"<br>" + itemList.get(total).getDescription() + "<br>Price: " 
									+ itemPrice + "</html>");
							infoLabel.setFont(new Font("Source Code Pro Semibold", Font.PLAIN, 25));
							infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
							int answer = JOptionPane.showConfirmDialog(null, infoLabel, "Purchase?", JOptionPane.YES_NO_OPTION);
							JLabel receiptLabel = null;
						    if (answer == JOptionPane.YES_OPTION) {
				               String finalMessage = currentOutpost.completeTransaction(manager.getPlayerShip(), itemList.get(total), total, itemPrice);
				               if (finalMessage.equals("N0-MUNS") ) {
				            	   receiptLabel = new JLabel("ERROR " + finalMessage + " - NOT ENOUGH CURRENCY BZZZZT.");
				               } else if (finalMessage.equals("N0-MUNS")) {
				            	   receiptLabel = new JLabel("ERROR " + finalMessage + " - NO AVAILABLE SPACE TO PLACE ITEM BZZT. PLEASE MAKE SPACE.");
				               } else {
				            	   receiptLabel = new JLabel(finalMessage);
				            	   disableButton(total, itemButtonArray);
				            	   currentCurrencyLabel.setText("Current Space Credits: " + manager.getPlayerShip().getCurrency());
				               }
				               
						    } else {
						    	receiptLabel = new JLabel("TRANSACTION CANCELLED.");
						    }
						    receiptLabel.setFont(new Font("Source Code Pro Semibold", Font.PLAIN, 25));
				              JOptionPane.showMessageDialog(null, receiptLabel);
						}
					});
					itemButton.setFont(new Font("Eras Medium ITC", Font.PLAIN, 25));
				} else {
					itemButton = new JButton("");
					itemButton.setBounds(xModifier, yModifier, 208, 160);
					itemButton.setFont(new Font("Eras Medium ITC", Font.PLAIN, 25));
					itemButton.setEnabled(false);
				}
				itemButton.setBackground(Color.DARK_GRAY);
				itemButton.setForeground(Color.WHITE);
				itemButtonArray[tempI + j] = itemButton; 
				frame.getContentPane().add(itemButton);
				yModifier += 170;
				
			}
			xModifier += 220;
		}
		JLabel outpostNameLabel = new JLabel(currentOutpost.getName());
		outpostNameLabel.setForeground(Color.WHITE);
		outpostNameLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		outpostNameLabel.setBounds(10, 11, 500, 65);
		frame.getContentPane().add(outpostNameLabel);
		
		JLabel buyItemsLabel = new JLabel("Buy Items:");
		buyItemsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		buyItemsLabel.setForeground(Color.WHITE);
		buyItemsLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		buyItemsLabel.setBounds(579, 11, 169, 49);
		frame.getContentPane().add(buyItemsLabel);
		
		JButton itemsMenuButton = new JButton("View Items");
		itemsMenuButton.setBackground(Color.DARK_GRAY);
		itemsMenuButton.setForeground(Color.WHITE);
		itemsMenuButton.setFont(new Font("Eras Medium ITC", Font.PLAIN, 27));
		itemsMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
				finishedWindowToViewItems();
			}
		});
		itemsMenuButton.setBounds(1013, 21, 223, 205);
		frame.getContentPane().add(itemsMenuButton);
		
		JButton repairShipButton = new JButton("Repair Ship");
		repairShipButton.setForeground(Color.WHITE);
		repairShipButton.setFont(new Font("Eras Medium ITC", Font.PLAIN, 27));
		repairShipButton.setBackground(Color.DARK_GRAY);
		repairShipButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				findCandidateForRepair(manager.getPlayerShip());
				} catch (GameOverException message) {
					manager.setDeathMessage("Game Over - Time to become a mechanic?");
					closeWindow();
					finishedWindowToGameOver();
				} catch(DeadCrewException message) {
					JOptionPane.showMessageDialog(null, message.toString());
				} catch(TakenDamageException message) {
						JOptionPane.showMessageDialog(null, message.toString());
			} finally {
				closeWindow();
				finishedWindow();
			}
			}
		});
		repairShipButton.setBounds(1013, 249, 223, 194);
		frame.getContentPane().add(repairShipButton);
		
		JButton leaveButton = new JButton("Leave Outpost");
		leaveButton.setFont(new Font("Eras Medium ITC", Font.PLAIN, 27));
		leaveButton.setForeground(Color.WHITE);
		leaveButton.setBackground(Color.DARK_GRAY);
		leaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
				finishedWindow();
			}
		});
		leaveButton.setBounds(1013, 465, 223, 205);
		frame.getContentPane().add(leaveButton);
		
		JLabel backgroundImage = new JLabel("");
		backgroundImage.setIcon(new ImageIcon(OutpostScreen.class.getResource("/Images/startScreenBackground.jpg")));
		backgroundImage.setBounds(0, 0, 1264, 681);
		frame.getContentPane().add(backgroundImage);
	}
	private void disableButton(int total, JButton[] itemButtonArray) {
		itemButtonArray[total].setEnabled(false);
	}
}
