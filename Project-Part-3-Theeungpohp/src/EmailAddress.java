/**
 * EmailAddress - holds email data
 * 
 * @author Timothy Couch
 * @date 4/4/17
 */
public class EmailAddress
{
	private String name;
	private String siteName;
	private String address;

	EmailAddress(String a)
	{
		name = a.substring(0, a.indexOf("@"));
		siteName = a.substring(a.indexOf("@"));
		address = a;
	}

	public String getName()
	{
		return name;
	}

	public String getSiteName()
	{
		return siteName;
	}

	public String getAddress()
	{
		return address;
	}

	public String toString()
	{
		return getAddress();
	}
}
