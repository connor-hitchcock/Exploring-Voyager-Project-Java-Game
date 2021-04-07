package graphicalInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import allitems.Item;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class ItemMenuScreen {

	private GameManager manager;
	private JFrame frame;

	/**
	 * Create the application.
	 */
	public ItemMenuScreen(GameManager incomingManager) {
		manager = incomingManager;
		initialize();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JLabel crew1InventoryUpdatingLabel = new JLabel(getItemInfo(0));
		crew1InventoryUpdatingLabel.setForeground(Color.WHITE);
		crew1InventoryUpdatingLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		crew1InventoryUpdatingLabel.setBounds(23, 126, 400, 201);
		frame.getContentPane().add(crew1InventoryUpdatingLabel);
		
		JLabel crew2InventoryUpdatingLabel = new JLabel(getItemInfo(1));
		crew2InventoryUpdatingLabel.setForeground(Color.WHITE);
		crew2InventoryUpdatingLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		crew2InventoryUpdatingLabel.setBounds(432, 126, 400, 201);
		frame.getContentPane().add(crew2InventoryUpdatingLabel);
		
		JLabel crew3InventoryUpdatingLabel = new JLabel(getItemInfo(2));
		crew3InventoryUpdatingLabel.setForeground(Color.WHITE);
		crew3InventoryUpdatingLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		crew3InventoryUpdatingLabel.setBounds(23, 385, 400, 200);
		frame.getContentPane().add(crew3InventoryUpdatingLabel);
		
		JLabel crew4InventoryUpdatingLabel = new JLabel(getItemInfo(3));
		crew4InventoryUpdatingLabel.setForeground(Color.WHITE);
		crew4InventoryUpdatingLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		crew4InventoryUpdatingLabel.setBounds(432, 385, 400, 200);
		frame.getContentPane().add(crew4InventoryUpdatingLabel);
		
		JLabel ShipInventoryLabel = new JLabel(manager.getShipName() + "'s Inventory:");
		ShipInventoryLabel.setForeground(Color.WHITE);
		ShipInventoryLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		ShipInventoryLabel.setBounds(842, 79, 388, 41);
		frame.getContentPane().add(ShipInventoryLabel);
		
		JLabel shipInventoryUpdatingLabel = new JLabel(getShipItemInfo());
		shipInventoryUpdatingLabel.setForeground(Color.WHITE);
		shipInventoryUpdatingLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		shipInventoryUpdatingLabel.setBounds(842, 126, 388, 140);
		frame.getContentPane().add(shipInventoryUpdatingLabel);
		
		JButton useItemButton = new JButton("Use Item");
		useItemButton.setForeground(Color.WHITE);
		useItemButton.setBackground(Color.DARK_GRAY);
		useItemButton.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		useItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.launchSelectItemScreen();	
				closeWindow();
				finishedWindow();
			}
		});
		useItemButton.setBounds(23, 596, 388, 84);
		frame.getContentPane().add(useItemButton);
		
		JButton exitButton = new JButton("Exit");
		exitButton.setBackground(Color.DARK_GRAY);
		exitButton.setForeground(Color.WHITE);
		exitButton.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				manager.launchMainShipScreen();	
				closeWindow();
				finishedWindow();
			}
		});
		exitButton.setBounds(432, 596, 400, 84);
		frame.getContentPane().add(exitButton);
		
		JLabel backgroundImage = new JLabel("");
		backgroundImage.setIcon(new ImageIcon(ItemMenuScreen.class.getResource("/Images/startScreenBackground.jpg")));
		backgroundImage.setBounds(0, 0, 1274, 691);
		frame.getContentPane().add(backgroundImage);
	}
	public void closeWindow() {
		frame.dispose();
	}
	public void finishedWindow() {
		manager.closeItemMenuScreen(this);
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
		
		JLabel yourItemsLabel = new JLabel("Your Items:");
		yourItemsLabel.setForeground(Color.WHITE);
		yourItemsLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 50));
		yourItemsLabel.setBounds(474, 27, 270, 41);
		frame.getContentPane().add(yourItemsLabel);
		
		JLabel crew1InventoryLabel = new JLabel(getCrewNameInv(0));
		crew1InventoryLabel.setForeground(Color.WHITE);
		crew1InventoryLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		crew1InventoryLabel.setBounds(23, 79, 400, 41);
		frame.getContentPane().add(crew1InventoryLabel);
		
		JLabel crew2InventoryLabel = new JLabel(getCrewNameInv(1));
		crew2InventoryLabel.setForeground(Color.WHITE);
		crew2InventoryLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		crew2InventoryLabel.setBounds(432, 79, 400, 41);
		frame.getContentPane().add(crew2InventoryLabel);
		
		JLabel crew3InventoryLabel = new JLabel(getCrewNameInv(2));
		crew3InventoryLabel.setForeground(Color.WHITE);
		crew3InventoryLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		crew3InventoryLabel.setBounds(23, 338, 400, 41);
		frame.getContentPane().add(crew3InventoryLabel);
		
		JLabel crew4InventoryLabel = new JLabel(getCrewNameInv(3));
		crew4InventoryLabel.setForeground(Color.WHITE);
		crew4InventoryLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		crew4InventoryLabel.setBounds(432, 338, 400, 41);
		frame.getContentPane().add(crew4InventoryLabel);
	}
	
	public String getCrewNameInv(int index) {
		String crewName = "";
		if(index < manager.getCrewCount()) {
			crewName = manager.getCrewList().get(index).getName() + "'s Inventory";
		}
		return crewName;
	}
	
	public String getItemInfo(int index) {
		String itemInfo = "";
		if(manager.gettingAllItems()[index] != null) {
			Item currentItem = manager.gettingAllItems()[index];
			itemInfo = "<html>" + "Name: " + currentItem.getName() + " | Uses: " + currentItem.getCount() + "<br/>" + currentItem.getDescription() + "</html>";
		}
		return itemInfo;
	}
	
	public String getShipItemInfo() {
		String itemInfo = "";
		for(int i = 4; i <= 12; i++) {
			itemInfo += getItemInfo(i) + "<html><br/></html>";
		}
		return itemInfo;
	}
}
