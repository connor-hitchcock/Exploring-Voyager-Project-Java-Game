package graphicalInterface;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import exceptions.IncorrectCrewNumberException;
import exceptions.InsufficientStaminaException;
import exceptions.MaximumHealthException;
import exceptions.MaximumStaminaException;
import exceptions.ShipMaximumHealthException;
import main.GameEnvironment;
import matter.CrewMember;
import matter.SpaceShip;

import javax.swing.ImageIcon;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Color;
import javax.swing.JButton;

abstract class GenericScreenProcessing {
	
	public void generateStatusText(JFrame frame) {
		JLabel currentHealthLabel = new JLabel("HEALTH");
		currentHealthLabel.setForeground(Color.WHITE);
		currentHealthLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		currentHealthLabel.setBounds(10, 53, 107, 27);
		frame.getContentPane().add(currentHealthLabel);
		
		JLabel currentShieldLabel = new JLabel("SHIELD");
		currentShieldLabel.setForeground(Color.WHITE);
		currentShieldLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		currentShieldLabel.setBounds(10, 20, 73, 27);
		frame.getContentPane().add(currentShieldLabel);
		
		JLabel currentFuelLabel = new JLabel("FUEL");
		currentFuelLabel.setForeground(Color.WHITE);
		currentFuelLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		currentFuelLabel.setBounds(10, 80, 73, 34);
		frame.getContentPane().add(currentFuelLabel);
	}
	
	public void updateStatusBar(String stat, int value, JLabel[] statBar) {
		int barValue = value / 10;
		if (stat == "Fuel") {
			barValue = value / 500;
		}
		for (int i=0; i < barValue; i++ ) {
			statBar[i].setVisible(true);
		}
		for (int i=barValue; i < statBar.length; i++ ) {
			statBar[i].setVisible(false);
		}
	}
	
	public JLabel[] generateStatusBar(String stat, int value, JFrame frame) { //int maxStat, int currentStat
		String colour = "";
		int yModifier = 0;
		int barValue = value / 10;
		
		switch(stat) {
			case "Health":  
				colour = "green";
				yModifier = 30;
				break;
			case "Shields":  
				colour = "blue";
				break;
			case "Fuel":  
				colour = "yellow";
				barValue = value / 500;
				yModifier = 60;
				break;		
		}
		JLabel[] barList = new JLabel[barValue];
		if (barValue != 0) {
			int xIndex = 95;
			for (int i=1; i < barList.length - 1; i++) {
				JLabel middleBar = new JLabel("");
				middleBar.setIcon(new ImageIcon(MainShipScreen.class.getResource("/Images/barHorizontal_" + colour + "_mid.png")));
				middleBar.setBounds(xIndex, yModifier, 16, 71);
				barList[i] = middleBar;
				xIndex += 16;
			}
			JLabel leftBoundBar = new JLabel("");
			leftBoundBar.setIcon(new ImageIcon(MainShipScreen.class.getResource("/Images/barHorizontal_" + colour + "_left.png")));
			leftBoundBar.setBounds(95, yModifier, 6, 71);
			barList[0] = leftBoundBar;
			
			JLabel rightBoundBar = new JLabel("");
			rightBoundBar.setIcon(new ImageIcon(MainShipScreen.class.getResource("/Images/barHorizontal_" + colour + "_right.png")));
			rightBoundBar.setBounds(xIndex, yModifier, 6, 71);
			barList[barValue - 1] = rightBoundBar;
			 
			for (int i=0; i < barList.length; i++) {

				frame.getContentPane().add(barList[i]);
			}
		}
		return barList;
	}
	public JLabel[] generateStaminaLabelArray(ArrayList<CrewMember> crewList, JFrame frame) {
		JLabel[] newStaminaLabelArray = new JLabel[crewList.size()];
		int yPositionModifier = 65;
		for (int i=0; i < newStaminaLabelArray.length; i++) {
			CrewMember currentCrew = crewList.get(i);
			JLabel newStaminaLabel = new JLabel(currentCrew.getCurrentStamina() + "/" + currentCrew.getMaxStamina());
			newStaminaLabel.setForeground(Color.WHITE);
			newStaminaLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
			newStaminaLabel.setBounds(170, yPositionModifier, 100, 50);
			newStaminaLabelArray[i] = newStaminaLabel;
			frame.getContentPane().add(newStaminaLabel);
			yPositionModifier += 30;
	}
		return newStaminaLabelArray;
	}
	public JLabel[] generateBackgroundBarList(String stat, int value, JFrame frame) {
		int barValue = value / 10;
		int yModifier = 0;
		switch(stat) {
			case "Health":  
				yModifier = 30;
				break;
			case "Fuel":  
				barValue = value / 500;
				yModifier = 60;
				break;	
		}
		JLabel[] backgroundBarList = new JLabel[barValue];
		if (barValue != 0) {
			int xIndex = 95;
			for (int i=1; i < backgroundBarList.length - 1; i++) {
					
				JLabel middleBar = new JLabel("");
				middleBar.setIcon(new ImageIcon(MainShipScreen.class.getResource("/Images/barHorizontal_white_mid.png")));
				middleBar.setBounds(xIndex, yModifier, 16, 71);
				backgroundBarList[i] = middleBar;
				xIndex += 16;
			}
			JLabel leftBoundBar = new JLabel("");
			leftBoundBar.setIcon(new ImageIcon(MainShipScreen.class.getResource("/Images/barHorizontal_white_left.png")));
			leftBoundBar.setBounds(95, yModifier, 6, 71);
			backgroundBarList[0] = leftBoundBar;
				
			JLabel rightBoundBar = new JLabel("");
			rightBoundBar.setIcon(new ImageIcon(MainShipScreen.class.getResource("/Images/barHorizontal_white_right.png")));
			rightBoundBar.setBounds(xIndex, yModifier, 6, 71);
			backgroundBarList[barValue - 1] = rightBoundBar;
				
			for (int i=0; i < backgroundBarList.length; i++) {
				frame.getContentPane().add(backgroundBarList[i]);
			}
		}
		return backgroundBarList;
		
	}
	public void generateCoordinates(SpaceShip playerShip, JFrame frame) {
		JLabel coordinateLabel = new JLabel("<html> Coords:<br>(" + playerShip.getCoordinateX() + ", " + playerShip.getCoordinateY() + ")</html>");
		coordinateLabel.setForeground(Color.WHITE);
		coordinateLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 30));
		coordinateLabel.setBounds(1050, 590, 169, 90);
		frame.getContentPane().add(coordinateLabel);
	}
	public void findCandidateForRepair(SpaceShip playerShip) {
		ArrayList<CrewMember> crewList = playerShip.getCrewList();
		JList<CrewMember> selectableCrewList = new JList(crewList.toArray());
		JOptionPane.showMessageDialog(
		  null, selectableCrewList, "Pick a crew member to repair the ship.", JOptionPane.PLAIN_MESSAGE);
		int[] selectedCrewIndices = (selectableCrewList.getSelectedIndices());
		ArrayList<CrewMember> selectedCrewArray = new ArrayList<CrewMember>();
		for (int i=0; i < selectedCrewIndices.length; i++) {
			selectedCrewArray.add(crewList.get(i));
		}
		try {
		if (playerShip.checkValidCrew(1, selectedCrewArray)) {
			try {
			selectedCrewArray.get(0).repair(playerShip);
			} catch(InsufficientStaminaException e) {
				JOptionPane.showMessageDialog(null, e);
			} catch (ShipMaximumHealthException e) { 	
				JOptionPane.showMessageDialog(null, e);
			}
		}
		
		} catch(IncorrectCrewNumberException e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	public JLabel generateTimeLabel(JFrame frame) {
		JLabel timeLabel = new JLabel(GameEnvironment.getHoursTaken() + "/" + GameEnvironment.getTotalHours() + " Hours");
		timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		timeLabel.setBounds(10, 612, 190, 52);
		timeLabel.setForeground(Color.WHITE);
		timeLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 30));
		frame.getContentPane().add(timeLabel);
		return timeLabel;
	}
	public void updateTimeLabel(JLabel timeLabel) {
		timeLabel.setText(GameEnvironment.getHoursTaken() + "/" + GameEnvironment.getTotalHours() + " Hours");
	}
}