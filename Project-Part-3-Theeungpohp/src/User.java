/**
 * User - represents a person who uses the email system. Has sites
 * @author Timothy Couch
 * @date 4/4/17
 */
public class User
{
	public String name;
	private Site localSite;
	private Site remoteSite;
	
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
	public Email getEmail(int siteIndex, int accountIndex, int mailboxIndex, int emailIndex)
	{
		return getSiteByIndex(siteIndex).getEmail(accountIndex, mailboxIndex, emailIndex);
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
	public boolean deleteEmail(int siteIndex, int accountIndex, int mailboxIndex, int emailIndex)
	{
		return getSiteByIndex(siteIndex).deleteEmail(accountIndex, mailboxIndex, emailIndex);
	}
	
	/**
	 * returns the site associated with the index
	 * @param siteIndex 0 for local or 1 for remote
	 * @return site associated with index
	 */
	private Site getSiteByIndex(int siteIndex)
	{
		if (siteIndex == 0)
			return localSite;
		if (siteIndex == 1)
			return remoteSite;
		return null;
	}
}
