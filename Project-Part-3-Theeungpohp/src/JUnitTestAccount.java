import static org.junit.Assert.*;

import org.junit.Test;
/**
 * JUnitTestAccount - Tests Account
 * @author Timothy Couch
 * @date 4/11/17
 */
public class JUnitTestAccount
{
	@Test
	public void TestAccountConstructor()
	{
		Account a = new Account(null);
		assertTrue(a != null);
		
		a = new Account("test@gmail.com");
		assertTrue(a != null);
	}
	
	@Test
	public void TestGetEmail()
	{
		Account a = new Account("test@gmail.com");
		assertTrue(a.getEmail(0, 0) == null);
		assertTrue(a.getEmail(1, 0) == null);
		assertTrue(a.getEmail(0, 1) == null);

		a.sendEmail(new Email("Hi", "test@gmail.com", "test2@gmail.com"));
		assertTrue(a.getEmail(0, 0) == null);
		assertTrue(a.getEmail(1, 0) != null);
		assertTrue(a.getEmail(0, 1) == null);
	}
	
	@Test
	public void TestDeleteEmail()
	{
		Account a = new Account("test@gmail.com");
		Email e = new Email("Hi", "test@gmail.com", "test2@gmail.com");
		a.sendEmail(e);
		assertTrue(a.deleteEmail(0, 0) == null);
		assertTrue(a.deleteEmail(0, 1) == null);
		Email deleted = a.deleteEmail(1, 0);
		assertTrue(deleted.getMessage().equals(e.getMessage()));
		assertTrue(deleted.getSender().equals(e.getSender()));
		assertTrue(deleted.getReceiver().equals(e.getReceiver()));
		assertTrue(deleted.getTimeStamp().equals(e.getTimeStamp()));
		
		assertTrue(a.deleteEmail(1, 0) == null);
		deleted = a.deleteEmail(2, 0);
		assertTrue(deleted.getMessage().equals(e.getMessage()));
		assertTrue(deleted.getSender().equals(e.getSender()));
		assertTrue(deleted.getReceiver().equals(e.getReceiver()));
		assertTrue(deleted.getTimeStamp().equals(e.getTimeStamp()));
	}
	
	@Test
	public void TestSendEmail()
	{
		Account a = new Account("test@gmail.com");

		a.sendEmail(null);
		assertTrue(a.getEmail(1, 0) == null);
		
		a.sendEmail(new Email("Hi", "test@gmail.com", "test2@gmail.com"));
		assertTrue(a.getEmail(1, 0) != null);
		
		a.sendEmail(new Email("Hello", "test2@gmail.com", "test@gmail.com"));
		assertTrue(a.getEmail(0, 0) != null);
		
	}
	
	@Test
	public void TestGetEmailAddress()
	{
		Account a = new Account(null);
		assertTrue(a.getEmailAddress() == "");
		
		a = new Account("test@gmail.com");
		assertTrue(a.getEmailAddress().equals("test@gmail.com"));
	}
}
