import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * @author Will Hildreth
 * JUnit test for User
 * 4/12/2017
 *
 */
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
	
	@Test
	public void TestDeleteEmail()
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
