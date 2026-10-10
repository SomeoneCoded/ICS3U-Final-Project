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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

// settings screen
@SuppressWarnings("serial")
public class SettingsScreen extends JPanel implements ActionListener,ChangeListener{
	// different setting headers
	JLabel themeHeader = new JLabel("Theme Settings");
	JLabel soundHeader = new JLabel("Sound Settings");
	JLabel backgroundLabel = new JLabel("Background:");
	JLabel colorLabel = new JLabel("Color (RGBA): ");
	JLabel soundLabel = new JLabel("Sound:");
	JLabel soundControlLabel = new JLabel("Sound Control:");

	// colour sliders
	JSlider red = new JSlider(JSlider.HORIZONTAL, 0, 255, 255);
	JSlider green = new JSlider(JSlider.HORIZONTAL, 0, 255, 255);
	JSlider blue = new JSlider(JSlider.HORIZONTAL, 0, 255, 255);
	JSlider alpha = new JSlider(JSlider.HORIZONTAL, 0, 255, 102);
	
	// image selection
	JButton backgroundButton = new JButton("Image");
	String backgroundFileString;
	JLabel backgroundFileName = new JLabel("");
	File backgroundFile;
	
	// audio selection
	JButton soundChooser = new JButton("Audio");
	String soundFileString;
	JLabel soundFileName = new JLabel("");
	File soundFile;
	
	// small media player
	JButton playButton = new JButton("Play");
	JButton pauseButton = new JButton("Pause");
	JButton muteButton = new JButton("Mute");
	JButton loopButton = new JButton("Loop");
	
	// apply settings (hasn't been added yet)
	JButton applyButton = new JButton("Apply");
	
	// background sound
	Sound backgroundSound; 
	
	// constructor method
	public SettingsScreen() {
		setTheme();
		setSound();
		setPanel();
	}

	
	// create theme setting
	private void setTheme() {
		themeHeader.setBounds(40, 50, 280, 35);
		themeHeader.setFont(new Font("Consolas", Font.BOLD, 30));
		themeHeader.setForeground(Color.white);
		add(themeHeader);
		backgroundImage();
		colorEditor();
	}
	
	// set up for selection of background image
	private void backgroundImage() {
		backgroundLabel.setBounds(40, 200, 280, 35);
		backgroundLabel.setFont(new Font("Consolas", Font.PLAIN, 30));
		backgroundLabel.setForeground(Color.white);
		add(backgroundLabel);
		
		backgroundButton.setBounds(330, 200, 160, 35);
		backgroundButton.setFont(new Font("Consolas", Font.PLAIN, 15));
		backgroundButton.addActionListener(this);
		add(backgroundButton);
		
		backgroundFileName.setBounds(500, 200, 400, 35);
		backgroundFileName.setFont(new Font("Consolas", Font.PLAIN, 10));
		backgroundFileName.setForeground(Color.white);
		add(backgroundFileName);

	}

	// color editor
	private void colorEditor() {
		colorLabel.setBounds(40, 300, 280, 35);
		colorLabel.setFont(new Font("Consolas", Font.PLAIN, 30));
		colorLabel.setForeground(Color.white);
		add(colorLabel);
		
		setRedSlider();
		setBlueSlider();
		setGreenSlider();
		setAlphaSlider();

	}

	// set the red slider
	private void setRedSlider() {
		red.setBounds(40, 400, 600, 50);
		red.setMajorTickSpacing(255);
		red.setMinorTickSpacing(51);
		red.setPaintTicks(true);
		red.setPaintLabels(true);
		red.setBackground(null);
		red.setForeground(Color.white);
		red.addChangeListener(this);
		add(red);

	}

	// set the green slider
	private void setGreenSlider() {
		green.setBounds(40, 500, 600, 50);
		green.setMajorTickSpacing(255);
		green.setMinorTickSpacing(51);
		green.setPaintTicks(true);
		green.setPaintLabels(true);
		green.setBackground(null);
		green.setForeground(Color.white);
		green.addChangeListener(this);
		add(green);
	}

	// set the blue
	private void setBlueSlider() {
		blue.setBounds(40, 600, 600, 50);
		blue.setMajorTickSpacing(255);
		blue.setMinorTickSpacing(51);
		blue.setPaintTicks(true);
		blue.setPaintLabels(true);
		blue.setBackground(null);
		blue.setForeground(Color.white);
		blue.addChangeListener(this);
		add(blue);
	}

	// set the intensity of the color
	private void setAlphaSlider() {
		alpha.setBounds(40, 700, 600, 50);
		alpha.setMajorTickSpacing(255);
		alpha.setMinorTickSpacing(51);
		alpha.setPaintTicks(true);
		alpha.setPaintLabels(true);
		alpha.setBackground(null);
		alpha.setForeground(Color.white);
		alpha.addChangeListener(this);
		add(alpha);

	}

	// set sound settings
	private void setSound() {
		soundHeader.setBounds(960, 50, 280, 35);
		soundHeader.setFont(new Font("Consolas", Font.BOLD, 30));
		soundHeader.setForeground(Color.white);
		add(soundHeader);
		
		soundFile();
		soundEditor();
	}

	// set sound file display
	private void soundFile() {
		soundLabel.setBounds(960, 200, 280, 35);
		soundLabel.setFont(new Font("Consolas", Font.PLAIN, 30));
		soundLabel.setForeground(Color.white);
		add(soundLabel);
		
		soundControlLabel.setBounds(960, 300, 280, 35);
		soundControlLabel.setFont(new Font("Consolas", Font.PLAIN, 30));
		soundControlLabel.setForeground(Color.white);
		add(soundControlLabel);
		
		soundChooser.setBounds(1200, 200, 160, 35);
		soundChooser.setFont(new Font("Consolas", Font.PLAIN, 15));
		soundChooser.addActionListener(this);
		add(soundChooser);
		
		soundFileName.setBounds(1400, 200, 400, 35);
		soundFileName.setFont(new Font("Consolas", Font.PLAIN, 10));
		soundFileName.setForeground(Color.white);
		add(soundFileName);

	}
	
	// set up the sound controller
	private void soundEditor() {

		playButton.setBounds(960, 400, 160, 35);
		playButton.setFont(new Font("Consolas", Font.PLAIN, 15));

		pauseButton.setBounds(1160, 400, 160, 35);
		pauseButton.setFont(new Font("Consolas", Font.PLAIN, 15));

		muteButton.setBounds(1360, 400, 160, 35);
		muteButton.setFont(new Font("Consolas", Font.PLAIN, 15));

		loopButton.setBounds(1560, 400, 160, 35);
		loopButton.setFont(new Font("Consolas", Font.PLAIN, 15));

		ActionListener soundListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				if (event.getSource() == playButton) {
					backgroundSound.play();
				} else if (event.getSource() == pauseButton) {
					backgroundSound.stop();
				} else if (event.getSource() == muteButton) {
					backgroundSound.mute();
				} else if (event.getSource() == loopButton) {
					backgroundSound.loop();
				}

			}

		};

		playButton.addActionListener(soundListener);
		pauseButton.addActionListener(soundListener);
		muteButton.addActionListener(soundListener);
		loopButton.addActionListener(soundListener);

		add(playButton);
		add(pauseButton);
		add(muteButton);
		add(loopButton);

	}

	// sound effects
	private void playSoundEffect(String name) {
		Sound soundEffect = new Sound(name);
		soundEffect.play();
	}

	// set screen
	private void setPanel() {
		setSize(1920, 1060);
		setLayout(null);
		setBackground(new Color(0, 0, 0, 255));

	}

	// action listener
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == backgroundButton) { // if background button was clicked
			// find a file from user
			JFileChooser file_upload = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG, JPG & GIF Images", "jpg", "gif", "png");
			file_upload.setFileFilter(filter);
			int result = file_upload.showSaveDialog(null);
			
			if (result == JFileChooser.APPROVE_OPTION) { // get new file
				backgroundFile = file_upload.getSelectedFile();
				backgroundFileName.setText(backgroundFile.getAbsolutePath());
			}
			
			playSoundEffect("sound/anime-wow-sound-effect.wav"); // sound effect

		} else if (event.getSource() == soundChooser) { // if sound button was clicked
			// find a file from user
			JFileChooser file_upload = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Wave Audio File", "wav");
			file_upload.setFileFilter(filter);
			int result = file_upload.showSaveDialog(null);
			
			if (result == JFileChooser.APPROVE_OPTION) {
				soundFile = file_upload.getSelectedFile();
				backgroundSound = new Sound(soundFile.getAbsolutePath());
				soundFileName.setText(soundFile.getAbsolutePath());
			}
			
			playSoundEffect("sound/anime-wow-sound-effect.wav"); // sound effect
		}
	}

	@Override
	// read the sliders
	public void stateChanged(ChangeEvent event) {
		// for color sliders
	}

}
