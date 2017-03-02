import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.border.EtchedBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * GUI - handles user input with a graphical user interface
 * 
 * @author Timothy Couch, Will Hildreth, Daniel Johnson
 * @date 27 February 2017
 */
public class GUI {
	Controller controller;

	JTextArea messageArea;
	JTree tree;

	/**
	 * Initializes GUI with Controller supplied. Sets up the GUI.
	 * 
	 * @param c
	 *            Controller to use
	 */
	GUI(Controller c) {
		controller = c;

		// Main window
		JFrame mainFrame = new JFrame();
		mainFrame.setSize(800, 600);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setTitle("Email System - Theeungpohp");

		// top panel of stuff - Daniel
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		topPanel.setBorder(new EtchedBorder());

		// top panel of buttons
		JPanel buttonPanel = new JPanel();

		JButton composeButton = new JButton("Compose");
		buttonPanel.add(composeButton);
		composeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				// Compose message Pop-up code here
			}
		});
		
		JButton sendButton = new JButton("Send");
		buttonPanel.add(sendButton);
		sendButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				// Send message code here
			}
		});
		
		JButton replyButton = new JButton("Reply");
		buttonPanel.add(replyButton);
		replyButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				// Reply to message Pop-up code here
			}
		});
		
		JButton deleteButton = new JButton("Delete");
		buttonPanel.add(deleteButton);
		deleteButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				// Delete message code here
			}
		});
		
		topPanel.add(buttonPanel);
		//END TOP BUTTON PANEL CODE -Daniel
		
		// Adding temp 2nd button panel for JTree testing -Daniel
		JPanel buttonPanel2 = new JPanel();
		

		// testing - Please DO NOT REMOVE (Only comment out)
		JButton clearButton = new JButton("Clear Message");
		buttonPanel2.add(clearButton);
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				messageArea.setText("");
			}
		});

		JButton addNodeButton = new JButton("Add Node");
		buttonPanel2.add(addNodeButton);
		addNodeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				model.insertNodeInto(new DefaultMutableTreeNode("newChild"), selectedNode, selectedNode.getChildCount());
			}
		});

		JButton deleteNodeButton = new JButton("Delete Node");
		buttonPanel2.add(deleteNodeButton);
		deleteNodeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				tree.setSelectionPath(null);
				model.removeNodeFromParent(selectedNode);
			}
		});

		topPanel.add(buttonPanel2);

		mainFrame.add(topPanel, BorderLayout.NORTH);

		// center panel with hierarchy and message - Timothy Couch
		JSplitPane centerPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		// centerPanel.setResizeWeight(.5);

		// hierarchy panel
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());
		leftPanel.setBorder(new EtchedBorder());

		// root node
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Email - Theeungpohp");

		// users
		DefaultMutableTreeNode kaneNode = new DefaultMutableTreeNode("KaneStaff");
		kaneNode.add(new DefaultMutableTreeNode("Marth"));
		kaneNode.add(new DefaultMutableTreeNode("Link"));
		kaneNode.add(new DefaultMutableTreeNode("Fox"));

		root.add(kaneNode);
		root.add(new DefaultMutableTreeNode("Tieske"));
		root.add(new DefaultMutableTreeNode("Super Fang Chan"));

		// set up the tree and add a listener
		tree = new JTree(root);

		tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if (selectedNode != null)
				{
					//make arraylist of path
					ArrayList<Integer> pathList = new ArrayList<>();
					DefaultMutableTreeNode currNode = selectedNode;
					while (currNode.getParent() != null)
					{
						pathList.add(0, currNode.getParent().getIndex(currNode));
						currNode = (DefaultMutableTreeNode) currNode.getParent();
					}
					
					messageArea.append(pathList.toString());
					
					messageArea.append(e.getPath().toString() + ": ");
					messageArea.append(selectedNode.getUserObject().toString() + "\n");
				}
			}
		});

		leftPanel.add(tree, BorderLayout.CENTER);

		centerPanel.add(new JScrollPane(leftPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));

		// main email message area
		JPanel messagePanel = new JPanel();
		messagePanel.setLayout(new BorderLayout());
		messagePanel.setBorder(new EtchedBorder());
		messageArea = new JTextArea("sdlfkjsdkljsbsdbs\n\n\n\n\n\n\n\nsdf\n\n\n\n\n\n\nsdfsdfds");
		messageArea.setEditable(false);
		messageArea.setLineWrap(true);
		messageArea.setWrapStyleWord(true);
		messagePanel.add(new JScrollPane(messageArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
		centerPanel.add(messagePanel);

		mainFrame.add(centerPanel);

		// ending line
		mainFrame.setVisible(true);
	}
}
