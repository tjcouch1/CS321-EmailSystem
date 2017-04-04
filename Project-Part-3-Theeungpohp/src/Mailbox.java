import java.util.ArrayList;

public class Mailbox
{
	private ArrayList<Email> emails = new ArrayList<Email>();

	public Mailbox()
	{
		
	}

	// Skeleton Methods
	public Email getEmail(int emailIndex)
	{
		return emails.get(emailIndex);
	}

	public void addEmail(Email email)
	{
		
	}

	public Email deleteEmail(int emailIndex)
	{
		if (emails.size() >= emailIndex || emails.size() < 0)
			return null;
		return emails.remove(emailIndex);
	}
}
