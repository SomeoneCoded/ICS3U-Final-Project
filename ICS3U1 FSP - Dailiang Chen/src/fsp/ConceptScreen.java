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
// import statement
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

// concept screen
@SuppressWarnings("serial")
public class ConceptScreen extends JPanel implements ActionListener{
	// content area to display content
	JTextArea contentArea = new JTextArea();
	
	// make content be scrollable
	JScrollPane contentScroll = new JScrollPane(contentArea);
	
	// progress bar to show user progress
	JProgressBar progressBar = new JProgressBar(0, 100);
	
	// array of headers
	JLabel [] headerArray = new JLabel[9];
	
	// array of files
	File [] fileArray = new File[9];
	
	// page buttons
	JButton prevButton = new JButton();
	JButton nextButton = new JButton();
	
	// arrow icons 
	ImageIcon leftArrow =  new ImageIcon("images/Left Arrow.png");
	ImageIcon rightArrow = new ImageIcon("images/Right Arrow.png");
	
	//  page number
	int pageNumber = 1;
	
	// page label to display page number
	JLabel pageLabel; 
	
	// constructor method
	public ConceptScreen() {
		setProgressBar();
		setLabelArray();
		setFileArray();
		setTextArea();
		setHeader();
		setButtons();
		setPages();
		setPanel();
	}
	
	// create header array to store all topics covered
	private void setLabelArray() {
		for(int idx = 0; idx < headerArray.length; idx++) {
			headerArray[idx] = new JLabel();
			headerArray[idx].setFont(new Font("Consolas", Font.BOLD, 100));
			headerArray[idx].setForeground(Color.white);
			headerArray[idx].setBounds(960, 60, 1000, 890);
			
		}
		
		headerArray[1].setText("<html>Classes, Objects, Methods, Instance Variables</html>");
		headerArray[2].setText("<html>Declaring Classes with Methods and Objects</html>");
		headerArray[3].setText("<html>Declaring Methods with Parameters</html>");
		headerArray[4].setText("<html>Instance Variables, Setters & Getters</html>");
		headerArray[5].setText("<html>Primitive vs Reference</html>");
		headerArray[6].setText("<html>Objects with Constructors</html>");
		headerArray[7].setText("<html>Floating point and Doubles</html>");
		headerArray[8].setText("<html>Glossary</html>");
		
		
	}
	
	// create a file array to store all text file pages
	private void setFileArray() {
		for(int idx = 1; idx < fileArray.length; idx++) {
			fileArray[idx] = new File("concepts/Page " + idx);
		}
		
	}

	// set up a progress bar
	private void setProgressBar() {
		progressBar.setBounds(30, 10, 1840, 30);
		progressBar.setStringPainted(true);
		progressBar.setValue((int) ((double) ((double) pageNumber/(headerArray.length-1)) * 100));
		add(progressBar);
	}

	// set up the content placeholder
	private void setTextArea() {
		contentScroll.setBounds(30, 60, 900, 900);
		contentArea.setEditable(false);
		contentArea.setOpaque(false);
		contentArea.setText(""); // refresh text
		
		// read a text file and input that into the placeholder
		readFile();
		
		contentArea.setBackground(null);
		contentArea.setFont(new Font("Consolas", Font.PLAIN, 30));
		contentArea.setForeground(Color.white);
		contentArea.setBorder(null);
		contentArea.setLineWrap(true);
		contentArea.setWrapStyleWord(true); 
		contentScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		contentScroll.getViewport().setOpaque(false);
		contentScroll.setOpaque(false);
		contentScroll.setBorder(null);
	}

	// read files
	private void readFile() {
		// https://stackoverflow.com/questions/39196676/how-to-read-a-paragraph-from-a-file-in-java
		String text = "";
		try {
			Scanner inputFile = new Scanner(fileArray[pageNumber]).useDelimiter("\r\n  ");
			while(inputFile.hasNext()) {
				text = inputFile.next();
				contentArea.setText(contentArea.getText() + text);
			}
			
			inputFile.close();
		}
		catch(FileNotFoundException exception) {
			exception.printStackTrace();
		}
		
	}

	// set the header based on page number
	private void setHeader() {
		add(headerArray[pageNumber]);
	}
	
	// set the buttons
	private void setButtons() {
		prevButton.setIcon(leftArrow);
		nextButton.setIcon(rightArrow);
		
		prevButton.setBackground(null);
		nextButton.setBackground(null);
		
		prevButton.setBorder(null);
		nextButton.setBorder(null);
		
		prevButton.setOpaque(false);
		prevButton.setContentAreaFilled(false);
		prevButton.setBorderPainted(false);
		
		nextButton.setOpaque(false);
		nextButton.setContentAreaFilled(false);
		nextButton.setBorderPainted(false);
		
		prevButton.setBounds(1100, 900, 200, 90);
		nextButton.setBounds(1600, 900, 200, 90);
		
		prevButton.addActionListener(this);
		nextButton.addActionListener(this);
		
		add(prevButton);
		add(nextButton);
	}
	
	// set up displaying the page number
	private void setPages() {
		pageLabel = new JLabel(Integer.toString(pageNumber));
		pageLabel.setBounds(1430, 900, 100, 100);
		pageLabel.setFont(new Font("Consolas", Font.BOLD, 90));
		pageLabel.setForeground(Color.white);
		add(pageLabel);
	}

	// remove components to refresh the panel and display a new topic
	private void removeComponents() {
		remove(headerArray[pageNumber]);
		remove(pageLabel);
	}
	
	// sound effects
	private void playSoundEffect(String name) {
		Sound soundEffect = new Sound(name);
		soundEffect.play();
	}
	
	// set the screen
	private void setPanel() {
		setSize(1920, 1080);
		setLayout(null);
		setBackground(new Color(0,0,0,255));
		add(contentScroll);
	
	}
	
	// action listener
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == prevButton) { // if we go to the previous page
			if (pageNumber - 1 > 0) { // if it is within the boundaries, proceed
				removeComponents();
				pageNumber--;
				setProgressBar();
				setHeader();
				setTextArea();
				setPages();
				repaint();
				validate();
				playSoundEffect("sound/roblox-spawn.wav"); // sound effect

			} else // out of bounds
				playSoundEffect("sound/windows-xp-error.wav"); // sound effect

		} else if (event.getSource() == nextButton) { // if we go to the next page
			if (pageNumber + 1 < headerArray.length) { // if it is within the boundaries, proceed
				removeComponents();
				pageNumber++;
				setProgressBar();
				setHeader();
				setTextArea();
				setPages();
				repaint();
				validate();
				playSoundEffect("sound/roblox-spawn.wav"); // sound effect

			} else // out of bounds
				playSoundEffect("sound/windows-xp-error.wav"); // sound effect
		}

	}
}
