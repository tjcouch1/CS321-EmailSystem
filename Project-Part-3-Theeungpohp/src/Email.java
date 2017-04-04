/**
 * Email - holds data for an email
 * 
 * @author Daniel Johnson, Timothy Couch
 *
 */
public class Email
{
	private String message;
	private String sender;
	private String receiver;

	/**
	 * creates an email with designated message, sender, and receiver
	 * 
	 * @param message
	 *            email message
	 * @param sender
	 *            email sender
	 * @param receiver
	 *            email receiver
	 */
	public Email(String message, String sender, String receiver)
	{
		this.message = message;
		this.sender = sender;
		this.receiver = receiver;
	}

	public String getMessage()
	{
		return message;
	}

	public String getSender()
	{
		return sender;
	}

	public String getReceiver()
	{
		return receiver;
	}
}
