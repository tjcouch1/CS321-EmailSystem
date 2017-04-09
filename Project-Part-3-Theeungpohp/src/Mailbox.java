import java.util.ArrayList;

public class Mailbox
{
	private ArrayList<Email> emails;

	public Mailbox()
	{
		emails = new ArrayList<>();
	}

	/**
	 * retrieves an email at specified spot
	 * 
	 * @param emailIndex index of email to retrieve
	 * @return email to retrieve
	 * 
	 * @author Timothy Couch
	 */
	public Email getEmail(int emailIndex)
	{
		if (emailIndex < 0 || emailIndex >= emails.size())
			return null;
		return emails.get(emailIndex);
	}
	
	/**
	 * adds email to this mailbox
	 * @param email email to add
	 * 
	 * @author Timothy Couch
	 */
	public void addEmail(Email email)
	{
		emails.add(email);
	}
	
	/**
	 * deletes an email (moves to trash or deletes)
	 * 
	 * @param emailIndex index of email
	 * @return whether or not it deleted properly
	 * 
	 * @author Timothy Couch
	 */
	public Email deleteEmail(int emailIndex)
	{
		if (emailIndex < 0 || emailIndex >= emails.size())
			return null;
		return emails.remove(emailIndex);
	}
}
