import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

/**
 * GUI - handles user input with a graphical user interface
 * @author Timothy Couch, Will Hildreth, Daniel Johnson
 * @date 27 February 2017
 */
public class GUI
{
	Controller controller;
	
	JTextArea messageArea;
	
	/**
	 * Initializes GUI with Controller supplied. Sets up the GUI.
	 * @param c Controller to use
	 */
	GUI(Controller c)
	{
		controller = c;
		
		//Main window
		JFrame mainFrame = new JFrame();
		mainFrame.setSize(800, 600);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setTitle("Email System - Theeungpohp");
		
		//main email message area
		JPanel messagePanel = new JPanel();
		messagePanel.setLayout(new BorderLayout());
		messagePanel.setBorder(new EtchedBorder());
		messageArea = new JTextArea();
		messagePanel.add(messageArea, BorderLayout.CENTER);
		mainFrame.add(messagePanel, BorderLayout.CENTER);
		
		//top panel of stuff
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		topPanel.setBorder(new EtchedBorder());
		
		//top panel of buttons
		JPanel buttonPanel = new JPanel();
		
		JButton testButton = new JButton("Test 1");
		buttonPanel.add(testButton);
		
		topPanel.add(buttonPanel);
		
		mainFrame.add(topPanel, BorderLayout.NORTH);
		
		//left hierarchy panel
		JPanel leftPanel = new JPanel();
		leftPanel.setBorder(new EtchedBorder());
		
		testButton = new JButton("Test 2");
		leftPanel.add(testButton);
		
		mainFrame.add(leftPanel, BorderLayout.WEST);
		
		//ending line
		mainFrame.setVisible(true);
	}
}
