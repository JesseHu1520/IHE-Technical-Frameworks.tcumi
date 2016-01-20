package edu.tcu.mi.spring.test;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml" })
@ActiveProfiles("mongodb")
public abstract class GenericTest {
	protected static final Logger logger = Logger.getLogger(GenericTest.class);

	protected Gson gson;
	
	@Before
	public void init(){
		gson = new Gson();
	}
}
