import java.io.*;
import java.net.*;

class t1 extends Thread
{
	DataInputStream din;
	String str1="";
 public t1(DataInputStream din)
 {
 	this.din = din;
 }
	public void run()
	{
		while(true)
		{
			try{
			str1 = (String)din.readUTF();
			System.out.println("Client: "+str1);
			}
			catch (Exception e)
			{
				
			}
		}
	}
}

class t2 extends Thread
{
	DataOutputStream dout;
	String str2="";
	
 public t2(DataOutputStream dout)
 {
 	this.dout = dout;
 }
	public void run()
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			try{
			str2 = br.readLine();
			dout.writeUTF(str2);
			dout.flush();
			}
			catch(Exception e)
			{
				
			}
		}
	}
}

public class MyServer
{
	public static void main(String args[])
	{
		try
		{
			ServerSocket ss = new ServerSocket(4444);
			Socket s = ss.accept();
			DataInputStream din = new DataInputStream(s.getInputStream());
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
			
			t1 ob1 = new t1(din);
			ob1.start();
			t2 ob2 = new t2(dout);
			ob2.start();
			
		}
		catch( Exception e)
		{
			System.out.println(e);
		}
	}
}