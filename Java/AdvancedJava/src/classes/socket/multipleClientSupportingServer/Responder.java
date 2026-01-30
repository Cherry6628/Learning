package classes.socket.multipleClientSupportingServer;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.EOFException;
import java.net.Socket;

abstract class Responder implements Runnable, Closeable {
	private Socket client;
	private DataInputStream in;
	private DataOutputStream out;

	public Responder(Socket client) throws IOException {
		this.client = client;
		in = new DataInputStream(client.getInputStream());
		out = new DataOutputStream(client.getOutputStream());
	}

	public Socket client() {return this.client;}
	abstract public String responseMessage(String requestMessage);
	abstract public String processRequest(String requestMessage);
	abstract public boolean shallCloseNow(String requestMessage);

	public void run() {
		String data;
		while (true) {
			try {
				data = in.readUTF();
				if (shallCloseNow(data))
					break;
				out.writeUTF(processRequest(data));
			} 
			catch (EOFException e){break;}
			catch (IOException e) {break;}
			
		}
		try {
			this.close();
		} catch (IOException e) {
		}
	}

	public void close() throws IOException {
		if (in != null) {
			try {in.close();} catch (Exception e) {}
			in = null;
		}
		if (out != null) {
			try {out.close();} catch (Exception e) {}
			out = null;
		}
		if (client != null && client.isConnected()) {
			try {client.close();} catch (Exception e) {}
			client = null;
		}
	}
}
