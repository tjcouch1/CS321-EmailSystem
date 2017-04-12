import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

/**
 * GUI - handles user input with a graphical user interface
 * 
 * @author Timothy Couch, Will Hildreth, Daniel Johnson
 * @date 27 February 2017
 */
public class GUI
{
	Controller controller;

	JTextArea messageArea;
	JTree tree;

	/**
	 * Initializes GUI with Controller supplied. Sets up the GUI.
	 * 
	 * @param c Controller to use
	 */
	GUI(Controller c)
	{
		controller = c;

		// Main window
		JFrame mainFrame = new JFrame();
		mainFrame.setSize(800, 600);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setTitle("Email System - Theeungpohp");

		// top menu thing
		JMenuBar menuBar = new JMenuBar();

		JMenu accountMenu = new JMenu("Account");

		JMenuItem addAccountItem = new JMenuItem("Add Account");
		addAccountItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				//Code by Will Hildreth
				String input = JOptionPane.showInputDialog("Add Account:");
				
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if (selectedNode != null)
				{
					ArrayList<Integer> pathList = treeNodePath(selectedNode);
					if (pathList.size() == 2) // a site
					{
						DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getModel().getRoot();
						addAccountToTree(controller.addAccount(input, pathList.get(0), pathList.get(1)),
									(DefaultMutableTreeNode) root.getChildAt(pathList.get(0)).getChildAt(pathList.get(1)));
					}
				}
			}
		});
		accountMenu.add(addAccountItem);
		JMenuItem removeAccountItem = new JMenuItem("Remove Account");
		removeAccountItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				// remove account code by Timothy Couch
				DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if (selectedNode != null)
				{
					ArrayList<Integer> pathList = treeNodePath(selectedNode);
					if (pathList.size() == 3)//it is an account
					{
						Account deleted = deleteAccount(pathList.get(0), pathList.get(1), pathList.get(2));
						if (deleted != null)
						{
							tree.setSelectionPath(null);
							model.removeNodeFromParent(selectedNode);
						}
					}
				}
			}
		});
		accountMenu.add(removeAccountItem);

		menuBar.add(accountMenu);

		JMenu userMenu = new JMenu("User");

		JMenuItem addUserItem = new JMenuItem("Add User");
		addUserItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				//Code by Will Hildreth
				String input = JOptionPane.showInputDialog("Add User:");
	
				DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getModel().getRoot();
				addUserToTree(controller.addUser(input),
							(DefaultMutableTreeNode) root);
				
				tree.expandRow(0);
			}
		});
		userMenu.add(addUserItem);
		JMenuItem removeUserItem = new JMenuItem("Remove User");
		removeUserItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				// remove account code by Will Hildreth
				DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if (selectedNode != null)
				{
					ArrayList<Integer> pathList = treeNodePath(selectedNode);
					if (pathList.size() == 1)//it is a user
					{
						User deleted = deleteUser(pathList.get(0));
						if (deleted != null)
						{
							tree.setSelectionPath(null);
							model.removeNodeFromParent(selectedNode);
						}
					}
				}
			}
		});
		userMenu.add(removeUserItem);

		menuBar.add(userMenu);

		// finish and add menubar
		mainFrame.setJMenuBar(menuBar);

		// top panel of stuff - Daniel
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		topPanel.setBorder(new EtchedBorder());

		// top panel of buttons
		JPanel buttonPanel = new JPanel();

		JButton composeButton = new JButton("Compose");
		buttonPanel.add(composeButton);
		composeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				// Compose message Pop-up code by Daniel Johnson
				
				DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
				DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if(selectedNode != null)
				{
					ArrayList<Integer> pathList = treeNodePath(selectedNode);
					if(pathList.size() >= 3) // in a specific email account
					{
						DefaultMutableTreeNode theNode = (DefaultMutableTreeNode) root.getChildAt(pathList.get(0)).getChildAt(pathList.get(1)).getChildAt(pathList.get(2)); // node for current account
						JDialog composeDialog = new JDialog(mainFrame, "Compose New Email", true);
						composeDialog.setSize(500, 400);
						
						// Add Text field
						JPanel messagePanel = new JPanel();
						messagePanel.setLayout(new BorderLayout());
						messagePanel.setBorder(new EtchedBorder());
						JTextArea messageArea = new JTextArea("");
						messageArea.setEditable(true);
						messageArea.setLineWrap(true);
						messageArea.setWrapStyleWord(true);
						
						
						messagePanel.add(new JScrollPane(messageArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
								JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
						composeDialog.add(messagePanel, BorderLayout.CENTER);
						
						// Add address field and send button
						JPanel topPanel = new JPanel();
						topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
						topPanel.setBorder(new EtchedBorder());
						
						JPanel messagePane2 = new JPanel();
						messagePane2.setLayout(new BorderLayout());
						messagePane2.setBorder(new EtchedBorder());
						JTextArea toField = new JTextArea("");
						toField.setEditable(true);
						toField.setLineWrap(true);
						
						// label
						JLabel toLabel = new JLabel("To:", JLabel.LEADING);
						toLabel.setLabelFor(toField);
						toLabel.setOpaque(true);
						
						JLabel fromLabel = new JLabel("From: " + theNode.getUserObject().toString());
						fromLabel.setOpaque(true);
						
						messagePane2.add(fromLabel, BorderLayout.NORTH);
						messagePane2.add(toLabel, BorderLayout.CENTER);
						messagePane2.add(toField, BorderLayout.SOUTH);
						topPanel.add(messagePane2);
						
						JPanel buttonPanel = new JPanel();
						
						JButton sendButton = new JButton("Send");
						buttonPanel.add(sendButton);
						sendButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e)
							{ 
								Email email = new Email(messageArea.getText(),theNode.getUserObject().toString() , toField.getText());
								controller.sendEmail(email);
								addEmailToTree(email, root);
								messageArea.setText("SENT");
								toField.setText("");
							}
						});
						
						topPanel.add(buttonPanel);
						composeDialog.add(topPanel, BorderLayout.NORTH);
						
						composeDialog.setVisible(true);
					}
				}
			}
			
		});

		JButton replyButton = new JButton("Reply");
		buttonPanel.add(replyButton);
		replyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				// Reply to message Pop-up code by Daniel Johnson
				DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
				DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if(selectedNode != null)
				{
					ArrayList<Integer> pathList = treeNodePath(selectedNode);
					if(pathList.size() == 5) // in a specific email account
					{
						Email currentEmail = getEmail(pathList.get(0), pathList.get(1), pathList.get(2), pathList.get(3), pathList.get(4)); // get selected email
						DefaultMutableTreeNode theNode = (DefaultMutableTreeNode) root.getChildAt(pathList.get(0)).getChildAt(pathList.get(1)).getChildAt(pathList.get(2));
						JDialog composeDialog = new JDialog(mainFrame, "Compose New Reply", true);
						composeDialog.setSize(500, 400);
						
						// Add Text field
						JPanel messagePanel = new JPanel();
						messagePanel.setLayout(new BorderLayout());
						messagePanel.setBorder(new EtchedBorder());
						JTextArea messageArea = new JTextArea("");
						messageArea.setEditable(true);
						messageArea.setLineWrap(true);
						messageArea.setWrapStyleWord(true);
						
						
						messagePanel.add(new JScrollPane(messageArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
								JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
						composeDialog.add(messagePanel, BorderLayout.CENTER);
						
						// Add address field and send button
						JPanel topPanel = new JPanel();
						topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
						topPanel.setBorder(new EtchedBorder());
						
						JPanel messagePane2 = new JPanel();
						messagePane2.setLayout(new BorderLayout());
						messagePane2.setBorder(new EtchedBorder());
						JTextArea toField = new JTextArea(currentEmail.getSender());
						toField.setEditable(false);
						toField.setLineWrap(true);
						
						// label
						JLabel toLabel = new JLabel("To:", JLabel.LEADING);
						toLabel.setLabelFor(toField);
						toLabel.setOpaque(true);
						
						JLabel fromLabel = new JLabel("From: " + theNode.getUserObject().toString());
						fromLabel.setOpaque(true);
						
						messagePane2.add(fromLabel, BorderLayout.NORTH);
						messagePane2.add(toLabel, BorderLayout.CENTER);
						messagePane2.add(toField, BorderLayout.SOUTH);
						topPanel.add(messagePane2);
						
						JPanel buttonPanel = new JPanel();
						
						JButton sendButton = new JButton("Send");
						buttonPanel.add(sendButton);
						sendButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e)
							{ 
								Email email = new Email(messageArea.getText(),theNode.getUserObject().toString() , toField.getText());
								controller.sendEmail(email);
								addEmailToTree(email, root);
								messageArea.setText("SENT");
							}
						});
						
						topPanel.add(buttonPanel);
						composeDialog.add(topPanel, BorderLayout.NORTH);
						
						composeDialog.setVisible(true);
					}
				}
			}
		});

		JButton deleteButton = new JButton("Delete");
		buttonPanel.add(deleteButton);
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				// Delete code by Timothy Couch
				DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if (selectedNode != null)
				{
					ArrayList<Integer> pathList = treeNodePath(selectedNode);
					if (pathList.size() == 5)//it is an email
					{
						Email deleted = deleteEmail(pathList.get(0), pathList.get(1), pathList.get(2), pathList.get(3),
								pathList.get(4));
						if (deleted != null)
						{
							if (pathList.get(3) != 2)//if mailbox is not trash
							{
								MutableTreeNode trashNode = (MutableTreeNode) selectedNode.getParent().getParent()
										.getChildAt(2);
								model.insertNodeInto(
										new DefaultMutableTreeNode(selectedNode.getUserObject().toString()), trashNode,
										trashNode.getChildCount());
							}
							tree.setSelectionPath(null);
							model.removeNodeFromParent(selectedNode);
						}
					}
				}
				/*
				selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if (selectedNode != null)
				{
					tree.setSelectionPath(null);
					model.removeNodeFromParent(selectedNode);
				}
				*/
			}
		});

		topPanel.add(buttonPanel);
		// END TOP BUTTON PANEL CODE -Daniel

		// Adding temp 2nd button panel for JTree testing -Daniel
		/*
		JPanel buttonPanel2 = new JPanel();

		// testing - Please DO NOT REMOVE (Only comment out)
		JButton clearButton = new JButton("Clear Message");
		buttonPanel2.add(clearButton);
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				messageArea.setText("");
			}
		});

		JButton addNodeButton = new JButton("Add Node");
		buttonPanel2.add(addNodeButton);
		addNodeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if (selectedNode != null)
					model.insertNodeInto(new DefaultMutableTreeNode("newChild"), selectedNode,
							selectedNode.getChildCount());
			}
		});

		JButton deleteNodeButton = new JButton("Delete Node");
		buttonPanel2.add(deleteNodeButton);
		deleteNodeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if (selectedNode != null)
				{
					tree.setSelectionPath(null);
					model.removeNodeFromParent(selectedNode);
				}
			}
		});

		JButton addTestTreeButton = new JButton("Add Test Tree");
		buttonPanel2.add(addTestTreeButton);
		addTestTreeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				createTestingTree();
			}
		});

		topPanel.add(buttonPanel2);
		*/
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

		//set up default tree for testing
		//createTestingTree(root);

		// set up the tree and add a listener
		tree = new JTree(root);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

		//listener for clicking on nodes in tree
		tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e)
			{
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if (selectedNode != null)
				{
					// make arraylist of path
					ArrayList<Integer> pathList = treeNodePath(selectedNode);

					if (pathList.size() == 5)//Open email - Timothy Couch
					{
						Email email = getEmail(pathList.get(0), pathList.get(1), pathList.get(2), pathList.get(3),
								pathList.get(4));
						messageArea.setText("Sender: " + email.getSender() + "\nRecipient: " + email.getReceiver()
								+ "\nTime Stamp: " + email.getTimeStamp() + "\n\n\n" + email.getMessage());
					}
					else // close email - Daniel Johnson
						messageArea.setText("");
					/*Good code to see how to access various parts of the tree
					messageArea.append(pathList.toString()); //[0, 2]
					
					messageArea.append(e.getPath().toString() + ": "); //[EmailSystem - Theeungpohp, KaneStaff, Link]: 
					messageArea.append(selectedNode.getUserObject().toString() + "\n"); //Link
					*/
				}
				else // close email - Daniel Johnson
					messageArea.setText("");
			}
		});

		leftPanel.add(tree, BorderLayout.CENTER);

		centerPanel.add(new JScrollPane(leftPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));

		// main email message area
		JPanel messagePanel = new JPanel();
		messagePanel.setLayout(new BorderLayout());
		messagePanel.setBorder(new EtchedBorder());
		messageArea = new JTextArea("");
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

	/**
	 * retrieves an email at specified spot
	 * 
	 * @param userIndex index of user
	 * @param siteIndex index of site
	 * @param accountIndex index of account
	 * @param mailboxIndex index of mailbox
	 * @param emailIndex index of email to retrieve
	 * @return email to retrieve
	 * 
	 * @author Timothy Couch
	 */
	public Email getEmail(int userIndex, int siteIndex, int accountIndex, int mailboxIndex, int emailIndex)
	{
		return controller.getEmail(userIndex, siteIndex, accountIndex, mailboxIndex, emailIndex);
	}

	/**
	 * deletes an email (moves to trash or deletes)
	 * 
	 * @param userIndex index of user
	 * @param siteIndex index of site
	 * @param accountIndex index of account
	 * @param mailboxIndex index of mailbox
	 * @param emailIndex index of email
	 * @return the email deleted
	 * 
	 * @author Timothy Couch
	 */
	public Email deleteEmail(int userIndex, int siteIndex, int accountIndex, int mailboxIndex, int emailIndex)
	{
		return controller.deleteEmail(userIndex, siteIndex, accountIndex, mailboxIndex, emailIndex);
	}

	/**
	 * deletes an account
	 * 
	 * @param userIndex user that holds the account
	 * @param siteIndex site that holds the account
	 * @param accountIndex the account index in the site
	 * @return the account deleted
	 *
	 * @author Timothy Couch
	 */
	public Account deleteAccount(int userIndex, int siteIndex, int accountIndex)
	{
		return controller.deleteAccount(userIndex, siteIndex, accountIndex);
	}
	
	/**
	* deletes a user
	*
	* @param userIndex the user index
	* @return the user deleted
	*
	* @author Will Hildreth
	*/
	public User deleteUser(int userIndex)
	{
		return controller.deleteUser(userIndex);
	}
	
	/**
	* adds an account
	*
	* @param email address to add
	* @param userIndex index of user to add to
	* @param siteIndex index of site to add to
	* @return the account added
	*
	* @author Will Hildreth
	*/
	public Account addAccount(String emailAddress, int userIndex, int siteIndex)
	{
		return controller.addAccount(emailAddress, userIndex, siteIndex);
	}
	
	/**
	* adds a user
	*
	* @param user name to add
	* @return the user added
	*
	* @author Will Hildreth
	*/
	public User addUser(String userName)
	{
			return controller.addUser(userName);
	}

	/**
	 * gets an arrayList of the path of integers to a node
	 * 
	 * @param n node to get the path to
	 * @return integer path to node
	 * 
	 * @author Timothy Couch
	 */
	private ArrayList<Integer> treeNodePath(DefaultMutableTreeNode n)
	{
		ArrayList<Integer> pathList = new ArrayList<>();
		while (n.getParent() != null)
		{
			pathList.add(0, n.getParent().getIndex(n));
			n = (DefaultMutableTreeNode) n.getParent();
		}
		return pathList;
	}
	
	/**
	 * sets up a testing setup
	 * 
	 * @precondition tree must be fully initialized
	 * 
	 * @author Timothy Couch
	 */
	public void createTestingTree()//DefaultMutableTreeNode root)
	{
		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
		// KaneStaff-
		addUserToTree(controller.addUser("KaneStaff"), root);
		//   Local Site-
		//     kane@local.edu-
		Account kaneLocal1 = addAccountToTree(controller.addAccount("kane@local.edu", 0, 0),
				(DefaultMutableTreeNode) root.getChildAt(0).getChildAt(0));
		//       Inbox-
		//         Email from tie@local.net
		//       Sent-
		//         Email to tie@local.net
		//         Email to kane@remote.gov
		//       Trash-
		//     staff@local.com-
		Account kaneLocal2 = addAccountToTree(controller.addAccount("staff@local.com", 0, 0),
				(DefaultMutableTreeNode) root.getChildAt(0).getChildAt(0));
		//       Inbox-
		//         Email from tie@remote.swag
		//       Sent-
		//       Trash-
		//   Remote Site-
		//     kane@remote.gov-
		Account kaneRemote1 = addAccountToTree(controller.addAccount("kane@remote.gov", 0, 1),
				(DefaultMutableTreeNode) root.getChildAt(0).getChildAt(1));
		//       Inbox-
		//         Email from kane@local.edu
		//       Sent-
		//         Email to ske@remote.edu
		//       Trash-
		// Tieske-
		addUserToTree(controller.addUser("Tieske"), root);
		//   Local Site-
		//     tie@local.net-
		Account tieLocal1 = addAccountToTree(controller.addAccount("tie@local.net", 1, 0),
				(DefaultMutableTreeNode) root.getChildAt(1).getChildAt(0));
		//       Inbox-
		//         Email from kane@local.edu
		//       Sent-
		//         Email to kane@local.edu
		//       Trash-
		//   Remote Site-
		//     tie@remote.swag-
		Account tieRemote1 = addAccountToTree(controller.addAccount("tie@remote.swag", 1, 1),
				(DefaultMutableTreeNode) root.getChildAt(1).getChildAt(1));
		//       Inbox-
		//       Sent-
		//         Email to staff@local.com
		//       Trash-
		//     ske@remote.edu-
		Account tieRemote2 = addAccountToTree(controller.addAccount("ske@remote.edu", 1, 1),
				(DefaultMutableTreeNode) root.getChildAt(1).getChildAt(1));
		//       Inbox-
		//         Email from kane@remote.gov
		//       Sent-
		//       Trash-

		//kaneLocal1
		//         Email from tieLocal1-
		//         Email to tieLocal1-
		Email e = new Email("Hi, tieLocal1\nFrom kaneLocal1", kaneLocal1.getEmailAddress(),
				tieLocal1.getEmailAddress());
		kaneLocal1.sendEmail(e);
		tieLocal1.sendEmail(e);
		addEmailToTree(e, root);
		//         Email to kaneRemote1-
		e = new Email("Hi, kaneRemote1\nFrom kaneLocal1", kaneLocal1.getEmailAddress(), kaneRemote1.getEmailAddress());
		kaneLocal1.sendEmail(e);
		kaneRemote1.sendEmail(e);
		addEmailToTree(e, root);
		//kaneLocal2
		//         Email from tieRemote1-
		//kaneRemote1
		//         Email from kaneLocal1-
		//         Email to tieRemote2-
		e = new Email("Hi, tieRemote2\nFrom kaneRemote1", kaneRemote1.getEmailAddress(), tieRemote2.getEmailAddress());
		kaneRemote1.sendEmail(e);
		tieRemote2.sendEmail(e);
		addEmailToTree(e, root);
		//tieLocal1
		//         Email from kaneLocal1-
		//         Email to kaneLocal1-
		e = new Email("Hi, kaneLocal1\nFrom tieLocal1", tieLocal1.getEmailAddress(), kaneLocal1.getEmailAddress());
		kaneLocal1.sendEmail(e);
		tieLocal1.sendEmail(e);
		addEmailToTree(e, root);
		//tieRemote1
		//         Email to kaneLocal2-
		e = new Email("Hi, kaneLocal2\nFrom tieRemote1", tieRemote1.getEmailAddress(), kaneLocal2.getEmailAddress());
		kaneLocal2.sendEmail(e);
		tieRemote1.sendEmail(e);
		addEmailToTree(e, root);
		//tieRemote2
		//         Email from kaneRemote1-
		
		tree.expandRow(0);
	}

	/**
	 * adds a user to the tree at the node specified
	 * 
	 * @param u user to add (along with his sites)
	 * @param root node from which to add the user
	 * @return same user
	 * 
	 * @precondition tree must be fully initialized
	 * 
	 * @author Timothy Couch
	 */
	private User addUserToTree(User u, DefaultMutableTreeNode root)
	{
		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();

		DefaultMutableTreeNode userNode = new DefaultMutableTreeNode(u.getName());
		//userNode.add(new DefaultMutableTreeNode("Local Site"));
		//userNode.add(new DefaultMutableTreeNode("Remote Site"));
		//root.add(userNode);

		if (root != null)
		{
			model.insertNodeInto(userNode, root, root.getChildCount());
			model.insertNodeInto(new DefaultMutableTreeNode("Local Site"), userNode, userNode.getChildCount());
			model.insertNodeInto(new DefaultMutableTreeNode("Remote Site"), userNode, userNode.getChildCount());
		}

		return u;
	}

	/**
	 * adds an account to the tree at the site specified
	 * 
	 * @param a account to add (along with mailboxes)
	 * @param siteNode node from which to add account
	 * @return same account
	 * 
	 * @precondition tree must be fully initialized
	 * 
	 * @author Timothy Couch
	 */
	private Account addAccountToTree(Account a, DefaultMutableTreeNode siteNode)
	{
		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();

		DefaultMutableTreeNode accountNode = new DefaultMutableTreeNode(a.getEmailAddress());
		//accountNode.add(new DefaultMutableTreeNode("Inbox"));
		//accountNode.add(new DefaultMutableTreeNode("Sent"));
		//accountNode.add(new DefaultMutableTreeNode("Trash"));
		//siteNode.add(accountNode);

		if (siteNode != null)
		{
			model.insertNodeInto(accountNode, siteNode, siteNode.getChildCount());
			model.insertNodeInto(new DefaultMutableTreeNode("Inbox"), accountNode, accountNode.getChildCount());
			model.insertNodeInto(new DefaultMutableTreeNode("Sent"), accountNode, accountNode.getChildCount());
			model.insertNodeInto(new DefaultMutableTreeNode("Trash"), accountNode, accountNode.getChildCount());
		}

		return a;
	}

	/**
	 * adds an email to the tree on the sender and the receiver's accounts
	 * 
	 * @param e email to add
	 * @param root root of the tree
	 * @return same email
	 * 
	 * @precondition tree must be fully initialized
	 * 
	 * @author Timothy Couch
	 */
	private Email addEmailToTree(Email e, DefaultMutableTreeNode root)
	{
		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();

		int emailsSent = 0;
		for (int i = 0; i < root.getChildCount(); i++)
		{
			if (emailsSent >= 2)
				break;
			MutableTreeNode userNode = (MutableTreeNode) root.getChildAt(i);
			for (int j = 0; j < userNode.getChildCount(); j++)
			{
				if (emailsSent >= 2)
					break;
				MutableTreeNode siteNode = (MutableTreeNode) userNode.getChildAt(j);
				for (int k = 0; k < siteNode.getChildCount(); k++)
				{
					if (emailsSent >= 2)
						break;
					DefaultMutableTreeNode accountNode = (DefaultMutableTreeNode) siteNode.getChildAt(k);
					if (accountNode.getUserObject().toString() == e.getSender())//the account is the sender
					{
						DefaultMutableTreeNode sentBoxNode = (DefaultMutableTreeNode) accountNode.getChildAt(1);
						//sentBoxNode.add(new DefaultMutableTreeNode("To " + e.getReceiver() + " at " + e.getTimeStamp()));

						if (sentBoxNode != null)
							model.insertNodeInto(
									new DefaultMutableTreeNode("To " + e.getReceiver() + " at " + e.getTimeStamp()),
									sentBoxNode, sentBoxNode.getChildCount());
					}
					else if (accountNode.getUserObject().toString().equals(e.getReceiver()))//the account is the receiver
					{
						DefaultMutableTreeNode inboxNode = (DefaultMutableTreeNode) accountNode.getChildAt(0);
						//inboxNode.add(new DefaultMutableTreeNode("From " + e.getSender() + " at " + e.getTimeStamp()));

						if (inboxNode != null)
							model.insertNodeInto(
									new DefaultMutableTreeNode("From " + e.getSender() + " at " + e.getTimeStamp()),
									inboxNode, inboxNode.getChildCount());
					}
				}
			}
		}

		//selectedNode.getUserObject().toString()

		return e;
	}
}
