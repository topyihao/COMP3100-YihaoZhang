import java.net.Socket;
import java.net.ServerSocket;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import java.util.concurrent.TimeUnit;

public class TCPServer {
	public static void main(String[] args) throws IOException {
		
		int aPort = Integer.parseInt(args[0]);
		System.out.println("port number: " + aPort);
		ServerSocket ss = new ServerSocket(aPort);

		while(true) {
			try{
				Socket s = ss.accept(); //establishes connection
				DataInputStream din = new DataInputStream(s.getInputStream());
				DataOutputStream dout = new DataOutputStream(s.getOutputStream());

				System.out.println("target IP: " + s.getInetAddress() + "Target port: " + s.getPort());
				System.out.println("Local IP: " + s.getLocalAddress() + "Local port: " + s.getLocalPort());

				try {TimeUnit.SECONDS.sleep(10);} catch(InterruptedException e) {System.out.println(e);}

				String str = (String)din.readUTF();
				System.out.println("RCVD: " + str);

				dout.writeUTF("Good Day");
				System.out.println("sent good day");
				
				str = (String)din.readUTF();
				System.out.println("RCVD: " + str);

				dout.writeUTF("Bye");
				System.out.println("sent BYE");

				din.close();
				dout.close();
				s.close();

			}
			catch(Exception e) {
				System.out.println(e);
			}
		}
	}
}
