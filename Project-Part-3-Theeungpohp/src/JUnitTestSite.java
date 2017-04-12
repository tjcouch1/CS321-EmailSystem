import static org.junit.Assert.*;

import org.junit.Test;

public class JUnitTestSite {

	@Test
	public void testUser() 
	{
		//user constructor
		Site s = new Site();
		assertTrue(s != null);
	}
	
	@Test public void testGetEmail()
	{
		Site s = new Site();
		Email e = new Email(null, null, null);
	
		e = s.deleteEmail(0,0,0);
		assertTrue(e == null);
		
		e = s.getEmail(0,0,0);
		assertTrue(e == null);
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
