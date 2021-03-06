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
	
	/**
	 * creates an email address from string a
	 * @param a address in string form
	 * 
	 * @author Timothy Couch
	 */
	public EmailAddress(String a)
	{
		if (a == null || a.length() == 0)
		{
			name = "";
			siteName = "";
			address = "";
		}
		else if (a.indexOf("@") < 0)
		{
			name = a;
			siteName = "";
			address = a;
		}
		else
		{
			name = a.substring(0, a.indexOf("@"));
			siteName = a.substring(a.indexOf("@"));
			address = a;
		}
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
