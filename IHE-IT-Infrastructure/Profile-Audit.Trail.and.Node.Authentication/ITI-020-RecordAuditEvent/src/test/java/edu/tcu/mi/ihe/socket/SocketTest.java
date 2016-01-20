package edu.tcu.mi.ihe.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import org.junit.Test;

import edu.tcu.mi.ihe.date.DateFormat;
import edu.tcu.mi.ihe.security.CertificateDetails;

public class SocketTest {

	@Test
	public void testSocket(){
		CertificateDetails cert = CertificateDetails.getInstance();
//		cert.setCertificate("openatna_2011/clientKeyStore", "password", "openatna_2011/clientKeyStore", "password");
		
		
		try {
//			Socket socket = new Socket("203.64.84.247", 2862);

			SSLSocketFactory f = (SSLSocketFactory) SSLSocketFactory.getDefault();
	        SSLSocket socket = (SSLSocket) f.createSocket("203.64.84.247", 2862);
	        socket.startHandshake();
			Class<? extends SocketTest> clazz = SocketTest.class;
			ClassLoader loader = clazz.getClassLoader();
			InputStream resource = loader.getResourceAsStream("openxds.xml");
			BufferedReader brResource = new BufferedReader(new InputStreamReader(resource));
			String temp = "", str = "";
			while( (temp = brResource.readLine()) != null){
				str += temp;
			}
			brResource.close();
			
			InetAddress localMachine = InetAddress.getLocalHost();
	        String version = "1";
	        String timestamp = DateFormat.formatDate(new java.util.Date()); //"2014-02-10T00:53:09.624-06:00";
	        String host = localMachine.getHostAddress();
	        String app = "GaduoSyslogSender";
	        String proc = "183";
	        String messageId = "IHE+RFC-3881";
	        
	        str = "<" + (85) + ">" + version + " " + timestamp + " " + host + " " + app + " " + proc + " " + messageId + " - " + str;
			str = str.replace("${timeStamp}", timestamp);
			System.out.println(str);
			byte[] bytes = str.getBytes("UTF-8");

	        OutputStream out = socket.getOutputStream();
	        for (int i = 0; i < 5; i++) {
	            // add message length plus space before message
	            out.write((String.valueOf(bytes.length) + " ").getBytes("UTF-8"));
	            out.write(bytes);
	            out.flush();
	        }
	        
	        InputStream in = socket.getInputStream();
	        BufferedReader bw = new BufferedReader(new InputStreamReader(in));
	        String string = "";
	        while((string = bw.readLine()) != null){
	        	System.out.println(string);
	        }
	        in.close();
	        out.close();
	        socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
