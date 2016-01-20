package edu.tcu.mi.ihe;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.app.Connection;
import ca.uhn.hl7v2.app.Initiator;
import ca.uhn.hl7v2.llp.LLPException;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.parser.Parser;
import edu.tcu.mi.hl7v2.ADT.A01;
import edu.tcu.mi.hl7v2.ADT.A04;
import edu.tcu.mi.hl7v2.ADT.A05;
import edu.tcu.mi.hl7v2.ADT.A08;
import edu.tcu.mi.hl7v2.ADT.A40;
import edu.tcu.mi.ihe.hl7v231.info.MergePatientInformation;
import edu.tcu.mi.ihe.hl7v231.info.MessageHeader;
import edu.tcu.mi.ihe.hl7v231.info.PatientIdentification;
import edu.tcu.mi.ihe.sender.socket.SocketSender;

/**
 * Unit test for simple App.
 */
public class AppTest  {
	public static Logger logger = Logger.getLogger(AppTest.class);
	private String host ;
	private int port;
	
	@Before
	public void init(){
		Class<AppTest> clazz = AppTest.class;
		ClassLoader loader = clazz.getClassLoader();
		InputStream is = loader.getResourceAsStream("PatientIdentityFeed.properties");
		Properties pro = new Properties();
		try {
			pro.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		host = pro.getProperty("pix.manager.host");
		port = Integer.valueOf(pro.getProperty("pix.manager.port"));

//		ICertificate cert = Certificate .getInstance();
//		cert.setCertificate("openxds_2010/OpenXDS_2010_Truststore.jks", "password", 
//				"openxds_2010/OpenXDS_2010_Truststore.jks", "password");

		String certificate = loader.getResource("certificate/openxds_2010/OpenXDS_2010_Keystore.p12").toString().replace("file:/", "");
		System.setProperty("javax.net.ssl.keyStore", certificate);
		System.setProperty("javax.net.ssl.keyStorePassword", "password");
		
	}
	
	
	public void testHAPI(){
		
		try{
			MessageHeader msh = new MessageHeader();
			msh.setSendingApplication("foxb1249");
			msh.setSendingFacility("Gaduo");
			msh.setReceivingApplication("MESA_XREF");
			msh.setReceivingFacility("XYZ_HOSPITAL");
			msh.setMessageControlID("Gaduo-090528110022806");
			PatientIdentification pid = new PatientIdentification();
			pid.setPid03("2013031143^^^IHENA&1.3.6.1.4.1.21367.2010.1.2.300&ISO");
			pid.setPid05("Wang^Dai-Wei^^^");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			pid.setPid07("19661109");
			pid.setPid08("F");
			pid.setPid11("Sec.2, Linong Street^^ Taipei ^112^ Taiwan");
			A01 a01 = new A01(pid, msh);
			HapiContext context = new DefaultHapiContext();
	 		Connection connection = context.newClient(host, port, true);
	
	        Initiator initiator = connection.getInitiator();
	        Message response = initiator.sendAndReceive(a01.getADT_A01());
	        
	        Parser p = context.getPipeParser();
	        String responseString = p.encode(response);
		} catch(ca.uhn.hl7v2.HL7Exception e){
			e.printStackTrace();
		} catch (LLPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
