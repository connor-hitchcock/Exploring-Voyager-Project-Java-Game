package graphicalInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import main.GameEnvironment;

import com.jgoodies.forms.layout.FormSpecs;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ImageIcon;
import java.awt.Color;

public class EndScreen {
	private JFrame endFrame;
	private GameManager manager;

	/**
	 * Create the application.
	 */
	public EndScreen(GameManager incomingManager) {
		manager = incomingManager;
		manager.setScore(manager.getScore());
		initialize();
		endFrame.setVisible(true);
		endFrame.setResizable(false);
	}

	public void closeWindow() {
		endFrame.dispose();
	}
	public void finishedWindow() {
		System.exit(0);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		endFrame = new JFrame();
		endFrame.setBounds(100, 100, 999, 701);
		endFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		endFrame.getContentPane().setLayout(null);
		
		JLabel finishedLabel = new JLabel("Fin");
		finishedLabel.setForeground(Color.WHITE);
		finishedLabel.setBounds(420, 281, 135, 87);
		finishedLabel.setFont(new Font("Monotype Corsiva", Font.PLAIN, 99));
		finishedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		endFrame.getContentPane().add(finishedLabel);
		
		JLabel scoreLabel = new JLabel("Score:");
		scoreLabel.setForeground(Color.WHITE);
		scoreLabel.setBounds(380, 494, 152, 58);
		scoreLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 50));
		endFrame.getContentPane().add(scoreLabel);
		
		JLabel scoredUpdatingLabel = new JLabel(Integer.toString(manager.getScore()));
		scoredUpdatingLabel.setForeground(Color.WHITE);
		scoredUpdatingLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 50));
		scoredUpdatingLabel.setBounds(542, 494, 43, 58);
		endFrame.getContentPane().add(scoredUpdatingLabel);
		
		JLabel backgroundImage = new JLabel("");
		backgroundImage.setIcon(new ImageIcon(EndScreen.class.getResource("/Images/startScreenBackground.jpg")));
		backgroundImage.setBounds(0, 0, 993, 672);
		endFrame.getContentPane().add(backgroundImage);
	}

}
