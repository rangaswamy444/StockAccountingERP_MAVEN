package DriverFactory;

import org.testng.annotations.Test;

public class AppTest {
@Test
public void kickStart()
{
	try {
	DriverScript ds = new DriverScript();
	ds.startTest();
	}catch (Throwable t) 
	{
		System.out.println(t.getMessage());
	}
	
}
}
