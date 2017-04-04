import java.util.ArrayList;

/**
 * Site - handles accounts and email addresses
 * @author Timothy Couch
 * @date 4/4/17
 */
public class Site
{
	private ArrayList<Account> accounts;
	
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
	public Email getEmail(int accountIndex, int mailboxIndex, int emailIndex)
	{
		return accounts.get(accountIndex).getEmail(mailboxIndex, emailIndex);
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
	public boolean deleteEmail(int accountIndex, int mailboxIndex, int emailIndex)
	{
		return accounts.get(accountIndex).deleteEmail(mailboxIndex, emailIndex);
	}
}
