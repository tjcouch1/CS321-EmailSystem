import java.time.LocalDateTime;

/**
 * Email - holds data for an email
 * 
 * @author Daniel Johnson, Timothy Couch
 *
 */
public class Email implements Cloneable
{
	private String message;
	private String sender;
	private String receiver;
	private String timeStamp;

	/**
	 * creates an email with designated message, sender, and receiver
	 * 
	 * @param message email message
	 * @param sender email sender
	 * @param receiver email receiver
	 */
	public Email(String message, String sender, String receiver)
	{
		this.message = message;
		this.sender = sender;
		this.receiver = receiver;
		timeStamp = LocalDateTime.now().toString();
	}

	/**
	 * creates an email with designated message, sender, and receiver
	 * 
	 * @param message email message
	 * @param sender email sender
	 * @param receiver email receiver
	 * @param timeStamp time stamp
	 */
	private Email(String message, String sender, String receiver, String timeStamp)
	{
		this.message = message;
		this.sender = sender;
		this.receiver = receiver;
		this.timeStamp = timeStamp;
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

	public String getTimeStamp()
	{
		return timeStamp;
	}
	
	/**
	 * makes a clone of this email
	 */
	public Email clone()
	{
		return new Email(message, sender, receiver, timeStamp);
	}
}
