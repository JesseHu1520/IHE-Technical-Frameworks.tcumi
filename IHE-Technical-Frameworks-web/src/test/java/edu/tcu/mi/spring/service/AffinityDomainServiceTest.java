package edu.tcu.mi.spring.service;

import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import edu.tcu.mi.spring.test.GenericTest;
import edu.tcu.mi.spring.web.model.CodesType;

public class AffinityDomainServiceTest extends GenericTest {

	@Test
	public void save() throws IOException{

		ClassPathResource resource = new ClassPathResource("codes.xml");
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(CodesType.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			CodesType codes = (CodesType) jaxbUnmarshaller.unmarshal(resource.getInputStream());
			System.out.println(codes);
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
