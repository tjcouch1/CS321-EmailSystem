import static org.junit.Assert.*;

import org.junit.Test;

public class JUnitTestUser
{

	@Test
	public void TestUserConstructor() {
		
		User a = new User(null);
		assertTrue(a != null);
		
		a = new User("test user");
		assertTrue(a != null);
	}
	
	@Test
	public void TestEmail()
	{
		User a = new User("test user");
		
		//email constructor works
		Email e = new Email("test message", "test sender", "test receiver");
		assertTrue(e != null);
		
		//make sure sending is ok
		a.sendEmail(e);
		
		//get email works
		e = a.getEmail(0,0,0,0);
		assertTrue(e == null);
	}
	
	@Test public void TestGetName()
	{
		User a = new User("Test User");
		
		String s = a.getName();
		assertTrue(s.equals("Test User"));
	}
	
	@Test public void TestAddAccount()
	{
		//user constructor
		User u = new User("Test User");
		
		//account constructor
		Account a = new Account("Test Account");
		assertTrue(a != null);
		
		//add account
		a = u.addAccount("Test Account", 0);
		assertTrue(a != null);
		
		//delete account
		a = u.deleteAccount(0, 0);
		assertTrue(a != null);
	}
}
