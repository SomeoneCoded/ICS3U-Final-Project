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

// GUI Screen --> Holds all my other screens
@SuppressWarnings("serial")
public class GUIScreen extends JFrame {
	// other panels (screens)
	JPanel mainMenuPanel = new MainMenuScreen();
	JPanel conceptPanel = new ConceptScreen();
	JPanel activityPanel = new ActivityScreen();
	JPanel assessmentPanel = new AssessmentScreen();
	JPanel settingsPanel = new SettingsScreen();
	
	// menubar to access other screens
	JMenuBar menuBar = new JMenuBar();
	JMenu menu = new JMenu("Menu");
	JMenuBar windowMenuBar = new JMenuBar();
	JMenu windowMenu = new JMenu("Windows");
	
	// panel to hold other screens
	static JPanel cardPanel;

	// card layout to shuffle through the screens efficiently
	static CardLayout card = new CardLayout();

	// constructor method
	public GUIScreen() {
		setMenuBar();
		setupCardLayout();
		setFrame();
	}

	// https://docs.oracle.com/javase/tutorial/uiswing/layout/card.html
	// set up a card layout to store the screens
	private void setupCardLayout() {
		cardPanel = new JPanel();
		cardPanel.setBounds(0, 20, 1920, 1060);
		cardPanel.setLayout(card);

		cardPanel.add(mainMenuPanel, "Main Menu");
		cardPanel.add(conceptPanel, "Concepts");
		cardPanel.add(activityPanel, "Activity");
		cardPanel.add(assessmentPanel, "Assessment");
		cardPanel.add(settingsPanel, "Settings");

		add(cardPanel);
	}

	// https://stackoverflow.com/questions/10823382/how-to-show-different-cards-in-a-cardlayout
	// method to switch panels and show the requested screen
	static void showCard(String componentName) {
		card.show(cardPanel, componentName);
	}

	// set up the menubar
	private void setMenuBar() {
		menuBar.setBounds(0, 0, 40, 20);
		windowMenuBar.setBounds(40, 0, 80, 20);
		setScreenMenu();
		setWindowMenu();
		menuBar.add(menu);
		windowMenuBar.add(windowMenu);

	}

	// set the windows menu
	private void setWindowMenu() {
		JMenuItem window1 = new JMenuItem("Settings");
		JMenuItem window2 = new JMenuItem("Quit");

		// action listener for windows menu items
		ActionListener windowAction = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				if (event.getSource() == window1) { // access the settings screen
					card.show(cardPanel, "Settings"); 
					playSoundEffect("sound/valorant-teleporter.wav"); // sound effect
					playSoundEffect("voice/FSP-Settings.wav"); // offer a tutorial
					
				} else if (event.getSource() == window2) {
					playSoundEffect("sound/windows-xp-shutdown.wav"); // sound effect
					// playSoundEffect("voice/FSP-Conclusion.wav"); --> get's overlapped
					dispose(); // close the application
				}

			}

		};
		
		// add action listener to window items
		window1.addActionListener(windowAction);
		window2.addActionListener(windowAction);
		
		// add window items to window menu
		windowMenu.add(window1);
		windowMenu.add(window2);

	}
	
	// set the screen menu
	private void setScreenMenu() {
		// screen menu items
		JMenuItem menu1 = new JMenuItem("Main Menu");
		JMenuItem menu2 = new JMenuItem("Concepts");
		JMenuItem menu3 = new JMenuItem("Activity");
		JMenuItem menu4 = new JMenuItem("Assessment");

		// action listener for screen menu
		ActionListener menuAction = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				if (event.getSource() == menu1) { // access the main menu screen
					card.show(cardPanel, "Main Menu");
					playSoundEffect("sound/valorant-teleporter.wav"); // sound effect
					
				} else if (event.getSource() == menu2) { // access the concepts screen
					card.show(cardPanel, "Concepts");
					playSoundEffect("sound/valorant-teleporter.wav"); // sound effect
					
				} else if (event.getSource() == menu3) { // access the activity screen
					card.show(cardPanel, "Activity");
					playSoundEffect("sound/valorant-teleporter.wav"); // sound effect
					
				} else if (event.getSource() == menu4) { // access the assessment screen
					card.show(cardPanel, "Assessment");
					playSoundEffect("sound/valorant-teleporter.wav"); // sound effect
					
				}
			}

		};

		// add action listener to menu items
		menu1.addActionListener(menuAction);
		menu2.addActionListener(menuAction);
		menu3.addActionListener(menuAction);
		menu4.addActionListener(menuAction);
		
		// add menu items to their menu
		menu.add(menu1);
		menu.add(menu2);
		menu.add(menu3);
		menu.add(menu4);

	}
	
	// play sound effects
	private void playSoundEffect(String name) {
		Sound soundEffect = new Sound(name);
		soundEffect.play();
	}
	
	// set up the frame
	private void setFrame() {
		setSize(1920, 1080);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.black);
		setLayout(null);
		setResizable(false);
		setTitle("Objects and Classes");
		add(menuBar);
		add(windowMenuBar);
		setVisible(true);

	}
}
