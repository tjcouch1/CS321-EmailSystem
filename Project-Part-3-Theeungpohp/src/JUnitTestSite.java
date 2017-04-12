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
		Site s = new Site();
		Email e = new Email("Hi", "test@gmail.com", "test2@gmail.com");
		s.addAccount("test2@gmail.com");
		
		s.sendEmail(e);
		assertTrue(s.deleteEmail(0, 0, 1) == null);
		assertTrue(s.deleteEmail(0, 1, 0) == null);
		assertTrue(s.deleteEmail(0, 1, 1) == null);
		assertTrue(s.deleteEmail(1, 0, 0) == null);
		assertTrue(s.deleteEmail(1, 0, 1) == null);
		assertTrue(s.deleteEmail(1, 1, 0) == null);
		assertTrue(s.deleteEmail(1, 1, 1) == null);
		
		Email get = s.getEmail(0, 0, 0);
		assertTrue(get.getMessage().equals(e.getMessage()));
		assertTrue(get.getSender().equals(e.getSender()));
		assertTrue(get.getReceiver().equals(e.getReceiver()));
		assertTrue(get.getTimeStamp().equals(e.getTimeStamp()));
		
		Email deleted = s.deleteEmail(0, 0, 0);
		assertTrue(deleted.getMessage().equals(e.getMessage()));
		assertTrue(deleted.getSender().equals(e.getSender()));
		assertTrue(deleted.getReceiver().equals(e.getReceiver()));
		assertTrue(deleted.getTimeStamp().equals(e.getTimeStamp()));
		
		assertTrue(s.deleteEmail(0, 0, 0) == null);
		assertTrue(s.getEmail(0, 0, 0) == null);
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
