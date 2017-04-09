
/**
 * Controller - handles interfacing GUI and EmailClientSystem
 * 
 * @author Timothy Couch
 * @date 27 February 2017
 */
public class Controller
{
	EmailClientSystem ecs;

	/**
	 * Initializes a controller with the EmailClientSystem supplied
	 * 
	 * @param e EmailClientSystem to use
	 */
	Controller(EmailClientSystem e)
	{
		ecs = e;
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
		return ecs.getEmail(userIndex, siteIndex, accountIndex, mailboxIndex, emailIndex);
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
		return ecs.deleteEmail(userIndex, siteIndex, accountIndex, mailboxIndex, emailIndex);
	}

	/**
	 * adds a user
	 * 
	 * @param userName name for user
	 * @return new user
	 */
	public User addUser(String userName)
	{
		return ecs.addUser(userName);
	}

	/**
	 * adds an account
	 * 
	 * @param emailAddress address for account
	 * @param userIndex index of user to add to
	 * @param siteIndex index of site to add to
	 * @return new account
	 */
	public Account addAccount(String emailAddress, int userIndex, int siteIndex)
	{
		return ecs.addAccount(emailAddress, userIndex, siteIndex);
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
		return ecs.deleteAccount(userIndex, siteIndex, accountIndex);
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
		return ecs.deleteUser(userIndex);
	}
	
	/**
	* adds an account
	*
	* @param email address to add
	* @return the account added
	*
	* @author Will Hildreth
	*/
	public Account addAccount(String emailAddress)
	{
			return ecs.addAccount(emailAdress);
	}
	
	/**
	* adds a user
	*
	* @param user name to add
	* @return the user added
	*
	* @author Will Hildreth
	*/
	public User addAccount(String userName)
	{
			return ecs.addUser(userName);
	}
}
