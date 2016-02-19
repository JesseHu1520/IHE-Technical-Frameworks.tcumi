package edu.tcu.mi.ihe.mockmvc;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class MetadataBuilderMockMvcTest extends GenericMockMvcTest {

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
