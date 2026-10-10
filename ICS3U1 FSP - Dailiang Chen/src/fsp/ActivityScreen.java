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
import java.awt.image.BufferedImage;

import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

// activity screen class
@SuppressWarnings("serial")
public class ActivityScreen extends JPanel implements ActionListener, ChangeListener{
	// Object edidtor headers
	JLabel header = new JLabel("Object Editor:");
	JLabel shapeLabel = new JLabel("Shape: ");
	JLabel colorLabel = new JLabel("Color (RGBA): ");
	JLabel sizeLabel = new JLabel("Size: ");
	JLabel actionLabel = new JLabel("Actions: ");
	
	// Possible shapes
	String[] shapes = {"Circle", "Triangle", "Rectangle", "Square", "Pentagon", "Hexagon", "Cylinder", "Cube", "Cone", "Sphere", "Pyramid", "Man", "Woman", "Tube"};
	// Stored in combo box
	JComboBox<String> shapeList =new JComboBox<String>(shapes);

	// RGBA values
	JSlider red = new JSlider(JSlider.HORIZONTAL, 0, 255, 255);
	JSlider green = new JSlider(JSlider.HORIZONTAL, 0, 255, 255);
	JSlider blue = new JSlider(JSlider.HORIZONTAL, 0, 255, 255);
	JSlider alpha = new JSlider(JSlider.HORIZONTAL, 0, 255, 102);
	
	// slider
	JSlider sizeSlider = new JSlider(JSlider.HORIZONTAL, 0, 200, 100);
	
	// shape image --> used for reading files
	BufferedImage shapeImage;		
	
	// image object (used to get attributes)
	ImageObject imageObject;
	
	// import image button
	JButton customImageButton = new JButton("Import");
	
	// shape that is being displayed
	Image shape;
	
	// holds the processed image
	JLabel display;
	
	// constructor method
	public ActivityScreen() {
		// starter shape
		try {
			shapeImage = ImageIO.read(new File("shape/Circle.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// default shape
		imageObject = new ImageObject(shapeImage, new Color(red.getValue(), green.getValue(), blue.getValue(), alpha.getValue()), sizeSlider.getValue());

		setObjectEditor(); // set up an object editor
		createShape();
		setPanel(); // set up the panel for this screen
	}
	
	// object editor method
	private void setObjectEditor() {
		header.setBounds(960, 50, 400, 80);
		header.setFont(new Font("Consolas", Font.BOLD, 50));
		header.setForeground(Color.white);
		add(header);
		shapeEditor();
		colorEditor();
		sizeEditor();
		
	}
	
	// shape editor
	private void shapeEditor() {
		shapeLabel.setBounds(960, 200, 280, 35);
		shapeLabel.setFont(new Font("Consolas", Font.PLAIN, 30));
		shapeLabel.setForeground(Color.white);
		add(shapeLabel);
		shapeList.setBounds(1100, 200, 300, 35);
		shapeList.addActionListener(this);
		add(shapeList);
		customImageButton.setBounds(1500, 200, 300, 35);
		customImageButton.setFont(new Font("Consolas", Font.PLAIN, 30));
		customImageButton.addActionListener(this);
		add(customImageButton);
		
	}
	
	// color editor
	private void colorEditor() {
		colorLabel.setBounds(960, 300, 280, 35);
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
		red.setBounds(960, 400, 600, 50);
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
		green.setBounds(960, 500, 600, 50);
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
		blue.setBounds(960, 600, 600, 50);
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
		alpha.setBounds(960, 700, 600, 50);
		alpha.setMajorTickSpacing(255);
		alpha.setMinorTickSpacing(51);
		alpha.setPaintTicks(true);
		alpha.setPaintLabels(true);
		alpha.setBackground(null);
		alpha.setForeground(Color.white);
		alpha.addChangeListener(this);
		add(alpha);
		
	}

	// size editor
	private void sizeEditor() {
		sizeLabel.setBounds(960, 800, 280, 35);
		sizeLabel.setFont(new Font("Consolas", Font.PLAIN, 30));
		sizeLabel.setForeground(Color.white);
		add(sizeLabel);
		setSizeSlider();
		
	}
	
	// set the size
	private void setSizeSlider() {
		
		sizeSlider.setBounds(1100, 800, 600, 50);
		sizeSlider.setMajorTickSpacing(100);
		sizeSlider.setMinorTickSpacing(50);
		sizeSlider.setPaintTicks(true);
		sizeSlider.setPaintLabels(true);
		sizeSlider.setBackground(null);
		sizeSlider.setForeground(Color.white);
		sizeSlider.addChangeListener(this);
		add(sizeSlider);
	}
	
	// create the shape based on given information of attributes of image object
	private void createShape() {
		colorImage(imageObject.getColor());
		sizeImage(imageObject.getSize());
		displayImage();
		
	}

	// read the image and display it
	private void readImage(String name) {
		try {
			shapeImage = ImageIO.read(new File("shape/" + name + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// colorize the image based on user input of the slider
	private void colorImage(Color color) {
		shapeImage = dye(imageObject.getShape(), color);
		
	}

	// https://stackoverflow.com/questions/21382966/colorize-a-picture-in-java
	// Give a different hue to the image
	private BufferedImage dye(BufferedImage image, Color color) {
		int w = image.getWidth();
		int h = image.getHeight();
		BufferedImage dyed = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = dyed.createGraphics();
		g.drawImage(image, 0, 0, w, h, null);
		g.setComposite(AlphaComposite.SrcAtop);
		g.setColor(color);
		g.fillRect(0, 0, w, h);
		g.dispose();
		return dyed;
	}

	// size the image depending on the slider
	private void sizeImage(int size) {
		double width = shapeImage.getWidth()*((double) size/100);
		
		double height = shapeImage.getHeight()*((double) size/100);
		
		shape = shapeImage.getScaledInstance((int) width, (int) height, Image.SCALE_DEFAULT);
		
	}
	
	// display the image once everything is processed
	private void displayImage() {
		display = new JLabel(new ImageIcon(shape)); 
		display.setBounds(0, 0, 960, 1080);
		add(display);
		
	}
	
	// process and update image
	private void processImage() {
		// update image object's attributes
		imageObject.setColor(new Color(red.getValue(), green.getValue(), blue.getValue(), alpha.getValue()));
		imageObject.setSize(sizeSlider.getValue());
		// update component
		remove(display);
		createShape();	
		repaint();
		revalidate();
	}
	
	private void playSoundEffect(String name) {
		Sound soundEffect = new Sound(name);
		soundEffect.play();
	}
	
	// set this screen's panel
	private void setPanel() {
		setSize(1920, 1080);
		setLayout(null);
		setBackground(new Color(0,0,0,255));
		
	}
	
	// action listener
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == shapeList) { // if the shape was a preset
			readImage(String.valueOf(shapeList.getSelectedItem())); // read image
			imageObject.setShape(shapeImage); // set image shape
			processImage(); // process the image
			playSoundEffect("sound/roblox-spawn.wav"); // sound effect after you click
			
		} else if (event.getSource() == customImageButton) { // if the shape is imported
			// set the shape null
			shapeImage = null;
			// upload a file from computer
			JFileChooser file_upload = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG, JPG & GIF Images", "jpg", "gif", "png"); // image supported format
			file_upload.setFileFilter(filter);
			int result = file_upload.showSaveDialog(null);
			if (result == JFileChooser.APPROVE_OPTION) {
				try {
					shapeImage = ImageIO.read(file_upload.getSelectedFile()); // new shape
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			imageObject.setShape(shapeImage); // set the shape image
			processImage(); // process the image
			playSoundEffect("sound/anime-wow-sound-effect.wav"); // sound effect
			
		}

	}

	// change listener
	@Override
	public void stateChanged(ChangeEvent event) {
		if (event.getSource() == red) { // if they change the red value
			processImage(); // update image
			
		} else if (event.getSource() == green) { // if they change the green value
			processImage(); // update image
			
		} else if (event.getSource() == blue) { // if they change the blue value
			processImage(); // update image
			
		} else if (event.getSource() == alpha) { // if they change the alpha value
			processImage(); // update image
			
		} else if (event.getSource() == sizeSlider) { // if they change the size
			processImage(); // update image
			
		}
		
	}

}
