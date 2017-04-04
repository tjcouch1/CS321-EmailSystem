import java.util.ArrayList;

/**
 * EmailClientSystem - handles execution of email actions
 * @author catsu
 *
 */
public class EmailClientSystem
{
	private ArrayList<User> users;
	
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
		return users.get(userIndex).getEmail(siteIndex, accountIndex, mailboxIndex, emailIndex);
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
		return users.get(userIndex).deleteEmail(siteIndex, accountIndex, mailboxIndex, emailIndex);
	}
}
