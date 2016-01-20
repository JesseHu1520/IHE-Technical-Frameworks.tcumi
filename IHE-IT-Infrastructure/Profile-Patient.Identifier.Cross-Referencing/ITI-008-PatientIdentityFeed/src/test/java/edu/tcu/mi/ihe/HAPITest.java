package edu.tcu.mi.ihe;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v231.datatype.CX;
import ca.uhn.hl7v2.model.v231.datatype.HD;
import ca.uhn.hl7v2.model.v231.datatype.IS;
import ca.uhn.hl7v2.model.v231.datatype.ST;
import ca.uhn.hl7v2.model.v231.datatype.TS;
import ca.uhn.hl7v2.model.v231.datatype.TSComponentOne;
import ca.uhn.hl7v2.model.v231.datatype.XAD;
import ca.uhn.hl7v2.model.v231.datatype.XPN;
import ca.uhn.hl7v2.model.v231.message.ADT_A01;
import ca.uhn.hl7v2.model.v231.segment.EVN;
import ca.uhn.hl7v2.model.v231.segment.MSH;
import ca.uhn.hl7v2.model.v231.segment.PID;
import ca.uhn.hl7v2.model.v231.segment.PV1;
import ca.uhn.hl7v2.parser.EncodingNotSupportedException;
import ca.uhn.hl7v2.parser.Parser;

public class HAPITest {

	public static Logger logger = Logger.getLogger(HAPITest.class);

	String msg;

	@Before
	public void init() {
		msg = "MSH|^~\\&|foxb1249|Gaduo|NIST_RCVR_FOXB1249|NIST|20130520164102||ADT^A01^ADT_A01|Gaduo-20130520164102|P|2.3.1\r"
				+ "EVN||20130520164102\r"
				+ "PID|||gaduo^^^IHENA&1.3.6.1.4.1.21367.2010.1.2.300&ISO||簡^嘎^都^^||20140122|M|||foxb1249@gmail.com\r"
				+ "PV1||O\r";
	}

	@Test
	public void testADT_A01() {
		HapiContext context = new DefaultHapiContext();
		Parser p = context.getPipeParser();
		Message hapiMsg;
		try {
			// The parse method performs the actual parsing
			hapiMsg = p.parse(msg);
		} catch (EncodingNotSupportedException e) {
			e.printStackTrace();
			return;
		} catch (HL7Exception e) {
			e.printStackTrace();
			return;
		}
		String version = hapiMsg.getVersion();
		if (version.equals("2.3.1")) {
			ADT_A01 adtMsg = (ADT_A01) hapiMsg;
			PID pid = adtMsg.getPID();

			try {
				CX pid03 = pid.getPid3_PatientIdentifierList(0);
				ST id = pid03.getID();
				HD aa = pid03.getAssigningAuthority();
				logger.info(id);
				logger.info(aa.getNamespaceID());
				logger.info(aa.getUniversalID());

				XPN pid05 = pid.getPid5_PatientName(0);
				logger.info(pid05.getXpn1_FamilyLastName().getFamilyName());
				logger.info(pid05.getXpn2_GivenName().getValue());
				logger.info(pid05.getXpn3_MiddleInitialOrName().getValue());

				TS pid07 = pid.getPid7_DateTimeOfBirth();
				logger.info(pid07.getTimeOfAnEvent());

				IS pid08 = pid.getPid8_Sex();
				logger.info(pid08);

				XAD pid11 = pid.getPid11_PatientAddress(0);
				logger.info(pid11.getComponent(0));
			} catch (DataTypeException e) {
				e.printStackTrace();
			}
		}

	}

	PID parseHL7Message(String message) {
		// PID pid = new PID();
		int start = message.indexOf("PID");
		int end = message.indexOf("PV1");
		message = message.substring(start, end);
		String[] array = message.split("[|]");
		int i = 1;
		for (i = 1; i < array.length; i++) {
			switch (i) {
			case 3:
				logger.info(array[i]);
				// pid.setPid03(array[i]);
				break;
			case 5:
				logger.info(array[i]);
				// pid.setPid05(array[i]);
				break;
			case 7:
				logger.info(array[i]);
				break;
			case 8:
				logger.info(array[i]);
				// pid.setPid08(array[i]);
				break;
			case 11:
				logger.info(array[i]);
				// pid.setPid11(array[i]);
				break;
			}
		}
		if (i != array.length)
			// return pid;
			return null;
		return null;
	}

	@Test
	public void generateHL7v2_ADT_A01(){
		try {
			char Start_Block = '\u000b';
			char End_Block = '\u001c';
			char Carriage_Return = 13;
		    String SendingApplication = "foxb1249";
		    String SendingFacility = "Gaduo";
		    String ReceivingApplication = "NIST_RCVR_FOXB1249";
		    String ReceivingFacility = "NIST";
		    String MessageControlID = "20130520164102";
		    
		    ADT_A01 adt = new ADT_A01();
		    MSH msh = adt.getMSH();
		    EVN evn = adt.getEVN();
		    PID pid = adt.getPID();
		    PV1 pv1 = adt.getPV1();
			adt.initQuickstart("ADT", "A01", "P");
			
			msh.getSendingApplication().getNamespaceID().setValue(SendingApplication);
			msh.getSendingFacility().getNamespaceID().setValue(SendingFacility);
			msh.getMsh5_ReceivingApplication().getHd1_NamespaceID().setValue(ReceivingApplication);
			msh.getMsh6_ReceivingFacility().getHd1_NamespaceID().setValue(ReceivingFacility);
			msh.getDateTimeOfMessage().getTimeOfAnEvent().setValue(System.currentTimeMillis()+"");
			msh.getMsh10_MessageControlID().setValue(MessageControlID);
			
			TS ts = evn.getEvn2_RecordedDateTime(); 
			TSComponentOne componentOne = ts.getTimeOfAnEvent();
			componentOne.setValue(System.currentTimeMillis() + "");
			
			pid.getPid1_SetIDPID().parse("gaduo^^^IHENA&1.3.6.1.4.1.21367.2010.1.2.300&ISO");
			pid.getPid5_PatientName(0).parse("簡^嘎^都^^");
			pid.getPid7_DateTimeOfBirth().parse("20140122");
			pid.getPid8_Sex().parse("M");
			pid.getPid11_PatientAddress(0).parse("foxb1249@gmail.com");

			pv1 = adt.getPV1();
			IS is = pv1.getPatientClass();
			is.setValue("o");

			StringBuffer bs = new StringBuffer();
			try {
				bs.append(Start_Block);
				bs.append(msh.encode()).append(Carriage_Return);
				bs.append(evn.encode()).append(Carriage_Return);
				bs.append(pid.encode()).append(Carriage_Return);
				bs.append(pv1.encode()).append(Carriage_Return);
				bs.append(End_Block);
			} catch (HL7Exception e) {
				e.printStackTrace();
			}
			logger.info("\n" + bs.toString());

		} catch (HL7Exception e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
