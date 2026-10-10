package fsp;
//Name: Dailiang Chen
//Date: June 14, 2024
//Course Code: ICS3U1-01
//Title: FSP - Dailiang Chen
//Description:
//This program teaches the users the concepts of Objects and Classes in Java.
//To learn more about this topic, users can access the concept screen and read the content at their own pace
//If the user wants a visualization of how objects work, they can check out the activity and see what happens to an object when you modify its attributes
//If the user wants to test their knowledge, they can visit the assessment page. There are 10 multiple choice questions. The user can retry if they fail.
//Features:
//Menu bar setup top left to access any screen from anywhere
//Screens are in a card layout so they transition smoothly and don't require a new instance of them
//Audio tutorial from me!
//Sound effects. A lot of sound effects...
//Settings page to modify the program's background and to import background audio (with a built-in sound controller)
//Major Skills:
//Methods, Objects, Classes, Java Swing, GUI, Frames, Panels, Card Layout, Imports, Arrays, Event listeners, File readers, Images, Buttons, Menubar, Sliders, ProgressBar
//Areas of Concern:
//Audio tutorials can overlap
//Background settings are not functional yet
//GIFs do not animate when you import it to the activity
//Make sure to turn down your volume a little as there will be a lot of sound effects
//Audio controls may not work properly. E.g. Cannot be unpaused once paused

// import statements
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// main menu screen
@SuppressWarnings("serial")
public class MainMenuScreen extends JPanel implements ActionListener{
	
	// main menu header
	JLabel headerLabel = new JLabel("Objects and Classes"); 
	
	// buttons to access other screens
	JButton conceptButton = new JButton("Concepts");
	JButton activityButton = new JButton("Activity");
	JButton assessmentButton = new JButton("Assessment");
	
	// main menu screen image
	JLabel menuImageLabel = new JLabel(new ImageIcon("images/Cube_Morph.gif"));
	
	// constructor method
	public MainMenuScreen() {
		setHeader();
		setMenuImage();
		setButtons();
		setPanel();
	}

	// set the main menu header
	private void setHeader() {
		headerLabel.setBounds(40, 30, 1800, 110);
		headerLabel.setFont(new Font("Consolas", Font.BOLD, 100));
		headerLabel.setForeground(Color.white);
		add(headerLabel);
		
	}

	// set the main menu image
	private void setMenuImage() {
		menuImageLabel.setBounds(10, 130, 900, 900);
		add(menuImageLabel);

	}
	
	// set the buttons
	private void setButtons() {
		conceptButton.setBounds(960, 300, 900, 100);
		activityButton.setBounds(960, 500, 900, 100);
		assessmentButton.setBounds(960, 700, 900, 100);
		
		conceptButton.setFont(new Font("Consolas",Font.PLAIN, 30));
		activityButton.setFont(new Font("Consolas",Font.PLAIN, 30));
		assessmentButton.setFont(new Font("Consolas",Font.PLAIN, 30));
		
		conceptButton.addActionListener(this);
		activityButton.addActionListener(this);
		assessmentButton.addActionListener(this);
		
		add(conceptButton);
		add(activityButton);
		add(assessmentButton);
	}
	
	// play sound effects
	private void playSoundEffect(String name) {
		Sound soundEffect = new Sound(name);
		soundEffect.play();
	}
	
	// set this panel (screen)
	private void setPanel() {
		setSize(1920, 1080);
		setLayout(null);
		setBackground(new Color(0,0,0,255));
		
	}

	// action listener
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == conceptButton) { // if they click to go to concept screen
			GUIScreen.showCard("Concepts"); // display concept screen
			playSoundEffect("sound/anime-wow-sound-effect.wav"); // sound effect
			playSoundEffect("voice/FSP-Concept.wav"); // tutorial
			
		} else if (event.getSource() == activityButton) { // if they click to go to activity screen
			GUIScreen.showCard("Activity"); // display activity screen
			playSoundEffect("sound/anime-wow-sound-effect.wav"); // sound effect
			playSoundEffect("voice/FSP-Activity.wav"); // tutorial
			
		} else if (event.getSource() == assessmentButton) { // if they click to go to assessment screen
			GUIScreen.showCard("Assessment"); // display assessment screen
			playSoundEffect("sound/anime-wow-sound-effect.wav"); // sound effect
			playSoundEffect("voice/FSP-Assessment.wav"); // tutorial
			
		}

	}
}
