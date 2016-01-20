package edu.tcu.mi.ihe;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import edu.tcu.mi.hl7.v2.QBP.Q23;
import edu.tcu.mi.ihe.actor.PIXConsumer;
import edu.tcu.mi.ihe.configuration.XDSConfiguration;
import edu.tcu.mi.ihe.hl7v25.info.MessageHeader;
import edu.tcu.mi.ihe.hl7v25.info.QueryParameterDefinition;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = XDSConfiguration.class)
@TestExecutionListeners(listeners = { DependencyInjectionTestExecutionListener.class })
public class PIXQueryTest {

	@Autowired
	private PIXConsumer pixConsumer;

	@Test
	public void testQBP_Q23() throws ParseException{
		MessageHeader msh = new MessageHeader();
		msh.setSendingApplication("foxb1249");
		msh.setSendingFacility("Gaduo");
		msh.setReceivingApplication("MESA_XREF");
		msh.setReceivingFacility("XYZ_HOSPITAL");
		msh.setMessageControlID("Gaduo-090528110022806");
		
		QueryParameterDefinition qpd = new QueryParameterDefinition();
		qpd.setQpd01("IHE PIX Query");
        qpd.setQpd02("QRY1248968460880");
		qpd.setQpd03("XYZ10501^^^XREF2005&1.3.6.1.4.1.21367.2005.1.2&ISO^PI");
		qpd.setQpd04("ALPHA^ALAN");
		
		Q23 q23 = new Q23(qpd, msh);
		String msg = pixConsumer.pixQuery(q23);
		System.out.println(msg);
	}
}

