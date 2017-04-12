import static org.junit.Assert.*;

import org.junit.Test;

/**
 * JUnitTestEmailClientSystem
 * @author Daniel Johnson
 * @date 4/12/17
 */
public class JUnitTestEmailClientSystem 
{
	EmailClientSystem ecs = new EmailClientSystem();
	Email e1 = new Email("hey", "user1@local.net", "user2@remote.net");
	Email e2 = new Email("hey", "user1@remote.net", "user2@local.net");
	User user1;
	User user2;
	Account a00;
	Account a01;
	Account a10;
	Account a11;
	
	private void initTestModel()
	{
		user1 = ecs.addUser("user1");
		user2 = ecs.addUser("user2");
		a00 = ecs.addAccount("user1@local.net", 0, 0);
		a01 = ecs.addAccount("user1@remote.net", 0, 1);
		a10 = ecs.addAccount("user2@local.net", 1, 0);
		a11 = ecs.addAccount("user2@remote.net", 1, 1);
		
		/* Test Model Visualization:
		 
		 -root
		 --user1
		 ---local
		 ----user1@local.net
		 -----inbox
		 -----sent
		 ------e1
		 -----trash
		 ---remote
		 ----user1@remote.net
		 -----inbox
		 -----sent
		 ------e2
		 -----trash
		 --user2
		 ---local
		 ----user2@local.net
		 -----inbox
		 ------e2
		 -----sent
		 -----trash
		 ---remote
		 ----user2@remote.net
		 -----inbox
		 ------e1
		 -----sent
		 -----trash
		 
		 */
	}
	

	@Test
	public void testConstructor()
	{
		EmailClientSystem testECS = new EmailClientSystem();
		assertTrue(testECS != null);
	}
	
	@Test
	public void testGetEmail()
	{
		initTestModel();
		ecs.sendEmail(e1);
		assertTrue(ecs.getEmail(0, 0, 0, 0, 0) == null);
		assertTrue(ecs.getEmail(1, 1, 0, 0, 0) != null);
		assertTrue(ecs.getEmail(1, 1, 0, 0, 0).getTimeStamp().equals(e1.getTimeStamp()));
		assertTrue(ecs.getEmail(1, 1, 0, 0, 0).getReceiver().equals(e1.getReceiver()));
		assertTrue(ecs.getEmail(1, 1, 0, 0, 0).getSender().equals(e1.getSender()));
		assertTrue(ecs.getEmail(1, 1, 0, 0, 0).getMessage().equals(e1.getMessage()));
	}
	
	@Test
	public void testSendEmail()
	{
		initTestModel();
		ecs.sendEmail(e1);
		ecs.sendEmail(null);
		assertTrue(ecs.getEmail(0, 0, 0, 0, 0) == null);
		assertTrue(ecs.getEmail(1, 1, 0, 0, 0) != null);
		assertTrue(ecs.getEmail(1, 1, 0, 0, 0).getTimeStamp().equals(e1.getTimeStamp()));
		assertTrue(ecs.getEmail(1, 1, 0, 0, 0).getReceiver().equals(e1.getReceiver()));
		assertTrue(ecs.getEmail(1, 1, 0, 0, 0).getSender().equals(e1.getSender()));
		assertTrue(ecs.getEmail(1, 1, 0, 0, 0).getMessage().equals(e1.getMessage()));
	}
	
	@Test
	public void testDeleteEmail()
	{
		initTestModel();
		ecs.sendEmail(e1);
		assertTrue(ecs.getEmail(1, 1, 0, 0, 0) != null);
		assertTrue(ecs.getEmail(0, 0, 0, 0, 0) == null);
		Email deleted1 = ecs.deleteEmail(1,1,0,0,0);
		Email deleted2 = ecs.deleteEmail(0, 0, 0, 0, 0);
		assertTrue(deleted1.getTimeStamp().equals(e1.getTimeStamp()));
		assertTrue(deleted1.getReceiver().equals(e1.getReceiver()));
		assertTrue(deleted1.getSender().equals(e1.getSender()));
		assertTrue(deleted1.getMessage().equals(e1.getMessage()));
		assertTrue(deleted2 == null);
	}
	
	@Test
	public void testAddUser()
	{
		assertTrue(ecs.addUser("user") != null);
		assertTrue(ecs.addUser(null) == null);
	}
	
	@Test
	public void testDeleteUser()
	{
		initTestModel();
		User deleted1 = ecs.deleteUser(1);
		User deleted2 = ecs.deleteUser(0);
		User deleted3 = ecs.deleteUser(3);
		
		assertTrue(deleted1 != null);
		assertTrue(deleted2 != null);
		assertTrue(deleted3 == null);
		assertTrue(deleted1 == user2);
		assertTrue(deleted2 == user1);
	}
	
	@Test
	public void testAddAccount()
	{
		ecs.addUser("user1");
		ecs.addUser("user2");
		assertTrue(ecs.addAccount("user1@local.net", 0, 0) != null);
		assertTrue(ecs.addAccount("user2@remote.net", 1, 1) != null);
	}
	
	@Test
	public void testDeleteAccount()
	{
		initTestModel();
		Account deleted1 = ecs.deleteAccount(0, 0, 0);
		Account deleted2 = ecs.deleteAccount(1, 1, 2);
		assertTrue(deleted1 != null);
		assertTrue(deleted2 == null);
		assertTrue(deleted1 == a00);
	}
}
