package edu.tcu.mi.ihe;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import edu.tcu.mi.hl7v2.ADT.A01;
import edu.tcu.mi.hl7v2.ADT.A04;
import edu.tcu.mi.hl7v2.ADT.A05;
import edu.tcu.mi.hl7v2.ADT.A08;
import edu.tcu.mi.hl7v2.ADT.A40;
import edu.tcu.mi.ihe.actor.PIXSource;
import edu.tcu.mi.ihe.configuration.XDSConfiguration;
import edu.tcu.mi.ihe.hl7v231.info.MergePatientInformation;
import edu.tcu.mi.ihe.hl7v231.info.MessageHeader;
import edu.tcu.mi.ihe.hl7v231.info.PatientIdentification;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = XDSConfiguration.class)
@TestExecutionListeners(listeners = { DependencyInjectionTestExecutionListener.class })
public class PatientIdentityFeedTest {

	@Autowired
	private PIXSource pixSource;

	@Test
	public void testADT_A01() throws ParseException{
		MessageHeader msh = new MessageHeader();
		msh.setSendingApplication("foxb1249");
		msh.setSendingFacility("Gaduo");
		msh.setReceivingApplication("MESA_XREF");
		msh.setReceivingFacility("XYZ_HOSPITAL");
		msh.setMessageControlID("Gaduo-090528110022806");
		PatientIdentification pid = new PatientIdentification();
		pid.setPid03("20140519^^^IHENA&1.3.6.1.4.1.21367.2010.1.2.300&ISO");
		pid.setPid05("Wang^Dai-Wei^^^");
		pid.setPid07("19661109");
		pid.setPid08("F");
		pid.setPid11("Sec.2, Linong Street^^ Taipei ^112^ Taiwan");
		A01 a01 = new A01(pid, msh);
		String response = pixSource.patientIdentityFeed(a01);
		System.out.println(response);
	}
	
	@Test
	public void testADT_A04() throws ParseException{
		MessageHeader msh = new MessageHeader();
		msh.setSendingApplication("foxb1249");
		msh.setSendingFacility("Gaduo");
		msh.setReceivingApplication("MESA_XREF");
		msh.setReceivingFacility("XYZ_HOSPITAL");
		msh.setMessageControlID("Gaduo-090528110022806");
		PatientIdentification pid = new PatientIdentification();
		pid.setPid03("2013031143^^^IHENA&1.3.6.1.4.1.21367.2010.1.2.300&ISO");
		pid.setPid05("Wang^Dai-Wei^^^");
		pid.setPid07("19661109");
		pid.setPid08("F");
		pid.setPid11("Sec.2, Linong Street^^ Taipei ^112^ Taiwan");
		A04 a01 = new A04(pid, msh);
		String response = pixSource.patientIdentityFeed(a01);
		System.out.println(response);
	}
	
	@Test
	public void testADT_A05() throws ParseException{
		MessageHeader msh = new MessageHeader();
		msh.setSendingApplication("foxb1249");
		msh.setSendingFacility("Gaduo");
		msh.setReceivingApplication("MESA_XREF");
		msh.setReceivingFacility("XYZ_HOSPITAL");
		msh.setMessageControlID("Gaduo-090528110022806");
		PatientIdentification pid = new PatientIdentification();
		pid.setPid03("2013031143^^^IHENA&1.3.6.1.4.1.21367.2010.1.2.300&ISO");
		pid.setPid05("Wang^Dai-Wei^^^");
		pid.setPid07("19661109");
		pid.setPid08("F");
		pid.setPid11("Sec.2, Linong Street^^ Taipei ^112^ Taiwan");
		A05 a01 = new A05(pid, msh);
		String response = pixSource.patientIdentityFeed(a01);
		System.out.println(response);
	}
	
	@Test
	public void testADT_A08() throws ParseException{
		MessageHeader msh = new MessageHeader();
		msh.setSendingApplication("foxb1249");
		msh.setSendingFacility("Gaduo");
		msh.setReceivingApplication("MESA_XREF");
		msh.setReceivingFacility("XYZ_HOSPITAL");
		msh.setMessageControlID("Gaduo-090528110022806");
		PatientIdentification pid = new PatientIdentification();
		pid.setPid03("2013031143^^^IHENA&1.3.6.1.4.1.21367.2010.1.2.300&ISO");
		pid.setPid05("Wang^Dai-Wei^^^");
		pid.setPid07("19661109");
		pid.setPid08("F");
		pid.setPid11("Sec.2, Linong Street^^ Taipei ^112^ Taiwan");
		A08 a01 = new A08(pid, msh);
		String response = pixSource.patientIdentityFeed(a01);
		System.out.println(response);
	}
	
	@Test
	public void testADT_A40() throws ParseException{
		MessageHeader msh = new MessageHeader();
		msh.setSendingApplication("foxb1249");
		msh.setSendingFacility("Gaduo");
		msh.setReceivingApplication("MESA_XREF");
		msh.setReceivingFacility("XYZ_HOSPITAL");
		msh.setMessageControlID("Gaduo-090528110022806");
		PatientIdentification pid = new PatientIdentification();
		pid.setPid03("2013031143^^^IHENA&1.3.6.1.4.1.21367.2010.1.2.300&ISO");
		pid.setPid05("Wang^Dai-Wei^^^");
		pid.setPid07("19661109");
		pid.setPid08("F");
		pid.setPid11("Sec.2, Linong Street^^ Taipei ^112^ Taiwan");
		MergePatientInformation mrg = new MergePatientInformation();
		mrg.setMrg01("20140519^^^IHENA&1.3.6.1.4.1.21367.2010.1.2.300&ISO");
		mrg.setMrg07("2013031143^^^IHENA&1.3.6.1.4.1.21367.2010.1.2.300&ISO");
		A40 a01 = new A40(pid, msh, mrg);
		String response = pixSource.patientIdentityFeed(a01);
		System.out.println(response);
	}
	
}

