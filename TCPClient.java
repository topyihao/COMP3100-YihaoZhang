import java.net.Socket;
import java.net.InetAddress;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.concurrent.TimeUnit;

import java.io.InputStreamReader;
import java.io.BufferedReader;

public class TCPClient {
	public static void main(String[] args) {
		while(true) {
			try {
				InetAddress aHost = InetAddress.getByName(args[0]);
				int aPort = Integer.parseInt(args[1]);
				Socket s = new Socket(aHost, aPort);
				DataOutputStream dout = new DataOutputStream(s.getOutputStream());
				//DataInputStream din = new DataInputStream(s.getInputStream());
				BufferedReader din = new BufferedReader(new InputStreamReader(s.getInputStream()));


				System.out.println("Target IP:" + s.getInetAddress() + "target port: " + s.getPort());
				System.out.println("Local IP: " + s.getLocalAddress() + "Local port: " + s.getLocalPort());
					
				dout.write(("HELO\n").getBytes());
				System.out.println("sent hello");
				
				String str = (String)din.readLine();
				System.out.println("RCVD: " + str);

				System.out.println("");
				dout.write(("AUTH hahaha\n").getBytes());
				System.out.println("");
				
				str = (String)din.readLine();
				System.out.println("RCVD: " + str);

				System.out.println("");
				din.close();
				dout.close();
				s.close();

			}
			catch(Exception e) {
				System.out.println(e);
			}

			try {
				TimeUnit.SECONDS.sleep(1);
			}
			catch(InterruptedException e) {
				System.out.println(e);
			}
		}
	}
}
