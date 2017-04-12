import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * @author Will Hildreth
 * JUnit test of Site
 * 4/12/2017
 *
 */
public class JUnitTestSite {

	@Test
	public void testSite() 
	{
		//user constructor
		Site s = new Site();
		assertTrue(s != null);
	}
	
	@Test public void testEmail()
	{
		User u = new User("test user");
		Email e = new Email("Hi", "test@gmail.com", "test2@gmail.com");
		u.addAccount("test2@gmail.com", 0);
		u.addAccount("test2@gmail.com", 1);
		
		u.sendEmail(e);
		assertTrue(u.deleteEmail(0, 0, 0, 1) == null);
		assertTrue(u.deleteEmail(0, 0, 1, 0) == null);
		assertTrue(u.deleteEmail(0, 0, 1, 1) == null);
		assertTrue(u.deleteEmail(0, 1, 0, 0) == null);
		assertTrue(u.deleteEmail(0, 1, 0, 1) == null);
		assertTrue(u.deleteEmail(0, 1, 1, 0) == null);
		assertTrue(u.deleteEmail(0, 1, 1, 1) == null);
		assertTrue(u.deleteEmail(1, 0, 0, 1) == null);
		assertTrue(u.deleteEmail(1, 0, 1, 0) == null);
		assertTrue(u.deleteEmail(1, 0, 1, 1) == null);
		assertTrue(u.deleteEmail(1, 1, 0, 0) == null);
		assertTrue(u.deleteEmail(1, 1, 0, 1) == null);
		assertTrue(u.deleteEmail(1, 1, 1, 0) == null);
		assertTrue(u.deleteEmail(1, 1, 1, 1) == null);
		
		Email deleted = u.deleteEmail(0, 0, 0, 0);
		assertTrue(deleted.getMessage().equals(e.getMessage()));
		assertTrue(deleted.getSender().equals(e.getSender()));
		assertTrue(deleted.getReceiver().equals(e.getReceiver()));
		assertTrue(deleted.getTimeStamp().equals(e.getTimeStamp()));
		
		deleted = u.deleteEmail(1, 0, 0, 0);
		assertTrue(deleted.getMessage().equals(e.getMessage()));
		assertTrue(deleted.getSender().equals(e.getSender()));
		assertTrue(deleted.getReceiver().equals(e.getReceiver()));
		assertTrue(deleted.getTimeStamp().equals(e.getTimeStamp()));
		
		assertTrue(u.deleteEmail(0, 0, 0, 0) == null);
		assertTrue(u.deleteEmail(1, 0, 0, 0) == null);
	}
	
	@Test public void testAccounts()
	{
		Site s = new Site();
		Account a = new Account(null);
		
		a = s.addAccount("Test@email.com");
		assertTrue(a != null);
		
		a = s.deleteAccount(0);
		assertTrue(a.getEmailAddress().equals("Test@email.com"));
	}

}
