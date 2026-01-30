package classes.socket.multipleClientSupportingServer;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Client implements Closeable{
	final String ip;
	final int port;
	private Socket client;
	private DataInputStream in;
	private DataOutputStream out;
	Client(String ip, int port) throws UnknownHostException, IOException{
		this.ip=ip;
		this.port=port;
		bind();
	}
	private void bind() throws UnknownHostException, IOException {
		this.client = new Socket(ip,port);
		this.in=new DataInputStream(this.client.getInputStream());
		this.out=new DataOutputStream(this.client.getOutputStream());
	}
	public String request(String body) throws IOException {
		out.writeUTF(body);
		return in.readUTF();
	}
	public void close() throws IOException{
		if(this.client!=null) {
			try {this.client.close();}catch(Exception e) {}
			client=null;
		}
		if(this.in!=null) {
			try {this.in.close();}catch(Exception e) {}
			in=null;
		}
		if(this.out!=null) {
			try {this.out.close();}catch(Exception e) {}
			out=null;
		}
	}
	
	
}
