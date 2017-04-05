/**
 * Account - holds mailboxes of emails
 * 
 * @author Timothy Couch
 * @date 4/4/17
 */
public class Account
{
	private Mailbox inbox; //index 0
	private Mailbox sent; //index 1
	private Mailbox trash; //index 2
	
	private EmailAddress emailAddress;
	
	/**
	 * makes an account with specified email address
	 * @param emailAddress name of email address
	 */
	public Account(String emailAddress)
	{
		inbox = new Mailbox();
		sent = new Mailbox();
		trash = new Mailbox();
		
		this.emailAddress = new EmailAddress(emailAddress);
	}

	/**
	 * retrieves an email at specified spot
	 * 
	 * @param mailboxIndex index of mailbox
	 * @param emailIndex index of email to retrieve
	 * @return email to retrieve
	 * 
	 * @author Timothy Couch
	 */
	public Email getEmail(int mailboxIndex, int emailIndex)
	{
		if (getMailboxByIndex(mailboxIndex) == null)
			return null;
		return getMailboxByIndex(mailboxIndex).getEmail(emailIndex);
	}

	/**
	 * deletes an email (moves to trash or deletes)
	 * 
	 * @param mailboxIndex index of mailbox
	 * @param emailIndex index of email
	 * @return whether or not it deleted properly
	 * 
	 * @author Timothy Couch
	 */
	public Email deleteEmail(int mailboxIndex, int emailIndex)
	{
		if (getMailboxByIndex(mailboxIndex) == null)
			return null;
		Email deleted = getMailboxByIndex(mailboxIndex).deleteEmail(emailIndex);
		if (deleted != null)
			if (getMailboxByIndex(mailboxIndex) == inbox || getMailboxByIndex(mailboxIndex) == sent)
				trash.addEmail(deleted);
		return deleted;
	}
	
	/**
	 * gets mailbox associated with a particular index
	 * @param mailboxIndex index of mailbox
	 * @return mailbox with index
	 * 
	 * @author Timothy Couch
	 */
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
	
	/**
	 * either receives an email or sends an email based on the sender and receiver of the email supplied
	 * @param e email to send or receive
	 * 
	 * NOTE: The email is cloned before adding to the account to ensure that no emails are linked
	 */
	public void sendEmail(Email e)
	{
		if (e.getSender() == emailAddress.toString())
			sent.addEmail(e.clone());
		else if (e.getReceiver() == emailAddress.toString())
			inbox.addEmail(e.clone());
	}
	
	public String getEmailAddress()
	{
		return emailAddress.toString();
	}
}
