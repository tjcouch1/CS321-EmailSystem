import static org.junit.Assert.*;

import org.junit.Test;

/**
 * JUnitTestController - Tests Controller
 * @author Daniel Johnson
 * @date 4/11/17
 */
public class JUnitTestController 
{
	EmailClientSystem ecs = new EmailClientSystem();
	Email e = new Email("hey", "user@local.net", "user@remote.net");
	
	@Test
	public void testConstructor() 
	{
		EmailClientSystem ecs = new EmailClientSystem();
		Controller c = new Controller(ecs);
		assertTrue(c != null);
	}
	
	@Test
	public void testSendEmail()
	{
		Controller c = new Controller(ecs);
		assertTrue(ecs.getEmail(0, 0, 0, 0, 0) == null);
		ecs.addUser("user");
		ecs.addAccount("user@remote.net", 0, 0);
		c.sendEmail(e);
		assertTrue(ecs.getEmail(0, 0, 0, 0, 0) != null);
	}
	
	@Test
	public void testGetEmail()
	{
		Controller c = new Controller(ecs);
		assertTrue(c.getEmail(0, 0, 0, 0, 0) == null);
		ecs.addUser("user");
		ecs.addAccount("user@remote.net", 0, 0);
		ecs.sendEmail(e);
		assertTrue(c.getEmail(0, 0, 0, 0, 0) != null);
	}
	
	@Test
	public void testDeleteEmail()
	{
		Controller c = new Controller(ecs);
		ecs.addUser("user");
		ecs.addAccount("user@remote.net", 0, 0);
		ecs.sendEmail(e);
		assertTrue(ecs.getEmail(0, 0, 0, 0, 0) != null);
		c.deleteEmail(0, 0, 0, 0, 0);
		assertTrue(ecs.getEmail(0, 0, 0, 0, 0) == null);
	}
	
	@Test
	public void testAddAccount()
	{
		Controller c = new Controller(ecs);
		ecs.addUser("user");
		assertTrue(c.addAccount("user@remote.net", 0, 0) != null);
	}
	
	@Test
	public void testDeleteAccount()
	{
		Controller c = new Controller(ecs);
		ecs.addUser("user");
		Account a = ecs.addAccount("user@remote.net",0, 0);
		assertTrue(a != null);
		assertTrue(c.deleteAccount(0, 0, 0) == a);
	}
	
	@Test
	public void testAddUser()
	{
		Controller c = new Controller(ecs);
		assertTrue(c.addUser("user") != null);
	}
	
	@Test
	public void testDeleteUser()
	{
		Controller c = new Controller(ecs);
		User a = ecs.addUser("user");
		assertTrue(a != null);
		assertTrue(c.deleteUser(0) == a);
	}

}
