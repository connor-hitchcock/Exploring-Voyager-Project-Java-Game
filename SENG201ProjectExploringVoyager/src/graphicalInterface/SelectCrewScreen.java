package graphicalInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import exceptions.IncorrectCrewNumberException;
import exceptions.TooManyCharactersException;
import matter.CrewMember;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SelectCrewScreen {

	private JFrame frame;
	private JTextField textField;
	private GameManager manager;
	
	// Initializing objecting
	private JButton addToCrewButton = new JButton("Add To Crew");
	private JLabel[] updatingLabels = generateUpdateLabels();
	
	// increase/decrease stat buttons
	private JButton negativeMaxHealthButton = new JButton("-");
	private JButton positiveMaxHealthButton = new JButton("+");
	private JButton negativeMaxStaminaButton = new JButton("-");
	private JButton positiveMaxStaminaButton = new JButton("+");
	private JButton negativeRepairLevelButton = new JButton("-");
	private JButton positiveRepairLevelButton = new JButton("+");
	private JButton negativeCharismaLevelButton = new JButton("-");
	private JButton positiveCharismaLevelButton = new JButton("+");
	private JButton positiveScavengingLevelButton = new JButton("+");
	private JButton negativeScavengingLevelButton = new JButton("-");
	private JButton negativeHealerLevelButton = new JButton("-");
	private JButton positiveHealerLevelButton = new JButton("+");
	

	public SelectCrewScreen(GameManager incomingManager) {
		manager = incomingManager;
		manager.setSelectableCrewList();
		manager.setUnchangableSelectableCrewList();
		initialize();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	public void finishedWindow() {
		JLabel startText = new JLabel("<html>Your crew's ship was attacked by the Pizza Cutter Pirates, and your parts of your warp drive was torn into multiple pieces... "
				+ "<br><b>Pizza King</b>: HARR HARR HARR, SMELL YOU LATER YOU ANCHOVIES! "
				+ "<br>Traverse over the galaxy and recover the scraps of your ship to return home. "
				+ "<br>Your selected crew are capable pilots that are flexible and can adapt to any situation. "
				+ " <br>Explore. Adapt. Overcome.");
		startText.setFont(new Font("Tahoma", Font.PLAIN, 25));
		JOptionPane.showMessageDialog(null, startText);
		manager.closeSelectCrewScreen(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel SelectCrewLabel = new JLabel("Select Your Crew Members");
		SelectCrewLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 50));
		SelectCrewLabel.setForeground(Color.WHITE);
		SelectCrewLabel.setBounds(334, 0, 613, 67);
		frame.getContentPane().add(SelectCrewLabel);
		
		for (JLabel label: updatingLabels) {
			frame.getContentPane().add(label);
		}
		
		JLabel StatsLabel = new JLabel("Stats:");
		StatsLabel.setForeground(Color.WHITE);
		StatsLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		StatsLabel.setBounds(20, 239, 144, 41);
		frame.getContentPane().add(StatsLabel);
		
		JLabel UnallocatedPointsLabel = new JLabel("Unallocated Stat Points:");
		UnallocatedPointsLabel.setForeground(Color.WHITE);
		UnallocatedPointsLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		UnallocatedPointsLabel.setBounds(410, 239, 373, 41);
		frame.getContentPane().add(UnallocatedPointsLabel);
		
		//create label list +50 ymod
		JLabel[] statLabels = createNameList();
		
		JLabel nameCrewLabel = new JLabel("First Crew Member's Name:");
		nameCrewLabel.setForeground(Color.WHITE);
		nameCrewLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		nameCrewLabel.setBounds(20, 629, 498, 41);
		frame.getContentPane().add(nameCrewLabel);
		
		JButton crewSelect1ButtonImage = new JButton("");
		crewSelect1ButtonImage.setIcon(new ImageIcon(SelectCrewScreen.class.getResource("/Images/astronaut_160px.png")));
		crewSelect1ButtonImage.setBackground(Color.DARK_GRAY);
		crewSelect1ButtonImage.setBounds(33, 68, 160, 160);
		frame.getContentPane().add(crewSelect1ButtonImage);
		crewSelect1ButtonImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.setCurrentCrewIndex(0);
				CrewMember currentCrew = manager.getSelectableCrewList().get(0);
				manager.setSelectCrewIndex(0);
				updateStatLabel(currentCrew, updatingLabels); //not adding to pane
				if(manager.getCrewCount() < 4) {
					addToCrewButton.setEnabled(true);
				}
			}
		});
		
		JButton crewSelect2ButtonImage = new JButton("");
		crewSelect2ButtonImage.setIcon(new ImageIcon(SelectCrewScreen.class.getResource("/Images/astronaut_160px.png")));
		crewSelect2ButtonImage.setBackground(Color.DARK_GRAY);
		crewSelect2ButtonImage.setBounds(235, 68, 160, 160);
		frame.getContentPane().add(crewSelect2ButtonImage);
		crewSelect2ButtonImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.setCurrentCrewIndex(1);
				CrewMember currentCrew = manager.getSelectableCrewList().get(1);
				manager.setSelectCrewIndex(1);
			    updateStatLabel(currentCrew, updatingLabels); //not adding to pane
			    if(manager.getCrewCount() < 4) {
					addToCrewButton.setEnabled(true);
				}
			}
		});
		
		JButton crewSelect3ButtonImage = new JButton("");
		crewSelect3ButtonImage.setIcon(new ImageIcon(SelectCrewScreen.class.getResource("/Images/astronaut_160px.png")));
		crewSelect3ButtonImage.setBackground(Color.DARK_GRAY);
		crewSelect3ButtonImage.setBounds(443, 68, 160, 160);
		frame.getContentPane().add(crewSelect3ButtonImage);
		crewSelect3ButtonImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.setCurrentCrewIndex(2);
				CrewMember currentCrew = manager.getSelectableCrewList().get(2);
				manager.setSelectCrewIndex(2);
			    updateStatLabel(currentCrew, updatingLabels); //not adding to pane
			    if(manager.getCrewCount() < 4) {
					addToCrewButton.setEnabled(true);
				}
			}
		});
		
		JButton crewSelect4ButtonImage = new JButton("");
		crewSelect4ButtonImage.setIcon(new ImageIcon(SelectCrewScreen.class.getResource("/Images/astronaut_160px.png")));
		crewSelect4ButtonImage.setBackground(Color.DARK_GRAY);
		crewSelect4ButtonImage.setBounds(654, 68, 160, 160);
		frame.getContentPane().add(crewSelect4ButtonImage);
		crewSelect4ButtonImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.setCurrentCrewIndex(3);
				CrewMember currentCrew = manager.getSelectableCrewList().get(3);
				manager.setSelectCrewIndex(3);
			    updateStatLabel(currentCrew, updatingLabels); //not adding to pane
			    if(manager.getCrewCount() < 4) {
					addToCrewButton.setEnabled(true);
				}
			}
		});
		
		JButton crewSelect5ButtonImage = new JButton("");
		crewSelect5ButtonImage.setIcon(new ImageIcon(SelectCrewScreen.class.getResource("/Images/astronaut_160px.png")));
		crewSelect5ButtonImage.setBackground(Color.DARK_GRAY);
		crewSelect5ButtonImage.setBounds(869, 68, 160, 160);
		frame.getContentPane().add(crewSelect5ButtonImage);
		crewSelect5ButtonImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.setCurrentCrewIndex(4);
				CrewMember currentCrew = manager.getSelectableCrewList().get(4);
				manager.setSelectCrewIndex(4);
			    updateStatLabel(currentCrew, updatingLabels); //not adding to pane
			    if(manager.getCrewCount() < 4) {
					addToCrewButton.setEnabled(true);
				}
			}
		});
		
		JButton crewSelect6ButtonImage = new JButton("");
		crewSelect6ButtonImage.setIcon(new ImageIcon(SelectCrewScreen.class.getResource("/Images/astronaut_160px.png")));
		crewSelect6ButtonImage.setBackground(Color.DARK_GRAY);
		crewSelect6ButtonImage.setBounds(1074, 68, 160, 160);
		frame.getContentPane().add(crewSelect6ButtonImage);
		crewSelect6ButtonImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.setCurrentCrewIndex(5);
				CrewMember currentCrew = manager.getSelectableCrewList().get(5);
				manager.setSelectCrewIndex(5);
			    updateStatLabel(currentCrew, updatingLabels); //not adding to pane
			    if(manager.getCrewCount() < 4) {
					addToCrewButton.setEnabled(true);
				}
			}
		});
		
		
		JTextField setNameInputTextField = new JTextField();
		setNameInputTextField.setBackground(Color.DARK_GRAY);
		setNameInputTextField.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		setNameInputTextField.setBounds(533, 629, 325, 41);
		frame.getContentPane().add(setNameInputTextField);
		setNameInputTextField.setColumns(10);
		
		JButton confirmButton = new JButton("Confirm");
		confirmButton.setBackground(Color.DARK_GRAY);
		confirmButton.setForeground(Color.WHITE);
		confirmButton.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
				finishedWindow();
				
			}
		});
		confirmButton.setEnabled(false);
		confirmButton.setBounds(901, 562, 333, 108);
		frame.getContentPane().add(confirmButton);
		addToCrewButton.setBackground(Color.DARK_GRAY);
		addToCrewButton.setForeground(Color.WHITE);
		addToCrewButton.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		
		
		addToCrewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addToCrewButton.setEnabled(false);
				if(manager.getCrewCount() < 4) {
					int currentCrewIndex = manager.getCurrentCrewIndex();
					try {
					manager.confirmAddCrewMember(setNameInputTextField.getText());
					setNameInputTextField.setText("");
					
					// To hide already selected crew member types
					switch(currentCrewIndex) {
						case 0: crewSelect1ButtonImage.setEnabled(false);
								break;
						case 1: crewSelect2ButtonImage.setEnabled(false);
								break;
						case 2: crewSelect3ButtonImage.setEnabled(false);
								break;
						case 3: crewSelect4ButtonImage.setEnabled(false);
								break;
						case 4: crewSelect5ButtonImage.setEnabled(false);
								break;
						case 5: crewSelect6ButtonImage.setEnabled(false);
								break;
					}
					

					switch(manager.getCrewCount()) {
						case 0: nameCrewLabel.setText("First Crew Member's Name:");
								break;
						case 1: nameCrewLabel.setText("Second Crew Member's Name:");
								break;
						case 2: nameCrewLabel.setText("Third Crew Member's Name:");
								break;
						case 3: nameCrewLabel.setText("Fourth Crew Member's Name:");
								break;
						case 4: nameCrewLabel.setText("Selected All Crew Members");
								break;
					}
					} catch(TooManyCharactersException message) {
						JOptionPane.showMessageDialog(null, message);
					}
				} 
				if (manager.getCrewCount()>= 2) {
					confirmButton.setEnabled(true);
				}
			}
		});
		addToCrewButton.setBounds(901, 427, 333, 108);
		frame.getContentPane().add(addToCrewButton);
		
		negativeMaxHealthButton.setForeground(Color.WHITE);
		
		
		negativeMaxHealthButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(manager.allocateStatPoints("health", false)) {
					positiveMaxHealthButton.setEnabled(true);
				} else {
					negativeMaxHealthButton.setEnabled(true);
				}
				updateStatLabel(manager.getSelectableCrewList().get(manager.getSelectCrewIndex()), updatingLabels);
			}
		});
		negativeMaxHealthButton.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		negativeMaxHealthButton.setBackground(Color.DARK_GRAY);
		negativeMaxHealthButton.setBounds(533, 291, 153, 41);
		frame.getContentPane().add(negativeMaxHealthButton);
		positiveMaxHealthButton.setForeground(Color.WHITE);
		
		
		positiveMaxHealthButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(manager.allocateStatPoints("health", true)) {
					negativeMaxHealthButton.setEnabled(true);
				} else {
					positiveMaxHealthButton.setEnabled(true);
				}
				updateStatLabel(manager.getSelectableCrewList().get(manager.getSelectCrewIndex()), updatingLabels);
			}
		});
		positiveMaxHealthButton.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		positiveMaxHealthButton.setBackground(Color.DARK_GRAY);
		positiveMaxHealthButton.setBounds(705, 291, 153, 41);
		frame.getContentPane().add(positiveMaxHealthButton);
		negativeMaxStaminaButton.setForeground(Color.WHITE);
		
		negativeMaxStaminaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(manager.allocateStatPoints("stamina", false)) {
					positiveMaxStaminaButton.setEnabled(true);
				} else {
					negativeMaxStaminaButton.setEnabled(true);
				}
				updateStatLabel(manager.getSelectableCrewList().get(manager.getSelectCrewIndex()), updatingLabels);
			}
		});
		negativeMaxStaminaButton.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		negativeMaxStaminaButton.setBackground(Color.DARK_GRAY);
		negativeMaxStaminaButton.setBounds(533, 343, 153, 41);
		frame.getContentPane().add(negativeMaxStaminaButton);
		positiveMaxStaminaButton.setForeground(Color.WHITE);
		
		positiveMaxStaminaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(manager.allocateStatPoints("stamina", true)) {
					negativeMaxStaminaButton.setEnabled(true);
				} else {
					positiveMaxStaminaButton.setEnabled(true);
				}
				updateStatLabel(manager.getSelectableCrewList().get(manager.getSelectCrewIndex()), updatingLabels);
			}
		});
		positiveMaxStaminaButton.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		positiveMaxStaminaButton.setBackground(Color.DARK_GRAY);
		positiveMaxStaminaButton.setBounds(705, 343, 153, 41);
		frame.getContentPane().add(positiveMaxStaminaButton);
		negativeRepairLevelButton.setForeground(Color.WHITE);
		
		negativeRepairLevelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(manager.allocateStatPoints("repair", false)) {
					positiveRepairLevelButton.setEnabled(true);
				} else {
					negativeRepairLevelButton.setEnabled(true);
				}
				updateStatLabel(manager.getSelectableCrewList().get(manager.getSelectCrewIndex()), updatingLabels);
			}
		});
		negativeRepairLevelButton.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		negativeRepairLevelButton.setBackground(Color.DARK_GRAY);
		negativeRepairLevelButton.setBounds(533, 551, 153, 41);
		frame.getContentPane().add(negativeRepairLevelButton);
		positiveRepairLevelButton.setForeground(Color.WHITE);
		
		positiveRepairLevelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(manager.allocateStatPoints("repair", true)) {
					negativeRepairLevelButton.setEnabled(true);
				} else {
					positiveRepairLevelButton.setEnabled(true);
				}
				updateStatLabel(manager.getSelectableCrewList().get(manager.getSelectCrewIndex()), updatingLabels);
			}
		});
		positiveRepairLevelButton.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		positiveRepairLevelButton.setBackground(Color.DARK_GRAY);
		positiveRepairLevelButton.setBounds(705, 551, 153, 41);
		frame.getContentPane().add(positiveRepairLevelButton);
		negativeCharismaLevelButton.setForeground(Color.WHITE);
		
		negativeCharismaLevelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(manager.allocateStatPoints("charisma", false)) {
					positiveCharismaLevelButton.setEnabled(true);
				} else {
					negativeCharismaLevelButton.setEnabled(true);
				}
				updateStatLabel(manager.getSelectableCrewList().get(manager.getSelectCrewIndex()), updatingLabels);
			}
		});
		negativeCharismaLevelButton.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		negativeCharismaLevelButton.setBackground(Color.DARK_GRAY);
		negativeCharismaLevelButton.setBounds(533, 447, 153, 41);
		frame.getContentPane().add(negativeCharismaLevelButton);
		positiveCharismaLevelButton.setForeground(Color.WHITE);
		
		positiveCharismaLevelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(manager.allocateStatPoints("charisma", true)) {
					negativeCharismaLevelButton.setEnabled(true);
				} else {
					positiveCharismaLevelButton.setEnabled(true);
				}
				updateStatLabel(manager.getSelectableCrewList().get(manager.getSelectCrewIndex()), updatingLabels);
			}
		});
		positiveCharismaLevelButton.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		positiveCharismaLevelButton.setBackground(Color.DARK_GRAY);
		positiveCharismaLevelButton.setBounds(705, 447, 153, 41);
		frame.getContentPane().add(positiveCharismaLevelButton);
		negativeScavengingLevelButton.setForeground(Color.WHITE);
		
		negativeScavengingLevelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(manager.allocateStatPoints("scavenging", false)) {
					positiveScavengingLevelButton.setEnabled(true);
				} else {
					negativeScavengingLevelButton.setEnabled(true);
				}
				updateStatLabel(manager.getSelectableCrewList().get(manager.getSelectCrewIndex()), updatingLabels);
			}
		});
		negativeScavengingLevelButton.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		negativeScavengingLevelButton.setBackground(Color.DARK_GRAY);
		negativeScavengingLevelButton.setBounds(533, 395, 153, 41);
		frame.getContentPane().add(negativeScavengingLevelButton);
		positiveScavengingLevelButton.setForeground(Color.WHITE);
		
		positiveScavengingLevelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(manager.allocateStatPoints("scavenging", true)) {
					negativeScavengingLevelButton.setEnabled(true);
				} else {
					positiveScavengingLevelButton.setEnabled(true);
				}
				updateStatLabel(manager.getSelectableCrewList().get(manager.getSelectCrewIndex()), updatingLabels);
			}
		});
		positiveScavengingLevelButton.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		positiveScavengingLevelButton.setBackground(Color.DARK_GRAY);
		positiveScavengingLevelButton.setBounds(705, 395, 153, 41);
		frame.getContentPane().add(positiveScavengingLevelButton);
		negativeHealerLevelButton.setForeground(Color.WHITE);
		
		negativeHealerLevelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(manager.allocateStatPoints("healer", false)) {
					positiveHealerLevelButton.setEnabled(true);
				} else {
					negativeHealerLevelButton.setEnabled(true);
				}
				updateStatLabel(manager.getSelectableCrewList().get(manager.getSelectCrewIndex()), updatingLabels);
			}
		});
		negativeHealerLevelButton.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		negativeHealerLevelButton.setBackground(Color.DARK_GRAY);
		negativeHealerLevelButton.setBounds(533, 499, 153, 41);
		frame.getContentPane().add(negativeHealerLevelButton);
		positiveHealerLevelButton.setForeground(Color.WHITE);
		
		positiveHealerLevelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(manager.allocateStatPoints("healer", true)) {
					negativeHealerLevelButton.setEnabled(true);
				} else {
					positiveHealerLevelButton.setEnabled(true);
				}
				updateStatLabel(manager.getSelectableCrewList().get(manager.getSelectCrewIndex()), updatingLabels);
			}
		});
		positiveHealerLevelButton.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		positiveHealerLevelButton.setBackground(Color.DARK_GRAY);
		positiveHealerLevelButton.setBounds(705, 499, 153, 41);
		frame.getContentPane().add(positiveHealerLevelButton);
		
		JLabel backgroundImage = new JLabel("");
		backgroundImage.setIcon(new ImageIcon(SelectCrewScreen.class.getResource("/Images/startScreenBackground.jpg")));
		backgroundImage.setBounds(0, 0, 1264, 681);
		frame.getContentPane().add(backgroundImage);	
		
	}
	
	
	
	public JLabel[] generateUpdateLabels() {
		int yModifier = 290;
		JLabel[] updatingtempLabelArray = new JLabel[7];
		for (int i=0; i < updatingtempLabelArray.length; i++) {
			JLabel updatingLabel = new JLabel("-");
			updatingLabel.setForeground(Color.WHITE);
			updatingLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
			if (i < 2) {
				updatingLabel.setBounds(370, yModifier, 500, 41);
			} else {
				updatingLabel.setBounds(410, yModifier, 47, 41);
			}
			updatingtempLabelArray[i] = updatingLabel;
			yModifier += 50;
		}
		JLabel UnallocatedPointsUpdatingLabel = new JLabel("-");
		UnallocatedPointsUpdatingLabel.setForeground(Color.WHITE);
		UnallocatedPointsUpdatingLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		UnallocatedPointsUpdatingLabel.setBounds(811, 239, 47, 41);
		updatingtempLabelArray[6] = UnallocatedPointsUpdatingLabel;
		return updatingtempLabelArray;
	}
	
	public JLabel[] createNameList() {
		String[] textArray = {"Maximum Health", "Maximum Stamina", "Scavenging Level", "Charisma Level", "Healer Level", "Repair Level"};
		JLabel[] tempLabelArray = new JLabel[6];
		int yModifier = 290;
		for (int i=0; i < textArray.length; i++) {
			JLabel nameLabel = new JLabel(textArray[i] + ":");
			nameLabel.setForeground(Color.WHITE);
			nameLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
			nameLabel.setBounds(20, yModifier, 365, 41);
			frame.getContentPane().add(nameLabel);
			yModifier += 50;
		}	
		return tempLabelArray;
	}
	
	public void updateStatLabel(CrewMember currentCrew, JLabel[] updatingLabels) {
		updatingLabels[0].setText(Integer.toString(currentCrew.getMaxHealth()));
		updatingLabels[1].setText(Integer.toString(currentCrew.getMaxStamina()));
		updatingLabels[2].setText(Integer.toString(currentCrew.getScavengingLevel()));
		updatingLabels[3].setText(Integer.toString(currentCrew.getCharismaLevel()));
		updatingLabels[4].setText(Integer.toString(currentCrew.getHealerLevel()));
		updatingLabels[5].setText(Integer.toString(currentCrew.getRepairLevel()));
		updatingLabels[6].setText(Integer.toString(currentCrew.getUnallocatedStatPoints()));
	}
}
