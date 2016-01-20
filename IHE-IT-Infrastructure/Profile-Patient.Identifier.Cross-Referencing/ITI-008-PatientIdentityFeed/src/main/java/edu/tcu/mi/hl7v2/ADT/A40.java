package edu.tcu.mi.hl7v2.ADT;

import java.io.IOException;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v231.message.ADT_A40;
import edu.tcu.mi.ihe.hl7v231.info.MergePatientInformation;
import edu.tcu.mi.ihe.hl7v231.info.MessageHeader;
import edu.tcu.mi.ihe.hl7v231.info.PatientIdentification;

public class A40 extends ADT {

	private ADT_A40 adt;

	/**
	 * ITI-08 transaction message, ADT^A40：合併病患資訊, merge patient - patient identifier list
	 * @param pidSegment
	 * @param mshSegment
	 * @param mrgSegment
	 */
	public A40(PatientIdentification pidSegment, MessageHeader mshSegment, MergePatientInformation mrgSegment) {
		try {
			adt = new ADT_A40();
			adt.initQuickstart("ADT", "A40", "P");			
			mshSegment.setMsh(adt.getMSH());
			msh = mshSegment.getMsh();
			evn = adt.getEVN();
			evn = this.getEvn(evn);
			pidSegment.setPid(adt.getPIDPD1MRGPV1().getPID());
			pid = pidSegment.getPid();
			mrgSegment.setMrg(adt.getPIDPD1MRGPV1().getMRG());
			mrg = mrgSegment.getMrg();
			pv1 = adt.getPIDPD1MRGPV1().getPV1();
			pv1 = this.getPv1(pv1);
		} catch (HL7Exception e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String toString() {
		StringBuffer bs = new StringBuffer();
		try {
			bs.append(Start_Block);
			bs.append(msh.encode()).append(Carriage_Return);
			bs.append(evn.encode()).append(Carriage_Return);
			bs.append(pid.encode()).append(Carriage_Return);
			bs.append(mrg.encode()).append(Carriage_Return);
			bs.append(pv1.encode()).append(Carriage_Return);
			bs.append(End_Block).append(Carriage_Return);
		} catch (HL7Exception e) {
			e.printStackTrace();
		}
		return bs.toString();
	}

	@Override
	public String getMessageFromHL7v2() {
		return toString() ;
	}

	@Override
	protected boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}

}
