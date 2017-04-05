/**
 * User - represents a person who uses the email system. Has sites
 * 
 * @author Timothy Couch
 * @date 4/4/17
 */
public class User
{
	private String name;
	private Site localSite; //index 0
	private Site remoteSite; //index 1
	
	/**
	 * sets up user with userName
	 * @param userName new user name
	 */
	public User(String userName)
	{
		name = userName;
		localSite = new Site();
		remoteSite = new Site();
	}

	/**
	 * retrieves an email at specified spot
	 * 
	 * @param siteIndex index of site
	 * @param accountIndex index of account
	 * @param mailboxIndex index of mailbox
	 * @param emailIndex index of email to retrieve
	 * @return email to retrieve
	 * 
	 * @author Timothy Couch
	 */
	public Email getEmail(int siteIndex, int accountIndex, int mailboxIndex, int emailIndex)
	{
		if (getSiteByIndex(siteIndex) == null)
			return null;
		return getSiteByIndex(siteIndex).getEmail(accountIndex, mailboxIndex, emailIndex);
	}

	/**
	 * deletes an email (moves to trash or deletes)
	 * 
	 * @param siteIndex index of site
	 * @param accountIndex index of account
	 * @param mailboxIndex index of mailbox
	 * @param emailIndex index of email
	 * @return whether or not it deleted properly
	 * 
	 * @author Timothy Couch
	 */
	public Email deleteEmail(int siteIndex, int accountIndex, int mailboxIndex, int emailIndex)
	{
		if (getSiteByIndex(siteIndex) == null)
			return null;
		return getSiteByIndex(siteIndex).deleteEmail(accountIndex, mailboxIndex, emailIndex);
	}
	
	/**
	 * adds an account
	 * 
	 * @param emailAddress address for account
	 * @param siteIndex index of site to add to
	 * @return new account
	 */
	public Account addAccount(String emailAddress, int siteIndex)
	{
		if (getSiteByIndex(siteIndex) == null)
			return null;
		return getSiteByIndex(siteIndex).addAccount(emailAddress);
	}

	/**
	 * deletes an account
	 * @param siteIndex site that holds the account
	 * @param accountIndex the account index in the site
	 * @return the account deleted
	 */
	public Account deleteAccount(int siteIndex, int accountIndex)
	{
		if (getSiteByIndex(siteIndex) == null)
			return null;
		return getSiteByIndex(siteIndex).deleteAccount(accountIndex);
	}

	/**
	 * returns the site associated with the index
	 * 
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
	
	public String getName()
	{
		return name;
	}
}
