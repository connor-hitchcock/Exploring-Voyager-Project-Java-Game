package graphicalInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import main.GameEnvironment;

import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class GameOverScreen {

	private GameManager manager;
	private JFrame frame;

	public GameOverScreen(GameManager incomingManager) {
		manager = incomingManager;
		manager.getGame();
		manager.setScore(GameEnvironment.getScore());
		initialize();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
	}
	public void closeWindow() {
		frame.dispose();
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
		
		JLabel gameOverLabel = new JLabel("Game Over:");
		gameOverLabel.setForeground(Color.WHITE);
		gameOverLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 99));
		gameOverLabel.setBounds(354, 156, 544, 75);
		frame.getContentPane().add(gameOverLabel);
		
		JLabel deathMsgUpdatingLabel = new JLabel(manager.getDeathMessage());
		deathMsgUpdatingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		deathMsgUpdatingLabel.setForeground(Color.WHITE);
		deathMsgUpdatingLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 36));
		deathMsgUpdatingLabel.setBounds(112, 270, 983, 208);
		frame.getContentPane().add(deathMsgUpdatingLabel);
		
		JLabel scoreLabel = new JLabel("Score: ");
		scoreLabel.setForeground(Color.WHITE);
		scoreLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 50));
		scoreLabel.setBounds(456, 467, 189, 95);
		frame.getContentPane().add(scoreLabel);
		
		JLabel scoreUpdatingLabel = new JLabel(Integer.toString(manager.getScore()));
		scoreUpdatingLabel.setForeground(Color.WHITE);
		scoreUpdatingLabel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 50));
		scoreUpdatingLabel.setBounds(606, 467, 501, 95);
		frame.getContentPane().add(scoreUpdatingLabel);
		
		JLabel backgroundImage = new JLabel("");
		backgroundImage.setIcon(new ImageIcon(GameOverScreen.class.getResource("/Images/startScreenBackground.jpg")));
		backgroundImage.setBounds(0, 0, 1264, 681);
		frame.getContentPane().add(backgroundImage);
	}

}
