package classes.socket;

import java.net.Socket;
import java.io.IOException;
import java.net.ServerSocket;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class SocketServer000 {
	int port;
//	private char signal;
	SocketServer000(int port){
		this.port = port;
	}
	public void start() throws IOException {
		ServerSocket server = new ServerSocket(port);
		System.out.println("Server Started");
		Socket client = server.accept();
		System.out.println("Client Connected");
		DataInputStream in = new DataInputStream(client.getInputStream());
		DataOutputStream out = new DataOutputStream(client.getOutputStream());
		String data = "\u0000";
		while (true) {
		    data = in.readUTF();
		    
		    if (data != null && !data.isEmpty()) {
		        System.out.println("Got Message from client: " + data);
		        out.writeUTF("Got your message: " + data);
		    } else {
		        out.writeUTF("Closing Connection...");
		        break;
		    }
		}
		System.out.println("Terminated...");
		server.close();
		
	}
	static public void main(String[]args) {
		SocketServer000 server = new SocketServer000(1234);
		try {
			server.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
