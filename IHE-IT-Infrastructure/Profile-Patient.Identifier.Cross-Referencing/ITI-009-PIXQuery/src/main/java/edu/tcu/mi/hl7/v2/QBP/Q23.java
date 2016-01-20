package edu.tcu.mi.hl7.v2.QBP;

import java.io.IOException;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.segments.QBP_Q21;
import edu.tcu.mi.ihe.hl7v25.info.MessageHeader;
import edu.tcu.mi.ihe.hl7v25.info.QueryParameterDefinition;


public class Q23 extends QBP {
	QBP_Q21 qbp = new QBP_Q21();
	
	public Q23(QueryParameterDefinition qpdSegment, MessageHeader mshSegment){
		try {
			qbp = new QBP_Q21();
			qbp.initQuickstart("QBP", "Q23", "P");
			mshSegment.setMsh(qbp.getMSH());
			msh = mshSegment.getMsh();
			qpdSegment.setQpd(qbp.getQPD());
			qpd = qpdSegment.getQpd();
			
			getRcp(qbp.getRCP());
			
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
			bs.append(qpd.encode()).append(Carriage_Return);
			bs.append(rcp.encode()).append(Carriage_Return);
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
