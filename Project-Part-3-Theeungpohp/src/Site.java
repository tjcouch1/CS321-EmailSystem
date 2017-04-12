import java.util.ArrayList;

/**
 * Site - handles accounts and email addresses
 * 
 * @author Timothy Couch
 * @date 4/4/17
 */
public class Site
{
	private ArrayList<Account> accounts;
	
	public Site()
	{
		accounts = new ArrayList<>();
	}

	/**
	 * retrieves an email at specified spot
	 * 
	 * @param accountIndex index of account
	 * @param mailboxIndex index of mailbox
	 * @param emailIndex index of email to retrieve
	 * @return email to retrieve
	 * 
	 * @author Timothy Couch
	 */
	public Email getEmail(int accountIndex, int mailboxIndex, int emailIndex)
	{
		if (accountIndex < 0 || accountIndex >= accounts.size())
			return null;
		return accounts.get(accountIndex).getEmail(mailboxIndex, emailIndex);
	}

	/**
	 * deletes an email (moves to trash or deletes)
	 * 
	 * @param accountIndex index of account
	 * @param mailboxIndex index of mailbox
	 * @param emailIndex index of email
	 * @return whether or not it deleted properly
	 * 
	 * @author Timothy Couch
	 */
	public Email deleteEmail(int accountIndex, int mailboxIndex, int emailIndex)
	{
		if (accountIndex < 0 || accountIndex >= accounts.size())
			return null;
		return accounts.get(accountIndex).deleteEmail(mailboxIndex, emailIndex);
	}

	/**
	 * adds an account
	 * 
	 * @param emailAddress address for account
	 * @return new account
	 * 
	 * @author Timothy Couch
	 */
	public Account addAccount(String emailAddress)
	{
		if (emailAddress != null)
		{
			Account a = new Account(emailAddress);
			accounts.add(a);
			return a;
		}
		return null;
	}

	/**
	 * deletes an account
	 * @param accountIndex the account index in the site
	 * @return the account deleted
	 * 
	 * @author Timothy Couch
	 */
	public Account deleteAccount(int accountIndex)
	{
		if (accountIndex < 0 || accountIndex >= accounts.size())
			return null;
		return accounts.remove(accountIndex);
	}
	
	/**
	 * adds email to appropriate mailboxes
	 * @param e email to send
	 * @author Daniel Johnson
	 */
	public void sendEmail(Email e)
	{
		for(Account account : accounts)
		{
			account.sendEmail(e);
		}
	}
}
