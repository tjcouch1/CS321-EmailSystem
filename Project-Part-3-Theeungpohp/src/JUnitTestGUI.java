import static org.junit.Assert.*;

import org.junit.Test;
/**
 * JUnitTestGUI - tests the methods of GUI
 * @author Timothy Couch
 * @date 4/11/17
 */
public class JUnitTestGUI
{
	@Test
	public void TestGUIConstructor()
	{
		EmailClientSystem ecs = new EmailClientSystem();
		Controller c = new Controller(ecs);
		
		GUI g = new GUI(null);
		assertTrue(g != null);
		
		g = new GUI(c);
		assertTrue(g != null);
	}
	
	@Test
	public void TestGetEmail()
	{
		EmailClientSystem ecs = new EmailClientSystem();
		Controller c = new Controller(ecs);
		GUI g = new GUI(c);
		try
		{
			Thread.sleep(4);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		g.createTestingTree();
		
		assertTrue(g.getEmail(0, 0, 0, 0, 0) != null);
		assertTrue(g.getEmail(1, 0, 0, 0, 0) != null);
		assertTrue(g.getEmail(0, 1, 0, 0, 0) != null);
		assertTrue(g.getEmail(0, 0, 1, 0, 0) != null);
		assertTrue(g.getEmail(0, 0, 0, 1, 0) != null);
		assertTrue(g.getEmail(0, 0, 0, 0, 1) == null);
		assertTrue(g.getEmail(0, 1, 1, 0, 0) == null);
		assertTrue(g.getEmail(0, 0, 1, 1, 0) == null);
		assertTrue(g.getEmail(1, 0, 0, 0, 1) == null);
		assertTrue(g.getEmail(0, 0, 0, 0, 0) != null);
		assertTrue(g.getEmail(0, 0, 0, 1, 0) != null);
		assertTrue(g.getEmail(0, 1, 0, 0, 0) != null);
		assertTrue(g.getEmail(0, 0, 1, 1, 0) == null);
		assertTrue(g.getEmail(1, 1, 0, 1, 0) != null);
		assertTrue(g.getEmail(0, 1, 0, 1, 1) == null);
		assertTrue(g.getEmail(1, 0, 1, 1, 1) == null);
		assertTrue(g.getEmail(0, 1, 1, 1, 0) == null);
		assertTrue(g.getEmail(0, 1, 0, 1, 1) == null);
	}
	
	@Test
	public void TestDeleteAccount()
	{
		EmailClientSystem ecs = new EmailClientSystem();
		Controller c = new Controller(ecs);
		GUI g = new GUI(c);
		try
		{
			Thread.sleep(4);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		g.createTestingTree();

		assertTrue(g.deleteAccount(0, 0, 1) != null);
		assertTrue(g.deleteAccount(0, 0, 0) != null);
		assertTrue(g.deleteAccount(1, 0, 0) != null);
		assertTrue(g.deleteAccount(0, 1, 0) != null);
		assertTrue(g.deleteAccount(4, 0, 0) == null);
		assertTrue(g.deleteAccount(0, 4, 0) == null);
		assertTrue(g.deleteAccount(0, 0, 4) == null);
	}
	
	@Test
	public void TestDeleteUser()
	{
		EmailClientSystem ecs = new EmailClientSystem();
		Controller c = new Controller(ecs);
		GUI g = new GUI(c);
		try
		{
			Thread.sleep(4);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		g.createTestingTree();

		assertTrue(g.deleteUser(1) != null);
		assertTrue(g.deleteUser(0) != null);
		assertTrue(g.deleteUser(5) == null);
	}
	
	@Test
	public void TestAddAccount()
	{
		EmailClientSystem ecs = new EmailClientSystem();
		Controller c = new Controller(ecs);
		GUI g = new GUI(c);
		try
		{
			Thread.sleep(4);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		g.createTestingTree();
		
		assertTrue(g.addAccount(null, 0, 0) == null);
		assertTrue(g.addAccount("test@gmail.com", 0, 0) != null);
		assertTrue(g.addAccount(null, 1, 0) == null);
		assertTrue(g.addAccount(null, 0, 1) == null);
		assertTrue(g.addAccount("test@gmail.com", 1, 1) != null);
	}
	
	@Test
	public void TestAddUser()
	{
		EmailClientSystem ecs = new EmailClientSystem();
		Controller c = new Controller(ecs);
		GUI g = new GUI(c);
		try
		{
			Thread.sleep(4);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		g.createTestingTree();
		
		assertTrue(g.addUser(null) == null);
		assertTrue(g.addUser("Swag") != null);
	}

	@Test
	public void TestCreateTestingTree()
	{
		EmailClientSystem ecs = new EmailClientSystem();
		Controller c = new Controller(ecs);
		GUI g = new GUI(c);
		try
		{
			Thread.sleep(4);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		g.createTestingTree();
		
		assertTrue(g.getEmail(0, 0, 0, 0, 0) != null);
		assertTrue(g.getEmail(1, 1, 0, 1, 0) != null);
		assertTrue(g.getEmail(2, 0, 1, 2, 4) == null);
	}
}
