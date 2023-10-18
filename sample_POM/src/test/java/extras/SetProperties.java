package extras;

import org.apache.commons.configuration.PropertiesConfiguration;

public class SetProperties 
{
	public static void main(String[] args) throws Exception
	{
		//1. Change OS built-in properties info
		System.setProperty("os.name","Mac");
		System.out.println(System.getProperty("os.name"));
		//2. Set Environment variables info created by system or users
		
		//3. Get our properties info in properties file 
        PropertiesConfiguration config = new PropertiesConfiguration("E:\\batch247\\org.magnitia.samplePOM\\src\\test\\resources\\gmaildata.properties");
        config.setProperty("url","http://www.amazon.co.in");
        config.setProperty("maxwait","30");
        config.setProperty("newproperty","value");
        config.save();
	}

}
