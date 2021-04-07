package graphicalInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import main.GameEnvironment;
import main.RandomEvents;
import matter.CrewMember;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import exceptions.DeadCrewException;
import exceptions.GameOverException;
import exceptions.IncorrectCrewNumberException;
import exceptions.InsufficientFuelException;
import exceptions.InsufficientStaminaException;
import exceptions.InvalidMoveException;
import exceptions.RandomEventException;
import exceptions.TakenDamageException;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class TraverseScreen extends GenericScreenProcessing {
	private GameManager manager;
	private JFrame frame;

	public TraverseScreen(GameManager incomingManager) {
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
		manager.closeTraverseScreen(this);
	}
	
	public void finishedWindowToGameOver() {
		manager.closeTraverseScreenToGameOver(this);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		String[] posStrings = manager.getGame().getPlayerShip().calculateTraverseData();
		generateCoordinates(manager.getPlayerShip(), frame);
		JLabel timeLabel =  generateTimeLabel(frame);
		
		JLabel selectedNumberLabel = new JLabel("<html>0/2 Selected</br> (Use CTRL to select).</html>");
		selectedNumberLabel.setForeground(Color.WHITE);
		selectedNumberLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		selectedNumberLabel.setBounds(20, 460, 170, 125);
		frame.getContentPane().add(selectedNumberLabel);
		
		JList<CrewMember> crewList = new JList(manager.getGame().getPlayerShip().getCrewList().toArray());
		crewList.setBackground(Color.DARK_GRAY);
		crewList.setForeground(Color.WHITE);
		crewList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				selectedNumberLabel.setText("<html>" + crewList.getSelectedIndices().length + "/2 Selected</br> (Use CTRL to select).</html>");
			}
		});

		crewList.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		crewList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		crewList.setSelectedIndices(new int[] {-1});
		crewList.setBounds(20, 254, 199, 174);
		frame.getContentPane().add(crewList);
		
		JLabel TraverseTitleLabel = new JLabel("Traverse");
		TraverseTitleLabel.setForeground(Color.WHITE);
		TraverseTitleLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 50));
		TraverseTitleLabel.setBounds(531, 301, 197, 58);
		frame.getContentPane().add(TraverseTitleLabel);
		
		JButton postion1TraversableButton = new JButton(posStrings[0]);
		postion1TraversableButton.setBackground(Color.DARK_GRAY);
		postion1TraversableButton.setForeground(Color.WHITE);
		postion1TraversableButton.setFont(new Font("Eras Medium ITC", Font.PLAIN, 16));
		if (posStrings[0].equals("Beyond Space-Time")) {
			postion1TraversableButton.setEnabled(false);
		}
		postion1TraversableButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				performTraverse(crewList.getSelectedIndices(), 0, true, manager.getGame().getEvents());
			}
		});
		postion1TraversableButton.setBounds(234, 11, 260, 210);
		frame.getContentPane().add(postion1TraversableButton);
		
		JButton postion2TraversableButton = new JButton(posStrings[1]);
		postion2TraversableButton.setForeground(Color.WHITE);
		postion2TraversableButton.setBackground(Color.DARK_GRAY);
		postion2TraversableButton.setFont(new Font("Eras Medium ITC", Font.PLAIN, 16));
		if (posStrings[1].equals("Beyond Space-Time")) {
			postion2TraversableButton.setEnabled(false);
		}
		postion2TraversableButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				performTraverse(crewList.getSelectedIndices(), 1, false, manager.getGame().getEvents());
			}
		});
		postion2TraversableButton.setBounds(504, 11, 260, 210);
		frame.getContentPane().add(postion2TraversableButton);
		
		JButton postion3TraversableButton = new JButton(posStrings[2]);
		postion3TraversableButton.setForeground(Color.WHITE);
		postion3TraversableButton.setBackground(Color.DARK_GRAY);
		postion3TraversableButton.setFont(new Font("Eras Medium ITC", Font.PLAIN, 16));
		if (posStrings[2].equals("Beyond Space-Time")) {
			postion3TraversableButton.setEnabled(false);
		}
		postion3TraversableButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				performTraverse(crewList.getSelectedIndices(), 2, true, manager.getGame().getEvents());
			}
		});
		postion3TraversableButton.setBounds(774, 11, 260, 210);
		frame.getContentPane().add(postion3TraversableButton);
		
		JButton postion4TraversableButton = new JButton(posStrings[3]);
		postion4TraversableButton.setBackground(Color.DARK_GRAY);
		postion4TraversableButton.setForeground(Color.WHITE);
		postion4TraversableButton.setFont(new Font("Eras Medium ITC", Font.PLAIN, 16));
		if (posStrings[3].equals("Beyond Space-Time")) {
			postion4TraversableButton.setEnabled(false);
		}
		postion4TraversableButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				performTraverse(crewList.getSelectedIndices(), 3, false, manager.getGame().getEvents());
			}
		});
		postion4TraversableButton.setBounds(234, 232, 260, 217);
		frame.getContentPane().add(postion4TraversableButton);
		
		JButton postion5TraversableButton = new JButton(posStrings[4]);
		postion5TraversableButton.setForeground(Color.WHITE);
		postion5TraversableButton.setBackground(Color.DARK_GRAY);
		postion5TraversableButton.setFont(new Font("Eras Medium ITC", Font.PLAIN, 16));
		if (posStrings[4].equals("Beyond Space-Time")) {
			postion5TraversableButton.setEnabled(false);
		}
		postion5TraversableButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				performTraverse(crewList.getSelectedIndices(), 4, false, manager.getGame().getEvents());
			}
		});
		postion5TraversableButton.setBounds(774, 232, 260, 217);
		frame.getContentPane().add(postion5TraversableButton);
		
		JButton postion6TraversableButton = new JButton(posStrings[5]);
		postion6TraversableButton.setForeground(Color.WHITE);
		postion6TraversableButton.setBackground(Color.DARK_GRAY);
		postion6TraversableButton.setFont(new Font("Eras Medium ITC", Font.PLAIN, 16));
		if (posStrings[5].equals("Beyond Space-Time")) {
			postion6TraversableButton.setEnabled(false);
		}
		postion6TraversableButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				performTraverse(crewList.getSelectedIndices(), 5, true, manager.getGame().getEvents());
			}
		});
		postion6TraversableButton.setBounds(234, 460, 260, 210);
		frame.getContentPane().add(postion6TraversableButton);
		
		JButton postion7TraversableButton = new JButton(posStrings[6]);
		postion7TraversableButton.setBackground(Color.DARK_GRAY);
		postion7TraversableButton.setForeground(Color.WHITE);
		postion7TraversableButton.setFont(new Font("Eras Medium ITC", Font.PLAIN, 16));
		if (posStrings[6].equals("Beyond Space-Time")) {
			postion7TraversableButton.setEnabled(false);
		}
		postion7TraversableButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				performTraverse(crewList.getSelectedIndices(), 6, false, manager.getGame().getEvents());
			}
		});
		postion7TraversableButton.setBounds(504, 460, 260, 210);
		frame.getContentPane().add(postion7TraversableButton);
		
		JButton postion8TraversableButton = new JButton(posStrings[7]);
		postion8TraversableButton.setForeground(Color.WHITE);
		postion8TraversableButton.setBackground(Color.DARK_GRAY);
		postion8TraversableButton.setFont(new Font("Eras Medium ITC", Font.PLAIN, 16));
		if (posStrings[7].equals("Beyond Space-Time")) {
			postion8TraversableButton.setEnabled(false);
		}
		postion8TraversableButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				performTraverse(crewList.getSelectedIndices(), 7, true, manager.getGame().getEvents());
			}
		});
		
		postion8TraversableButton.setBounds(774, 460, 260, 210);
		frame.getContentPane().add(postion8TraversableButton);
		
		JButton shipMenuButton = new JButton("Back to Ship");
		shipMenuButton.setBackground(Color.DARK_GRAY);
		shipMenuButton.setForeground(Color.WHITE);
		shipMenuButton.setFont(new Font("Eras Medium ITC", Font.PLAIN, 25));
		shipMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
				finishedWindow();
			}
		});
		shipMenuButton.setBounds(1062, 258, 180, 169);
		frame.getContentPane().add(shipMenuButton);
		
		JLabel backgroundImage = new JLabel("");
		backgroundImage.setIcon(new ImageIcon(TraverseScreen.class.getResource("/Images/startScreenBackground.jpg")));
		backgroundImage.setBounds(0, 0, 1264, 681);
		frame.getContentPane().add(backgroundImage);
	}
	
	public void performTraverse(int[] selectedCrew, int position, boolean diagonal, RandomEvents events) {

	ArrayList<CrewMember> selectedCrewArray = new ArrayList<CrewMember>();
	for (int i=0; i < selectedCrew.length; i++) {
		selectedCrewArray.add(manager.getGame().getPlayerShip().getCrewList().get(i));
	}
	try {
		if (manager.getPlayerShip().checkValidCrew(2, selectedCrewArray)) {
			try {
			manager.getPlayerShip().traverse(manager.getGame(), position, diagonal, selectedCrewArray, events);	
			closeWindow();
			finishedWindow();
			} catch (IncorrectCrewNumberException e) {
				JOptionPane.showMessageDialog(null, e);	
				closeWindow();
				finishedWindow();
			} catch (GameOverException e) {
				manager.setDeathMessage("Game Over - Extra long road trip.");
				closeWindow();
				finishedWindowToGameOver();
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
		}
	} catch(IncorrectCrewNumberException e) {
		JOptionPane.showMessageDialog(null, e);
	} catch(InsufficientFuelException e) {
		JOptionPane.showMessageDialog(null, e);
	} catch(InsufficientStaminaException e) {
		JOptionPane.showMessageDialog(null, e);
	} catch(InvalidMoveException e) {
		JLabel exploreLabel = new JLabel("Cannot travel there!");
		exploreLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		JOptionPane.showMessageDialog(null, exploreLabel);
	}
}
}
