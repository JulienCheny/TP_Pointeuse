package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * The Class TCPServerBuilder.
 */
public class TCPServerBuilder {
	
	/** The server socket. */
	private ServerSocket ss; /** The s. */
 Socket s;
	
	/** The address and port container. */
	private InetSocketAddress isA;
	
	/** The stream input for objects. */
	protected ObjectInputStream inObj;
	
	/** The stream output for objects. */
	protected ObjectOutputStream outObj;
	
	/**
	 * Initialize.
	 *
	 * @param port the port
	 */
	public void initialize (int port) {
		isA = new InetSocketAddress("localhost",port);
	}
	
	/**
	 * Connect.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void connect() throws IOException {
		ss = new ServerSocket(isA.getPort());		
		ss.setSoTimeout(300000);
		System.out.println("server wait a client connection ...");
		s = ss.accept();
		outObj = new ObjectOutputStream(s.getOutputStream()); 
		inObj = new ObjectInputStream(s.getInputStream());
		
	}
	
	/**
	 * Close.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void close() throws IOException {
		if(inObj != null) inObj.close();
		if(outObj != null) outObj.close();
		if(s !=null) {
			s.close();
			ss.close();
		}
	}
	
	/**
	 * Listen object stream.
	 *
	 * @return the object
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException the class not found exception
	 */
	protected Object listenObj() throws IOException, ClassNotFoundException{
		System.out.println("server listening ...");
		return inObj.readObject();
	}
}