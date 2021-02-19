package Utilites;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtil {
public static String getValueForKey(String key) throws Throwable
{
	Properties  confingProperties=new Properties();
	confingProperties.load(new FileInputStream("C:\\Selenium_OJT\\ERP_Maven\\PropertyFile\\Environment.properties"));
	return confingProperties.getProperty(key);
	
	
}
}
