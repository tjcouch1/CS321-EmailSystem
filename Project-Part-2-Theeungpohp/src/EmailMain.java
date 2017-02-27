
/**
 * EmailMain - runs the program
 * @author Timothy Couch
 * @date 27 February 2017
 */
public class EmailMain
{
	
	public static void main(String[] args)
	{
		EmailClientSystem ecs = new EmailClientSystem();
		Controller controller = new Controller(ecs);
		
		GUI gui = new GUI(controller);
	}
	
}
