import java.io.*;
import java.net.*;
import java.util.Scanner;


class Ct1 extends Thread
{
	DataInputStream din;
	String str2="";
	public Ct1(DataInputStream din)
	{
		this.din = din;
	}
	
	public void run()
	{
		try{
		while(true)
		{
		str2 = din.readUTF();
		System.out.println("Server: "+str2);		
		}}
		catch(Exception e)
		{
		
		}
	}
}

class Ct2 extends Thread
{
	DataOutputStream dout;
	String str1="";
	public Ct2(DataOutputStream dout)
	{
		this.dout = dout;
	}
	
	public void run()
	{
		try{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
		str1 = br.readLine();
		dout.writeUTF(str1);
		dout.flush();
		}
		}
		catch (Exception e)
		{
			
		}
	}
}

public class MyClient 
{
	public static void main(String str[])
	{
		try
		{
			Socket s = new Socket("localhost",4444);
			DataInputStream din = new DataInputStream(s.getInputStream());
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
			
			Ct1 obj1 = new Ct1(din);
			obj1.start();
			Ct2 obj2 = new Ct2(dout);
			obj2.start();
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}