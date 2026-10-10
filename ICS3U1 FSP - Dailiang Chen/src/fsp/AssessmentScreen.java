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
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

import javax.swing.*;

// assessment screen
@SuppressWarnings("serial")
public class AssessmentScreen extends JPanel implements ActionListener {

	ButtonGroup[] questions = new ButtonGroup[11]; // stores the options per question
	JPanel[] questionPanel = new JPanel[11]; // array of panels that display questions with their corresponding answers
	JLabel[] questionLabel = new JLabel[11]; // array of questions
	JPanel questionHolder; // panel to hold the question displayed
	JRadioButton[][] option = new JRadioButton[11][5]; // array of question options --> [question][option number]

	ImageIcon leftArrow = new ImageIcon("images/Left Arrow.png"); // image for going back
	ImageIcon rightArrow = new ImageIcon("images/Right Arrow.png"); // image for going forward

	JButton prevButton = new JButton(); // previous button to go back to a question
	JButton nextButton = new JButton(); // next button to go to the next question

	JProgressBar progressBar = new JProgressBar(0, 100); // progress bar to show user progress

	CardLayout card = new CardLayout();// card layout to store questions

	JLabel pageLabel; // display page number

	JButton submitButton = new JButton("Submit"); // display a submit button that allows users to submit whenever they
													// want

	int pageNumber = 1; // page number

	// constructor method
	public AssessmentScreen() {
		setProgressBar();
		setQuestionHolder();
		setQuestions();
		setButtons();
		setPages();
		setPanel();
	}

	// set the progress bar based on number of questions and what question the user
	// is on
	private void setProgressBar() {
		progressBar.setBounds(30, 10, 1840, 30);
		progressBar.setStringPainted(true);
		progressBar.setValue((int) ((double) ((double) pageNumber / (questionPanel.length - 1)) * 100));
		add(progressBar);
	}

	// set up the array of question panels that will hold a question with its
	// corresponding answers
	private void setQuestionHolder() {
		setQuestionPanels();
		questionHolder = new JPanel();
		questionHolder.setLayout(card);
		questionHolder.setBounds(30, 60, 1960, 800);
		questionHolder.setBackground(null);
		questionHolder.add(questionPanel[1], "1");
		questionHolder.add(questionPanel[2], "2");
		questionHolder.add(questionPanel[3], "3");
		questionHolder.add(questionPanel[4], "4");
		questionHolder.add(questionPanel[5], "5");
		questionHolder.add(questionPanel[6], "6");
		questionHolder.add(questionPanel[7], "7");
		questionHolder.add(questionPanel[8], "8");
		questionHolder.add(questionPanel[9], "9");
		questionHolder.add(questionPanel[10], "10");

	}

	// initialize the panels and set a layut
	private void setQuestionPanels() {
		for (int idx = 1; idx <= 10; idx++) {
			questionPanel[idx] = new JPanel();
			questionPanel[idx].setLayout(new BoxLayout(questionPanel[idx], BoxLayout.Y_AXIS));
			questionPanel[idx].setBackground(null);
		}

	}

	// set up the questions
	private void setQuestions() {
		// action listener for options
		ActionListener optionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				playSoundEffect("sound/are-you-sure-about-that.wav"); // sound effect to play when an answer is clicked

			}
		};

		// create questions and answers based on a text file
		try {
			Scanner input = new Scanner(new File("assessment/quiz"));
			while (input.hasNext()) { // while there's more to input
				for (int idx = 1; idx <= 10; idx++) { // 10 questions
					questionLabel[idx] = new JLabel(input.nextLine());
					questionLabel[idx].setFont(new Font("Consolas", Font.PLAIN, 40));
					questionLabel[idx].setForeground(Color.white);

					questions[idx] = new ButtonGroup(); // button group for the question
					option[idx][1] = new JRadioButton(input.nextLine()); // option A
					option[idx][2] = new JRadioButton(input.nextLine()); // option B
					option[idx][3] = new JRadioButton(input.nextLine()); // option C
					option[idx][4] = new JRadioButton(input.nextLine()); // option D

					// set font
					option[idx][1].setFont(new Font("Consolas", Font.PLAIN, 30));
					option[idx][2].setFont(new Font("Consolas", Font.PLAIN, 30));
					option[idx][3].setFont(new Font("Consolas", Font.PLAIN, 30));
					option[idx][4].setFont(new Font("Consolas", Font.PLAIN, 30));

					// set foreground
					option[idx][1].setForeground(Color.white);
					option[idx][2].setForeground(Color.white);
					option[idx][3].setForeground(Color.white);
					option[idx][4].setForeground(Color.white);

					// null background
					option[idx][1].setBackground(null);
					option[idx][2].setBackground(null);
					option[idx][3].setBackground(null);
					option[idx][4].setBackground(null);

					// add action listener to options
					option[idx][1].addActionListener(optionListener);
					option[idx][2].addActionListener(optionListener);
					option[idx][3].addActionListener(optionListener);
					option[idx][4].addActionListener(optionListener);

					// add answers to button group
					questions[idx].add(option[idx][1]);
					questions[idx].add(option[idx][2]);
					questions[idx].add(option[idx][3]);
					questions[idx].add(option[idx][4]);

					// add question to panel
					questionPanel[idx].add(questionLabel[idx]);

					// add options to panel
					questionPanel[idx].add(option[idx][1]);
					questionPanel[idx].add(option[idx][2]);
					questionPanel[idx].add(option[idx][3]);
					questionPanel[idx].add(option[idx][4]);
				}
			}

		} catch (FileNotFoundException exception) { // IOException
			exception.printStackTrace();
		}

	}

	// set and display page number
	private void setPages() {
		pageLabel = new JLabel(Integer.toString(pageNumber));
		pageLabel.setBounds(1430, 900, 100, 100);
		pageLabel.setFont(new Font("Consolas", Font.BOLD, 90));
		pageLabel.setForeground(Color.white);
		add(pageLabel);
	}

	// set the buttons
	private void setButtons() {
		setSubmitButton();
		setPrevButton();
		setNextButton();
	}

	// set a submit button
	private void setSubmitButton() {
		submitButton.setBounds(40, 900, 200, 90);
		submitButton.setFont(new Font("Consolas", Font.PLAIN, 30));
		submitButton.addActionListener(this);
		add(submitButton);
	}

	// set a previous button
	private void setPrevButton() {
		prevButton.setIcon(leftArrow);
		prevButton.setBackground(null);
		prevButton.setBorder(null);
		prevButton.setOpaque(false);
		prevButton.setContentAreaFilled(false);
		prevButton.setBorderPainted(false);
		prevButton.setBounds(1100, 900, 200, 90);
		prevButton.addActionListener(this);
		add(prevButton);

	}

	// set a next button
	private void setNextButton() {
		nextButton.setIcon(rightArrow);
		nextButton.setBackground(null);
		nextButton.setBorder(null);
		nextButton.setOpaque(false);
		nextButton.setContentAreaFilled(false);
		nextButton.setBorderPainted(false);
		nextButton.setBounds(1600, 900, 200, 90);
		nextButton.addActionListener(this);
		add(nextButton);
	}

	// remove old components
	private void removeComponents() {
		remove(progressBar);
		remove(pageLabel);
	}

	// sound effect
	private void playSoundEffect(String name) {
		Sound soundEffect = new Sound(name);
		soundEffect.play();
	}

	// set up the screen
	private void setPanel() {
		setSize(1920, 1080);
		setBackground(new Color(0, 0, 0, 255));
		setLayout(null);
		add(questionHolder);
		setVisible(true);
	}

	// action listener
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == prevButton) { // if the previous button is pressed
			if (pageNumber - 1 > 0) { // if we're within the page limit
				removeComponents();
				pageNumber--;
				setProgressBar();
				setPages();
				card.show(questionHolder, Integer.toString(pageNumber)); // show the previous question
				repaint();
				validate();
				playSoundEffect("sound/roblox-spawn.wav"); // sound effect

			} else { // out of bounds
				playSoundEffect("sound/windows-xp-error.wav"); // sound effect

			}

		} else if (event.getSource() == nextButton) { // if the next button is pressed
			if (pageNumber + 1 < questionPanel.length) { // if within the page limit
				removeComponents();
				pageNumber++;
				setProgressBar();
				setPages();
				card.show(questionHolder, Integer.toString(pageNumber)); // show the next question
				repaint();
				validate();
				playSoundEffect("sound/roblox-spawn.wav"); // sound effect

			} else { // out of bounds
				playSoundEffect("sound/windows-xp-error.wav"); // sound effect

			}

		} else if (event.getSource() == submitButton) { // if the user decides to submit
			// Note: Questions have been generated online and have been reviewed by me.
			int correctAnswer = 0; // tracks the correct answers

			if (option[1][3].isSelected()) // question 1
				correctAnswer++; // correct
			else {
				// incorrect
				playSoundEffect("sound/roblox-oof.wav");
				JOptionPane.showMessageDialog(this, "Check Question 1");

			}

			if (option[2][1].isSelected()) // question 2
				correctAnswer++; // correct
			else {
				// incorrect
				playSoundEffect("sound/roblox-oof.wav");
				JOptionPane.showMessageDialog(this, "Check Question 2");

			}

			if (option[3][1].isSelected()) // question 3
				correctAnswer++; // correct
			else {
				// incorrect
				playSoundEffect("sound/roblox-oof.wav");
				JOptionPane.showMessageDialog(this, "Check Question 3");

			}

			if (option[4][1].isSelected()) // question 4
				correctAnswer++; // correct
			else {
				// incorrect
				playSoundEffect("sound/roblox-oof.wav");
				JOptionPane.showMessageDialog(this, "Check Question 4");

			}

			if (option[5][2].isSelected()) // question 5
				correctAnswer++; // correct
			else {
				// incorrect
				playSoundEffect("sound/roblox-oof.wav");
				JOptionPane.showMessageDialog(this, "Check Question 5");

			}

			if (option[6][1].isSelected()) // question 6
				correctAnswer++; // correct
			else {
				// incorrect
				playSoundEffect("sound/roblox-oof.wav");
				JOptionPane.showMessageDialog(this, "Check Question 6");

			}

			if (option[7][3].isSelected()) // question 7
				correctAnswer++; // correct
			else {
				// incorrect
				playSoundEffect("sound/roblox-oof.wav");
				JOptionPane.showMessageDialog(this, "Check Question 7");

			}

			if (option[8][4].isSelected()) // question 8
				correctAnswer++; // correct
			else {
				// incorrect
				playSoundEffect("sound/roblox-oof.wav");
				JOptionPane.showMessageDialog(this, "Check Question 8");

			}

			if (option[9][2].isSelected()) // question 9
				correctAnswer++; // correct
			else {
				// incorrect
				playSoundEffect("sound/roblox-oof.wav");
				JOptionPane.showMessageDialog(this, "Check Question 9");

			}

			if (option[10][1].isSelected()) // question 10
				correctAnswer++; // correct
			else {
				// incorrect
				playSoundEffect("sound/roblox-oof.wav");
				JOptionPane.showMessageDialog(this, "Check Question 10");

			}

			// show how many answers they got right
			playSoundEffect("sound/result-sound.wav"); // sound effect
			JOptionPane.showMessageDialog(this,
					"Your Mark: " + correctAnswer + " out of " + (questionPanel.length - 1));
			// reset answers if they got everything correct
			if (correctAnswer == 10) {
				playSoundEffect("sound/congratulations.wav"); // sound effect
				for (int question = 1; question <= 10; question++) {
					for (int choice = 1; choice <= 4; choice++) {
						option[question][choice].setSelected(false); // deselect
					}
					questions[question].clearSelection(); // clear selection
				}
			}

		}

	}
}
