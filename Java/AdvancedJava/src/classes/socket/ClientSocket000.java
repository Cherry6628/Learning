package classes.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientSocket000 {
	int port;
	String ip;
	ClientSocket000(String ip, int port){
		this.ip=ip;
		this.port=port;
	}
	public void start() throws UnknownHostException, IOException {
		Socket client = new Socket(this.ip, this.port);
		InputStream inStream = client.getInputStream();
		OutputStream outStream = client.getOutputStream();
		DataInputStream in = new DataInputStream(inStream);
		DataOutputStream out = new DataOutputStream(outStream);
		String data = "\u0000";
		Scanner sc = new Scanner(System.in);
		while(!data.isEmpty()) {
			data = sc.nextLine();
			out.writeUTF(data);
			System.out.println(in.readUTF());
			
		}
		System.out.println("Terminated...");
		sc.close();
		client.close();
		
	}
	static public void main(String[]args) throws UnknownHostException, IOException {
		ClientSocket000 client = new ClientSocket000("localhost", 1234);
		client.start();
	}
}
