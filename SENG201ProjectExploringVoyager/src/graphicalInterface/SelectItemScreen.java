package graphicalInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JRadioButton;

import allitems.Item;
import allitems.MedicalItem;
import exceptions.MaximumFuelException;
import exceptions.MaximumHealthException;
import exceptions.MaximumStaminaException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SelectItemScreen {
	
	private GameManager manager;
	private JFrame frame;
	
	private JButton useCrew1Button = new JButton("");
	private JButton useCrew2Button = new JButton("");
	private JButton useCrew3Button = new JButton("");
	private JButton useCrew4Button = new JButton("");
	private JButton onCrew1Button = new JButton("");
	private JButton onCrew2Button = new JButton("");
	private JButton onCrew3Button = new JButton("");
	private JButton onCrew4Button = new JButton("");
	private JButton item1SelectButton = new JButton("");
	private JButton item2SelectButton = new JButton("");
	private JButton item3SelectButton = new JButton("");
	private JButton item4SelectButton = new JButton("");
	private JButton item5SelectButton = new JButton("");
	private JButton item6SelectButton = new JButton("");
	private JButton item7SelectButton = new JButton("");
	private JButton item8SelectButton = new JButton("");
	private JButton item9SelectButton = new JButton("");
	private JButton item10SelectButton = new JButton("");
	private JButton item11SelectButton = new JButton("");
	private JButton item12SelectButton = new JButton("");
	private JButton item13SelectButton = new JButton("");
	private JButton confirmButton = new JButton("Confirm");
	

	public SelectItemScreen(GameManager incomingManager) {
		manager = incomingManager;
		initialize();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		item1SelectButton.setForeground(Color.WHITE);
		item1SelectButton.setBackground(Color.DARK_GRAY);
		
		item1SelectButton.setText(getItemsName(0));
		item2SelectButton.setForeground(Color.WHITE);
		item2SelectButton.setBackground(Color.DARK_GRAY);
		item2SelectButton.setText(getItemsName(1));
		item3SelectButton.setForeground(Color.WHITE);
		item3SelectButton.setBackground(Color.DARK_GRAY);
		item3SelectButton.setText(getItemsName(2));
		item4SelectButton.setForeground(Color.WHITE);
		item4SelectButton.setBackground(Color.DARK_GRAY);
		item4SelectButton.setText(getItemsName(3));
		item5SelectButton.setForeground(Color.WHITE);
		item5SelectButton.setBackground(Color.DARK_GRAY);
		item5SelectButton.setText(getItemsName(4));
		item6SelectButton.setForeground(Color.WHITE);
		item6SelectButton.setBackground(Color.DARK_GRAY);
		item6SelectButton.setText(getItemsName(5));
		item7SelectButton.setForeground(Color.WHITE);
		item7SelectButton.setBackground(Color.DARK_GRAY);
		item7SelectButton.setText(getItemsName(6));
		item8SelectButton.setForeground(Color.WHITE);
		item8SelectButton.setBackground(Color.DARK_GRAY);
		item8SelectButton.setText(getItemsName(7));
		item9SelectButton.setForeground(Color.WHITE);
		item9SelectButton.setBackground(Color.DARK_GRAY);
		item9SelectButton.setText(getItemsName(8));
		item10SelectButton.setForeground(Color.WHITE);
		item10SelectButton.setBackground(Color.DARK_GRAY);
		item10SelectButton.setText(getItemsName(9));
		item11SelectButton.setForeground(Color.WHITE);
		item11SelectButton.setBackground(Color.DARK_GRAY);
		item11SelectButton.setText(getItemsName(10));
		item12SelectButton.setForeground(Color.WHITE);
		item12SelectButton.setBackground(Color.DARK_GRAY);
		item12SelectButton.setText(getItemsName(11));
		item13SelectButton.setForeground(Color.WHITE);
		item13SelectButton.setBackground(Color.DARK_GRAY);
		item13SelectButton.setText(getItemsName(12));
		
		useCrew1Button.setIcon(new ImageIcon(SelectItemScreen.class.getResource("/Images/astronaut_217px.png")));
		
		JLabel onCrewUpdatingLabel = new JLabel("None");
		JLabel useCrewUpdatingLabel = new JLabel("None");
		
		onCrewUpdatingLabel.setForeground(Color.WHITE);
		onCrewUpdatingLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 25));
		onCrewUpdatingLabel.setBounds(1042, 11, 217, 47);
		frame.getContentPane().add(onCrewUpdatingLabel);
		useCrewUpdatingLabel.setForeground(Color.WHITE);
		useCrewUpdatingLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 25));
		useCrewUpdatingLabel.setBounds(247, 11, 217, 47);
		frame.getContentPane().add(useCrewUpdatingLabel);
		
		useCrew1Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.setUsedCrewIndex(0);
				useCrewUpdatingLabel.setText(manager.getCrewList().get(0).getName());
				crewButtonEnabler(1);
			}
		});
		useCrew1Button.setForeground(Color.WHITE);
		useCrew1Button.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		useCrew1Button.setBackground(Color.DARK_GRAY);
		useCrew1Button.setBounds(20, 57, 217, 217);
		frame.getContentPane().add(useCrew1Button);
		
		JLabel useCrew1UpdatingLabel = new JLabel(getCrewName(0));
		useCrew1UpdatingLabel.setForeground(Color.WHITE);
		useCrew1UpdatingLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 30));
		useCrew1UpdatingLabel.setBounds(20, 278, 217, 41);
		frame.getContentPane().add(useCrew1UpdatingLabel);
		useCrew2Button.setIcon(new ImageIcon(SelectItemScreen.class.getResource("/Images/astronaut_217px.png")));
		
		
		useCrew2Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.setUsedCrewIndex(1);
				useCrewUpdatingLabel.setText(manager.getCrewList().get(1).getName());
				crewButtonEnabler(2);
			}
		});
		useCrew2Button.setForeground(Color.WHITE);
		useCrew2Button.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		useCrew2Button.setBackground(Color.DARK_GRAY);
		useCrew2Button.setBounds(249, 57, 217, 217);
		frame.getContentPane().add(useCrew2Button);
		
		JLabel useCrew2UpdatingLabel = new JLabel(getCrewName(1));
		useCrew2UpdatingLabel.setForeground(Color.WHITE);
		useCrew2UpdatingLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 30));
		useCrew2UpdatingLabel.setBounds(248, 278, 217, 41);
		frame.getContentPane().add(useCrew2UpdatingLabel);
		useCrew3Button.setIcon(new ImageIcon(SelectItemScreen.class.getResource("/Images/astronaut_217px.png")));
		
		
		useCrew3Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.setUsedCrewIndex(2);
				useCrewUpdatingLabel.setText(manager.getCrewList().get(2).getName());
				crewButtonEnabler(3);
			}
		});
		useCrew3Button.setForeground(Color.WHITE);
		useCrew3Button.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		useCrew3Button.setBackground(Color.DARK_GRAY);
		useCrew3Button.setBounds(20, 319, 217, 217);
		frame.getContentPane().add(useCrew3Button);
		
		JLabel useCrew3UpdatingLabel = new JLabel(getCrewName(2));
		useCrew3UpdatingLabel.setForeground(Color.WHITE);
		useCrew3UpdatingLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 30));
		useCrew3UpdatingLabel.setBounds(20, 540, 217, 41);
		frame.getContentPane().add(useCrew3UpdatingLabel);
		useCrew4Button.setIcon(new ImageIcon(SelectItemScreen.class.getResource("/Images/astronaut_217px.png")));
		
		
		useCrew4Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.setUsedCrewIndex(3);
				useCrewUpdatingLabel.setText(manager.getCrewList().get(3).getName());
				crewButtonEnabler(4);
			}
		});
		useCrew4Button.setForeground(Color.WHITE);
		useCrew4Button.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		useCrew4Button.setBackground(Color.DARK_GRAY);
		useCrew4Button.setBounds(249, 319, 217, 217);
		frame.getContentPane().add(useCrew4Button);
		
		JLabel useCrew4UpdatingLabel = new JLabel(getCrewName(3));
		useCrew4UpdatingLabel.setForeground(Color.WHITE);
		useCrew4UpdatingLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 30));
		useCrew4UpdatingLabel.setBounds(248, 540, 217, 41);
		frame.getContentPane().add(useCrew4UpdatingLabel);
		
		JLabel dividerImage = new JLabel("");
		dividerImage.setIcon(new ImageIcon(SelectItemScreen.class.getResource("/Images/divider.png")));
		dividerImage.setBounds(482, 0, 10, 691);
		frame.getContentPane().add(dividerImage);
		onCrew1Button.setIcon(new ImageIcon(SelectItemScreen.class.getResource("/Images/astronaut_217px.png")));
		
		
		onCrew1Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.setOnCrewIndex(0);
				onCrewUpdatingLabel.setText(manager.getCrewList().get(0).getName());
				crewButtonEnabler(1);
			}
		});
		onCrew1Button.setForeground(Color.WHITE);
		onCrew1Button.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		onCrew1Button.setBackground(Color.DARK_GRAY);
		onCrew1Button.setBounds(815, 59, 217, 217);
		frame.getContentPane().add(onCrew1Button);
		
		JLabel onCrew1UpdatingLabel = new JLabel(getCrewName(0));
		onCrew1UpdatingLabel.setForeground(Color.WHITE);
		onCrew1UpdatingLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 30));
		onCrew1UpdatingLabel.setBounds(815, 280, 217, 41);
		frame.getContentPane().add(onCrew1UpdatingLabel);
		onCrew2Button.setIcon(new ImageIcon(SelectItemScreen.class.getResource("/Images/astronaut_217px.png")));
		
		
		onCrew2Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.setOnCrewIndex(1);
				onCrewUpdatingLabel.setText(manager.getCrewList().get(1).getName());
				crewButtonEnabler(2);
			}
		});
		onCrew2Button.setForeground(Color.WHITE);
		onCrew2Button.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		onCrew2Button.setBackground(Color.DARK_GRAY);
		onCrew2Button.setBounds(1044, 59, 217, 217);
		frame.getContentPane().add(onCrew2Button);
		
		JLabel onCrew2UpdatingLabel = new JLabel(getCrewName(1));
		onCrew2UpdatingLabel.setForeground(Color.WHITE);
		onCrew2UpdatingLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 30));
		onCrew2UpdatingLabel.setBounds(1043, 280, 217, 41);
		frame.getContentPane().add(onCrew2UpdatingLabel);
		onCrew3Button.setIcon(new ImageIcon(SelectItemScreen.class.getResource("/Images/astronaut_217px.png")));
		
		
		onCrew3Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onCrewUpdatingLabel.setText(manager.getCrewList().get(2).getName());
				manager.setOnCrewIndex(2);
				crewButtonEnabler(3);
			}
		});
		onCrew3Button.setForeground(Color.WHITE);
		onCrew3Button.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		onCrew3Button.setBackground(Color.DARK_GRAY);
		onCrew3Button.setBounds(815, 319, 217, 217);
		frame.getContentPane().add(onCrew3Button);
		
		JLabel onCrew3UpdatingLabel = new JLabel(getCrewName(2));
		onCrew3UpdatingLabel.setForeground(Color.WHITE);
		onCrew3UpdatingLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 30));
		onCrew3UpdatingLabel.setBounds(815, 542, 217, 41);
		frame.getContentPane().add(onCrew3UpdatingLabel);
		onCrew4Button.setIcon(new ImageIcon(SelectItemScreen.class.getResource("/Images/astronaut_217px.png")));
		
		
		onCrew4Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.setOnCrewIndex(3);
				onCrewUpdatingLabel.setText(manager.getCrewList().get(3).getName());
				crewButtonEnabler(4);
			}
		});
		onCrew4Button.setForeground(Color.WHITE);
		onCrew4Button.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		onCrew4Button.setBackground(Color.DARK_GRAY);
		onCrew4Button.setBounds(1044, 321, 217, 217);
		frame.getContentPane().add(onCrew4Button);
		
		JLabel onCrew4UpdatingLabel = new JLabel(getCrewName(3));
		onCrew4UpdatingLabel.setForeground(Color.WHITE);
		onCrew4UpdatingLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 30));
		onCrew4UpdatingLabel.setBounds(1043, 542, 217, 41);
		frame.getContentPane().add(onCrew4UpdatingLabel);
		
		JLabel selectedItemLabel = new JLabel("Select Item:");
		selectedItemLabel.setForeground(Color.WHITE);
		selectedItemLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		selectedItemLabel.setBounds(540, 11, 209, 47);
		frame.getContentPane().add(selectedItemLabel);
		
		JLabel divider2Image = new JLabel("");
		divider2Image.setIcon(new ImageIcon(SelectItemScreen.class.getResource("/Images/divider.png")));
		divider2Image.setBounds(795, 0, 10, 691);
		frame.getContentPane().add(divider2Image);
		
		JLabel selectedItemUpdatingLabel = new JLabel("<selected item>");
		selectedItemUpdatingLabel.setForeground(Color.WHITE);
		selectedItemUpdatingLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 30));
		selectedItemUpdatingLabel.setBounds(502, 627, 283, 41);
		frame.getContentPane().add(selectedItemUpdatingLabel);
		
		item1SelectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.setSelectedItem(manager.gettingAllItems()[0]);
				selectedItemUpdatingLabel.setText(manager.gettingAllItems()[0].getName());
				checkCrewButtons();
			}
		});
		item1SelectButton.setBounds(502, 57, 282, 33);
		frame.getContentPane().add(item1SelectButton);
		
		
		item2SelectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.setSelectedItem(manager.gettingAllItems()[1]);
				selectedItemUpdatingLabel.setText(manager.gettingAllItems()[1].getName());
				checkCrewButtons();
			}
		});
		item2SelectButton.setBounds(502, 101, 282, 33);
		frame.getContentPane().add(item2SelectButton);
		
		
		item3SelectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.setSelectedItem(manager.gettingAllItems()[2]);
				selectedItemUpdatingLabel.setText(manager.gettingAllItems()[2].getName());
				checkCrewButtons();
			}
		});
		item3SelectButton.setBounds(502, 189, 282, 33);
		frame.getContentPane().add(item3SelectButton);
		
		
		item4SelectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.setSelectedItem(manager.gettingAllItems()[3]);
				selectedItemUpdatingLabel.setText(manager.gettingAllItems()[3].getName());
				checkCrewButtons();
			}
		});
		item4SelectButton.setBounds(502, 145, 282, 33);
		frame.getContentPane().add(item4SelectButton);
		
		
		item5SelectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.setSelectedItem(manager.gettingAllItems()[4]);
				selectedItemUpdatingLabel.setText(manager.gettingAllItems()[4].getName());
				checkCrewButtons();
			}
		});
		item5SelectButton.setBounds(502, 277, 282, 33);
		frame.getContentPane().add(item5SelectButton);
		
		
		item6SelectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.setSelectedItem(manager.gettingAllItems()[5]);
				selectedItemUpdatingLabel.setText(manager.gettingAllItems()[5].getName());
				checkCrewButtons();
			}
		});
		item6SelectButton.setBounds(502, 233, 282, 33);
		frame.getContentPane().add(item6SelectButton);
		
		
		item7SelectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.setSelectedItem(manager.gettingAllItems()[6]);
				selectedItemUpdatingLabel.setText(manager.gettingAllItems()[6].getName());
				checkCrewButtons();
			}
		});
		item7SelectButton.setBounds(502, 363, 282, 33);
		frame.getContentPane().add(item7SelectButton);
		
		
		item8SelectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.setSelectedItem(manager.gettingAllItems()[7]);
				selectedItemUpdatingLabel.setText(manager.gettingAllItems()[7].getName());
				checkCrewButtons();
			}
		});
		item8SelectButton.setBounds(502, 319, 282, 33);
		frame.getContentPane().add(item8SelectButton);
		
		
		item9SelectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.setSelectedItem(manager.gettingAllItems()[8]);
				selectedItemUpdatingLabel.setText(manager.gettingAllItems()[8].getName());
				checkCrewButtons();
			}
		});
		item9SelectButton.setBounds(503, 451, 282, 33);
		frame.getContentPane().add(item9SelectButton);
		
		
		item10SelectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.setSelectedItem(manager.gettingAllItems()[9]);
				selectedItemUpdatingLabel.setText(manager.gettingAllItems()[9].getName());
				checkCrewButtons();
			}
		});
		item10SelectButton.setBounds(503, 407, 282, 33);
		frame.getContentPane().add(item10SelectButton);
		
		
		item11SelectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.setSelectedItem(manager.gettingAllItems()[10]);
				selectedItemUpdatingLabel.setText(manager.gettingAllItems()[10].getName());
				checkCrewButtons();
			}
		});
		item11SelectButton.setBounds(502, 539, 282, 33);
		frame.getContentPane().add(item11SelectButton);
		
		
		item12SelectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.setSelectedItem(manager.gettingAllItems()[11]);
				selectedItemUpdatingLabel.setText(manager.gettingAllItems()[11].getName());
				checkCrewButtons();
			}
		});
		item12SelectButton.setBounds(502, 495, 282, 33);
		frame.getContentPane().add(item12SelectButton);
		
		
		item13SelectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.setSelectedItem(manager.gettingAllItems()[12]);
				selectedItemUpdatingLabel.setText(manager.gettingAllItems()[12].getName());
				checkCrewButtons();
			}
		});
		item13SelectButton.setBounds(503, 583, 282, 33);
		frame.getContentPane().add(item13SelectButton);
		
		JButton goBackButton = new JButton("Go Back");
		goBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.launchItemMenuScreen();	
				closeWindow();
				finishedWindow();
			}
		});
		goBackButton.setBackground(Color.DARK_GRAY);
		goBackButton.setForeground(Color.WHITE);
		goBackButton.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		goBackButton.setBounds(20, 594, 452, 76);
		frame.getContentPane().add(goBackButton);
		
		
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkItem();
				closeWindow();
				finishedWindow();
			}
		});
		confirmButton.setForeground(Color.WHITE);
		confirmButton.setBackground(Color.DARK_GRAY);
		confirmButton.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		confirmButton.setBounds(815, 594, 439, 76);
		frame.getContentPane().add(confirmButton);
		
		JLabel backgroundLabel = new JLabel("");
		backgroundLabel.setIcon(new ImageIcon(SelectItemScreen.class.getResource("/Images/startScreenBackground.jpg")));
		backgroundLabel.setBounds(0, 0, 1274, 691);
		frame.getContentPane().add(backgroundLabel);
		
		
	}
	public void closeWindow() {
		frame.dispose();
	}
	public void finishedWindow() {
		manager.closeSelectItemScreen(this);
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
		
		JLabel toUseItemLabel = new JLabel("To Use Item:");
		toUseItemLabel.setForeground(Color.WHITE);
		toUseItemLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		toUseItemLabel.setBounds(20, 11, 209, 47);
		frame.getContentPane().add(toUseItemLabel);
		
		JLabel selectCrewUseItemOnLabel = new JLabel("Use Item On:");
		selectCrewUseItemOnLabel.setForeground(Color.WHITE);
		selectCrewUseItemOnLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 35));
		selectCrewUseItemOnLabel.setBounds(815, 11, 217, 47);
		frame.getContentPane().add(selectCrewUseItemOnLabel);
		
		checkCrewButtons();
	}
	
	public void crewButtonEnabler(int whichButton) {
		switch(whichButton) {
		case 1: useCrew1Button.setEnabled(false);
				useCrew2Button.setEnabled(true);
				useCrew3Button.setEnabled(true);
				useCrew4Button.setEnabled(true);
				onCrew1Button.setEnabled(false);
				onCrew2Button.setEnabled(true);
				onCrew3Button.setEnabled(true);
				onCrew4Button.setEnabled(true);
				break;
		case 2: useCrew1Button.setEnabled(true);
				useCrew2Button.setEnabled(false);
				useCrew3Button.setEnabled(true);
				useCrew4Button.setEnabled(true);
				onCrew1Button.setEnabled(true);
				onCrew2Button.setEnabled(false);
				onCrew3Button.setEnabled(true);
				onCrew4Button.setEnabled(true);
				break;
		case 3: useCrew1Button.setEnabled(true);
				useCrew2Button.setEnabled(true);
				useCrew3Button.setEnabled(false);
				useCrew4Button.setEnabled(true);
				onCrew1Button.setEnabled(true);
				onCrew2Button.setEnabled(true);
				onCrew3Button.setEnabled(false);
				onCrew4Button.setEnabled(true);
				break;
		case 4: useCrew1Button.setEnabled(true);
				useCrew2Button.setEnabled(true);
				useCrew3Button.setEnabled(true);
				useCrew4Button.setEnabled(false);
				onCrew1Button.setEnabled(true);
				onCrew2Button.setEnabled(true);
				onCrew3Button.setEnabled(true);
				onCrew4Button.setEnabled(false);
				break;
		}
		checkCrewButtons();
	}
	
	public void changeOnCrewButtons(boolean state) {
		onCrew1Button.setEnabled(state);
		onCrew2Button.setEnabled(state);
		onCrew3Button.setEnabled(state);
		onCrew4Button.setEnabled(state);
	}
	
	public void changeOnCrewButtonIndividual(int whichButton, boolean state) {
		switch(whichButton) {
		case 1: onCrew1Button.setEnabled(state);
				break;
		case 2: onCrew2Button.setEnabled(state);
				break;
		case 3: onCrew3Button.setEnabled(state);
				break;
		case 4: onCrew4Button.setEnabled(state);
				break;
		}
	}
	
	public String getItemsName(int index) {
		String name = "";
		if(manager.gettingAllItems()[index] != null) {
			name += manager.gettingAllItems()[index].getName() + " | " + manager.gettingAllItems()[index].getCount() + " Uses";
		} else {
			disableSelectedItemButtons(index+1);
		}
		return name;
	}
	
	public void disableSelectedItemButtons(int whichButton) {
		switch(whichButton) {
		case 1: item1SelectButton.setEnabled(false);
				break;
		case 2: item2SelectButton.setEnabled(false);
				break;
		case 3: item3SelectButton.setEnabled(false);
				break;
		case 4: item4SelectButton.setEnabled(false);
				break;
		case 5: item5SelectButton.setEnabled(false);
				break;
		case 6: item6SelectButton.setEnabled(false);
				break;
		case 7: item7SelectButton.setEnabled(false);
				break;
		case 8: item8SelectButton.setEnabled(false);
				break;
		case 9: item9SelectButton.setEnabled(false);
				break;
		case 10: item10SelectButton.setEnabled(false);
				break;
		case 11: item11SelectButton.setEnabled(false);
				break;
		case 12: item12SelectButton.setEnabled(false);
				break;
		case 13: item13SelectButton.setEnabled(false);
				break;
		}
	}
	
	public void checkCrewButtons() {
		if(manager.getCrewCount() <= 3) {
			useCrew4Button.setEnabled(false);
			onCrew4Button.setEnabled(false);
		}
		if(manager.getCrewCount() == 2) {
			useCrew3Button.setEnabled(false);
			onCrew3Button.setEnabled(false);
		} 
		if(manager.getSelectedItem() == null)  {
			changeOnCrewButtons(false);
		} else if(manager.getSelectedItem().getType() != "Medical") {
			changeOnCrewButtons(false);
		} else if(manager.getSelectedItem().getType() == "Medical") { //Checking for space plague
			MedicalItem tempItem = (MedicalItem) manager.getSelectedItem();
			if(tempItem.getSpacePlagueCure()) {
				for(int i=1; i <= manager.getCrewCount(); i++) {
					if(manager.getCrewList().get(i-1).getHasSpacePlague() == false) {
						changeOnCrewButtonIndividual(i, false);
					}
				}
			}
		}
	}
	
	public String getCrewName(int index) {
		String crewName = "";
		if(index < manager.getCrewCount()) {
			crewName = manager.getCrewList().get(index).getName();
		}
		return crewName;
	}
	
	public void checkItem() {
		String itemType = manager.getSelectedItem().getType();
		if(itemType != "Medical") {
			changeOnCrewButtons(false);
			try {
				manager.getPlayerShip().useItemType(manager.getSelectedItem(), manager.getPlayerShip(), manager.getCrewList().get(manager.getUsedCrewIndex()));
			} catch (MaximumStaminaException e) {
				JOptionPane.showMessageDialog(null, e);
			} catch (MaximumFuelException e) {
				JOptionPane.showMessageDialog(null, e);
			}
		} else {
			try {
				manager.getPlayerShip().useItemTypeOn(manager.getSelectedItem(), manager.getPlayerShip(), manager.getCrewList().get(manager.getUsedCrewIndex()), manager.getCrewList().get(manager.getOnCrewIndex()));
			} catch (MaximumHealthException e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}
		
	}
}
