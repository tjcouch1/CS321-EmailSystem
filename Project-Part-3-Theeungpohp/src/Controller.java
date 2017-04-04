
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
	 * @param e
	 *            EmailClientSystem to use
	 */
	Controller(EmailClientSystem e)
	{
		ecs = e;
	}

	/**
	 * retrieves an email at specified spot
	 * 
	 * @param userIndex
	 *            index of user
	 * @param siteIndex
	 *            index of site
	 * @param accountIndex
	 *            index of account
	 * @param mailboxIndex
	 *            index of mailbox
	 * @param emailIndex
	 *            index of email to retrieve
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
	 * @param userIndex
	 *            index of user
	 * @param siteIndex
	 *            index of site
	 * @param accountIndex
	 *            index of account
	 * @param mailboxIndex
	 *            index of mailbox
	 * @param emailIndex
	 *            index of email
	 * @return whether or not it deleted properly
	 * 
	 * @author Timothy Couch
	 */
	public boolean deleteEmail(int userIndex, int siteIndex, int accountIndex, int mailboxIndex, int emailIndex)
	{
		return ecs.deleteEmail(userIndex, siteIndex, accountIndex, mailboxIndex, emailIndex);
	}
}
