package edu.tcu.mi.ihe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

import edu.tcu.mi.ihe.iti.rfc3881.AuditMessageType;

public class JaxbTest {

	//private Object //logger;

	@Test
	public void test() throws JAXBException, FileNotFoundException {
		Class<JaxbTest> clazz = JaxbTest.class;
		ClassLoader loader = clazz.getClassLoader();
		InputStream is = loader.getResourceAsStream("example/ITI-18_110112.dcm.xml");
		
		JAXBContext jaxbContext = JAXBContext.newInstance(AuditMessageType.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		AuditMessageType md = (AuditMessageType) jaxbUnmarshaller.unmarshal(is);
		//logger.info(md.buildEbXML());
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		File file = new File("a.xml");
		OutputStream fis = new FileOutputStream(file);
		jaxbMarshaller.marshal(md, fis);
		
	}
}
