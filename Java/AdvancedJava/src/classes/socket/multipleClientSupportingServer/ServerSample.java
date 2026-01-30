package classes.socket.multipleClientSupportingServer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;

class MyResponder extends Responder{

	public MyResponder(Socket client) throws IOException {super(client);}
	@Override
	public String responseMessage(String requestMessage) {
		 return "FROM SERVER";
	}

	@Override
	public String processRequest(String requestMessage) {
		System.out.println("Got Message from client: " + requestMessage);
		return responseMessage(requestMessage);
		
	}

	@Override
	public boolean shallCloseNow(String requestMessage) {
		return requestMessage == null || requestMessage.isBlank() || client().isClosed() || client().isInputShutdown() || client().isOutputShutdown();
	}
	
	
}

public class ServerSample {
	public static void main(String[]args) throws IOException {
		try(Server<MyResponder> serv = new Server<>(1234, MyResponder.class)){
			serv.listen();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		
		
	}
}
