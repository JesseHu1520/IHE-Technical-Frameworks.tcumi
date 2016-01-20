package edu.tcu.mi.ihe.socket;

import java.io.InputStream;

import org.apache.axiom.om.OMElement;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import edu.tcu.mi.ihe.security.CertificateDetails;
import edu.tcu.mi.ihe.sender.socket.SocketSender;
import edu.tcu.mi.ihe.sender.ws.ServiceConsumer;
import edu.tcu.mi.ihe.sender.ws.Soap;

public class ServerAvailableTest {
    public static Logger logger = Logger.getLogger(ServerAvailableTest.class);

    @Before
    public void init(){
		CertificateDetails cert = CertificateDetails.getInstance();
		cert.setCertificate(
				"openxds_2010/OpenXDS_2010_Truststore.jks", "password", 
				"openxds_2010/OpenXDS_2010_Truststore.jks", "password");
    }
    
	@Test
	public void testSocket(){

		char Start_Block = '\u000b';
		char End_Block = '\u001c';
		char Carriage_Return = 13;
		String message = 
				Start_Block
				+ "MSH|^~\\&|foxb1249|Gaduo|MESA_XREF|XYZ_HOSPITAL|1400487411133||ADT^A01|Gaduo-090528110022806|P|2.3.1".trim() + Carriage_Return
				+ "EVN||1400487411133".trim() + Carriage_Return
				+ "PID|||2013031143^^^IHENA&1.3.6.1.4.1.21367.2010.1.2.300&ISO||Wang^Dai-Wei||19661109|19661109|||Sec.2, Linong Street^^ Taipei ^112^ Taiwan".trim() + Carriage_Return
				+ "PV1||o".trim() + Carriage_Return 
				+ End_Block + Carriage_Return ;

		SocketSender sender = SocketSender.getInstance();
		String msg = sender.send("203.64.84.247", 3612, message);
		logger.info(msg);
		
	}


	@Test
	public void testWebServiceITI_41(){
		String ACTION = "urn:ihe:iti:2007:ProvideAndRegisterDocumentSet-b";
		String repositoryUrl = "https://203.64.84.214:8021/axis2/services/xdsrepositoryb?wsdl";
		Soap soap = new ServiceConsumer(repositoryUrl, ACTION);
		((ServiceConsumer)soap).setMtomXop(true);
		Class<ServerAvailableTest> clazz = ServerAvailableTest.class;
		ClassLoader loader = clazz.getClassLoader();
		InputStream is = loader.getResourceAsStream("available/iti_41_available.xml");
		OMElement response = soap.send(is);
		logger.info(response);
	}
	

	@Test
	public void testWebServiceITI_18(){
		String ACTION = "urn:ihe:iti:2007:RegistryStoredQuery";
		String repositoryUrl = "https://203.64.84.214:8021/axis2/services/xdsregistryb?wsdl";
		Soap soap = new ServiceConsumer(repositoryUrl, ACTION);
		((ServiceConsumer)soap).setMtomXop(false);
		Class<ServerAvailableTest> clazz = ServerAvailableTest.class;
		ClassLoader loader = clazz.getClassLoader();
		InputStream is = loader.getResourceAsStream("available/iti_18_available.xml");
		OMElement response = soap.send(is);
		logger.info(response);
	}
	

	@Test
	public void testWebServiceITI_43(){
		String ACTION = "urn:ihe:iti:2007:RetrieveDocumentSet";
		String repositoryUrl = "https://203.64.84.214:8021/axis2/services/xdsrepositoryb?wsdl";
		Soap soap = new ServiceConsumer(repositoryUrl, ACTION);
		((ServiceConsumer)soap).setMtomXop(true);
		Class<ServerAvailableTest> clazz = ServerAvailableTest.class;
		ClassLoader loader = clazz.getClassLoader();
		InputStream is = loader.getResourceAsStream("available/iti_43_available.xml");
		OMElement response = soap.send(is);
		logger.info(response);
	}

}
