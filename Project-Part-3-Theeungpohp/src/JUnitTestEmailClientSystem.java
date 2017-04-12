import static org.junit.Assert.*;

import org.junit.Test;

/**
 * JUnitTestEmailClientSystem
 * @author Daniel Johnson
 * @date 4/12/17
 */
public class JUnitTestEmailClientSystem 
{
	Email e = new Email("hey", "user@local.net", "user@remote.net");
	

	@Test
	public void testConstructor()
	{
		EmailClientSystem testECS = new EmailClientSystem();
		assertTrue(testECS != null);
	}
	
	@Test
	public void testGetEmail()
	{
		EmailClientSystem ecs = new EmailClientSystem();
		ecs.addUser("user");
		ecs.addAccount("user@remote.net",0,0);
		assertTrue(ecs.getEmail(0, 0, 0, 0, 0) == null);
		ecs.sendEmail(e);
		assertTrue(ecs.getEmail(0, 0, 0, 0, 0) != null);
	}
	
	@Test
	public void testSendEmail()
	{
		EmailClientSystem ecs = new EmailClientSystem();
		ecs.addUser("user");
		ecs.addAccount("user@remote.net", 0, 0);
		assertTrue(ecs.getEmail(0,0,0,0,0)== null);
		ecs.sendEmail(e);
		assertTrue(ecs.getEmail(0,0,0,0,0) != null);
	}
	
	@Test
	public void testDeleteEmail()
	{
		EmailClientSystem ecs = new EmailClientSystem();
		ecs.addUser("user");
		ecs.addAccount("user@remote.net", 0, 0);
		ecs.sendEmail(e);
		assertTrue(ecs.getEmail(0, 0, 0, 0, 0) != null);
		ecs.deleteAccount(0, 0, 0);
		assertTrue(ecs.getEmail(0,0,0,0,0) == null);
	}
	
	@Test
	public void testAddUser()
	{
		EmailClientSystem ecs = new EmailClientSystem();
		assertTrue(ecs.addUser("user") != null);
	}
	
	@Test
	public void testDeleteUser()
	{
		EmailClientSystem ecs = new EmailClientSystem();
		User user = ecs.addUser("user");
		assertTrue(user != null);
		assertTrue(ecs.deleteUser(0) == user);
	}
	
	@Test
	public void testAddAccount()
	{
		EmailClientSystem ecs = new EmailClientSystem();
		ecs.addUser("user");
		assertTrue(ecs.addAccount("user@remote.net", 0, 0) != null);
	}
	
	@Test
	public void testDeleteAccount()
	{
		EmailClientSystem ecs = new EmailClientSystem();
		ecs.addUser("user");
		Account a = ecs.addAccount("user@remote.net",0,0);
		assertTrue(a != null);
		assertTrue(ecs.deleteAccount(0, 0, 0) == a);
	}
}
