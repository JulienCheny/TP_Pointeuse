package controller;

import java.io.IOException;

import launchers.MainApp;
import modele.tcp_serialized_objects.CheckOfflineBuffer;


/**
 * The Class TCPServerController.
 */
public class TCPServerController extends TCPServerBuilder implements Runnable {
	
	/** The instance. */
	private static TCPServerController INSTANCE = null;
	
	/**
	 * Gets the single instance of TCPServerController.
	 *
	 * @return single instance of TCPServerController
	 */
	public static TCPServerController getInstance()
	{			
		if (INSTANCE == null)
		{ 	INSTANCE = new TCPServerController();	
		}
		return INSTANCE;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		while(true) {
				
			try {
				connect();
				System.out.println("server connected");
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}
			Object msg = null;
			try {
				msg = listenObj();
				
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			if(msg != null && msg.equals(new String("Update request")))
				try {
					MainApp.cop.serializeEmployeeList(outObj);
					System.out.println("server send employees list");
				} catch (IOException e) {
					e.printStackTrace();
				}
			else {
					int i;
					CheckOfflineBuffer offBuffer = (CheckOfflineBuffer) msg;
					for(i = 0; i < offBuffer.size();i++) {
						try {
							MainApp.cop.checkEmployee(offBuffer.getIdOnIndex(i), offBuffer.getDateOnIndex(i));
						} catch (Exception e) {
							e.printStackTrace();
						}
						MainApp.ctrlWin.updateWindow();
					}
			}
			try {
				close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
