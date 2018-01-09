package jackson.rce;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class App 
{
    public static void main( String[] args ) throws IOException 
    {
		System.out.println("creating objectmapper");
		ObjectMapper om = new ObjectMapper();
		om.enableDefaultTyping();
		
		inner i = om.readValue(Files.readAllBytes(Paths.get(args[0])), inner.class);
		System.out.println("done");
    }
}

class inner {
	public int id;
	public Object obj;
}
