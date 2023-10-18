package extras;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class Getproperties
{
	public static void main(String[] args) throws Exception
	{
		//1. Get OS built-in properties info
        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("os.name"));
        //2. Get Environment variables info created by system or users
        System.out.println(System.getenv("JAVA_HOME"));
        ProcessBuilder pb = new ProcessBuilder();
        Map<String, String> envMap=pb.environment();
        Set<String> keys=envMap.keySet();
        for(String key:keys)
        {
            System.out.println(key+"==>"+envMap.get(key));
        }
        //3. Get our properties info in properties file 
        File f=new File(System.getProperty("user.dir")+"\\gmaildata.properties");
        FileReader fr=new FileReader(f);
        BufferedReader br=new BufferedReader(fr);
        Properties p=new Properties();
        p.load(br);
        System.out.println(p.getProperty("url"));    
	}
}
