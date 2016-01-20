package edu.tcu.mi.ihe.ebxml;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

import edu.tcu.mi.ihe.iti.ebxml.ihe.DocumentResponseType;
import edu.tcu.mi.ihe.iti.ebxml.ihe.RetrieveDocumentSetResponseType;

public class RetrieveDocumentResponseTest {

	@Test
	public void test() throws JAXBException {
		Class<RetrieveDocumentResponseTest> clazz = RetrieveDocumentResponseTest.class;
		ClassLoader loader = clazz.getClassLoader();
		InputStream is = loader.getResourceAsStream("response.xml");
		
		JAXBContext jaxbContext = JAXBContext.newInstance(RetrieveDocumentSetResponseType.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		RetrieveDocumentSetResponseType rds = (RetrieveDocumentSetResponseType) jaxbUnmarshaller.unmarshal(is);
		
		
		List<DocumentResponseType> documents = rds.getDocumentResponses();
		Iterator<DocumentResponseType> dIterator = documents.iterator();
		while(dIterator.hasNext()){
			DocumentResponseType dNext = dIterator.next();
			System.out.println(dNext.getDocument().getValue());
		}
//		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//		jaxbMarshaller.marshal(rds, System.out);
		
	}

}
