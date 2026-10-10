package fsp;

//Name: Dailiang Chen
//Date: June 14, 2024
//Course Code: ICS3U1-01
//Title: FSP - Dailiang Chen
//Description:
//This program teaches the users the concepts of Objects and Classes in Java.
// To learn more about this topic, users can access the concept screen and read the content at their own pace
// If the user wants a visualization of how objects work, they can check out the activity and see what happens to an object when you modify its attributes
// If the user wants to test their knowledge, they can visit the assessment page. There are 10 multiple choice questions. The user can retry if they fail.
//Features:
// Menu bar setup top left to access any screen from anywhere
// Screens are in a card layout so they transition smoothly and don't require a new instance of them
// Audio tutorial from me!
// Sound effects. A lot of sound effects...
// Settings page to modify the program's background and to import background audio (with a built-in sound controller)
//Major Skills:
// Methods, Objects, Classes, Java Swing, GUI, Frames, Panels, Card Layout, Imports, Arrays, Event listeners, File readers, Images, Buttons, Menubar, Sliders, ProgressBar
//Areas of Concern:
// Audio tutorials can overlap
// Background settings are not functional yet
// GIFs do not animate when you import it to the activity
// Make sure to turn down your volume a little as there will be a lot of sound effects
// Audio controls may not work properly. E.g. Cannot be unpaused once paused

// Application class
public class FSPApplication {

	// method to play sound
	private static void playSoundEffect(String name) {
		Sound soundEffect = new Sound(name);
		soundEffect.play();
	}
	// main method
	public static void main(String[] args) {
		// sound effect indicating the start up
		playSoundEffect("sound/windows-xp-startup.wav");
		// playSoundEffect("voice/FSP-Introduction.wav"); <-- gets overlapped
		// open a new screen
		new GUIScreen();
		// explain to the user what they first see in front of them (main menu)
		playSoundEffect("voice/FSP-Main Menu.wav");
	}

}
