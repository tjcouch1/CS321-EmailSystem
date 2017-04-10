import java.util.ArrayList;

/**
 * EmailClientSystem - handles execution of email actions
 * 
 * @author Will Hildreth, Timothy Couch
 *
 */
public class EmailClientSystem
{
	private ArrayList<User> users;
	
	public EmailClientSystem()
	{
		users = new ArrayList<>();
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
		if (userIndex < 0 || userIndex >= users.size())
			return null;
		return users.get(userIndex).getEmail(siteIndex, accountIndex, mailboxIndex, emailIndex);
	}

	/**
	 * deletes an email (moves to trash or deletes)
	 * 
	 * @param userIndex index of user
	 * @param siteIndex index of site
	 * @param accountIndex index of account
	 * @param mailboxIndex index of mailbox
	 * @param emailIndex index of email
	 * @return whether or not it deleted properly
	 * 
	 * @author Timothy Couch
	 */
	public Email deleteEmail(int userIndex, int siteIndex, int accountIndex, int mailboxIndex, int emailIndex)
	{
		if (userIndex < 0 || userIndex >= users.size())
			return null;
		return users.get(userIndex).deleteEmail(siteIndex, accountIndex, mailboxIndex, emailIndex);
	}

	/**
	 * adds a user
	 * 
	 * @param userName name for user
	 * @return new user
	 * 
	 * @author Timothy Couch
	 */
	public User addUser(String userName)
	{
		User u = new User(userName);
		users.add(u);
		return u;
	}

	/**
	 * adds an account
	 * 
	 * @param emailAddress address for account
	 * @param userIndex index of user to add to
	 * @param siteIndex index of site to add to
	 * @return new account
	 * 
	 * @author Timothy Couch
	 */
	public Account addAccount(String emailAddress, int userIndex, int siteIndex)
	{
		if (userIndex < 0 || userIndex >= users.size())
			return null;
		return users.get(userIndex).addAccount(emailAddress, siteIndex);
	}

	/**
	 * deletes an account
	 * @param userIndex user that holds the account
	 * @param siteIndex site that holds the account
	 * @param accountIndex the account index in the site
	 * @return the account deleted
	 */
	public Account deleteAccount(int userIndex, int siteIndex, int accountIndex)
	{
		if (userIndex < 0 || userIndex >= users.size())
			return null;
		return users.get(userIndex).deleteAccount(siteIndex, accountIndex);
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
		if (userIndex < 0 || userIndex >= users.size())
			return null;
		return users.remove(userIndex);
	}
	
	/**
	 * add email to appropriate mailboxes
	 * @param e email to send
	 * @author Daniel Johnson
	 */
	public void sendEmail(Email e)
	{
		for(User user:users)
		{
			user.sendEmail(e);
		}
	}
}
