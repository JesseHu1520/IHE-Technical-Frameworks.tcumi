package edu.tcu.mi.ihe;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ApplicationTests {

	protected static Logger logger = Logger.getLogger(ApplicationTests.class);

    @InjectMocks
    @Autowired
    protected WebApplicationContext wac;

    protected MockMvc mockMvc;
//    protected TestingAuthenticationToken principal;
    

	protected Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	
	@Before
	public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
//        GaduoUserDetails user = new GaduoUserDetails(new User("gaduo.chien@gmail.com", "vul3cj4g4", "ROLE_ADMIN"));
//        principal = new TestingAuthenticationToken(user, null);
//        SecurityContextHolder.getContext().setAuthentication(principal);
	}
	

	private String api = "/base/ProvideAndRegisterDocumentSet";

	@Test
	public void post() {
		try {
//			MetadataBuilder builder = new MetadataBuilder();
//			String json = gson.toJson(builder);
			MockHttpServletRequestBuilder perform = MockMvcRequestBuilders
														.post(api + "")
//														.principal(principal)
														.contentType("application/json")
														.content("");
			ResultActions results = mockMvc.perform(perform)
	                						.andExpect(MockMvcResultMatchers.status().isOk());
			MvcResult result = results.andReturn();
			MockHttpServletResponse response = result.getResponse();
			logger.info(response.getStatus());
			logger.info(response.getContentAsString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
