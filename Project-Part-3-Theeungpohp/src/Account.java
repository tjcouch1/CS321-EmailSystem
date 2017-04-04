/**
 * Account - holds mailboxes of emails
 * @author Timothy Couch
 * @date 4/4/17
 */
public class Account
{
	private Mailbox inbox;
	private Mailbox sent;
	private Mailbox trash;
	
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
	public Email getEmail(int mailboxIndex, int emailIndex)
	{
		return getMailboxByIndex(mailboxIndex).getEmail(emailIndex);
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
	public boolean deleteEmail(int mailboxIndex, int emailIndex)
	{
		Email deleted = getMailboxByIndex(mailboxIndex).deleteEmail(emailIndex);
		if (deleted != null)
			if (getMailboxByIndex(mailboxIndex) == inbox || getMailboxByIndex(mailboxIndex) == sent)
				trash.addEmail(deleted);
		return deleted != null;
	}
	
	private Mailbox getMailboxByIndex(int mailboxIndex)
	{
		if (mailboxIndex == 0)
			return inbox;
		if (mailboxIndex == 1)
			return sent;
		if (mailboxIndex == 2)
			return trash;
		return null;
	}
}
