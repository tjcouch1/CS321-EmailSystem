public class Email 
{
	private String subject;
	private String message;
	
	/* are these necessary?
	private String sender;
	private String receiver;
	*/
	
	public Email(String subject, String message /* , String sender, String Receiver */)
	{
		this.subject = subject;
		this.message = message;
		//this.sender = sender;
		//this.receiver = receiver;
	}
	
	public String getSubject(){ return subject;}
	public String getMessage(){ return message;}
	//public String getSender() { return sender;}
	//public String getReceiver(){ return receiver;}
}
