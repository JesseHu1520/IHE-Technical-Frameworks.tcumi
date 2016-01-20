package edu.tcu.mi.spring.web.controller.rest;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.tcu.mi.spring.web.model.CodeType;
import edu.tcu.mi.spring.web.model.CodeTypeType;
import edu.tcu.mi.spring.web.model.CodesType;

@RestController
@RequestMapping("/api/AffinityDomain")
public class AffinityDomainRestController extends GenericRestController  {

	
	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	@ResponseBody
    public ResponseEntity<?> list(Principal principal){
		String body = "{}";
		ClassPathResource resource = new ClassPathResource("codes.xml");
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(CodesType.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			CodesType root = (CodesType) jaxbUnmarshaller.unmarshal(resource.getInputStream());
			CodesType _root = new CodesType();
			
			List<CodeTypeType> codeTypes = root.getCodeTypes();
			
			for(CodeTypeType codeType : codeTypes){
				String name = codeType.getName();
				String classScheme = codeType.getClassScheme();
				List<CodeType> codes = codeType.getCodes();
				for(CodeType code : codes){
					CodeTypeType _codeType = new CodeTypeType();
					_codeType.setName(name);
					_codeType.setClassScheme(classScheme);
					_codeType.addCode(code);
					_root.addCodeType(_codeType);
				}
			}
			
			String json = gson.toJson(_root.getCodeTypes());
			
			body = "{\"data\":" + json + "}";
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(body, headers, HttpStatus.OK);
    }
}
