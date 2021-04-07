package graphicalInterface;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import exceptions.DeadCrewException;
import exceptions.GameOverException;
import exceptions.IncorrectCrewNumberException;
import exceptions.InsufficientStaminaException;
import exceptions.RandomEventException;
import exceptions.TakenDamageException;
import matter.CrewMember;
import matter.LandMark;
import matter.Outpost;
import matter.Planet;
import matter.PlanetLocation;
import matter.SpaceShip;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

public class ExploreScreen extends GenericScreenProcessing {
	private GameManager manager; 
	private JFrame frame;

	public ExploreScreen(GameManager incomingManager) {
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
		manager.closeExploreScreen(this);
	}
	public void finishedWindowToOutpost() {
		manager.closeExploreScreenToOutpost(this);
	}
	public void finishedWindowToGameOver() {
		manager.closeExploreScreenToGameOver(this);
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		int shipXCoord = manager.getGame().getPlayerShip().getCoordinateX();
		int shipYCoord = manager.getGame().getPlayerShip().getCoordinateY();
		ArrayList<CrewMember> shipCrewList = manager.getGame().getPlayerShip().getCrewList();
		Planet currentPlanet = manager.getGame().getPlayerShip().getPlane()[shipXCoord][shipYCoord].getCurrentPlanet();
		int[] exploredPositions = currentPlanet.getExploredPositions();
		JLabel timeLabel = generateTimeLabel(frame);
		
		JLabel selectedNumberLabel = new JLabel("<html>0/1 Selected</br></html>");
		selectedNumberLabel.setForeground(Color.WHITE);
		selectedNumberLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		selectedNumberLabel.setBounds(20, 547, 170, 125);
		frame.getContentPane().add(selectedNumberLabel);
		
		JList<CrewMember> crewList = new JList(shipCrewList.toArray());
		crewList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				selectedNumberLabel.setText("<html>" + crewList.getSelectedIndices().length + "/1 Selected.</html>");
			}
		});

		crewList.setFont(new Font("Eras Medium ITC", Font.PLAIN, 22));
		crewList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		crewList.setSelectedIndices(new int[] {-1});
		crewList.setBounds(10, 78, 146, 481);
		frame.getContentPane().add(crewList);
		
		JLabel lblExplore = new JLabel("Explore");
		lblExplore.setForeground(Color.WHITE);
		lblExplore.setFont(new Font("Eras Medium ITC", Font.PLAIN, 50));
		lblExplore.setBounds(550, 12, 165, 58);
		frame.getContentPane().add(lblExplore);
		
		JLabel[] staminaLabelArray = generateStaminaLabelArray(shipCrewList, frame);
		
		
		
		JButton[] exploreButtonArray = new JButton[4];
		JButton exploreButton = null;
		for (int i=0; i < 2; i++) {
			for (int j=0; j < 2; j++) {
				int tempIndex = i * 2;
				if (exploredPositions[tempIndex + j] == 0) {
					exploreButton = new JButton("Unexplored!");
				} else {
					exploreButton = new JButton(currentPlanet.getLandMap()[i][j].getName());
				}
				exploreButton.setBounds(300 + ((tempIndex / 2) * 330), 70 + (j	 * 300), 316, 290);
				exploreButton.setFont(new Font("Eras Medium ITC", Font.PLAIN, 16));
				frame.getContentPane().add(exploreButton);
				exploreButtonArray[tempIndex+j] = exploreButton;
			}
		}
		exploreButtonArray[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				performExplore(crewList.getSelectedIndices(), 0, 0, currentPlanet); //not pulling right character
				updateStaminaLabels(frame, staminaLabelArray, shipCrewList);
				updateTimeLabel(timeLabel);
				updateExploreButtons(exploreButtonArray, currentPlanet);
			}
		});
		exploreButtonArray[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				performExplore(crewList.getSelectedIndices(), 0, 1, currentPlanet);
				updateStaminaLabels(frame, staminaLabelArray, shipCrewList);
				updateTimeLabel(timeLabel);
				updateExploreButtons(exploreButtonArray, currentPlanet);
			}
		});
		exploreButtonArray[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				performExplore(crewList.getSelectedIndices(), 1, 0, currentPlanet);
				updateStaminaLabels(frame, staminaLabelArray, shipCrewList);
				updateTimeLabel(timeLabel);
				updateExploreButtons(exploreButtonArray, currentPlanet);
			}
		});
		exploreButtonArray[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				performExplore(crewList.getSelectedIndices(), 1, 1, currentPlanet);
				updateStaminaLabels(frame, staminaLabelArray, shipCrewList);
				updateTimeLabel(timeLabel);
				updateExploreButtons(exploreButtonArray, currentPlanet);
			}
		});
		
		JLabel selectCrewMemberLabel = new JLabel("Select Crew Member");
		selectCrewMemberLabel.setForeground(Color.WHITE);
		selectCrewMemberLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 30));
		selectCrewMemberLabel.setBounds(10, 12, 288, 50);	
		frame.getContentPane().add(selectCrewMemberLabel);
		
		JButton restButton = new JButton("Rest");
		restButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String restInformation = manager.getPlayerShip().restAllMembers(currentPlanet.getHabitability(),manager.getGame().getEvents());
					JLabel restDialog = new JLabel(restInformation);
					restDialog.setFont(new Font("Tahoma", Font.PLAIN, 25));
					JOptionPane.showMessageDialog(null, restDialog);
					updateTimeLabel(timeLabel);
					} catch (GameOverException message) {
						manager.setDeathMessage("By resting too much, the fragile remains of your warp drive fade into dust...");
						closeWindow();
						finishedWindowToGameOver();
					} catch(DeadCrewException message) {
						JOptionPane.showMessageDialog(null, message);
						updateTimeLabel(timeLabel);
					} catch(TakenDamageException message) {
						JOptionPane.showMessageDialog(null, message);
						updateTimeLabel(timeLabel);
					}
			}
		});
		restButton.setBounds(967, 12, 287, 209);
		frame.getContentPane().add(restButton);
		
		JButton repairShipButton = new JButton("Repair Ship");
		repairShipButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				findCandidateForRepair(manager.getPlayerShip());
				} catch (GameOverException message) {
					manager.setDeathMessage("If only you could fix your time management skills.");
					closeWindow();
					finishedWindowToGameOver();
				} catch(DeadCrewException message) {
					JOptionPane.showMessageDialog(null, e);
			} finally {
				closeWindow();
				finishedWindow();
			}
			}
		});
		repairShipButton.setBounds(967, 232, 287, 220);
		frame.getContentPane().add(repairShipButton);
		
		JButton leaveButton = new JButton("Leave");
		leaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
				finishedWindow();
			}
		});
		leaveButton.setBounds(967, 463, 287, 209);
		frame.getContentPane().add(leaveButton);
		
		JLabel backgroundImage = new JLabel("");
		backgroundImage.setIcon(new ImageIcon(ExploreScreen.class.getResource("/Images/startScreenBackground.jpg")));
		backgroundImage.setBounds(0, 0, 1264, 681);
		frame.getContentPane().add(backgroundImage);
	}
	public void performExplore(int[] selectedCrew, int cordX, int cordY, Planet currentPlanet) {
		SpaceShip playerShip = manager.getGame().getPlayerShip();
		ArrayList<CrewMember> selectedCrewArray = new ArrayList<CrewMember>();
		for (int i=0; i < selectedCrew.length; i++) {
			selectedCrewArray.add(manager.getGame().getPlayerShip().getCrewList().get(selectedCrew[i]));
		}
		PlanetLocation exploreType = null;
		try {
			if (manager.getGame().getPlayerShip().checkValidCrew(1, selectedCrewArray)) {
				try {
					exploreType = selectedCrewArray.get(0).explore(cordX, cordY, currentPlanet, playerShip);
					} catch (IncorrectCrewNumberException e) {
						JOptionPane.showMessageDialog(null, e);	
						closeWindow();
						finishedWindow();
					} catch (GameOverException e) {
						manager.setDeathMessage("Game Over - Taking the long route.");
						finishedWindowToGameOver();
						closeWindow();
					} catch(DeadCrewException e) {
						JOptionPane.showMessageDialog(null, e);
						closeWindow();
						finishedWindow();
					} catch(TakenDamageException message) {
						JOptionPane.showMessageDialog(null, message.toString());
					} catch(RandomEventException message) {
						JOptionPane.showMessageDialog(null, message.toString());
						closeWindow();
						finishedWindow(); 
					}	
				if (exploreType != null) {
					if (exploreType.getCurrentOutpost() instanceof Outpost) {
						closeWindow();
						manager.setCurrentOutpost(exploreType.getCurrentOutpost());
						finishedWindowToOutpost();
						
					} else if (exploreType.getCurrentLandMark() instanceof LandMark) {
						String reward[] = selectedCrewArray.get(0).searchLandMark(exploreType.getCurrentLandMark(), manager.getPlayerShip());
						JLabel exploreLabel = new JLabel(selectedCrewArray.get(0).getName() + " goes exploring to find the " + exploreType.getCurrentLandMark().getName() + "!");
						exploreLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(null, exploreLabel);
						JLabel rewardLabel = new JLabel(reward[0]);
						rewardLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(null, rewardLabel);
						if (reward[1] == "StarPiece") {
							if (manager.getPlayerShip().checkStarPieces() == true) {
								manager.setCompletedGame(true);
								JLabel outputLabel = new JLabel("Captain! We have all the Star Pieces! Let's leave this galaxy!");
								outputLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
								JOptionPane.showMessageDialog(null, outputLabel);
							} else {
								JLabel outputLabel = new JLabel( "Captain, we have " + playerShip.getInventoryLength(playerShip.getStarPieceInventory()) + "/" + playerShip.getStarPieceInventoryLimit() + " Star pieces.");
								outputLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
								JOptionPane.showMessageDialog(null, outputLabel);
							}
						}
					}	
				}
			}
			 
		} catch(IncorrectCrewNumberException e) {
			JOptionPane.showMessageDialog(null, e);
		} catch(InsufficientStaminaException e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	public void updateStaminaLabels(JFrame frame, JLabel[] staminaLabelArray, ArrayList<CrewMember> crewList) { //doesnt check for dead crew
		for (int i=0; i < staminaLabelArray.length; i++) {
			CrewMember currentCrew = crewList.get(i);
			staminaLabelArray[i].setText(currentCrew.getCurrentStamina() + "/" + currentCrew.getMaxStamina());
		}
	}
	
	public void updateExploreButtons(JButton[] exploreButtonArray, Planet currentPlanet) {
		int[] exploredPositions = currentPlanet.getExploredPositions();
		
		for (int i=0; i < 2; i++) {
			for (int j=0; j < 2; j++) {
				int pos = (2 * i) + j;
				if (exploredPositions[pos] == 0) {
					exploreButtonArray[pos].setText("Unexplored!");
				} else {
					exploreButtonArray[pos].setText(currentPlanet.getLandMap()[i][j].getName());
				}
			}
		}
	}
}