package edu.tcu.mi.ihe.sender.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import edu.tcu.mi.ihe.sender.MessageSender;

public class SocketSender extends MessageSender {
	private static SocketSender instance = null; 
	private SocketSender() {
		
	}
	public synchronized static SocketSender getInstance(){
		if(instance == null) {
			instance = new SocketSender();
		}
		return instance;
	}
	
	public String send(String ip, int port, String request) {
        logger.info("\n" + request);
        String response = null ;
        byte[] bytes = null;
        try {
            Socket socket = new Socket(ip, port);
            socket.setSoTimeout(2*10*1000);
            OutputStream os = socket.getOutputStream();
            InputStream is = socket.getInputStream();
            
            bytes = request.getBytes();
            os.write(bytes);
            os.flush();
            bytes = new byte[500];
            is.read(bytes);
            socket.close();
            response = new String(bytes);
        } catch (IOException e) {
        	logger.error(e.toString());
    		return e.toString();
        }
        logger.info("\n" + response);
		return response;
	}
}
