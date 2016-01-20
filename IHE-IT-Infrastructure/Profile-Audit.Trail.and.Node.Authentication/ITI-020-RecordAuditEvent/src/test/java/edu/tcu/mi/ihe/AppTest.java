package edu.tcu.mi.ihe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Test;

import edu.tcu.mi.ihe.AppTest;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

/**
 * Unit test for simple App.
 */
public class AppTest {

	public static Logger logger = Logger.getLogger(AppTest.class);

	
	@Test
	public void testAppParallel(){
//		int count = 100;
//		CountDownLatch latch = new CountDownLatch(count);
//		Executor executor = Executors.newFixedThreadPool(count);
//		for (int i = 0; i < count; i++) {
//			Worker worker = new Worker(i, latch);
//			executor.execute(worker);
//		}
//		while (latch.getCount() != 0){
//		}
	}
	
	
	/**
	 * Rigourous Test :-)
	 * http://gazelle-gold.wustl.edu/SyslogSender/Sender.jsf
	 */
	@Test
	public void testApp() {
		
		try{
			Class<? extends AppTest> clazz = this.getClass();
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
	        String timestamp = formatDate(new java.util.Date()); //"2014-02-10T00:53:09.624-06:00";
	        String host = localMachine.getHostAddress();
	        String app = "GaduoSyslogSender";
	        String proc = "183";
	        String messageId = "IHE+RFC-3881";
	        
	        str = "<" + (85) + ">" + version + " " + timestamp + " " + host + " " + app + " " + proc + " " + messageId + " - " + str;
			str = str.replace("${timeStamp}", timestamp);
			System.out.println(str);
			byte[] bytes = str.getBytes("UTF-8");
			DatagramPacket packet = new DatagramPacket(bytes, 0, bytes.length, new InetSocketAddress("203.64.84.214", 2861));
			DatagramSocket datagramSocket = new DatagramSocket();
			datagramSocket.send(packet);
			datagramSocket.close();
		

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public String formatDate(Date date) {
        StringBuilder sb = new StringBuilder();
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        c.setTime(date);
        sb.append(c.get(Calendar.YEAR));
        sb.append('-');
        int f = c.get(Calendar.MONTH);
        if (f < 9) {
            sb.append('0');
        }
        sb.append(f + 1);
        sb.append('-');
        f = c.get(Calendar.DATE);
        if (f < 10) {
            sb.append('0');
        }
        sb.append(f);
        sb.append('T');
        f = c.get(Calendar.HOUR_OF_DAY);
        if (f < 10) {
            sb.append('0');
        }
        sb.append(f);
        sb.append(':');
        f = c.get(Calendar.MINUTE);
        if (f < 10) {
            sb.append('0');
        }
        sb.append(f);
        sb.append(':');
        f = c.get(Calendar.SECOND);
        if (f < 10) {
            sb.append('0');
        }
        sb.append(f);
        sb.append('.');
        f = c.get(Calendar.MILLISECOND);
        if (f < 100) {
            sb.append('0');
        }
        if (f < 10) {
            sb.append('0');
        }
        sb.append(f);
        sb.append('Z');
        return sb.toString();
    }
	
	
	
	
	public void testBase64(){
		// MS4xOS42LjI0LjEwOS40Mi4xLjU=
		byte[] bytes = Base64.decodeBase64("MS4yLjg0MC4yNjk2NDkuOC44ODYuMTkyLjE2OC43LjMuMS4y".getBytes());
		logger.info(new String(bytes));

	}
	
	
}
