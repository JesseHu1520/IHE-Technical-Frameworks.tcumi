package edu.tcu.mi.ihe;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class ServerSocketTest {
	
	@Test
	public void testApp() {
		ServerSocket server = null;
		try {
			server = new ServerSocket(2861);
		} catch (IOException e) {
			e.printStackTrace();
		} 

		while (true) {
			Socket socket = null;
			byte[] data = null;
			try {
				socket = server.accept();
				DataInputStream is;
				is = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
				data = new byte[4096];
				is.read(data);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (socket != null) {
					try {
						socket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (server != null) {
					try {
						server.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			long timestamp = System.currentTimeMillis();
			System.out.println(timestamp);
			File file = new File(timestamp + ".xml");
			DataOutputStream dos = null;
			try {
				dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
				dos.write(data);
				dos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (dos != null) {
					try {
						dos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	
}
