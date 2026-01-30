package classes.socket.multipleClientSupportingServer;

import java.io.IOException;
import java.net.UnknownHostException;

public class ClientSample {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Client c = new Client("localhost", 1234);
		System.out.println(c.request("s"));
		
		c.close();
	}
}
