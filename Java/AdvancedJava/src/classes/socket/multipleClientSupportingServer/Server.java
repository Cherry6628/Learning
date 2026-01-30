package classes.socket.multipleClientSupportingServer;

import java.net.Socket;
import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;

public class Server<R extends Responder> implements Closeable {
	int port;
	private ServerSocket server;
	private final Class<R> responderClass;

	public Server(int port, Class<R> responderClass) throws IOException {
		this.responderClass=responderClass;
		this.port = port;
		bind();
	}

	private void bind() throws IOException {
		this.server = new ServerSocket(this.port);
	}

	private Socket acceptClient() throws IOException {
		if (this.server == null)
			throw new IOException("Socket is not initiated");
		return this.server.accept();
	}

	public void listen() throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		while (true) {
//			Responder respondingWorker = new R(acceptClient());
			Socket client = acceptClient();
			Responder respondingWorker = this.responderClass.getConstructor(Socket.class).newInstance(client);
			System.out.println("Client Connected: "+client.getRemoteSocketAddress().toString());
			Thread pWorker = new Thread(respondingWorker);
			pWorker.start();
		}
	}

	@Override
	public void close() throws IOException {
		port = 0;
		if (server != null)
			server.close();
		server = null;
	}
}
